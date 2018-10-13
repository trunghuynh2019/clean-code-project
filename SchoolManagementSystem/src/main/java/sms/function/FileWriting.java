package sms.function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sms.functionInterface.FileWritingITF;
import sms.functionInterface.FunctionITF;
import sms.model.School;
import sms.model.Teacher;

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

	public XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	public boolean writeSchoolToExcelFile(List<School> schools) {
		System.out.println("Reading...");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		int rowNum = 0;
		Cell cell;
		Row row;
		XSSFCellStyle style = createStyleForTitle(workbook);
		row = sheet.createRow(rowNum);

		// Create title.
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("ID");
		cell.setCellStyle(style);
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Name");
		cell.setCellStyle(style);
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Number of students");
		cell.setCellStyle(style);
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Address");
		cell.setCellStyle(style);

		// Create data.
		for (School school: schools) {
			rowNum++;
			row = sheet.createRow(rowNum);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(school.getId());
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(school.getName());
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(school.getNumOfStudents());
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(school.getAddress());
		}
		
		// Save to xlsx file
		File file = new File("export/excel/" + schoolFileName);
		file.getParentFile().mkdirs();
		try {
			workbook.write(new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		System.out.println("Success...");
		return true;
	}

	public boolean writeTeacherToExcelFile(List<School> schools) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		int rowNum = 0;
		Cell cell;
		Row row;
		XSSFCellStyle style = createStyleForTitle(workbook);
		row = sheet.createRow(rowNum);

		// Create title.
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("ID");
		cell.setCellStyle(style);
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Name");
		cell.setCellStyle(style);
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("School ID");
		cell.setCellStyle(style);

		// Create data.
		for (School school: schools) {
			List<Teacher> teachers = school.getTeachers();
			for (Teacher teacher: teachers) {
				rowNum++;
				row = sheet.createRow(rowNum);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(teacher.getId());
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(teacher.getName());
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(teacher.getSchoolId());
			}
		}
		
		// Save to xlsx file
		File file = new File("export/excel/" + teacherFileName);
		file.getParentFile().mkdirs();
		try {
			workbook.write(new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
