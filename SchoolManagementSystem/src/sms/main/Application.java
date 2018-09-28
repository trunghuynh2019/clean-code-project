package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.function.Function;
import sms.model.School;
import sms.model.Teacher;
import sms.view.MainView;
import sms.view.SchoolView;
import sms.view.TeacherView;

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
				viewAllSchool(schoolList, scanner);
				break;
			}
			case 2: {
				addNewSchool(schoolList, scanner, function);
				break;
			}
			case 3: {
				// Choose a school and show options to do with its teacher list.
				manageTeachersList(schoolList, scanner, function);
				break;
			}
			case 4:
				programEnd = true;
				return;
			}
		} while (!programEnd);

	}

	public static void viewAllSchool(List<School> schoolList, Scanner scanner) {
		SchoolView.displayAllSchool(schoolList);
		MainView.loopAgain(scanner);
	}

	public static void addNewSchool(List<School> schoolList, Scanner scanner, Function function) {
		School school = new School();
		SchoolView.insertSchoolData(school, scanner);
		function.addSchool(schoolList, school);
		MainView.loopAgain(scanner);
	}

	public static void manageTeachersList(List<School> schoolList, Scanner scanner, Function function) {
		SchoolView.displayAllSchool(schoolList);
		MainView.enterSchoolName();
		School school = function.findSchoolByName(schoolList, scanner.nextLine());
		if (school == null) {
			SchoolView.displaySchoolNotFound();
			return;
		} else {
			MainView.displayManageTeacherMenu(school);
			List<Teacher> teacherList = school.getTeacherList();
			int _choice = scanner.nextInt();
			scanner.nextLine();
			switch (_choice) {
			case 1: {
				TeacherView.displayAllTeachersOfSchool(school);
				break;
			}
			case 2: {
				MainView.enterTeacherName();
				Teacher teacher = function.findTeacherByName(teacherList, scanner.nextLine());
				if (teacher == null) {
					TeacherView.displayTeacherNotFound();
				} else {
					TeacherView.displayTeacher(teacher);
				}
				break;
			}
			case 3: {
				MainView.enterTeacherAddress();
				Teacher teacher = function.findTeacherByAddress(teacherList, scanner.nextLine());
				if (teacher == null) {
					TeacherView.displayTeacherNotFound();
				} else {
					TeacherView.displayTeacher(teacher);
				}
				break;
			}
			case 4: {

			}
			}
		}
		MainView.loopAgain(scanner);
	}
}
