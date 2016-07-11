package at.jsfpractice.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

// eager 屬性。當 eager 屬性為 true 時，JSF 將在啟動時創建託管 bean 並將其放入應用程序範圍
@ManagedBean(name = "comboBoxControllerTwo", eager = true)
@SessionScoped
public class ComboBoxControllerTwo implements Serializable {

	private static final long serialVersionUID = 1L;

	// setComboBox default selectItem
	public String data = "2";

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public void validarSelect(ValueChangeEvent valueChangeEvent) {
	    // Add event code here...
	    System.out.println("data is ==" + data);
	}
	
	public void subjectSelectionChanged()  
	{
		
	}
}