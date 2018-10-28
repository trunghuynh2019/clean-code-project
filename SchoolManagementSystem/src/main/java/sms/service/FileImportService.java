package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;

public interface FileImportService {
	public void importByTextFile(List<School> schools, Scanner scanner);
}
