/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.cleancode.education.controller.TeacherController;
import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.service.SchoolService;
import com.cleancode.education.service.TeacherService;
import com.cleancode.education.service.impl.SchoolServiceImpl;
import com.cleancode.education.service.impl.TeacherServiceImpl;
import com.cleancode.education.views.SchoolPrinter;

public class TeacherControllerImpl implements TeacherController{
	private TeacherService teacherService;
	private SchoolPrinter schoolPrinter = new SchoolPrinter();
	
	public TeacherControllerImpl(TeacherServiceImpl teacherServiceImpl) {
		this.teacherService = teacherServiceImpl;
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

//	@Override
//	public void signContractWithTeacher(Teacher newTeacher) {
//		
//		if (teachers.isEmpty()) {
//			school.signContractWith(newTeacher);
//		} else {
//			boolean existedTeacher = false;
//			for (Teacher teacher : school.getTeachers()) {
//				if (teacher.equalId(newTeacher)) {
//					existedTeacher = true;
//					teacher.setName(newTeacher.getName());
//					teacher.setSchoolId(newTeacher.getSchoolId());
//					break;
//				}
//			}
//			if (!existedTeacher) {
//				school.signContractWith(newTeacher);
//			}
//		}
//	}

	
	
	
}
