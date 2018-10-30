/*
 * Title
 * 
 * @author Huy
 * @date Oct 30, 2018
 * @version 1.0
 */
package com.cleancode.education.filereader;

import java.util.List;

import com.cleancode.education.models.School;
import com.cleancode.education.models.Teacher;

public interface FileReader {
	List<School> importSchool(String fileName);
	List<Teacher> importTeacher(String fileName);
}
