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


}
