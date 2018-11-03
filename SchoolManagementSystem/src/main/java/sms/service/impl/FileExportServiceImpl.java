package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import sms.filewriter.ExcelWriter;
import sms.filewriter.FileWriter;
import sms.filewriter.HtmlWriter;
import sms.filewriter.JsonWriter;
import sms.filewriter.PdfWriter;
import sms.filewriter.TextWriter;
import sms.model.School;
import sms.service.FileExportService;
import sms.view.MainView;

public class FileExportServiceImpl implements FileExportService {
	private static final String SCHOOL_FILE = "truong";
	private static final String TEACHER_FILE = "giaovien";
	private static final MainView VIEW = new MainView();

	@Override
	public void exportToTextFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new TextWriter(SCHOOL_FILE + ".txt", TEACHER_FILE + ".txt");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToExcelFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new ExcelWriter(SCHOOL_FILE + ".xlsx", TEACHER_FILE + ".xlsx");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToPdfFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new PdfWriter(SCHOOL_FILE + ".pdf", TEACHER_FILE + ".pdf");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToHtmlFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new HtmlWriter(SCHOOL_FILE + ".html", TEACHER_FILE + ".html");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToJsonFile(List<School> schools, Scanner scanner) {
		FileWriter jsonWriter = new JsonWriter(SCHOOL_FILE + ".json", TEACHER_FILE + ".json");
		jsonWriter.exportSchoolsToFile(schools);
		jsonWriter.exportTeachersToFile(schools);
	}

}
