package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Teachers {
    private ArrayList<Teacher> teachers;
    
    public Teachers(){
        
        teachers = new ArrayList<Teacher>();
    }
    
    public Teachers(ArrayList<Teacher> teachers){
        
        this.teachers = teachers;
    }
    
    public ArrayList<Teacher> getTeachers(){
        
        return teachers;
    }
    
    public void setTeachers(ArrayList<Teacher> teachers){
        
        this.teachers = teachers;
    }
    
    public Integer getSize(){
        
        return teachers.size();
    }
    
    public void show(){
        
        Iterator<Teacher> itr = teachers.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next().getName());
        }
    }
}
