/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.agiletec.aps.system.common.AbstractSearcherDAO;
import com.agiletec.aps.system.common.FieldSearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttendeeDAO extends AbstractSearcherDAO implements IAttendeeDAO {

	private static final Logger _logger =  LoggerFactory.getLogger(AttendeeDAO.class);

	@Override
	protected String getTableFieldName(String metadataFieldKey) {
		return metadataFieldKey;
	}
	
	@Override
	protected String getMasterTableName() {
		return "jpattendee_attendee";
	}
	
	@Override
	protected String getMasterTableIdFieldName() {
		return "id";
	}
	
	@Override
	protected boolean isForceCaseInsensitiveLikeSearch() {
		return true;
	}

	@Override
	public List<Integer> searchAttendees(FieldSearchFilter[] filters) {
		List attendeesId = null;
		try {
			attendeesId  = super.searchId(filters);
		} catch (Throwable t) {
			_logger.error("error in searchAttendees",  t);
			throw new RuntimeException("error in searchAttendees", t);
		}
		return attendeesId;
	}

	@Override
	public List<Integer> loadAttendees() {
		List<Integer> attendeesId = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			conn = this.getConnection();
			stat = conn.prepareStatement(LOAD_ATTENDEES_ID);
			res = stat.executeQuery();
			while (res.next()) {
				int id = res.getInt("id");
				attendeesId.add(id);
			}
		} catch (Throwable t) {
			_logger.error("Error loading Attendee list",  t);
			throw new RuntimeException("Error loading Attendee list", t);
		} finally {
			closeDaoResources(res, stat, conn);
		}
		return attendeesId;
	}
	
	@Override
	public void insertAttendee(Attendee attendee) {
		PreparedStatement stat = null;
		Connection conn  = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			this.insertAttendee(attendee, conn);
 			conn.commit();
		} catch (Throwable t) {
			this.executeRollback(conn);
			_logger.error("Error on insert attendee",  t);
			throw new RuntimeException("Error on insert attendee", t);
		} finally {
			this.closeDaoResources(null, stat, conn);
		}
	}

	public void insertAttendee(Attendee attendee, Connection conn) {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(ADD_ATTENDEE);
			int index = 1;
			stat.setInt(index++, attendee.getId());
 			stat.setString(index++, attendee.getName());
 			stat.setString(index++, attendee.getLname());
			stat.setInt(index++, attendee.getAge());
 			if(StringUtils.isNotBlank(attendee.getRemarks())) {
				stat.setString(index++, attendee.getRemarks());				
			} else {
				stat.setNull(index++, Types.VARCHAR);
			}
			stat.executeUpdate();
		} catch (Throwable t) {
			_logger.error("Error on insert attendee",  t);
			throw new RuntimeException("Error on insert attendee", t);
		} finally {
			this.closeDaoResources(null, stat, null);
		}
	}

	@Override
	public void updateAttendee(Attendee attendee) {
		PreparedStatement stat = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			this.updateAttendee(attendee, conn);
 			conn.commit();
		} catch (Throwable t) {
			this.executeRollback(conn);
			_logger.error("Error updating attendee {}", attendee.getId(),  t);
			throw new RuntimeException("Error updating attendee", t);
		} finally {
			this.closeDaoResources(null, stat, conn);
		}
	}

	public void updateAttendee(Attendee attendee, Connection conn) {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE_ATTENDEE);
			int index = 1;

 			stat.setString(index++, attendee.getName());
 			stat.setString(index++, attendee.getLname());
			stat.setInt(index++, attendee.getAge());
 			if(StringUtils.isNotBlank(attendee.getRemarks())) {
				stat.setString(index++, attendee.getRemarks());				
			} else {
				stat.setNull(index++, Types.VARCHAR);
			}
			stat.setInt(index++, attendee.getId());
			stat.executeUpdate();
		} catch (Throwable t) {
			_logger.error("Error updating attendee {}", attendee.getId(),  t);
			throw new RuntimeException("Error updating attendee", t);
		} finally {
			this.closeDaoResources(null, stat, null);
		}
	}

	@Override
	public void removeAttendee(int id) {
		PreparedStatement stat = null;
		Connection conn = null;
		try {
			conn = this.getConnection();
			conn.setAutoCommit(false);
			this.removeAttendee(id, conn);
 			conn.commit();
		} catch (Throwable t) {
			this.executeRollback(conn);
			_logger.error("Error deleting attendee {}", id, t);
			throw new RuntimeException("Error deleting attendee", t);
		} finally {
			this.closeDaoResources(null, stat, conn);
		}
	}
	
	public void removeAttendee(int id, Connection conn) {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(DELETE_ATTENDEE);
			int index = 1;
			stat.setInt(index++, id);
			stat.executeUpdate();
		} catch (Throwable t) {
			_logger.error("Error deleting attendee {}", id, t);
			throw new RuntimeException("Error deleting attendee", t);
		} finally {
			this.closeDaoResources(null, stat, null);
		}
	}

	public Attendee loadAttendee(int id) {
		Attendee attendee = null;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			conn = this.getConnection();
			attendee = this.loadAttendee(id, conn);
		} catch (Throwable t) {
			_logger.error("Error loading attendee with id {}", id, t);
			throw new RuntimeException("Error loading attendee with id " + id, t);
		} finally {
			closeDaoResources(res, stat, conn);
		}
		return attendee;
	}

	public Attendee loadAttendee(int id, Connection conn) {
		Attendee attendee = null;
		PreparedStatement stat = null;
		ResultSet res = null;
		try {
			stat = conn.prepareStatement(LOAD_ATTENDEE);
			int index = 1;
			stat.setInt(index++, id);
			res = stat.executeQuery();
			if (res.next()) {
				attendee = this.buildAttendeeFromRes(res);
			}
		} catch (Throwable t) {
			_logger.error("Error loading attendee with id {}", id, t);
			throw new RuntimeException("Error loading attendee with id " + id, t);
		} finally {
			closeDaoResources(res, stat, null);
		}
		return attendee;
	}

	protected Attendee buildAttendeeFromRes(ResultSet res) {
		Attendee attendee = null;
		try {
			attendee = new Attendee();				
			attendee.setId(res.getInt("id"));
			attendee.setName(res.getString("name"));
			attendee.setLname(res.getString("lname"));
			attendee.setAge(res.getInt("age"));
			attendee.setRemarks(res.getString("remarks"));
		} catch (Throwable t) {
			_logger.error("Error in buildAttendeeFromRes", t);
		}
		return attendee;
	}

	private static final String ADD_ATTENDEE = "INSERT INTO jpattendee_attendee (id, name, lname, age, remarks ) VALUES (?, ?, ?, ?, ? )";

	private static final String UPDATE_ATTENDEE = "UPDATE jpattendee_attendee SET  name=?,  lname=?,  age=?, remarks=? WHERE id = ?";

	private static final String DELETE_ATTENDEE = "DELETE FROM jpattendee_attendee WHERE id = ?";
	
	private static final String LOAD_ATTENDEE = "SELECT id, name, lname, age, remarks  FROM jpattendee_attendee WHERE id = ?";
	
	private static final String LOAD_ATTENDEES_ID  = "SELECT id FROM jpattendee_attendee";
	
}