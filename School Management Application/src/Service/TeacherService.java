package Service;

import DTO.TeacherDto;
import Model.Teacher;
import Repository.TeacherRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherService {
    private TeacherRepository teachRepo;
    
    public TeacherService(){
        
        teachRepo = new TeacherRepository();
    }
    
    public Teacher signContractWithTeacher(TeacherDto teacherDto){
        List<Teacher> teachers = teachRepo.findByName(teacherDto.getName());
        Teacher teacher = new Teacher();
        
        if(teachers != null){
            return null;
        }
        teacher.setName(teacherDto.getName());
        teacher.setAddress(teacherDto.getAddress());
        teacher.setPhoneNo(teacherDto.getPhoneNo());
        teachRepo.save(teacher);
        return teacher;
    }
}
