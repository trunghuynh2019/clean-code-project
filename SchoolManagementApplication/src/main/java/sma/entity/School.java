package sma.entity;

import java.util.ArrayList;
import java.util.List;

import sma.entity.Teacher;

public class School {
	
	String id;
	String name;
	String address;
	String phone;
	int numOfTeachers;
	List<Teacher> teachers = new ArrayList<Teacher>();

	public School() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getNumOfTeachers() {
		return numOfTeachers;
	}

	public void setNumOfTeachers(int numberOfTeacher) {
		this.numOfTeachers = numberOfTeacher;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
		this.numOfTeachers = teachers.size();
	}

	public void signContractWith(Teacher teacher) {
		this.teachers.add(teacher);
		this.numOfTeachers = teachers.size();
	}
	
	public boolean equalId(School school) {
		return this.id.equals(school.getId());
	}
	
	public String getTeacherId() {
		if (teachers.isEmpty()) {
			return "";
		}else {
			String teachersId = teachers.get(0).getId();
			for (int i = 1; i < teachers.size(); i++) {
				teachersId += "," + teachers.get(i).getId() ;
			}
			return teachersId;
		}
		
	}
}
