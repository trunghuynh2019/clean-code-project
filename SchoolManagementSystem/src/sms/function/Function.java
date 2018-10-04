package sms.function;

import java.util.List;
import sms.model.School;
import sms.model.Teacher;

public class Function implements FunctionInterface{

	@Override
	public void addSchool(List<School> schools, School school) {
		schools.add(school);
	}

	@Override
	public void addTeacherToSchool(School school, Teacher teacher) {
		school.addTeacher(teacher);
	}
	
	@Override
	public School findSchoolById(List<School> schools, String id) {
		for (School school : schools) {
			if (school.getId().equals(id)) {
				return school;
			}
		}
		return null;
	}

	@Override
	public School findSchoolByName(List<School> schools, String name) {
		for (School school : schools) {
			if (school.getName().equals(name)) {
				return school;
			}
		}
		return null;
	}

	@Override
	public Teacher findTeacherByName(List<Teacher> teachers, String name) {
		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(name)) {
				return teacher;
			}
		}
		return null;
	}

	@Override
	public Teacher findTeacherByAddress(List<Teacher> teachers, String address) {
		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(address)) {
				return teacher;
			}
		}
		return null;
	}
}
