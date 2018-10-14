package Service;

import DTO.TeacherDto;
import Model.Teacher;
import Repository.TeacherRepository;
import java.util.List;
import java.util.ListIterator;

public class TeacherService {
    private TeacherRepository teachRepo;
    
    public TeacherService(){
        
        teachRepo = new TeacherRepository();
    }
    public Teacher findTeacher(TeacherDto teacherDto){
        List<Teacher> teach_name = teachRepo.findByName(teacherDto.getName());
        Teacher teacher;
        
        if(teach_name == null){
            return null;
        }
        ListIterator<Teacher> itr = teach_name.listIterator();
        
        while(itr.hasNext()){
            teacher = itr.next();
            if(teacher.getAddress().equals(teacherDto.getAddress()) && teacher.getSchoolID().equals(teacherDto.getSchoolID())){
                return teacher;
            }
        }
        return null;
    }
    
    public Teacher signContractWithTeacher(TeacherDto teacherDto){
        Teacher teacher = findTeacher(teacherDto);
        
        if(teacher != null){
            return null;
        }
        teacher = new Teacher();
        teacher.setSchoolID(teacherDto.getSchoolID());
        teacher.setName(teacherDto.getName());
        teacher.setIdentityCard(teacherDto.getIdentityCard());
        teacher.setPhoneNo(teacherDto.getPhoneNo());
        teacher.setAddress(teacherDto.getAddress());
        teachRepo.save(teacher);
        return teacher;
    }
    
    public Teacher updateTeacherInfor(TeacherDto teacherDto){
        Teacher teacher;
        
        if(teacherDto.getTeacherID() == null){
            teacher = findTeacher(teacherDto);
        }
        else {
            teacher = teachRepo.findByTeacherID(teacherDto.getTeacherID());
        }
        teacher.setSchoolID(teacherDto.getSchoolID());
        teacher.setName(teacherDto.getName());
        if(!teacher.isHaveIdentityCard(teacherDto.getIdentityCard())){
            teacher.addIdentityCard(teacherDto.getIdentityCard());
        }
        teacher.setPhoneNo(teacherDto.getPhoneNo());
        teacher.setAddress(teacherDto.getAddress());
        teachRepo.save(teacher);
        return teacher;
    }
    
    public List<Teacher> showTeacherList(){
        
        return teachRepo.findAll();
    }
    
    public Integer getNumberOfTeacher(){
        
        return teachRepo.getsize();
    }
    
    public Teacher fireTeacher(Integer teacherID){
        
        return teachRepo.deleteByTeacherID(teacherID);
    }
}
