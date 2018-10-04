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

public interface SchoolManagement {
	public School findSchoolById(List<School> schools, String id);
	public void viewAllSchools(List<School> schools);
	public void viewAllTeachers(School school);
	public void addSchool(List<School> schools, School school);
	public void addSchoolFrom(String fileName, List<School> schools);
	public void signContractWithTeacher(School school, Teacher teacher);
	public void signContractWithTeacherFrom(String fileName, List<School> schools);
}
