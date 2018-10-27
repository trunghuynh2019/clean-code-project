package sms.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sms.model.School;
import sms.repository.SchoolRepo;

public class SchoolRepoImpl implements SchoolRepo {
	public Optional<School> findSchoolById(List<School> schools, String id) {
		for (School school : schools) {
			if (school.getId().equals(id)) {
				return Optional.of(school);
			}
		}
		return Optional.ofNullable(null);
	}

	public Optional<School> findSchoolByName(List<School> schools, String name) {
		for (School school : schools) {
			if (school.getName().equals(name)) {
				return Optional.of(school);
			}
		}
		return Optional.ofNullable(null);
	}

	public Optional<List<String>> getStringFromSchoolList(List<School> schools) {
		List<String> schoolsData = new ArrayList<String>();
		if (schools == null)
			return Optional.ofNullable(null);
		else {
			for (School school : schools) {
				String schoolData = "- " + school.getId() + " ||| " + school.getName() + " ||| "
						+ school.getNumOfStudents() + " ||| " + school.getAddress();
				schoolsData.add(schoolData);
			}
			return Optional.of(schoolsData);
		}
	}
}
