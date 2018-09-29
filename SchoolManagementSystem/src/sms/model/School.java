package sms.model;

import java.util.ArrayList;
import java.util.List;

public class School {
	private String name;
	private String address;
	private int numOfTeachers;
	private int numOfStudents;
	private List<Teacher> teacherList = new ArrayList<>();
	
	public School(String name, String address, int numOfTeachers, int numOfStudents) {
		super();
		this.name = name;
		this.address = address;
		this.numOfTeachers = numOfTeachers;
		this.numOfStudents = numOfStudents;
	}

	public School(String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.numOfTeachers = 0;
		this.numOfStudents = 0;
	}

	public School(String name) {
		super();
		this.name = name;
		this.address = "";
		this.numOfTeachers = 0;
		this.numOfStudents = 0;
	}

	public School() {
		super();
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

	public int getNumOfTeachers() {
		return numOfTeachers;
	}

	public void setNumOfTeachers(int numOfTeachers) {
		this.numOfTeachers = numOfTeachers;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	@Override
	public String toString() {
		return "School name: " + this.name + "\nAddress: " + this.address + "\nNumber of Teachers: "
				+ this.numOfTeachers + "\nNumber of Students: " + this.numOfStudents;
	}

}
