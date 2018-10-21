package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import sms.function.FileReading;
import sms.function.FileWriting;
import sms.functionInterface.FileReadingITF;
import sms.functionInterface.FileWritingITF;
import sms.model.School;
import sms.model.Teacher;
import sms.repo.Repository;
import sms.repoInterface.RepositoryITF;
import sms.view.MainView;
import sms.view.SchoolView;
import sms.view.TeacherView;

public class Application {

	public static void main(String[] args) {
		List<School> schools = new ArrayList<School>();
		RepositoryITF repo = new Repository();
		boolean programEnd = false;
		Scanner scanner = new Scanner(System.in);

		do {
			MainView.displayMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				viewAllSchool(schools, scanner);
				break;
			}
			case 2: {
				addNewSchool(schools, scanner, repo);
				break;
			}
			case 3: {
				// Choose a school and show options to do with its teacher list.
				manageTeachersList(schools, scanner, repo);
				break;
			}
			case 4: {
				insertDataByFileText(schools, scanner);
				break;
			}
			case 5: {
				writeDataToTextFile(schools, scanner);
				break;
			}
			case 6: {
				writeDataToExcelFile(schools, scanner);
				break;
			}
			case 7:
				programEnd = true;
				return;
			}
		} while (!programEnd);

	}

	public static void viewAllSchool(List<School> schools, Scanner scanner) {
		SchoolView.displayAllSchool(schools);
		MainView.loopAgain(scanner);
	}

	public static void addNewSchool(List<School> schools, Scanner scanner, RepositoryITF repo) {
		School school = new School();
		SchoolView.insertSchoolData(school, scanner);
		schools.add(school);
		MainView.loopAgain(scanner);
	}

	public static void manageTeachersList(List<School> schools, Scanner scanner, RepositoryITF repo) {
		SchoolView.displayAllSchool(schools);
		MainView.enterSchoolId();
		Optional<School> opSchool = repo.findSchoolById(schools, scanner.nextLine());
		if (!opSchool.isPresent()) {
			SchoolView.displaySchoolNotFound();
			return;
		} else {
			do {
				School school = opSchool.get();
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
					Optional<Teacher> opTeacher = repo.findTeacherByName(teachers, scanner.nextLine());
					if (!opTeacher.isPresent()) {
						TeacherView.displayTeacherNotFound();
					} else {
						TeacherView.displayTeacher(opTeacher.get());
					}
					break;
				}
				case 3: {
					MainView.enterTeacherId();
					Optional<Teacher> opTeacher = repo.findTeacherById(teachers, scanner.nextInt());
					scanner.nextLine();
					if (!opTeacher.isPresent()) {
						TeacherView.displayTeacherNotFound();
					} else {
						TeacherView.displayTeacher(opTeacher.get());
					}
					break;
				}
				case 4: {
					MainView.loopAgain(scanner);
					return;
				}
				}
				MainView.loopAgain(scanner);
			} while (true);
		}
	}

	public static void insertDataByFileText(List<School> schools, Scanner scanner) {
		MainView.enterSchoolFileName();
		String schoolFile = scanner.nextLine();
		MainView.enterTeacherFileName();
		String teacherFile = scanner.nextLine();
		FileReadingITF fileReading = new FileReading(schoolFile, teacherFile);

		boolean readSuccessfully = fileReading.readSchoolFile(schools) && fileReading.readTeacherFile(schools);
		if (readSuccessfully) {
			MainView.readFileSuccessfully();
		} else {
			MainView.fileNotFound();
		}
		MainView.loopAgain(scanner);
	}

	public static void writeDataToTextFile(List<School> schools, Scanner scanner) {
		FileWritingITF fileWriting = new FileWriting("truong.txt", "giaovien.txt");

		boolean writeSuccessfully = fileWriting.writeSchoolToTextFile(schools)
				&& fileWriting.writeTeacherToTextFile(schools);
		if (writeSuccessfully) {
			MainView.writeFileSuccessfully();
		} else {
			MainView.writeFileFailed();
		}
		MainView.loopAgain(scanner);
	}

	public static void writeDataToExcelFile(List<School> schools, Scanner scanner) {
		FileWritingITF fileWriting = new FileWriting("truong.xlsx", "giaovien.xlsx");

		boolean writeSuccessfully = fileWriting.writeSchoolToExcelFile(schools)
				&& fileWriting.writeTeacherToExcelFile(schools);
		if (writeSuccessfully) {
			MainView.writeFileSuccessfully();
		} else {
			MainView.writeFileFailed();
		}
		MainView.loopAgain(scanner);
	}
}
