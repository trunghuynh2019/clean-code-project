/*
 * Title
 * 
 * @author Huy
 * @date Sep 26, 2018
 * @version 1.0
 */
package sma.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sma.object.School;
import sma.object.Teacher;

public class SchoolManagementFunctions {
	
	private List<School> schoolList;
	
	public SchoolManagementFunctions() {
		super();
	}
	
	public SchoolManagementFunctions(List<School> schoolList) {
		super();
		this.schoolList = schoolList;
	}

	public void displayMainMenu() {
		System.out.println("========== School Management System ============");
		System.out.println("1. Display all schools.");
		System.out.println("2. Add school");
		System.out.println("3. Add teacher to school.");
		System.out.println("4. Exit");
		System.out.println("Enter your selection: ");
	}
	
	public boolean displayAllSchool() {
		if (schoolList.size() == 0) {
			System.out.println("List of schools are empty...");
			return false;
		} else {
			System.out.println("List of school: ");
			for (School s : schoolList) {
				System.out.println(s.toString());
			}
			return true;
		}
	}
	
	public void addSchool(Scanner scanner) {
		School school = new School();
		
        System.out.println("Enter school's name: ");
        String schoolName = scanner.nextLine();
        school.setName(schoolName);
        
        System.out.println("Enter school's address: ");
        String schoolAddress = scanner.nextLine();
        school.setAddress(schoolAddress);
        
        System.out.println("Enter the number of students: ");
        int numOfStudents = scanner.nextInt();
        scanner.nextLine();
        school.setNumOfStudents(numOfStudents);
        
        System.out.println("Enter the number of teachers: ");
        System.out.println("You must fill name and address for each teacher.");
        int numOfTeacher = scanner.nextInt();
        scanner.nextLine();
        List<Teacher> teacherList = new ArrayList<>();
        for (int i = 0; i < numOfTeacher; i++) {
        	Teacher newTeacher = new Teacher("","",school.getName());
            System.out.println("Enter teacher's name #" + i + ": ");
            newTeacher.setName(scanner.nextLine());
            System.out.println("Enter teacher's address #" + i +  ": ");
            newTeacher.setAddress(scanner.nextLine());
            teacherList.add(newTeacher);
        }
        school.setTeacherList(teacherList);
        this.schoolList.add(school);
	}
	
	public void addTeacherToSchool(School school, Scanner scanner) {
		Teacher newTeacher = new Teacher();
		
		System.out.println("Enter teacher's name: ");
		newTeacher.setName(scanner.nextLine());
		
		System.out.println("Enter teacher's address: ");
		newTeacher.setAddress(scanner.nextLine());
		
		school.getTeacherList().add(newTeacher);
	}
	
	public School findSchoolByName(String schoolName) {
		for (School s: schoolList) {
			if(s.getName().equals(schoolName)) {
				return s;
			}
		}
		return null;
	}
}
