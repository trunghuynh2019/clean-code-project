package school_management_application.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
<<<<<<< HEAD
import java.io.InputStream;
import java.io.InputStreamReader;
=======
>>>>>>> 663dbac63f66da70460979c94e09dd3561b2c3bc
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

import com.google.gson.stream.JsonWriter;
<<<<<<< HEAD
import com.google.gson.stream.JsonReader;

=======
>>>>>>> 663dbac63f66da70460979c94e09dd3561b2c3bc
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
	private Row row;
	private Cell cell;
	private PdfPTable table;
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
	
	public List<School> importNewSchools(List<School> schools){
		
		return schoolRepo.setSchools(schools);
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
	
	public void addCellToRowExport(int cellIndex, String cellType, String cellValue, HSSFCellStyle style) {
		
		if(cellType.equals("String")) {
			cell = row.createCell(cellIndex, CellType.STRING);
		}
		else {
			cell = row.createCell(cellIndex, CellType.NUMERIC);
		}
		cell.setCellValue(cellValue);
		cell.setCellStyle(style);
	}
	
	public boolean createExcelFile(String fileName, HSSFWorkbook workbook) {
		FileOutputStream outFile;
		File file = new File("src/main/resources/output/" + fileName);
		
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
        }
        return false;
	}
	
	
	public boolean exportSchoolsToExcel(List<Integer> countTeacher, String fileName) throws IOException {
        int rownum = 0;
        int cellIndex = 0;
        int headerIndex = 0;        
        School school;
        
        String headerValue[]= {"ID","School Name","Address","Number Of Student","Number Of Teacher"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Schools");
        HSSFCellStyle style = createStyleForTitle(workbook);
        List<School> schools = showAllSchool();
        ListIterator<School> itr_sch = schools.listIterator();
        ListIterator<Integer> itr_count = countTeacher.listIterator();
        
        row = sheet.createRow(rownum);
        while(cellIndex<5 && headerIndex<headerValue.length) {
        	addCellToRowExport(cellIndex,"String",headerValue[headerIndex],style);
        	cellIndex++;
        	headerIndex++;
        }
        // Data
        while (itr_sch.hasNext() && itr_count.hasNext()) {
	        school = itr_sch.next();
	        rownum++;
            row = sheet.createRow(rownum);
            // ID (A)
            addCellToRowExport(0,"String",school.getID(),style);
            // School Name (B)
            addCellToRowExport(1,"String",school.getName(),style);
            // Address (C)
            addCellToRowExport(2,"String",school.getAddress(),style);
            // Number Of Student (D)
            addCellToRowExport(3,"Numeric",String.valueOf(school.getCountStudent()),style);
            // Number Of Teacher (E)
            addCellToRowExport(4,"Numeric",String.valueOf(itr_count.next()),style);
        }
        for(int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }
        return createExcelFile(fileName,workbook);
    }
	
	public Font chooseFontForParagraphExport(String fontType) {
		Font titlePage = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
        Font headerTable = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLDITALIC);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
		
        if(fontType.equals("titlePage")) {
        	return titlePage;
        }
        else {
        	if(fontType.equals("headerTable")) {
        		return headerTable;
        	}
        	else {
        		return normal;
        	}
        }
        
	}
	
	public void modifyAndAddNewCellToTableExportInPDF(String paragraphString, String fontType, int isUpperCase, String horizon, String vertical) {
		PdfPCell cellName;
		
		if(isUpperCase == 1) {
			cellName = new PdfPCell(new Paragraph(paragraphString.toUpperCase(),chooseFontForParagraphExport(fontType)));
		}
		else {
			cellName = new PdfPCell(new Paragraph(paragraphString,chooseFontForParagraphExport(fontType)));
		}
		
		if(horizon.equals("left")) {
			cellName.setHorizontalAlignment(Element.ALIGN_LEFT);
		}
		else {
			if(horizon.equals("right")) {
				cellName.setHorizontalAlignment(Element.ALIGN_RIGHT);
			}
			else {
				cellName.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
		}
		if(vertical.equals("left")) {
			cellName.setVerticalAlignment(Element.ALIGN_LEFT);
		}
		else {
			if(vertical.equals("right")) {
				cellName.setVerticalAlignment(Element.ALIGN_RIGHT);
			}
			else {
				cellName.setVerticalAlignment(Element.ALIGN_CENTER);
			}
		}
		table.addCell(cellName);
	}

	public boolean exportSchoolsToPDF(List<Integer> countTeacher, String fileName) {
        Document document = new Document();
        Paragraph paragraphTitle = new Paragraph(("School List").toUpperCase(), chooseFontForParagraphExport("titlePage"));
        table = new PdfPTable(5);
        School school;
        List<School> schools = showAllSchool();
        ListIterator<School> itr_sch = schools.listIterator();
        ListIterator<Integer> itr_count = countTeacher.listIterator();

        try {
        	// khởi tạo một PdfWriter truyền vào document và FileOutputStream
            PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/output/" + fileName));
            
            // mở file để thực hiện viết
            document.open();
            // thêm nội dung sử dụng add function
            paragraphTitle.setAlignment(Element.ALIGN_CENTER);
            paragraphTitle.setSpacingAfter(8);
            document.add(paragraphTitle);
        	//Khởi tạo 5 ô header, canh lề và thêm 5 ô header vào table
            modifyAndAddNewCellToTableExportInPDF("ID","headerTable",0,"center","center");
            modifyAndAddNewCellToTableExportInPDF("School's Name","headerTable",1,"center","center");
            modifyAndAddNewCellToTableExportInPDF("Address","headerTable",1,"center","center");
            modifyAndAddNewCellToTableExportInPDF("Number Of Student","headerTable",1,"center","center");
            modifyAndAddNewCellToTableExportInPDF("Number Of Teacher","headerTable",1,"center","center");
        	//Thêm data vào bảng.
            while (itr_sch.hasNext() && itr_count.hasNext()) {
            	school = itr_sch.next();
            	modifyAndAddNewCellToTableExportInPDF(school.getID(),"normal",0,"left","center");
            	modifyAndAddNewCellToTableExportInPDF(school.getName(),"normal",0,"left","center");
            	modifyAndAddNewCellToTableExportInPDF(school.getAddress(),"normal",0,"left","center");
            	modifyAndAddNewCellToTableExportInPDF(String.valueOf(school.getCountStudent()),"normal",0,"center","center");
            	modifyAndAddNewCellToTableExportInPDF(String.valueOf(itr_count.next()),"normal",0,"center","center");
            }
            table.setHeaderRows(1);
            document.add(table);
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
	
<<<<<<< HEAD
	public List<School> writeJsonStream(OutputStream out, List<School> schools) throws IOException {
		List<School> schoolsWriter;
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out,"UTF-8"));
		
		writer.setIndent("    ");
		schoolsWriter = writeSchoolsArray(writer, schools);
		writer.close();
		return schoolsWriter;
	}

    public List<School> writeSchoolsArray(JsonWriter writer, List<School> schools) throws IOException {
	    List<School> schoolsWriter = new ArrayList<School>();
    	
    	writer.beginArray();
	    for (School school : schools) {
	    	schoolsWriter.add(writeSchool(writer, school));
	    }
	    writer.endArray();
	    return schoolsWriter;
    }

    public School writeSchool(JsonWriter writer, School school) throws IOException {
=======
	public void writeJsonStream(OutputStream out, List<School> schools) throws IOException {
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out,"UTF-8"));
		
		writer.setIndent("    ");
		writeSchoolsArray(writer, schools);
		writer.close();
	}

    public void writeSchoolsArray(JsonWriter writer, List<School> schools) throws IOException {
	    
    	writer.beginArray();
	    for (School school : schools) {
	    	writeSchool(writer, school);
	    }
	    writer.endArray();
    }

    public void writeSchool(JsonWriter writer, School school) throws IOException {
>>>>>>> 663dbac63f66da70460979c94e09dd3561b2c3bc
	    
    	writer.beginObject();
	    writer.name("ID").value(school.getID());
	    writer.name("Name").value(school.getName());
	    writer.name("Address").value(school.getAddress());
	    writer.name("Number of student").value(school.getCountStudent());
	    writer.endObject();
<<<<<<< HEAD
	    return school;
    }

	public boolean jsonWriterToSaveData(String fileName) throws FileNotFoundException {
       File outFile= new File("src/main/resources/input/" + fileName);
=======
    }

	public boolean jsonWriterToSaveData(String fileName) throws FileNotFoundException {
       File outFile= new File("src/main/resources/output/" + fileName);
>>>>>>> 663dbac63f66da70460979c94e09dd3561b2c3bc
       
       outFile.getParentFile().mkdirs();
       OutputStream os = new FileOutputStream(outFile);
       
       try {
    	   writeJsonStream(os,showAllSchool());
       }
       catch (IOException e) {
    	   e.printStackTrace();
    	   return false;
       }
       return true;
	}
<<<<<<< HEAD
	
	public List<School> readJsonStream(InputStream in) throws IOException {
	    JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
	    
	    try {
	    	return readSchoolsArray(reader);
	    }
	    finally {
	    	reader.close();
	    }
	}

    public List<School> readSchoolsArray(JsonReader reader) throws IOException {
		List<School> schools = new ArrayList<School>();
		
		reader.beginArray();
		while (reader.hasNext()) {
			schools.add(readSchool(reader));
		}
		reader.endArray();
		return schools;
    }

    public School readSchool(JsonReader reader) throws IOException {
	    String ID = null;
	    String schoolName = null;
	    String address = null;
	    int countStudent = 0;
	    
	    reader.beginObject();
	    while(reader.hasNext()) {
	        String readerName = reader.nextName();
	        
	        if(readerName.equals("ID")) {
	        	ID = reader.nextString();
	        }
	        else {
		        if(readerName.equals("Name")) {
		        	schoolName = reader.nextString();
		        }
		        else {
			        if(readerName.equals("Address")) {
			        	address = reader.nextString(); 
			        }
			        else {
				        if(readerName.equals("Number of student")) {
				        	countStudent = reader.nextInt();
				        }
				        else {
				        	reader.skipValue();
				        }
			        }
		        }
	        }
	    }
	    reader.endObject();
	    return new School(ID,schoolName,address,countStudent);
    }
    
    public List<School> jsonReaderToLoadData(String fileName) throws FileNotFoundException {
        List<School> schools = null;
    	InputStream in = new FileInputStream("src/main/resources/input/" + fileName);
        
    	
        try {
     	   schools = readJsonStream(in);
     	   schools = importNewSchools(schools);
        }
        catch (IOException e) {
     	   e.printStackTrace();
        }
        return schools;
 	}
=======
>>>>>>> 663dbac63f66da70460979c94e09dd3561b2c3bc
}
