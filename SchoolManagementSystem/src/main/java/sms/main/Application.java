package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.filewriter.JsonWriter;
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
		List<School> schools = new ArrayList<School>();
		SchoolRepo schoolRepo = new SchoolRepoImpl();
		TeacherRepo teacherRepo = new TeacherRepoImpl();
		MainView view = new MainView();
		boolean programEnd = false;
		Scanner scanner = new Scanner(System.in);

		do {
			view.displayMainMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1: {
				SchoolService service = new SchoolServiceImpl();
				service.viewAllSchools(schools, scanner);
				break;
			}
			case 2: {
				SchoolService service = new SchoolServiceImpl();
				service.addNewSchool(schools, scanner);
				break;
			}
			case 3: {
				TeacherService service = new TeacherServiceImpl();
				service.manageTeachers(schools, scanner, schoolRepo, teacherRepo);
				break;
			}
			case 4: {
				FileImportService service = new FileImportServiceImpl();
				service.importByTextFile(schools, scanner);
				System.out.println(new JsonWriter().exportSchoolToJson(schools));
				break;
			}
			case 5: {
				FileExportService service = new FileExportServiceImpl();
				service.exportToTextFile(schools, scanner);
				break;
			}
			case 6: {
				FileExportService service = new FileExportServiceImpl();
				service.exportToExcelFile(schools, scanner);
				break;
			}
			case 7: {
				FileExportService service = new FileExportServiceImpl();
				service.exportToPdfFile(schools, scanner);
				break;
			}
			case 8: {
				FileExportService service = new FileExportServiceImpl();
				service.exportToHtmlFile(schools, scanner);
				break;
			}
			case 9: {
				programEnd = true;
				return;
			}
			}
		} while (!programEnd);

	}

	
}
