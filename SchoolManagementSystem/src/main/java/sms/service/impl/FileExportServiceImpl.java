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

public class FileExportServiceImpl implements FileExportService {
	private static final String SCHOOL_FILE = "truong";
	private static final String TEACHER_FILE = "giaovien";
	// private static final MainView VIEW = new MainView();

	@Override
	public boolean exportToTextFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new TextWriter(SCHOOL_FILE + ".txt", TEACHER_FILE + ".txt");
		return fileWriter.exportSchoolsToFile(schools) && fileWriter.exportTeachersToFile(schools);
		// VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public boolean exportToExcelFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new ExcelWriter(SCHOOL_FILE + ".xlsx", TEACHER_FILE + ".xlsx");
		return fileWriter.exportSchoolsToFile(schools) && fileWriter.exportTeachersToFile(schools);
		// VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public boolean exportToPdfFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new PdfWriter(SCHOOL_FILE + ".pdf", TEACHER_FILE + ".pdf");
		return fileWriter.exportSchoolsToFile(schools) && fileWriter.exportTeachersToFile(schools);
		// VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public boolean exportToHtmlFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new HtmlWriter(SCHOOL_FILE + ".html", TEACHER_FILE + ".html");
		return fileWriter.exportSchoolsToFile(schools) && fileWriter.exportTeachersToFile(schools);
		// VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public boolean exportToJsonFile(List<School> schools, Scanner scanner) {
		FileWriter jsonWriter = new JsonWriter(SCHOOL_FILE + ".json", TEACHER_FILE + ".json");
		return jsonWriter.exportSchoolsToFile(schools) && jsonWriter.exportTeachersToFile(schools);
	}

}
