package sma.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data 
@ToString 
@EqualsAndHashCode(of = {"id","name"})
public class Teacher {
	
	private String id;
	private String name;
	private String schoolId;
	private String address;
	private String phone;
	
	

	public Teacher(String id, String name, String schoolId) {
		super();
		this.id = id;
		this.name = name;
		this.schoolId = schoolId;
	}
	
	public Teacher(String id, String name, String address, String phone, String schoolId) {
		this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.schoolId = schoolId;
    }

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}


}
