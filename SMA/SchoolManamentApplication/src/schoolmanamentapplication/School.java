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
public class School {
    String name;
    String address;
    int telephoneNumber;
    int numberOfStudent;
    Vector<Teacher> listTeacher= new Vector<Teacher>();

    public School(String name, String address, int telephoneNumber, int numberOfStudent) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.numberOfStudent = numberOfStudent;
    }

    School() {
    }
    
    void addTeacher(Teacher teacher){
        listTeacher.add(teacher);
    }
    
    void displayTeacher(){
        for (int i = 0; i < listTeacher.size(); i++) {
            listTeacher.get(i).output();
        }
    }
    
    Teacher findTeacherById(String id){
        for (int i = 0; i < listTeacher.size(); i++) {
            if(listTeacher.get(i).getTeacherId().equals(id))
                return listTeacher.get(i);
            }
        return null;
    }
  
    int findTeacherById2(String id){
        for (int i = 0; i < listTeacher.size(); i++) {
            if(listTeacher.get(i).getTeacherId().equals(id))
                return i; 
            }
        return -1;
    }
    
    void removeTeacher(String id){
    int position=findTeacherById2(id);
        if (position==-1){
          System.out.println("Không có giáo viên này trong trường");
          return;
          }
        listTeacher.remove(position);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Vector<Teacher> getListTeacher() {
        return listTeacher;
    }

    public void setListTeacher(Vector<Teacher> v) {
        this.listTeacher = v;
    }

    @Override
    public String toString() {
        return "School{" + "name=" + name + ", address=" + address + ", telephoneNumber=" + telephoneNumber + ", numberOfStudent=" + numberOfStudent + ", listTeacher=" + listTeacher + '}';
    }
    
    public void output(){
        System.out.println("");
        System.out.println("Tên trường: "+ name);
        System.out.println("Địa chỉ: "+ address);
        System.out.println("Số điện thoại: "+ telephoneNumber);
        System.out.println("Số lượng sinh viên: "+ numberOfStudent);
        System.out.println("Danh sách giáo viên: "+ listTeacher.toString());
    }
}
