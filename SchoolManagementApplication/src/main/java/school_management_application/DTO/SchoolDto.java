package school_management_application.DTO;

public class SchoolDto {
	private String name;
    private String address;
    private Integer countStudent;
    
    public SchoolDto() {
            name = "";
            address = "";
            countStudent = 0;
    }
	
    public SchoolDto(String name, String address, Integer countStudent) {
        this.name = name;
        this.address = address;
        this.countStudent = countStudent;
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
