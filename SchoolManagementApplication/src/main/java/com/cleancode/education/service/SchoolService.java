/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.service;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.cleancode.education.models.School;

public interface SchoolService {
	public void viewAllSchools();
	public void addSchool(School school);
	public void addSchoolFrom(String fileName);
	public void exportSchoolsToText(String fileName);
	public void exportSchoolsToExcel() throws IOException, InvalidFormatException;
}
