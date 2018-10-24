/*
 * Title
 * 
 * @author Huy
 * @date Oct 24, 2018
 * @version 1.0
 */
package com.cleancode.education.util;

import java.util.stream.Stream;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfUtil {
	public void addTableHeader(PdfPTable table, String[] columnHeader) {
	    Stream.of(columnHeader)
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	public void addRows(PdfPTable table, School school) {
	    table.addCell(school.getId());
	    table.addCell(school.getName());
	    table.addCell(String.valueOf(school.getNumberOfTeacher()));
	    table.addCell(school.getAddress());
	    table.addCell(school.getTeacherId());
	}
	
	public void addRows(PdfPTable table, Teacher teacher) {
	    table.addCell(teacher.getId());
	    table.addCell(teacher.getName());
	    table.addCell(teacher.getSchoolId());
	}
}
