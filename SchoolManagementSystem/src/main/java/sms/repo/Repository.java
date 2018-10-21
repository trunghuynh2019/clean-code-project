package sms.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sms.model.School;
import sms.model.Teacher;
import sms.repoInterface.RepositoryITF;

public class Repository implements RepositoryITF {
	public Optional<School> findSchoolById(List<School> schools, String id) {
		for (School school : schools) {
			if (school.getId().equals(id)) {
				return Optional.of(school);
			}
		}
		return Optional.ofNullable(null);
	}

	public Optional<School> findSchoolByName(List<School> schools, String name) {
		for (School school : schools) {
			if (school.getName().equals(name)) {
				return Optional.of(school);
			}
		}
		return Optional.ofNullable(null);
	}

	public Optional<List<String>> getStringFromSchoolList(List<School> schools) {
		List<String> schoolsData = new ArrayList<String>();
		if (schools == null)
			return Optional.ofNullable(null);
		else {
			for (School school : schools) {
				String schoolData = "- " + school.getId() + " ||| " + school.getName() + " ||| "
						+ school.getNumOfStudents() + " ||| " + school.getAddress();
				schoolsData.add(schoolData);
			}
			return Optional.of(schoolsData);
		}
	}
	
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
