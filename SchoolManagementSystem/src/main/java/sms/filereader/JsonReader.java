package sms.filereader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import sms.model.School;
import sms.model.Teacher;
import sms.repository.SchoolRepo;
import sms.repository.impl.SchoolRepoImpl;

public class JsonReader implements FileReader {
	private String schoolFileName;
	private String teacherFileName;

	public JsonReader(String schoolFileName, String teacherFileName) {
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
	public boolean importSchoolFromFile(List<School> schools) {
		Gson gson = new Gson();
		try {
			Reader reader = new java.io.FileReader("export/json/" + schoolFileName);
			JsonElement json = gson.fromJson(reader, JsonElement.class);
			String jsonInString = gson.toJson(json);
			schools = gson.fromJson(jsonInString, new TypeToken<List<School>>() {
			}.getType());
			return true;
		} catch (IOException e) {
			System.out.println("You don't have truong.json file!");
			return false;
		}
	}

	@Override
	public boolean importTeacherFromFile(List<School> schools, List<Teacher> teachers) {
		Gson gson = new Gson();
		teachers = new ArrayList<>();
		try {
			Reader reader = new java.io.FileReader("export/json/" + teacherFileName);
			JsonElement json = gson.fromJson(reader, JsonElement.class);
			String jsonInString = gson.toJson(json);
			teachers = gson.fromJson(jsonInString, new TypeToken<List<Teacher>>() {
			}.getType());

			SchoolRepo sRepo = new SchoolRepoImpl();
			for (Teacher teacher : teachers) {
				Optional<School> curr = sRepo.findSchoolById(schools, teacher.getSchoolId());
				if (curr.isPresent()) {
					curr.get().addTeacher(teacher);
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println("You don't have giaovien.json file!");
			return false;
		}
	}

}
