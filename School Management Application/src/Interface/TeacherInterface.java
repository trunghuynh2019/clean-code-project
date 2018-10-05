package Interface;

import Model.Teacher;
import java.util.List;

public interface TeacherInterface {
    
    public void save(Teacher teacher);
    public Teacher findByTeacherID(String teacherID);
    public List<Teacher> findBySchoolID(String schoolID);
    public List<Teacher> findByName(String name);
    public List<Teacher> findByAddress(String address);
    public Teacher findByPhoneNo(String phoneNo);
    public List<Teacher> findAll();
    public Integer getsize();
    public List<Teacher> deleteByTeacherID(String teacherID);
}
