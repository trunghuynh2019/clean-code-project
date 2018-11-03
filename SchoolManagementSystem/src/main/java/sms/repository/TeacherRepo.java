package sms.repository;

import java.util.List;
import java.util.Optional;

import sms.model.School;
import sms.model.Teacher;

public interface TeacherRepo {
	public Optional<Teacher> findTeacherByName(List<Teacher> teacherList, String name);
	public Optional<Teacher> findTeacherById(List<Teacher> teacherList, int id);
	public Optional<List<String>> getStringFromTeacherList(List<School> schools);
	public Optional<List<Teacher>> getAllTeachersFromSchoolList(List<School> schools);
}
