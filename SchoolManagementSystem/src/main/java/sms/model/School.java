package sms.model;

import java.util.ArrayList;
import java.util.List;

public class School {
	private String id;
	private String name;
	private String address;
	private int numOfTeachers;
	private int numOfStudents;
	private List<Teacher> teachers = new ArrayList<Teacher>();

	public School() {
		super();
	}

	public School(String id) {
		super();
		this.id = id;
	}

	public School(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public School(String name, String address, int numOfTeachers, int numOfStudents) {
		super();
		this.name = name;
		this.address = address;
		this.numOfTeachers = numOfTeachers;
		this.numOfStudents = numOfStudents;
	}

	public School(String id, String name, int numOfStudents, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.numOfStudents = numOfStudents;
	}

	public School(String name, String address, int numOfTeachers, int numOfStudents, List<Teacher> teachers) {
		super();
		this.name = name;
		this.address = address;
		this.numOfTeachers = numOfTeachers;
		this.numOfStudents = numOfStudents;
		this.teachers = teachers;
	}

	public School(String id, String name, String address, int numOfTeachers, int numOfStudents,
			List<Teacher> teachers) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.numOfTeachers = numOfTeachers;
		this.numOfStudents = numOfStudents;
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

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
		this.numOfTeachers = teachers.size();
	}

	@Override
	public String toString() {
		return "School name: " + this.name + "\nAddress: " + this.address + "\nNumber of Teachers: "
				+ this.numOfTeachers + "\nNumber of Students: " + this.numOfStudents;
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
		numOfTeachers = teachers.size();
	}
}
