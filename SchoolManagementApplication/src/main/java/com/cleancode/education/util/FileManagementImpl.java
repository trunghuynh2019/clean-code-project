/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.util;

import static j2html.TagCreator.h1;
import static j2html.TagCreator.p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.views.PrinterSupport;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class FileManagementImpl implements FileManagement{
	
	private PrinterSupport printerSupport = new PrinterSupport();
	private ExcelUtil excelUtil = new ExcelUtil();
	
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
		String[] headerColumns = {"CMND", "Name", "Working School's ID"};
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        Sheet sheet = excelUtil.createSheetWithHeader(workbook, "Teacher", headerColumns);

        int currentRow = 1;
        for(School school: schools) {
        	for (Teacher teacher : school.getTeachers()) {
        		excelUtil.createRowForSheetBy(sheet, currentRow++, teacher);
        	}
        }

        for(int i = 0; i < headerColumns.length; i++) {
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
		
		String[] headerColumns = {"ID", "Name", "Number Of Teacher", "Address", "Teacher's CMND"};

		Workbook workbook = new XSSFWorkbook();

        Sheet sheet = excelUtil.createSheetWithHeader(workbook, "School", headerColumns);

        int currentRow = 1;
        for(School school : schools) {
        	excelUtil.createRowForSheetBy(sheet, currentRow++, school);
        }

        for(int i = 0; i < headerColumns.length; i++) {
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
	
	

	private void addTableHeader(PdfPTable table) {
	    Stream.of("column header 1", "column header 2", "column header 3")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
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
	private void addRows(PdfPTable table) {
	    table.addCell("row 1, col 1");
	    table.addCell("row 1, col 2");
	    table.addCell("row 1, col 3");
	}

	@Override
	public void exportSchoolsToPdf(List<School> schools, String fileName) {
		Document pdfDoc = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(pdfDoc, new FileOutputStream("resources/"+ fileName))
					.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			pdfDoc.open();
			PdfPTable table = new PdfPTable(3);
			addTableHeader(table);
			addRows(table);
			addRows(table);
			 
			pdfDoc.add(table);
			pdfDoc.add(new Paragraph("\n"));
			Paragraph para = new Paragraph("Hello world" + "\n");
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			pdfDoc.add(para);
			
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}finally {
			pdfDoc.close();
		}
		
	}

	@Override
	public void exportTeachersToPdf(List<School> schools, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportTeachersToHtml(List<School> schools, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportSchoolsToHtml(List<School> schools, String fileName) {
//		StringBuilder htmlBuilder = new StringBuilder();
//		htmlBuilder.append("<html>");
//		htmlBuilder.append("<head><title>Hello World</title></head>");
//		htmlBuilder.append("<body><p>Look at my body!</p></body>");
//		htmlBuilder.append("</html>");
//		String html = htmlBuilder.toString();
		
//		File file = new File("resources/" + fileName);
//		File file = new File("resources/" + fileName);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("resources/" + fileName);
			pw.println(h1("Danh sach truong").render());
			pw.println();
			for(School s : schools) {
				pw.println(p("- " + s.getId() + " ||| " + s.getName() + " ||| " + s.getNumberOfStudent() + " ||| " + s.getAddress()).render());
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
	
}
