package com.cleancode.education;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.views.ShowNameWithSpecialCharView;

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
    public void testApp() // not very meaningful name
    {
    	// very trivial test. We dont want this
        School school = new School();
        school.setName("school 1");
        assertEquals("school 1", school.getName());
    }
    
   
    public void testAddTeacherToSchoolShouldIncreaseTheNumberOfTeacher() // meaningful name, reflect 
    //business logic
    {
        School school = new School();
        assertEquals(0, school.getNumberOfTeacher());
        Teacher teacher = new Teacher();
        school.signContractWith(teacher);
        // see how logic is test here!
        assertEquals(1, school.getNumberOfTeacher());
    }
    
    public void nameShouldHaveStartInConsole() {
    	ShowNameWithSpecialCharView view = new ShowNameWithSpecialCharView();
    	assertEquals("***name***", view.nameWithThreeStartAround("name"));
    }
    
}
