package sms.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;

import sms.model.School;
import sms.model.Teacher;

public class Management implements ManagementInterface {

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

	public boolean exportDataOfSchoolsToExcel(List<School> schools, String fileName) {
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

	public boolean exportDataOfTeachersToExcel(List<Teacher> teachers, String fileName) {
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

	public boolean exportDataOfSchoolsToPDF(List<School> schools, String fileName) {
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
	
	public boolean exportDataOfTeachersToPDF(List<Teacher> teachers, String fileName) {
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
	

	@Override
	public boolean exportDataOfSchoolsToHTML(List<School> schools, String fileName) {
		String dataHtml =
			html(
					head(
						title("Schools List")
					),
					body(
						h1("Schools List"),
						table(
							tr(
								td("ID"),
								td("Name"),
								td("Address"),
								td("Number Of Teacher"),
								td("Number Of Student")
							),
							each(schools, 
								school -> tr(
									td(school.getId()),
									td(school.getName()),
									td(school.getAddress()),
									td(school.getNumberOfTeachers()+""),
									td(school.getNumberOfStudents()+"")
								)
							)
						)
					)
			).render();
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(fileName);
			pw.println(dataHtml);
		}catch (IOException e) {
			// TODO: handle exception
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
		return false;
	}

	@Override
	public boolean exportDataOfTeachersToHTML(List<Teacher> teachers, String fileName) {
		String dataHtml =
				html(
						head(
							title("Teachers List")
						),
						body(
							h1("Teachers List"),
							table(
								tr(
									td("Name"),
									td("Address"),
									td("Phone")
								),
								each(teachers, 
									teacher -> tr(
										td(teacher.getName()),
										td(teacher.getAddress()),
										td(teacher.getPhone()+"")
									)
								)
							)
						)
				).render();
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(fileName);
				pw.println(dataHtml);
			}catch (IOException e) {
				// TODO: handle exception
			}finally {
				if(pw!=null) {
					pw.close();
				}
			}
			return false;
	}
	
	public void loadDatabaseOfSchool(String fileName, List<School> schools) {
		String tmpFileName = System.getProperty("user.dir")+"\\fileData\\"+fileName;
		File f = new File(tmpFileName);
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
						Integer.parseInt(stk.nextToken()), stk.nextToken(), 0, new ArrayList<Teacher>());
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
		String tmpFileName = System.getProperty("user.dir")+"\\fileData\\"+fileName;
		File f = new File(tmpFileName);
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
