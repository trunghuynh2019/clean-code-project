package com.cleancode.education;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cleancode.education.functions.SchoolManagement;
import com.cleancode.education.functions.SchoolManagementImpl;
import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.views.SchoolPrinter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	List<School> schools = new ArrayList<>();
    	SchoolManagement schoolManagement = new SchoolManagementImpl();
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
    			schoolManagement.viewAllSchools(schools);
    			break;
    		case 2: // Add A New School
    			School school = printer.inputNewSchool();
    			schoolManagement.addSchool(schools, school);
    			break;
    		case 3: // Add Schools From File
    			String schoolFile = printer.inputFileName(scanner);
    			schoolManagement.addSchoolFrom(schoolFile, schools);
    			break;
    		case 4: // View All Teachers
    			schoolManagement.viewAllSchools(schools);
    			if (!schools.isEmpty()) {
        			String inputSchoolId = printer.inputSchoolId(scanner);
        			School schoolFoundById = schoolManagement.findSchoolById(schools, inputSchoolId);
        			schoolManagement.viewAllTeachers(schoolFoundById);
    			}
    			break;
    		case 5: // Sign Contract With Teacher
    			schoolManagement.viewAllSchools(schools);
    			String inputSchoolId2 = printer.inputSchoolId(scanner);
    			School schoolFoundById2 = schoolManagement.findSchoolById(schools, inputSchoolId2);
    			Teacher teacher = printer.inputTeacher(inputSchoolId2);
    			schoolManagement.signContractWithTeacher(schoolFoundById2, teacher);
    			break;
    		case 6: // Sign Contract With Teachers From File
    			String teacherFile = printer.inputFileName(scanner);
    			schoolManagement.signContractWithTeacherFrom(teacherFile, schools);
    			break;
    		case 7: // Exit
    			flag = false;
    			break;
    		}
    		
    	} while (flag);
    	scanner.close();
    }
}
