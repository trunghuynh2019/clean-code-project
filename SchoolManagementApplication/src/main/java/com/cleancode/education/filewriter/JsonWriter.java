/*
 * Title
 * 
 * @author Huy
 * @date Oct 30, 2018
 * @version 1.0
 */
package com.cleancode.education.filewriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.google.gson.Gson;

public class JsonWriter implements FileWriter {
	private Gson gson = new Gson();
	
	@Override
	public void exportSchool(List<School> schools, String fileName) {
		
		try (java.io.FileWriter writer = new java.io.FileWriter("resources/" + fileName)) {
            gson.toJson(schools, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }


	}

	@Override
	public void exportTeacher(List<School> schools, String fileName) {
		try (java.io.FileWriter writer = new java.io.FileWriter("resources/" + fileName)) {
			List<Teacher> teachers = getTeachersFrom(schools);
			gson.toJson(teachers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private List<Teacher> getTeachersFrom(List<School> schools){
		List<Teacher> teachers = new ArrayList<>();
		for(School school : schools) {
			for (Teacher teacher : school.getTeachers()) {
				teachers.add(teacher);
			}
		}
		
		return teachers;
	
	}

}
