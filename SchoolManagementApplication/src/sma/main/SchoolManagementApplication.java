/*
 * Title
 * 
 * @author Huy
 * @date Sep 26, 2018
 * @version 1.0
 */
package sma.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sma.function.SchoolManagementFunctions;
import sma.object.School;

public class SchoolManagementApplication {
	
	public static void main(String[] args) {
		List<School> schoolList = new ArrayList<>();
		SchoolManagementFunctions smf = new SchoolManagementFunctions(schoolList);
		boolean flag = true;
		Scanner scanner = new Scanner(System.in);
		
		do {
			
			smf.displayMainMenu();
			int selection = scanner.nextInt();
			scanner.nextLine();
			switch (selection) {
			case 1:
				smf.displayAllSchool();
				System.out.println("Press enter to continue...");
				scanner.nextLine();
				break;
			case 2:
				smf.addSchool(scanner);
				break;
			case 3:
				if (smf.displayAllSchool()) {
					System.out.println("Please enter school's name to add teacher: ");
					String schoolName = scanner.nextLine();
					School school = smf.findSchoolByName(schoolName);
					if(school != null) {
						smf.addTeacherToSchool(school, scanner);
					}
					else {
						System.out.println("No school found.");
					}
				}
				else {
					System.out.println("You need add school first.");
				}
				break;
			case 4:
				flag = false;
				break;
			}
			
		} while (flag);
		
	}
	
	
}
