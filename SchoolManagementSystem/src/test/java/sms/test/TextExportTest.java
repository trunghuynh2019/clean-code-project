package sms.test;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.file.JsonExport;
import sms.file.TextExport;
import sms.model.School;

public class TextExportTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong.txt";
	private static final String TEACHER_FILE_NAME = "giaovien.txt";
	private static TextExport export;
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
		export = new TextExport(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(export.importSchoolFromTextFile(schools));
	}
	
	public void testReadSchoolFromTextFileNotFound() {
		export = new TextExport("a.txt", TEACHER_FILE_NAME);
		assertFalse(export.importSchoolFromTextFile(schools));
	}
	
	public void testReadTeacherFromTextFileSuccessful() {
		export = new TextExport(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(export.importTeacherFromTextFile(schools));
	}
	
	public void testReadTeacherFromTextFileNotFound() {
		export = new TextExport(SCHOOL_FILE_NAME, "b.txt");
		assertFalse(export.importTeacherFromTextFile(schools));
	}
	
	public void testAddSchoolToList() {
		School school1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000, "Vinh Long");
		School school2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500, "Quang Ngai");
		List<School> expected = asList(school1, school2);
		export = new TextExport(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		schools = new ArrayList<School>();
		export.importSchoolFromTextFile(schools);
		assertTrue(schools.equals(expected));
	}
	
	public void testWriteSchoolToTextFileSuccessfully() {
		export = new TextExport(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = export.exportSchoolsToFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteTeacherToTextFileSuccessfully() {
		export = new TextExport(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = export.exportTeachersToFile(SCHOOLS);
		assertTrue(result);
	}
}
