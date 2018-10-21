package sms.repoInterface;

import java.util.List;
import java.util.Optional;

import sms.model.School;
import sms.model.Teacher;

public interface RepositoryITF {
	public Optional<School> findSchoolById(List<School> schoolList, String id);
	public Optional<School> findSchoolByName(List<School> schoolList, String name);
	public Optional<List<String>> getStringFromSchoolList(List<School> schools);
	

	public Optional<Teacher> findTeacherByName(List<Teacher> teacherList, String name);
	public Optional<Teacher> findTeacherById(List<Teacher> teacherList, int id);
	public Optional<List<String>> getStringFromTeacherList(List<School> schools);
}
