package sms.model;

import java.io.Serializable;
import java.util.List;

public class School implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String address;
	private int numberOfTeachers;
	private int numberOfStudents;
 	private List<Teacher> teachers;
	
	public School() {super();}
	
	public School(String id, String name, int numberOfStudents, String address, int numberOfTeachers, List<Teacher> teachers) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.numberOfStudents = numberOfStudents;
		this.numberOfTeachers = numberOfTeachers;
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


	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public int getNumberOfTeachers() {
		return numberOfTeachers;
	}

	public void setNumberOfTeachers(int numberOfTeachers) {
		this.numberOfTeachers = numberOfTeachers;
	}

	@Override
	public String toString() {
		return "============== "+name+" ==============\n"
			+  "- Id: "+id+"\n"
			+  "- Address: "+address+"\n"
			+  "- Number of students: "+numberOfStudents+"\n"
			+  "- Number of teachers: "+numberOfTeachers+"\n";
	}

	
}
