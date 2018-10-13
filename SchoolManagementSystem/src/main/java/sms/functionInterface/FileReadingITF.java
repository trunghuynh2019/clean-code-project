package sms.functionInterface;

import java.util.List;

import sms.model.School;

public interface FileReadingITF {
	public boolean readSchoolFile(List<School> schools);
	public boolean readTeacherFile(List<School> schools);
}
