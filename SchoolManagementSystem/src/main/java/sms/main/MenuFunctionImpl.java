package sms.main;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import sms.model.School;
import sms.model.Teacher;
import sms.repository.SchoolRepo;
import sms.repository.TeacherRepo;
import sms.util.ExcelUtil;
import sms.util.HtmlUtil;
import sms.util.PdfUtil;
import sms.util.TextUtil;
import sms.view.MainView;
import sms.view.SchoolView;
import sms.view.TeacherView;

public class MenuFunctionImpl implements MenuFunction {
	public void viewAllSchool(List<School> schools, Scanner scanner) {
		SchoolView.displayAllSchool(schools);
		MainView.loopAgain(scanner);
	}

	public void addNewSchool(List<School> schools, Scanner scanner) {
		School school = new School();
		SchoolView.insertSchoolData(school, scanner);
		schools.add(school);
		MainView.loopAgain(scanner);
	}

	public void manageTeachersList(List<School> schools, Scanner scanner, SchoolRepo schoolRepo,
			TeacherRepo teacherRepo) {
		SchoolView.displayAllSchool(schools);
		MainView.enterSchoolId();
		Optional<School> opSchool = schoolRepo.findSchoolById(schools, scanner.nextLine());
		if (!opSchool.isPresent()) {
			SchoolView.displaySchoolNotFound();
			return;
		} else {
			do {
				School school = opSchool.get();
				MainView.displayManageTeacherMenu(school);
				List<Teacher> teachers = school.getTeachers();
				int _choice = scanner.nextInt();
				scanner.nextLine();
				switch (_choice) {
				case 1: {
					TeacherView.displayAllTeachersOfSchool(school);
					break;
				}
				case 2: {
					MainView.enterTeacherName();
					Optional<Teacher> opTeacher = teacherRepo.findTeacherByName(teachers, scanner.nextLine());
					if (!opTeacher.isPresent()) {
						TeacherView.displayTeacherNotFound();
					} else {
						TeacherView.displayTeacher(opTeacher.get());
					}
					break;
				}
				case 3: {
					MainView.enterTeacherId();
					Optional<Teacher> opTeacher = teacherRepo.findTeacherById(teachers, scanner.nextInt());
					scanner.nextLine();
					if (!opTeacher.isPresent()) {
						TeacherView.displayTeacherNotFound();
					} else {
						TeacherView.displayTeacher(opTeacher.get());
					}
					break;
				}
				case 4: {
					MainView.loopAgain(scanner);
					return;
				}
				}
				MainView.loopAgain(scanner);
			} while (true);
		}
	}

	public void insertDataByFileText(List<School> schools, Scanner scanner) {
		MainView.enterSchoolFileName();
		String schoolFile = scanner.nextLine();
		MainView.enterTeacherFileName();
		String teacherFile = scanner.nextLine();
		TextUtil fileReading = new TextUtil(schoolFile, teacherFile);

		boolean readSuccessfully = fileReading.readSchoolFromTextFile(schools)
				&& fileReading.readTeacherTextFileFile(schools);
		if (readSuccessfully) {
			MainView.readFileSuccessfully();
		} else {
			MainView.fileNotFound();
		}
		MainView.loopAgain(scanner);
	}

	public void writeDataToTextFile(List<School> schools, Scanner scanner) {
		TextUtil fileWriting = new TextUtil("truong.txt", "giaovien.txt");

		boolean writeSuccessfully = fileWriting.writeSchoolToTextFile(schools)
				&& fileWriting.writeTeacherToTextFile(schools);
		if (writeSuccessfully) {
			MainView.writeFileSuccessfully();
		} else {
			MainView.writeFileFailed();
		}
		MainView.loopAgain(scanner);
	}

	public void writeDataToExcelFile(List<School> schools, Scanner scanner) {
		ExcelUtil fileWriting = new ExcelUtil("truong.xlsx", "giaovien.xlsx");

		boolean writeSuccessfully = fileWriting.writeSchoolToExcelFile(schools)
				&& fileWriting.writeTeacherToExcelFile(schools);
		if (writeSuccessfully) {
			MainView.writeFileSuccessfully();
		} else {
			MainView.writeFileFailed();
		}
		MainView.loopAgain(scanner);
	}

	@Override
	public void writeDataToPdfFile(List<School> schools, Scanner scanner) {
		PdfUtil pdfUtil = new PdfUtil("truong.pdf", "giaovien.pdf");

		boolean writeSuccessfully = pdfUtil.writeSchoolToPdfFile(schools) && pdfUtil.writeTeacherToPdfFile(schools);
		if (writeSuccessfully) {
			MainView.writeFileSuccessfully();
		} else {
			MainView.writeFileFailed();
		}
		MainView.loopAgain(scanner);
	}

	@Override
	public void writeDataToHtmlFile(List<School> schools, Scanner scanner) {
		HtmlUtil htmlUtil = new HtmlUtil("truong.html", "giaovien.html");

		boolean writeSuccessfully = htmlUtil.writeSchoolToHtmlFile(schools) && htmlUtil.writeTeacherToHtmlFile(schools);
		if (writeSuccessfully) {
			MainView.writeFileSuccessfully();
		} else {
			MainView.writeFileFailed();
		}
		MainView.loopAgain(scanner);
	}
}
