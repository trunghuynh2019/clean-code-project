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
	private static final MainView VIEW = new MainView();
	private static final SchoolView SCHOOL_VIEW = new SchoolView();
	private static final TeacherView TEACHER_VIEW = new TeacherView();

	@Override
	public void manageTeachers(List<School> schools, Scanner scanner, SchoolRepo schoolRepo, TeacherRepo teacherRepo) {
		SCHOOL_VIEW.displayAllSchool(schools);
		VIEW.enterSchoolId();
		Optional<School> opSchool = schoolRepo.findSchoolById(schools, scanner.nextLine());
		if (!opSchool.isPresent()) {
			SCHOOL_VIEW.displaySchoolNotFound();
			return;
		} else {
			do {
				School school = opSchool.get();
				VIEW.displayManageTeacherMenu(school);
				List<Teacher> teachers = school.getTeachers();
				int _choice = scanner.nextInt();
				scanner.nextLine();
				switch (_choice) {
				case 1: {
					TEACHER_VIEW.displayAllTeachersOfSchool(school);
					break;
				}
				case 2: {
					VIEW.enterTeacherName();
					Optional<Teacher> opTeacher = teacherRepo.findTeacherByName(teachers, scanner.nextLine());
					if (!opTeacher.isPresent()) {
						TEACHER_VIEW.displayTeacherNotFound();
					} else {
						TEACHER_VIEW.displayTeacher(opTeacher.get());
					}
					break;
				}
				case 3: {
					VIEW.enterTeacherId();
					Optional<Teacher> opTeacher = teacherRepo.findTeacherById(teachers, scanner.nextInt());
					scanner.nextLine();
					if (!opTeacher.isPresent()) {
						TEACHER_VIEW.displayTeacherNotFound();
					} else {
						TEACHER_VIEW.displayTeacher(opTeacher.get());
					}
					break;
				}
				case 4: {
					VIEW.loopAgain(scanner);
					return;
				}
				}
				VIEW.loopAgain(scanner);
			} while (true);
		}
	}

}
