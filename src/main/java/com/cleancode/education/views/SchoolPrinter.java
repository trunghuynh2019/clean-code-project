package com.cleancode.education.views;

import com.cleancode.education.models.School;

public class SchoolPrinter {
	
	public void print(School school) {
		// Sis Thuy want to print the name with three start around it. For ex: 
		// ***Truong Nguyen Binh Kiem***
		ShowNameWithSpecialCharView view = new ShowNameWithSpecialCharView();
		System.out.println(view.nameWithThreeStartAround(school.getName()));
		
	}
	
}
