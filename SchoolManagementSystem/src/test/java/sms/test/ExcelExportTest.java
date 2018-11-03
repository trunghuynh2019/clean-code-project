package sms.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.filewriter.ExcelWriter;
import sms.model.School;

public class ExcelExportTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong.xlsx";
	private static final String TEACHER_FILE_NAME = "giaovien.xlsx";

	private static final School SCHOOL1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000,
			"Vinh Long");
	private static final School SCHOOL2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500,
			"Quang Ngai");
	private static final List<School> SCHOOLS = Arrays.asList(SCHOOL1, SCHOOL2);

	private static ExcelWriter export;

	public ExcelExportTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ExcelExportTest.class);
	}
	
	public void testWriteSchoolToExcelFileSuccessfully() {
		export = new ExcelWriter(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = export.exportSchoolsToFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteTeacherToExcelFileSuccessfully() {
		export = new ExcelWriter(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = export.exportTeachersToFile(SCHOOLS);
		assertTrue(result);
	}
}
