/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public class SchoolPrinter {
	private PrinterSupport print = new PrinterSupport();
	
	/* Example:
	 * *** Truong trung hoc Chuyen Nguyen Binh Khiem ***
	 * Id: nbk-vl
	 * Address: Vinh Long
	 * Number of students: 2000
	 * Number of teachers: 200
	 * */
	public void print(School school) {
		System.out.println(print.nameWithThreeStarAround(school.getName()));
		System.out.println(print.idFormat(school.getId()));
		System.out.println(print.addressFormat(school.getAddress()));
		System.out.println(print.numberOf("students", school.getNumberOfStudent()));
		System.out.println(print.numberOf("teachers", school.getNumberOfTeacher()));
	}
	
	/* Example:
	 * *** Le Cong Huy ***
	 * Id: 123456789
	 * Working School's Id: nbk-vl
	 * */
	public void print(Teacher teacher) {
		System.out.println(print.nameWithThreeStarAround(teacher.getName()));
		System.out.println(print.idFormat(teacher.getId()));
		System.out.println(print.workingSchool(teacher.getSchoolId()));
	}
	
	public void printMenu() {
		System.out.println("\n========== SCHOOL MANAGEMENT SYSTEM ==========");
		System.out.println("|   1. View All Schools                      |");
		System.out.println("|   2. Add A New School                      |");
		System.out.println("|   3. Add Schools From File                 |");
		System.out.println("|   4. View All Teachers                     |");
		System.out.println("|   5. Sign Contract With Teacher            |");
		System.out.println("|   6. Sign Contract With Teachers From File |");
		System.out.println("|   7. Export School/Teacher To Text File    |");
		System.out.println("|   8. Export School/Teacher To Excel File   |");
		System.out.println("|   9. Export School/Teacher To Pdf File     |");
		System.out.println("|   10. Export School/Teacher To Html File   |");
		System.out.println("|   11. Export School/Teacher To Json File   |");
		System.out.println("|   12. Exit                                 |");
		System.out.println("==============================================");
		System.out.print("Please Enter Your Choice: ");
	}
	
	public School inputNewSchool(Scanner scanner) {
		School school = new School();
		
		System.out.print("Enter School Id: ");
		school.setId(scanner.nextLine());
		
		System.out.print("Enter School Name: ");
		school.setName(scanner.nextLine());
		
		System.out.print("Enter School Address: ");
		school.setAddress(scanner.nextLine());
		
		System.out.print("Enter Number Of Students: ");
		school.setNumberOfStudent(scanner.nextInt());
		scanner.nextLine();
		
		System.out.print("Enter Number of Teachers: ");
		int numberOfTeacher = scanner.nextInt();
		scanner.nextLine();
		List<Teacher> teachers = new ArrayList<>();
		
		for (int i = 0; i < numberOfTeacher; i++) {
			System.out.println("Enter Information of Teacher #" + (i+1));
			Teacher newTeacher = inputTeacher(scanner, school.getId());
            teachers.add(newTeacher);
		}
		school.setTeachers(teachers);
		System.out.println("School is added.\n");
		return school;
	}
	
	public Teacher inputTeacher(Scanner scanner, String schoolId) {
		Teacher teacher = new Teacher();
		
		System.out.print("Enter Teacher Id: ");
		teacher.setId(scanner.nextLine());
		
		System.out.print("Enter Teacher Name: ");
		teacher.setName(scanner.nextLine());
		teacher.setSchoolId(schoolId);
		
		return teacher;
	}
	
	public String inputFileName(Scanner scanner) {
		System.out.print("Enter file name: ");
		String fileName = scanner.nextLine();
		return fileName;
	}
	
	public String inputSchoolId(Scanner scanner) {
		System.out.print("\nPlease enter school Id: ");
		String schoolId = scanner.nextLine();
		return schoolId;
	}
	
	public void printExportedMessage(String schoolFileName, String teacherFileName) {
		System.out.println("Data exported to " + schoolFileName + " and " + teacherFileName );
	}
	
}
