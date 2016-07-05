package at.jsfpractice.jsf22.lazy;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import at.jsfpractice.jsf22.lazy.Car;

@ManagedBean(name = "dtLazyView")
@ViewScoped
public class LazyView implements Serializable {


	private LazyDataModel<Car> lazyModel;

	private Car selectedCar;

	@ManagedProperty("#{carService}")
	private CarService service;

	@PostConstruct
	public void init() {
		lazyModel = new LazyCarDataModel(service.createCars(200));
	}

	public LazyDataModel<Car> getLazyModel() {
		return lazyModel;
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public void setService(CarService service) {
		this.service = service;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void postProcessXLS(Object document) 
	{
       HSSFWorkbook wb = (HSSFWorkbook) document;
       wb.setSheetName(0, "postProcessXLS");
       HSSFSheet sheet = wb.getSheetAt(0);
       
       HSSFCellStyle cellStrStyle = wb.createCellStyle();
       Font hSSFFont = wb.createFont();
       hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
       hSSFFont.setFontHeightInPoints((short) 16);
       hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       hSSFFont.setColor(HSSFColor.GREEN.index);
       cellStrStyle.setFont(hSSFFont);
       
       CellStyle cellNumBerStyle = wb.createCellStyle();
       Font numBerFont = wb.createFont();
       numBerFont.setColor(HSSFColor.BLUE.index);
       cellNumBerStyle.setFont(numBerFont);
       DataFormat format = wb.createDataFormat();
       cellNumBerStyle.setDataFormat(format.getFormat("#,##0"));
       
       CellStyle cellHeadStyle = wb.createCellStyle();
       Font headFont = wb.createFont();
       cellHeadStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
       cellHeadStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
       
       Row rowHead = sheet.createRow(0);
       for(int i = 0 ; i < 4;  i++) 
       { 
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
       }
       
//       System.err.println("==lazyModel==" + lazyModel.);
       BigDecimal total = new BigDecimal("0");
       // valueset
       /*for(int i = 0 ; i < getCars().size(); i ++) 
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
    		   Cell cell = row.createCell(car.getCarStructSize());
    		   cell.setCellStyle(cellNumBerStyle);
    		   cell.setCellValue(total.doubleValue());
				
    		   sheet.addMergedRegion(new CellRangeAddress(
    				   1, //first row (0-based)
    				   getCars().size(), //last row  (0-based)
    				   car.getCarStructSize(), //first column (0-based)
    				   car.getCarStructSize()  //last column  (0-based)
    				   ));
    	   }
       } */
	}
}
