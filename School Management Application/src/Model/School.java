package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class School {  
        private Integer ID = 0;
	private String name;
        private String address;
        private Integer countStudent;
        private Integer countTeacher;
        private ArrayList<Teacher> teacherList;
        
	public School() {
            ID++;
            name = "";
            address = "";
            countStudent = 0;
            countTeacher = 0;
            teacherList = new ArrayList<Teacher>();
	}
	
	public School(String name, String address, Integer countStudent, ArrayList<Teacher> teacherList) {
            ID++;
            this.name = name;
            this.address = address;
            this.countStudent = countStudent;
            this.teacherList = teacherList;
            this.countStudent = teacherList.size();
	}
        
        public Integer getID(){
            
            return ID;
        }
	
	public String getName() {
            
            return name;
	}

	public void setName(String name) {
            
            this.name = name;
	}
        
        public String getAddress(){
            
            return address;
        }
        
        public void setAddress(String address){
            
            this.address = address;
        }
        
        public Integer getCountStudent(){
            
            return countStudent;
        }
        
        public void setCountStudent(Integer countStudent){
            
            this.countStudent = countStudent;
        }
        
        public Integer getCountTeacher(){
            
            return countTeacher;
        }
        
        public void setCountTeacher(){
            
            countTeacher = teacherList.size();
        }
        
        public ArrayList<Teacher> getTeacherList(){
            
            return teacherList;
        }
        
        public void setTeacherList(ArrayList<Teacher> teacherList){
            
            this.teacherList = teacherList;
        }
}
