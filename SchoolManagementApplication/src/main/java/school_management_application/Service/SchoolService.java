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
            PdfPCell headerID = new PdfPCell(new Paragraph("ID"));
            PdfPCell headerName = new PdfPCell(new Paragraph(("School's Name").toUpperCase()));
            PdfPCell headerAddress = new PdfPCell(new Paragraph(("Address").toUpperCase()));
            PdfPCell headerNoStudent = new PdfPCell(new Paragraph(("Number Of Student").toUpperCase()));
            PdfPCell headerNoTeacher = new PdfPCell(new Paragraph(("Number Of Teacher").toUpperCase()));
            
        	//Thêm 3 ô header vào table
            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);

        	//Khởi tạo 3 ô data: ô số 1 là string, ô số 2 là ảnh, ô số 3 là table
            PdfPCell data1 = new PdfPCell(new Paragraph("Data String"));
            PdfPCell data2 = new PdfPCell(Image.getInstance("framgia.png"), false);

            PdfPTable nestedTable = new PdfPTable(2);
            nestedTable.addCell(new Paragraph("Nested Cell 1"));
            nestedTable.addCell(new Paragraph("Nested Cell 2"));
            PdfPCell data3 = new PdfPCell(nestedTable);
        	//Thêm data vào bảng.
            table.addCell(data1);
            table.addCell(data2);
            table.addCell(data3);

            document.add(table);

            // đóng file
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return true;
	}
}
