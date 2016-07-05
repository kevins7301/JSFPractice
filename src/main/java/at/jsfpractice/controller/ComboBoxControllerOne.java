package at.jsfpractice.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


// eager 屬性。當 eager 屬性為 true 時，JSF 將在啟動時創建託管 bean 並將其放入應用程序範圍
@ManagedBean(name = "comboBoxControllerOne", eager = true)
@SessionScoped
public class ComboBoxControllerOne implements Serializable {

	private static final long serialVersionUID = 1L;

	// setComboBox default selectItem
	public String data = "1";	

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}