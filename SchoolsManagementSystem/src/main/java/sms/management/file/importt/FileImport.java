package sms.management.file.importt;

import java.util.List;

import sms.model.School;
import sms.model.Teacher;

public interface FileImport {
	public boolean importDataOfTeacherFromFile(List<Teacher> teachers, String fileName);
	public boolean importDataOfSchoolFromFile(List<School> schools, String fileName);
}
