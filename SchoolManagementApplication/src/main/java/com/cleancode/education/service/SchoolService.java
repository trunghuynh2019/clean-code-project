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

public interface SchoolService {
	School getSchoolById(String id);
	List<School> getAllSchool();
	void add(School school);
	void update(School school);
	void remove(School school);
}
