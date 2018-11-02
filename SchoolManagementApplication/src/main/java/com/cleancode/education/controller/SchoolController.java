/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public interface SchoolController {
	void viewAllSchools();
	
	void addSchool(School school);
	void addSchoolsFrom(String fileName);
	
	void signContractWithTeacher(Teacher teacher);
	void signContractWithTeacherFrom(String fileName);
	
	void exportSchoolToText(String fileName);
	void exportSchoolToExcel(String fileName);
	void exportSchoolToPDF(String fileName);
	void exportSchoolToHtml(String fileName);
	void exportSchoolToJson(String fileName);	
	
	void exportTeacherToText(String fileName);
	void exportTeacherToExcel(String fileName);
	void exportTeacherToPDF(String fileName);
	void exportTeacherToHtml(String fileName);
	void exportTeacherToJson(String fileName);
	
}
