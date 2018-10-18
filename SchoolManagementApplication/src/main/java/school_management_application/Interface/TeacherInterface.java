package school_management_application.Interface;

import java.util.List;
import school_management_application.Model.Teacher;

public interface TeacherInterface {
	
	public void save(Teacher teacher);
	public void setTeachers(List<Teacher> teachers);
	public List<Teacher> getTeachers();
    public Teacher findByTeacherID(Integer teacherID);
    public List<Teacher> findBySchoolID(String schoolID);
    public List<Teacher> findByName(String name);
    public Teacher findByIdentityCard(String identityCard);
    public List<Teacher> findByAddress(String address);
    public Teacher findByPhoneNo(String phoneNo);
    public List<Teacher> findAll();
    public Integer getsize();
    public Teacher deleteByTeacherID(Integer teacherID);
}
