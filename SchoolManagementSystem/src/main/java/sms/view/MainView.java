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
		System.out.println("\t4. Enter data by file text input");
		System.out.println("\t5. Export data to text file");
		System.out.println("\t6. Export data to excel file");
		System.out.println("\t7. Export data to pdf file");
		System.out.println("\t8. Exit");
		System.out.println("------------------------------------");
		System.out.print("Please enter your choice: ");
	}

	public static void displayManageTeacherMenu(School school) {
		System.out.println("--------------------");
		System.out.println("Welcome to " + school.getName() + " school!");
		System.out.println("1. View all teachers");
		System.out.println("2. Search teachers by Name");
		/*System.out.println("3. Search teachers by Address");*/
		System.out.println("3. Search teachers by SchoolId");
		System.out.println("4. Back to main menu");
		System.out.println("-----");
		System.out.print("Please enter your choice: ");
	}

	public static void loopAgain(Scanner scanner) {
		System.out.print("Press Enter to continue...");
		scanner.nextLine();
	}

	public static void enterSchoolId() {
		System.out.print("Enter a school id to manage its teachers list: ");
	}

	public static void enterTeacherName() {
		System.out.print("Enter a teacher name to search: ");
	}

	public static void enterTeacherId() {
		/*System.out.print("Enter a teacher address to search: ");*/
		System.out.print("Enter a teacher id to search: ");
	}
	
	//
	public static void enterSchoolFileName() {
		System.out.println("First, put the files into folder src/main/resources/file");
		System.out.println("Second, enter the name of file. Ex: truong.txt, giaovien.txt");
		System.out.print("Name of school file: ");
	}
	
	public static void enterTeacherFileName() {
		System.out.print("Name of teacher file: ");
	}
	
	public static void fileNotFound() {
		System.out.println("File not found!...");
	}
	
	public static void readFileSuccessfully() {
		System.out.println("Reading successfully!...");
	}
	
	public static void writeFileSuccessfully() {
		System.out.println("Writing successfully!...");
	}
	
	public static void writeFileFailed() {
		System.out.println("Writing failed!...");
	}
}
