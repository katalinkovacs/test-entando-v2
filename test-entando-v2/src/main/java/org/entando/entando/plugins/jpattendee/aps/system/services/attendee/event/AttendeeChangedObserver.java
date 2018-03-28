/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee.event;

import com.agiletec.aps.system.common.notify.ObserverService;

public interface AttendeeChangedObserver extends ObserverService {
	
	public void updateFromAttendeeChanged(AttendeeChangedEvent event);
	
}
