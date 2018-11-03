package sms.view;

import java.util.Scanner;

import sms.model.*;

public class TeacherView {
	public static void showInformationOfTeacher(Teacher teacher) {
		System.out.println(teacher.toString());
	}

	public static void showTeacherNotFound() {
		System.out.println("Teacher not found!");
	}

	public static void enterInformationOfTeacher(Teacher teacher, Scanner scanner) {
		do {
			System.out.print("Teacher name: ");
			teacher.setName(scanner.nextLine());
			System.out.print("Teacher address: ");
			teacher.setAddress(scanner.nextLine());
			System.out.print("Teacher phone: ");
			teacher.setPhone(Integer.parseInt(scanner.nextLine()));
		}while(loopEnterTeacher(scanner).equals("Y"));
	}
	
	public static String loopEnterTeacher(Scanner scanner) {
		System.out.print("Do you want to sign a contract with other teacher ? (Y/N) ");
		return scanner.nextLine().toUpperCase();
	}
	
	public static void showMessageEmptyTeacherList() {
		System.out.println("The teacher list is empty!");
	}
	
	public static void enterTeacherName() {
		System.out.print("Enter teacher name for searching: ");
	}
	
	public static void enterTeacherAddress() {
		System.out.print("Enter teacher address for searching: ");
	}
	
	public static void enterFileNameOfTeacher() {
		System.out.print("Enter file name for loading information of teacher: ");
	}
	
	public static void showStatusExportFile(String status) {
		System.out.println("Export Data Of School "+status);
	}
	
	public static void showStatusImportFile(String status) {
		System.out.println("Import Data Of School "+status);
	}
}
