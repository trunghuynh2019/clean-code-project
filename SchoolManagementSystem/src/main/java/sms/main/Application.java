package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.repository.impl.SchoolRepoImpl;
import sms.repository.impl.TeacherRepoImpl;
import sms.view.MainView;

public class Application {

	public static void main(String[] args) {
		List<School> schools = new ArrayList<School>();
		SchoolRepo schoolRepo = new SchoolRepoImpl();
		TeacherRepo teacherRepo = new TeacherRepoImpl();
		MenuFunction function = new MenuFunctionImpl();
		boolean programEnd = false;
		Scanner scanner = new Scanner(System.in);

		do {
			MainView.displayMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				function.viewAllSchool(schools, scanner);
				break;
			}
			case 2: {
				function.addNewSchool(schools, scanner);
				break;
			}
			case 3: {
				// Choose a school and show options to do with its teacher list.
				function.manageTeachersList(schools, scanner, schoolRepo, teacherRepo);
				break;
			}
			case 4: {
				function.insertDataByFileText(schools, scanner);
				break;
			}
			case 5: {
				function.writeDataToTextFile(schools, scanner);
				break;
			}
			case 6: {
				function.writeDataToExcelFile(schools, scanner);
				break;
			}
			case 7: {
				function.writeDataToPdfFile(schools, scanner);
				break;
			}
			case 8:
				programEnd = true;
				return;
			}
		} while (!programEnd);

	}

	
}
