package sms.model;

public class Teacher {
	private String name, address;
	private int phone;

	public Teacher() {
		super();
	}

	public Teacher(int phone, String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "- "+name+"\n"
			+  "- "+address+"\n"
		    +  "- "+phone;
	}
	
	
}
