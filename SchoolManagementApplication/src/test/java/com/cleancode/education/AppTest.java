package com.cleancode.education;

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
}
