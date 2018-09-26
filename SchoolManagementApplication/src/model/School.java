/*
 * Title
 * 
 * @author Huy
 * @date Sep 26, 2018
 * @version 1.0
 */
package model;

import java.util.List;

public class School {
	private String name;
	private String address;
	private List<Teacher> teacherList;
	private List<Student> studentList;
	
	public School() {
		super();
	}

	public School(String name, String address, List<Teacher> teacherList, List<Student> studentList) {
		super();
		this.name = name;
		this.address = address;
		this.teacherList = teacherList;
		this.studentList = studentList;
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

	public List<Teacher> getNumOfTeachers() {
		return teacherList;
	}

	public void setNumOfTeachers(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public List<Student> getNumOfStudents() {
		return studentList;
	}

	public void setNumOfStudents(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\n"
				+ "Address: " + address + "\n"
				+ "The number of teachers: " + teacherList.size() + "\n"
				+ "The number of students: " + studentList.size() + "\n";
	}
	
}
