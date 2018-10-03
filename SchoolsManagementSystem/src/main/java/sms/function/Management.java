package sms.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import sms.model.School;
import sms.model.Teacher;
public class Management implements ManagementInterface{
	
	public void loadDatabaseOfSchool(String fileName, List<School> schools) {
		File f = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String tmp;
			StringTokenizer stk;
			while((tmp=br.readLine())!=null) {
				stk = new StringTokenizer(tmp, "|||");
				School school = new School(stk.nextToken().toUpperCase(), stk.nextToken(),
						Integer.parseInt(stk.nextToken()), stk.nextToken(), 0, null);
				addSchool(schools, school);
			}
		}catch(IOException e) {e.printStackTrace();}
		finally {
			try {
				if(fr!=null) fr.close();
				if(br!=null) br.close();
			}catch(IOException e) {e.printStackTrace();}
		}
	}
	
	public void loadDatabaseOfTeacher(String fileName, School school) {
		File f = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String tmp;
			StringTokenizer stk;
			while((tmp=br.readLine())!=null) {
				stk = new StringTokenizer(tmp, "|||");
				Teacher teacher = new Teacher(Integer.parseInt(stk.nextToken()),stk.nextToken(),stk.nextToken());
				signContractWithTeacher(school, teacher);
			}
			school.setNumberOfTeachers(school.getTeachers().size());
		}catch(IOException e) {e.printStackTrace();}
		finally {
			try {
				if(fr!=null) fr.close();
				if(br!=null) br.close();
			}catch(IOException e) {e.printStackTrace();}
		}
	}
	

	public void addSchool(List<School> schools, School school) {
		schools.add(school);
	}
	

	public void signContractWithTeacher(School school, Teacher teacher) {
		school.getTeachers().add(teacher);
	}
	

	public School searchSchoolById(List<School> schools,String id) {
		for(School school: schools) 
			if(school.getId().equals(id))
				return school;
		return null;
	}
	

	public Teacher searchTeacherByName(List<Teacher> teachers, String name) {
		for(Teacher teacher: teachers)
			if(teacher.getName().equals(name))
				return teacher;
		return null;
	}
	

	public Teacher searchTeacherByAddress(List<Teacher> teachers, String address) {
		for(Teacher teacher: teachers)
			if(teacher.getAddress().equals(address))
				return teacher;
		return null;
	}
	
}

