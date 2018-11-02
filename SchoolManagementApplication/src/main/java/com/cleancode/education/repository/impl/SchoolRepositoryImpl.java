/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.cleancode.education.filereader.FileReader;
import com.cleancode.education.filereader.JsonReader;
import com.cleancode.education.models.School;
import com.cleancode.education.repository.SchoolRepository;

public class SchoolRepositoryImpl implements SchoolRepository {
	
	private FileReader jsonReader = new JsonReader();
	private List<School> schools = new ArrayList<>();
	
	public SchoolRepositoryImpl(String SCHOOL_DATABASE) {
		this.schools = jsonReader.importSchool(SCHOOL_DATABASE + ".json");
	}
	
	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
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

	@Override
	public void create(School school) {
		schools.add(school);
	}

	@Override
	public void update(School school) {
		School oldSchool = findById(school.getId());
		if (oldSchool != null) {
			oldSchool.setName(school.getName());
			oldSchool.setAddress(school.getAddress());
			oldSchool.setNumberOfStudent(school.getNumberOfStudent());
			oldSchool.setTeachers(school.getTeachers());
		}
	}

	@Override
	public void remove(School school) {
		// TODO Auto-generated method stub
		
	}

}
