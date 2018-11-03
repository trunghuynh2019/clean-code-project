package sms.management.file.importt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import sms.model.School;
import sms.model.Teacher;

public class JSonImport implements FileImport{

	@Override
	public boolean importDataOfTeacherFromFile(List<Teacher> teachers, String fileName) {
		boolean check = false;
		File f = new File(fileName);
		FileReader frReader = null;
		BufferedReader brReader = null;
		try {
			frReader = new FileReader(f);
			brReader = new BufferedReader(frReader);
			String content;
			while((content=brReader.readLine())!=null) {
				JSONObject objDetail = new JSONObject(content);
				String address = objDetail.getString("Address");
				int phone = objDetail.getInt("Phone");
				String name = objDetail.getString("Name");
				
				Teacher teacher = new Teacher(phone, name, address);
				teachers.add(teacher);
			}
			check = true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(brReader!=null) {
					brReader.close();
				}
				if(frReader!=null) {
					frReader.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	@Override
	public boolean importDataOfSchoolFromFile(List<School> schools, String fileName) {
		boolean check = false;
		File f = new File(fileName);
		FileReader frReader = null;
		BufferedReader brReader = null;
		try {
			frReader = new FileReader(f);
			brReader = new BufferedReader(frReader);
			String content;
			while((content=brReader.readLine())!=null) {
				JSONObject objDetail = new JSONObject(content);
				int numberOfTeachers = objDetail.getInt("Number Of Teacher");
				String address = objDetail.getString("Address");
				int numberOfStudents = objDetail.getInt("Number Of Student");
				String id = objDetail.getString("ID");
				String name = objDetail.getString("Name");
				
				School school = new School(id, name, numberOfStudents, address, numberOfTeachers, new ArrayList<Teacher>());
				schools.add(school);
			}
			check = true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(brReader!=null) {
					brReader.close();
				}
				if(frReader!=null) {
					frReader.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
	
}
