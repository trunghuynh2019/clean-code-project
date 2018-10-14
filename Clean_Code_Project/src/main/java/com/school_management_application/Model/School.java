package Model;

public class School {  
    private Integer ID;
    private String name;
    private String address;
    private Integer countStudent;
    private Integer countTeacher;
    private Teachers teacherList;

    public School() {
        ID++;
        name = "";
        address = "";
        countStudent = 0;
        countTeacher = 0;
        teacherList = new Teachers();
    }

    public School(String name, String address, Integer countStudent, Teachers teacherList) {
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

    public Teachers getTeacherList(){

        return teacherList;
    }

    public void setTeacherList(Teachers teacherList){

        this.teacherList = teacherList;
    }
}
