package sms.function;

import static org.junit.jupiter.api.Assertions.*;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sms.model.School;
import sms.model.Teacher;

class FunctionTest {
	private Function function = new Function();

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAddSchool() {
		List<School> schoolList = new ArrayList<>();
		School school = new School();
		List<School> expected = asList(school);

		function.addSchool(schoolList, school);
		assertTrue(schoolList.equals(expected));
	}

	@Test
	void testAddTeacherToSchool() {
		School school = new School();
		Teacher teacher = new Teacher();
		List<Teacher> expected = asList(teacher);

		function.addTeacherToSchool(school, teacher);
		assertTrue(school.getTeacherList().equals(expected));
	}

	@Test
	void testFindSchoolByName() {
		School school1 = new School("name1");
		School school2 = new School("name2");
		List<School> schools = asList(school1, school2);

		School actual = function.findSchoolByName(schools, "name2");
		School expected = school2;
		assertTrue(actual.equals(expected));
	}
	
	/*@Test
	void testFindSchoolByName_NotFound() {
		School school1 = new School("name1");
		School school2 = new School("name2");
		List<School> schools = asList(school1, school2);

		School actual = function.findSchoolByName(schools, "name3");
		School expected = null;
		assertTrue(actual.equals(expected));
	}*/

	@Test
	void testFindTeacherByName() {
		Teacher teacher1 = new Teacher("name1");
		Teacher teacher2 = new Teacher("name2");
		List<Teacher> teachers=asList(teacher1,teacher2);
		
		Teacher actual = function.findTeacherByName(teachers, "name1");
		Teacher expect = teacher1;
		assertTrue(actual.equals(expect));
	}

	@Test
	void testFindTeacherByAddress() {
		Teacher teacher1 = new Teacher("address1");
		Teacher teacher2 = new Teacher("address2");
		List<Teacher> teachers=asList(teacher1,teacher2);
		
		Teacher actual = function.findTeacherByAddress(teachers, "address2");
		Teacher expect = teacher2;
		assertTrue(actual.equals(expect));
	}

}
