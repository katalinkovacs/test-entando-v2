/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.apsadmin.attendee;

import java.util.List;
import org.apache.commons.lang.StringUtils;

import com.agiletec.aps.system.common.FieldSearchFilter;
import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.Attendee;
import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.IAttendeeManager;
import com.agiletec.apsadmin.system.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttendeeFinderAction extends BaseAction {

	private static final Logger _logger =  LoggerFactory.getLogger(AttendeeFinderAction.class);

	public List<Integer> getAttendeesId() {
		try {
			FieldSearchFilter[] filters = new FieldSearchFilter[0];
			if (null != this.getId()) {
				//TODO add a constant into your IAttendeeManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("id"), this.getId(), false);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (StringUtils.isNotBlank(this.getName())) {
				//TODO add a constant into your IAttendeeManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("name"), this.getName(), true);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (StringUtils.isNotBlank(this.getLname())) {
				//TODO add a constant into your IAttendeeManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("lname"), this.getLname(), true);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (null != this.getAge()) {
				//TODO add a constant into your IAttendeeManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("age"), this.getAge(), false);
				filters = this.addFilter(filters, filterToAdd);
			}
			if (StringUtils.isNotBlank(this.getRemarks())) {
				//TODO add a constant into your IAttendeeManager class
				FieldSearchFilter filterToAdd = new FieldSearchFilter(("remarks"), this.getRemarks(), true);
				filters = this.addFilter(filters, filterToAdd);
			}
			List<Integer> attendees = this.getAttendeeManager().searchAttendees(filters);
			return attendees;
		} catch (Throwable t) {
			_logger.error("Error getting attendees list", t);
			throw new RuntimeException("Error getting attendees list", t);
		}
	}

	protected FieldSearchFilter[] addFilter(FieldSearchFilter[] filters, FieldSearchFilter filterToAdd) {
		int len = filters.length;
		FieldSearchFilter[] newFilters = new FieldSearchFilter[len + 1];
		for(int i=0; i < len; i++){
			newFilters[i] = filters[i];
		}
		newFilters[len] = filterToAdd;
		return newFilters;
	}

	public Attendee getAttendee(int id) {
		Attendee attendee = null;
		try {
			attendee = this.getAttendeeManager().getAttendee(id);
		} catch (Throwable t) {
			_logger.error("Error getting attendee with id {}", id, t);
			throw new RuntimeException("Error getting attendee with id " + id, t);
		}
		return attendee;
	}


	public Integer getId() {
		return _id;
	}
	public void setId(Integer id) {
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


	public Integer getAge() {
		return _age;
	}
	public void setAge(Integer age) {
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
	
	private Integer _id;
	private String _name;
	private String _lname;
	private Integer _age;
	private String _remarks;
	private IAttendeeManager _attendeeManager;
}