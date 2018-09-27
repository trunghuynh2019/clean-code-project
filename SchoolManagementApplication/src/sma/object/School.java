/*
 * Title
 * 
 * @author Huy
 * @date Sep 26, 2018
 * @version 1.0
 */
package sma.object;

import java.util.ArrayList;
import java.util.List;

public class School {
	private String name;
	private String address;
	private List<Teacher> teacherList = new ArrayList<>();
	private int numOfStudents;
	
	public School() {
		super();
	}

	public School(String name, String address, List<Teacher> teacherList, int numOfStudents) {
		super();
		this.name = name;
		this.address = address;
		this.teacherList = teacherList;
		this.numOfStudents = numOfStudents;
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

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public int getNumOfStudents() {
		return numOfStudents;
	}

	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}

	@Override
	public String toString() {
		return "===========================================\n" 
				+ "School's name: " + name + "\n"
				+ "Address: " + address + "\n"
				+ "The number of teachers: " + teacherList.size() + "\n"
				+ "The number of students: " + numOfStudents + "\n"
				+ "===========================================\n";
	}
	
}
