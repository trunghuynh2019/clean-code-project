package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;

public interface FileImportService {
	public boolean importByTextFile(List<School> schools, Scanner scanner);
	public boolean importByJsonString(List<School> schools, String json);
}
