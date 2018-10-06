package sm.view;

import java.util.Scanner;

public class ApplicationMenu {
	
	public ApplicationMenu() {
		super();
		// TODO Auto-generated constructor stub     
	}
	
	/**
	 * Main Menu 
	 */
	public void mainMenu()
	{
		System.out.println("----  EDUCATION MANAGEMENT  ----");
		System.out.println("\t1. Menu for Schools");
		System.out.println("\t2. Menu for Teachers");
		System.out.println("\t3. Writing from School File (truong.txt) to Schools Array");
		System.out.println("\t4. Writing from Teacher File (giaovien.txt) to Teachers Array");
		System.out.println("\t5. Exit");
		System.out.println("===================================");
		System.out.print("Please input your choice ( number of menu ) : ");
	}
	
	/**
	 * Menu Schools
	 */
	public void menuSchools()
	{
		System.out.println("----  MENU SCHOOL CONTROL ----");
		System.out.println("\t1. Display Schools");
		System.out.println("\t2. Insert Schools");
		System.out.println("\t3. Find Schools by School Id");
		System.out.println("\t4. Find Schools by School Name");
		System.out.println("\t5. Find Schools by School Address");
		System.out.println("\t6. Find Schools by School Phone");
		System.out.println("\t7. Return to Main Menu");
		System.out.println("\t8. Exit");
		System.out.println("======================================");
		System.out.print("Please enter your choice: ");
	}
	
	/**
	 * Menu Teachers
	 */
	public void menuTeachers()
	{
		System.out.println("----  MENU TEACHER CONTROL  ----");
		System.out.println("\t1. Display Teachers belongs to a specific School Name");
		System.out.println("\t2. Insert Teachers");
		System.out.println("\t3. Find Teachers by Teacher Id");
		System.out.println("\t4. Find Teachers by Teacher Name");
		System.out.println("\t5. Find Teachers by Teacher Address");
		System.out.println("\t6. Find Teachers by Teacher Phone");
		System.out.println("\t7. Return to Main Menu");
		System.out.println("\t8. Exit");
		System.out.println("========================================");
		System.out.print("Please enter your choice: ");
	}

	public void returnMenu(Scanner scanner) {
		System.out.print("Press Enter to return main Menu ");
		scanner.nextLine();
	}
}
