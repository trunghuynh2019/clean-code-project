package sma.repository;

import java.util.List;

import sma.entity.School;
import sma.entity.Teacher;

public interface TeacherRepository {
	School getSchool();
	Teacher findById(String id);
	List<Teacher> findByName(String name);
	List<Teacher> findBySchoolId(String schoolId);
	void create(Teacher teacher);
	void update(Teacher teacher);
	void remove(Teacher teacher);
	
}
