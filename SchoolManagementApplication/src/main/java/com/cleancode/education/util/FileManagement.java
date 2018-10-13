/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.util;

import java.io.IOException;
import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public interface FileManagement {
	public List<School> getSchoolsFrom(String fileName);
	public List<Teacher> getTeachersFrom(String fileName);
	public void exportTeachersToExcel(List<School> schools, String fileName) throws IOException;
	public void exportTeachersToText(List<School> schools, String fileName);
	public void exportSchoolsToExcel(List<School> schools, String fileName) throws IOException;
	public void exportSchoolsToText(List<School> schools, String fileName);
}
