package UnitTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import sm.entity.School;
import sm.entity.Teacher;

class EMTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	public void signContractWithTeacher()
	{
		School school = new School();
		assertEquals(0, school.getNumOfTeachers());
	}
	

}
