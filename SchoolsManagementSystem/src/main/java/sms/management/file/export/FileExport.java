package sms.management.file.export;

import java.util.List;
import sms.model.School;
import sms.model.Teacher;

public interface FileExport {
	public boolean exportDataOfSchoolToFile(List<School> schools, String fileName);
	public boolean exportDataOfTeacherToFile(List<Teacher> teachers, String fileName);
}
