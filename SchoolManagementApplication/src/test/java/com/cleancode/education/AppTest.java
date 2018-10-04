package com.cleancode.education;

import java.util.ArrayList;
import java.util.List;

import com.cleancode.education.functions.SchoolManagement;
import com.cleancode.education.functions.SchoolManagementImpl;
import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
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
    
    /*
	public void addSchool(List<School> schools, School school);
	public void addSchoolFrom(String fileName, List<School> schools);
	public void signContractWithTeacher(School school, Teacher teacher);
	public void signContractWithTeacherFrom(String fileName, List<School> schools);
	*/
    
    public void testAddSchoolWithEmptyList() {
    	List<School> schools = new ArrayList<>();
    	SchoolManagement sm = new SchoolManagementImpl();
    	
    	sm.addSchool(schools, new School());
    	
    	assertEquals(1, schools.size());
    }
    
    public void testAddSchoolWithNoneEmptyList() {
    	List<School> schools = new ArrayList<>();
    	schools.add(new School("nbk-vl","NBK","VL",null,50));
    	
    	SchoolManagement sm = new SchoolManagementImpl();
    	sm.addSchool(schools, new School());
    	
    	assertEquals(2, schools.size());
    }
    
    public void testAddExistedSchoolShouldNotIncreaseTheNumberOfSchool() {
    	List<School> schools = new ArrayList<>();
    	schools.add(new School("nbk-vl","NBK","VL",null,50));
    	
    	SchoolManagement sm = new SchoolManagementImpl();
    	sm.addSchool(schools, new School("nbk-vl","BK","HCM",null,50));
    	
    	assertEquals(1, schools.size());
    }
    
    public void testAddExistedSchoolShouldUpdateSchoolInfo() {
    	List<School> schools = new ArrayList<>();
    	schools.add(new School("nbk-vl","NBK","VL",null,50));
    	
    	SchoolManagement sm = new SchoolManagementImpl();
    	sm.addSchool(schools, new School("nbk-vl","BK","HCM",null,40));
    	
    	assertEquals("BK", schools.get(0).getName());
    	assertEquals("HCM", schools.get(0).getAddress());
    	assertEquals(40, schools.get(0).getNumberOfStudent());
    }
    
    public void testSignContractWithTeacherInEmptyList() {
    	School school = new School();
    	SchoolManagement sm = new SchoolManagementImpl();
    	sm.signContractWithTeacher(school, new Teacher());
    	
    	assertEquals(1, school.getNumberOfTeacher());
    }
    
    public void testSignContractWithTeacherInNoneEmptyList() {
    	School school = new School();
    	school.signContractWith(new Teacher("123654789","Tien","nbk-vl"));
    	
    	SchoolManagement sm = new SchoolManagementImpl();
    	sm.signContractWithTeacher(school, new Teacher("123456789","Huy","nbk-vl"));
    	
    	assertEquals(2, school.getNumberOfTeacher());
    }
    
    public void testAddSchoolFromFile(){
    	List<School> schools = new ArrayList<School>();
    	SchoolManagement sm = new SchoolManagementImpl();
    	String fileName = "truong.txt";
    	sm.addSchoolFrom(fileName, schools);
    	
    	assertEquals(2, schools.size());
    }
    
    public void testAddSchoolFromFileShouldNotIncreaseTheNumberOfSchoolIfSchoolExisted() {
    	List<School> schools = new ArrayList<School>();
    	schools.add(new School("nbk-vl","","",null,50));
    	
    	SchoolManagement sm = new SchoolManagementImpl();
    	String fileName = "truong.txt";
    	sm.addSchoolFrom(fileName, schools);
    	
    	assertEquals(2, schools.size());
    }
    
    public void testAddSchoolFromFileShouldUpdateSchoolInfoIfSchoolExisted() {
    	List<School> schools = new ArrayList<School>();
    	schools.add(new School("nbk-vl","","",null,50));
    	
    	SchoolManagement sm = new SchoolManagementImpl();
    	String fileName = "truong.txt";
    	sm.addSchoolFrom(fileName, schools);
    	
    	assertEquals("Truong trung hoc Chuyen Nguyen Binh Khiem", schools.get(0).getName());
    	assertEquals("Vinh Long", schools.get(0).getAddress());
    	assertEquals(2000, schools.get(0).getNumberOfStudent());
    }
    
    public void testSignContractWithTeacherFromFile() {
    	List<School> schools = new ArrayList<School>();
    	SchoolManagement sm = new SchoolManagementImpl();
    	String schoolFile = "truong.txt";
    	String teacherFile = "giaovien.txt";
    	sm.addSchoolFrom(schoolFile, schools);
    	sm.signContractWithTeacherFrom(teacherFile, schools);
    	
    	assertEquals(1, schools.get(0).getNumberOfTeacher());
    	assertEquals(1, schools.get(1).getNumberOfTeacher());
    }
    
    public void testAddTeacherFromFileShouldNotIncreaseTheNumberOfTeacherIfTeacherExisted() {
    	List<School> schools = new ArrayList<School>();
    	SchoolManagement sm = new SchoolManagementImpl();
    	String schoolFile = "truong.txt";
    	sm.addSchoolFrom(schoolFile, schools);
    	List<Teacher> teachers = new ArrayList<>();
    	teachers.add(new Teacher("337829999","","nbk-vl"));
    	schools.get(0).setTeachers(teachers);
    	
    	String teacherFile = "giaovien.txt";
    	sm.signContractWithTeacherFrom(teacherFile, schools);
    	
    	assertEquals(1, schools.get(0).getNumberOfTeacher());
    }
    
    public void testAddTeacherFromFileShouldUpdateTeacherInfoIfTeacherExisted() {
    	List<School> schools = new ArrayList<School>();
    	SchoolManagement sm = new SchoolManagementImpl();
    	String schoolFile = "truong.txt";
    	sm.addSchoolFrom(schoolFile, schools);
    	List<Teacher> teachers = new ArrayList<>();
    	teachers.add(new Teacher("337829999","","nbk-vl"));
    	schools.get(0).setTeachers(teachers);
    	
    	String teacherFile = "giaovien.txt";
    	sm.signContractWithTeacherFrom(teacherFile, schools);
    	
    	assertEquals(1, schools.get(0).getNumberOfTeacher());
    	assertEquals("Tran Van Thanh", schools.get(0).getTeachers().get(0).getName());
    }
}
