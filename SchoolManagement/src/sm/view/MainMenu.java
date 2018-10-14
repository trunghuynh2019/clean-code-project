package sm.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sm.controller.MenuSchoolsControl;
import sm.controller.MenuTeachersControl;
import sm.controller.SchoolFunction;
import sm.controller.TeacherFunction;
import sm.entity.School;
import sm.entity.Teacher;

public class MainMenu {
	
	public static List<School> schools = new ArrayList<>();
	public static List<Teacher> teachers = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean cont = true;
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
				cont= false;
				break;
				
		}	
		} while (cont==true);
	}

}
