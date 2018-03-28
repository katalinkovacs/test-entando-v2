/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.apsadmin.portal.specialwidget.attendee;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.agiletec.aps.system.services.lang.Lang;
import com.agiletec.apsadmin.portal.specialwidget.SimpleWidgetConfigAction;
import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.IAttendeeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttendeeConfigAction extends SimpleWidgetConfigAction {

	private static final Logger _logger =  LoggerFactory.getLogger(AttendeeConfigAction.class);
	
	protected String extractInitConfig() {
		String result = super.extractInitConfig();
		String id = this.getWidget().getConfig().getProperty("id");
		if (StringUtils.isNotBlank(id)) {
			this.setId(new Integer(id));
		}
		return result;
	}

	public List<Integer> getAttendeesId() {
		try {
			List<Integer> attendees = this.getAttendeeManager().searchAttendees(null);
			return attendees;
		} catch (Throwable t) {
			_logger.error("error in getAttendeesId", t);
			throw new RuntimeException("Error getting attendees list", t);
		}
	}
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}

	protected IAttendeeManager getAttendeeManager() {
		return _attendeeManager;
	}
	public void setAttendeeManager(IAttendeeManager attendeeManager) {
		this._attendeeManager = attendeeManager;
	}

	private int _id;
	private IAttendeeManager _attendeeManager;
}

