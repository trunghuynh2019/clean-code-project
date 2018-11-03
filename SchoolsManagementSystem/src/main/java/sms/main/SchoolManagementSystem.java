package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.management.file.export.ExcelExport;
import sms.management.file.export.FileExport;
import sms.management.file.export.HtmlExport;
import sms.management.file.export.JSonExport;
import sms.management.file.export.PdfExport;
import sms.management.file.importt.FileImport;
import sms.management.file.importt.JSonImport;
import sms.management.file.importt.TextImport;
import sms.management.function.*;
import sms.model.*;
import sms.view.*;

public class SchoolManagementSystem {

	private final static String PATH = System.getProperty("user.dir") + "\\fileData\\";
	private final static String FILE_DATA_SCHOOL = PATH + "dataSchool";
	private final static String FILE_DATA_TEACHER = PATH + "dataTeacher";
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		List<School> schools= new ArrayList<School>();
		Management management = new ManagementImpl();
		
		FileExport fileExport = null;
		FileImport fileImport = null;

		boolean stopProgram=true;
		int choice;
		
		do {
			MainView.showMainMenu();
			choice = MainView.getMainOption(scanner);
			System.out.println();
			
			switch (choice) {
				case 1: {
					if(schools.isEmpty()) {
						SchoolView.showEmptySchool();
					}else {
						management.viewAllSchool(schools); 
					}
					break;
				}
				case 2:{
					School school = new School();
					SchoolView.enterInformationOfSchool(school,scanner);
					management.addSchool(schools, school);
					break;
				}
				case 3: manageTeachersList(schools, fileExport, fileImport, management, scanner); break;
				case 4: manageFileSchool(schools, fileExport, fileImport, scanner); break;
				case 5: stopProgram=false; break;
			}
		} while(stopProgram);
	}

	
	/*
	 * Teachers List Management
	 */
	public static void manageTeachersList(List<School> schools, FileExport fileExport, FileImport fileImport, Management management, Scanner scanner) {
		boolean backToMainMenu = true;
		
		management.viewAllSchool(schools);
		
		SchoolView.enterSchoolId();
		School school = management.searchSchoolById(schools, scanner.nextLine().toUpperCase());
		if (school == null) {
			SchoolView.showSchoolNotFound();
			return;
		} else {
			List<Teacher> teachers = school.getTeachers();
			if(teachers==null) {
				school.setTeachers(new ArrayList<Teacher>());
			}
			do {
				MainView.showTeacherManagementMenu(school);
				int choice = MainView.getTeacherManagementOption(scanner);
				System.out.println();
				switch (choice) {
					case 1: {
						if(teachers.isEmpty()) {
							TeacherView.showMessageEmptyTeacherList();
						}else {
							management.viewAllTeacher(teachers);
						} 
						break;
					}
					case 2: {
						Teacher teacher = new Teacher();
						TeacherView.enterInformationOfTeacher(teacher, scanner);
						management.signContractWithTeacher(teachers, teacher);
						break;
					}
					case 3: {
						if(teachers.isEmpty()) {
							TeacherView.showMessageEmptyTeacherList();
						}else {
							TeacherView.enterTeacherName();
							Teacher teacher = management.searchTeacherByName(school.getTeachers(), scanner.nextLine());
							if (teacher == null) {
								TeacherView.showTeacherNotFound();
							}else {
								TeacherView.showInformationOfTeacher(teacher);
							}
						}
						break;
					}
					case 4: {
						if(teachers.isEmpty()) {
							TeacherView.showMessageEmptyTeacherList();
						} else {
							TeacherView.enterTeacherAddress();
							Teacher teacher = management.searchTeacherByAddress(school.getTeachers(), scanner.nextLine());
							if (teacher == null) {
								TeacherView.showTeacherNotFound();
							}else {
								TeacherView.showInformationOfTeacher(teacher);
							}
						}
						break;
					}
					case 5: manageFileTeacher(teachers, fileExport, fileImport,  scanner); break;
					case 6: backToMainMenu=false; break;
				}
			}while(backToMainMenu);
		}
	}
	
	/*
	 *  File Management
	 */
	public static void manageFileSchool(List<School> schools, FileExport fileExport, FileImport fileImport, Scanner scanner) {
		int choice;
		boolean backToMain = true;
		boolean checkHandleFile;
		do {
			MainView.showFileManagementMenu("School");
			choice = MainView.getMainOption(scanner);
			switch(choice) {
				case 1:{
					fileExport = new ExcelExport();
					checkHandleFile = fileExport.exportDataOfSchoolToFile(schools, FILE_DATA_SCHOOL+".xls"); 
					if(checkHandleFile) {
						SchoolView.showStatusExportFile("Successed!");
					}else {
						SchoolView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 2:{
					fileExport = new PdfExport();
					checkHandleFile = fileExport.exportDataOfSchoolToFile(schools, FILE_DATA_SCHOOL+".pdf"); 
					if(checkHandleFile) {
						SchoolView.showStatusExportFile("Successed!");
					}else {
						SchoolView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 3:{
					fileExport = new HtmlExport();
					checkHandleFile = fileExport.exportDataOfSchoolToFile(schools, FILE_DATA_SCHOOL+".html"); 
					if(checkHandleFile) {
						SchoolView.showStatusExportFile("Successed!");
					}else {
						SchoolView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 4:{
					fileExport = new JSonExport();
					checkHandleFile = fileExport.exportDataOfSchoolToFile(schools, FILE_DATA_SCHOOL+".json"); 
					if(checkHandleFile) {
						SchoolView.showStatusExportFile("Successed!");
					}else {
						SchoolView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 5:{
					fileImport = new JSonImport();
					checkHandleFile = fileImport.importDataOfSchoolFromFile(schools, FILE_DATA_SCHOOL+".json"); 
					if(checkHandleFile) {
						SchoolView.showStatusImportFile("Successed!");
					}else {
						SchoolView.showStatusImportFile("Failed!");
					}
					break;
				}
				case 6:{
					fileImport = new TextImport();
					SchoolView.enterFileNameOfSchool();
					checkHandleFile = fileImport.importDataOfSchoolFromFile(schools, scanner.nextLine()); 
					if(checkHandleFile) {
						SchoolView.showStatusImportFile("Successed!");
					}else {
						SchoolView.showStatusImportFile("Failed!");
					}
					break;
				}
				case 7: backToMain = false; break;
			}
		}while(backToMain);
	}
	
	public static void manageFileTeacher(List<Teacher> teachers, FileExport fileExport, FileImport fileImport,  Scanner scanner) {
		int choice;
		boolean backToMain = true;
		boolean checkHanleFile;
		do {
			MainView.showFileManagementMenu("Teacher");
			choice = MainView.getMainOption(scanner);
			switch(choice) {
				case 1:{
					fileExport = new ExcelExport();
					checkHanleFile = fileExport.exportDataOfTeacherToFile(teachers, FILE_DATA_TEACHER+".xls");
					if(checkHanleFile) {
						TeacherView.showStatusExportFile("Successed!");
					}else {
						TeacherView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 2:{
					fileExport = new PdfExport();
					checkHanleFile = fileExport.exportDataOfTeacherToFile(teachers, FILE_DATA_TEACHER+".pdf"); 
					if(checkHanleFile) {
						TeacherView.showStatusExportFile("Successed!");
					}else {
						TeacherView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 3:{
					fileExport = new HtmlExport();
					checkHanleFile = fileExport.exportDataOfTeacherToFile(teachers, FILE_DATA_TEACHER+".html"); 
					if(checkHanleFile) {
						TeacherView.showStatusExportFile("Successed!");
					}else {
						TeacherView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 4:{
					fileExport = new JSonExport();
					checkHanleFile = fileExport.exportDataOfTeacherToFile(teachers, FILE_DATA_TEACHER+".json"); 
					if(checkHanleFile) {
						TeacherView.showStatusExportFile("Successed!");
					}else {
						TeacherView.showStatusExportFile("Failed!");
					}
					break;
				}
				case 5:{
					fileImport = new JSonImport();
					checkHanleFile = fileImport.importDataOfTeacherFromFile(teachers, FILE_DATA_TEACHER+".json"); 
					if(checkHanleFile) {
						TeacherView.showStatusImportFile("Successed!");
					}else {
						TeacherView.showStatusImportFile("Failed!");
					}
					break;
				}
				case 6:{
					fileImport = new TextImport();
					TeacherView.enterFileNameOfTeacher();
					checkHanleFile = fileImport.importDataOfTeacherFromFile(teachers, scanner.nextLine()); 
					if(checkHanleFile) {
						TeacherView.showStatusImportFile("Successed!");
					}else {
						TeacherView.showStatusImportFile("Failed!");
					}
					break;
				}
				case 7: backToMain = false; break;
			}
		}while(backToMain);
	}
}
