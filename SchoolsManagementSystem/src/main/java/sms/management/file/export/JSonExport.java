package sms.management.file.export;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONObject;

import sms.model.School;
import sms.model.Teacher;

public class JSonExport implements FileExport{

	@Override
	public boolean exportDataOfSchoolToFile(List<School> schools, String fileName) {
		boolean check = false;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			for(School school: schools) {
				JSONObject objDetail = new JSONObject();
				objDetail.put("ID", school.getId());
				objDetail.put("Name", school.getName());
				objDetail.put("Address", school.getAddress());
				objDetail.put("Number Of Teacher", school.getNumberOfTeachers());
				objDetail.put("Number Of Student", school.getNumberOfStudents());
				
				pw.write(objDetail.toString());
				pw.write(13);
			}
			check = true;
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			pw.close();
		}
		return check;
	}

	@Override
	public boolean exportDataOfTeacherToFile(List<Teacher> teachers, String fileName) {
		boolean check = false;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			for(Teacher teacher: teachers) {
				JSONObject objDetail = new JSONObject();
				objDetail.put("Name", teacher.getName());
				objDetail.put("Address", teacher.getAddress());
				objDetail.put("Phone", teacher.getPhone());
			
				pw.write(objDetail.toString());
				pw.write(13);
			}
			check = true;
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			pw.close();
		}
		return check;
	}

}
