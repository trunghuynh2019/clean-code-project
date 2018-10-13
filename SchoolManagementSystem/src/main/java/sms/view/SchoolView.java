package sms.view;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.model.Teacher;

public class SchoolView {
	public static void displaySchool(School school) {
		System.out.println(school.toString());
		System.out.println("--------------------------------");
	}
	
	public static void displaySchoolNotFound() {
		System.out.println("School not found!");
	}

	public static void displayAllSchool(List<School> schoolList) {
		if (schoolList.isEmpty()) {
			System.out.println("There is no school now...");
		} else {
			for (School school : schoolList) {
				displaySchool(school);
			}
		}
	}

	public static void insertSchoolData(School school, Scanner scanner) {
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
			TeacherView.insertTeacherData(teacher, scanner);
			teacher.setSchoolId(school.getId());
			school.addTeacher(teacher);
		}
	}
}
