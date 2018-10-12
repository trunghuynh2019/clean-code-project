/*
 * Title
 * 
 * @author Huy
 * @date Oct 12, 2018
 * @version 1.0
 */
package com.cleancode.education.repository;

import java.util.List;

import com.cleancode.education.models.Teacher;

public interface TeacherRepository {
	Teacher findById(String id);
	List<Teacher> findByName(String name);
	List<Teacher> findBySchoolId(String schoolId);
	List<Teacher> findAll();
}
