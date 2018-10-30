/*
 * Title
 * 
 * @author Huy
 * @date Oct 30, 2018
 * @version 1.0
 */
package com.cleancode.education.filewriter;

import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.table;
import static j2html.TagCreator.tbody;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.cleancode.education.models.School;

public class HtmlWriter implements FileWriter {

	@Override
	public void exportSchool(List<School> schools, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("resources/" + fileName);
			pw.println(convertSchoolDataToHtml(schools));
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
			pw.println(convertTeacherDataToHtml(schools));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}
	
	private String convertSchoolDataToHtml(List<School> schools) {
		return html(
				head(
					title("School Management")
				),
				body(
					h1("Danh sach truong"),
					table(
						tbody(
							tr(
								td("ID"),
								td("Name"),
								td("Number of teacher"),
								td("Address"),
								td("Teacher's CMND")
							),
							each(schools, school -> tr(
								td(school.getId()),
								td(school.getName()),
								td(String.valueOf(school.getNumberOfTeacher())),
								td(school.getAddress()),
								td(school.getTeacherId())
							))))
				)
			).render();
	}
	
	private String convertTeacherDataToHtml(List<School> schools) {
		return html(
				head(
					title("School Management")
				),
				body(
					h1("Danh sach giao vien"),
					table(
						tbody(
							tr(
								td("ID"),
								td("Name"),
								td("Working School's Id")
							),
							each(schools, school
									-> each(school.getTeachers(), teacher -> tr(
											td(teacher.getId()),
											td(teacher.getName()),
											td(teacher.getSchoolId())
									
							)))))
				)
			).render();
	}
}
