package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;

public interface SchoolService {
	public void viewAllSchools(List<School> schools, Scanner scanner);
	public void addNewSchool(List<School> schools, Scanner scanner);
}
