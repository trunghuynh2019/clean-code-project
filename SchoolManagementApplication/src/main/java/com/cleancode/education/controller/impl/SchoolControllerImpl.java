/*
 * Title
 * 
 * @author Huy
 * @date Oct 13, 2018
 * @version 1.0
 */
package com.cleancode.education.controller.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cleancode.education.controller.SchoolController;
import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;
import com.cleancode.education.service.SchoolService;
import com.cleancode.education.service.impl.SchoolServiceImpl;
import com.cleancode.education.views.SchoolPrinter;

public class SchoolControllerImpl implements SchoolController{
	private SchoolService schoolService;
	private SchoolPrinter schoolPrinter = new SchoolPrinter();
	
	public SchoolControllerImpl(SchoolServiceImpl schoolServiceImpl) {
		this.schoolService = schoolServiceImpl;
	}
	
	@Override
	public void viewAllSchools() {
		List<School> schools = schoolService.getAllSchool();
		if (schools.isEmpty()) {
			System.out.println("The school list is empty.");
		} else {
			System.out.println("\nThe list of schools are below: ");
			for (School school : schools) {
				System.out.println("=================================================");
				schoolPrinter.print(school);
			}
		}
	}

	@Override
	public void addSchool(School newSchool) {
		List<School> schools = schoolService.getAllSchool();
		/*
		 * Kiểm tra nếu list rỗng -> add trực tiếp vào list
		 * Nếu không -> kiểm tra từng mã trường một để quyết định update thông tin hay add mới*/
		if (schools.isEmpty()) { 
			schools.add(newSchool);
		} else {
			boolean existedSchool = false;
			for (School school : schools) {
				if (school.equalId(newSchool)) {
					existedSchool = true;
					school.setName(newSchool.getName());
					school.setAddress(newSchool.getAddress());
					school.setNumberOfStudent(newSchool.getNumberOfStudent());
//					for (Teacher teacher: newSchool.getTeachers()) {
//						signContractWithTeacher(school, teacher);
//					}
					break;
				}
			}
			if (!existedSchool) {
				schools.add(newSchool);
			}
		}
	}

	@Override
	public void addSchoolFrom(String fileName) {
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
				addSchool(newSchool);
			}
		} catch (IOException e) {e.printStackTrace();}
		finally {
				try {
					if(br!=null) {
					br.close();}
				} catch (IOException e) {e.printStackTrace();}
		}
	}

	@Override
	public void exportSchoolsToText(String fileName) {
		List<School> schools = schoolService.getAllSchool();
		File file = new File("resources/" + fileName);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			
			pw.println("Danh sach truong");
			pw.println();
			for(School s : schools) {
				pw.println("- " + s.getId() + " ||| " + s.getName() + " ||| " + s.getNumberOfStudent() + " ||| " + s.getAddress());
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
	public void exportSchoolsToExcel() throws IOException, InvalidFormatException {
		List<School> schools = schoolService.getAllSchool();
		String[] columns = {"ID", "Name", "Number Of Teacher", "Address", "Teacher's CMND"};
		// Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */

        // Create a Sheet
        Sheet sheet = workbook.createSheet("School");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(School school: schools) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(school.getId());

            row.createCell(1)
                    .setCellValue(school.getName());

            row.createCell(2).setCellValue(school.getNumberOfTeacher());

            row.createCell(3)
                    .setCellValue(school.getAddress());
            Cell c = row.createCell(4);
            String temp = "";
            for (Teacher t : school.getTeachers()) {
            	
            	temp = c.getStringCellValue() + ",";
            	temp += t.getId();
            	c.setCellValue(temp);
            }
            
//            row.createCell(4).setCellValue(school.getTeacherId());
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("exportedschool.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
	}

	@Override
	public void signContractWithTeacherFrom(String fileName) {
		List<School> schools = schoolService.getAllSchool();
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
				for (School school : schools) {
					if (school.getId().equals(dataArr[2]))
						;
				}
				
			}
		} catch (IOException e) {e.printStackTrace();}
		finally {
				try {
					if(br!=null) {
					br.close();}
				} catch (IOException e) {e.printStackTrace();}
		}
		
	}
}
