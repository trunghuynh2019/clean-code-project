/*
 * Title
 * 
 * @author Huy
 * @date Oct 30, 2018
 * @version 1.0
 */
package com.cleancode.education.filewriter;

import java.util.List;

import com.cleancode.education.models.School;

public interface FileWriter {
	
	public static final String[] TEACHER_COLUMN_HEADER = {"CMND", "Name", "Working School's ID"};
	public static final String[] SCHOOL_COLUMN_HEADER = {"ID", "Name", "Number Of Teacher", "Address", "Teacher's CMND"};
	
	void exportSchool(List<School> schools, String fileName);
	void exportTeacher(List<School> schools, String fileName);
}
