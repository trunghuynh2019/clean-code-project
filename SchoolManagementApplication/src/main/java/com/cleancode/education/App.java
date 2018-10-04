package com.cleancode.education;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String format = "%-45s%-10s%-20s%20s%20s%n";
    	System.out.printf(format, "School", "ID", "Address", "Number of students", "Number of teachers");
    	System.out.printf(format, "Truong trung hoc Chuyen Nguyen Binh Khiem", "nbk-vl", "Vinh Long", "2000", "200");
    }
}
