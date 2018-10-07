package UnitTest;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import sm.controller.SchoolFunction;
import sm.controller.TeacherFunction;
import sm.entity.School;
import sm.entity.Teacher;

class EMTest {

	@Test
	//void test() {
	//	fail("Not yet implemented");
	//}
	
	public void testWritingFileTeacher()
	{
		TeacherFunction teacherFunction = new TeacherFunction();
		List<Teacher> teachers = new ArrayList<>();
		School school = new School();
		teacherFunction.WritingFileToTeachers(teachers);
		assertNotNull(teachers);
	}
	
	public void testWritingFileSchool()
	{
		SchoolFunction schoolFunction = new SchoolFunction();
		List<School> schools = new ArrayList<>();
		schoolFunction.WritingFileToSchools(schools);
		assertNotNull(schools);
	}
	public void testInsertSchools()
	{
		SchoolFunction schoolFunction = new SchoolFunction();
		List<School> schools = new ArrayList<>();
		schoolFunction.insertSchools();
		assertNotNull(schools);
	}
	public void testsignContractWithTeacher()
	{
		TeacherFunction teacherFunction = new TeacherFunction();
		List<Teacher> teachers = new ArrayList<>();
		School school = new School();
		Scanner input = new Scanner(System.in);
		teacherFunction.signContractWithTeacher(input, school.getId(), teachers);
		assertNotNull(teachers);
	}
}
