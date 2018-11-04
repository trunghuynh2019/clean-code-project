package sms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import sms.model.School;
import sms.model.Teacher;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.repository.impl.SchoolRepoImpl;
import sms.repository.impl.TeacherRepoImpl;
import sms.service.SchoolService;
import sms.service.TeacherService;
import sms.view.MainView;

public class TeacherServiceImpl implements TeacherService {
	private void displayTeacher(Teacher teacher) {
		System.out.println(teacher.toString());
		System.out.println("------");

	}

	private void displayTeacherNotFound() {
		System.out.println("Teacher not found!");
	}

	private void displayAllTeachers(School school) {
		if (school.getNumOfTeachers() == 0) {
			System.out.println("There is no teacher...");
		} else {
			int i = 1;
			List<Teacher> teachers = school.getTeachers();
			for (Teacher teacher : teachers) {
				System.out.println("Teacher #" + i++ + ":");
				displayTeacher(teacher);
			}
		}
	}

	private void searchTeacherByName(List<Teacher> teachers, Scanner scanner) {
		System.out.print("Name of teacher file: ");
		TeacherRepo teacherRepo = new TeacherRepoImpl();
		Optional<Teacher> opTeacher = teacherRepo.findTeacherByName(teachers, scanner.nextLine());
		if (!opTeacher.isPresent()) {
			displayTeacherNotFound();
		} else {
			displayTeacher(opTeacher.get());
		}
	}
	
	private void searchTeacherById(List<Teacher> teachers, Scanner scanner) {
		System.out.print("Enter a teacher id to search: ");
		TeacherRepo teacherRepo = new TeacherRepoImpl();
		Optional<Teacher> opTeacher = teacherRepo.findTeacherById(teachers, scanner.nextInt());
		scanner.nextLine();
		if (!opTeacher.isPresent()) {
			displayTeacherNotFound();
		} else {
			displayTeacher(opTeacher.get());
		}
	}
	
	public void insertTeacherData(Teacher teacher, Scanner scanner) {
		System.out.print("Teacher id: ");
		teacher.setId(scanner.nextInt());
		scanner.nextLine();
		System.out.print("Teacher name: ");
		teacher.setName(scanner.nextLine());
	}

	@Override
	public void manageTeachers(List<School> schools, Scanner scanner) {
		SchoolRepo schoolRepo = new SchoolRepoImpl();
		SchoolService schoolService = new SchoolServiceImpl();
		schoolService.displayAllSchool(schools);
		System.out.print("Enter a school id to manage its teachers list: ");
		Optional<School> opSchool = schoolRepo.findSchoolById(schools, scanner.nextLine());
		if (!opSchool.isPresent()) {
			System.out.println("School not found!");
			return;
		} else {
			do {
				School school = opSchool.get();
				List<Teacher> teachers = school.getTeachers();

				// This is a menu view on console. Should not remove?
				MainView view = new MainView();
				view.displayManageTeacherMenu(school);
				//
				
				int _choice = scanner.nextInt();
				scanner.nextLine();
				switch (_choice) {
				case 1: {
					displayAllTeachers(school);
				}
				case 2: {
					searchTeacherByName(teachers, scanner);
					break;
				}
				case 3: {
					searchTeacherById(teachers, scanner);
					break;
				}
				case 4: {
					view.loopAgain(scanner);
					return;
				}
				}
				view.loopAgain(scanner);
			} while (true);
		}
	}

}
