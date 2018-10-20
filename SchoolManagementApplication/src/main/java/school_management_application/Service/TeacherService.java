package school_management_application.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import school_management_application.DTO.TeacherDto;
import school_management_application.Model.Teacher;
import school_management_application.Repository.TeacherRepository;

public class TeacherService {
	private TeacherRepository teachRepo;
    
    public TeacherService(){
        
        teachRepo = new TeacherRepository();
    }
    
    public List<TeacherDto> importTeachersDataByFileTXT(String fileName){
    	BufferedReader br = null;
    	String contentLine;
    	String[] data;
    	TeacherDto teacherDto;
    	List<TeacherDto> teacherDtos = new ArrayList<TeacherDto>();
    	
    	try {
    		br = new BufferedReader(new FileReader("src/main/resources/input/" + fileName));
    		br.readLine();
    		br.readLine();
    		while((contentLine = br.readLine()) != null) {
    			contentLine = contentLine.substring(2);
    			data = contentLine.split(" \\|\\|\\| ");
    			teacherDto = new TeacherDto(null,data[2],data[1],data[0],null,null);
    			if(signContractWithTeacher(teacherDto) != null) {
    				teacherDtos.add(teacherDto);
    			}
    		}
    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    	finally {
    		try {
    			if(br != null) {
    				br.close();
    			}
    		}
    		catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	return teacherDtos;
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
    
    public Teacher fireTeacher(Integer teacherID){
	    Teacher teacher = teachRepo.deleteByTeacherID(teacherID);
	    
	    if(teacher != null) {
	    	return teacher;
	    }
	    return null;
	}
	public Integer displayNumberOfTeacherInASchool(String schoolID) {
    	List<Teacher> teachers = findTeacherBySchoolID(schoolID);
    	
    	return teachers.size();
    }
    
    public Integer displayNumberOfAllTeacher(){
        
        return teachRepo.getsize();
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
	public Teacher findTeacherByTeacherID(Integer teacherID) {
    	
    	return teachRepo.findByTeacherID(teacherID);
    }
    
    public List<Teacher> findTeacherBySchoolID(String schoolID){
    	
    	return teachRepo.findBySchoolID(schoolID);
    }
    
    public List<Teacher> findTeacherByTeacherName(String name){
    	
    	return teachRepo.findByName(name);
    }
    
    public Teacher findTeacherByIdentityCard(String identityCard) {
    	
    	return teachRepo.findByIdentityCard(identityCard);
    }
    
    public List<Teacher> findTeacherByAddress(String address){
    	
    	return teachRepo.findByAddress(address);
    }
	public List<Teacher> showAllTeacher(){
	    
	    return teachRepo.findAll();
	}
}
