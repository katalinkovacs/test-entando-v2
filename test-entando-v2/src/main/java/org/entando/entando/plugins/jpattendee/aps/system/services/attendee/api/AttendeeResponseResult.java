/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee.api;

import javax.xml.bind.annotation.XmlElement;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;


public class AttendeeResponseResult extends AbstractApiResponseResult {
    
    @Override
    @XmlElement(name = "attendee", required = false)
    public JAXBAttendee getResult() {
        return (JAXBAttendee) this.getMainResult();
    }
    
}