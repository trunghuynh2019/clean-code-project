/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.views.SchoolPrinter;

public class SchoolManagementImpl implements SchoolManagement{

	private SchoolPrinter schoolPrinter = new SchoolPrinter();
	
	public void viewAllSchools(List<School> schools) {
		if (schools.isEmpty()) {
			System.out.println("The school list is empty.");
		} else {
			System.out.println("\nThe list of schools are below: ");
			for (School school : schools) {
				System.out.println("=================================================");
				schoolPrinter.print(school);
			}
		}
		
	}

	public void viewAllTeachers(School school) {
		if(school.getNumberOfTeacher() == 0) {
			System.out.println("The teacher list is empty.");
		} else {
			System.out.println("\nThe list of teachers are below: ");
			for (Teacher teacher : school.getTeachers()) {
				System.out.println("=================================================");
				schoolPrinter.print(teacher);
			}
		}
	}

	public void addSchool(List<School> schools, School newSchool) {
		/*
		 * Kiểm tra nếu list rỗng -> add trực tiếp vào list
		 * Nếu không -> kiểm tra từng mã trường một để quyết định update thông tin hay add mới*/
		if (schools.isEmpty()) { 
			schools.add(newSchool);
		} else {
			boolean existedSchool = false;
			for (School school : schools) {
				if (school.equalId(newSchool)) {
					existedSchool = true;
					school.setName(newSchool.getName());
					school.setAddress(newSchool.getAddress());
					school.setNumberOfStudent(newSchool.getNumberOfStudent());
					for (Teacher teacher: newSchool.getTeachers()) {
						signContractWithTeacher(school, teacher);
					}
					break;
				}
			}
			if (!existedSchool) {
				schools.add(newSchool);
			}
		}
	}

	public void addSchoolFrom(String fileName, List<School> schools) {
		File file = new File("resources/" + fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				currentLine = currentLine.substring(2);
				String[] dataArr = currentLine.split(" \\|\\|\\| ");
				School newSchool = new School(dataArr[0],dataArr[1],dataArr[3],new ArrayList<Teacher>(),Integer.parseInt(dataArr[2]));
				addSchool(schools, newSchool);
			}
		} catch (IOException e) {e.printStackTrace();}
		finally {
				try {
					if(br!=null) {
					br.close();}
				} catch (IOException e) {e.printStackTrace();}
		}
	}

	public void signContractWithTeacher(School school, Teacher newTeacher) {
		if (school.getTeachers().isEmpty()) {
			school.signContractWith(newTeacher);
		} else {
			boolean existedTeacher = false;
			for (Teacher teacher : school.getTeachers()) {
				if (teacher.equalId(newTeacher)) {
					existedTeacher = true;
					teacher.setName(newTeacher.getName());
					teacher.setSchoolId(newTeacher.getSchoolId());
					break;
				}
			}
			if (!existedTeacher) {
				school.signContractWith(newTeacher);
			}
		}
	}

	public void signContractWithTeacherFrom(String fileName, List<School> schools) {
		File file = new File("resources/" + fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				currentLine = currentLine.substring(2);
				String[] dataArr = currentLine.split(" \\|\\|\\| ");
				Teacher teacher = new Teacher(dataArr[0],dataArr[1],dataArr[2]);
				for (School school : schools) {
					if (school.getId().equals(dataArr[2]))
						signContractWithTeacher(school, teacher);
				}
				
			}
		} catch (IOException e) {e.printStackTrace();}
		finally {
				try {
					if(br!=null) {
					br.close();}
				} catch (IOException e) {e.printStackTrace();}
		}
	}

	@Override
	public School findSchoolById(List<School> schools, String id) {
		for (School s: schools) {
			if (s.getId().equals(id))
				return s;
		}
		return null;
	}
	
}
