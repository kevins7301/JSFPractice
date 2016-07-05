package at.jsfpractice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.datatable.DataTable;


@ManagedBean(name="poiView")
@ViewScoped
public class PoiView implements Serializable {

	
	private List<Car> cars;

	@ManagedProperty("#{poiService}")
	private PoiService service;

	@PostConstruct
	public void init() {
		cars = service.createCars(100);
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setService(PoiService service) {
		this.service = service;
	}
	
	
	
	public void exportXLS(ActionEvent actionEvent)
	{
		try 
        { 
			File f = new File("D:\\Formulas.xlsx"); 
        	if(!f.exists()) 
        	{
        		f.createNewFile();
        	}
        	
        	FileOutputStream fos = new FileOutputStream(f);
			
			Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Numbers");
	        
	        
	        CellStyle cellStrStyle = workbook.createCellStyle();
	        Font hSSFFont = workbook.createFont();
	        
	        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
	        hSSFFont.setFontHeightInPoints((short) 16);
	        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        hSSFFont.setColor(HSSFColor.GREEN.index);
	        cellStrStyle.setFont(hSSFFont);
	        
	        
	        CellStyle cellNumBerStyle = workbook.createCellStyle();
	        cellNumBerStyle = workbook.createCellStyle();
	        Font numBerFont = workbook.createFont();
	        
	        numBerFont.setColor(HSSFColor.BLUE.index);
	        cellNumBerStyle.setFont(numBerFont);
	        DataFormat format = workbook.createDataFormat();
	        cellNumBerStyle.setDataFormat(format.getFormat("#,##0"));
	        
	        CellStyle cellHeadStyle = workbook.createCellStyle();
	        cellHeadStyle = workbook.createCellStyle();
	        Font headFont = workbook.createFont();
	        
//	        headFont.setColor(HSSFColor.ROSE.index);
	        
	        cellHeadStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
	        cellHeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//	        cellHeadStyle.setFont(headFont);
	        
	        Row rowHead = sheet.createRow(0);
//	        for(int i = 0 ; i < 4;  i++) 
//        	{ 
	        	Cell cell1 = rowHead.createCell(0);
	        	cell1.setCellValue("YEAR");
	        	cell1.setCellStyle(cellHeadStyle);
	        	
	        	Cell cell2 = rowHead.createCell(1);
	        	cell2.setCellValue("BRAND");
	        	cell2.setCellStyle(cellHeadStyle);
	        	
	        	Cell cell3 = rowHead.createCell(2);
	        	cell3.setCellValue("COLOR");
	        	cell3.setCellStyle(cellHeadStyle);
	        	
	        	Cell cell4 = rowHead.createCell(3);
	        	cell4.setCellValue("PRICE");
	        	cell4.setCellStyle(cellHeadStyle);
	        	
	        	Cell cell5 = rowHead.createCell(4);
	        	cell5.setCellValue("TOTAL");
	        	cell5.setCellStyle(cellHeadStyle);
//        	}
	        
	        
        	BigDecimal total = new BigDecimal("0");
	        // valueset
        	for(int i = 0 ; i < getCars().size(); i ++) 
        	{ 
        		Row row = sheet.createRow(i + 1); // i = 0 是標頭欄位
        		Car car = getCars().get(i);
        		for (int j = 0; j < car.getCarStructSize(); j++)
        		{
        			Cell cell = row.createCell(j);
        			
        			if (car.CarValue(j) instanceof String)
        			{
        				cell.setCellStyle(cellStrStyle);
        				cell.setCellValue(car.CarValue(j).toString());
        			}
        			else if (car.CarValue(j) instanceof Integer)
        			{
        				cell.setCellStyle(cellNumBerStyle);
        				cell.setCellValue(new BigDecimal(car.CarValue(j).toString()).doubleValue());
        				
        				total = total.add(new BigDecimal(car.CarValue(j).toString()));
        			}
        		}
        		
        		//
        		if (i == getCars().size()-1)
        		{
        			
        			
//    				Cell cell = row.createCell(car.getCarStructSize());
//        			cell.setCellStyle(cellNumBerStyle);
//    				cell.setCellValue(total.doubleValue());
    				
    				sheet.addMergedRegion(new CellRangeAddress(
    	                    1, //first row (0-based)
    	                    getCars().size(), //last row  (0-based)
    	                    car.getCarStructSize(), //first column (0-based)
    	                    car.getCarStructSize()  //last column  (0-based)
    	        			));
    				
    				Cell cell = (Cell) new CellRangeAddress(1, getCars().size(), car.getCarStructSize(), car.getCarStructSize());
    				cell.setCellValue("test ");
    				
    				System.err.println("getNumMergedRegions=="+sheet.getNumMergedRegions());
    				
    				
        		}
        	} 
        	
        	
        	
        	// Merge cell show total
	        //set formula cell
	         
	        //lets write to file
	        workbook.write(fos);
	        fos.close();
      	} 
        catch(java.io.IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void postProcessXLS(Object document) 
	{
    	HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
  
        HSSFCellStyle cellStrStyle = wb.createCellStyle();
        Font hSSFFont = wb.createFont();
        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
        hSSFFont.setFontHeightInPoints((short) 16);
        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        hSSFFont.setColor(HSSFColor.GREEN.index);
        cellStrStyle.setFont(hSSFFont);
        cellStrStyle.setWrapText(true);
        
        CellStyle cellNumBerStyle = wb.createCellStyle();
        Font numBerFont = wb.createFont();
        numBerFont.setColor(HSSFColor.BLUE.index);
        cellNumBerStyle.setFont(numBerFont);
        DataFormat format = wb.createDataFormat();
        cellNumBerStyle.setDataFormat(format.getFormat("#,##0"));
        cellNumBerStyle.setWrapText(true);
        
        CellStyle cellHeadStyle = wb.createCellStyle();
        cellHeadStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        cellHeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellHeadStyle.setWrapText(true);
        
        BigDecimal total = new BigDecimal("0");
 
        
		HSSFCellStyle styleHeight = wb.createCellStyle();
		styleHeight.setWrapText(true);
       
        for (int i = 0 ; i <= sheet.getLastRowNum(); i++) 
        {
    		Row row = sheet.getRow(i);
    		row.setRowStyle(styleHeight);
    		 
            for (int j = 0 ; j < row.getLastCellNum(); j++ ) 
            {
            	Cell cell = row.getCell(j);
            	
            	if (i == 0)
            	{
            		cell.setCellStyle(cellHeadStyle);
                    cell.setCellValue(cell.getStringCellValue());
                    
                    Cell celltotal = row.createCell(4);
                    celltotal.setCellValue("TOTAL");
                    celltotal.setCellStyle(cellHeadStyle);
            	}
            	else 
            	{
            		if (j == 3)
            		{
            			cell.setCellStyle(cellNumBerStyle);
            			cell.setCellValue(new BigDecimal(cell.getStringCellValue()).doubleValue());
            			
            			total = total.add(new BigDecimal(cell.getNumericCellValue()));
            		}
            		else
            		{
            			cell.setCellStyle(cellStrStyle);
                		cell.setCellValue(cell.getStringCellValue());
            		}
				}
            }
        }
			
        for (int i = 0 ; i <= sheet.getLastRowNum(); i++) 
        {
    		Row row = sheet.getRow(i);
    		row.setRowStyle(styleHeight);
        }
        
        // Merge cell index change
		sheet.addMergedRegion(new CellRangeAddress(
            1, //first row (0-based)
            sheet.getLastRowNum(), //last row  (0-based)
            4, //first column (0-based)
            4  //last column  (0-based)
			));
		
		// create cell and give cell value 
		Row row = sheet.getRow(1);
		Cell celltotal = row.createCell(4);
		
        celltotal.setCellStyle(cellNumBerStyle);
		celltotal.setCellValue(total.doubleValue());
            
		Row signatureRow = sheet.createRow(sheet.getLastRowNum() + 2);
		Cell signatureLabelCell = signatureRow.createCell(2);
		signatureLabelCell.setCellStyle(cellStrStyle);
		signatureLabelCell.setCellValue("簽核 : ");
		
		Cell signatureIputCell = signatureRow.createCell(3);
		signatureIputCell.setCellStyle(cellStrStyle);
		signatureIputCell.setCellValue("__________");
		
		// autoSize
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		
       

        
		// sourcecode 
//		HSSFWorkbook wb = (HSSFWorkbook) document;
//      wb.setSheetName(0, "postProcessXLS");
//      HSSFSheet sheet = wb.getSheetAt(0);
		
//		for (Row row2 : sheet)
//		{
//			for (Cell cell : row)
//			{
//				cell.setCellValue(cell.getStringCellValue());
//			}
//		}
		
        
    }
}