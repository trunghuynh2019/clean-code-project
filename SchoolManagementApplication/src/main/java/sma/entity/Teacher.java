package sma.entity;


public class Teacher {
	
	String id;
	
	String name;
	String schoolId;
	String address;
	String phone;
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Teacher(String id, String name, String schoolId) {
		super();
		this.id = id;
		this.name = name;
		this.schoolId = schoolId;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
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
	
	@Override
	public boolean equals(Object obj) {
	    return obj instanceof Teacher &&
	        getName().equals(((Teacher) obj).getName());
	}
	
	public Teacher(String id, String name, String address, String phone, String schoolId) {
		this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.schoolId = schoolId;
    }


}
