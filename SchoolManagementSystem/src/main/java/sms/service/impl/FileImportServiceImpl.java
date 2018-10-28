package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import sms.export.TextExport;
import sms.model.School;
import sms.service.FileImportService;
import sms.view.MainView;

public class FileImportServiceImpl implements FileImportService{
	private static final MainView VIEW = new MainView();

	@Override
	public void importByTextFile(List<School> schools, Scanner scanner) {
		VIEW.enterSchoolFileName();
		String schoolFile = scanner.nextLine();
		VIEW.enterTeacherFileName();
		String teacherFile = scanner.nextLine();
		TextExport fileReading = new TextExport(schoolFile, teacherFile);

		boolean importSuccessfully = fileReading.readSchoolFromTextFile(schools)
				&& fileReading.readTeacherTextFileFile(schools);
		VIEW.importFileResult(importSuccessfully, scanner);
	}

}
