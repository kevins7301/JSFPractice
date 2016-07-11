package at.jsfpractice.jsf22.testpage;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean
//@RequestScoped
@SessionScoped
public class PlayersBean 
{
	private String playerName;
	private String playerSurname;
	
	public void init()	
	{
		System.err.println("show== " + FacesContext.getCurrentInstance().isPostback());
		if	(!FacesContext.getCurrentInstance().isPostback())	
		{				
			//	do	something	with	playerName	and	playerSurname
			
			
			
		}
	}
	
	public String getPlayerName() 
	{
		return playerName;
	}
	
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}
	
	public String getPlayerSurname() 
	{
		return playerSurname;
	}
	
	public void setPlayerSurname(String playerSurname) 
	{
		this.playerSurname = playerSurname;
	}
	
	public String toUpperCase()
	{		
		System.err.println("show=toUpperCase= " + FacesContext.getCurrentInstance().isPostback());
		playerName=playerName.toUpperCase();		
		playerSurname=playerSurname.toUpperCase();								
		return	"results?faces-redirect=true&includeViewParams=true"; 
	} 
	
	public	String	validateData()	
	{		
		//validation	conditions		
		return	"scopePageTwo";	//or	other	page 
	} 
	
	public void handleFileUpload(FileUploadEvent evt) 
	{
		System.err.println("insert this===");
		//upload	specific	tasks,	see	PrimeFaces	documentation
		String playerName =	(String) evt.getComponent().getAttributes().get("playerNameAttr");		
		String playerSurname = (String) evt.getComponent().getAttributes().get("playerSurnameAttr");
		
		FacesMessage msg = new	FacesMessage("Successful",	evt.getFile().getFileName()	+	"	is	uploaded	for	"	+	playerName	+	"	"	+	playerSurname);
		FacesContext.getCurrentInstance().addMessage(null,	msg); 
	} 
}
