/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services;

import org.entando.entando.plugins.jpattendee.aps.JpattendeeBaseTestCase;
import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.IAttendeeManager;

public class TestAttendeeManager extends JpattendeeBaseTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.init();
	}
	
	public void testGetAttendee() {
		//TODO complete test
		assertNotNull(this._attendeeManager);
	}

	public void testGetAttendees() {
		//TODO complete test
		assertNotNull(this._attendeeManager);
	}
	
	public void testSearchAttendees() {
		//TODO complete test
		assertNotNull(this._attendeeManager);
	}

	public void testAddAttendee() {
		//TODO complete test
		assertNotNull(this._attendeeManager);
	}

	public void testUpdateAttendee() {
		//TODO complete test
		assertNotNull(this._attendeeManager);
	}

	public void testDeleteAttendee() {
		//TODO complete test
		assertNotNull(this._attendeeManager);
	}
	
	private void init() {
		//TODO add the spring bean id as constant
		this._attendeeManager = (IAttendeeManager) this.getService("jpattendeeAttendeeManager");
	}
	
	private IAttendeeManager _attendeeManager;
}

