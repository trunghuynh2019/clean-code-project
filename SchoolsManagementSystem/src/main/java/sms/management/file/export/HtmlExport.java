package sms.management.file.export;

import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import sms.model.School;
import sms.model.Teacher;

public class HtmlExport implements FileExport{
	@Override
	public boolean exportDataOfSchoolToFile(List<School> schools, String fileName) {
		String dataHtml =
			html(
					head(
						title("Schools List")
					),
					body(
						h1("Schools List"),
						table(
							tr(
								td("ID"),
								td("Name"),
								td("Address"),
								td("Number Of Teacher"),
								td("Number Of Student")
							),
							each(schools, 
								school -> tr(
									td(school.getId()),
									td(school.getName()),
									td(school.getAddress()),
									td(school.getNumberOfTeachers()+""),
									td(school.getNumberOfStudents()+"")
								)
							)
						)
					)
			).render();
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			pw.println(dataHtml);
		}catch (IOException e) {
			// TODO: handle exception
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
		return false;
	}

	@Override
	public boolean exportDataOfTeacherToFile(List<Teacher> teachers, String fileName) {
		String dataHtml =
				html(
						head(
							title("Teachers List")
						),
						body(
							h1("Teachers List"),
							table(
								tr(
									td("Name"),
									td("Address"),
									td("Phone")
								),
								each(teachers, 
									teacher -> tr(
										td(teacher.getName()),
										td(teacher.getAddress()),
										td(teacher.getPhone()+"")
									)
								)
							)
						)
				).render();
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(fileName);
				pw.println(dataHtml);
			}catch (IOException e) {
				// TODO: handle exception
			}finally {
				if(pw!=null) {
					pw.close();
				}
			}
			return false;
	}
}
