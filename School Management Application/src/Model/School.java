package Model;

public class School {  
    private Integer ID = 0;
    private String name;
    private String address;
    private Integer countStudent;
    private Integer countTeacher;
    private TeacherList teacherList;

    public School() {
        ID++;
        name = "";
        address = "";
        countStudent = 0;
        countTeacher = 0;
        teacherList = new TeacherList();
    }

    public School(String name, String address, Integer countStudent, TeacherList teacherList) {
        ID++;
        this.name = name;
        this.address = address;
        this.countStudent = countStudent;
        this.teacherList = teacherList;
        this.countTeacher = teacherList.getSize();
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

    public Integer getCountStudent(){

        return countStudent;
    }

    public void setCountStudent(Integer countStudent){

        this.countStudent = countStudent;
    }

    public Integer getCountTeacher(){

        return countTeacher;
    }

    public TeacherList getTeacherList(){

        return teacherList;
    }

    public void setTeacherList(TeacherList teacherList){

        this.teacherList = teacherList;
    }
    
    public void show(){
        
        System.out.println(name + "\n" +
            "ID: " + ID + "\n" +
            "Address: " + address + "\n" +
            "Nuber of Student: " + countStudent + "\n" +
            "Number of Teacher: " + countTeacher + "\n" +
            "List of teacher:");
        teacherList.show();
    }
}
