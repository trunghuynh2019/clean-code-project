package school_management_application.Service;

import java.util.List;
import school_management_application.DTO.SchoolDto;
import school_management_application.Model.School;
import school_management_application.Repository.SchoolRepository;

public class SchoolService {
	private SchoolRepository schoolRepo;
	
	public SchoolService() {
		
		schoolRepo = new SchoolRepository();
	}
	
	public School findASchool(SchoolDto schoolDto) {
		School school = schoolRepo.findByID(schoolDto.getID());
        
        if(school == null){
            return null;
        }
        return school;
	}
	
	public School openASchool(SchoolDto schoolDto) {
		School school = findASchool(schoolDto);
        
        if(school != null){
            return null;
        }
        school = new School();
        school.setID(schoolDto.getID());
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setCountStudent(school.getCountStudent());
        schoolRepo.save(school);
        return school;
	}
	
	public School updateSchoolInfor(SchoolDto schoolDto) {
		School school = schoolRepo.findByID(schoolDto.getID());
        
        if(school == null){
            return null;
        }
        school.setName(schoolDto.getName());
        school.setAddress(schoolDto.getAddress());
        school.setCountStudent(schoolDto.getCountStudent());
        schoolRepo.save(school);
        return school;
	}
	
	public Integer displayNumberOfSchool() {
		
		return schoolRepo.getsize();
	}
	
	public List<School> showAllSchool(){
		
		return schoolRepo.findAll();
	}
	
	public School closeASchool(String ID) {
		School school = schoolRepo.deleteByID(ID);
		
		if(school != null) {
			return school;
		}
		return null;
	}
	
	public School findSchoolByID(String ID) {
		
		return schoolRepo.findByID(ID);
	}
	
	public List<School> findSchoolByName(String name){
		
		return schoolRepo.findByName(name);
	}
	
	public School findSchoolByAddress(String address) {
		
		return schoolRepo.findByAddress(address);
	}
}
