package sms.test;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.function.FileReading;
import sms.model.School;

public class FileReadingTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong.txt";
	private static final String TEACHER_FILE_NAME = "giaovien.txt";
	private static FileReading fileReading;
	private static List<School> schools = new ArrayList<School>();
	
	public FileReadingTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(FunctionTest.class, FileReadingTest.class);
	}
	
	public void testReadSchoolFileSuccessfully() {
		fileReading = new FileReading(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(fileReading.readSchoolFile(schools));
	}
	
	public void testReadSchoolFileNotFound() {
		fileReading = new FileReading("a.txt", TEACHER_FILE_NAME);
		assertFalse(fileReading.readSchoolFile(schools));
	}
	
	public void testReadTeacherFileSuccessful() {
		fileReading = new FileReading(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(fileReading.readTeacherFile(schools));
	}
	
	public void testReadTeacherFileNotFound() {
		fileReading = new FileReading(SCHOOL_FILE_NAME, "b.txt");
		assertFalse(fileReading.readTeacherFile(schools));
	}
	
	public void testAddSchoolToList() {
		School school1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000, "Vinh Long");
		School school2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500, "Quang Ngai");
		List<School> expected = asList(school1, school2);
		fileReading = new FileReading(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		schools = new ArrayList<School>();
		fileReading.readSchoolFile(schools);
		System.out.println("1---------");
		System.out.println(schools);
		System.out.println("2---------");
		System.out.println(expected);
		System.out.println("3---------");
		assertTrue(schools.equals(expected));
	}
}
