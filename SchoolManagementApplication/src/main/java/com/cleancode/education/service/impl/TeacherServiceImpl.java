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
import com.cleancode.education.models.Teacher;
import com.cleancode.education.repository.TeacherRepository;
import com.cleancode.education.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{
	
	private TeacherRepository teacherRepository;
	
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}
	
	@Override
	public Teacher getTeacherById(String id) {
		
		return teacherRepository.findById(id);
	}

	@Override
	public List<Teacher> getTeacherByName(String name) {
		
		return teacherRepository.findByName(name);
	}

	@Override
	public List<Teacher> getTeacherBySchoolId(String schoolId) {

		return teacherRepository.findBySchoolId(schoolId);
	}

	@Override
	public List<Teacher> getAllTeacher() {

		return teacherRepository.findAll();
	}

	@Override
	public void add(Teacher teacher) {
		if(teacher != null)
			teacherRepository.create(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		if(teacher != null)
			teacherRepository.update(teacher);
	}

	@Override
	public void remove(Teacher teacher) {
		if(teacher != null)
			teacherRepository.remove(teacher);
	}

	@Override
	public School getSchool() {
		return teacherRepository.getSchool();
	}
	

}
