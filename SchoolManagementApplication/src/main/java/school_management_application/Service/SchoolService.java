package school_management_application.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import school_management_application.DTO.SchoolDto;
import school_management_application.Model.School;
import school_management_application.Repository.SchoolRepository;

public class SchoolService {
	private SchoolRepository schoolRepo;
	
	public SchoolService() {
		
		schoolRepo = new SchoolRepository();
	}
	
	public List<SchoolDto> importSchoolsDataByFileTXT(String fileName){
    	BufferedReader br = null;
    	String contentLine;
    	String[] data;
    	SchoolDto schoolDto;
    	List<SchoolDto> schoolDtos = new ArrayList<SchoolDto>();
    	
    	try {
    		br = new BufferedReader(new FileReader("src/main/resources/input/" + fileName));
    		br.readLine();
    		br.readLine();
    		while((contentLine = br.readLine()) != null) {
    			contentLine = contentLine.substring(2);
    			data = contentLine.split(" \\|\\|\\| ");
    			schoolDto = new SchoolDto(data[0],data[1],data[3],Integer.parseInt(data[2]));
    			if(openASchool(schoolDto) != null) {
    				schoolDtos.add(schoolDto);
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
    	return schoolDtos;
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

	public School closeASchool(String ID) {
		School school = schoolRepo.deleteByID(ID);
		
		if(school != null) {
			return school;
		}
		return null;
	}

	public Integer displayNumberOfSchool() {
		
		return schoolRepo.getsize();
	}
	
	public School findASchool(SchoolDto schoolDto) {
		School school = schoolRepo.findByID(schoolDto.getID());
	    
	    if(school == null){
	        return null;
	    }
	    return school;
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

	public List<School> showAllSchool(){
		
		return schoolRepo.findAll();
	}
}
