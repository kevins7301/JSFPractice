package at.jsfpractice.jsf22.testpage;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
public class CalendarView {

	private Date calendarOne;
	private Date calendarTwo;
	private Date calendarThree;
	
	
	public Date getCalendarOne() {
		return calendarOne;
	}

	public void setCalendarOne(Date calendarOne) {
		this.calendarOne = calendarOne;
	}

	public Date getCalendarTwo() {
		return calendarTwo;
	}

	public void setCalendarTwo(Date calendarTwo) {
		this.calendarTwo = calendarTwo;
	}

	public Date getCalendarThree() {
		return calendarThree;
	}

	public void setCalendarThree(Date calendarThree) {
		this.calendarThree = calendarThree;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("display");
		requestContext.execute("PF('dlg').show()");
	}

	/*
	// fix layout resize not work 
	private long width;
	private long height;

	public void handleResize(ResizeEvent event) {
		System.err.println("change resize");
		width = event.getWidth();
		height = event.getWidth();
		System.out.println("in resize");
	}

	public long getWidth() {
		return this.width;
	}

	public long getHeight() {
		return this.height;
	}*/
}