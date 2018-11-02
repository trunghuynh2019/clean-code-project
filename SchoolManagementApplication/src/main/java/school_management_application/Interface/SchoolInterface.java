package school_management_application.Interface;

import java.util.List;
import school_management_application.Model.School;

public interface SchoolInterface {
	
	public void save(School school);
	public List<School> setSchools(List<School> schools);
	public School findByID(String ID);
    public List<School> findByName(String name);
    public School findByAddress(String address);
    public List<School> findAll();
    public Integer getsize();
    public School deleteByID(String ID);
}
