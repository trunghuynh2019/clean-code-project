package sms.filewriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;
import sms.model.School;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.repository.impl.SchoolRepoImpl;
import sms.repository.impl.TeacherRepoImpl;

public class TextWriter implements FileWriter {
	private String schoolFileName;
	private String teacherFileName;
	private BufferedWriter writer;
	private SchoolRepo schoolRepo = new SchoolRepoImpl();
	private TeacherRepo teacherRepo = new TeacherRepoImpl();

	public TextWriter(String schoolFileName, String teacherFileName) {
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
