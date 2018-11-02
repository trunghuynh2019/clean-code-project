package com.cleancode.education;

import java.util.Scanner;

import com.cleancode.education.controller.SchoolController;
import com.cleancode.education.controller.TeacherController;
import com.cleancode.education.controller.impl.SchoolControllerImpl;
import com.cleancode.education.controller.impl.TeacherControllerImpl;
import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.repository.SchoolRepository;
import com.cleancode.education.repository.TeacherRepository;
import com.cleancode.education.repository.impl.SchoolRepositoryImpl;
import com.cleancode.education.repository.impl.TeacherRepositoryImpl;
import com.cleancode.education.service.SchoolService;
import com.cleancode.education.service.TeacherService;
import com.cleancode.education.service.impl.SchoolServiceImpl;
import com.cleancode.education.service.impl.TeacherServiceImpl;
import com.cleancode.education.views.SchoolPrinter;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String EXPORTED_SCHOOL_FILE_NAME = "exportedschool";
	private static final String EXPORTED_TEACHER_FILE_NAME = "exportedteacher";
	private static final String SCHOOL_DATABASE = "schooldatabase";
	private static final String TEACHER_DATABASE = "teacherdatabase";
	
    public static void main( String[] args )
    {
    	
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl(SCHOOL_DATABASE);
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	
    	
    	SchoolPrinter printer = new SchoolPrinter();
    	Scanner scanner = new Scanner(System.in);
    	boolean flag = true;
    	int choice;
    	
    	do {
    		printer.printMenu();
    		choice = scanner.nextInt();
    		scanner.nextLine();
    		
    		switch(choice) {
    		case 1: // View All Schools 
    			schoolController.viewAllSchools();
    			break;
    		case 2: // Add A New School
    			School school = printer.inputNewSchool(scanner);
    			schoolController.addSchool(school);
    			break;
    		case 3: // Add Schools From File
    			String schoolFile = printer.inputFileName(scanner);
    			schoolController.addSchoolsFrom(schoolFile);
    			break;
    		case 4: // View All Teachers
    			schoolController.viewAllSchools();
    			if (!schoolService.getAllSchool().isEmpty()) {
        			String inputSchoolId = printer.inputSchoolId(scanner);
        			School schoolFoundById = schoolService.getSchoolById(inputSchoolId);
        			TeacherRepository teacherRepository = new TeacherRepositoryImpl(schoolFoundById);
        			TeacherService teacherService = new TeacherServiceImpl(teacherRepository);
        			TeacherController teacherController = new TeacherControllerImpl(teacherService);
        			teacherController.viewAllTeachers();
    			}
    			break;
    		case 5: // Sign Contract With Teacher
    			schoolController.viewAllSchools();
    			String inputSchoolId = printer.inputSchoolId(scanner);
    			Teacher teacher = printer.inputTeacher(scanner, inputSchoolId);
    			schoolController.signContractWithTeacher(teacher);
    			break;
    		case 6: // Sign Contract With Teachers From File
    			String teacherFile = printer.inputFileName(scanner);
    			schoolController.signContractWithTeacherFrom(teacherFile);
    			break;
    		case 7: // Export schools/teachers to text
    			schoolController.exportSchoolToText(EXPORTED_SCHOOL_FILE_NAME + ".txt");
    			schoolController.exportTeacherToText(EXPORTED_TEACHER_FILE_NAME + ".txt");
    			printer.printExportedMessage(EXPORTED_SCHOOL_FILE_NAME + ".txt", EXPORTED_TEACHER_FILE_NAME + ".txt");
    			break;
    		case 8: // Export to excel
    			schoolController.exportSchoolToExcel(EXPORTED_SCHOOL_FILE_NAME + ".xlsx");
				schoolController.exportTeacherToExcel(EXPORTED_TEACHER_FILE_NAME + ".xlsx");
				printer.printExportedMessage(EXPORTED_SCHOOL_FILE_NAME + ".xlsx", EXPORTED_TEACHER_FILE_NAME + ".xlsx");
    			break;
    		case 9: // Export to pdf
    			schoolController.exportSchoolToPDF(EXPORTED_SCHOOL_FILE_NAME + ".pdf"); 
    			schoolController.exportTeacherToPDF(EXPORTED_TEACHER_FILE_NAME + ".pdf");
    			printer.printExportedMessage(EXPORTED_SCHOOL_FILE_NAME + ".pdf", EXPORTED_TEACHER_FILE_NAME + ".pdf");
    			break;
    		case 10: // Export to html
    			schoolController.exportSchoolToHtml(EXPORTED_SCHOOL_FILE_NAME + ".html");
    			schoolController.exportTeacherToHtml(EXPORTED_TEACHER_FILE_NAME + ".html");
    			printer.printExportedMessage(EXPORTED_SCHOOL_FILE_NAME + ".html", EXPORTED_TEACHER_FILE_NAME + ".html");
    			break;
    		case 11: // Export to json
    			schoolController.exportSchoolToJson(EXPORTED_SCHOOL_FILE_NAME + ".json");
    			schoolController.exportTeacherToJson(EXPORTED_TEACHER_FILE_NAME + ".json");
    			printer.printExportedMessage(EXPORTED_SCHOOL_FILE_NAME + ".json", EXPORTED_TEACHER_FILE_NAME + ".json");
    			break;
    		case 12: // Exit
    			flag = false;
    			schoolController.exportSchoolToJson(SCHOOL_DATABASE + ".json");
    			schoolController.exportTeacherToJson(TEACHER_DATABASE + ".json");
    			break;
    		}
    		
    	} while (flag);
    	scanner.close();
    }
}
