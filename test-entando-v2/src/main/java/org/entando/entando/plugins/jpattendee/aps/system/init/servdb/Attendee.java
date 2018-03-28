/*
 *
 * <Your licensing text here>
 *
 */
package org.entando.entando.plugins.jpattendee.aps.system.init.servdb;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Attendee.TABLE_NAME)
public class Attendee {
	
	public Attendee() {}
	
	@DatabaseField(columnName = "id", 
		dataType = DataType.INTEGER, 
		 canBeNull=false, id = true)
	private int _id;
	
	@DatabaseField(columnName = "name", 
		dataType = DataType.STRING, 
		width=30,  canBeNull=false)
	private String _name;
	
	@DatabaseField(columnName = "lname", 
		dataType = DataType.STRING, 
		width=30,  canBeNull=false)
	private String _lname;
	
	@DatabaseField(columnName = "age", 
		dataType = DataType.INTEGER, 
		 canBeNull=false)
	private int _age;
	
	@DatabaseField(columnName = "remarks", 
		dataType = DataType.STRING, 
		width=150,  canBeNull= true)
	private String _remarks;
	

public static final String TABLE_NAME = "jpattendee_attendee";
}
