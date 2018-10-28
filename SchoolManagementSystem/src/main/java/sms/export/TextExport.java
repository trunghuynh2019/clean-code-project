package sms.export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import sms.model.School;
import sms.model.Teacher;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.repository.impl.SchoolRepoImpl;
import sms.repository.impl.TeacherRepoImpl;

public class TextExport implements FileExport {
	private String schoolFileName;
	private String teacherFileName;
	private BufferedWriter writer;
	private SchoolRepo schoolRepo = new SchoolRepoImpl();
	private TeacherRepo teacherRepo = new TeacherRepoImpl();

	public TextExport(String schoolFileName, String teacherFileName) {
		super();
		this.schoolFileName = schoolFileName;
		this.teacherFileName = teacherFileName;
	}

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

	public boolean readSchoolFromTextFile(List<School> schools) {
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

	public boolean readTeacherTextFileFile(List<School> schools) {
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

	@Override
	public boolean exportSchoolsToFile(List<School> schools) {
		try {
			FileOutputStream file = new FileOutputStream("export/text/" + schoolFileName);
			writer = new BufferedWriter(new OutputStreamWriter(file));
			writer.write("Danh sach truong");
			writer.newLine();
			writer.newLine();

			Optional<List<String>> schoolsData = schoolRepo.getStringFromSchoolList(schools);
			if (!schoolsData.isPresent()) {
				return true;
			}
			for (String string : schoolsData.get()) {
				writer.write(string);
				writer.newLine();
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean exportTeachersToFile(List<School> schools) {
		try {
			FileOutputStream file = new FileOutputStream("export/text/" + teacherFileName);
			writer = new BufferedWriter(new OutputStreamWriter(file));
			writer.write("Danh sach giao vien");
			writer.newLine();
			writer.newLine();

			Optional<List<String>> teachersData = teacherRepo.getStringFromTeacherList(schools);
			if (!teachersData.isPresent()) {
				return true;
			}
			for (String string : teachersData.get()) {
				writer.write(string + "\n");
				writer.newLine();
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
