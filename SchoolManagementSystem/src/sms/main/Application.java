package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.function.Function;
import sms.model.School;
import sms.view.MainView;
import sms.view.SchoolView;

public class Application {

	public static void main(String[] args) {
		List<School> schoolList = new ArrayList<>();
		Function function = new Function();
		boolean programEnd = false;
		Scanner scanner = new Scanner(System.in);

		do {
			MainView.displayMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				SchoolView.displayAllSchool(schoolList);
				MainView.loopAgain(scanner);
				break;
			}
			case 2: {
				/*
				 * Add a new school to school list.
				 */
				School school = new School();
				SchoolView.insertSchoolData(school, scanner);
				function.addSchool(schoolList, school);
				MainView.loopAgain(scanner);
				break;
			}
			case 3: {
				/*
				 * Choose a school and show options to do with its teacher list
				 */
				MainView.loopAgain(scanner);
				break;
			}
			case 4:
				programEnd = true;
				return;
			}
		} while (!programEnd);

	}

}
