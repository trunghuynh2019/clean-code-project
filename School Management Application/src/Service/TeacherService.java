package Service;

import DTO.TeacherDto;
import Model.Teacher;
import Repository.TeacherRepository;
import java.util.List;

public class TeacherService {
    private TeacherRepository teachRepo;
    
    public TeacherService(){
        
        teachRepo = new TeacherRepository();
    }
    
    public Teacher signContractWithTeacher(TeacherDto teacherDto){
        Teacher teacher = teachRepo.findByTeacherID(teacherDto.getTeacherID());
        
        if(teacher != null){
            return null;
        }
        teacher.setTeacherID(teacherDto.getTeacherID());
        teacher.setSchoolID(teacherDto.getSchoolID());
        teacher.setName(teacherDto.getName());
        teacher.setAddress(teacherDto.getAddress());
        teacher.setPhoneNo(teacherDto.getPhoneNo());
        teachRepo.save(teacher);
        return teacher;
    }
    
    public Teacher updateTeacherInfor(TeacherDto teacherDto){
        Teacher teacher = teachRepo.findByTeacherID(teacherDto.getTeacherID());
        
        teacher.setTeacherID(teacherDto.getTeacherID());
        teacher.setSchoolID(teacherDto.getSchoolID());
        teacher.setName(teacherDto.getName());
        teacher.setAddress(teacherDto.getAddress());
        teacher.setPhoneNo(teacherDto.getPhoneNo());
        teachRepo.save(teacher);
        return teacher;
    }
    
    public List<Teacher> showTeacherList(){
        
        return teachRepo.findAll();
    }
    
    public Integer getNumberOfTeacher(){
        
        return teachRepo.getsize();
    }
    
    public List<Teacher> fireTeacher(String teacherID){
        
        return teachRepo.deleteByTeacherID(teacherID);
    }
}
