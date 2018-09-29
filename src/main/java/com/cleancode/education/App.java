package com.cleancode.education;

import com.cleancode.education.models.School;
import com.cleancode.education.views.SchoolPrinter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // for example, in this case, we tests what console will print. It is not easy at all
        // so instead we test what send to it.
    	School school = new School();
    	school.setName("namewillbeaddedwithstart");
    	SchoolPrinter printer = new SchoolPrinter();
    	printer.print(school);
    }
}
