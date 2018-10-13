package sms.function;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import sms.functionInterface.FileWritingITF;
import sms.functionInterface.FunctionITF;
import sms.model.School;

public class FileWriting implements FileWritingITF {
	private String schoolFileName;
	private String teacherFileName;

	public FileWriting(String schoolFileName, String teacherFileName) {
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

	public boolean writeSchoolToTextFile(List<School> schools) {
		BufferedWriter writer;
		FunctionITF function = new Function();
		try {
			FileOutputStream file = new FileOutputStream("export/text/" + schoolFileName);
			writer = new BufferedWriter(new OutputStreamWriter(file));
			writer.write("Danh sach truong");
			writer.newLine();
			writer.newLine();

			List<String> schoolsData = function.getStringFromSchoolList(schools);
			for (String string : schoolsData) {
				writer.write(string);
				writer.newLine();
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean writeTeacherToTextFile(List<School> schools) {
		BufferedWriter writer;
		FunctionITF function = new Function();
		try {
			FileOutputStream file = new FileOutputStream("export/text/" + teacherFileName);
			writer = new BufferedWriter(new OutputStreamWriter(file));
			writer.write("Danh sach giao vien");
			writer.newLine();
			writer.newLine();

			List<String> teachersData = function.getStringFromTeacherList(schools);
			for (String string : teachersData) {
				writer.write(string + "\n");
				writer.newLine();
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean writeSchoolToExcelFile(List<School> schools) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean writeTeacherToExcelFile(List<School> schools) {
		// TODO Auto-generated method stub
		return false;
	}

}
