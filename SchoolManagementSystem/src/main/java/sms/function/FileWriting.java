package sms.function;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import sms.functionInterface.FileWritingITF;
import sms.functionInterface.FunctionITF;
import sms.model.School;
import sms.model.Teacher;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileWriting implements FileWritingITF {
	private String schoolFileName;
	private String teacherFileName;
	private BufferedWriter writer;
	private FunctionITF function = new Function();

	public FileWriting(String schoolFileName, String teacherFileName) {
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

	public boolean writeSchoolToTextFile(List<School> schools) {
		try {
			FileOutputStream file = new FileOutputStream("export/text/" + schoolFileName);
			writer = new BufferedWriter(new OutputStreamWriter(file));
			writer.write("Danh sach truong");
			writer.newLine();
			writer.newLine();

			List<String> schoolsData = function.getStringFromSchoolList(schools);
			if (schoolsData == null)
				return true;
			for (String string : schoolsData) {
				writer.write(string);
				writer.newLine();
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean writeTeacherToTextFile(List<School> schools) {
		try {
			FileOutputStream file = new FileOutputStream("export/text/" + teacherFileName);
			writer = new BufferedWriter(new OutputStreamWriter(file));
			writer.write("Danh sach giao vien");
			writer.newLine();
			writer.newLine();

			List<String> teachersData = function.getStringFromTeacherList(schools);
			if (teachersData == null)
				return true;
			for (String string : teachersData) {
				writer.write(string + "\n");
				writer.newLine();
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
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
