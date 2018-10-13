/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.service;

import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public interface TeacherService {
	School getSchool();
	Teacher getTeacherById(String id);
	List<Teacher> getTeacherByName(String name);
	List<Teacher> getTeacherBySchoolId(String schoolId);
	List<Teacher> getAllTeacher();
	void add(Teacher teacher);
	void update(Teacher teacher);
	void remove(Teacher teacher);
}
