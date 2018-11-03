package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;

public interface FileExportService {
	public boolean exportToTextFile(List<School> schools, Scanner scanner);
	public boolean exportToExcelFile(List<School> schools, Scanner scanner);
	public boolean exportToPdfFile(List<School> schools, Scanner scanner);
	public boolean exportToHtmlFile(List<School> schools, Scanner scanner);
	
	public boolean exportToJsonFile(List<School> schools, Scanner scanner);
}
