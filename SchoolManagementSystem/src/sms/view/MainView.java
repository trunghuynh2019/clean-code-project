package sms.view;

import java.util.Scanner;

public class MainView {
	public static void displayMainMenu() {
		System.out.println("====================================");
		System.out.println("----  SCHOOL MANAGEMENT SYSTEM  ----");
		System.out.println("\t1. Display all schools");
		System.out.println("\t2. Add a new school");
		System.out.println("\t3. Choose a school to manage teachers");
		System.out.println("\t4. Exit");
		System.out.println("-----");
		System.out.println("Enter your choice: ");
	}
	
	public static void loopAgain(Scanner scanner) {
		System.out.print("Press Enter to continue...");
		scanner.nextLine();
	}
}
