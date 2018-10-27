package sms.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.model.School;
import sms.util.ExcelUtil;

public class ExcelUtilTest extends TestCase {
	private static final String SCHOOL_FILE_NAME = "truong.xlsx";
	private static final String TEACHER_FILE_NAME = "giaovien.xlsx";

	private static final School SCHOOL1 = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2000,
			"Vinh Long");
	private static final School SCHOOL2 = new School("nbk-qn", "Truong trung hoc Chuyen Nguyen Binh Khiem", 2500,
			"Quang Ngai");
	private static final List<School> SCHOOLS = Arrays.asList(SCHOOL1, SCHOOL2);

	private static ExcelUtil excelUtil;

	public ExcelUtilTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ExcelUtilTest.class);
	}
	
	public void testWriteSchoolToExcelFileSuccessfully() {
		excelUtil = new ExcelUtil(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = excelUtil.writeSchoolToExcelFile(SCHOOLS);
		assertTrue(result);
	}
	
	public void testWriteTeacherToExcelFileSuccessfully() {
		excelUtil = new ExcelUtil(SCHOOL_FILE_NAME, TEACHER_FILE_NAME);
		boolean result = excelUtil.writeTeacherToExcelFile(SCHOOLS);
		assertTrue(result);
	}
}
