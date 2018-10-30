/*
 * Title
 * 
 * @author Huy
 * @date Oct 30, 2018
 * @version 1.0
 */
package com.cleancode.education.filewriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public class ExcelWriter implements FileWriter {
	
	private enum SchoolCells {
		SCHOOL_ID(0), NAME(1), NUMBER_OF_TEACHER(2), ADDRESS(3), TEACHER_CMND(4);
		
		private final int value;
	    private SchoolCells(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	private enum TeacherCells {
		CMND(0), NAME(1), SCHOOL_ID(2);
		
		private final int value;
	    private TeacherCells(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	@Override
	public void exportSchool(List<School> schools, String fileName) {
		Workbook workbook = new XSSFWorkbook();

        Sheet sheet = createSheetWithHeader(workbook, "School", FileWriter.SCHOOL_COLUMN_HEADER);

        int currentRow = 1;
        for(School school : schools) {
        	createRowForSheetBy(sheet, currentRow++, school);
        }

        for(int i = 0; i < FileWriter.SCHOOL_COLUMN_HEADER.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("resources/" + fileName);
            workbook.write(fileOut);
            fileOut.close();
	        workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportTeacher(List<School> schools, String fileName) {
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        Sheet sheet = createSheetWithHeader(workbook, "Teacher", FileWriter.TEACHER_COLUMN_HEADER);

        int currentRow = 1;
        for(School school: schools) {
        	for (Teacher teacher : school.getTeachers()) {
        		createRowForSheetBy(sheet, currentRow++, teacher);
        	}
        }

        for(int i = 0; i < FileWriter.TEACHER_COLUMN_HEADER.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(new File("resources/" + fileName));
			workbook.write(fileOut);
			fileOut.close();
	        workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private Sheet createSheetWithHeader(Workbook workbook, String sheetName, String[] columns) {
		Sheet sheet = workbook.createSheet(sheetName);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        return sheet;
	}
	
	private void createRowForSheetBy(Sheet sheet, int currentRow, School school) {
		Row row = sheet.createRow(currentRow);
        row.createCell(SchoolCells.SCHOOL_ID.getValue())
                .setCellValue(school.getId());

        row.createCell(SchoolCells.NAME.getValue())
                .setCellValue(school.getName());

        row.createCell(SchoolCells.NUMBER_OF_TEACHER.getValue()).setCellValue(school.getNumberOfTeacher());

        row.createCell(SchoolCells.ADDRESS.getValue())
                .setCellValue(school.getAddress());
        row.createCell(SchoolCells.TEACHER_CMND.getValue()).setCellValue(school.getTeacherId());
		
	}
	
	private void createRowForSheetBy(Sheet sheet, int currentRow, Teacher teacher) {
		Row row = sheet.createRow(currentRow);
		row.createCell(TeacherCells.CMND.getValue())
        	.setCellValue(teacher.getId());

		row.createCell(TeacherCells.NAME.getValue())
			.setCellValue(teacher.getName());
		
		row.createCell(TeacherCells.SCHOOL_ID.getValue())
			.setCellValue(teacher.getSchoolId());
		
	}

}
