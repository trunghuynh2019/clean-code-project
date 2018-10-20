package school_management_application;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import school_management_application.DTO.SchoolDto;
import school_management_application.DTO.TeacherDto;
import school_management_application.Model.School;
import school_management_application.Model.Teacher;
import school_management_application.Repository.TeacherRepository;
import school_management_application.Service.TeacherService;

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
    public void testCreateASchoolHaveData(){
        School school = new School();
        
    	school.setID("ID");
    	school.setName("Name");
    	school.setAddress("Address");
    	school.setCountStudent(1);
        assertEquals("ID",school.getID());
        assertEquals("Name",school.getName());
        assertEquals("Address",school.getAddress());
        assertEquals(Integer.valueOf(1),school.getCountStudent());
        
    }
    
    public void testCreateASchoolDtoHaveData(){
        SchoolDto schoolDto = new SchoolDto();
        
    	schoolDto.setID("ID");
    	schoolDto.setName("Name");
    	schoolDto.setAddress("Address");
    	schoolDto.setCountStudent(1);
        assertEquals("ID",schoolDto.getID());
        assertEquals("Name",schoolDto.getName());
        assertEquals("Address",schoolDto.getAddress());
        assertEquals(Integer.valueOf(1),schoolDto.getCountStudent());
        
    }
    
    public void testCreateATeacherHaveData(){
        Teacher teacher = new Teacher();
        
        List<String> phNo = new ArrayList<String>();
        
        teacher.setTeacherID(1);
        teacher.setSchoolID("SchoolID");
    	teacher.setName("Name");
    	teacher.setAddress("Address");
    	teacher.setIdentityCard("IdentityCard");
    	phNo.add("PhoneNo");
    	teacher.setPhoneNo(phNo);
    	teacher.setAddress("Address");
        assertEquals(Integer.valueOf(1),teacher.getTeacherID());
        assertEquals("SchoolID",teacher.getSchoolID());
        assertEquals("Name",teacher.getName());
        assertEquals("IdentityCard",teacher.getIdentityCard().get(0));
        assertEquals("PhoneNo",teacher.getPhoneNo().get(0));
        assertEquals("Address",teacher.getAddress());
    }
    
    public void testCreateATeacherDtoHaveData(){
        TeacherDto teacherDto = new TeacherDto();
        
        List<String> phNo = new ArrayList<String>();
        
        teacherDto.setTeacherID(1);
        teacherDto.setSchoolID("SchoolID");
    	teacherDto.setName("Name");
    	teacherDto.setAddress("Address");
    	teacherDto.setIdentityCard("IdentityCard");
    	phNo.add("PhoneNo");
    	teacherDto.setPhoneNo(phNo);
    	teacherDto.setAddress("Address");
        assertEquals(Integer.valueOf(1),teacherDto.getTeacherID());
        assertEquals("SchoolID",teacherDto.getSchoolID());
        assertEquals("Name",teacherDto.getName());
        assertEquals("IdentityCard",teacherDto.getIdentityCard());
        assertEquals("PhoneNo",teacherDto.getPhoneNo().get(0));
        assertEquals("Address",teacherDto.getAddress());
    }
    
    public void testSignInContractWithTeacherSuccessful() {
    	TeacherDto teacherDto = new TeacherDto();
    	TeacherService teachSv = new TeacherService();
    	Teacher teacher = new Teacher();
    	
    	teacherDto.setName("Name");
    	teacher = teachSv.signContractWithTeacher(teacherDto);
    	assertEquals("Name",teacher.getName());
    }
    public void testSignInContractWithTeacherErrorDuplicateData() {
    	TeacherDto teacherDto = new TeacherDto();
    	TeacherService teachSv = new TeacherService();
    	Teacher teacher = new Teacher();
    	TeacherDto teachDto;
    	
    	teacherDto.setName("Name");
    	teacherDto.setAddress("Address");
    	teacherDto.setSchoolID("SchoolID");
    	teacher = teachSv.signContractWithTeacher(teacherDto);
    	teachDto = teacherDto;
    	teacher = teachSv.signContractWithTeacher(teachDto);
    	assertEquals(null,teacher);
    }
    
    public void testSaveTeacherToRepositorySuccessfulWithoutTeacherID(){
    	Teacher teacher = new Teacher();
    	TeacherRepository teachRepo = new TeacherRepository();
    	
    	teacher.setName("Name");
    	teachRepo.save(teacher);
    	assertEquals("Name",teachRepo.findByTeacherID(1).getName());
    }
    
    public void testSaveTeacherToRepositorySuccessfulWithTeacherID(){
    	Teacher teacher = new Teacher();
    	TeacherRepository teachRepo = new TeacherRepository();
    	
    	teacher.setTeacherID(1);
    	teacher.setName("Name");
    	teachRepo.save(teacher);
    	assertEquals("Name",teachRepo.findByTeacherID(1).getName());
    }
    
    public void testSaveTeacherToRepositoryWithTeacherIDErrorDuplicate(){
    	Teacher teacher = new Teacher();
    	TeacherRepository teachRepo = new TeacherRepository();
    	
    	teacher.setTeacherID(1);
    	teacher.setName("Name");
    	teachRepo.save(teacher);
    	teacher.setTeacherID(1);
    	teacher.setName("Name2");
    	teachRepo.save(teacher);
    	assertEquals("Name2",teachRepo.findByTeacherID(1).getName());
    }
    
    public void testSaveTeacherToRepositoryIncreaseTeacherID(){
    	Teacher teacher = new Teacher();
    	TeacherRepository teachRepo = new TeacherRepository();
    	Teacher teach = new Teacher();
    	
    	teacher.setName("Name");
    	teachRepo.save(teacher);
    	assertEquals(Integer.valueOf(1),teachRepo.findAll().get(0).getTeacherID());
    	teach.setName("Name2");
    	teachRepo.save(teach);
    	assertEquals(Integer.valueOf(2),teachRepo.findAll().get(1).getTeacherID());
    }
}
