package Model;

public class Teacher {
    private String teacherID;
    private String schoolID;
    private String name;
    private String address;
    private String phoneNo;
    
    public Teacher() {
            
        teacherID = "";
        schoolID = "";
        name = "";
        address = "";
        phoneNo = "";
    }

    public Teacher (String teacherID, String schoolID, String name, String address, String phoneNo) {
        
        this.teacherID = teacherID;
        this.schoolID = schoolID;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }
    
    public String getTeacherID(){
        
        return teacherID;
    }
    
    public void setTeacherID(String teacherID){
        
        this.teacherID = teacherID;
    }
    
    public String getSchoolID(){
        
        return schoolID;
    }
    
    public void setSchoolID(String schoolID){
        
        this.schoolID = schoolID;
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