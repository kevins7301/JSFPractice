package at.jsfpractice.jsf22.componentuse;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@ManagedBean
@RequestScoped
public class RadioComponent 
{
    private List<HashMap> radioList;
    
    private String renderStr;
    
    public RadioComponent()
    {
    	radioList = new ArrayList();
    	for (int i = 0; i < 2; i++)
    	{
    		HashMap dataMap = new HashMap();
    		dataMap.put("value", "radio"+i);
    		radioList.add(dataMap);
    	}
    }
    
    public String getRenderStr() {
		return renderStr;
	}

	public void setRenderStr(String renderStr) {
		this.renderStr = renderStr;
	}

	public List<HashMap> getRadioList() {
		return radioList;
	}

	public void setRadioList(List<HashMap> radioList) {
		this.radioList = radioList;
	} 
}
