package sms.functionInterface;

import java.util.List;

import sms.model.School;
import sms.model.Teacher;

public interface FunctionITF {
	public School findSchoolById(List<School> schoolList, String id);
	public School findSchoolByName(List<School> schoolList, String name);
	public Teacher findTeacherByName(List<Teacher> teacherList, String name);
	public Teacher findTeacherById(List<Teacher> teacherList, int id);
	public List<String> getStringFromSchoolList(List<School> schools);
	public List<String> getStringFromTeacherList(List<School> schools);
}
