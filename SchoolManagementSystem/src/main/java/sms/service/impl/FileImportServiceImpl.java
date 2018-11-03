package sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sms.filereader.FileReader;
import sms.filereader.JsonReader;
import sms.filereader.TextReader;
import sms.model.School;
import sms.model.Teacher;
import sms.service.FileImportService;
import sms.view.MainView;

public class FileImportServiceImpl implements FileImportService {
	private static final String SCHOOL_FILE = "truong";
	private static final String TEACHER_FILE = "giaovien";
	private static final MainView VIEW = new MainView();

	@Override
	public boolean importByTextFile(List<School> schools, Scanner scanner) {
		VIEW.enterSchoolFileName();
		String schoolFile = scanner.nextLine();
		VIEW.enterTeacherFileName();
		String teacherFile = scanner.nextLine();
		FileReader textReader = new TextReader(schoolFile, teacherFile);
		// Confuse!!!
		List<Teacher> teachers = new ArrayList<>();

		boolean importSuccessfully = textReader.importSchoolFromFile(schools)
				&& textReader.importTeacherFromFile(schools, teachers);
		VIEW.importFileResult(importSuccessfully, scanner);
		return importSuccessfully;
	}

	@Override
	public boolean importByJsonFile(List<School> schools, Scanner scanner) {
		FileReader jsonReader = new JsonReader(SCHOOL_FILE + ".json", TEACHER_FILE + ".json");
		// Confuse!!!
		List<Teacher> teachers = new ArrayList<>();

		boolean importSuccessfully = jsonReader.importSchoolFromFile(schools)
				&& jsonReader.importTeacherFromFile(schools, teachers);
		VIEW.loopAgain(scanner);
		return importSuccessfully;
	}
}
