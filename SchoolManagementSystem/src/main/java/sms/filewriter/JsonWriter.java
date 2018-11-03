package sms.filewriter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import sms.model.School;
import sms.model.Teacher;
import sms.repository.TeacherRepo;
import sms.repository.impl.TeacherRepoImpl;

public class JsonWriter implements FileWriter {
	private String schoolFileName;
	private String teacherFileName;

	public JsonWriter(String schoolFileName, String teacherFileName) {
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
			java.io.FileWriter writer = new java.io.FileWriter("export/json/" + schoolFileName);
			Gson gson = new Gson();
			gson.toJson(schools, writer);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean exportTeachersToFile(List<School> schools) {
		try {
			java.io.FileWriter writer = new java.io.FileWriter("export/json/" + teacherFileName);
			Gson gson = new Gson();
			TeacherRepo tRepo = new TeacherRepoImpl();
			Optional<List<Teacher>> optional = tRepo.getAllTeachersFromSchoolList(schools);
			List<Teacher> teachers;
			if (optional.isPresent()) {
				teachers = optional.get();
			} else {
				teachers = null;
			}
			gson.toJson(teachers, writer);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
