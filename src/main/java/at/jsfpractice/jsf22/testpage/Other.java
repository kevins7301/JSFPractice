package at.jsfpractice.jsf22.testpage;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;


@ManagedBean (name="otherTest")
public class Other 
{
	private boolean value1;  
    private boolean value2;
    private int activeTabIndex;
    private String tabRadioOne;
    private String tabRadioTwo;
    private String tabRadioThree;
    private String tabRadioDefault = "N";
    
    public Other()
    {
    	this.tabRadioOne = "N";
    	this.tabRadioTwo = "Y";
    }
    
    
    public String getTabRadioDefault() {
		return tabRadioDefault;
	}

	public void setTabRadioDefault(String tabRadioDefault) {
		this.tabRadioDefault = tabRadioDefault;
	}

	public String getTabRadioOne() {
		return tabRadioOne;
	}

	public void setTabRadioOne(String tabRadioOne) {
		this.tabRadioOne = tabRadioOne;
	}

	public String getTabRadioTwo() {
		return tabRadioTwo;
	}

	public void setTabRadioTwo(String tabRadioTwo) {
		this.tabRadioTwo = tabRadioTwo;
	}

	public String getTabRadioThree() {
		return tabRadioThree;
	}

	public void setTabRadioThree(String tabRadioThree) {
		this.tabRadioThree = tabRadioThree;
	}

	public void setActiveTabIndex(int activeTabIndex) 
    {
        this.activeTabIndex = activeTabIndex;
    }
    
    public int getActiveTabIndex() {
		return activeTabIndex;
	}

	public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }
 
    public boolean isValue2() {
        return value2;
    }
 
    public void setValue2(boolean value2) {
        this.value2 = value2;
    }
     
    public void onTabChange(TabChangeEvent event) 
    {
        TabView tv = (TabView) event.getComponent();
        this.setActiveTabIndex(tv.getActiveIndex());
    }
    
    public void test()
    {
    	System.err.println("tabRadioOne is = " + tabRadioOne);
    	System.err.println("tabRadioTwo is = " + tabRadioTwo);
    }
    
    public void changeOtherRadioComponent()
    {
    	
    	if (getTabRadioOne().equals("Y"))
    	{
    		tabRadioTwo = "N";
    	}
    	else 
    	{
			tabRadioTwo = "Y";
		}
    }
    
    private String show;
    
    public void setShow(String value)
    {
    	this.show = value;
    }
    
    public String getShow() {
		return show;
	}

	public void changeOtherComponent() 
    {
    	// dosomthing controll or logic 
		System.err.println("changeOtherComponent = ");
    	if (value1 == true)
    	{
    		value2 = value1;
    		activeTabIndex = 1;
    	}
    	else
    	{
    		value2 = false;
    		activeTabIndex = 0;
    	}
    }
	
	public void ValueChangeEvent(ValueChangeEvent event) 
    {
		this.tabRadioDefault = event.getNewValue().toString();
    }
}
