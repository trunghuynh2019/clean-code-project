/*
 * Title
 * 
 * @author Huy
 * @date Oct 10, 2018
 * @version 1.0
 */
package com.cleancode.education.functions;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public class ExcelWriter {
	 private static String[] columns = {"ID", "Name", "Number Of Teacher", "Address", "Teacher's CMND"};
	 private static List<School> schools =  new ArrayList<>();
	 
	 static {
		 	List<Teacher> teachers = new ArrayList<>();
		 	List<Teacher> teachers1 = new ArrayList<>();
		 	teachers.add(new Teacher("285696696", "Le Cong C", "nbk-vl"));
		 	teachers.add(new Teacher("285126545", "Le Cong A", "nbk-vl"));
		 	teachers.add(new Teacher("285687996", "Le Cong B", "nbk-vl"));
		 	teachers1.add(new Teacher("285654456", "Le Cong D", "nbk-qn"));
		 	teachers1.add(new Teacher("285846854", "Le Cong E", "nbk-qn"));
		 	teachers1.add(new Teacher("285586453", "Le Cong F", "nbk-qn"));
	        schools.add(new School("nbk-vl","Truong trung hoc Chuyen Nguyen Binh Khiem","Vinh Long",teachers,50));
	        schools.add(new School("nbk-qn","Truong trung hoc Chuyen Nguyen Binh Khiem","Quang Ngai",teachers1,50));
	    }
	 public static void main(String[] args) throws IOException, InvalidFormatException {
	        // Create a Workbook
	        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

	        /* CreationHelper helps us create instances of various things like DataFormat, 
	           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */

	        // Create a Sheet
	        Sheet sheet = workbook.createSheet("School");

	        // Create a Font for styling header cells
	        Font headerFont = workbook.createFont();
	        headerFont.setBold(true);

	        // Create a CellStyle with the font
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);

	        // Create a Row
	        Row headerRow = sheet.createRow(0);

	        // Create cells
	        for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(headerCellStyle);
	        }

	        // Create Other rows and cells with employees data
	        int rowNum = 1;
	        for(School school: schools) {
	            Row row = sheet.createRow(rowNum++);

	            row.createCell(0)
	                    .setCellValue(school.getId());

	            row.createCell(1)
	                    .setCellValue(school.getName());

	            row.createCell(2).setCellValue(school.getNumberOfTeacher());

	            row.createCell(3)
	                    .setCellValue(school.getAddress());
	            Cell c = row.createCell(4);
	            String temp = "";
	            for (Teacher t : school.getTeachers()) {
	            	
	            	temp = c.getStringCellValue() + ",";
	            	temp += t.getId();
	            	c.setCellValue(temp);
	            }
	            
//	            row.createCell(4).setCellValue(school.getTeacherId());
	        }

			// Resize all columns to fit the content size
	        for(int i = 0; i < columns.length; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write the output to a file
	        FileOutputStream fileOut = new FileOutputStream("exportedschool.xlsx");
	        workbook.write(fileOut);
	        fileOut.close();

	        // Closing the workbook
	        workbook.close();
	    }
}
