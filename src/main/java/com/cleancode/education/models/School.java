package com.cleancode.education.models;

import java.util.*;

public class School {
	
	private int id;
	private String name;
	private List<Teacher> teachers = new ArrayList<Teacher>();
	private int numberOfTeacher;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public int getNumberOfTeacher() {
		return teachers.size();
	}
	// see how we disable this! to discuss
//	public void setNumberOfTeacher(int numberOfTeacher) {
//		this.numberOfTeacher = numberOfTeacher;
//	}
	
	// see how signContractWith is used here!
	public void signContractWith(Teacher teacher) {
		teachers.add(teacher);
	}
	
}
