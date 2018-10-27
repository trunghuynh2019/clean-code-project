package sms.view;

import sms.model.*;
import java.util.Scanner;

public class MainView {
	public static void showMainMenu() {
		System.out.println("\n========== SCHOOL MANAGEMENT SYSTEM ==========");
		System.out.println("|   1. View All Schools                      |");
		System.out.println("|   2. Add A New School                      |");
		System.out.println("|   3. Manage Teacher Of School              |");
		System.out.println("|   4. File Management                       |");
		System.out.println("|   5. Exit                                  |");
		System.out.println("==============================================");
		System.out.print("Please Enter Your Choice: ");
	}

	public static void showFileManagementMenu(String type) {
		System.out.println("\n========== FILE MANAGEMENT SYSTEM ==========");
		System.out.println("|  1. Export All "+type+" Into Excel File  ");
		System.out.println("|  2. Export All "+type+" Into PDF File ");
		System.out.println("|  3. Export All "+type+" Into HTML File ");
		System.out.println("|  4. Import All "+type+" From Text File ");
		System.out.println("|  5. Back To Main Menu                ");
		System.out.println("==============================================");
		System.out.print("Please Enter Your Choice: ");
	}
	
	public static void showTeacherManagementMenu(School school) {
		System.out.println("========= Welcome to " + school.getName().toUpperCase() + " school! =========");
		System.out.println("|   1. View All Teachers                ");
		System.out.println("|   2. Sign Contract With Teacher       ");
		System.out.println("|   3. Search Teacher By Name           ");
		System.out.println("|   4. Search Teacher By Address        ");
		System.out.println("|   5. File Management					");
		System.out.println("|   6. Back To Main Menu                ");
		System.out.println("========================================");
		System.out.print("Please Enter Your Choice: ");
	}

	public static int getMainOption(Scanner scanner) {
		return Integer.parseInt(scanner.nextLine());
	}
	
	public static int getTeacherManagementOption(Scanner scanner) {
		return Integer.parseInt(scanner.nextLine());
	}
	
	public static void confirmLoadingFile() {
		System.out.println("Load successfull !");
	}
}
