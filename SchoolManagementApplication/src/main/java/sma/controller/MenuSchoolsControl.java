package sma.controller;

import java.util.List;
import java.util.Scanner;

import sma.entity.School;
import sma.entity.Teacher;
import sma.view.ApplicationMenu;
import sma.view.MainMenu;

public class MenuSchoolsControl {
	
	public void MenuSchool(List<School> schools,List<Teacher> teachers)
	{
		boolean cont = true;
		SchoolFunction schoolFunction = new SchoolFunction(schools, teachers);
		//MenuSchoolsControl menuSchoolControl = new MenuSchoolsControl();
		ApplicationMenu aMenu = new ApplicationMenu();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		do {
			aMenu.menuSchools();
			int choice = input.nextInt();
			input.nextLine();
			switch (choice)
			{
			case 1:
				schoolFunction.searchSchools();
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 2:
				schoolFunction.insertSchools();
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 3:
				System.out.println("Please ! enter School Id :");
				String id = input.nextLine();
				schoolFunction.searchSchoolsBySchoolId(id);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 4:
				System.out.println("Please ! enter School Name :");
				String name = input.nextLine();
				schoolFunction.searchSchoolsBySchoolName(name);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 5:
				System.out.println("Please ! enter School Address :");
				String address = input.nextLine();
				schoolFunction.searchSchoolsBySchoolAddress(address);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 6:
				System.out.println("Please ! enter School Phone :");
				String phone = input.nextLine();
				schoolFunction.searchSchoolsBySchoolPhone(phone);
				System.out.println("Press enter to continue ");
				input.nextLine();
				break;
			case 7 :
				aMenu.mainMenu();
				MainMenu.main(new String[] {});
				break;	
			case 8 :
				cont = false;
				//input.close();
				break;	
			}
			//menuSchoolControl.MenuSchool(schools,teachers);
		}while(cont==true);
	}

}
