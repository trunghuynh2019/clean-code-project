/*
 * Title
 * 
 * @author Huy
 * @date Oct 12, 2018
 * @version 1.0
 */
package com.cleancode.education.repository;

import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public interface TeacherRepository {
	School getSchool();
	Teacher findById(String id);
	List<Teacher> findByName(String name);
	List<Teacher> findBySchoolId(String schoolId);
	List<Teacher> findAll();
	void create(Teacher teacher);
	void update(Teacher teacher);
	void remove(Teacher teacher);
}
