/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.models;

import java.util.ArrayList;
import java.util.List;

public class School {
	private String id;
	private String name;
	private String address;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private int numberOfStudent;
	
	public School() {
		super();
	}
	
	public School(String id, String name, String address, List<Teacher> teachers, int numberOfStudent) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.teachers = teachers;
		this.numberOfStudent = numberOfStudent;
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
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}

	public int getNumberOfTeacher() {
		return teachers.size();
	}
	
	public void signContractWith(Teacher teacher) {
		teachers.add(teacher);
	}
	
	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", address=" + address + ", teachers=" + teachers
				+ ", numberOfStudent=" + numberOfStudent + "]";
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
