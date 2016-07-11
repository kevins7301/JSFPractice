package at.jsfpractice.jsf22.testpage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sun.faces.facelets.PrivateApiFaceletCacheAdapter;

@ManagedBean
@SessionScoped
public class useAjaxShowAndHide implements Serializable 
{
	private List<HashMap> radioList;
    private String tabRadioOne;

    private String Show;
    
    
    public useAjaxShowAndHide()
    {
    	radioList = new ArrayList();
    	for (int i = 0; i < 4; i++)
    	{
    		HashMap dataMap = new HashMap();
    		dataMap.put("value", "radio"+i);
    		radioList.add(dataMap);
    	}
    	
    }
    
    public List<HashMap> getRadioList() {
		return radioList;
	}

	public void setRadioList(List<HashMap> radioList) {
		this.radioList = radioList;
	} 

	public String getTabRadioOne() {
		return tabRadioOne;
	}

	public void setTabRadioOne(String tabRadioOne) {
		this.tabRadioOne = tabRadioOne;
	}
	
	
    public String getShow() {
		return Show;
	}

	public void setShow(String show) {
		Show = show;
	}

	public void changeOtherRadioComponent()
    {
		this.Show = getTabRadioOne();
    }
	
	
}
