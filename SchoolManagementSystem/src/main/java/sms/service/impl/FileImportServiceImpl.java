package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import sms.filereader.FileReader;
import sms.filereader.TextReader;
import sms.model.School;
import sms.service.FileImportService;
import sms.view.MainView;

public class FileImportServiceImpl implements FileImportService{
	private static final MainView VIEW = new MainView();

	@Override
	public boolean importByTextFile(List<School> schools, Scanner scanner) {
		VIEW.enterSchoolFileName();
		String schoolFile = scanner.nextLine();
		VIEW.enterTeacherFileName();
		String teacherFile = scanner.nextLine();
		FileReader textReader = new TextReader(schoolFile, teacherFile);

		boolean importSuccessfully = textReader.importSchoolFromFile(schools)
				&& textReader.importTeacherFromFile(schools);
		VIEW.importFileResult(importSuccessfully, scanner);
		return importSuccessfully;
	}

	@Override
	public boolean importByJsonString(List<School> schools, String json) {
		for (int i =0;i<json.length();i++) {
			
		}
		return false;
	}
}
