package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.model.Teacher;
import sms.service.SchoolService;

public class SchoolServiceImpl implements SchoolService{
	private void displaySchool(School school) {
		System.out.println(school.toString());
		System.out.println("--------------------------------");
	}

	@Override
	public void displayAllSchool(List<School> schools) {
		if (schools.isEmpty()) {
			System.out.println("There is no school now...");
		} else {
			for (School school : schools) {
				displaySchool(school);
			}
		}
	}

	@Override
	public void addNewSchool(List<School> schools, Scanner scanner) {
		School school = new School();
		System.out.print("School id: ");
		school.setId(scanner.nextLine());
		System.out.print("School name: ");
		school.setName(scanner.nextLine());
		System.out.print("Address: ");
		school.setAddress(scanner.nextLine());
		System.out.print("Number of Students: ");
		school.setNumOfStudents(scanner.nextInt());
		scanner.nextLine();
		System.out.print("Number of Teachers: ");
		school.setNumOfTeachers(scanner.nextInt());
		int N = school.getNumOfTeachers();
		scanner.nextLine();
		for (int i = 1; i <= N; i++) {
			System.out.println("Insert information of Teacher #" + i);
			Teacher teacher = new Teacher();
			new TeacherServiceImpl().insertTeacherData(teacher, scanner);
			teacher.setSchoolId(school.getId());
			school.addTeacher(teacher);
		}
		schools.add(school);
	}
}
