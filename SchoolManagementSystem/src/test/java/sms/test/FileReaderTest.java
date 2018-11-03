package sms.test;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.filereader.FileReader;
import sms.filereader.JsonReader;
import sms.filereader.TextReader;
import sms.model.School;
import sms.model.Teacher;

public class FileReaderTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong";
	private static final String TEACHER_FILE_NAME = "giaovien";

	private FileReader reader;
	private List<School> schools = new ArrayList<>();
	private List<Teacher> teachers = new ArrayList<>();

	public FileReaderTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(FileReaderTest.class);
	}

	public void testTextReaderForSchoolSuccessfully() {
		reader = new TextReader(SCHOOL_FILE_NAME + ".txt", TEACHER_FILE_NAME + ".txt");
		assertTrue(reader.importSchoolFromFile(schools));
	}

	public void testTextReaderForSchoolNotFound() {
		reader = new TextReader("a.txt", TEACHER_FILE_NAME + ".txt");
		assertFalse(reader.importSchoolFromFile(schools));
	}

	public void testTextReaderForTeacherSuccessfully() {
		reader = new TextReader(SCHOOL_FILE_NAME + ".txt", TEACHER_FILE_NAME + ".txt");
		assertTrue(reader.importTeacherFromFile(schools, teachers));
	}

	public void testTextReaderForTeacherNotFound() {
		reader = new TextReader(SCHOOL_FILE_NAME + ".txt", "b.txt");
		assertFalse(reader.importTeacherFromFile(schools, teachers));
	}

	public void testAddSchoolToList() {
		School school1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000, "Vinh Long");
		School school2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500, "Quang Ngai");
		List<School> expected = asList(school1, school2);
		reader = new TextReader(SCHOOL_FILE_NAME + ".txt", TEACHER_FILE_NAME + ".txt");
		schools = new ArrayList<School>();
		reader.importSchoolFromFile(schools);
		assertTrue(schools.equals(expected));
	}

	public void testJsonReaderForSchoolSuccessfully() {
		reader = new JsonReader(SCHOOL_FILE_NAME + ".json", TEACHER_FILE_NAME + ".json");
		reader.importSchoolFromFile(schools);
		assertEquals(schools.get(0).getId(), "nbk-vl");
		assertEquals(schools.get(1).getId(), "nbk-qn");
	}

	public void testJsonReaderForTeacherSuccessfully() {
		reader = new JsonReader(SCHOOL_FILE_NAME + ".json", TEACHER_FILE_NAME + ".json");
		reader.importTeacherFromFile(schools, teachers);
		assertEquals(teachers.get(0).getId(), "337829999");
		assertEquals(teachers.get(1).getId(), "334442222");
	}
}
