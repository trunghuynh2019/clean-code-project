package sms.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.function.FileWriting;
import sms.functionInterface.FileWritingITF;
import sms.model.School;

public class FileWritingTest extends TestCase {
	// File export from TestCase is put in export/text/test and export/excel/test
	private static final String SCHOOL_FILE_NAME_TXT = "test/truong.txt";
	private static final String TEACHER_FILE_NAME_TXT = "test/giaovien.txt";
	private static final String SCHOOL_FILE_NAME_XLSX = "test/truong.xlsx";
	private static final String TEACHER_FILE_NAME_XLSX = "test/giaovien.xlsx";

	private static final School SCHOOL1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000,
			"Vinh Long");
	private static final School SCHOOL2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500,
			"Quang Ngai");
	private static final List<School> SCHOOLS = Arrays.asList(SCHOOL1, SCHOOL2);

	private static FileWritingITF fileWriting;

	public FileWritingTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(FileWritingTest.class);
	}

	public void testWriteSchoolToTextFileSuccessfully() {
		fileWriting = new FileWriting(SCHOOL_FILE_NAME_TXT, TEACHER_FILE_NAME_TXT);
		boolean result = fileWriting.writeSchoolToTextFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteTeacherToTextFileSuccessfully() {
		fileWriting = new FileWriting(SCHOOL_FILE_NAME_TXT, TEACHER_FILE_NAME_TXT);
		boolean result = fileWriting.writeTeacherToTextFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteSchoolToExcelFileSuccessfully() {
		fileWriting = new FileWriting(SCHOOL_FILE_NAME_XLSX, TEACHER_FILE_NAME_XLSX);
		boolean result = fileWriting.writeSchoolToExcelFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteTeacherToExcelFileSuccessfully() {
		fileWriting = new FileWriting(SCHOOL_FILE_NAME_XLSX, TEACHER_FILE_NAME_XLSX);
		boolean result = fileWriting.writeTeacherToExcelFile(SCHOOLS);
		assertTrue(result);
	}
}
