/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.views;

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
		System.out.println("|   4. View All Teachers                	 |");
		System.out.println("|   5. Sign Contract With Teacher       	 |");
		System.out.println("|   6. Sign Contract With Teachers From File |");
		System.out.println("|   7. Exit                                  |");
		System.out.println("==============================================");
		System.out.print("Please Enter Your Choice: ");
	}
	/*
	 * Xong school printer
	 * Lam tiep teacher printer
	 * menu printer
	 * Lam cac function add teacher/school by console, by file
	 * */
	
}
