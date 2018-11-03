package sms.service.impl;

import java.util.List;
import java.util.Scanner;

import sms.model.School;
import sms.service.SchoolService;
import sms.view.MainView;
import sms.view.SchoolView;

public class SchoolServiceImpl implements SchoolService{
	private MainView view = new MainView();
	private SchoolView schoolView = new SchoolView();

	@Override
	public void viewAllSchools(List<School> schools, Scanner scanner) {
		schoolView.displayAllSchool(schools);
		view.loopAgain(scanner);
	}

	@Override
	public void addNewSchool(List<School> schools, Scanner scanner) {
		School school = new School();
		schoolView.insertSchoolData(school, scanner);
		schools.add(school);
		view.loopAgain(scanner);
	}
}
