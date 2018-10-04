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
	private String schoolCode;
	
	public Teacher() {
		super();
	}
	
	public Teacher(String id, String name, String schoolCode) {
		super();
		this.id = id;
		this.name = name;
		this.schoolCode = schoolCode;
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
	
	public String getSchoolCode() {
		return schoolCode;
	}
	
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	
	@Override
	public String toString() {
		return "Teacher [CMND=" + id + ", name=" + name + ", schoolCode=" + schoolCode + "]";
	}
}
