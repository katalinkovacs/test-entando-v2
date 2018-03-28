/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee.event;

import com.agiletec.aps.system.common.IManager;
import com.agiletec.aps.system.common.notify.ApsEvent;
import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.Attendee;


public class AttendeeChangedEvent extends ApsEvent {
	
	@Override
	public void notify(IManager srv) {
		((AttendeeChangedObserver) srv).updateFromAttendeeChanged(this);
	}
	
	@Override
	public Class getObserverInterface() {
		return AttendeeChangedObserver.class;
	}
	
	public int getOperationCode() {
		return _operationCode;
	}
	public void setOperationCode(int operationCode) {
		this._operationCode = operationCode;
	}
	
	public Attendee getAttendee() {
		return _attendee;
	}
	public void setAttendee(Attendee attendee) {
		this._attendee = attendee;
	}

	private Attendee _attendee;
	private int _operationCode;
	
	public static final int INSERT_OPERATION_CODE = 1;
	public static final int REMOVE_OPERATION_CODE = 2;
	public static final int UPDATE_OPERATION_CODE = 3;

}
