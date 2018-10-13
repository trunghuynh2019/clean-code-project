package sms.function;

import java.util.List;
import sms.model.*;

public interface ManagementInterface {
	public void exportDataOfSchools(List<School> schools, String fileName);
	public void exportDataOfTeachers(List<Teacher> teachers, String fileName);
	public void loadDatabaseOfSchool(String fileName, List<School> schools);
	public void loadDatabaseOfTeacher(String fileName, School schools);
	public void addSchool(List<School> schools, School school);
	public void signContractWithTeacher(School school, Teacher teacher);
	public School searchSchoolById(List<School> schools,String name);
	public Teacher searchTeacherByName(List<Teacher> teachers, String name);
	public Teacher searchTeacherByAddress(List<Teacher> teachers, String address);
}
