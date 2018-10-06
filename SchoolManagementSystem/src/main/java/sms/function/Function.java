package sms.function;

import java.util.List;

import sms.functionInterface.FunctionInterface;
import sms.model.School;
import sms.model.Teacher;

public class Function implements FunctionInterface {

	public void addSchool(List<School> schools, School school) {
		schools.add(school);
	}

	public void addTeacherToSchool(School school, Teacher teacher) {
		school.addTeacher(teacher);
	}
	
	public School findSchoolById(List<School> schools, String id) {
		for (School school : schools) {
			if (school.getId().equals(id)) {
				return school;
			}
		}
		return null;
	}

	public School findSchoolByName(List<School> schools, String name) {
		for (School school : schools) {
			if (school.getName().equals(name)) {
				return school;
			}
		}
		return null;
	}

	public Teacher findTeacherByName(List<Teacher> teachers, String name) {
		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(name)) {
				return teacher;
			}
		}
		return null;
	}

	public Teacher findTeacherById(List<Teacher> teachers, int id) {
		for (Teacher teacher : teachers) {
			if (teacher.getId() == id) {
				return teacher;
			}
		}
		return null;
	}
}
