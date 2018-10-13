package sms.view;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.model.Teacher;

public class TeacherView {
	public static void displayTeacher(Teacher teacher) {
		System.out.println(teacher.toString());
	}

	public static void displayTeacherNotFound() {
		System.out.println("Teacher not found!");
	}
	
	public static void displayAllTeachersOfSchool(School school) {
		if (school.getNumOfTeachers() == 0) {
			System.out.println("There is no teacher...");
		} else {
			int i = 1;
			List<Teacher> teachers = school.getTeachers();
			for (Teacher teacher : teachers) {
				System.out.println("Teacher #" + i++ + ":");
				displayTeacher(teacher);
				System.out.println("------");
			}
		}
	}

	public static void insertTeacherData(Teacher teacher, Scanner scanner) {
		System.out.print("Teacher id: ");
		teacher.setId(scanner.nextInt());
		scanner.nextLine();
		System.out.print("Teacher name: ");
		teacher.setName(scanner.nextLine());
	}
}
