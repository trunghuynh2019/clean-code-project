/*
 * Title
 * 
 * @author Huy
 * @date Oct 24, 2018
 * @version 1.0
 */
package com.cleancode.education.util;

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

import java.util.List;

import com.cleancode.education.models.School;

public class HtmlUtil {
	
	public String schoolDataToHtml(List<School> schools) {
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
	
	public String teacherDataToHtml(List<School> schools) {
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
