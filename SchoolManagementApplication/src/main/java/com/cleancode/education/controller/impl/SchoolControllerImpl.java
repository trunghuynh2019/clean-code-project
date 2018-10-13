/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller.impl;

import java.util.List;

import com.cleancode.education.controller.SchoolController;
import com.cleancode.education.models.School;
import com.cleancode.education.service.SchoolService;
import com.cleancode.education.service.impl.SchoolServiceImpl;
import com.cleancode.education.views.SchoolPrinter;

public class SchoolControllerImpl implements SchoolController{
	private SchoolService schoolService;
	private SchoolPrinter schoolPrinter = new SchoolPrinter();
	
	public SchoolControllerImpl(SchoolServiceImpl schoolServiceImpl) {
		this.schoolService = schoolServiceImpl;
	}
	
	@Override
	public void viewAllSchools() {
		List<School> schools = schoolService.getAllSchool();
		if (schools.isEmpty()) {
			System.out.println("The school list is empty.");
		} else {
			System.out.println("\nThe list of schools are below: ");
			for (School school : schools) {
				System.out.println("=================================================");
				schoolPrinter.print(school);
			}
		}
	}

	@Override
	public void addSchool(School newSchool) {
		List<School> schools = schoolService.getAllSchool();
		/*
		 * Kiểm tra nếu list rỗng -> add trực tiếp vào list
		 * Nếu không -> kiểm tra từng mã trường một để quyết định update thông tin hay add mới*/
		if (schools.isEmpty()) { 
			schools.add(newSchool);
		} else {
			boolean existedSchool = false;
			for (School school : schools) {
				if (school.equalId(newSchool)) {
					existedSchool = true;
					school.setName(newSchool.getName());
					school.setAddress(newSchool.getAddress());
					school.setNumberOfStudent(newSchool.getNumberOfStudent());
//					for (Teacher teacher: newSchool.getTeachers()) {
//						signContractWithTeacher(school, teacher);
//					}
					break;
				}
			}
			if (!existedSchool) {
				schools.add(newSchool);
			}
		}
	}
}
