package school_management_application.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import school_management_application.Interface.SchoolInterface;
import school_management_application.Model.School;

public class SchoolRepository implements SchoolInterface {
	private List<School> schools;	
	
	public SchoolRepository() {
		
		schools = new ArrayList<School>();
	}
	
	public void save(School school) {
		School sch_check = findByID(school.getID());
		
        if(sch_check == null){
            schools.add(school);
        }
        else {
            schools.remove(sch_check);
            schools.add(school);
        }
		
	}
	
	public List<School> setSchools(List<School> schools) {
		
		this.schools = schools;
		return this.schools;
	}

	public School findByID(String ID) {
		ListIterator<School> itr = schools.listIterator();
        School school;
        
        while(itr.hasNext()) {
            school = itr.next();
            if(school.getID().equals(ID)){
                return school;
            }
        }
        return null;
	}

	public List<School> findByName(String name) {
		ListIterator<School> itr = schools.listIterator();
        List<School> schoolDtos = new ArrayList<School>();
        School school;
        
        while(itr.hasNext()) {
            school = itr.next();
            if(school.getName().toUpperCase().equals(name.toUpperCase())){
                schoolDtos.add(school);
            }
        }
        return schoolDtos;
	}

	public School findByAddress(String address) {
		ListIterator<School> itr = schools.listIterator();
        School school;
        
        while(itr.hasNext()) {
            school = itr.next();
            if(school.getAddress().toUpperCase().equals(address.toUpperCase())){
                return school;
            }
        }
        return null;
	}

	public List<School> findAll() {
		
		return schools;
	}

	public Integer getsize() {
		
		return schools.size();
	}

	public School deleteByID(String ID) {
		ListIterator<School> itr = schools.listIterator();
        School school;
        
        while(itr.hasNext()) {
            school = itr.next();
            if(school.getID().equals(ID)){
                schools.remove(school);
                return school;
            }
        }
        return null;
	}
}
