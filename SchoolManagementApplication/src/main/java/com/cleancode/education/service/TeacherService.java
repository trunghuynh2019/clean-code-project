/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.service;

import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public interface TeacherService {
	public void viewAllTeachers();
	public void signContractWithTeacher(School school, Teacher teacher);
	public void signContractWithTeacherFrom(String fileName, List<School> schools);
}
