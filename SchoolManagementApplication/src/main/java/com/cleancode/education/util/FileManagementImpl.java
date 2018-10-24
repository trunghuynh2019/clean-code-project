/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.views.PrinterSupport;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class FileManagementImpl implements FileManagement{
	
	private PrinterSupport printerSupport = new PrinterSupport();
	private ExcelUtil excelUtil = new ExcelUtil();
	private PdfUtil pdfUtil = new PdfUtil();
	private HtmlUtil htmlUtil = new HtmlUtil();
	
	String[] teacherColumnHeader = {"CMND", "Name", "Working School's ID"};
	String[] schoolColumnHeader = {"ID", "Name", "Number Of Teacher", "Address", "Teacher's CMND"};
	
	@Override
	public List<School> getSchoolsFrom(String fileName){
		List<School> schools = new ArrayList<>();
		File file = new File("resources/" + fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				currentLine = currentLine.substring(2);
				String[] dataArr = currentLine.split(" \\|\\|\\| ");
				School newSchool = new School(dataArr[0],dataArr[1],dataArr[3],new ArrayList<Teacher>(),Integer.parseInt(dataArr[2]));
				schools.add(newSchool);
			}
			return schools;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
				try {
					if(br!=null) {
					br.close();}
				} catch (IOException e) {e.printStackTrace();}
		}
		
	}
	
	@Override
	public List<Teacher> getTeachersFrom(String fileName){
		List<Teacher> teachers = new ArrayList<>();
		File file = new File("resources/" + fileName);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			br.readLine();
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				currentLine = currentLine.substring(2);
				String[] dataArr = currentLine.split(" \\|\\|\\| ");
				Teacher teacher = new Teacher(dataArr[0],dataArr[1],dataArr[2]);
				teachers.add(teacher);
			}
			return teachers;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
				try {
					if(br!=null) {
					br.close();}
				} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	@Override
	public void exportTeachersToExcel(List<School> schools, String fileName){
		
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        Sheet sheet = excelUtil.createSheetWithHeader(workbook, "Teacher", teacherColumnHeader);

        int currentRow = 1;
        for(School school: schools) {
        	for (Teacher teacher : school.getTeachers()) {
        		excelUtil.createRowForSheetBy(sheet, currentRow++, teacher);
        	}
        }

        for(int i = 0; i < teacherColumnHeader.length; i++) {
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
	
	@Override
	public void exportTeachersToText(List<School> schools, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("resources/" + fileName);
			pw.print(printerSupport.teacherTextFileHeader());
			for(School school : schools) {
				for (Teacher teacher : school.getTeachers()) {
					pw.println(printerSupport.formatTextRow(teacher));
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	@Override
	public void exportSchoolsToExcel(List<School> schools, String fileName) {
		
		Workbook workbook = new XSSFWorkbook();

        Sheet sheet = excelUtil.createSheetWithHeader(workbook, "School", schoolColumnHeader);

        int currentRow = 1;
        for(School school : schools) {
        	excelUtil.createRowForSheetBy(sheet, currentRow++, school);
        }

        for(int i = 0; i < schoolColumnHeader.length; i++) {
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
	public void exportSchoolsToText(List<School> schools, String fileName) {
		File file = new File("resources/" + fileName);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.print(printerSupport.schoolTextFileHeader());
			for(School school : schools) {
				pw.println(printerSupport.formatTextRow(school));
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	

	@Override
	public void exportSchoolsToPdf(List<School> schools, String fileName) {
		Document pdfDoc = new Document(PageSize.A4);
		try {
			
			PdfWriter.getInstance(pdfDoc, new FileOutputStream("resources/"+ fileName))
				.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			pdfDoc.open();
	
			Paragraph para = new Paragraph("Danh sach truong\n\n");
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDoc.add(para);
			
			
			PdfPTable table = new PdfPTable(schoolColumnHeader.length);
			table.setWidthPercentage(90);
			pdfUtil.addTableHeader(table, schoolColumnHeader);
			for (School school : schools) {
				pdfUtil.addRows(table, school);
			}
			 
			pdfDoc.add(table);
			
			
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}finally {
			pdfDoc.close();
		}
		
	}

	@Override
	public void exportTeachersToPdf(List<School> schools, String fileName) {
		Document pdfDoc = new Document(PageSize.A4);
		try {
			
			PdfWriter.getInstance(pdfDoc, new FileOutputStream("resources/"+ fileName))
				.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			pdfDoc.open();
	
			Paragraph para = new Paragraph("Danh sach giao vien\n\n");
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDoc.add(para);
			
			
			PdfPTable table = new PdfPTable(teacherColumnHeader.length);
			table.setWidthPercentage(90);
			pdfUtil.addTableHeader(table, teacherColumnHeader);
			for (School school : schools) {
				for (Teacher teacher : school.getTeachers()) {
					pdfUtil.addRows(table, teacher);
				}
			}
			 
			pdfDoc.add(table);
			
			
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}finally {
			pdfDoc.close();
		}
	}

	@Override
	public void exportTeachersToHtml(List<School> schools, String fileName) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("resources/" + fileName);
			pw.println(htmlUtil.teacherDataToHtml(schools));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	@Override
	public void exportSchoolsToHtml(List<School> schools, String fileName) {

		PrintWriter pw = null;
		try {
			pw = new PrintWriter("resources/" + fileName);
			pw.println(htmlUtil.schoolDataToHtml(schools));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
}
