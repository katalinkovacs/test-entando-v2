/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.apsadmin.attendee;

import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.Attendee;
import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.IAttendeeManager;



import com.agiletec.apsadmin.system.ApsAdminSystemConstants;
import com.agiletec.apsadmin.system.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttendeeAction extends BaseAction {

	private static final Logger _logger =  LoggerFactory.getLogger(AttendeeAction.class);

	public String newAttendee() {
		try {
			this.setStrutsAction(ApsAdminSystemConstants.ADD);
		} catch (Throwable t) {
			_logger.error("error in newAttendee", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String edit() {
		try {
			Attendee attendee = this.getAttendeeManager().getAttendee(this.getId());
			if (null == attendee) {
				this.addActionError(this.getText("error.attendee.null"));
				return INPUT;
			}
			this.populateForm(attendee);
			this.setStrutsAction(ApsAdminSystemConstants.EDIT);
		} catch (Throwable t) {
			_logger.error("error in edit", t);
			return FAILURE;
		}
		return SUCCESS;
	}

	public String save() {
		try {
			Attendee attendee = this.createAttendee();
			int strutsAction = this.getStrutsAction();
			if (ApsAdminSystemConstants.ADD == strutsAction) {
				this.getAttendeeManager().addAttendee(attendee);
			} else if (ApsAdminSystemConstants.EDIT == strutsAction) {
				this.getAttendeeManager().updateAttendee(attendee);
			}
		} catch (Throwable t) {
			_logger.error("error in save", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String trash() {
		try {
			Attendee attendee = this.getAttendeeManager().getAttendee(this.getId());
			if (null == attendee) {
				this.addActionError(this.getText("error.attendee.null"));
				return INPUT;
			}
			this.populateForm(attendee);
			this.setStrutsAction(ApsAdminSystemConstants.DELETE);
		} catch (Throwable t) {
			_logger.error("error in trash", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String delete() {
		try {
			if (this.getStrutsAction() == ApsAdminSystemConstants.DELETE) {
				this.getAttendeeManager().deleteAttendee(this.getId());
			}
		} catch (Throwable t) {
			_logger.error("error in delete", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	public String view() {
		try {
			Attendee attendee = this.getAttendeeManager().getAttendee(this.getId());
			if (null == attendee) {
				this.addActionError(this.getText("error.attendee.null"));
				return INPUT;
			}
			this.populateForm(attendee);
		} catch (Throwable t) {
			_logger.error("error in view", t);
			return FAILURE;
		}
		return SUCCESS;
	}
	
	private void populateForm(Attendee attendee) throws Throwable {
		this.setId(attendee.getId());
		this.setName(attendee.getName());
		this.setLname(attendee.getLname());
		this.setAge(attendee.getAge());
		this.setRemarks(attendee.getRemarks());
	}
	
	private Attendee createAttendee() {
		Attendee attendee = new Attendee();
		attendee.setId(this.getId());
		attendee.setName(this.getName());
		attendee.setLname(this.getLname());
		attendee.setAge(this.getAge());
		attendee.setRemarks(this.getRemarks());
		return attendee;
	}
	

	public int getStrutsAction() {
		return _strutsAction;
	}
	public void setStrutsAction(int strutsAction) {
		this._strutsAction = strutsAction;
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}

	public String getLname() {
		return _lname;
	}
	public void setLname(String lname) {
		this._lname = lname;
	}

	public int getAge() {
		return _age;
	}
	public void setAge(int age) {
		this._age = age;
	}

	public String getRemarks() {
		return _remarks;
	}
	public void setRemarks(String remarks) {
		this._remarks = remarks;
	}

	
	protected IAttendeeManager getAttendeeManager() {
		return _attendeeManager;
	}
	public void setAttendeeManager(IAttendeeManager attendeeManager) {
		this._attendeeManager = attendeeManager;
	}
	
	private int _strutsAction;
	private int _id;
	private String _name;
	private String _lname;
	private int _age;
	private String _remarks;
	
	private IAttendeeManager _attendeeManager;
	
}