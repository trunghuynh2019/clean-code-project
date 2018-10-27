package school_management_application.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import school_management_application.DTO.SchoolDto;
import school_management_application.Model.School;
import school_management_application.Repository.SchoolRepository;


public class SchoolService {
	private SchoolRepository schoolRepo;
	private HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
	
	public SchoolService() {
		
		schoolRepo = new SchoolRepository();
	}
	
	public List<SchoolDto> importSchoolsDataByFileTXT(String fileName){
    	BufferedReader br = null;
    	String contentLine;
    	String[] data;
    	SchoolDto schoolDto;
    	List<SchoolDto> schoolDtos = new ArrayList<SchoolDto>();
    	
    	try {
    		br = new BufferedReader(new FileReader("src/main/resources/input/" + fileName));
    		br.readLine();
    		br.readLine();
    		while((contentLine = br.readLine()) != null) {
    			contentLine = contentLine.substring(2);
    			data = contentLine.split(" \\|\\|\\| ");
    			schoolDto = new SchoolDto(data[0],data[1],data[3],Integer.parseInt(data[2]));
    			if(openASchool(schoolDto) != null) {
    				schoolDtos.add(schoolDto);
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
    	return schoolDtos;
    }
	
	public School openASchool(SchoolDto schoolDto) {
		School school = findASchool(schoolDto);
	    
	    if(school != null){
	        return null;
	    }
	    school = new School();
	    school.setID(schoolDto.getID());
	    school.setName(schoolDto.getName());
	    school.setAddress(schoolDto.getAddress());
	    school.setCountStudent(school.getCountStudent());
	    schoolRepo.save(school);
	    return school;
	}

	public School updateSchoolInfor(SchoolDto schoolDto) {
		School school = schoolRepo.findByID(schoolDto.getID());
	    
	    if(school == null){
	        return null;
	    }
	    school.setName(schoolDto.getName());
	    school.setAddress(schoolDto.getAddress());
	    school.setCountStudent(schoolDto.getCountStudent());
	    schoolRepo.save(school);
	    return school;
	}

	public School closeASchool(String ID) {
		School school = schoolRepo.deleteByID(ID);
		
		if(school != null) {
			return school;
		}
		return null;
	}

	public Integer displayNumberOfSchool() {
		
		return schoolRepo.getsize();
	}
	
	public School findASchool(SchoolDto schoolDto) {
		School school = schoolRepo.findByID(schoolDto.getID());
	    
	    if(school == null){
	        return null;
	    }
	    return school;
	}

	public School findSchoolByID(String ID) {
		
		return schoolRepo.findByID(ID);
	}
	
	public List<School> findSchoolByName(String name){
		
		return schoolRepo.findByName(name);
	}
	
	public School findSchoolByAddress(String address) {
		
		return schoolRepo.findByAddress(address);
	}

	public List<School> showAllSchool(){
		
		return schoolRepo.findAll();
	}
	
	public boolean exportSchoolsToExcel(List<Integer> countTeacher, String fileName) throws IOException {
        int rownum = 0;
        Cell cell;
        Row row;
        School school;
        FileOutputStream outFile;
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Schools");
        List<School> schools = showAllSchool();
        HSSFCellStyle style = createStyleForTitle(workbook);
        ListIterator<School> itr_sch = schools.listIterator();
        ListIterator<Integer> itr_count = countTeacher.listIterator();
        File file = new File("src/main/resources/output/" + fileName);
        
        row = sheet.createRow(rownum);
        // ID
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        // School Name
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("School Name");
        cell.setCellStyle(style);
        // Address
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Address");
        cell.setCellStyle(style);
        // Number Of Student
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Number Of Student");
        cell.setCellStyle(style);
        // Number Of Teacher
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Number Of Teacher");
        cell.setCellStyle(style);
        // Data
        while (itr_sch.hasNext() && itr_count.hasNext()) {
	        school = itr_sch.next();
	        rownum++;
            row = sheet.createRow(rownum);
            // ID (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(school.getID());
            // School Name (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(school.getName());
            // Address (C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(school.getAddress());
            // Number Of Student (D)
            cell = row.createCell(3, CellType.NUMERIC);
            cell.setCellValue(school.getCountStudent());
            // Number Of Teacher
            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue(itr_count.next());
        }
        for(int i = 0; i < 4; i++) {
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

	public boolean exportSchoolsToPDF(List<Integer> countTeacher, String fileName) {
		// tạo một document
        Document document = new Document();
        Font titlePageFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
        Font headerTableFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);
        Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
        Paragraph paragraphTitle = new Paragraph(("School List").toUpperCase(), titlePageFont);
        PdfPTable table = new PdfPTable(5);

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
            PdfPCell headerID = new PdfPCell(new Paragraph("ID",headerTableFont));
            PdfPCell headerName = new PdfPCell(new Paragraph(("School's Name").toUpperCase(),headerTableFont));
            PdfPCell headerAddress = new PdfPCell(new Paragraph(("Address").toUpperCase(),headerTableFont));
            PdfPCell headerNoStudent = new PdfPCell(new Paragraph(("Number Of Student").toUpperCase(),headerTableFont));
            PdfPCell headerNoTeacher = new PdfPCell(new Paragraph(("Number Of Teacher").toUpperCase(),headerTableFont));
        	
            headerID.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerID.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerName.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerName.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerAddress.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerAddress.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerNoStudent.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerNoStudent.setVerticalAlignment(Element.ALIGN_CENTER);
        	headerNoTeacher.setHorizontalAlignment(Element.ALIGN_CENTER);
        	headerNoTeacher.setVerticalAlignment(Element.ALIGN_CENTER);
            //Thêm 5 ô header vào table
            table.addCell(headerID);
            table.addCell(headerName);
            table.addCell(headerAddress);
            table.addCell(headerNoStudent);
            table.addCell(headerNoTeacher);
            table.setHeaderRows(1);
            
            School school;
            List<School> schools = showAllSchool();
            ListIterator<School> itr_sch = schools.listIterator();
            ListIterator<Integer> itr_count = countTeacher.listIterator();
            
        	//Thêm data vào bảng.
            while (itr_sch.hasNext() && itr_count.hasNext()) {
            	school = itr_sch.next();
            	PdfPCell dataID = new PdfPCell(new Paragraph(school.getID(),normalFont));
            	PdfPCell dataName = new PdfPCell(new Paragraph(school.getName(),normalFont));
            	PdfPCell dataAddress = new PdfPCell(new Paragraph(school.getAddress(),normalFont));
            	PdfPCell dataNoStudent = new PdfPCell(new Paragraph(String.valueOf(school.getCountStudent()),normalFont));
            	PdfPCell dataNoTeacher = new PdfPCell(new Paragraph(String.valueOf(itr_count.next()),normalFont));
            	
            	dataID.setHorizontalAlignment(Element.ALIGN_LEFT);
            	dataID.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataName.setHorizontalAlignment(Element.ALIGN_LEFT);
            	dataName.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataAddress.setHorizontalAlignment(Element.ALIGN_LEFT);
            	dataAddress.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataNoStudent.setHorizontalAlignment(Element.ALIGN_CENTER);
            	dataNoStudent.setVerticalAlignment(Element.ALIGN_CENTER);
            	dataNoTeacher.setHorizontalAlignment(Element.ALIGN_CENTER);
            	dataNoTeacher.setVerticalAlignment(Element.ALIGN_CENTER);            	
            	table.addCell(dataID);
	            table.addCell(dataName);
	            table.addCell(dataAddress);
	            table.addCell(dataNoStudent);
	            table.addCell(dataNoTeacher);
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
}
