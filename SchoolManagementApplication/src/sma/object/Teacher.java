/*
 * Title
 * 
 * @author Huy
 * @date Sep 26, 2018
 * @version 1.0
 */
package sma.object;

public class Teacher {
	private String name;
	private String address;
	private String workingSchool;

	public Teacher() {
		super();
	}

	public Teacher(String name, String address, String school) {
		super();
		this.name = name;
		this.address = address;
		this.workingSchool = school;
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
		return "Teacher's name: " + name + "\n"
				+ "Address " + address + "\n"
				+ "Working School: " + workingSchool + "\n";
	}
	
	
}
