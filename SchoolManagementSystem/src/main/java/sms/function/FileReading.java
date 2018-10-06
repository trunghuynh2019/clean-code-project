package sms.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import sms.model.School;
import sms.model.Teacher;

public class FileReading {
	private String schoolFileName;
	private String teacherFileName;

	public String getSchoolFileName() {
		return schoolFileName;
	}

	public void setSchoolFileName(String schoolFileName) {
		this.schoolFileName = schoolFileName;
	}

	public String getTeacherFileName() {
		return teacherFileName;
	}

	public void setTeacherFileName(String teacherFileName) {
		this.teacherFileName = teacherFileName;
	}

	public FileReading(String schoolFileName, String teacherFileName) {
		super();
		this.schoolFileName = schoolFileName;
		this.teacherFileName = teacherFileName;
	}

	public boolean readSchoolFile(List<School> schools) {
		BufferedReader bufferedReader;
		try {
			File file = new File("resources/file/" + schoolFileName);
			FileReader reader = new FileReader(file);
			bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			bufferedReader.readLine();
			bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				String[] schoolData = line.substring(2).split(" ||| ");
				School school = new School(schoolData[0], schoolData[1], Integer.parseInt(schoolData[2]),
						schoolData[3]);
				schools.add(school);
			}
			reader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean readTeacherFile(List<School> schools) {
		BufferedReader bufferedReader;
		try {
			File file = new File("resources/file/" + teacherFileName);
			FileReader reader = new FileReader(file);
			bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			bufferedReader.readLine();
			bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				String[] teacherData = line.substring(2).split(" ||| ");
				Teacher teacher = new Teacher(Integer.parseInt(teacherData[0]), teacherData[1], teacherData[2]);
				Function function = new Function();
				School school = function.findSchoolById(schools, teacher.getSchoolId());
				if (school == null) {

				} else {
					school.addTeacher(teacher);
				}
			}

			reader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
}
