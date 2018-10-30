/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller.impl;

import java.util.List;

import com.cleancode.education.controller.SchoolController;
import com.cleancode.education.filereader.FileReader;
import com.cleancode.education.filereader.TextReader;
import com.cleancode.education.filewriter.ExcelWriter;
import com.cleancode.education.filewriter.FileWriter;
import com.cleancode.education.filewriter.HtmlWriter;
import com.cleancode.education.filewriter.PdfWriter;
import com.cleancode.education.filewriter.TextWriter;
import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.service.SchoolService;
import com.cleancode.education.views.SchoolPrinter;

public class SchoolControllerImpl implements SchoolController{
	private SchoolService schoolService;
	private FileWriter excelWriter = new ExcelWriter();
	private FileWriter pdfWriter = new PdfWriter();
	private FileWriter textWriter = new TextWriter();
	private FileWriter htmlWriter = new HtmlWriter();
	private FileReader textReader = new TextReader();
	private SchoolPrinter schoolPrinter = new SchoolPrinter();
	
	public SchoolControllerImpl(SchoolService schoolService) {
		this.schoolService = schoolService;
	}
	
	@Override
	public void viewAllSchools() {
		List<School> schools = schoolService.getAllSchool();
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

	@Override
	public void addSchool(School newSchool) {
		
		List<School> schools = schoolService.getAllSchool();
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
					schoolService.update(newSchool);
					break;
				}
			}
			if (!existedSchool) {
				schools.add(newSchool);
			}
		}
	}
	
	@Override
	public void signContractWithTeacher(Teacher newTeacher) {
		School school = schoolService.getSchoolById(newTeacher.getSchoolId());
		if(school == null)
			return;
		if (school.getNumberOfTeacher() == 0) {
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

	@Override
	public void addSchoolsFrom(String fileName) {
		List<School> schools = textReader.importSchool(fileName);
		for (School school : schools) {
			addSchool(school);
		}		
	}

	@Override
	public void signContractWithTeacherFrom(String fileName) {
		List<Teacher> teachers = textReader.importTeacher(fileName);
		for(Teacher teacher : teachers) {
			signContractWithTeacher(teacher);
		}
	}

	@Override
	public void exportSchoolsToText(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		textWriter.exportSchool(schools, fileName);
	}

	@Override
	public void exportSchoolsToExcel(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		excelWriter.exportSchool(schools, fileName);
	}

	@Override
	public void exportTeacherToText(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		textWriter.exportTeacher(schools, fileName);
	}

	@Override
	public void exportTeacherToExcel(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		excelWriter.exportTeacher(schools, fileName);	
	}

	@Override
	public void exportSchoolToPDF(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		pdfWriter.exportSchool(schools, fileName);
	}

	@Override
	public void exportSchoolToHtml(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		htmlWriter.exportSchool(schools, fileName);
	}

	@Override
	public void exportTeacherToPDF(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		pdfWriter.exportTeacher(schools, fileName);
	}

	@Override
	public void exportTeacherToHtml(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		htmlWriter.exportTeacher(schools, fileName);
	}

}
