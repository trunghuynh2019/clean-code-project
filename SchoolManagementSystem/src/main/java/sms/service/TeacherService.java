package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;

public interface TeacherService {
	public void manageTeachers(List<School> schools, Scanner scanner);
}
