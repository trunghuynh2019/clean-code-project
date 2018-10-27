package sms.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
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

	public boolean writeSchoolToExcelFile(List<School> schools) {
		Workbook workbook = new XSSFWorkbook();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Schools");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create header.
		Row headerRow = sheet.createRow(0);
		Cell cell;
		cell = headerRow.createCell(0, CellType.STRING);
		cell.setCellValue("ID");
		cell.setCellStyle(headerCellStyle);
		cell = headerRow.createCell(1, CellType.STRING);
		cell.setCellValue("Name");
		cell.setCellStyle(headerCellStyle);
		cell = headerRow.createCell(2, CellType.STRING);
		cell.setCellValue("Number of students");
		cell.setCellStyle(headerCellStyle);
		cell = headerRow.createCell(3, CellType.STRING);
		cell.setCellValue("Address");
		cell.setCellStyle(headerCellStyle);

		// Create data.
		int rowNum = 0;
		for (School school : schools) {
			rowNum++;
			Row row = sheet.createRow(rowNum);
			row.createCell(0, CellType.STRING).setCellValue(school.getId());
			row.createCell(1, CellType.STRING).setCellValue(school.getName());
			row.createCell(2, CellType.STRING).setCellValue(school.getNumOfStudents());
			row.createCell(3, CellType.STRING).setCellValue(school.getAddress());
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < 4; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("export/excel/" + schoolFileName);
			workbook.write(fileOut);
			fileOut.close();
			// Closing the workbook
			workbook.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean writeTeacherToExcelFile(List<School> schools) {

		Workbook workbook = new XSSFWorkbook();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Teachers");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create header.
		Row headerRow = sheet.createRow(0);
		Cell cell;
		cell = headerRow.createCell(0, CellType.STRING);
		cell.setCellValue("ID");
		cell.setCellStyle(headerCellStyle);
		cell = headerRow.createCell(1, CellType.STRING);
		cell.setCellValue("Name");
		cell.setCellStyle(headerCellStyle);
		cell = headerRow.createCell(2, CellType.STRING);
		cell.setCellValue("School ID");
		cell.setCellStyle(headerCellStyle);

		// Create data.
		int rowNum = 0;
		for (School school : schools) {
			List<Teacher> teachers = school.getTeachers();
			for (Teacher teacher : teachers) {
				rowNum++;
				Row row = sheet.createRow(rowNum);
				row.createCell(0, CellType.STRING).setCellValue(teacher.getId());
				row.createCell(1, CellType.STRING).setCellValue(teacher.getName());
				row.createCell(2, CellType.STRING).setCellValue(teacher.getSchoolId());
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < 3; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("export/excel/" + teacherFileName);
			workbook.write(fileOut);
			fileOut.close();
			// Closing the workbook
			workbook.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
