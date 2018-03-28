/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee;

import java.util.List;
import com.agiletec.aps.system.exception.ApsSystemException;

import com.agiletec.aps.system.common.FieldSearchFilter;

public interface IAttendeeManager {

	public Attendee getAttendee(int id) throws ApsSystemException;

	public List<Integer> getAttendees() throws ApsSystemException;

	public List<Integer> searchAttendees(FieldSearchFilter filters[]) throws ApsSystemException;

	public void addAttendee(Attendee attendee) throws ApsSystemException;

	public void updateAttendee(Attendee attendee) throws ApsSystemException;

	public void deleteAttendee(int id) throws ApsSystemException;

}