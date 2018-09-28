package sms.function;

import java.util.List;

import sms.model.School;
import sms.model.Teacher;

public interface FunctionInterface {	
	public void addSchool(List<School> schoolList, School school);
	public void addTeacherToSchool(School school, Teacher teacher);
}
