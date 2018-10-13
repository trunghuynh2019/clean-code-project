/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller.impl;

import java.util.List;

import com.cleancode.education.controller.TeacherController;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.service.TeacherService;
import com.cleancode.education.views.SchoolPrinter;

public class TeacherControllerImpl implements TeacherController{
	private TeacherService teacherService;
	private SchoolPrinter schoolPrinter = new SchoolPrinter();
	
	public TeacherControllerImpl(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	
	
	@Override
	public void viewAllTeachers() {
		List<Teacher> teachers = teacherService.getAllTeacher();
		if(teachers.size() == 0) {
			System.out.println("The teacher list is empty.");
		} else {
			System.out.println("\nThe list of teachers are below: ");
			for (Teacher teacher : teachers) {
				System.out.println("=================================================");
				schoolPrinter.print(teacher);
			}
		}
	}



	
	
	
}
