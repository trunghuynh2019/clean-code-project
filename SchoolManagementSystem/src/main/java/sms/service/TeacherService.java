package sms.service;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;

public interface TeacherService {
	public void manageTeachers(List<School> schools, Scanner scanner, SchoolRepo schoolRepo, TeacherRepo teacherRepo);
}
