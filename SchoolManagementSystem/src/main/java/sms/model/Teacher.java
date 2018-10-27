package sms.model;

public class Teacher {
	private int id;
	private String name;
	private String schoolId;

	public Teacher() {
		super();
	}

	public Teacher(int id) {
		super();
		this.id = id;
	}

	public Teacher(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Teacher(String schoolId) {
		super();
		this.schoolId = schoolId;
	}

	public Teacher(int id, String name, String schoolId) {
		super();
		this.id = id;
		this.name = name;
		this.schoolId = schoolId;
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

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@Override
	public String toString() {
		return "Teacher name: " + this.name + "\nSchool Id: " + this.schoolId;
	}
	
	public enum TeacherCells {
		ID(0), NAME(1), SCHOOL_ID(2);
		private final int value;
		private TeacherCells(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
}
