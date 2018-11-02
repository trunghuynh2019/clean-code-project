package sms.file;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sms.model.School;
import sms.model.Teacher;
import sms.repository.impl.SchoolRepoImpl;

public class JsonExport {
	public boolean importSchoolFromJson(List<School> schools, String schoolJson) {
		schools = new Gson().fromJson(schoolJson, new TypeToken<List<School>>() {
		}.getType());
		return true;
	}

	public boolean importTeacherFromJson(List<School> schools, String teacherJson) {
		List<Teacher> teachers = new Gson().fromJson(teacherJson, new TypeToken<List<Teacher>>() {}.getType());
		for (Teacher teacher : teachers) {
			Optional<School> curr = new SchoolRepoImpl().findSchoolById(schools, teacher.getSchoolId());
			if (curr.isPresent()) {
				curr.get().addTeacher(teacher);
			}
		}
		return true;
	}

	public String exportSchoolToJson(List<School> schools) {
		return new Gson().toJson(schools);
	}
}
