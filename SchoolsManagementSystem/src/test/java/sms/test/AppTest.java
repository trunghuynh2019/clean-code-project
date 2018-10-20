package sms.test;

import java.util.ArrayList;
import java.util.List;

import sms.function.Management;
import sms.model.School;
import sms.model.Teacher;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	private static Management management = new Management();

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
    // test export data of school
    public void testExportDataOfAllSchoolInSchoolSystem() {
    	List<School> schools = new ArrayList<School>();
    	String fileName = "dataSchool.xls";
    	schools.add(new School("FPT-U", "FPT", 5, "Ho Chi Minh city", 0, null));
    	schools.add(new School("BKU", "Bach Khoa", 10, "Ho Chi Minh city", 0, null));
    	
    	boolean check = management.exportDataOfSchools(schools, fileName);
    	assertEquals(true, check);
    }
    
 // test export data of school
    public void testExportDataTeacherOfEachSchool() {
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	String fileName = "dataTeacher.xls";
    	teachers.add(new Teacher(123123123,"Teacher A","Ho Chi Minh"));
    	teachers.add(new Teacher(123564125,"Teacher B","Ho Chi Minh"));
    	
    	boolean check = management.exportDataOfTeachers(teachers, fileName);
    	assertEquals(true, check);
    }
    
    // test load database of school list
    public void testLoadDataOfSchoolFromFile(){
    	List<School> schools = new ArrayList<School>();
    	String fileName = "truong.txt";
    	management.loadDatabaseOfSchool(fileName, schools);
    	
    	// compare with number of student in database
    	assertEquals(2, schools.size());
    }
    
    // test load database of school list
    public void testLoadDataOfTeacherFromFile(){
    	School school = new School();
    	school.setTeachers(new ArrayList<Teacher>());
    	String fileName = "giaovien.txt";
    	management.loadDatabaseOfTeacher(fileName, school);
    	
    	// compare with number of teacher in database
    	assertEquals(2, school.getTeachers().size());
    }
    
    // test add new school
    public void testAddSchoolIntoSchoolSystem() {
    	List<School> schools = new ArrayList<School>();
    	School school = new School();
    	management.addSchool(schools, school);
    	
    	assertEquals(1, schools.size());
    }
    
    // test Sign a Contract With Teacher
    public void testSignContractWithTeacherOfEachSchool() {
    	School school = new School();
    	school.setTeachers(new ArrayList<Teacher>());
    	Teacher teacher = new Teacher();
    	management.signContractWithTeacher(school, teacher);
    	
    	assertEquals(1, school.getTeachers().size());
    }
    
    // test search school by id 
    public void testSearchSchoolById() {
    	List<School> schools = new ArrayList<School>();
    	School school = new School();
    	school.setId("E1");
    	schools.add(school);
    	
    	assertEquals(schools.get(0), management.searchSchoolById(schools, "E1"));
    }
    
    // test search school by name
    public void testSearchTeacherByName() {
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	teachers.add(new Teacher());
    	teachers.get(0).setName("John Carter");
    	
    	assertEquals(teachers.get(0), 
    			management.searchTeacherByName(teachers, "John Carter"));
    }
    
    // test search school by address
    public void testSearchTeacherByAddress() {
    	List<Teacher> teachers = new ArrayList<Teacher>();
    	teachers.add(new Teacher());
    	teachers.get(0).setAddress("Ho Chi Minh");
    	
    	assertEquals(teachers.get(0), 
    			management.searchTeacherByAddress(teachers, "Ho Chi Minh"));
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
