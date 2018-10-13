/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller;

import com.cleancode.education.models.Teacher;

public interface TeacherController {
	public void viewAllTeachers();
	public void signContractWithTeacher(Teacher teacher);
}
