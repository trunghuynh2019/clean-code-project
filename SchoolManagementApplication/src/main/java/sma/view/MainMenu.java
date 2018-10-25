package sma.view;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import sma.controller.MenuSchoolsControl;
import sma.controller.MenuTeachersControl;
import sma.controller.SchoolFunction;
import sma.controller.TeacherFunction;
import sma.entity.School;
import sma.entity.Teacher;



public class MainMenu {
	
	public static List<School> schools = new ArrayList<School>();
	public static List<Teacher> teachers = new ArrayList<Teacher>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean cont = true;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		ApplicationMenu appMenu = new ApplicationMenu();
		MenuSchoolsControl menuSchoolControl = new MenuSchoolsControl();
		MenuTeachersControl menuTeachersControl = new MenuTeachersControl();
		SchoolFunction schoolFunction = new SchoolFunction(schools, teachers);
		TeacherFunction teacherFunction = new TeacherFunction(teachers);
		do {
		appMenu.mainMenu();
		int choice = input.nextInt();
		switch (choice)
		{
			case 1 : 
				appMenu.menuSchools();
				menuSchoolControl.MenuSchool(schools, teachers);
				break;
			case 2 : 
				appMenu.menuTeachers();
				menuTeachersControl.MenuTeacher(teachers);
				break;
			case 3 : 
				schoolFunction.WritingFileToSchools(schools);
				break;
			case 4 : 
				teacherFunction.WritingFileToTeachers(teachers);
				break;
			case 5 :
				schoolFunction.SchoolsWriteIntoExcel();
				break;
			case 6 :
				teacherFunction.TeachersWriteIntoExcel();
				break;
			case 7 :
				schoolFunction.SchoolsWriteIntoPDF();
				break;
			case 8 :
				teacherFunction.TeachersWriteIntoPDF();
				break;
			case 9 :
				schoolFunction.SchoolsWriteIntoHTML();
				break;
			case 10 :
				teacherFunction.TeachersWriteIntoHTML();
				break;
			case 11 :
				cont= false;
				break;
		}	
		} while (cont==true);
	}

}
