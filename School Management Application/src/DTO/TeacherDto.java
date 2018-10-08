package DTO;

import java.util.ArrayList;
import java.util.List;

public class TeacherDto {
    
    private Integer teacherID;
    private String schoolID;
    private String name;
    private String identityCard;
    private List<String> phoneNo;
    private String address;
    
    public TeacherDto() {
        
        teacherID = null;
        schoolID = null;
        name = null;
        identityCard = null;
        phoneNo = new ArrayList<String>();
        address = null;
    }

    public TeacherDto(Integer teacherID, String schoolID, String name, String identityCard, List<String> phoneNo, String address) {
        
        this.teacherID = teacherID;
        this.schoolID = schoolID;
        this.name = name;
        this.identityCard = identityCard;
        this.phoneNo = phoneNo;
        this.address = address;
    }
    
    public Integer getTeacherID(){
        
        return teacherID;
    }
    
    public void setTeacherID(Integer teacherID){
        
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
    
    public String getIdentityCard(){
        
        return identityCard;
    }
    
    public void setIdentityCard(String identityCard){
        
        this.identityCard = identityCard;
    }
    
    public List<String> getPhoneNo(){
        
        return phoneNo;
    }
    
    public void setPhoneNo(List<String> phoneNo){
        
        this.phoneNo = phoneNo;
    }
    
    public String getAddress(){

        return address;
    }

    public void setAddress(String address){

        this.address = address;
    }
}
