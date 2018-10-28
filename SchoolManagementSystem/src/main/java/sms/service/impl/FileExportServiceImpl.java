package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import sms.export.ExcelExport;
import sms.export.FileExport;
import sms.export.HtmlExport;
import sms.export.PdfExport;
import sms.export.TextExport;
import sms.model.School;
import sms.service.FileExportService;
import sms.view.MainView;

public class FileExportServiceImpl implements FileExportService {
	private static final MainView VIEW = new MainView();

	@Override
	public void exportToTextFile(List<School> schools, Scanner scanner) {
		FileExport fileExport = new TextExport("truong.txt", "giaovien.txt");
		boolean exportSuccessfully = fileExport.exportSchoolsToFile(schools)
				&& fileExport.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToExcelFile(List<School> schools, Scanner scanner) {
		FileExport fileExport = new ExcelExport("truong.xlsx", "giaovien.xlsx");
		boolean exportSuccessfully = fileExport.exportSchoolsToFile(schools)
				&& fileExport.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToPdfFile(List<School> schools, Scanner scanner) {
		FileExport fileExport = new PdfExport("truong.pdf", "giaovien.pdf");
		boolean exportSuccessfully = fileExport.exportSchoolsToFile(schools)
				&& fileExport.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

	@Override
	public void exportToHtmlFile(List<School> schools, Scanner scanner) {
		FileExport fileExport = new HtmlExport("truong.html", "giaovien.html");
		boolean exportSuccessfully = fileExport.exportSchoolsToFile(schools)
				&& fileExport.exportTeachersToFile(schools);
		VIEW.exportFileResult(exportSuccessfully, scanner);
	}

}
