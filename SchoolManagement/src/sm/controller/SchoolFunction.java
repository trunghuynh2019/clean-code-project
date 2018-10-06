package sm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import sm.entity.School;
import sm.entity.Teacher;
public class SchoolFunction {
	
	List<School> schools;
	List<Teacher> teachers;
	
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

	public void insertSchools(Scanner input)
	{
		School school = new School();
		
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
		try {
			// open School.txt
			FileReader fr = new FileReader("C:\\Users\\yentran83\\ec\\task1\\src\\file\\truong.txt");
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
		}
		catch(IOException ignored)
		{
			
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
