package sms.filewriter;

import java.util.List;

import sms.model.School;

public interface FileWriter {
	public boolean exportSchoolsToFile(List<School> schools);
	public boolean exportTeachersToFile(List<School> schools);
}
