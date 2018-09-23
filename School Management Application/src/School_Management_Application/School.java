package School_Management_Application;

public class School {
	private String name;

	public School() {
		this.name = "";
	}
	
	public School(String name) {
		
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
