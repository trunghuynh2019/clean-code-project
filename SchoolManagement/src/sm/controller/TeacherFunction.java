package sm.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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

import sm.entity.School;
import sm.entity.Teacher;

public class TeacherFunction {
	
	List<School> schools;
	List<Teacher> teachers;

	private static String[] columns = {"Id", "Name", "SchoolId", "Address","Phone"};
	private static String path = System.getProperty("user.dir");
	public TeacherFunction() {
		super();
		// TODO Auto-generated constructor stub
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
		school.setTeachers(teachers); 
		school.setNumOfTeachers(teachers.size());
		
	}
		
	@SuppressWarnings("deprecation")
	public void WritingFileToTeachers(List<Teacher> teachers)
	{
		String path = System.getProperty("user.dir");
		System.out.println(path);
		try {
			// open School.txt
			FileReader fr = new FileReader(path+"\\src\\file\\giaovien.txt");
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
		}
		catch(IOException ignored)
		{
			
		}
		
	}
	
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
        FileOutputStream fileOut = new FileOutputStream(path+"\\src\\file\\teachers.xlsx");
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
