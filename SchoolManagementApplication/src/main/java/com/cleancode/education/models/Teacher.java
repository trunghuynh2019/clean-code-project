/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.models;

public class Teacher {
	private String id;
	private String name;
	private String schoolId;
	
	public Teacher() {
		super();
	}
	
	public Teacher(String id, String name, String schoolId) {
		super();
		this.id = id;
		this.name = name;
		this.schoolId = schoolId;
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
	
	public String getSchoolId() {
		return schoolId;
	}
	
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	@Override
	public String toString() {
		return "Teacher [Id=" + id + ", name=" + name + ", schoolId=" + schoolId + "]";
	}

	public boolean equalId(Teacher teacher) {
		return this.id.equals(teacher.getId());
	}
	
}
