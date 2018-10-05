package Interface;

import Model.Teacher;
import java.util.List;

public interface TeacherInterface {
    
    public Teacher findByID(Integer ID);
    public List<Teacher> findByName(String name);
    public List<Teacher> findByAddress(String address);
    public Teacher findByPhoneNo(String phoneNo);
}
