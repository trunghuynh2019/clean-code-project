/*
 * Title
 * 
 * @author Huy
 * @date Sep 26, 2018
 * @version 1.0
 */
package model;

public class Student {
	private String name;
	private String address;

	public Student() {
		super();
	}

	public Student(String name, String address) {
		super();
		this.name = name;
		this.address = address;
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
		return "Student's name: " + name + "\n"
				+ "Address " + address + "\n";
	}
	
}
