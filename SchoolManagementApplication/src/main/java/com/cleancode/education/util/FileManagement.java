/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.util;

import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public interface FileManagement {
	public List<School> getSchoolsFrom(String fileName);
	public List<Teacher> getTeachersFrom(String fileName);
	public boolean exportTeachersToExcel(List<School> schools, String fileName);
	public boolean exportTeachersToText(List<School> schools, String fileName);
	public boolean exportSchoolsToExcel(List<School> schools, String fileName);
	public boolean exportSchoolsToText(List<School> schools, String fileName);
}
