package sms.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.filewriter.ExcelWriter;
import sms.filewriter.FileWriter;
import sms.filewriter.TextWriter;
import sms.model.School;

public class FileWriterTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong";
	private static final String TEACHER_FILE_NAME = "giaovien";

	private static final School SCHOOL1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000,
			"Vinh Long");
	private static final School SCHOOL2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500,
			"Quang Ngai");
	private static final List<School> SCHOOLS = Arrays.asList(SCHOOL1, SCHOOL2);

	private FileWriter writer;

	public FileWriterTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(FileWriterTest.class);
	}

	public void testTextWriterForSchoolSuccessfully() {
		writer = new TextWriter(SCHOOL_FILE_NAME + ".txt", TEACHER_FILE_NAME + ".txt");
		boolean result = writer.exportSchoolsToFile(SCHOOLS);
		assertTrue(result);
	}

	public void testTextWriterForTeacherSuccessfully() {
		writer = new TextWriter(SCHOOL_FILE_NAME + ".txt", TEACHER_FILE_NAME + ".txt");
		boolean result = writer.exportTeachersToFile(SCHOOLS);
		assertTrue(result);
	}

	public void testExcelWriterForSchoolSuccessfully() {
		writer = new ExcelWriter(SCHOOL_FILE_NAME + ".xlsx", TEACHER_FILE_NAME + ".xlsx");
		boolean result = writer.exportSchoolsToFile(SCHOOLS);
		assertTrue(result);
	}

	public void testExcelWriterForTeacherSuccessfully() {
		writer = new ExcelWriter(SCHOOL_FILE_NAME + ".xlsx", TEACHER_FILE_NAME + ".xlsx");
		boolean result = writer.exportTeachersToFile(SCHOOLS);
		assertTrue(result);
	}
}
