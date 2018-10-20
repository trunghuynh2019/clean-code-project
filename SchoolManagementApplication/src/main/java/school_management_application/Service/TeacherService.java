package school_management_application.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import school_management_application.DTO.TeacherDto;
import school_management_application.Model.Teacher;
import school_management_application.Repository.TeacherRepository;

public class TeacherService {
	private TeacherRepository teachRepo;
	private HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    
    public TeacherService(){
        
        teachRepo = new TeacherRepository();
    }
    
    public List<TeacherDto> importTeachersDataByFileTXT(String fileName){
    	BufferedReader br = null;
    	String contentLine;
    	String[] data;
    	TeacherDto teacherDto;
    	List<TeacherDto> teacherDtos = new ArrayList<TeacherDto>();
    	
    	try {
    		br = new BufferedReader(new FileReader("src/main/resources/input/" + fileName));
    		br.readLine();
    		br.readLine();
    		while((contentLine = br.readLine()) != null) {
    			contentLine = contentLine.substring(2);
    			data = contentLine.split(" \\|\\|\\| ");
    			teacherDto = new TeacherDto(null,data[2],data[1],data[0],null,null);
    			if(signContractWithTeacher(teacherDto) != null) {
    				teacherDtos.add(teacherDto);
    			}
    		}
    	}
    	catch(Exception ex) {
    		ex.printStackTrace();
    		return null;
    	}
    	finally {
    		try {
    			if(br != null) {
    				br.close();
    			}
    		}
    		catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	return teacherDtos;
    }
    
    public Teacher signContractWithTeacher(TeacherDto teacherDto){
        Teacher teacher = findTeacher(teacherDto);
        
        if(teacher != null){
            return null;
        }
        teacher = new Teacher();
        teacher.setSchoolID(teacherDto.getSchoolID());
        teacher.setName(teacherDto.getName());
        teacher.setIdentityCard(teacherDto.getIdentityCard());
        teacher.setPhoneNo(teacherDto.getPhoneNo());
        teacher.setAddress(teacherDto.getAddress());
        teachRepo.save(teacher);
        return teacher;
    }
    
    public Teacher updateTeacherInfor(TeacherDto teacherDto){
        Teacher teacher;
        
        if(teacherDto.getTeacherID() == null){
            teacher = findTeacher(teacherDto);
        }
        else {
            teacher = teachRepo.findByTeacherID(teacherDto.getTeacherID());
        }
        teacher.setSchoolID(teacherDto.getSchoolID());
        teacher.setName(teacherDto.getName());
        if(!teacher.isHaveIdentityCard(teacherDto.getIdentityCard())){
            teacher.addIdentityCard(teacherDto.getIdentityCard());
        }
        teacher.setPhoneNo(teacherDto.getPhoneNo());
        teacher.setAddress(teacherDto.getAddress());
        teachRepo.save(teacher);
        return teacher;
    }
    
    public Teacher fireTeacher(Integer teacherID){
	    Teacher teacher = teachRepo.deleteByTeacherID(teacherID);
	    
	    if(teacher != null) {
	    	return teacher;
	    }
	    return null;
	}
	public Integer displayNumberOfTeacherInASchool(String schoolID) {
    	List<Teacher> teachers = findTeacherBySchoolID(schoolID);
    	
    	return teachers.size();
    }
    
    public Integer displayNumberOfAllTeacher(){
        
        return teachRepo.getsize();
    }
    
    public Teacher findTeacher(TeacherDto teacherDto){
	    List<Teacher> teach_name = teachRepo.findByName(teacherDto.getName());
	    Teacher teacher;
	    
	    if(teach_name == null){
	        return null;
	    }
	    ListIterator<Teacher> itr = teach_name.listIterator();
	    
	    while(itr.hasNext()){
	        teacher = itr.next();
	        if(teacher.getAddress().equals(teacherDto.getAddress()) && teacher.getSchoolID().equals(teacherDto.getSchoolID())){
	            return teacher;
	        }
	    }
	    return null;
	}
	public Teacher findTeacherByTeacherID(Integer teacherID) {
    	
    	return teachRepo.findByTeacherID(teacherID);
    }
    
    public List<Teacher> findTeacherBySchoolID(String schoolID){
    	
    	return teachRepo.findBySchoolID(schoolID);
    }
    
    public List<Teacher> findTeacherByTeacherName(String name){
    	
    	return teachRepo.findByName(name);
    }
    
    public Teacher findTeacherByIdentityCard(String identityCard) {
    	
    	return teachRepo.findByIdentityCard(identityCard);
    }
    
    public List<Teacher> findTeacherByAddress(String address){
    	
    	return teachRepo.findByAddress(address);
    }
    
	public List<Teacher> showAllTeacher(){
	    
	    return teachRepo.findAll();
	}
	
	public boolean exportTeachersToExcel(String fileName) throws IOException {
        int rownum = 0;
        Cell cell;
        Row row;
        FileOutputStream outFile;
        String idenCard = null;
        String phNo = null;
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Teachers");
        List<Teacher> teachers = showAllTeacher();
        HSSFCellStyle style = createStyleForTitle(workbook);
        File file = new File("src/main/resources/output/" + fileName);
        
        row = sheet.createRow(rownum);
        // Teacher ID
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Teacher ID");
        cell.setCellStyle(style);
        // School ID
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("School ID");
        cell.setCellStyle(style);
        // Teacher Name
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Teacher Name");
        cell.setCellStyle(style);
        // Identity Card
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Identity Card");
        cell.setCellStyle(style);
        // Phone Number
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Phone Number");
        cell.setCellStyle(style);
        // Address
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Address");
        cell.setCellStyle(style);
        // Data
        for (Teacher teacher : teachers) {
	        rownum++;
            row = sheet.createRow(rownum);
            // Teacher ID (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(teacher.getTeacherID());
            // School ID (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(teacher.getSchoolID());
            // Teacher Name (C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(teacher.getName());
            // Identity Card (D)
            ListIterator<String> itr_idenCard = teacher.getIdentityCard().listIterator();
            while(itr_idenCard.hasNext()) {
            	idenCard = idenCard + itr_idenCard.next() + ", ";
            }
            idenCard = idenCard.substring(0, idenCard.length()-2);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(idenCard);
            // Phone Number (E)
            ListIterator<String> itr_phNo = teacher.getPhoneNo().listIterator();
            while(itr_phNo.hasNext()) {
            	phNo = phNo + itr_phNo.next() + ", ";
            }
            phNo = phNo.substring(0, idenCard.length()-2);
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(phNo);
            // Address (F)
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(teacher.getAddress());
        }
        for(int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
        file.getParentFile().mkdirs();
        try {
	        outFile = new FileOutputStream(file);
	        workbook.write(outFile);
	        outFile.close();
	        workbook.close();
	        return true;
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        	return false;
        }
    }
}
