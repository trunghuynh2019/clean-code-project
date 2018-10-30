/*
 * Title
 * 
 * @author Huy
 * @date Oct 30, 2018
 * @version 1.0
 */
package com.cleancode.education.filewriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfWriter implements FileWriter {

	@Override
	public void exportSchool(List<School> schools, String fileName) {
		Document pdfDoc = new Document(PageSize.A4);
		try {
			
			com.itextpdf.text.pdf.PdfWriter.getInstance(pdfDoc, new FileOutputStream("resources/"+ fileName))
				.setPdfVersion(com.itextpdf.text.pdf.PdfWriter.PDF_VERSION_1_7);
			pdfDoc.open();
	
			Paragraph para = new Paragraph("Danh sach truong\n\n");
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDoc.add(para);
			
			PdfPTable table = createTable(FileWriter.SCHOOL_COLUMN_HEADER);
			for (School school : schools) {
				addRows(table, school);
			}
			 
			pdfDoc.add(table);
			
			
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}finally {
			pdfDoc.close();
		}
	}

	@Override
	public void exportTeacher(List<School> schools, String fileName) {
		Document pdfDoc = new Document(PageSize.A4);
		try {
			
			com.itextpdf.text.pdf.PdfWriter.getInstance(pdfDoc, new FileOutputStream("resources/"+ fileName))
				.setPdfVersion(com.itextpdf.text.pdf.PdfWriter.PDF_VERSION_1_7);
			pdfDoc.open();
	
			Paragraph para = new Paragraph("Danh sach giao vien\n\n");
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDoc.add(para);
			
			
			PdfPTable table = createTable(FileWriter.TEACHER_COLUMN_HEADER);
			for (School school : schools) {
				for (Teacher teacher : school.getTeachers()) {
					addRows(table, teacher);
				}
			}
			 
			pdfDoc.add(table);
			
			
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}finally {
			pdfDoc.close();
		}
	}
	
	
	private PdfPTable createTable(String[] columnHeader) {
		PdfPTable table = new PdfPTable(columnHeader.length);
		table.setWidthPercentage(90);
		addTableHeader(table, columnHeader);
		return table;
	}
	
	private void addTableHeader(PdfPTable table, String[] columnHeader) {
	    Stream.of(columnHeader)
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private void addRows(PdfPTable table, School school) {
	    table.addCell(school.getId());
	    table.addCell(school.getName());
	    table.addCell(String.valueOf(school.getNumberOfTeacher()));
	    table.addCell(school.getAddress());
	    table.addCell(school.getTeacherId());
	}
	
	private void addRows(PdfPTable table, Teacher teacher) {
	    table.addCell(teacher.getId());
	    table.addCell(teacher.getName());
	    table.addCell(teacher.getSchoolId());
	}
}
