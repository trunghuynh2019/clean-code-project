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

public class FileImportServiceImpl implements FileImportService {
	private static final String SCHOOL_FILE = "truong";
	private static final String TEACHER_FILE = "giaovien";

	@Override
	public boolean importByTextFile(List<School> schools, Scanner scanner) {
		// VIEW.enterSchoolFileName();
		System.out.println("First, put the files into folder src/main/resources/file");
		System.out.println("Second, enter the name of file. Ex: truong.txt, giaovien.txt");
		System.out.print("Name of school file: ");
		String schoolFile = scanner.nextLine();
		System.out.print("Name of teacher file: ");
		String teacherFile = scanner.nextLine();
		FileReader textReader = new TextReader(schoolFile, teacherFile);
		List<Teacher> teachers = new ArrayList<>();

		return textReader.importSchoolFromFile(schools) && textReader.importTeacherFromFile(schools, teachers);
	}

	@Override
	public boolean importByJsonFile(List<School> schools, Scanner scanner) {
		FileReader jsonReader = new JsonReader(SCHOOL_FILE + ".json", TEACHER_FILE + ".json");
		List<Teacher> teachers = new ArrayList<>();

		return jsonReader.importSchoolFromFile(schools) && jsonReader.importTeacherFromFile(schools, teachers);
	}
}
