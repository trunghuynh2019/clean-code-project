/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.cleancode.education.models.School;

public interface SchoolController {
	void viewAllSchools();
	void addSchool(School school);
	void addSchoolFrom(String fileName);
	void exportSchoolsToText(String fileName);
	void exportSchoolsToExcel() throws IOException, InvalidFormatException;
	public void signContractWithTeacherFrom(String fileName);
}
