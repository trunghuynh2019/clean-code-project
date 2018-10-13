package sms.function;

import java.util.ArrayList;
import java.util.List;

import sms.functionInterface.FunctionITF;
import sms.model.School;
import sms.model.Teacher;

public class Function implements FunctionITF {

	public School findSchoolById(List<School> schools, String id) {
		for (School school : schools) {
			if (school.getId().equals(id)) {
				return school;
			}
		}
		return null;
	}

	public School findSchoolByName(List<School> schools, String name) {
		for (School school : schools) {
			if (school.getName().equals(name)) {
				return school;
			}
		}
		return null;
	}

	public Teacher findTeacherByName(List<Teacher> teachers, String name) {
		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(name)) {
				return teacher;
			}
		}
		return null;
	}

	public Teacher findTeacherById(List<Teacher> teachers, int id) {
		for (Teacher teacher : teachers) {
			if (teacher.getId() == id) {
				return teacher;
			}
		}
		return null;
	}

	public List<String> getStringFromSchoolList(List<School> schools) {
		List<String> schoolsData = new ArrayList<String>();
		if (schools.size() == 0)
			return null;
		else {
			for (School school : schools) {
				String schoolData = "- " + school.getId() + " ||| " + school.getName() + " ||| "
						+ school.getNumOfStudents() + " ||| " + school.getAddress();
				schoolsData.add(schoolData);
			}
			return schoolsData;
		}
	}

	public List<String> getStringFromTeacherList(List<School> schools) {
		List<String> teachersData = new ArrayList<String>();
		if (schools.size() == 0)
			return null;
		else {

			for (School school : schools) {
				List<Teacher> teachers = school.getTeachers();
				for (Teacher teacher : teachers) {
					String teacherData = "- " + teacher.getId() + " ||| " + teacher.getName() + " ||| "
							+ teacher.getSchoolId();
					teachersData.add(teacherData);
				}
			}
			return teachersData;
		}
	}
}
