/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;
import org.entando.entando.aps.system.services.api.model.ListResponse;

@XmlSeeAlso({JAXBAttendee.class})
public class AttendeeListResponseResult extends AbstractApiResponseResult {
    
    @XmlElement(name = "items", required = false)
    public ListResponse<JAXBAttendee> getResult() {
        if (this.getMainResult() instanceof Collection) {
            List<JAXBAttendee> attendees = new ArrayList<JAXBAttendee>();
            attendees.addAll((Collection<JAXBAttendee>) this.getMainResult());
            ListResponse<JAXBAttendee> entity = new ListResponse<JAXBAttendee>(attendees) {};
            return entity;
        }
        return null;
    }

}