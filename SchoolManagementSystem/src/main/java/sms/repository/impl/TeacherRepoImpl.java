package sms.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sms.model.School;
import sms.model.Teacher;
import sms.repository.TeacherRepo;

public class TeacherRepoImpl implements TeacherRepo {
	public Optional<Teacher> findTeacherByName(List<Teacher> teachers, String name) {
		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(name)) {
				return Optional.of(teacher);
			}
		}
		return Optional.ofNullable(null);
	}

	public Optional<Teacher> findTeacherById(List<Teacher> teachers, int id) {
		for (Teacher teacher : teachers) {
			if (teacher.getId() == id) {
				return Optional.of(teacher);
			}
		}
		return Optional.ofNullable(null);
	}

	public Optional<List<String>> getStringFromTeacherList(List<School> schools) {
		List<String> teachersData = new ArrayList<String>();
		if (schools == null)
			return Optional.ofNullable(null);
		else {

			for (School school : schools) {
				List<Teacher> teachers = school.getTeachers();
				for (Teacher teacher : teachers) {
					String teacherData = "- " + teacher.getId() + " ||| " + teacher.getName() + " ||| "
							+ teacher.getSchoolId();
					teachersData.add(teacherData);
				}
			}
			return Optional.of(teachersData);
		}
	}
}
