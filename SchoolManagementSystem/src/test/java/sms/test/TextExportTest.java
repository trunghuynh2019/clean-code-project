package sms.test;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.filereader.FileReader;
import sms.filereader.TextReader;
import sms.filewriter.FileWriter;
import sms.filewriter.TextWriter;
import sms.model.School;

public class TextExportTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong.txt";
	private static final String TEACHER_FILE_NAME = "giaovien.txt";
	private static FileReader importFile;
	private static FileWriter exportFile;
	private static List<School> schools = new ArrayList<School>();
	private static final School SCHOOL1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000,
			"Vinh Long");
	private static final School SCHOOL2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500,
			"Quang Ngai");
	private static final List<School> SCHOOLS = Arrays.asList(SCHOOL1, SCHOOL2);
	
	public TextExportTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(TextExportTest.class);
	}
	
	public void testReadSchoolFromTextFileSuccessfully() {
		importFile = new TextReader(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(importFile.importSchoolFromFile(schools));
	}
	
	public void testReadSchoolFromTextFileNotFound() {
		importFile = new TextReader("a.txt", TEACHER_FILE_NAME);
		assertFalse(importFile.importSchoolFromFile(schools));
	}
	
	public void testReadTeacherFromTextFileSuccessful() {
		importFile = new TextReader(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(importFile.importTeacherFromFile(schools));
	}
	
	public void testReadTeacherFromTextFileNotFound() {
		importFile = new TextReader(SCHOOL_FILE_NAME, "b.txt");
		assertFalse(importFile.importTeacherFromFile(schools));
	}
	
	public void testAddSchoolToList() {
		School school1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000, "Vinh Long");
		School school2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500, "Quang Ngai");
		List<School> expected = asList(school1, school2);
		importFile = new TextReader(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		schools = new ArrayList<School>();
		importFile.importSchoolFromFile(schools);
		assertTrue(schools.equals(expected));
	}
	
	public void testWriteSchoolToTextFileSuccessfully() {
		exportFile = new TextWriter(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = exportFile.exportSchoolsToFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteTeacherToTextFileSuccessfully() {
		exportFile = new TextWriter(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = exportFile.exportTeachersToFile(SCHOOLS);
		assertTrue(result);
	}
}
