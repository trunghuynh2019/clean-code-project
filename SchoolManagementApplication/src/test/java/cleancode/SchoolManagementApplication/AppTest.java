package cleancode.SchoolManagementApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sma.entity.School;
import sma.entity.Teacher;

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
    	school.setName("DH Can Tho");
        assertEquals("DH Can Tho", school.getName());
    }
    
    public void testAddSchool() {
		List<School> schools = new ArrayList<School>();
		School school = new School();
		List<School> expected = Arrays.asList(school);

		schools.add(school);
		assertTrue(schools.equals(expected));
	}

    
    public void testAddTeacherToSchool() {
    	School school = new School();
    	Teacher teacher = null;
    	List<Teacher> teachers1 = Arrays.asList(teacher);
    	//int i = school.getNumOfTeachers();
    	school.signContractWith(teacher);
    	assertTrue(school.getTeachers().equals(teachers1));
    }
    
}
