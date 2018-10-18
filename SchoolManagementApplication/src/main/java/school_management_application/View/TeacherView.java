package school_management_application.View;

import java.util.List;
import java.util.ListIterator;
import school_management_application.Model.Teacher;

public class TeacherView {
	
	public void displayEveryTeacher(Teacher teacher) {
		ListIterator<String> itr = teacher.getIdentityCard().listIterator();
		
		System.out.printf(String.format("%-50s\n", teacher.getName()));
		System.out.printf(String.format("Teacher ID: %s\n", teacher.getName()));
		System.out.printf(String.format("School ID: %s\n", teacher.getSchoolID()));
		System.out.printf(String.format("Indentity Card: "));
        while(itr.hasNext()){
        	System.out.printf(String.format("%s, ", itr.next()));
        }
        System.out.printf(String.format("\b\b\n"));
        System.out.printf(String.format("Phone: "));
        itr = teacher.getPhoneNo().listIterator();
        
        while(itr.hasNext()){
        	System.out.printf(String.format("%s, ", itr.next()));
        }
        System.out.printf(String.format("\b\b\n"));
        System.out.printf(String.format("Address: %s\n", teacher.getAddress()));
	}
	
	public void displayTeachers(List<Teacher> teachers) {
		ListIterator<Teacher> itr = teachers.listIterator();
        
        while(itr.hasNext()){
        	displayEveryTeacher(itr.next());
        	System.out.println();
        }
	}
}
