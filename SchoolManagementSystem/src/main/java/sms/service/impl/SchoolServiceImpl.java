package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.service.SchoolService;
import sms.view.MainView;
import sms.view.SchoolView;

public class SchoolServiceImpl implements SchoolService{
	private static final MainView VIEW = new MainView();
	private static final SchoolView SCHOOL_VIEW = new SchoolView();

	@Override
	public void viewAllSchools(List<School> schools, Scanner scanner) {
		SCHOOL_VIEW.displayAllSchool(schools);
		VIEW.loopAgain(scanner);
	}

	@Override
	public void addNewSchool(List<School> schools, Scanner scanner) {
		School school = new School();
		SCHOOL_VIEW.insertSchoolData(school, scanner);
		schools.add(school);
		VIEW.loopAgain(scanner);
	}
}
