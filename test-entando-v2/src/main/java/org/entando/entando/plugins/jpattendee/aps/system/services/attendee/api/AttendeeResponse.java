/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.entando.entando.aps.system.services.api.model.AbstractApiResponse;
import org.entando.entando.aps.system.services.api.model.AbstractApiResponseResult;


@XmlRootElement(name = "response")
public class AttendeeResponse extends AbstractApiResponse {
    
    @Override
    @XmlElement(name = "result", required = true)
    public AttendeeResponseResult getResult() {
        return (AttendeeResponseResult) super.getResult();
    }
    
    @Override
    protected AbstractApiResponseResult createResponseResultInstance() {
        return new AttendeeResponseResult();
    }
    
}