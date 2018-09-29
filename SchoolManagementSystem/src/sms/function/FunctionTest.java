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
		school.getTeacherList().add(new Teacher());
		System.out.println(school.getTeacherList());
		Teacher teacher = new Teacher();
		List<Teacher> expected = asList(teacher);
		
		function.addTeacherToSchool(school, teacher);
		assertTrue(school.getTeacherList().equals(expected));
	}

	/*
	 * @Test void testFindSchoolByName() { fail("Not yet implemented"); }
	 * 
	 * @Test void testFindTeacherByName() { fail("Not yet implemented"); }
	 * 
	 * @Test void testFindTeacherByAddress() { fail("Not yet implemented"); }
	 */

}
