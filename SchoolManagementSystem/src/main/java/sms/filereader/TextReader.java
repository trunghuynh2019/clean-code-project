package sms.filereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import sms.model.School;
import sms.model.Teacher;
import sms.repository.SchoolRepo;
import sms.repository.impl.SchoolRepoImpl;

public class TextReader implements FileReader {
	private String schoolFileName;
	private String teacherFileName;
	private SchoolRepo schoolRepo = new SchoolRepoImpl();

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

	public TextReader(String schoolFileName, String teacherFileName) {
		super();
		this.schoolFileName = schoolFileName;
		this.teacherFileName = teacherFileName;
	}

	@Override
	public boolean importSchoolFromFile(List<School> schools) {
		BufferedReader bufferedReader;
		InputStreamReader inputStreamReader;
		try {
			try {
				inputStreamReader = new InputStreamReader(
						this.getClass().getResourceAsStream("/file/" + schoolFileName));
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

	@Override
	public boolean importTeacherFromFile(List<School> schools, List<Teacher> teachers) {
		BufferedReader bufferedReader;
		InputStreamReader inputStreamReader;
		try {
			try {
				inputStreamReader = new InputStreamReader(
						this.getClass().getResourceAsStream("/file/" + teacherFileName));
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
				Optional<School> school = schoolRepo.findSchoolById(schools, teacher.getSchoolId());
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
