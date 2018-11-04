package sms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.service.FileExportService;
import sms.service.FileImportService;
import sms.service.impl.FileExportServiceImpl;
import sms.service.impl.FileImportServiceImpl;
import sms.view.MainView;

public class Application {

	public static void main(String[] args) {
		FileImportService fileImportService = new FileImportServiceImpl();
		FileExportService fileExportService = new FileExportServiceImpl();

		List<School> schools = new ArrayList<School>();

		MainView view = new MainView();
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
				view.viewAllSchools(schools, scanner);
				break;
			}
			case 2: {
				view.addNewSchool(schools, scanner);
				break;
			}
			case 3: {
				view.manageTeachers(schools, scanner);
				break;
			}
			case 4: {
				view.importByTextFile(schools, scanner);
				break;
			}
			case 5: {
				view.exportToTextFile(schools, scanner);
				break;
			}
			case 6: {
				view.exportToExcelFile(schools, scanner);
				break;
			}
			case 7: {
				view.exportToPdfFile(schools, scanner);
				break;
			}
			case 8: {
				view.exportToHtmlFile(schools, scanner);
				break;
			}
			case 9: {
				return;
			}
			}
			fileExportService.exportToJsonFile(schools, scanner);
		} while (true);

	}

}
