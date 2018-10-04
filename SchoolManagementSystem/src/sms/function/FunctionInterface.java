package sms.function;

import java.util.List;

import sms.model.School;
import sms.model.Teacher;

public interface FunctionInterface {	
	public void addSchool(List<School> schoolList, School school);
	public void addTeacherToSchool(School school, Teacher teacher);
	public School findSchoolById(List<School> schoolList, String name);
	public School findSchoolByName(List<School> schoolList, String name);
	public Teacher findTeacherByName(List<Teacher> teacherList, String name);
	public Teacher findTeacherByAddress(List<Teacher> teacherList, String address);
}
