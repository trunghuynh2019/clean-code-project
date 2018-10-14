package sm.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sm.entity.School;
import sm.entity.Teacher;
public class SchoolFunction {
	
	List<School> schools;
	List<Teacher> teachers;

	private static String[] columns = {"Id", "Name", "Address","Phone","Number of Teacher"};
	private static String path = System.getProperty("user.dir");
	
	public SchoolFunction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SchoolFunction(List<School> schools, List<Teacher> teachers) {
		super();
		this.schools = schools;
		this.teachers = teachers;
	}

	/**
	 * Insert Schools
	 * @param school
	 * @param schools
	 */

	public void insertSchools()
	{
		School school = new School();
		Scanner input = new Scanner(System.in);
		System.out.println("Please ! Input the number of Schools : ");
		int numOfSchools = input.nextInt();
		input.nextLine();
		for(int i = 0; i < numOfSchools; i++)
		{
			TeacherFunction teacherFunction = new TeacherFunction(teachers);
			System.out.println("Please ! Input School Id :" );
			String schoolId = input.nextLine();
			school.setId(schoolId);
			System.out.println("Please ! Input School Name :" );
			school.setName(input.nextLine());
			System.out.println("Please ! Input School Address :" );
			school.setAddress(input.nextLine());
			System.out.println("Please ! Input School Phone :" );
			school.setPhone(input.nextLine());
			this.schools.add(school);
			// Input of Teachers
			teacherFunction.signContractWithTeacher(input,schoolId,teachers);
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void WritingFileToSchools(List<School> schools)
	{
		String path = System.getProperty("user.dir");
		try {
			// open School.txt
			FileReader fr = new FileReader(path+"\\src\\file\\truong.txt");
			//write data 
			BufferedReader br = new BufferedReader(fr);
			String newLine, line; 
			while ((line = br.readLine())!= null)
			{
				newLine = line.substring(2);
				String[] data = newLine.split("\\s\\|{3}\\s");
				School school = new School(data[0],data[1],Integer.parseInt(data[2]),data[3]);
				schools.add(school);
			}
			br.close();
			fr.close();
			//SchoolsWriteIntoExcel();
		}
		catch(IOException ignored)
		{
			
		}
		
	}
	
	public void SchoolsWriteIntoExcel()
	{
		
		
        XSSFWorkbook workbook = new XSSFWorkbook();
        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        //CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("School");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        	Cell cell;
        	
            cell = headerRow.createCell(0, CellType.STRING);
            cell.setCellValue("ID");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(1, CellType.STRING);
            cell.setCellValue("Name");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(2, CellType.STRING);
            cell.setCellValue("Address");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(3, CellType.STRING);
            cell.setCellValue("Phone");
            cell.setCellStyle(headerCellStyle);
        
            cell = headerRow.createCell(4, CellType.NUMERIC);
            cell.setCellValue("Number of Teacher");
            cell.setCellStyle(headerCellStyle);

        int rowNum = 0;
        for(School school: schools) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(school.getId());

            row.createCell(1)
                    .setCellValue(school.getName());

            //Cell dateOfBirthCell = row.createCell(2);
            //dateOfBirthCell.setCellValue(employee.getDateOfBirth());
            //dateOfBirthCell.setCellStyle(dateCellStyle);

            row.createCell(2)
                    .setCellValue(school.getAddress());
            row.createCell(3)
            		.setCellValue(school.getPhone());
            row.createCell(4)
            		.setCellValue(school.getNumOfTeachers());
            
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        try
        {
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(path+"\\src\\file\\schools.xlsx");
        workbook.write(fileOut);
        fileOut.close();
     // Closing the workbook
        workbook.close();
        System.out.println("Write file success !");
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }      
       
	}
	
	/**
	 * Find Schools by School Id
	 * @param id
	 * @return School Entity
	 */

	public School findBySchoolId(String id)
	{
		for(School object : schools)
		{
			if(object.getId()== id)
			{
				return object;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Find Schools by School name
	 * @param name
	 * @return School Entity
	 */
	public School findBySchoolName(String name)
	{
		for(School object : schools)
		{
			if(object.getName().equals(name))
			{
				return object;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Find Schools by School address
	 * @param address
	 * @return School Entity
	 */
	public School findBySchoolAddress(String address)
	{
		for(School object : schools)
		{
			if(object.getAddress().equals(address))
			{
				return object;
			}
			return null;
		}
		return null;
	}

	
	/**
	 * Find Schools by School phone
	 * @param phone
	 * @return School Entity
	 */
	public School findBySchoolPhone(String phone)
	{
		for(School object : schools)
		{
			if(object.getPhone().equals(phone))
			{
				return object;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * display Schools
	 * @param school
	 */
	public void displaySchools(School school)
	{
		System.out.println("\n School Id : " +school.getId());
		System.out.println("\n School Name : " +school.getName());
		System.out.println("\n School address : "+school.getAddress());
		System.out.println("\n School phone : "+school.getPhone());
		System.out.println("\n School number of Teacher : "+school.getNumOfTeachers());
		System.out.println("\t ============With teacher============= ");
		for (Teacher teacher : teachers) {
			if(teacher.getSchoolId().equals(school.getId()))
			{
				System.out.println("\t Teacher Id : " +teacher.getId());
				System.out.println("\t Teacher Name : " +teacher.getName());
				System.out.println("\t School Id : " +teacher.getSchoolId());
				System.out.println("\t Teacher address : "+teacher.getAddress());
				System.out.println("\t Teacher phone : "+teacher.getPhone());
			}
		}
		System.out.println("\t ===================================== ");
	}
	
	/**
	 * Information School not found
	 */
	public void schoolNotFound() {
		System.out.println("Infomation School not found!");
	}
	
	/**
	 * Search Schools
	 */
	public void searchSchools()
	{
		if (schools.size()== 0) {
			System.out.println("You did not input any information School !");
		} else {
			System.out.println("\n Dispay Schools Information");
			for (School school : schools) {
				displaySchools(school);
			}
		}
	}

	/**
	 * Search Teachers of specific School id
	 */
	public void searchSchoolsBySchoolId(String id)
	{
		if (schools.size()== 0) {
			System.out.println("You did not input any information School !");
		} else {
			System.out.println("\n Dispay Schools Information of specific School Id");
			for (School school : schools) {
				if(school.getId().equals(id))
				{
					displaySchools(school);
				}
			}
		}
	}
	
	/**
	 * Search Teachers of specific School name
	 */
	public void searchSchoolsBySchoolName(String name)
	{
		if (schools.size()== 0) {
			System.out.println("You did not input any information School !");
		} else {
			System.out.println("\n Dispay Teachers Information of specific School Id");
			for (School school : schools) {
				if(school.getName().equals(name))
				{
					displaySchools(school);
				}
			}
		}
	}
	
	/**
	 * Search Teachers of specific School address
	 */
	public void searchSchoolsBySchoolAddress(String address)
	{
		if (schools.size()== 0) {
			System.out.println("You did not input any information Teacher !");
		} else {
			System.out.println("\n Dispay Schools Information of School Address");
			for (School school : schools) {
				if(school.getAddress().equals(address))
				{
					displaySchools(school);
				}
			}
		}
	}
	
	/**
	 * Search Teachers of specific School phone
	 */
	public void searchSchoolsBySchoolPhone(String phone)
	{
		if (schools.size()== 0) {
			System.out.println("You did not input any information School !");
		} else {
			System.out.println("\n Dispay Schools Information of specific School Phone");
			for (School school : schools) {
				if(school.getPhone().equals(phone))
				{
					displaySchools(school);
				}
			}
		}
	}
	
}
