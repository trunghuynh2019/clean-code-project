package sma.repository;

import java.util.List;

import sma.entity.School;

public interface SchoolRepository {

		School findById(String id);
		List<School> findAll();
		void create(School school);
		void update(School school);
		void remove(School school);

}
