package School_Management_Application;

public class Teacher {
    private String name;
    private String address;
    
    public Teacher() {

            this.name = "";
            this.address = "";
    }

    public Teacher (String name, String address) {

            this.name = name;
            this.address = address;
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
}
