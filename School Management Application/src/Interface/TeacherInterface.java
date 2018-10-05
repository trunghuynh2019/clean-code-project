package Interface;

import Model.Teacher;
import java.util.List;

public interface TeacherInterface {
    
    public void save(Teacher teacher);
    public Teacher findByTeacherID(String ID);
    public List<Teacher> findBySchoolID(String ID);
    public List<Teacher> findByName(String name);
    public List<Teacher> findByAddress(String address);
    public Teacher findByPhoneNo(String phoneNo);
}
