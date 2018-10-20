package school_management_application.View;

import java.util.List;
import java.util.ListIterator;

import school_management_application.Model.School;

public class SchoolView {
	
	public void displayEverySchool(School school, Integer countTeacher) {
		
		System.out.printf(String.format("%-30s\n", school.getName()));
		System.out.printf(String.format("ID: %s\n", school.getID()));
        System.out.printf(String.format("Address: %s\n", school.getAddress()));
        System.out.printf(String.format("Number of students: %d\n", school.getCountStudent()));
        System.out.printf(String.format("Number of teachers: %d\n", countTeacher));
	}
	public void displaySchools(List<School> schools, List<Integer> countTeacher) {
		ListIterator<School> itr_sch = schools.listIterator();
		ListIterator<Integer> itr_count = countTeacher.listIterator();
        
        while(itr_sch.hasNext() && itr_count.hasNext()){
        	displayEverySchool(itr_sch.next(), itr_count.next());
        	System.out.println();
        }
	}
}
