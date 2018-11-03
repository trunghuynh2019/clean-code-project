package sms.management.function;

import java.util.List;

import sms.model.School;
import sms.model.Teacher;
import sms.view.SchoolView;
import sms.view.TeacherView;

public class ManagementImpl implements Management {
	
	public void addSchool(List<School> schools, School school) {
		schools.add(school);
	}

	public void signContractWithTeacher(List<Teacher> teachers, Teacher teacher) {
		teachers.add(teacher);
	}

	public School searchSchoolById(List<School> schools, String id) {
		for (School school : schools)
			if (school.getId().equals(id))
				return school;
		return null;
	}

	public Teacher searchTeacherByName(List<Teacher> teachers, String name) {
		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(name)) {
				return teacher;
			}
		}
		return null;
	}

	public Teacher searchTeacherByAddress(List<Teacher> teachers, String address) {
		for (Teacher teacher : teachers) {
			if (teacher.getAddress().equals(address)) {
				return teacher;
			}
		}
		return null;
	}

	@Override
	public void viewAllSchool(List<School> schools) {
		for (School school : schools) {
			SchoolView.showInformationOfSchool(school);
		}
	}

	@Override
	public void viewAllTeacher(List<Teacher> teachers) {
		for (int i=0;i<teachers.size();i++) {
			System.out.println("Teacher " + i++ + ":");
			TeacherView.showInformationOfTeacher(teachers.get(i));
		}
	}


}
