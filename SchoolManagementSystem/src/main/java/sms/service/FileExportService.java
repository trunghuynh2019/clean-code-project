package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;

public interface FileExportService {
	public void exportToTextFile(List<School> schools, Scanner scanner);
	public void exportToExcelFile(List<School> schools, Scanner scanner);
	public void exportToPdfFile(List<School> schools, Scanner scanner);
	public void exportToHtmlFile(List<School> schools, Scanner scanner);
}
