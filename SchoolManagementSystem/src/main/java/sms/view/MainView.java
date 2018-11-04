package sms.view;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.service.FileExportService;
import sms.service.FileImportService;
import sms.service.SchoolService;
import sms.service.TeacherService;
import sms.service.impl.FileExportServiceImpl;
import sms.service.impl.FileImportServiceImpl;
import sms.service.impl.SchoolServiceImpl;
import sms.service.impl.TeacherServiceImpl;

public class MainView {
	private SchoolService schoolService = new SchoolServiceImpl();
	private TeacherService teacherService = new TeacherServiceImpl();
	
	FileImportService fileImportService = new FileImportServiceImpl();
	FileExportService fileExportService = new FileExportServiceImpl();
	
	public void displayMainMenu() {
		System.out.println("====================================");
		System.out.println("----  SCHOOL MANAGEMENT SYSTEM  ----");
		
		System.out.println("\t1. Display all schools");
		System.out.println("\t2. Add a new school");
		System.out.println("\t3. Choose a school to manage teachers");
		System.out.println("\t4. Enter data by file text input");
		System.out.println("\t5. Export data to text file");
		System.out.println("\t6. Export data to excel file");
		System.out.println("\t7. Export data to pdf file");
		System.out.println("\t8. Export data to html file");
		System.out.println("\t9. Exit");
		System.out.println("------------------------------------");
		System.out.print("Please enter your choice: ");
	}

	public void displayManageTeacherMenu(School school) {
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

	public void loopAgain(Scanner scanner) {
		System.out.print("Press Enter to continue...");
		scanner.nextLine();
	}

//	public void enterSchoolId() {
//		System.out.print("Enter a school id to manage its teachers list: ");
//	}

//	public void enterTeacherName() {
//		System.out.print("Enter a teacher name to search: ");
//	}
//
//	public void enterTeacherId() {
//		/*System.out.print("Enter a teacher address to search: ");*/
//		System.out.print("Enter a teacher id to search: ");
//	}
//	
//	public void enterTeacherFileName() {
//		System.out.print("Name of teacher file: ");
//	}
	//
//	public void enterSchoolFileName() {
//		System.out.println("First, put the files into folder src/main/resources/file");
//		System.out.println("Second, enter the name of file. Ex: truong.txt, giaovien.txt");
//		System.out.print("Name of school file: ");
//	}
	
	private void fileNotFound() {
		System.out.println("File not found!...");
	}
	
	private void readFileSuccessfully() {
		System.out.println("Imported successfully!...");
	}
	
	private void exportFileSuccessfully() {
		System.out.println("Exported successfully!...");
	}
	
	private void exportFileFailed() {
		System.out.println("Writing failed!...");
	}
	
	private void importFileResult(boolean importSuccessfully, Scanner scanner) {
		if (importSuccessfully) {
			readFileSuccessfully();
		} else {
			fileNotFound();
		}
		loopAgain(scanner);
	}
	
	private void exportFileResult(boolean exportSuccessfully, Scanner scanner) {
		if (exportSuccessfully) {
			exportFileSuccessfully();
		} else {
			exportFileFailed();
		}
		loopAgain(scanner);
	}
	
	/*
	 * Function Views.
	 */
	
	public void viewAllSchools(List<School> schools, Scanner scanner) {
		schoolService.displayAllSchool(schools);
		loopAgain(scanner);
	}
	
	public void addNewSchool(List<School> schools, Scanner scanner) {
		schoolService.addNewSchool(schools, scanner);
		loopAgain(scanner);
	}
	
	public void manageTeachers(List<School> schools, Scanner scanner) {
		teacherService.manageTeachers(schools, scanner);
	}
	
	public void importByTextFile(List<School> schools, Scanner scanner) {
		boolean successful = fileImportService.importByTextFile(schools, scanner);
		importFileResult(successful, scanner);
	}
	
	public void exportToTextFile(List<School> schools, Scanner scanner) {
		boolean successful = fileExportService.exportToTextFile(schools, scanner);
		exportFileResult(successful, scanner);
	}
	public void exportToExcelFile(List<School> schools, Scanner scanner) {
		boolean successful = fileExportService.exportToExcelFile(schools, scanner);
		exportFileResult(successful, scanner);
	}
	public void exportToPdfFile(List<School> schools, Scanner scanner) {
		boolean successful = fileExportService.exportToPdfFile(schools, scanner);
		exportFileResult(successful, scanner);
	}
	public void exportToHtmlFile(List<School> schools, Scanner scanner) {
		boolean successful = fileExportService.exportToHtmlFile(schools, scanner);
		exportFileResult(successful, scanner);
	}
}
