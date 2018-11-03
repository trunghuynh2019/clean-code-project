package sms.filereader;

import java.util.List;

import sms.model.School;
import sms.model.Teacher;

public interface FileReader {
	public boolean importSchoolFromFile(List<School> schools);
	public boolean importTeacherFromFile(List<School> schools, List<Teacher> teachers);
}
