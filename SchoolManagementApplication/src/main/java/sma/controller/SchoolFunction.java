package sma.controller;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sma.entity.School;
import sma.entity.Teacher;

public class SchoolFunction {
	
	List<School> schools;
	List<Teacher> teachers;
	private static String[] columns = {"Id", "Name", "SchoolId", "Address","Phone"};
	private static String path = System.getProperty("user.dir");
	
	public SchoolFunction() {
		super();
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
	
	public void WritingFileToSchools(List<School> schools) 
	{
		String path = System.getProperty("user.dir");
		try {
			// open School.txt
			FileReader fr = new FileReader(path+"\\src\\main\\java\\file\\truong.txt");
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
			SchoolsWriteIntoExcel();
		}
		catch(IOException ignored)
		{
			
		}
		
	}
	
	public void SchoolsWriteIntoExcel()
	{
		
		 // Create a Workbook
        //Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
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

        // Create Cell Style for formatting Date
        //CellStyle dateCellStyle = workbook.createCellStyle();
        //dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
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
        FileOutputStream fileOut = new FileOutputStream(path+"\\src\\main\\java\\file\\schools.xlsx");
        workbook.write(fileOut);
        fileOut.close();
     // Closing the workbook
        workbook.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }      
       
	}
	
	public void SchoolsWriteIntoPDF() 
	{  
		 // Tạo đối tượng tài liệu
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);

			try {

				// Tạo đối tượng PdfWriter
				PdfWriter.getInstance(document, new FileOutputStream(path+"\\src\\main\\java\\file\\schools.pdf"));

				// Mở file để thực hiện ghi
				document.open();

				// Thêm nội dung sử dụng add function
				document.add(new Paragraph("List Of School"));
				
				//Create table
				PdfPTable t = new PdfPTable(5);
				t.setSpacingBefore(25);
				t.setSpacingAfter(25);

				PdfPCell c1 = new PdfPCell(new Phrase("ID"));
				t.addCell(c1);
				PdfPCell c2 = new PdfPCell(new Phrase("Name"));
				t.addCell(c2);
				PdfPCell c3 = new PdfPCell(new Phrase("Address"));
				t.addCell(c3);
				PdfPCell c4 = new PdfPCell(new Phrase("Phone"));
				t.addCell(c4);
				PdfPCell c5 = new PdfPCell(new Phrase("Number Of Teacher"));
				t.addCell(c5);

				for (School school: schools) {  
					t.addCell(school.getId());
					t.addCell(school.getName());
					t.addCell(school.getAddress());
					t.addCell(school.getPhone());
					t.addCell(String.valueOf(school.getNumOfTeachers()));
					
		        }  

				document.add(t);
				
				Anchor anchorTarget = new Anchor("First page of the document.");
				anchorTarget.setName("BackToTop");
				document.add(anchorTarget);

				// Đóng File
				document.close();
				System.out.println("Write file succes!");
			} catch (FileNotFoundException e) {
				System.err.println("Error: " + e.getMessage()); 
			}
			catch(DocumentException ex)
			{
				System.err.println("Error: " + ex.getMessage());
			}
	}
	
	public void SchoolsWriteIntoHTML() throws NullPointerException 
	{  
		String id, name, numberOfTeachers, address, phone;

			try {

				// Creating HTML file
				File file = new File(path+"\\src\\main\\java\\file\\schools.html");
				PrintWriter printWriter = new PrintWriter(file);
				
				printWriter.write("<html><body>");
				printWriter.write("<head><style>	table{font-family: arial, sans-serif;border-collapse: collapse; width: 100%;} td, th { border: 1px solid #dddddd; text-align: left; padding: 8px;} tr:nth-child(even){ background-color: #dddddd;}" + "</style>");
				printWriter.write("<title>Clean Code Project</title><h1>List Of Teacher</h1>");
				printWriter.write("<table>" + "<tr>" + "<th>ID</th>" + " <th>Name</th>" + "<th>Address</th>" + " <th>Phone</th> " +	" <th>Number Of Teachers</th>" +	" </tr>");				
				
				for ( School school : schools) { 
					
					id = school.getId();
					name = school.getName();
					address = school.getAddress();
					phone = school.getPhone();
					numberOfTeachers = String.valueOf(school.getNumOfTeachers());
					printWriter.write("<tr>" + "<td>" + id + "</td> <td>" + name + "</td> <td>" + address + "</td> <td>" + phone +"</td><td>" + numberOfTeachers + "</td></tr>");
							
		        }  

				printWriter.write("</table></html></body>");
	            printWriter.close();
				System.out.println("Write file success!");
			
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println("Error: " + e.getMessage()); 
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
