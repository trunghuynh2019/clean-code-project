package Repository;

import Interface.TeacherInterface;
import Model.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TeacherRepository implements TeacherInterface {
    private List<Teacher> teachers;
    
    public TeacherRepository (){
        
        teachers = new ArrayList<Teacher>();
    }
    
    @Override
    public void save(Teacher teacher) {
        
        teachers.add(teacher);
    }    
    @Override
    public Teacher findByID(Integer ID) {
        ListIterator<Teacher> itr = teachers.listIterator();
        Teacher teacher = new Teacher();
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getID().equals(ID)){
                break;
            }
        }
        return teacher;
    }

    @Override
    public List<Teacher> findByName(String name) {
        ListIterator<Teacher> itr = teachers.listIterator();
        List<Teacher> teacherDtos = new ArrayList<Teacher>();
        Teacher teacher = new Teacher();
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getName().toUpperCase().equals(name.toUpperCase())){
                teacherDtos.add(teacher);
            }
        }
        return teacherDtos;
    }

    @Override
    public List<Teacher> findByAddress(String address) {
        ListIterator<Teacher> itr = teachers.listIterator();
        List<Teacher> teacherDtos = new ArrayList<Teacher>();
        Teacher teacher = new Teacher();
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getAddress().toUpperCase().equals(address.toUpperCase())){
                teacherDtos.add(teacher);
            }
        }
        return teacherDtos;
    }

    @Override
    public Teacher findByPhoneNo(String phoneNo) {
        ListIterator<Teacher> itr = teachers.listIterator();
        Teacher teacher = new Teacher();
        
        while(itr.hasNext()) {
            teacher = itr.next();
            if(teacher.getPhoneNo().equals(phoneNo)){
                break;
            }
        }
        return teacher;
    }
}
