/*
 * Title
 * 
 * @author Huy
 * @date Oct 4, 2018
 * @version 1.0
 */
package com.cleancode.education.views;

public class PrinterSupport {
	
	public String nameWithThreeStarAround(String name) {
		return "*** " + name + " ***";
	}
	
	public String idFormat(String id) {
		return "Id: " + id;
	}
	
	public String addressFormat(String address) {
		return "Address: " + address;
	}
	
	public String numberOf(String name, int amount) {
		return "Number of " + name + ": " + amount;
	}
	
	public String workingSchool(String schoolID) {
		return "Working School's ID: " + schoolID;
	}
}
