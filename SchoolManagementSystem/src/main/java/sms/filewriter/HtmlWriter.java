package sms.filewriter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.meta;
import static j2html.TagCreator.link;
import static j2html.TagCreator.script;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.table;
import static j2html.TagCreator.thead;
import static j2html.TagCreator.tbody;
import static j2html.TagCreator.th;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import sms.model.School;

public class HtmlWriter implements FileWriter {
	private String schoolFileName;
	private String teacherFileName;

	public HtmlWriter(String schoolFileName, String teacherFileName) {
		super();
		this.schoolFileName = schoolFileName;
		this.teacherFileName = teacherFileName;
	}

	public String getSchoolFileName() {
		return schoolFileName;
	}

	public void setSchoolFileName(String schoolFileName) {
		this.schoolFileName = schoolFileName;
	}

	public String getTeacherFileName() {
		return teacherFileName;
	}

	public void setTeacherFileName(String teacherFileName) {
		this.teacherFileName = teacherFileName;
	}

	private String getHtmlStringFromSchools(List<School> schools) {
		return html(
				head(meta().withName("viewport").withContent("width=device-width, initial-scale=1"), title("Schools"),
						link().withRel("stylesheet")
								.withHref("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"),
						script().withSrc("https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"),
						script().withSrc("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")),
				body(h1("DANH SACH TRUONG"), table(
						thead(tr(th("ID"), th("Name"), th("Number of Teacher"), th("Number of Student"),
								th("Address"))),
						tbody(each(schools,
								school -> tr(td(school.getId()), td(school.getName()),
										td(String.valueOf(school.getNumOfTeachers())),
										td(String.valueOf(school.getNumOfStudents())), td(school.getAddress()))))
												).withClasses("table", "table-hover"))).render();
	}

	private String getHtmlStringFromTeachers(List<School> schools) {
		return html(
				head(meta().withName("viewport").withContent("width=device-width, initial-scale=1"), title("Teachers"),
						link().withRel("stylesheet")
								.withHref("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"),
						script().withSrc("https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"), script()
								.withSrc("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")),
				body(h1("DANH SACH GIAO VIEN"), table(thead(tr(th("ID"), th("Name"), th("SchoolID"))),
						tbody(each(schools,
								school -> each(school.getTeachers(),
										teacher -> tr(td(String.valueOf(teacher.getId())), td(teacher.getName()),
												td(teacher.getSchoolId())))))).withClasses("table", "table-hover")))
														.render();
	}

	@Override
	public boolean exportSchoolsToFile(List<School> schools) {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter("export/html/" + schoolFileName);
			pWriter.println(getHtmlStringFromSchools(schools));
			pWriter.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean exportTeachersToFile(List<School> schools) {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter("export/html/" + teacherFileName);
			pWriter.println(getHtmlStringFromTeachers(schools));
			pWriter.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
