package sms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import sms.model.School;
import sms.model.Teacher;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.service.TeacherService;
import sms.view.MainView;
import sms.view.SchoolView;
import sms.view.TeacherView;

public class TeacherServiceImpl implements TeacherService{
	private MainView view = new MainView();
	private SchoolView schoolView = new SchoolView();
	private TeacherView teacherView = new TeacherView();

	@Override
	public void manageTeachers(List<School> schools, Scanner scanner, SchoolRepo schoolRepo, TeacherRepo teacherRepo) {
		schoolView.displayAllSchool(schools);
		view.enterSchoolId();
		Optional<School> opSchool = schoolRepo.findSchoolById(schools, scanner.nextLine());
		if (!opSchool.isPresent()) {
			schoolView.displaySchoolNotFound();
			return;
		} else {
			do {
				School school = opSchool.get();
				view.displayManageTeacherMenu(school);
				List<Teacher> teachers = school.getTeachers();
				int _choice = scanner.nextInt();
				scanner.nextLine();
				switch (_choice) {
				case 1: {
					teacherView.displayAllTeachersOfSchool(school);
					break;
				}
				case 2: {
					view.enterTeacherName();
					Optional<Teacher> opTeacher = teacherRepo.findTeacherByName(teachers, scanner.nextLine());
					if (!opTeacher.isPresent()) {
						teacherView.displayTeacherNotFound();
					} else {
						teacherView.displayTeacher(opTeacher.get());
					}
					break;
				}
				case 3: {
					view.enterTeacherId();
					Optional<Teacher> opTeacher = teacherRepo.findTeacherById(teachers, scanner.nextInt());
					scanner.nextLine();
					if (!opTeacher.isPresent()) {
						teacherView.displayTeacherNotFound();
					} else {
						teacherView.displayTeacher(opTeacher.get());
					}
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
