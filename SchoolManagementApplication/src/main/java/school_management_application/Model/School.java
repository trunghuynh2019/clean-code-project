package school_management_application.Model;

public class School {
	private String ID;
    private String name;
    private String address;
    private Integer countStudent;

    public School() {
        ID = null;
        name = null;
        address = null;
        countStudent = 0;
    }

    public School(String ID, String name, String address, Integer countStudent) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.countStudent = countStudent;
    }

    public String getID(){

        return ID;
    }
    
    public void setID(String ID) {
    	
    	this.ID = ID;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAddress(){

        return address;
    }

    public void setAddress(String address){

        this.address = address;
    }

    public Integer getCountStudent(){

        return countStudent;
    }

    public void setCountStudent(Integer countStudent){

        this.countStudent = countStudent;
    }
}
