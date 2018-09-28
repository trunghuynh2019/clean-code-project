package sms.model;

public class Teacher {
	private String name;
	private String address;

	public Teacher(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Teacher(String name) {
		super();
		this.name = name;
	}

	public Teacher() {
		super();
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

	@Override
	public String toString() {
		return "Teacher name: " + this.name + "\nAddress: " + this.address;
	}
}
