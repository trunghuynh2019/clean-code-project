package sms.test;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.model.School;
import sms.util.TextUtil;

public class TextUtilTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong.txt";
	private static final String TEACHER_FILE_NAME = "giaovien.txt";
	private static TextUtil textUtil;
	private static List<School> schools = new ArrayList<School>();
	private static final School SCHOOL1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000,
			"Vinh Long");
	private static final School SCHOOL2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500,
			"Quang Ngai");
	private static final List<School> SCHOOLS = Arrays.asList(SCHOOL1, SCHOOL2);
	
	public TextUtilTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(TextUtilTest.class);
	}
	
	public void testReadSchoolFromTextFileSuccessfully() {
		textUtil = new TextUtil(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(textUtil.readSchoolFromTextFile(schools));
	}
	
	public void testReadSchoolFromTextFileNotFound() {
		textUtil = new TextUtil("a.txt", TEACHER_FILE_NAME);
		assertFalse(textUtil.readSchoolFromTextFile(schools));
	}
	
	public void testReadTeacherFromTextFileSuccessful() {
		textUtil = new TextUtil(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		assertTrue(textUtil.readTeacherTextFileFile(schools));
	}
	
	public void testReadTeacherFromTextFileNotFound() {
		textUtil = new TextUtil(SCHOOL_FILE_NAME, "b.txt");
		assertFalse(textUtil.readTeacherTextFileFile(schools));
	}
	
	public void testAddSchoolToList() {
		School school1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000, "Vinh Long");
		School school2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500, "Quang Ngai");
		List<School> expected = asList(school1, school2);
		textUtil = new TextUtil(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		schools = new ArrayList<School>();
		textUtil.readSchoolFromTextFile(schools);
		assertTrue(schools.equals(expected));
	}
	
	public void testWriteSchoolToTextFileSuccessfully() {
		textUtil = new TextUtil(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = textUtil.writeSchoolToTextFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteTeacherToTextFileSuccessfully() {
		textUtil = new TextUtil(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = textUtil.writeTeacherToTextFile(SCHOOLS);
		assertTrue(result);
	}
}
