package sms.management.file.importt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sms.model.School;
import sms.model.Teacher;

public class TextImport implements FileImport{
	
	@Override
	public boolean importDataOfSchoolFromFile(List<School> schools, String fileName) {
		boolean check = false;
		String tmpFileName = System.getProperty("user.dir")+"\\fileData\\"+fileName;
		File f = new File(tmpFileName);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String tmp;
			StringTokenizer stk;
			while ((tmp = br.readLine()) != null) {
				stk = new StringTokenizer(tmp, "|||");
				School school = new School(stk.nextToken().toUpperCase(), stk.nextToken(),
						Integer.parseInt(stk.nextToken()), stk.nextToken(), 0, new ArrayList<Teacher>());
				schools.add(school);
			}
			check = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	@Override
	public boolean importDataOfTeacherFromFile(List<Teacher> teachers, String fileName) {
		boolean check = false;
		String tmpFileName = System.getProperty("user.dir")+"\\fileData\\"+fileName;
		File f = new File(tmpFileName);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String tmp;
			StringTokenizer stk;
			while ((tmp = br.readLine()) != null) {
				stk = new StringTokenizer(tmp, "|||");
				Teacher teacher = new Teacher(Integer.parseInt(stk.nextToken()), stk.nextToken(), stk.nextToken());
				
				teachers.add(teacher);
			}
			check = true;
//			school.setNumberOfTeachers(school.getTeachers().size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return check;
	}
}
