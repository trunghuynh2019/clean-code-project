package com.cleancode.education;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cleancode.education.controller.SchoolController;
import com.cleancode.education.controller.impl.SchoolControllerImpl;
import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.repository.SchoolRepository;
import com.cleancode.education.repository.impl.SchoolRepositoryImpl;
import com.cleancode.education.service.SchoolService;
import com.cleancode.education.service.impl.SchoolServiceImpl;
import com.cleancode.education.util.ExcelUtil;
import com.cleancode.education.views.PrinterSupport;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	School school = new School();
    	school.setName("NBK");
        assertEquals("NBK", school.getName());
    }
    
    public void testAddTeacherToSchoolShouldIncreaseTheNumberOfTeacher() {
    	School school = new School();
    	assertEquals(0, school.getNumberOfTeacher());
    	Teacher teacher = new Teacher();
    	school.signContractWith(teacher);
    	assertEquals(1, school.getNumberOfTeacher());
    }
    
    public void testPrintSchoolInfo() {
    	PrinterSupport print = new PrinterSupport();
    	
    	// Name should have start in console
    	assertEquals("*** Truong trung hoc Chuyen Nguyen Binh Khiem ***", print.nameWithThreeStarAround("Truong trung hoc Chuyen Nguyen Binh Khiem"));
    	// Other fields should be as format
    	assertEquals("Id: nbk-vl", print.idFormat("nbk-vl"));
    	assertEquals("Address: Vinh Long", print.addressFormat("Vinh Long"));
    	assertEquals("Number of students: 2000", print.numberOf("students", 2000));
    	assertEquals("Number of teachers: 200", print.numberOf("teachers", 200));
    	
    }
    
    public void testPrintTeacherInfo() {
    	PrinterSupport print = new PrinterSupport();
    	
    	assertEquals("*** Le Cong Huy ***", print.nameWithThreeStarAround("Le Cong Huy"));
    	assertEquals("Id: 123456789", print.idFormat("123456789"));
    	assertEquals("Working School's ID: nbk-vl", print.workingSchool("nbk-vl"));
    }
    
    
    public void testAddSchoolWithEmptyList() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	schoolController.addSchool(new School());
    	
    	assertEquals(1, schoolService.getAllSchool().size());
    }
    
    public void testAddSchoolWithNoneEmptyList() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	schoolController.addSchool(new School("nbk-vl","NBK","VL",null,50));
    	
    	schoolController.addSchool(new School());
    	
    	assertEquals(2, schoolService.getAllSchool().size());
    }
    
    public void testAddExistedSchoolShouldNotIncreaseTheNumberOfSchool() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	schoolController.addSchool(new School("nbk-vl","NBK","VL",null,50));
    	schoolController.addSchool(new School("nbk-vl","BK","HCM",null,50));
    	
    	assertEquals(1, schoolService.getAllSchool().size());
    }
    
    public void testAddExistedSchoolShouldUpdateSchoolInfo() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	schoolController.addSchool(new School("nbk-vl","NBK","VL",null,50));
    	
    	
    	schoolController.addSchool(new School("nbk-vl","BK","HCM",null,40));
    	
    	assertEquals("BK", schoolService.getSchoolById("nbk-vl").getName());
    	assertEquals("HCM", schoolService.getSchoolById("nbk-vl").getAddress());
    	assertEquals(40, schoolService.getSchoolById("nbk-vl").getNumberOfStudent());
    }
    
    public void testSignContractWithTeacher() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	schoolController.addSchool(new School("nbk-vl","NBK","VL",new ArrayList<Teacher>(),50));
    	schoolController.signContractWithTeacher(new Teacher("285656600","Huy","nbk-vl"));
    	
    	assertEquals(1, schoolService.getSchoolById("nbk-vl").getNumberOfTeacher());
    }
    
    public void testSignContractWithTeacherHaveSchoolIdNotExistedInSystem() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	
    	schoolController.addSchool(new School("nbk-vl","NBK","VL",new ArrayList<Teacher>(),50));
    	schoolController.signContractWithTeacher(new Teacher("285656600","Huy","nbk-vl"));
    	schoolController.signContractWithTeacher(new Teacher("285656644","Tien","nbk-qn"));
    	
    	assertEquals(1, schoolService.getSchoolById("nbk-vl").getNumberOfTeacher());
    }
    
    public void testAddSchoolFromFile(){
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	
    	
    	String fileName = "truong.txt";
    	schoolController.addSchoolsFrom(fileName);
    	
    	assertEquals(2, schoolService.getAllSchool().size());
    }
    
    public void testAddSchoolFromFileShouldNotIncreaseTheNumberOfSchoolIfSchoolExisted() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	
    	schoolController.addSchool(new School("nbk-vl","","",null,50));
    	
    	String fileName = "truong.txt";
    	schoolController.addSchoolsFrom(fileName);
    	
    	assertEquals(2, schoolService.getAllSchool().size());
    }
    
    public void testAddSchoolFromFileShouldUpdateSchoolInfoIfSchoolExisted() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	schoolController.addSchool(new School("nbk-vl","","",null,50));
    	
    	String fileName = "truong.txt";
    	schoolController.addSchoolsFrom(fileName);
    	
    	assertEquals("Truong trung hoc Chuyen Nguyen Binh Khiem", schoolService.getSchoolById("nbk-vl").getName());
    	assertEquals("Vinh Long", schoolService.getSchoolById("nbk-vl").getAddress());
    	assertEquals(2000, schoolService.getSchoolById("nbk-vl").getNumberOfStudent());
    }
    
    public void testSignContractWithTeacherFromFile() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	
    	String schoolFile = "truong.txt";
    	String teacherFile = "giaovien.txt";
    	
    	schoolController.addSchoolsFrom(schoolFile);
    	schoolController.signContractWithTeacherFrom(teacherFile);
    	
    	assertEquals(1, schoolService.getSchoolById("nbk-vl").getNumberOfTeacher());
    	assertEquals(1, schoolService.getSchoolById("nbk-qn").getNumberOfTeacher());
    }
    
    public void testAddTeacherFromFileShouldNotIncreaseTheNumberOfTeacherIfTeacherExisted() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	
    	String schoolFile = "truong.txt";
    	schoolController.addSchoolsFrom(schoolFile);
    	
    	List<Teacher> teachers = new ArrayList<>();
    	teachers.add(new Teacher("337829999","","nbk-vl"));
    	schoolService.getSchoolById("nbk-vl").setTeachers(teachers);
    	
    	String teacherFile = "giaovien.txt";
    	schoolController.signContractWithTeacherFrom(teacherFile);
    	
    	assertEquals(1, schoolService.getSchoolById("nbk-vl").getNumberOfTeacher());
    }
    
    public void testAddTeacherFromFileShouldUpdateTeacherInfoIfTeacherExisted() {
    	SchoolRepository schoolRepository = new SchoolRepositoryImpl();
    	SchoolService schoolService = new SchoolServiceImpl(schoolRepository);
    	SchoolController schoolController = new SchoolControllerImpl(schoolService);
    	
    	String schoolFile = "truong.txt";
    	schoolController.addSchoolsFrom(schoolFile);
    	List<Teacher> teachers = new ArrayList<>();
    	teachers.add(new Teacher("337829999","","nbk-vl"));
    	schoolService.getSchoolById("nbk-vl").setTeachers(teachers);
    	
    	String teacherFile = "giaovien.txt";
    	schoolController.signContractWithTeacherFrom(teacherFile);
    	
    	assertEquals(1, schoolService.getSchoolById("nbk-vl").getNumberOfTeacher());
    	assertEquals("Tran Van Thanh", schoolService.getSchoolById("nbk-vl").getTeachers().get(0).getName());
    }
    
    public void testFormatTextRow() {
    	PrinterSupport printerSupport = new PrinterSupport();
    	Teacher teacher = new Teacher("337829999", "Tran Van Thanh", "nbk-vl");
    	List<Teacher> teachers = new ArrayList<>();
    	teachers.add(teacher);
    	School school = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", "Vinh Long", teachers, 2000);
    	
    	assertEquals("- 337829999 ||| Tran Van Thanh ||| nbk-vl", printerSupport.formatTextRow(teacher));
    	assertEquals("- nbk-vl ||| Truong trung hoc Chuyen Nguyen Binh Khiem ||| 2000 ||| Vinh Long", printerSupport.formatTextRow(school));
    }
    
    public void testSchoolDataWillBeExportedToTextFileMustSameAsExpectedSchoolFile() {
    	
    	PrinterSupport printerSupport = new PrinterSupport();
    	School school = new School("nbk-vl", "Truong trung hoc Chuyen Nguyen Binh Khiem", "Vinh Long", new ArrayList<Teacher>(), 2000);
    	School school2 = new School("nbk-qn","Truong trung hoc Chuyen Nguyen Binh Khiem","Quang Ngai", new ArrayList<Teacher>(), 2500);
    	List<School> schools = new ArrayList<>();
    	schools.add(school);
    	schools.add(school2);
    	
    	String expectedSchoolFile = "Danh sach truong\n\n"
    			+ "- nbk-vl ||| Truong trung hoc Chuyen Nguyen Binh Khiem ||| 2000 ||| Vinh Long"
    			+ "- nbk-qn ||| Truong trung hoc Chuyen Nguyen Binh Khiem ||| 2500 ||| Quang Ngai";
      	
    	StringBuilder textBuilder = new StringBuilder();
    	textBuilder.append(printerSupport.schoolTextFileHeader());
    	for (School s : schools) {
    		textBuilder.append(printerSupport.formatTextRow(s));
    	}
    	String dataExported = textBuilder.toString();
    	
    	assertEquals(expectedSchoolFile, dataExported);
    }
    
    public void testTeacherDataWillBeExportedToTextFileMustSameAsExpectedTeacherFile() {
    	
    	PrinterSupport printerSupport = new PrinterSupport();
    	Teacher teacher = new Teacher("337829999", "Tran Van Thanh", "nbk-vl");
    	Teacher teacher2 = new Teacher("285656695", "Nguyen Van Han", "nbk-qn");
    	List<Teacher> teachers = new ArrayList<>();
    	teachers.add(teacher);
    	teachers.add(teacher2);
    	
    	String expectedTeacherFile = "Danh sach giao vien\n\n"
    			+ "- 337829999 ||| Tran Van Thanh ||| nbk-vl"
    			+ "- 285656695 ||| Nguyen Van Han ||| nbk-qn";
    	
    	StringBuilder textBuilder = new StringBuilder();
    	textBuilder.append(printerSupport.teacherTextFileHeader());
    	for (Teacher t : teachers) {
    		textBuilder.append(printerSupport.formatTextRow(t));
    	}
    
    	String dataExported = textBuilder.toString();
    	
    	assertEquals(expectedTeacherFile, dataExported);
    }
    
    public void testCreateSheetWithHeaderFunction() {
    	ExcelUtil excelUtil = new ExcelUtil();
    	String[] headerColumns = {"CMND", "Name", "Working School's ID"};
        Workbook workbook = new XSSFWorkbook(); 
        Sheet sheet = excelUtil.createSheetWithHeader(workbook, "Teacher", headerColumns);
      
        assertEquals("CMND", sheet.getRow(0).getCell(0).getStringCellValue());
        assertEquals("Name", sheet.getRow(0).getCell(1).getStringCellValue());
        assertEquals("Working School's ID", sheet.getRow(0).getCell(2).getStringCellValue());
    }
    
    public void testCreateRowByTeacher() {
    	
    	ExcelUtil excelUtil = new ExcelUtil();
    	String[] headerColumns = {"CMND", "Name", "Working School's ID"};
        Workbook workbook = new XSSFWorkbook(); 
        Sheet sheet = excelUtil.createSheetWithHeader(workbook, "Teacher", headerColumns);
        
        Teacher teacher1 = new Teacher("285656456", "Le Van A", "nbk-vl");
        Teacher teacher2 = new Teacher("285656567", "Le Van B", "nbk-qn");
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher1);
        teachers.add(teacher2);
        
        int currentRow = 1;
        for (Teacher teacher : teachers) {
            excelUtil.createRowForSheetBy(sheet, currentRow++, teacher);
        }
        
        assertEquals("285656456", sheet.getRow(1).getCell(0).getStringCellValue());
        assertEquals("Le Van A", sheet.getRow(1).getCell(1).getStringCellValue());
        assertEquals("nbk-vl", sheet.getRow(1).getCell(2).getStringCellValue());
        assertEquals("285656567", sheet.getRow(2).getCell(0).getStringCellValue());
        assertEquals("Le Van B", sheet.getRow(2).getCell(1).getStringCellValue());
        assertEquals("nbk-qn", sheet.getRow(2).getCell(2).getStringCellValue());
        
    }
}
