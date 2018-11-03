package school_management_application.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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
    
    public List<Teacher> importNewTeachers(List<Teacher> teachers){
		
		return teachRepo.setTeachers(teachers);
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
	
	public boolean exportTeachersToPDF(String fileName) {
		// tạo một document
        Document document = new Document();
        Font titlePageFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
        Font headerTableFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);
        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        Paragraph paragraphTitle = new Paragraph(("Teachers List").toUpperCase(), titlePageFont);
        PdfPTable table = new PdfPTable(6);

        try {
        	// khởi tạo một PdfWriter truyền vào document và FileOutputStream
            PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/output/" + fileName));
            
            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            paragraphTitle.setAlignment(Element.ALIGN_CENTER);
            paragraphTitle.setSpacingAfter(8);
            document.add(paragraphTitle);
        	//Khởi tạo 5 ô header
            PdfPCell headerTeacherID = new PdfPCell(new Paragraph(("Teacher ID").toUpperCase(),headerTableFont));
            PdfPCell headerSchoolID = new PdfPCell(new Paragraph(("School ID").toUpperCase(),headerTableFont));
            PdfPCell headerName = new PdfPCell(new Paragraph(("School's Name").toUpperCase(),headerTableFont));
            PdfPCell headerIdentityCard = new PdfPCell(new Paragraph(("Identity Card").toUpperCase(),headerTableFont));
            PdfPCell headerPhoneNo = new PdfPCell(new Paragraph(("Phone Number").toUpperCase(),headerTableFont));
            PdfPCell headerAddress = new PdfPCell(new Paragraph(("Address").toUpperCase(),headerTableFont));
        	
            headerTeacherID.setHorizontalAlignment(Element.ALIGN_LEFT);
        	headerTeacherID.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerSchoolID.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerSchoolID.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerName.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerName.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerIdentityCard.setHorizontalAlignment(Element.ALIGN_LEFT);
        	headerIdentityCard.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerPhoneNo.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerPhoneNo.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerAddress.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerAddress.setVerticalAlignment(Element.ALIGN_CENTER);
            //Thêm 6 ô header vào table
            table.addCell(headerTeacherID);
            table.addCell(headerSchoolID);
            table.addCell(headerName);
            table.addCell(headerIdentityCard);
            table.addCell(headerPhoneNo);
            table.addCell(headerAddress);
            table.setHeaderRows(1);
            
            Teacher teacher;
            List<Teacher> teachers = showAllTeacher();
            ListIterator<Teacher> itr_teach = teachers.listIterator();
            
        	//Thêm data vào bảng.
            while (itr_teach.hasNext()) {
            	teacher = itr_teach.next();
            	ListIterator<String> itr_idenCrd = teacher.getIdentityCard().listIterator();
            	ListIterator<String> itr_phNo = teacher.getPhoneNo().listIterator();
            	Paragraph identityCard = new Paragraph();
            	Paragraph phoneNo = new Paragraph();
            	
            	PdfPCell dataTeacherID = new PdfPCell(new Paragraph(String.valueOf(teacher.getTeacherID()),normalFont));
            	PdfPCell dataSchoolID = new PdfPCell(new Paragraph(teacher.getSchoolID(),normalFont));
            	PdfPCell dataName = new PdfPCell(new Paragraph(teacher.getName(),normalFont));
            	
            	while(itr_idenCrd.hasNext()) {
            		identityCard.add(new Paragraph(itr_idenCrd.next(),normalFont));
            	}
            	PdfPCell dataIdentityCard = new PdfPCell(identityCard);
            	
            	while(itr_phNo.hasNext()) {
            		phoneNo.add(new Paragraph(itr_phNo.next(),normalFont));
            	}
            	PdfPCell dataPhoneNo = new PdfPCell(phoneNo);
            	PdfPCell dataAddress = new PdfPCell(new Paragraph(teacher.getAddress(),normalFont));
            	
            	dataTeacherID.setHorizontalAlignment(Element.ALIGN_LEFT);
            	dataTeacherID.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataSchoolID.setHorizontalAlignment(Element.ALIGN_CENTER);
            	dataSchoolID.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataName.setHorizontalAlignment(Element.ALIGN_LEFT);
            	dataName.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataIdentityCard.setHorizontalAlignment(Element.ALIGN_CENTER);
            	dataIdentityCard.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataPhoneNo.setHorizontalAlignment(Element.ALIGN_CENTER);
            	dataPhoneNo.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataAddress.setHorizontalAlignment(Element.ALIGN_LEFT);
            	dataAddress.setVerticalAlignment(Element.ALIGN_CENTER);
            	   	
            	table.addCell(dataTeacherID);
            	table.addCell(dataSchoolID);
	            table.addCell(dataName);
	            table.addCell(dataIdentityCard);
	            table.addCell(dataPhoneNo);
	            table.addCell(dataAddress);
            }
            document.add(table);
            // đóng file
            document.close();
        }
        catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}
	
	public List<Teacher> writeJsonStream(OutputStream out, List<Teacher> teachers) throws IOException {
		List<Teacher> teachersWriter;
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out,"UTF-8"));
		
		writer.setIndent("    ");
		teachersWriter = writeTeachersArray(writer, teachers);
		writer.close();
		return teachersWriter;
	}

    public List<Teacher> writeTeachersArray(JsonWriter writer, List<Teacher> teachers) throws IOException {
	    List<Teacher> teachersWriter = new ArrayList<Teacher>();
    	
    	writer.beginArray();
	    for (Teacher teacher : teachers) {
	    	teachersWriter.add(writeTeacher(writer, teacher));
	    }
	    writer.endArray();
	    return teachersWriter;
    }

    public Teacher writeTeacher(JsonWriter writer, Teacher teacher) throws IOException {
	    
    	writer.beginObject();
	    writer.name("TeacherID").value(teacher.getTeacherID());
	    writer.name("SchoolID").value(teacher.getSchoolID());
	    writer.name("Name").value(teacher.getName());
	    writer.name("IdentityCard");
	    writeDoublesArray(writer, teacher.getIdentityCard());
	    writer.name("PhoneNumber");
	    writeDoublesArray(writer, teacher.getPhoneNo());
	    writer.name("Address").value(teacher.getAddress());
	    writer.endObject();
	    return teacher;
    }
    
    public void writeDoublesArray(JsonWriter writer, List<String> arrayVariable) throws IOException {
        
    	writer.beginArray();
        for (String value : arrayVariable) {
        	writer.value(value);
        }
        writer.endArray();
    }

	public List<Teacher> jsonWriterToSaveData(String fileName) throws FileNotFoundException {
       File outFile= new File("src/main/resources/input/" + fileName);
       List<Teacher> teachers = null;
       
       outFile.getParentFile().mkdirs();
       OutputStream os = new FileOutputStream(outFile);
       
       try {
    	   teachers = writeJsonStream(os,showAllTeacher());
       }
       catch (IOException e) {
    	   e.printStackTrace();
       }
       return teachers;
	}
	
	public List<Teacher> readJsonStream(InputStream in) throws IOException {
	    JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
	    
	    try {
	    	return readTeachersArray(reader);
	    }
	    finally {
	    	reader.close();
	    }
	}

    public List<Teacher> readTeachersArray(JsonReader reader) throws IOException {
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		reader.beginArray();
		while (reader.hasNext()) {
			teachers.add(readTeacher(reader));
		}
		reader.endArray();
		return teachers;
    }

    public Teacher readTeacher(JsonReader reader) throws IOException {
	    int teacherID = 0;
    	String schoolID = null;
	    String teacherName = null;
	    List<String> identityCard = null;
	    List<String> phoneNo = null;
	    String address = null;
	    
	    reader.beginObject();
	    while(reader.hasNext()) {
	        String readerName = reader.nextName();
	        
	        switch (readerName) {
		        case "TeacherID":
		        	teacherID = reader.nextInt();
		        	break;
		        case "SchoolID":
		        	schoolID = reader.nextString();
			        break;
		        case "Name":
		        	teacherName = reader.nextString();
				    break;
		        case "IdentityCard":
		        	identityCard = readStringsArray(reader);
					break;
		        case "PhoneNumber":
	        		phoneNo = readStringsArray(reader);
					break;
		        case "Address":
					address = reader.nextString(); 
					break;
				default:
					reader.skipValue();
	        }
	    }
	    reader.endObject();
	    return new Teacher(teacherID, schoolID, teacherName, identityCard, phoneNo, address);
    }
    
    public List<String> readStringsArray(JsonReader reader) throws IOException {
        List<String> values = new ArrayList<String>();

        reader.beginArray();
        while (reader.hasNext()) {
          values.add(reader.nextString());
        }
        reader.endArray();
        return values;
      }
    
    public List<Teacher> jsonReaderToLoadData(String fileName) throws FileNotFoundException {
        List<Teacher> teachers = null;
    	InputStream in = new FileInputStream("src/main/resources/input/" + fileName);
    	
        try {
     	   teachers = readJsonStream(in);
     	   teachers = importNewTeachers(teachers);
        }
        catch (IOException e) {
     	   e.printStackTrace();
        }
        return teachers;
 	}
}
