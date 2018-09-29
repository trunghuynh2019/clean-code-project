package Model;

public class Teacher {
    private Integer ID = 0;
    private String name;
    private String address;
    private String phoneNo;
    
    public Teacher() {
            
        ID++;
        name = "";
        address = "";
        phoneNo = "";
    }

    public Teacher (String name, String address, String phoneNo) {
        
        ID++;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }
    
    public Integer getID(){
        
        return ID;
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
    
    public String getPhoneNo(){
        
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo){
        
        this.phoneNo = phoneNo;
    }
}