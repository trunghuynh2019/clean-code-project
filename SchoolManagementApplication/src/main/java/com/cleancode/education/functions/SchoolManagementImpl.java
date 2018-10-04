/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.functions;

import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.views.SchoolPrinter;

public class SchoolManagementImpl implements SchoolManagement{

	private SchoolPrinter schoolPrinter = new SchoolPrinter();
	
	public void viewAllSchools(List<School> schools) {
		if (schools.isEmpty()) {
			System.out.println("The school list is empty.");
		} else {
			for (School school : schools) {
				schoolPrinter.print(school);
			}
		}
		
	}

	public void viewAllTeachers(List<Teacher> teachers) {
		if(teachers.isEmpty()) {
			System.out.println("The teacher list is empty.");
		} else {
			for (Teacher teacher : teachers) {
				schoolPrinter.print(teacher);
			}
		}
	}

	public void addSchool(List<School> schools, School newSchool) {
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
					school.setTeachers(newSchool.getTeachers());
					break;
				}
			}
			if (!existedSchool) {
				schools.add(newSchool);
			}
		}
	}

	public void addSchoolFrom(String fileName, List<School> schools) {
		
	}

	public void signContractWithTeacher(School school, Teacher newTeacher) {
		if (school.getTeachers().isEmpty()) {
			school.signContractWith(newTeacher);
		} else {
			boolean existedTeacher = false;
			for (Teacher teacher : school.getTeachers()) {
				if (teacher.equalId(newTeacher)) {
					existedTeacher = true;
					teacher.setName(newTeacher.getName());
					teacher.setSchoolId(newTeacher.getSchoolId());
					break;
				}
			}
			if (!existedTeacher) {
				school.signContractWith(newTeacher);
			}
		}
	}

	public void signContractWithTeacherFrom(String fileName, List<School> schools) {
		// TODO Auto-generated method stub
		
	}
	
}
