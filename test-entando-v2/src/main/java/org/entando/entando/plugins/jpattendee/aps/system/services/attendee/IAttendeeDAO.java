/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee;

import java.util.List;

import com.agiletec.aps.system.common.FieldSearchFilter;

public interface IAttendeeDAO {

	public List<Integer> searchAttendees(FieldSearchFilter[] filters);
	
	public Attendee loadAttendee(int id);

	public List<Integer> loadAttendees();

	public void removeAttendee(int id);
	
	public void updateAttendee(Attendee attendee);

	public void insertAttendee(Attendee attendee);
	

}