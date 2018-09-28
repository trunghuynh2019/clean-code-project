package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class School {
	private String name;
        private ArrayList<Teacher> teacherList;
        
	public School() {
            this.name = "";
            teacherList = new ArrayList<Teacher>();
	}
	
	public School(String name, ArrayList<Teacher> teacherList) {
		
            this.name = name;
            this.teacherList = teacherList;
	}
	
	public String getName() {
            
            return name;
	}

	public void setName(String name) {
            
            this.name = name;
	}
        
        public ArrayList<Teacher> getTeacherList(){
            
            return teacherList;
        }
        
        public void setTeacherList(ArrayList<Teacher> teacherList){
            
            this.teacherList = teacherList;
        }
}
