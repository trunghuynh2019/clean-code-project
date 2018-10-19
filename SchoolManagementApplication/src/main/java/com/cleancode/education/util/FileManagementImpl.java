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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;



public class FileManagementImpl implements FileManagement{
	
	public enum SchoolCells {
		SCHOOL_ID(0), NAME(1), NUMBER_OF_TEACHER(2), ADDRESS(3), TEACHER_CMND(4);
		
		private final int value;
	    private SchoolCells(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
	public enum TeacherCells {
		CMND(0), NAME(1), SCHOOL_ID(2);
		
		private final int value;
	    private TeacherCells(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
	
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
	public boolean exportTeachersToExcel(List<School> schools, String fileName){
		String[] columns = {"CMND", "Name", "Working School's ID"};
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        Sheet sheet = workbook.createSheet("Teacher");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for(School school: schools) {
        	for (Teacher teacher : school.getTeachers()) {
        		Row row = sheet.createRow(rowNum++);

                row.createCell(TeacherCells.CMND.getValue())
                        .setCellValue(teacher.getId());

                row.createCell(TeacherCells.NAME.getValue())
                        .setCellValue(teacher.getName());

                row.createCell(TeacherCells.SCHOOL_ID.getValue()).setCellValue(teacher.getSchoolId());
        	}
            
            
        }

        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(new File("resources/" + fileName));
			workbook.write(fileOut);
			fileOut.close();
	        workbook.close();
	        return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
        

        
	}
	
	@Override
	public boolean exportTeachersToText(List<School> schools, String fileName) {
		File file = new File("resources/" + fileName);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.println("Danh sach giao vien");
			pw.println();
			for(School s : schools) {
				for (Teacher t : s.getTeachers()) {
					pw.println("- " + t.getId() + " ||| " + t.getName() + " ||| " + t.getSchoolId());
				}
				
			}
			return true;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	@Override
	public boolean exportSchoolsToExcel(List<School> schools, String fileName) {
		
		String[] columns = {"ID", "Name", "Number Of Teacher", "Address", "Teacher's CMND"};

		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        Sheet sheet = workbook.createSheet("School");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for(School school: schools) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(SchoolCells.SCHOOL_ID.getValue())
                    .setCellValue(school.getId());

            row.createCell(SchoolCells.NAME.getValue())
                    .setCellValue(school.getName());

            row.createCell(SchoolCells.NUMBER_OF_TEACHER.getValue()).setCellValue(school.getNumberOfTeacher());

            row.createCell(SchoolCells.ADDRESS.getValue())
                    .setCellValue(school.getAddress());
           row.createCell(SchoolCells.TEACHER_CMND.getValue()).setCellValue(school.getTeacherId());
            
        }

        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

       
        try {
            FileOutputStream fileOut = new FileOutputStream(new File("resources/" + fileName));
            workbook.write(fileOut);
			fileOut.close();
	        workbook.close();
	        
	        return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

       
	}

	@Override
	public boolean exportSchoolsToText(List<School> schools, String fileName) {
		File file = new File("resources/" + fileName);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.println("Danh sach truong");
			pw.println();
			for(School s : schools) {
				pw.println("- " + s.getId() + " ||| " + s.getName() + " ||| " + s.getNumberOfStudent() + " ||| " + s.getAddress());
			}
			return true;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}
