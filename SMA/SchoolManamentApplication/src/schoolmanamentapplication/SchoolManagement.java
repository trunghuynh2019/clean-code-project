/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanamentapplication;

import java.util.Vector;

/**
 *
 * @author Admin
 */
public class SchoolManagement {
    Vector<School> listSchool= new Vector<School>();
    void addSchool(School School){
        listSchool.add(School);
    }
    
    void displaySchool(){
        for (int i = 0; i < listSchool.size(); i++) {
            listSchool.get(i).output();
        }
    }
    
    School findSchoolByName(String name){
        for (int i = 0; i < listSchool.size(); i++) {
            if(listSchool.get(i).name.equals(name))
                return listSchool.get(i);
            }
        return null;
    }
  
    int findSchoolByName2(String name){
        for (int i = 0; i < listSchool.size(); i++) {
            if(listSchool.get(i).name.equals(name))
                return i; 
            }
        return -1;
    }
    
    void removeSchool(String id){
        int position = findSchoolByName2(id);
            if (position==-1){
              System.out.println("Không có trường này trong hệ thống");
              return;
              }
            listSchool.remove(position);
    }
    
}
