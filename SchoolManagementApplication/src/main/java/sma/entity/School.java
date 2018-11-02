package sma.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import sma.entity.Teacher;

@Data
@ToString 
@EqualsAndHashCode(exclude= "id")
public class School {
	
	public School() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String id;
	private String name;
	private String address;
	private String phone;
	private int numOfTeachers;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	public School(String id, String name, int numOfTeachers, String address) {
		super();
		this.id = id;
		this.name = name;
		this.numOfTeachers = numOfTeachers;
		this.address = address;
	}

	public School(String id, String name, String address, String phone, int numOfTeachers, List<Teacher> teachers) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.numOfTeachers = numOfTeachers;
		this.teachers = teachers;
	}

	public int getNumOfTeachers() {
		return teachers.size();
	}

	public void signContractWith(Teacher teacher) {
		this.teachers.add(teacher);
		this.numOfTeachers = teachers.size();
	}
	
		
	
}
