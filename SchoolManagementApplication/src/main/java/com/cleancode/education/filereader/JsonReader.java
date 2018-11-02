/*
 * Title
 * 
 * @author Huy
 * @date Nov 2, 2018
 * @version 1.0
 */
package com.cleancode.education.filereader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class JsonReader implements FileReader {
	
	@Override
	public List<School> importSchool(String fileName) {
		Gson gson = new Gson();
		List<School> schools = new ArrayList<>();
        try (Reader reader = new java.io.FileReader("resources/" + fileName)) {

			// Convert JSON to JsonElement, and later to String
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            schools = gson.fromJson(jsonInString, new TypeToken<List<School>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

		
		return schools;
	}
	
	@Override
	public List<Teacher> importTeacher(String fileName) {
		Gson gson = new Gson();
		List<Teacher> teachers = new ArrayList<>();
        try (Reader reader = new java.io.FileReader("resources/" + fileName)) {

			// Convert JSON to JsonElement, and later to String
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            teachers = gson.fromJson(jsonInString, new TypeToken<List<Teacher>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

		
		return teachers;
	}
	

}
