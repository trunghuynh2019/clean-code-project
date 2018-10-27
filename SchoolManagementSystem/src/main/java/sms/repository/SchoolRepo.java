package sms.repository;

import java.util.List;
import java.util.Optional;

import sms.model.School;

public interface SchoolRepo {
	public Optional<School> findSchoolById(List<School> schoolList, String id);
	public Optional<School> findSchoolByName(List<School> schoolList, String name);
	public Optional<List<String>> getStringFromSchoolList(List<School> schools);
}
