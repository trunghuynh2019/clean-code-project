package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.function.FileReading;
import sms.function.Function;
import sms.model.School;
import sms.model.Teacher;
import sms.view.MainView;
import sms.view.SchoolView;
import sms.view.TeacherView;

public class Application {

	public static void main(String[] args) {
		List<School> schools = new ArrayList<School>();
		Function function = new Function();
		boolean programEnd = false;
		Scanner scanner = new Scanner(System.in);

		do {
			MainView.displayMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 0: {
				insertDataByFileText(schools, scanner);
				break;
			}
			case 1: {
				viewAllSchool(schools, scanner);
				break;
			}
			case 2: {
				addNewSchool(schools, scanner, function);
				break;
			}
			case 3: {
				// Choose a school and show options to do with its teacher list.
				manageTeachersList(schools, scanner, function);
				break;
			}
			case 4:
				programEnd = true;
				return;
			}
		} while (!programEnd);

	}
	
	//
	public static void insertDataByFileText(List<School> schools, Scanner scanner) {
		MainView.enterSchoolFileName();
		String schoolFile = scanner.nextLine();
		MainView.enterTeacherFileName();
		String teacherFile = scanner.nextLine();
		FileReading fileReading = new FileReading(schoolFile, teacherFile);
		System.out.println(fileReading.getSchoolFileName());
		System.out.println(fileReading.getTeacherFileName());
		
		boolean readSuccessfully = fileReading.readSchoolFile(schools) && fileReading.readTeacherFile(schools);
		if (readSuccessfully) {
			MainView.readFileSuccessfully();
		} else {
			MainView.fileNotFound();
		}
		MainView.loopAgain(scanner);
	}
	//

	public static void viewAllSchool(List<School> schools, Scanner scanner) {
		SchoolView.displayAllSchool(schools);
		MainView.loopAgain(scanner);
	}

	public static void addNewSchool(List<School> schools, Scanner scanner, Function function) {
		School school = new School();
		SchoolView.insertSchoolData(school, scanner);
		function.addSchool(schools, school);
		MainView.loopAgain(scanner);
	}

	public static void manageTeachersList(List<School> schools, Scanner scanner, Function function) {
		SchoolView.displayAllSchool(schools);
		MainView.enterSchoolName();
		School school = function.findSchoolByName(schools, scanner.nextLine());
		if (school == null) {
			SchoolView.displaySchoolNotFound();
			return;
		} else {
			MainView.displayManageTeacherMenu(school);
			List<Teacher> teachers = school.getTeachers();
			int _choice = scanner.nextInt();
			scanner.nextLine();
			switch (_choice) {
			case 1: {
				TeacherView.displayAllTeachersOfSchool(school);
				break;
			}
			case 2: {
				MainView.enterTeacherName();
				Teacher teacher = function.findTeacherByName(teachers, scanner.nextLine());
				if (teacher == null) {
					TeacherView.displayTeacherNotFound();
				} else {
					TeacherView.displayTeacher(teacher);
				}
				break;
			}
			case 3: {
				MainView.enterTeacherId();
				Teacher teacher = function.findTeacherById(teachers, scanner.nextInt());
				scanner.nextLine();
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
