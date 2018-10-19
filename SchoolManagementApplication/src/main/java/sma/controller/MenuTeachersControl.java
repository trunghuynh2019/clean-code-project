package sma.controller;

import java.util.List;
import java.util.Scanner;

import sma.entity.Teacher;
import sma.view.ApplicationMenu;
import sma.view.MainMenu;

public class MenuTeachersControl {

	public void MenuTeacher(List<Teacher> teachers) 
	{
		boolean cont = true;
		String name, schoolId;
		TeacherFunction teacherFunction = new TeacherFunction(teachers);
		//MenuTeachersControl menuTeachersControl = new MenuTeachersControl();
		ApplicationMenu aMenu = new ApplicationMenu();
		do {
			aMenu.menuTeachers();
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			//MenuTeacher(teachers);
			switch (choice)
			{
			case 1:
				System.out.println("Display Teachers belongs to a specific School Id ");
				schoolId = input.nextLine();
				input .nextLine();
				teacherFunction.searchTeachersBySchoolId(schoolId);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 2:
				System.out.println("Please ! Input School Id");
				schoolId = input.nextLine();
				input .nextLine();
				teacherFunction.signContractWithTeacher(input, schoolId, teachers);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 3:
				System.out.println("Please ! enter Teacher Id :");
				String id = input.nextLine();
				input .nextLine();
				teacherFunction.searchTeachersByTeacherId(id);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 4:
				System.out.println("Please ! enter Teacher Name :");
				name = input.nextLine();
				input .nextLine();
				teacherFunction.searchTeachersByTeacherName(name);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 5:
				System.out.println("Please ! enter Teacher Address :");
				String address = input.nextLine();
				input .nextLine();
				teacherFunction.searchTeachersByTeacherAddress(address);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 6:
				System.out.println("Please ! enter Teacher Phone :");
				String phone = input.nextLine();
				input .nextLine();
				teacherFunction.searchTeachersByTeacherPhone(phone);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 7 :
				aMenu.mainMenu();
				MainMenu.main(new String[] {});
				input.nextLine();
				break;	
			case 8 :
				cont = false;
				//input.close();
				break;	
			}
	
		}while(cont== true);
	}

}
