/*
 * Title
 * 
 * @author Huy
 * @date Oct 30, 2018
 * @version 1.0
 */
package com.cleancode.education.filewriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.views.PrinterSupport;

public class TextWriter implements FileWriter {
	
	private PrinterSupport printerSupport = new PrinterSupport();
	
	@Override
	public void exportSchool(List<School> schools, String fileName) {
		File file = new File("resources/" + fileName);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.print(printerSupport.schoolTextFileHeader());
			for(School school : schools) {
				pw.println(printerSupport.formatTextRow(school));
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	@Override
	public void exportTeacher(List<School> schools, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("resources/" + fileName);
			pw.print(printerSupport.teacherTextFileHeader());
			for(School school : schools) {
				for (Teacher teacher : school.getTeachers()) {
					pw.println(printerSupport.formatTextRow(teacher));
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

}
