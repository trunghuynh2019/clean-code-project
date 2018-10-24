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
	
    public static void main( String[] args )
    {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
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
    			schoolController.exportSchoolsToText(EXPORTED_SCHOOL_FILE_NAME + ".txt");
    			schoolController.exportTeacherToText(EXPORTED_TEACHER_FILE_NAME + ".txt");
    			System.out.println("Data exported to " + EXPORTED_SCHOOL_FILE_NAME + ".txt and " + EXPORTED_TEACHER_FILE_NAME + ".txt" );
    			break;
    		case 8: // Export to excel
    			schoolController.exportSchoolsToExcel(EXPORTED_SCHOOL_FILE_NAME + ".xlsx");
				schoolController.exportTeacherToExcel(EXPORTED_TEACHER_FILE_NAME + ".xlsx");
				System.out.println("Data exported to " + EXPORTED_SCHOOL_FILE_NAME + ".xlsx and " + EXPORTED_TEACHER_FILE_NAME + ".xlsx" );
    			break;
    		case 9: // Export to pdf
    			schoolController.exportSchoolToPDF(EXPORTED_SCHOOL_FILE_NAME + ".pdf"); 
    			schoolController.exportTeacherToPDF(EXPORTED_TEACHER_FILE_NAME + ".pdf");
    			break;
    		case 10: // Export to html
    			schoolController.exportSchoolToHtml(EXPORTED_SCHOOL_FILE_NAME + ".html");
    			schoolController.exportTeacherToHtml(EXPORTED_TEACHER_FILE_NAME + ".html");
    			break;
    		case 11: // Exit
    			flag = false;
    			break;
    		}
    		
    	} while (flag);
    	scanner.close();
    }
}
