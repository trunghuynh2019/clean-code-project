package sms.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import sms.model.School;
import sms.model.Teacher;

public class Management implements ManagementInterface {

	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	public boolean exportDataOfSchools(List<School> schools, String fileName) {
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

			int pos = 0;
			
			cell = row.createCell(pos, CellType.STRING);
			cell.setCellValue(school.getId());

			pos++;
			cell = row.createCell(pos, CellType.STRING);
			cell.setCellValue(school.getName());

			pos++;
			cell = row.createCell(pos, CellType.STRING);
			cell.setCellValue(school.getAddress());

			pos++;
			cell = row.createCell(pos, CellType.NUMERIC);
			cell.setCellValue(school.getNumberOfTeachers());

			pos++;
			cell = row.createCell(pos, CellType.NUMERIC);
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

	public boolean exportDataOfTeachers(List<Teacher> teachers, String fileName) {
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

			int pos = 0;
			
			cell = row.createCell(pos, CellType.STRING);
			cell.setCellValue(teacher.getName());
			
			pos++;
			cell = row.createCell(pos, CellType.STRING);
			cell.setCellValue(teacher.getAddress());

			pos++;
			cell = row.createCell(pos, CellType.STRING);
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

	public void loadDatabaseOfSchool(String fileName, List<School> schools) {
		File f = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String tmp;
			StringTokenizer stk;
			while ((tmp = br.readLine()) != null) {
				stk = new StringTokenizer(tmp, "|||");
				School school = new School(stk.nextToken().toUpperCase(), stk.nextToken(),
						Integer.parseInt(stk.nextToken()), stk.nextToken(), 0, null);
				addSchool(schools, school);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadDatabaseOfTeacher(String fileName, School school) {
		File f = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String tmp;
			StringTokenizer stk;
			while ((tmp = br.readLine()) != null) {
				stk = new StringTokenizer(tmp, "|||");
				Teacher teacher = new Teacher(Integer.parseInt(stk.nextToken()), stk.nextToken(), stk.nextToken());
				signContractWithTeacher(school, teacher);
			}
			school.setNumberOfTeachers(school.getTeachers().size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addSchool(List<School> schools, School school) {
		schools.add(school);
	}

	public void signContractWithTeacher(School school, Teacher teacher) {
		school.getTeachers().add(teacher);
	}

	public School searchSchoolById(List<School> schools, String id) {
		for (School school : schools)
			if (school.getId().equals(id))
				return school;
		return null;
	}

	public Teacher searchTeacherByName(List<Teacher> teachers, String name) {
		for (Teacher teacher : teachers) {
			if (teacher.getName().equals(name)) {
				return teacher;
			}
		}
		return null;
	}

	public Teacher searchTeacherByAddress(List<Teacher> teachers, String address) {
		for (Teacher teacher : teachers) {
			if (teacher.getAddress().equals(address)) {
				return teacher;
			}
		}
		return null;
	}

}
