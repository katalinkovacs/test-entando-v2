/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.services.attendee;



public class Attendee {

	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}

	public String getLname() {
		return _lname;
	}
	public void setLname(String lname) {
		this._lname = lname;
	}

	public int getAge() {
		return _age;
	}
	public void setAge(int age) {
		this._age = age;
	}

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
