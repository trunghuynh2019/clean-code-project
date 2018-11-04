package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;

public interface SchoolService {
	public void displayAllSchool(List<School> schoolList);
	public void addNewSchool(List<School> schools, Scanner scanner);
}
