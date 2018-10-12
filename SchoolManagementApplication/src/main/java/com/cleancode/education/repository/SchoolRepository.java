/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.repository;

import java.util.List;

import com.cleancode.education.models.School;

public interface SchoolRepository {
	School findById(String id);
	List<School> findAll();
}
