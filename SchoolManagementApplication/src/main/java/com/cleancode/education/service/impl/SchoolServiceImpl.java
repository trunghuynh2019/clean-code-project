/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.service.impl;

import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.repository.SchoolRepository;
import com.cleancode.education.service.SchoolService;

public class SchoolServiceImpl implements SchoolService {
	
	private SchoolRepository schoolRepository;
	
	public SchoolServiceImpl(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}
	
	
	@Override
	public School getSchoolById(String id) {
		return schoolRepository.findById(id);
	}

	@Override
	public List<School> getAllSchool() {
		return schoolRepository.findAll();
	}

	@Override
	public void add(School school) {
		if(school != null)
			schoolRepository.create(school);
	}

	@Override
	public void update(School school) {
		if(school != null)
			schoolRepository.update(school);
	}

	@Override
	public void remove(School school) {
		if(school != null)
			schoolRepository.remove(school);
	}
	
	


}
