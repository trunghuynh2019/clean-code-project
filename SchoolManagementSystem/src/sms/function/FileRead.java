package sms.function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import sms.model.School;
import sms.model.Teacher;

public class FileRead {
	private static String schoolFileName;
	private static String teacherFileName;

	private static FileReader reader;
	private static BufferedReader bufferedReader;
	private static String line;

	public FileRead(String schoolFileName, String teacherFileName) {
		super();
		FileRead.schoolFileName = schoolFileName;
		FileRead.teacherFileName = teacherFileName;
	}

	public static void readSchoolFile(List<School> schools) {
		try {
			reader = new FileReader(schoolFileName);
			bufferedReader = new BufferedReader(reader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] schoolData = line.substring(2).split(" ||| ");
				School school = new School(schoolData[0], schoolData[1], Integer.parseInt(schoolData[2]),
						schoolData[3]);
				schools.add(school);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readTeacherFile(List<School> schools) {
		try {
			reader = new FileReader(teacherFileName);
			bufferedReader = new BufferedReader(reader);
			bufferedReader.readLine();
			bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				String[] teacherData = line.substring(2).split(" ||| ");
				Teacher teacher = new Teacher(Integer.parseInt(teacherData[0]), teacherData[1], teacherData[2]);
				Function function = new Function();
				School school = function.findSchoolById(schools, teacher.getSchoolId());
				if (school == null) {

				} else {
					school.addTeacher(teacher);
				}
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
