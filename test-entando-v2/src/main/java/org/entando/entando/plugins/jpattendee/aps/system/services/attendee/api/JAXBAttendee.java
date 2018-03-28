/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee.api;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.entando.entando.plugins.jpattendee.aps.system.services.attendee.Attendee;

@XmlRootElement(name = "attendee")
@XmlType(propOrder = {"id", "name", "lname", "age", "remarks"})
public class JAXBAttendee {

    public JAXBAttendee() {
        super();
    }

    public JAXBAttendee(Attendee attendee) {
		this.setId(attendee.getId());
		this.setName(attendee.getName());
		this.setLname(attendee.getLname());
		this.setAge(attendee.getAge());
		this.setRemarks(attendee.getRemarks());
    }
    
    public Attendee getAttendee() {
    	Attendee attendee = new Attendee();
		attendee.setId(this.getId());
		attendee.setName(this.getName());
		attendee.setLname(this.getLname());
		attendee.setAge(this.getAge());
		attendee.setRemarks(this.getRemarks());
    	return attendee;
    }

	@XmlElement(name = "id", required = true)
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}

	@XmlElement(name = "name", required = true)
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}

	@XmlElement(name = "lname", required = true)
	public String getLname() {
		return _lname;
	}
	public void setLname(String lname) {
		this._lname = lname;
	}

	@XmlElement(name = "age", required = true)
	public int getAge() {
		return _age;
	}
	public void setAge(int age) {
		this._age = age;
	}

	@XmlElement(name = "remarks", required = true)
	public String getRemarks() {
		return _remarks;
	}
	public void setRemarks(String remarks) {
		this._remarks = remarks;
	}


	private int _id;
	private String _name;
	private String _lname;
	private int _age;
	private String _remarks;

}
