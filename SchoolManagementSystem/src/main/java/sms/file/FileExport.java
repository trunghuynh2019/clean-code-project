package sms.file;

import java.util.List;

import sms.model.School;

public interface FileExport {
	public boolean exportSchoolsToFile(List<School> schools);
	public boolean exportTeachersToFile(List<School> schools);
}
