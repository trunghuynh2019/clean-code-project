package sms.functionInterface;

import java.util.List;

import sms.model.School;

public interface FileWritingITF {
	public boolean writeSchoolToTextFile(List<School> schools);
	public boolean writeTeacherToTextFile(List<School> schools);
	
	/*public XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook);*/
	public boolean writeSchoolToExcelFile(List<School> schools);
	public boolean writeTeacherToExcelFile(List<School> schools);
}
