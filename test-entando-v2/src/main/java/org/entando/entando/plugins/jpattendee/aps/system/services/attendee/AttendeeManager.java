/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee;

import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.event.AttendeeChangedEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;
import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.api.JAXBAttendee;
import org.entando.entando.aps.system.services.api.IApiErrorCodes;
import org.entando.entando.aps.system.services.api.model.ApiException;

import com.agiletec.aps.system.common.FieldSearchFilter;
import com.agiletec.aps.system.common.AbstractService;
import com.agiletec.aps.system.exception.ApsSystemException;
import com.agiletec.aps.system.services.keygenerator.IKeyGeneratorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttendeeManager extends AbstractService implements IAttendeeManager {

	private static final Logger _logger =  LoggerFactory.getLogger(AttendeeManager.class);

	@Override
	public void init() throws Exception {
		_logger.debug("{} ready.", this.getClass().getName());
	}
 
	@Override
	public Attendee getAttendee(int id) throws ApsSystemException {
		Attendee attendee = null;
		try {
			attendee = this.getAttendeeDAO().loadAttendee(id);
		} catch (Throwable t) {
			_logger.error("Error loading attendee with id '{}'", id,  t);
			throw new ApsSystemException("Error loading attendee with id: " + id, t);
		}
		return attendee;
	}

	@Override
	public List<Integer> getAttendees() throws ApsSystemException {
		List<Integer> attendees = new ArrayList<Integer>();
		try {
			attendees = this.getAttendeeDAO().loadAttendees();
		} catch (Throwable t) {
			_logger.error("Error loading Attendee list",  t);
			throw new ApsSystemException("Error loading Attendee ", t);
		}
		return attendees;
	}

	@Override
	public List<Integer> searchAttendees(FieldSearchFilter filters[]) throws ApsSystemException {
		List<Integer> attendees = new ArrayList<Integer>();
		try {
			attendees = this.getAttendeeDAO().searchAttendees(filters);
		} catch (Throwable t) {
			_logger.error("Error searching Attendees", t);
			throw new ApsSystemException("Error searching Attendees", t);
		}
		return attendees;
	}

	@Override
	public void addAttendee(Attendee attendee) throws ApsSystemException {
		try {
			int key = this.getKeyGeneratorManager().getUniqueKeyCurrentValue();
			attendee.setId(key);
			this.getAttendeeDAO().insertAttendee(attendee);
			this.notifyAttendeeChangedEvent(attendee, AttendeeChangedEvent.INSERT_OPERATION_CODE);
		} catch (Throwable t) {
			_logger.error("Error adding Attendee", t);
			throw new ApsSystemException("Error adding Attendee", t);
		}
	}
 
	@Override
	public void updateAttendee(Attendee attendee) throws ApsSystemException {
		try {
			this.getAttendeeDAO().updateAttendee(attendee);
			this.notifyAttendeeChangedEvent(attendee, AttendeeChangedEvent.UPDATE_OPERATION_CODE);
		} catch (Throwable t) {
			_logger.error("Error updating Attendee", t);
			throw new ApsSystemException("Error updating Attendee " + attendee, t);
		}
	}

	@Override
	public void deleteAttendee(int id) throws ApsSystemException {
		try {
			Attendee attendee = this.getAttendee(id);
			this.getAttendeeDAO().removeAttendee(id);
			this.notifyAttendeeChangedEvent(attendee, AttendeeChangedEvent.REMOVE_OPERATION_CODE);
		} catch (Throwable t) {
			_logger.error("Error deleting Attendee with id {}", id, t);
			throw new ApsSystemException("Error deleting Attendee with id:" + id, t);
		}
	}


	/**
	 * GET http://localhost:8080/<portal>/api/rs/en/attendees?
	 * @param properties
	 * @return
	 * @throws Throwable
	 */
	public List<JAXBAttendee> getAttendeesForApi(Properties properties) throws Throwable {
		List<JAXBAttendee> list = new ArrayList<JAXBAttendee>();
		List<Integer> idList = this.getAttendees();
		if (null != idList && !idList.isEmpty()) {
			Iterator<Integer> attendeeIterator = idList.iterator();
			while (attendeeIterator.hasNext()) {
				int currentid = attendeeIterator.next();
				Attendee attendee = this.getAttendee(currentid);
				if (null != attendee) {
					list.add(new JAXBAttendee(attendee));
				}
			}
		}
		return list;
	}

	/**
	 * GET http://localhost:8080/<portal>/api/rs/en/attendee?id=1
	 * @param properties
	 * @return
	 * @throws Throwable
	 */
    public JAXBAttendee getAttendeeForApi(Properties properties) throws Throwable {
        String idString = properties.getProperty("id");
        int id = 0;
		JAXBAttendee jaxbAttendee = null;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new ApiException(IApiErrorCodes.API_PARAMETER_VALIDATION_ERROR, "Invalid Integer format for 'id' parameter - '" + idString + "'", Response.Status.CONFLICT);
        }
        Attendee attendee = this.getAttendee(id);
        if (null == attendee) {
            throw new ApiException(IApiErrorCodes.API_VALIDATION_ERROR, "Attendee with id '" + idString + "' does not exist", Response.Status.CONFLICT);
        }
        jaxbAttendee = new JAXBAttendee(attendee);
        return jaxbAttendee;
    }

    /**
     * POST Content-Type: application/xml http://localhost:8080/<portal>/api/rs/en/attendee 
     * @param jaxbAttendee
     * @throws ApiException
     * @throws ApsSystemException
     */
    public void addAttendeeForApi(JAXBAttendee jaxbAttendee) throws ApiException, ApsSystemException {
        if (null != this.getAttendee(jaxbAttendee.getId())) {
            throw new ApiException(IApiErrorCodes.API_VALIDATION_ERROR, "Attendee with id " + jaxbAttendee.getId() + " already exists", Response.Status.CONFLICT);
        }
        Attendee attendee = jaxbAttendee.getAttendee();
        this.addAttendee(attendee);
    }

    /**
     * PUT Content-Type: application/xml http://localhost:8080/<portal>/api/rs/en/attendee 
     * @param jaxbAttendee
     * @throws ApiException
     * @throws ApsSystemException
     */
    public void updateAttendeeForApi(JAXBAttendee jaxbAttendee) throws ApiException, ApsSystemException {
        if (null == this.getAttendee(jaxbAttendee.getId())) {
            throw new ApiException(IApiErrorCodes.API_VALIDATION_ERROR, "Attendee with id " + jaxbAttendee.getId() + " does not exist", Response.Status.CONFLICT);
        }
        Attendee attendee = jaxbAttendee.getAttendee();
        this.updateAttendee(attendee);
    }

    /**
     * DELETE http://localhost:8080/<portal>/api/rs/en/attendee?id=1
	 * @param properties
     * @throws ApiException
     * @throws ApsSystemException
     */
    public void deleteAttendeeForApi(Properties properties) throws Throwable {
        String idString = properties.getProperty("id");
        int id = 0;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new ApiException(IApiErrorCodes.API_PARAMETER_VALIDATION_ERROR, "Invalid Integer format for 'id' parameter - '" + idString + "'", Response.Status.CONFLICT);
        }
        this.deleteAttendee(id);
    }

	private void notifyAttendeeChangedEvent(Attendee attendee, int operationCode) {
		AttendeeChangedEvent event = new AttendeeChangedEvent();
		event.setAttendee(attendee);
		event.setOperationCode(operationCode);
		this.notifyEvent(event);
	}


	protected IKeyGeneratorManager getKeyGeneratorManager() {
		return _keyGeneratorManager;
	}
	public void setKeyGeneratorManager(IKeyGeneratorManager keyGeneratorManager) {
		this._keyGeneratorManager = keyGeneratorManager;
	}

	public void setAttendeeDAO(IAttendeeDAO attendeeDAO) {
		 this._attendeeDAO = attendeeDAO;
	}
	protected IAttendeeDAO getAttendeeDAO() {
		return _attendeeDAO;
	}

	private IKeyGeneratorManager _keyGeneratorManager;
	private IAttendeeDAO _attendeeDAO;
}
