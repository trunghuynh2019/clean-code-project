package sms.function;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.model.Teacher;

public class Function implements FunctionInterface{

	@Override
	public void addSchool(List<School> schoolList, School school) {
		schoolList.add(school);
	}

	@Override
	public void addTeacherToSchool(School school, Teacher teacher) {
		school.getTeacherList().add(teacher);
	}

	@Override
	public School findSchoolByName(List<School> schoolList, String name) {
		for (School school : schoolList) {
			if (school.getName().equals(name)) {
				return school;
			}
		}
		return null;
	}

	@Override
	public Teacher findTeacherByName(List<Teacher> teacherList, String name) {
		for (Teacher teacher : teacherList) {
			if (teacher.getName().equals(name)) {
				return teacher;
			}
		}
		return null;
	}

	@Override
	public Teacher findTeacherByAddress(List<Teacher> teacherList, String address) {
		for (Teacher teacher : teacherList) {
			if (teacher.getName().equals(address)) {
				return teacher;
			}
		}
		return null;
	}


}
