/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.views;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public class PrinterSupport {
	
	public String nameWithThreeStarAround(String name) {
		return "*** " + name + " ***";
	}
	
	public String codeFormat(String code) {
		return "Code: " + code;
	}
	
	public String idFormat(String id) {
		return "Id: " + id;
	}
	
	public String addressFormat(String address) {
		return "Address: " + address;
	}
	
	public String numberOf(String name, int amount) {
		return "Number of " + name + ": " + amount;
	}
	
	public String workingSchool(String schoolID) {
		return "Working School's ID: " + schoolID;
	}
	
	public String teacherTextFileHeader() {
		return "Danh sach giao vien\n\n";
	}
	
	public String schoolTextFileHeader() {
		return "Danh sach truong\n\n";
	}
	
	public String formatTextRow(Teacher teacher) {
		
		return "- " + teacher.getId() + " ||| " + teacher.getName() + " ||| " + teacher.getSchoolId();
	}
	
	public String formatTextRow(School school) {
		
		return "- " + school.getId() + " ||| " + school.getName() + " ||| " + school.getNumberOfStudent() + " ||| " + school.getAddress();
	}
	
	public String formatExcelRow(Teacher teacher) {
		return null;
	}
	
}
