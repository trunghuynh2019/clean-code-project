package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class TeacherList {
    private ArrayList<Teacher> teacherList;
    
    public TeacherList(){
        
        teacherList = new ArrayList<Teacher>();
    }
    
    public TeacherList(ArrayList<Teacher> teacherList){
        
        this.teacherList = teacherList;
    }
    
    public ArrayList<Teacher> getTeacherList(){
        
        return teacherList;
    }
    
    public void setTeacherList(ArrayList<Teacher> teacherList){
        
        this.teacherList = teacherList;
    }
    
    public Integer getSize(){
        
        return teacherList.size();
    }
    
    public void show(){
        
        Iterator<Teacher> itr = teacherList.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next().getName());
        }
    }
}
