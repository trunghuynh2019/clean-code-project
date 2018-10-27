package sms.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sms.model.School;
import sms.model.Teacher;

public class ExcelUtil {
	private String schoolFileName;
	private String teacherFileName;

	public ExcelUtil(String schoolFileName, String teacherFileName) {
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
	
	public enum SchoolCells {
		ID(0), NAME(1), NUMBER_OF_TEACHER(2), NUMBER_OF_STUDENT(3), ADDRESS(4);
		private final int value;
		private SchoolCells(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	public enum TeacherCells {
		ID(0), NAME(1), SCHOOL_ID(2);
		private final int value;
		private TeacherCells(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	public Sheet createHeader(Workbook workbook, String sheetName, String[] columns) {
		Sheet sheet = workbook.createSheet(sheetName);

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

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

	public boolean writeSchoolToExcelFile(List<School> schools) {
		Workbook workbook = new XSSFWorkbook();
		String[] columns = {"ID", "Name", "Number of Teacher", "Number of Student", "Address"};
		Sheet sheet = createHeader(workbook, "truong.xlsx", columns);
		
		int rowNum = 0;
		for (School school : schools) {
			rowNum++;
			Row row = sheet.createRow(rowNum);
			row.createCell(SchoolCells.ID.getValue()).setCellValue(school.getId());
			row.createCell(SchoolCells.NAME.getValue()).setCellValue(school.getName());
			row.createCell(SchoolCells.NUMBER_OF_TEACHER.getValue()).setCellValue(school.getNumOfTeachers());
			row.createCell(SchoolCells.NUMBER_OF_STUDENT.getValue()).setCellValue(school.getNumOfStudents());
			row.createCell(SchoolCells.ADDRESS.getValue()).setCellValue(school.getAddress());
		}

		// Resize all columns to fit the content size
		for (int i = 0; i <= SchoolCells.ADDRESS.value; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("export/excel/" + schoolFileName);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean writeTeacherToExcelFile(List<School> schools) {
		Workbook workbook = new XSSFWorkbook();
		String[] columns = {"ID", "Name", "SchoolID"};
		Sheet sheet = createHeader(workbook, "giaovien.xlsx", columns);

		// Create data.
		int rowNum = 0;
		for (School school : schools) {
			List<Teacher> teachers = school.getTeachers();
			for (Teacher teacher : teachers) {
				rowNum++;
				Row row = sheet.createRow(rowNum);
				row.createCell(TeacherCells.ID.getValue()).setCellValue(teacher.getId());
				row.createCell(TeacherCells.NAME.getValue()).setCellValue(teacher.getName());
				row.createCell(TeacherCells.SCHOOL_ID.getValue()).setCellValue(teacher.getSchoolId());
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i <= TeacherCells.SCHOOL_ID.getValue(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("export/excel/" + teacherFileName);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
