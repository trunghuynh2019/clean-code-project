package sma.controller;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import sma.entity.School;
import sma.entity.Teacher;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TeacherFunction {
	
	List<Teacher> teachers;
	private static String[] columns = {"Id", "Name", "SchoolId", "Address","Phone"};
	private static String path = System.getProperty("user.dir");

	private XMLEncoder xmlFile;
	public TeacherFunction() {
		super();
	}

	public TeacherFunction(List<Teacher> teachers) {
		super();
		this.teachers = teachers;
	}

	/**
	 *  Insert Teachers
	 * @param teacher
	 * @param teachers
	*/
	/**
	 * @param input
	 * @param schoolName
	 */
	public void signContractWithTeacher(Scanner input, String schoolId, List<Teacher> teachers)
	{
		
		System.out.println("Please ! Input the number of Teachers ( number > 0 )  : ");
		int numOfTeachers = input.nextInt();
		input.nextLine();
		School school = new School();
		
		for(int i = 0; i < numOfTeachers; i++)
		{
			Teacher teacher = new Teacher();
			
			System.out.println("Please ! Input Teacher Id :" );
			teacher.setId(input.nextLine());
			System.out.println("Please ! Input Teacher Name :" );
			teacher.setName(input.nextLine());
			System.out.println("Please ! Input Teacher Address :" );
			teacher.setAddress(input.nextLine());
			System.out.println("Please ! Input Teacher Phone :" );
			teacher.setPhone(input.nextLine());
			teacher.setSchoolId(schoolId);
			input.nextLine();
			this.teachers.add(teacher);	
			
		}
		SaveTeachersToXML();
		school.setTeachers(teachers); 
	}
	
	public void SaveTeachersToXML()
	{
		try {
			xmlFile = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path+"\\src\\main\\java\\data\\teachers.xml")));
			this.xmlFile.writeObject(teachers);
			xmlFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void WritingFileToTeachers(List<Teacher> teachers)
	{
		System.out.println(path);
		try {
			// open School.txt
			FileReader fr = new FileReader(path+"\\src\\main\\java\\file\\giaovien.txt");
			//write data 
			BufferedReader br = new BufferedReader(fr);
			String newLine, line;
			while ((line = br.readLine())!= null)
			{
				newLine = line.substring(2);
				String[] data = newLine.split("\\s\\|{3}\\s");
		
				Teacher teacher = new Teacher(data[0],data[1],data[2]);
				teachers.add(teacher);
				
			}
			br.close();
			fr.close();
			SaveTeachersToXML();
			TeachersWriteIntoExcel();
		}
		catch(IOException ignored)
		{
			
		}
		
	}
	
	public void TeachersWriteIntoPDF() 
	{  
		 // Tạo đối tượng tài liệu
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);

			try {

				// Tạo đối tượng PdfWriter
				PdfWriter.getInstance(document, new FileOutputStream(path+"\\src\\main\\java\\file\\teachers.pdf"));

				// Mở file để thực hiện ghi
				document.open();

				// Thêm nội dung sử dụng add function
				document.add(new Paragraph("List Of Teacher"));
				
				//Create table
				PdfPTable t = new PdfPTable(5);
				t.setSpacingBefore(25);
				t.setSpacingAfter(25);

				PdfPCell c1 = new PdfPCell(new Phrase("ID"));
				t.addCell(c1);
				PdfPCell c2 = new PdfPCell(new Phrase("School ID"));
				t.addCell(c2);
				PdfPCell c3 = new PdfPCell(new Phrase("Name"));
				t.addCell(c3);
				PdfPCell c4 = new PdfPCell(new Phrase("Address"));
				t.addCell(c4);
				PdfPCell c5 = new PdfPCell(new Phrase("Phone"));
				t.addCell(c5);

				for ( Teacher teacher : teachers) {  
					t.addCell(teacher.getId());
					t.addCell(teacher.getSchoolId());
					t.addCell(teacher.getName());
					t.addCell(teacher.getAddress());
					t.addCell(teacher.getPhone());
					
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
	
	public void TeachersWriteIntoHTML() throws NullPointerException 
	{  
		String id, schoolId, name, address, phone;
		
			try {

				// Creating HTML file
				File file = new File(path+"\\src\\main\\java\\file\\teachers.html");
				PrintWriter printWriter = new PrintWriter(file);
				
				printWriter.write("<html><body>");
				printWriter.write("<head><style>	table{font-family: arial, sans-serif;border-collapse: collapse; width: 100%;} td, th { border: 1px solid #dddddd; text-align: left; padding: 8px;} tr:nth-child(even){ background-color: #dddddd;}" + "</style>");
				printWriter.write("<title>Clean Code Project</title><h1>List Of Teacher</h1>");
				printWriter.write("<table>" + "<tr>" + "<th>ID</th>" + " <th>School ID</th>" + "<th>Name</th>" + " <th>Address</th> " +	" <th>Phone</th>" +	" </tr>");				
				
				for ( Teacher teacher : teachers) { 
					
					id = teacher.getId();
					schoolId = teacher.getSchoolId();
					name = teacher.getName();
					address = teacher.getAddress();
					phone = teacher.getPhone();
					printWriter.write("<tr>" + "<td>" + id + "</td> <td>" + schoolId + "</td> <td>" + name + "</td> <td>" + address + "</td> <td>" + phone +"</td></tr>");
							
		        }  

				printWriter.write("</table></html></body>");
	            printWriter.close();
				System.out.println("Write file succes!");
			
			} catch (FileNotFoundException e) {
				System.err.println("Error: " + e.getMessage()); 
			}
	}

	/**
	 * Find Teacher by Teacher Id	
	 * @param id
	 * @return Entity Teacher
	 */
	
	public Teacher findByTeacherId(String id)
	{
		for(Teacher object : teachers)
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
	 * Find Teacher by Teacher name
	 * @param name
	 * @return Entity Teacher
	 */
	public Teacher findByTeacherName(String name)
	{
		for(Teacher object : teachers)
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
	 * Find Teacher by School name
	 * @param name
	 * @return Entity Teacher
	 */
	public Teacher findBySchoolId(String schoolId)
	{
		for(Teacher object : teachers)
		{
			if(object.getSchoolId().equals(schoolId))
			{
				return object;
			}
			return null;
		}
		return null;
	}
	/**
	 * Find Teacher by Teacher address
	 * @param address
	 * @return Entity Teacher
	 */
	public Teacher findByTeacherAddress(String address)
	{
		for(Teacher object : teachers)
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
	 * Find Teacher by Teacher phone
	 * @param phone
	 * @return Entity Teacher
	 */
	public Teacher findByTeacherPhone(String phone)
	{
		for(Teacher object : teachers)
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
	 * display Teachers
	 * @param teacher
	 */
	public static void displayTeachers(Teacher teacher)
	{
		System.out.println("\n Teacher Id : " +teacher.getId());
		System.out.println("\n Teacher Name : " +teacher.getName());
		System.out.println("\n School Id of Teacher : " +teacher.getSchoolId());
		System.out.println("\n Teacher address : "+teacher.getAddress());
		System.out.println("\n Teacher phone : "+teacher.getPhone());

	}
	/**
	 * Excel Writer
	 * @throws IOException 
	 */
	public void TeachersWriteIntoExcel()
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        //CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Teacher");

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
            cell.setCellValue("SchoolId");
            cell.setCellStyle(headerCellStyle);
            
            cell = headerRow.createCell(3, CellType.STRING);
            cell.setCellValue("Address");
            cell.setCellStyle(headerCellStyle);
        
            cell = headerRow.createCell(4, CellType.NUMERIC);
            cell.setCellValue("Phone");
            cell.setCellStyle(headerCellStyle);

        int rowNum = 0;
        for(Teacher teacher: teachers) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(teacher.getId());

            row.createCell(1)
                    .setCellValue(teacher.getName());

            //Cell dateOfBirthCell = row.createCell(2);
            //dateOfBirthCell.setCellValue(employee.getDateOfBirth());
            //dateOfBirthCell.setCellStyle(dateCellStyle);

            row.createCell(2)
                    .setCellValue(teacher.getSchoolId());
            row.createCell(3)
            		.setCellValue(teacher.getAddress());
            row.createCell(4)
            		.setCellValue(teacher.getPhone());
            
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        try
        {
        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(path+"\\src\\main\\java\\file\\teachers.xlsx");
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
	 * Information Teacher not found
	 */
	public static void teacherNotFound() {
		System.out.println("Information Teacher not found!");
	}
	
	/**
	 * Search Teachers 
	 */
	public void searchTeachers()
	{
		if (teachers.size()== 0) {
			System.out.println("You did not input any information Teacher !");
		}
		else {
			System.out.println("\n ===================================== ");
			System.out.println("\t Dispay Teachers Information");
			for (Teacher teacher : teachers) {
				System.out.println("\t Teacher Id : " +teacher.getId());
				System.out.println("\t Teacher Name : " +teacher.getName());
				System.out.println("\t School Id of Teacher : " +teacher.getSchoolId());
				System.out.println("\t Teacher address : "+teacher.getAddress());
				System.out.println("\t Teacher phone : "+teacher.getPhone());

			}
		}
	}
	
	/**
	 * Search Teachers of specific School id
	 */
	public void searchTeachersByTeacherId(String id)
	{
		if (teachers.size()== 0) {
			System.out.println("You did not input any information Teacher !");
		}
		else {
			System.out.println("\n =============================================================== ");
			System.out.println("\t Dispay Teachers Information of specific Teacher Id");
			for (Teacher teacher : teachers) {
				if(teacher.getId()== id)
				{
					System.out.println("\t Teacher Id : " +teacher.getId());
					System.out.println("\t Teacher Name : " +teacher.getName());
					System.out.println("\t School Id of Teacher : " +teacher.getSchoolId());
					System.out.println("\t Teacher address : "+teacher.getAddress());
					System.out.println("\t Teacher phone : "+teacher.getPhone());

				}
			}
		}
	}
	
	/**
	 * Search Teachers of specific School name
	 */
	public void searchTeachersBySchoolId(String schoolId)
	{
		if (teachers.size()== 0) {
			System.out.println("You did not input any information Teacher !");
		} 
		else {
			System.out.println("\n =============================================================== ");
			System.out.println("\t Dispay Teachers Information of specific School Id");
			for (Teacher teacher : teachers) {
				if(teacher.getSchoolId().equals(schoolId))
				{
					System.out.println("\t Teacher Id : " +teacher.getId());
					System.out.println("\t Teacher Name : " +teacher.getName());
					System.out.println("\t School Id of Teacher : " +teacher.getSchoolId());
					System.out.println("\t Teacher address : "+teacher.getAddress());
					System.out.println("\t Teacher phone : "+teacher.getPhone());

				}
			}
		}
	}
	
	/**
	 * Search Teachers of specific Teacher name
	 */
	public void searchTeachersByTeacherName(String name)
	{
		if (teachers.size()== 0) {
			System.out.println("You did not input any information Teacher !");
		} 
		else {
			System.out.println("\n =============================================================== ");
			System.out.println("\t Dispay Teachers Information of specific Teacher Name");
			for (Teacher teacher : teachers) {
				if(teacher.getName().equals(name))
				{
					System.out.println("\t Teacher Id : " +teacher.getId());
					System.out.println("\t Teacher Name : " +teacher.getName());
					System.out.println("\t School Id of Teacher : " +teacher.getSchoolId());
					System.out.println("\t Teacher address : "+teacher.getAddress());
					System.out.println("\t Teacher phone : "+teacher.getPhone());

				}
			}
		}
	}
	
	/**
	 * Search Teachers of specific School address
	 */
	public void searchTeachersByTeacherAddress(String address)
	{
		if (teachers.size()== 0) {
			System.out.println("You did not input any information Teacher !");
		} 
		else {
			System.out.println("\n =============================================================== ");
			System.out.println("\t Dispay Teachers Information of specific School Address");
			for (Teacher teacher : teachers) {
				if(teacher.getAddress().equals(address))
				{
					System.out.println("\t Teacher Id : " +teacher.getId());
					System.out.println("\t Teacher Name : " +teacher.getName());
					System.out.println("\t School Id of Teacher : " +teacher.getSchoolId());
					System.out.println("\t Teacher address : "+teacher.getAddress());
					System.out.println("\t Teacher phone : "+teacher.getPhone());

				}
			}
		}
	}
	
	/**
	 * Search Teachers of specific School phone
	 */
	public void searchTeachersByTeacherPhone(String phone)
	{
		if (teachers.size()== 0) {
			System.out.println("You did not input any information Teacher !");
		} 
		else {
			System.out.println("\n =============================================================== ");
			System.out.println("\t Dispay Teachers Information of specific School Phone");
			for (Teacher teacher : teachers) {
				if(teacher.getPhone().equals(phone))
				{
					System.out.println("\t Teacher Id : " +teacher.getId());
					System.out.println("\t Teacher Name : " +teacher.getName());
					System.out.println("\t School Id of Teacher : " +teacher.getSchoolId());
					System.out.println("\t Teacher address : "+teacher.getAddress());
					System.out.println("\t Teacher phone : "+teacher.getPhone());

				}
			}
		}
	}
}
