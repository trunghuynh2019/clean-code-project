package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.repository.impl.SchoolRepoImpl;
import sms.repository.impl.TeacherRepoImpl;
import sms.service.FileExportService;
import sms.service.FileImportService;
import sms.service.SchoolService;
import sms.service.TeacherService;
import sms.service.impl.FileExportServiceImpl;
import sms.service.impl.FileImportServiceImpl;
import sms.service.impl.SchoolServiceImpl;
import sms.service.impl.TeacherServiceImpl;
import sms.view.MainView;

public class Application {

	public static void main(String[] args) {
		SchoolService schoolService = new SchoolServiceImpl();
		TeacherService teacherService = new TeacherServiceImpl();
		FileImportService fileImportService = new FileImportServiceImpl();
		FileExportService fileExportService = new FileExportServiceImpl();

		List<School> schools = new ArrayList<School>();

		SchoolRepo schoolRepo = new SchoolRepoImpl();
		TeacherRepo teacherRepo = new TeacherRepoImpl();

		MainView view = new MainView();
		boolean programEnd = false;
		Scanner scanner = new Scanner(System.in);

		try {
			fileImportService.importByJsonFile(schools, scanner);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		do {
			view.displayMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				schoolService.viewAllSchools(schools, scanner);
				break;
			}
			case 2: {
				schoolService.addNewSchool(schools, scanner);
				break;
			}
			case 3: {
				teacherService.manageTeachers(schools, scanner, schoolRepo, teacherRepo);
				break;
			}
			case 4: {
				boolean successful = fileImportService.importByTextFile(schools, scanner);
				view.importFileResult(successful, scanner);
				break;
			}
			case 5: {
				boolean successful = fileExportService.exportToTextFile(schools, scanner);
				view.exportFileResult(successful, scanner);
				break;
			}
			case 6: {
				boolean successful = fileExportService.exportToExcelFile(schools, scanner);
				view.exportFileResult(successful, scanner);
				break;
			}
			case 7: {
				boolean successful = fileExportService.exportToPdfFile(schools, scanner);
				view.exportFileResult(successful, scanner);
				break;
			}
			case 8: {
				boolean successful = fileExportService.exportToHtmlFile(schools, scanner);
				view.exportFileResult(successful, scanner);
				break;
			}
			case 9: {
				programEnd = true;
				return;
			}
			}
			fileExportService.exportToJsonFile(schools, scanner);
		} while (!programEnd);

	}

}
