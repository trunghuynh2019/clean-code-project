package sms.view;

import java.util.Scanner;

import sms.model.School;

public class MainView {
	public static void displayMainMenu() {
		System.out.println("====================================");
		System.out.println("----  SCHOOL MANAGEMENT SYSTEM  ----");
		System.out.println("\t1. Display all schools");
		System.out.println("\t2. Add a new school");
		System.out.println("\t3. Choose a school to manage teachers");
		System.out.println("\t4. Exit");
		System.out.println("-----");
		System.out.print("Please enter your choice: ");
	}

	public static void displayManageTeacherMenu(School school) {
		System.out.println("--------------------");
		System.out.println("Welcome to " + school.getName() + " school!");
		System.out.println("1. View all teachers");
		System.out.println("2. Search teachers by Name");
		System.out.println("3. Search teachers by Address");
		System.out.println("4. Back to main menu");
		System.out.println("-----");
		System.out.print("Please enter your choice: ");
	}

	public static void loopAgain(Scanner scanner) {
		System.out.print("Press Enter to continue...");
		scanner.nextLine();
	}

	public static void enterSchoolName() {
		System.out.print("Enter a school name to manage its teachers list: ");
	}

	public static void enterTeacherName() {
		System.out.print("Enter a teacher name to search: ");
	}

	public static void enterTeacherAddress() {
		System.out.print("Enter a teacher address to search: ");
	}
}
