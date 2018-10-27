package sms.main;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;

public interface DirectFunction {
	public void viewAllSchool(List<School> schools, Scanner scanner);
	public void addNewSchool(List<School> schools, Scanner scanner);
	public void manageTeachersList(List<School> schools, Scanner scanner, SchoolRepo schoolRepo, TeacherRepo teacherRepo);
	public void insertDataByFileText(List<School> schools, Scanner scanner);
	public void writeDataToTextFile(List<School> schools, Scanner scanner);
	public void writeDataToExcelFile(List<School> schools, Scanner scanner);
}
