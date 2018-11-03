package sms.management.file.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import sms.model.School;
import sms.model.Teacher;

public class ExcelExport implements FileExport{
	
	private enum SchoolCells {
		ID(0), NAME(1), ADDRESS(2), NUM_TEACHER(3), NUM_STUDENT(4);
		
		private final int value;
		private SchoolCells(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
	
	private enum TeacherCells {
		NAME(0), ADDRESS(1), PHONE(2);
		
		private final int value;
		private TeacherCells(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	}
	
	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}
	
	@Override
	public boolean exportDataOfSchoolToFile(List<School> schools, String fileName) {
		boolean check = false;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("School Sheet");

		String[] titleSchoolField = { "ID", "Name", "Address", "Number Of Teacher", "Number Of Students" };
		int rowNum = 0;
		Cell cell = null;
		Row row = null;

		HSSFCellStyle style = createStyleForTitle(workbook);

		row = sheet.createRow(rowNum);

		for (int i = 0; i < titleSchoolField.length; i++) {
			cell = row.createCell(i, CellType.STRING);
			cell.setCellValue(titleSchoolField[i]);
			cell.setCellStyle(style);
		}

		for (School school : schools) {
			rowNum++;
			row = sheet.createRow(rowNum);

			cell = row.createCell(SchoolCells.ID.getValue(), CellType.STRING);
			cell.setCellValue(school.getId());

			cell = row.createCell(SchoolCells.NAME.getValue(), CellType.STRING);
			cell.setCellValue(school.getName());

			cell = row.createCell(SchoolCells.ADDRESS.getValue(), CellType.STRING);
			cell.setCellValue(school.getAddress());

			cell = row.createCell(SchoolCells.NUM_STUDENT.getValue(), CellType.NUMERIC);
			cell.setCellValue(school.getNumberOfTeachers());

			cell = row.createCell(SchoolCells.NUM_TEACHER.getValue(), CellType.NUMERIC);
			cell.setCellValue(school.getNumberOfStudents());
		}

		File file = new File(fileName);
		FileOutputStream fileOut = null;

		try {
			fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			check = true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return check;
	}

	@Override
	public boolean exportDataOfTeacherToFile(List<Teacher> teachers, String fileName) {
		boolean check = false;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("School Sheet");

		String[] titleSchoolField = { "Name", "Address", "Phone" };
		int rowNum = 0;
		Cell cell = null;
		Row row = null;

		HSSFCellStyle style = createStyleForTitle(workbook);

		row = sheet.createRow(rowNum);

		for (int i = 0; i < titleSchoolField.length; i++) {
			cell = row.createCell(i, CellType.STRING);
			cell.setCellValue(titleSchoolField[i]);
			cell.setCellStyle(style);
		}

		for (Teacher teacher : teachers) {
			rowNum++;
			row = sheet.createRow(rowNum);
			
			cell = row.createCell(TeacherCells.NAME.getValue(), CellType.STRING);
			cell.setCellValue(teacher.getName());
			
			cell = row.createCell(TeacherCells.ADDRESS.getValue(), CellType.STRING);
			cell.setCellValue(teacher.getAddress());

			cell = row.createCell(TeacherCells.PHONE.getValue(), CellType.STRING);
			cell.setCellValue(String.valueOf(teacher.getPhone()));

		}

		File file = new File(fileName);
		FileOutputStream fileOut = null;

		try {
			fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			check = true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return check;
	}
}
