package entity;


public class school {
	
	int id;
	
	String name;
	String address;
	String phone;
	
	public school() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	@Override
	public int hashCode() {
	    return getName().hashCode();
	}**/

	@Override
	public boolean equals(Object obj) {
	    return obj instanceof school &&
	        getName().equals(((school) obj).getName());
	}
	
	public school(int id, String name, String address, String phone) {
		this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


}
