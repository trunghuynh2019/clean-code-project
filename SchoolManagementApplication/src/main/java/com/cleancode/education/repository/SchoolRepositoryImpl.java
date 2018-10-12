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

public class SchoolRepositoryImpl implements SchoolRepository {
	private List<School> schools;
	
	public SchoolRepositoryImpl(List<School> schools) {
		this.schools = schools;
	}

	@Override
	public School findById(String id) {
		for (School s : schools) {
			if (s.getId().equals(id))
				return s;
		}
		return null;
	}

	@Override
	public List<School> findAll() {
		return schools;
	}

}
