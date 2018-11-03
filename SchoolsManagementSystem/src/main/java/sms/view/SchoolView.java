package sms.view;

import sms.model.*;

import java.util.Scanner;

public class SchoolView {
	public static void showInformationOfSchool(School school) {
		System.out.println(school.toString());
	}
	
	public static void showSchoolNotFound() {
		System.out.println("School has not found!");
	}


	public static void enterInformationOfSchool(School school, Scanner scanner) {
		// add school's information
		System.out.print("School id: ");
		school.setId(scanner.nextLine().toUpperCase());
		System.out.print("School name: ");
		school.setName(scanner.nextLine());
		System.out.print("Address: ");
		school.setAddress(scanner.nextLine());
		System.out.print("Number of Students: ");
		school.setNumberOfStudents(Integer.parseInt(scanner.nextLine()));
		
		school.setNumberOfTeachers(0);
		school.setTeachers(null);
	}
	
	public static void enterFileNameOfSchool() {
		System.out.print("Enter file name for loading information of school: ");
	}
	
	public static void enterSchoolId() {
		System.out.print("Enter a school ID to manage its teacher list: ");
	}
	
	public static void showEmptySchool() {
		System.out.println("The school list is empty!");
	}
	
	public static void showStatusExportFile(String status) {
		System.out.println("Export Data Of School "+status);
	}
	
	public static void showStatusImportFile(String status) {
		System.out.println("Import Data Of School "+status);
	}
}
