/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanamentapplication;

/**
 *
 * @author Admin
 */
public class Teacher {
    private String teacherId;
    String name;
    String workingSchool;

    public Teacher(String teacherId, String name, String workingSchool) {
        this.teacherId = teacherId;
        this.name = name;
        this.workingSchool = workingSchool;
    }

    
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkingSchool() {
        return workingSchool;
    }

    public void setWorkingSchool(String workingSchool) {
        this.workingSchool = workingSchool;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teacherId=" + teacherId + ", name=" + name + ", workingSchool=" + workingSchool + '}';
    }
    
    public void output(){
        System.out.println("");
        System.out.println("Mã giáo viên: "+ teacherId);
        System.out.println("Tên giáo viên: "+ name);
        System.out.println("Nơi làm việc: "+ workingSchool);
    }
    
}
