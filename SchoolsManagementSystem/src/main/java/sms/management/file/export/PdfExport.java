package sms.management.file.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import sms.model.School;
import sms.model.Teacher;

public class PdfExport implements FileExport{
	@Override
	public boolean exportDataOfSchoolToFile(List<School> schools, String fileName) {
		boolean check = false;
		String[] headerColumn = {"ID","Name","Address","Number Of Teacher", "Number Of Student"};
		
		PdfPTable table = new PdfPTable(headerColumn.length);
		table.setWidthPercentage(100);
		
		for (String headerTitle : headerColumn) {
			PdfPCell cHeader = new PdfPCell(new Paragraph(headerTitle));
			cHeader.setBorderWidth(3);
			cHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cHeader);
		}
		
		for (School school : schools) {
			table.addCell(school.getId());
			table.addCell(school.getName());
			table.addCell(school.getAddress());
			table.addCell(school.getNumberOfTeachers()+"");
			table.addCell(school.getNumberOfStudents()+"");
		}
		
		Document pdfDocument = new Document(PageSize.A4);
		
		try {
			File f = new File(fileName);
			FileOutputStream fOutput = new FileOutputStream(f);
			
			PdfWriter.getInstance(pdfDocument, fOutput).setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			
			pdfDocument.open();
			
			Paragraph para = new Paragraph("Schools List: \n\n");
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDocument.add(para);
			
			pdfDocument.add(table);
		}catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}finally {
			pdfDocument.close();
		}
		return check;
	}
	
	@Override
	public boolean exportDataOfTeacherToFile(List<Teacher> teachers, String fileName) {
		boolean check = false;
		String[] headerColumn = {"Name","Address","Phone"};
		
		PdfPTable table = new PdfPTable(headerColumn.length);
		table.setWidthPercentage(100);
		
		for (String headerTitle : headerColumn) {
			PdfPCell cHeader = new PdfPCell(new Paragraph(headerTitle));
			cHeader.setBorderWidth(3);
			cHeader.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cHeader);
		}
		
		for (Teacher teacher : teachers) {
			table.addCell(teacher.getName());
			table.addCell(teacher.getAddress());
			table.addCell(teacher.getPhone()+"");
		}
		
		Document pdfDocument = new Document(PageSize.A4);
		
		try {
			File f = new File(fileName);
			FileOutputStream fOutput = new FileOutputStream(f);
			
			PdfWriter.getInstance(pdfDocument, fOutput).setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			
			pdfDocument.open();
			
			Paragraph para = new Paragraph("Schools List: \n\n");
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDocument.add(para);
			
			pdfDocument.add(table);
		}catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}finally {
			pdfDocument.close();
		}
		return check;
	}
}
