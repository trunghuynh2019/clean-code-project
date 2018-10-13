/*
 * Title
 * 
 * @author Huy
 * @date Oct 12, 2018
 * @version 1.0
 */
package com.cleancode.education.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.repository.TeacherRepository;

public class TeacherRepositoryImpl implements TeacherRepository{
	
	private School school;
	
	public TeacherRepositoryImpl(School school) {
		this.school = school;
	}
	
	@Override
	public School getSchool() {
		return school;
	}

	@Override
	public Teacher findById(String id) {
		for (Teacher t : school.getTeachers()) {
			if(t.getId().equals(id))
				return t;
		}
		return null;
	}

	@Override
	public List<Teacher> findByName(String name) {
		List<Teacher> teachers = new ArrayList<>();
		for (Teacher t : school.getTeachers()) {
			if(t.getName().equals(name))
				teachers.add(t);
		}
		return teachers;
	}

	@Override
	public List<Teacher> findBySchoolId(String schoolId) {
		List<Teacher> teachers = new ArrayList<>();
		for (Teacher t : school.getTeachers()) {
			if(t.getSchoolId().equals(schoolId))
				teachers.add(t);
		}
		return teachers;
	}

	@Override
	public List<Teacher> findAll() {
		return school.getTeachers();
	}

	@Override
	public void create(Teacher teacher) {
		school.signContractWith(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		for(Teacher t : school.getTeachers()) {
			if (t.getId().equals(teacher.getId()))
			{
				t.setName(teacher.getName());
				t.setSchoolId(teacher.getSchoolId());
				
			}
		}
	}

	@Override
	public void remove(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}
	
}
