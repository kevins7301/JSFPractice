package at.jsfpractice.jsf22.example01;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.*;
import javax.faces.event.ActionEvent;
import javax.faces.component.UIInput;
import javax.faces.validator.FacesValidator;
import javax.faces.view.ViewScoped;

import at.jsfpractice.jsf22.testDB.testDAO;

@ManagedBean (name="userBean", eager = true)
@SessionScoped
@FacesValidator("userValidator")
@ViewScoped
public class UserBean implements Serializable
{

	private static final long serialVersionUID = 1094801825228386363L;

	private String name;
	private String password;
	private String errMessage;
	
	private String toViewStr;
	
	private String[] arr1 = {"test1", "test2", "test3"};
	
	public String[] getArr1() {
		return arr1;
	}

	public void setArr1(String[] arr1) {
		this.arr1 = arr1;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setErrMessage(String errMessage) 
	{
		this.errMessage = errMessage;
	}

	public String getErrMessage() 
	{
		return errMessage;
	}

	public void setToViewStr(String toViewStr) 
	{
		this.toViewStr = toViewStr;
	}
	
	public String getToViewStr() 
	{
		return toViewStr;
	}

	public void validateModelNo(FacesContext context, UIComponent comp, Object value) 
	{
		System.out.println("inside validate method");

		StringBuffer strbuff = new StringBuffer();

		if (comp.getId().equals("username")) 
		{
			String mno = (String) value;

			if (mno.length() < 3) 
			{
				((UIInput) comp).setValid(false);

				strbuff.append("username:最少3碼");
			}
		} 
		else if (comp.getId().equals("password")) 
		{
			String mno = (String) value;

			if (mno.length() < 3) 
			{
				((UIInput) comp).setValid(false);

				strbuff.append("password:最少3碼");
			}
		}

		FacesMessage message = new FacesMessage(strbuff.toString());
		context.addMessage(comp.getClientId(context), message);
	}

	public void verifyListener(ActionEvent event) 
	{
		System.out.println("inside verifyAction method");

		if (!name.equals("admin") || !password.equals("admin")) 
		{
			errMessage = "帳號或密碼錯誤";
			toViewStr = "Failure";
		} 
		else 
		{
			errMessage = "";
			toViewStr = "Success";
		}
	}

	public String verify() 
	{
		boolean valid = testDAO.validate(name, password);
		
		if (valid) 
		{
			System.out.println("valid s-->" + valid);
			return "welcome?faces-redirect=true";//"Success";
		} 
		else 
		{
			System.out.println("valid f -->" + valid);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "loginValidate?faces-redirect=true";//"Failure";
		}
		
		
//		System.out.println("toViewStr=="+toViewStr);
//		"welcom?faces-redirect=true";
//		return toViewStr;
	}
	
	
	public String logout() 
	{
//		HttpSession session = SessionUtils.getSession();
//		session.invalidate();
//		return "login";
		
		return "loginValidate?faces-redirect=true";
	}
}