package at.jsfpractice.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean
@SessionScoped
public class PageController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	public String processPage1(){
		System.err.println("processPage1");
		return "success";
	}
	
	public String processPage2(){
		System.err.println("processPage2");
		return "success";
	}
	
	
	
}