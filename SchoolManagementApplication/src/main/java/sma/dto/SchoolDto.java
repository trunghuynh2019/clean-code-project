package sma.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;
import sma.entity.School;
import sma.entity.Teacher;

@Data @ToString
public class SchoolDto {

	private String id;
	private String name;
	private String address;
	private String phone;
	private Integer numberOfTeacher;
	
	private List<Teacher> teachers ;
	
}
