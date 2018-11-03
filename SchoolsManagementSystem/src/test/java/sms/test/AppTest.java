package sms.test;

import java.util.ArrayList;
import java.util.List;

import sms.management.file.export.ExcelExport;
import sms.management.file.export.FileExport;
import sms.management.file.export.HtmlExport;
import sms.management.file.export.JSonExport;
import sms.management.file.importt.FileImport;
import sms.management.file.importt.JSonImport;
import sms.management.file.importt.TextImport;
import sms.management.function.ManagementImpl;
import sms.model.School;
import sms.model.Teacher;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	private static ManagementImpl management = null;
	private static FileExport fileExport = null;
	private static FileImport fileImport = null;
	private final static String PATH = System.getProperty("user.dir") + "\\fileData\\";
	private final static String FILE_DATA_SCHOOL = PATH + "dataSchool";
	private final static String FILE_DATA_TEACHER = PATH + "dataTeacher";
	
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
    
    public void testSchoolManagementSystem() {
    	assertTrue(true);
    }
    
    /*
     * Test Management Class
     */
    // test import data of teacher in json file
    public void testImportDataOfAllTeacherInSchoolSystemInJSON() {
    	fileImport = new JSonImport();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	String fileName = FILE_DATA_TEACHER + ".json";
    	
    	fileImport.importDataOfTeacherFromFile(teachers, fileName);
    	assertEquals(2, teachers.size());
    }
    
 // test import data of teacher in json file
    public void testImportDataOfAllSchoolInSchoolSystemInJSON() {
    	fileImport = new JSonImport();
    	List<School> schools = new ArrayList<School>();
    	String fileName = FILE_DATA_SCHOOL + ".json";
    	
    	fileImport.importDataOfSchoolFromFile(schools, fileName);
    	assertEquals(2, schools.size());
    }
    
    
    // test export data of teacher in json file
    public void testExportDataOfAllTeacherInSchoolSystemInJSON() {
    	fileExport = new JSonExport();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	String fileName = FILE_DATA_TEACHER + ".json";
    	teachers.add(new Teacher(334442222,"Tran Van Thanh","Vinh Long"));
    	teachers.add(new Teacher(337829999,"Nguyen Thi Tho","Quang Ngai"));
    	
    	boolean check = fileExport.exportDataOfTeacherToFile(teachers, fileName);
    	assertEquals(true, check);
    }
    
    // test export data of school in json file
    public void testExportDataOfAllSchoolInSchoolSystemInJSON() {
    	fileExport = new JSonExport();
    	List<School> schools = new ArrayList<School>();
    	String fileName = FILE_DATA_SCHOOL + ".json";
    	schools.add(new School("FPT-U", "FPT", 5, "Ho Chi Minh city", 0, null));
    	schools.add(new School("BKU", "Bach Khoa", 10, "Ho Chi Minh city", 0, null));
    	
    	boolean check = fileExport.exportDataOfSchoolToFile(schools, fileName);
    	assertEquals(true, check);
    }
    
    
    // test export data of school in html file
    public void testExportDataOfAllTeacherInSchoolSystemInHTML() {
    	fileExport = new HtmlExport();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	String fileName = FILE_DATA_TEACHER + ".html";
    	teachers.add(new Teacher(334442222,"Tran Van Thanh","Vinh Long"));
    	teachers.add(new Teacher(337829999,"Nguyen Thi Tho","Quang Ngai"));
    	
    	boolean check = fileExport.exportDataOfTeacherToFile(teachers, fileName);
    	assertEquals(true, check);
    }
    
    // test export data of school in html file
    public void testExportDataOfAllSchoolInSchoolSystemInHTML() {
    	fileExport = new HtmlExport();
    	List<School> schools = new ArrayList<School>();
    	String fileName = FILE_DATA_SCHOOL + ".html";
    	schools.add(new School("FPT-U", "FPT", 5, "Ho Chi Minh city", 0, null));
    	schools.add(new School("BKU", "Bach Khoa", 10, "Ho Chi Minh city", 0, null));
    	
    	boolean check = fileExport.exportDataOfSchoolToFile(schools, fileName);
    	assertEquals(true, check);
    }
    
    // test export data of school in excel file
    public void testExportDataOfAllSchoolInSchoolSystem() {
    	fileExport = new ExcelExport();
    	List<School> schools = new ArrayList<School>();
    	String fileName = FILE_DATA_SCHOOL + ".xls";
    	schools.add(new School("FPT-U", "FPT", 5, "Ho Chi Minh city", 0, null));
    	schools.add(new School("BKU", "Bach Khoa", 10, "Ho Chi Minh city", 0, null));
    	
    	boolean check = fileExport.exportDataOfSchoolToFile(schools, fileName);
    	assertEquals(true, check);
    }
    
    // test export data of Teacher in excel file
    public void testExportDataTeacherOfEachSchool() {
    	fileExport = new ExcelExport();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	String fileName = FILE_DATA_TEACHER + ".xls";
    	teachers.add(new Teacher(123123123,"Teacher A","Ho Chi Minh"));
    	teachers.add(new Teacher(123564125,"Teacher B","Ho Chi Minh"));
    	
    	boolean check = fileExport.exportDataOfTeacherToFile(teachers, fileName);
    	assertEquals(true, check);
    }
    
    // test load database of school list from text file
    public void testLoadDataOfSchoolFromFile(){
    	fileImport = new TextImport();
    	List<School> schools = new ArrayList<School>();
    	String fileName = "truong.txt";
    	fileImport.importDataOfSchoolFromFile(schools, fileName);
    	
    	// compare with number of student in database
    	assertEquals(2, schools.size());
    }
    
    // test load database of school list  from text file
    public void testLoadDataOfTeacherFromFile(){
    	fileImport = new TextImport();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	String fileName = "giaovien.txt";
    	fileImport.importDataOfTeacherFromFile(teachers, fileName);
    	
    	// compare with number of teacher in database
    	assertEquals(2, teachers.size());
    }
    
    // test add new school
    public void testAddSchoolIntoSchoolSystem() {
    	management = new ManagementImpl();
    	List<School> schools = new ArrayList<School>();
    	School school = new School();
    	management.addSchool(schools, school);
    	
    	assertEquals(1, schools.size());
    }
    
    // test Sign a Contract With Teacher
    public void testSignContractWithTeacherOfEachSchool() {
    	management = new ManagementImpl();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	Teacher teacher = new Teacher();
    	management.signContractWithTeacher(teachers, teacher);
    	
    	assertEquals(1, teachers.size());
    }
    
    // test search school by id 
    public void testSearchSchoolById() {
    	management = new ManagementImpl();
    	List<School> schools = new ArrayList<School>();
    	School school = new School();
    	school.setId("E1");
    	schools.add(school);
    	
    	assertEquals(schools.get(0), management.searchSchoolById(schools, "E1"));
    }
    
    // test search school by name
    public void testSearchTeacherByName() {
    	management = new ManagementImpl();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	teachers.add(new Teacher());
    	teachers.get(0).setName("John Carter");
    	
    	assertEquals(teachers.get(0), management.searchTeacherByName(teachers, "John Carter"));
    }
    
    // test search school by address
    public void testSearchTeacherByAddress() {
    	management = new ManagementImpl();
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	teachers.add(new Teacher());
    	teachers.get(0).setAddress("Ho Chi Minh");
    	
    	assertEquals(teachers.get(0), management.searchTeacherByAddress(teachers, "Ho Chi Minh"));
    }
    
    
    /*
     * Test MainView Class
     */
    // test show the information of school
    public void testShowInformationOfSchool() {
    	School school = new School("AA", "BlaBla", 1000, "BB", 0, null);
    	String exp = "============== BlaBla ==============\n"
    			  +  "- Id: AA\n"
    			  +  "- Address: BB\n"
    			  +  "- Number of students: 1000\n"
    			  +  "- Number of teachers: 0\n";
    	
    	assertEquals(exp, school.toString());
    }
    
    
    /*
     * Test Teacher View
     */
    // test show the information of school
    public void testShowInformationOfTeacher() {
    	Teacher teacher = new Teacher(123456789, "AA", "BB");
    	String exp = "- AA\n"
    		      +  "- BB\n"
    		      +  "- 123456789";
    	
    	assertEquals(exp, teacher.toString());
    }
}
