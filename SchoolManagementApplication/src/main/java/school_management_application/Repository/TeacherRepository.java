package school_management_application.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import school_management_application.Interface.TeacherInterface;
import school_management_application.Model.Teacher;

public class TeacherRepository implements TeacherInterface {
	private List<Teacher> teachers;
	private Integer teacherID = 1;
    
    public TeacherRepository (){
        
        teachers = new ArrayList<Teacher>();
    }
    
    public void save(Teacher teacher) {
        Teacher teach_check = null;
        
        if(teacher.getTeacherID() == null) {
        	teacher.setTeacherID(teacherID);
        }
        else {
        	teach_check = findByTeacherID(teacher.getTeacherID());
        }
        
        if(teach_check == null){
            teachers.add(teacher);
        }
        else {
            teachers.remove(teach_check);
            teachers.add(teacher);
        }
        teacherID++;        
    }
    
    public void setTeachers(List<Teacher> teachers) {
    	
    	this.teachers = teachers;
    }
    
    public Teacher findByTeacherID(Integer teacherID) {
        ListIterator<Teacher> itr = teachers.listIterator();
        Teacher teacher;
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getTeacherID().equals(teacherID)){
                return teacher;
            }
        }
        return null;
    }

    public List<Teacher> findBySchoolID(String schoolID) {
        ListIterator<Teacher> itr = teachers.listIterator();
        List<Teacher> teacherDtos = new ArrayList<Teacher>();
        Teacher teacher;
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getSchoolID().equals(schoolID)){
                teacherDtos.add(teacher);
            }
        }
        return teacherDtos;
    }
    
    public List<Teacher> findByName(String name) {
        ListIterator<Teacher> itr = teachers.listIterator();
        List<Teacher> teacherDtos = new ArrayList<Teacher>();
        Teacher teacher;
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getName().toUpperCase().equals(name.toUpperCase())){
                teacherDtos.add(teacher);
            }
        }
        return teacherDtos;
    }
    
    public Teacher findByIdentityCard(String identityCard){
        ListIterator<Teacher> itr = teachers.listIterator();
        Teacher teacher;
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.isHaveIdentityCard(identityCard)){
                return teacher;
            }
        }
        return null;
    }
    
    public List<Teacher> findByAddress(String address) {
        ListIterator<Teacher> itr = teachers.listIterator();
        List<Teacher> teacherDtos = new ArrayList<Teacher>();
        Teacher teacher;
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getAddress().toUpperCase().equals(address.toUpperCase())){
                teacherDtos.add(teacher);
            }
        }
        return teacherDtos;
    }

    public Teacher findByPhoneNo(String phoneNo) {
        ListIterator<Teacher> itr = teachers.listIterator();
        Teacher teacher;
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.isHavePhoneNo(phoneNo)){
                return teacher;
            }
        }
        return null;
    }
    
    public List<Teacher> findAll() {
        
        return teachers;
    }
    
    public Integer getsize(){
        
        return teachers.size();
    }
    
    public Teacher deleteByTeacherID(Integer teacherID) {
        ListIterator<Teacher> itr = teachers.listIterator();
        Teacher teacher;
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getTeacherID().equals(teacherID)){
                teachers.remove(teacher);
                return teacher;
            }
        }
        return null;
    }
}
