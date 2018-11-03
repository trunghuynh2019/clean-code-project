package sms.management.function;

import java.util.List;
import sms.model.*;

public interface Management {

	public void viewAllSchool(List<School> schools);
	public void viewAllTeacher(List<Teacher> teachers);
	public void addSchool(List<School> schools, School school);
	public void signContractWithTeacher(List<Teacher> teachers, Teacher teacher);
	
	public School searchSchoolById(List<School> schools,String name);
	public Teacher searchTeacherByName(List<Teacher> teachers, String name);
	public Teacher searchTeacherByAddress(List<Teacher> teachers, String address);
}
