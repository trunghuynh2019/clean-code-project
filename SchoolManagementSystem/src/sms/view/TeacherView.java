package sms.view;

import java.util.Scanner;

import sms.model.Teacher;

public class TeacherView {
	public static void displayTeacher(Teacher teacher) {
		System.out.println(teacher.toString());
	}

	public static void insertTeacherData(Teacher teacher, Scanner scanner) {
		System.out.print("Teacher name: ");
		teacher.setName(scanner.nextLine());
		System.out.print("Address: ");
		teacher.setAddress(scanner.nextLine());
	}
}
