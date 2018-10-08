package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Teacher {
    
    private Integer teacherID = 0;
    private String schoolID;
    private String name;
    private List<String> identityCard;
    private List<String> phoneNo;
    private String address;
    
    public Teacher() {
        
        teacherID++;
        schoolID = "";
        name = "";
        
        identityCard = new ArrayList<String>();
        phoneNo = new ArrayList<String>();
        address = "";
    }

    public Teacher (String schoolID, String name, List<String> identityCard, List<String> phoneNo, String address) {
        
        teacherID++;
        this.schoolID = schoolID;
        this.name = name;
        this.identityCard = identityCard;
        this.phoneNo = phoneNo;
        this.address = address;
    }
    
    public Integer getTeacherID(){
        
        return teacherID;
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
    
    public List<String> getIdentityCard(){
        
        return identityCard;
    }
    
    public void setIdentityCard(String identityCard){
        
        this.identityCard.add(identityCard);
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
    
    public void addIdentityCard(String identityCard){
        
        this.identityCard.add(identityCard);
    }
    
    public String deleteIdentityCard(String identityCard){
        ListIterator<String> itr = this.identityCard.listIterator();
        String idCardDto;
        
        while(itr.hasNext()){
            idCardDto = itr.next();
            if(idCardDto.equals(identityCard)){
                this.identityCard.remove(idCardDto);
                return idCardDto;
            }
        }
        return null;
    }
    
    public boolean isHaveIdentityCard(String identityCard){
        ListIterator<String> itr = this.identityCard.listIterator();
        
        while(itr.hasNext()){
            if(itr.next().equals(identityCard)){
                return true;
            }
        }
        return false;
    }
    
    public void addPhoneNo(String phoneNo){
        
        this.phoneNo.add(phoneNo);
    }
        
    public String deletePhoneNo(String phoneNo){
        ListIterator<String> itr = this.phoneNo.listIterator();
        String phoneNoDto;
        
        while(itr.hasNext()){
            phoneNoDto = itr.next();
            if(phoneNoDto.equals(phoneNo)){
                this.phoneNo.remove(phoneNoDto);
                return phoneNoDto;
            }
        }
        return null;
    }
    
    public boolean isHavePhoneNo(String phoneNo){
        ListIterator<String> itr = this.phoneNo.listIterator();
        
        while(itr.hasNext()){
            if(itr.next().equals(phoneNo)){
                return true;
            }
        }
        return false;
    }
}