package sms.test;

import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import sms.model.School;
import sms.model.Teacher;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.repository.impl.SchoolRepoImpl;
import sms.repository.impl.TeacherRepoImpl;

public class RepositoryTest extends TestCase {
	private static final SchoolRepo SCHOOL_REPO = new SchoolRepoImpl();
	private static final TeacherRepo TEACHER_REPO = new TeacherRepoImpl();
	
	public RepositoryTest(String testName) {
		super(testName);
	}
	
	public static Test suite() {
		return new TestSuite(RepositoryTest.class);
	}
	
	public void testAddSchool() {
		List<School> schools = new ArrayList<School>();
		School school = new School();
		List<School> expected = asList(school);

		schools.add(school);
		assertTrue(schools.equals(expected));
	}

	public void testAddTeacherToSchool() {
		School school = new School();
		Teacher teacher = new Teacher();
		List<Teacher> expected = asList(teacher);

		school.addTeacher(teacher);
		assertTrue(school.getTeachers().equals(expected));
	}
	
	public void testFindSchoolById() {
		School school1 = new School("001", "name1");
		School school2 = new School("002", "name2");
		School school3 = new School("003", "name3");
		List<School> schools = asList(school1, school2, school3);

		School actual = SCHOOL_REPO.findSchoolById(schools, "002").get();
		School expected = school2;
		assertTrue(actual.equals(expected));
	}

	public void testFindSchoolByName() {
		School school1 = new School("001", "name1");
		School school2 = new School("002", "name2");
		List<School> schools = asList(school1, school2);

		School actual = SCHOOL_REPO.findSchoolByName(schools, "name2").get();
		School expected = school2;
		assertTrue(actual.equals(expected));
	}
	
	public void testFindTeacherById() {
		School school = new School("001");
		Teacher teacher1 = new Teacher(1, "name1");
		Teacher teacher2 = new Teacher(2, "name2");
		Teacher teacher3 = new Teacher(4, "name3");
		school.addTeacher(teacher1);
		school.addTeacher(teacher2);
		school.addTeacher(teacher3);
		
		Teacher actual = TEACHER_REPO.findTeacherById(school.getTeachers(), 4).get();
		Teacher expect = teacher3;
		assertTrue(actual.equals(expect));
	}
	
	public void testFindTeacherByName() {
		School school = new School("001");
		Teacher teacher1 = new Teacher(1, "name1");
		Teacher teacher2 = new Teacher(2, "name2");
		Teacher teacher3 = new Teacher(4, "name3");
		school.addTeacher(teacher1);
		school.addTeacher(teacher2);
		school.addTeacher(teacher3);
		
		Teacher actual = TEACHER_REPO.findTeacherByName(school.getTeachers(), "name2").get();
		Teacher expect = teacher2;
		assertTrue(actual.equals(expect));
	}
}
