package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

import sms.filewriter.ExcelWriter;
import sms.filewriter.FileWriter;
import sms.filewriter.HtmlWriter;
import sms.filewriter.PdfWriter;
import sms.filewriter.TextWriter;
import sms.model.School;
import sms.service.FileExportService;
import sms.view.MainView;

public class FileExportServiceImpl implements FileExportService {
	private static final MainView VIEW = new MainView();

	@Override
	public void exportToTextFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new TextWriter("truong.txt", "giaovien.txt");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToExcelFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new ExcelWriter("truong.xlsx", "giaovien.xlsx");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToPdfFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new PdfWriter("truong.pdf", "giaovien.pdf");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToHtmlFile(List<School> schools, Scanner scanner) {
		FileWriter fileWriter = new HtmlWriter("truong.html", "giaovien.html");
		boolean exportSuccessfully = fileWriter.exportSchoolsToFile(schools)
				&& fileWriter.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public String exportToJsonString(List<School> schools) {
		return new Gson().toJson(schools);
	}

}
