package sms.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sms.model.School;
import sms.model.Teacher;

public class PdfExport implements FileExport {
	private String schoolFileName;
	private String teacherFileName;

	public PdfExport(String schoolFileName, String teacherFileName) {
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

	public PdfPTable createTable(String[] header) {
		PdfPTable table = new PdfPTable(header.length);
		table.setWidthPercentage(90);
		createHeader(table, header);
		return table;
	}

	public void createHeader(PdfPTable table, String[] header) {
		for (int i = 0; i < header.length; i++) {
			PdfPCell cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setBorderWidth(1);
			cell.setPhrase(new Phrase(header[i]));
			table.addCell(cell);
		}
	}

	@Override
	public boolean exportSchoolsToFile(List<School> schools) {
		Document pDocument = new Document(PageSize.A4);
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("export/pdf/" + schoolFileName);
			PdfWriter.getInstance(pDocument, fileOut);
			pDocument.open();
			
			Paragraph title = new Paragraph("DANH SACH TRUONG\n	");
			title.setAlignment(Element.ALIGN_CENTER);
			pDocument.add(title);
			
			String[] header = {"ID", "Name", "Number of Teacher", "Number of Student", "Address"};
			PdfPTable table = createTable(header);
			for (School school : schools) {
				table.addCell(school.getId());
				table.addCell(school.getName());
				table.addCell(String.valueOf(school.getNumOfTeachers()));
				table.addCell(String.valueOf(school.getNumOfStudents()));
				table.addCell(school.getAddress());
			}
			pDocument.add(table);
			
			pDocument.close();
			return true;
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean exportTeachersToFile(List<School> schools) {
		Document pDocument = new Document(PageSize.A4);
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("export/pdf/" + teacherFileName);
			PdfWriter.getInstance(pDocument, fileOut);
			pDocument.open();
			
			Paragraph title = new Paragraph("DANH SACH GIAO VIEN\n");
			title.setAlignment(Element.ALIGN_CENTER);
			pDocument.add(title);
			
			String[] header = {"ID", "Name", "SchoolID"};
			PdfPTable table = createTable(header);
			for (School school : schools) {
				List<Teacher> teachers = school.getTeachers();
				for (Teacher teacher : teachers) {
					table.addCell(String.valueOf(teacher.getId()));
					table.addCell(teacher.getName());
					table.addCell(teacher.getSchoolId());
				}
			}
			pDocument.add(table);
			
			pDocument.close();
			return true;
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			return false;
		}
	}
}
