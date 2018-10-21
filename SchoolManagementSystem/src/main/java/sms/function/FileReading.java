package sms.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import sms.model.School;
import sms.model.Teacher;
import sms.repo.Repository;
import sms.repoInterface.RepositoryITF;
import sms.functionInterface.FileReadingITF;

public class FileReading implements FileReadingITF {
	private String schoolFileName;
	private String teacherFileName;
	private RepositoryITF repo = new Repository();

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
		InputStreamReader inputStreamReader;
		try {
			try {
				inputStreamReader = new InputStreamReader(this.getClass().getResourceAsStream("/file/" + schoolFileName));
			} catch (NullPointerException e) {
				return false;
			}
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			bufferedReader.readLine();
			bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				String[] schoolData = line.substring(2).split(Pattern.quote(" ||| "));
				School school = new School(schoolData[0], schoolData[1], Integer.parseInt(schoolData[2]),
						schoolData[3]);
				schools.add(school);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean readTeacherFile(List<School> schools) {
		BufferedReader bufferedReader;
		InputStreamReader inputStreamReader;
		try {
			try {
				inputStreamReader = new InputStreamReader(this.getClass().getResourceAsStream("/file/" + teacherFileName));
			} catch (NullPointerException e) {
				return false;
			}
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			bufferedReader.readLine();
			bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				String[] teacherData = line.substring(2).split(Pattern.quote(" ||| "));
				Teacher teacher = new Teacher(Integer.parseInt(teacherData[0]), teacherData[1], teacherData[2]);
				Optional<School> school = repo.findSchoolById(schools, teacher.getSchoolId());
				if (school.isPresent()) {
					school.get().addTeacher(teacher);
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
}
