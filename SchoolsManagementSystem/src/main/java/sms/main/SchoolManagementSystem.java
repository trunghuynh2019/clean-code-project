package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.function.*;
import sms.model.*;
import sms.view.*;

public class SchoolManagementSystem {

	public static void main(String[] args) {
		List<School> schools= new ArrayList<School>();
		ManagementInterface management = new Management();
		Scanner scanner = new Scanner(System.in);
		boolean stopProgram=true;
		int choice;
		String fileNameSchool = "dataSchool.xls";
		String fileNameTeacher = "dataTeacher.xls";
		do {
			MainView.showMainMenu();
			choice = MainView.getMainOption(scanner);
			System.out.println();
			
			switch (choice) {
				case 1: viewAllSchool(schools); break;
				case 2: addNewSchool(schools, scanner, management); break;
				case 3: addNewSchoolListFromFile(schools, scanner, management); break;
				case 4: manageTeachersList(schools, scanner, management, fileNameTeacher); break;
				case 5: exportAllSchool(schools, management, fileNameSchool); break;
				case 6: stopProgram=false; break;
			}
		} while(stopProgram);

	}

	// export data of school
	public static void exportAllSchool(List<School> schools,ManagementInterface management, String fileName) {
		management.exportDataOfSchools(schools, fileName);
	}
	
	// export data of teacher
	public static void exportAllTeacher(List<Teacher> teachers,ManagementInterface management, String fileName) {
		management.exportDataOfTeachers(teachers, fileName);
	}
	
	// Show information of all school in list
	public static void viewAllSchool(List<School> schools) {
		SchoolView.showAllSchool(schools);
	}

	// add new school into list school
	public static void addNewSchool(List<School> schools, Scanner scanner, ManagementInterface management) {
		School school = new School();
		SchoolView.enterInformationOfSchool(school,scanner);
		management.addSchool(schools, school);
	}

	
	// add the school list from file name
	public static void addNewSchoolListFromFile(List<School> schools, Scanner scanner, ManagementInterface management) {
		SchoolView.enterFileNameOfSchool();
		management.loadDatabaseOfSchool(scanner.nextLine(), schools);
		MainView.confirmLoadingFile();
	}
	
	// manage Teacher list
	public static void manageTeachersList(List<School> schools, Scanner scanner, ManagementInterface management, String fileName) {
		boolean backToMainMenu = true;
		
		viewAllSchool(schools);
		
		SchoolView.enterSchoolId();
		School school = management.searchSchoolById(schools, scanner.nextLine().toUpperCase());
		if (school == null) {
			SchoolView.showSchoolNotFound();
			return;
		} else {
			do {
				MainView.showTeacherManagementMenu(school);
				int choice = MainView.getTeacherManagementOption(scanner);
				System.out.println();
				switch (choice) {
					case 1: TeacherView.showAllTeachers(school); break;
					case 2: {
						List<Teacher> teachers = school.getTeachers();
						if(teachers==null) {
							school.setTeachers(new ArrayList<Teacher>());
						}
						Teacher teacher = new Teacher();
						TeacherView.enterInformationOfTeacher(teacher, scanner);
						management.signContractWithTeacher(school, teacher);
						break;
					}
					case 3: {
						List<Teacher> teachers = school.getTeachers();
						if(teachers==null) {
							school.setTeachers(new ArrayList<Teacher>());
						}
						TeacherView.enterFileNameOfTeacher();
						management.loadDatabaseOfTeacher(scanner.nextLine(), school);
						MainView.confirmLoadingFile();
						break;
					}
					case 4: {
						List<Teacher> teachers = school.getTeachers();
						if(teachers==null) {
							TeacherView.showMessageEmptyTeacherList();
						}
						else {
							TeacherView.enterTeacherName();
							Teacher teacher = management.searchTeacherByName(school.getTeachers(), scanner.nextLine());
							if (teacher == null) TeacherView.showTeacherNotFound();
							else TeacherView.showInformationOfTeacher(teacher);
						}
						break;
					}
					case 5: {
						List<Teacher> teachers = school.getTeachers();
						if(teachers==null) {
							TeacherView.showMessageEmptyTeacherList();
						}
						else {
							TeacherView.enterTeacherAddress();
							Teacher teacher = management.searchTeacherByAddress(school.getTeachers(), scanner.nextLine());
							if (teacher == null) TeacherView.showTeacherNotFound();
							else TeacherView.showInformationOfTeacher(teacher);
						}
						break;
					}
					case 6: exportAllTeacher(school.getTeachers(), management, fileName); break;
					case 7: backToMainMenu=false; break;
				}
			}while(backToMainMenu);
		}
	}
}
