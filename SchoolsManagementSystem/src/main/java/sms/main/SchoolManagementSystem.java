package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.function.*;
import sms.model.*;
import sms.view.*;

public class SchoolManagementSystem {

	private final static String PATH = System.getProperty("user.dir") + "\\fileData\\";
	private final static String FILE_DATA_SCHOOL = "dataSchool";
	private final static String FILE_DATA_TEACHER = "dataTeacher";
	
	public static void main(String[] args) {
		List<School> schools= new ArrayList<School>();
		ManagementInterface management = new Management();
		Scanner scanner = new Scanner(System.in);
		boolean stopProgram=true;
		int choice;
		do {
			MainView.showMainMenu();
			choice = MainView.getMainOption(scanner);
			System.out.println();
			
			switch (choice) {
				case 1: viewAllSchool(schools); break;
				case 2: addNewSchool(schools, scanner, management); break;
				case 3: manageTeachersList(schools, scanner, management); break;
				case 4: manageFileSchool(schools, management, scanner); break;
				case 5: stopProgram=false; break;
			}
		} while(stopProgram);
	}

	// export data of school into excel file
	public static void exportAllSchool(List<School> schools,ManagementInterface management) {
		management.exportDataOfSchoolsToExcel(schools, PATH+FILE_DATA_SCHOOL + ".xls");
	}
	
	// export data of teacher into excel file
	public static void exportAllTeacher(List<Teacher> teachers,ManagementInterface management) {
		management.exportDataOfTeachersToExcel(teachers, PATH+FILE_DATA_TEACHER + ".xls");
	}
	
	// export data of school into pdf file
	public static void exportAllShoolInPdf(List<School> schools, ManagementInterface management) {
		management.exportDataOfSchoolsToPDF(schools,  PATH+FILE_DATA_SCHOOL + ".pdf");
	}
	
	// export data of teacher into pdf file
	public static void exportAllTeacherInPdf(List<Teacher> teachers, ManagementInterface management) {
		management.exportDataOfTeachersToPDF(teachers, PATH+FILE_DATA_TEACHER + ".pdf");
	}
	
	// export data of school into html file
	public static void exportAllShoolInHtml(List<School> schools, ManagementInterface management) {
		management.exportDataOfSchoolsToHTML(schools, PATH+FILE_DATA_SCHOOL + ".html");
	}
	
	// export data of teacher into html file
	public static void exportAllTeacherInHtml(List<Teacher> teachers, ManagementInterface management) {
		management.exportDataOfTeachersToHTML(teachers, PATH+FILE_DATA_TEACHER + ".html");
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
	public static void importAllSchoolFromTextFile(List<School> schools, Scanner scanner, ManagementInterface management) {
		SchoolView.enterFileNameOfSchool();
		management.loadDatabaseOfSchool(scanner.nextLine(), schools);
		MainView.confirmLoadingFile();
	}
	
	// add the teacher list from file name
	public static void importAllTeacherFromTextFile(School school, Scanner scanner, ManagementInterface management) {
		SchoolView.enterFileNameOfSchool();
		management.loadDatabaseOfTeacher(scanner.nextLine(), school);
		MainView.confirmLoadingFile();
	}
	
	// manage Teacher list
	public static void manageTeachersList(List<School> schools, Scanner scanner, ManagementInterface management) {
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
					case 4: {
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
					case 5: manageFileTeacher(school, management, scanner); break;
					case 6: backToMainMenu=false; break;
				}
			}while(backToMainMenu);
		}
	}
	
	public static void manageFileSchool(List<School> schools,ManagementInterface management,  Scanner sc) {
		int choice;
		boolean backToMain = true;
		do {
			MainView.showFileManagementMenu("School");
			choice = MainView.getMainOption(sc);
			switch(choice) {
				case 1: exportAllSchool(schools, management); break;
				case 2: exportAllShoolInPdf(schools, management); break;
				case 3: exportAllShoolInHtml(schools, management); break;
				case 4: importAllSchoolFromTextFile(schools, sc, management); break;
				case 5: backToMain = false; break;
			}
		}while(backToMain);
	}
	
	public static void manageFileTeacher(School school,ManagementInterface management,  Scanner sc) {
		int choice;
		boolean backToMain = true;
		List<Teacher> teachers = school.getTeachers();
		do {
			MainView.showFileManagementMenu("School");
			choice = MainView.getMainOption(sc);
			switch(choice) {
				case 1: exportAllTeacher(teachers, management);break;
				case 2: exportAllTeacherInPdf(teachers, management); break;
				case 3: exportAllTeacherInHtml(teachers, management); break;
				case 4: importAllTeacherFromTextFile(school, sc, management);
				case 5: backToMain = false; break;
			}
		}while(backToMain);
	}
}
