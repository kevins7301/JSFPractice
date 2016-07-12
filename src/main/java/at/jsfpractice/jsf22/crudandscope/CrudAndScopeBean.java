package at.jsfpractice.jsf22.crudandscope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import at.jsfpractice.jsf22.testDB.testDAO;

@ManagedBean(name="crudAndScopeBean")
@ViewScoped
public class CrudAndScopeBean implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private ArrayList<CrudAndScope> dataList = new ArrayList<CrudAndScope>();

	private CrudAndScope item = new CrudAndScope();
	private String editOrderNo;
	
	@PostConstruct
	public void init() 
	{
		getDBdataAndSet();
	}
	
    public CrudAndScope getItem() {
        return this.item;
    }
	
	public ArrayList<CrudAndScope> getDataList() {
		
		return dataList;
	}

	public String saveEditAction() 
	{
		for (CrudAndScope crudAndScopeData : dataList) 
		{
			if (crudAndScopeData.getOrderNo() == editOrderNo)
			{
				String updateSqlStr = "UPDATE ORDERDATA " + 
	        		"set PRODUCTNAME = '" + crudAndScopeData.getProductName() + "', " + 
	        		"PRICE = " + crudAndScopeData.getPrice() + ", " + 
	        		"QUANTITY = " + crudAndScopeData.getQuantity() + " " + 
	        		"where NUMBER = '" + crudAndScopeData.getOrderNo() + "'";
		        
		        if (testDAO.updateData(updateSqlStr))
		        {
		        	getDBdataAndSet();
		        }
				
				crudAndScopeData.setEditable(false);
				break;
			}
		}
		// return to current page
		return null;
	}
	
	public String editAction(CrudAndScope crudAndScope) 
	{
		editOrderNo = crudAndScope.getOrderNo();
		
		crudAndScope.setEditable(true);
		
		return null;
	}
	
	
	public String deleteAction(CrudAndScope crudAndScope) 
	{
		String deleteSqlStr = "DELETE FROM ORDERDATA " + 
				"where NUMBER = '" + crudAndScope.getOrderNo() + "'";
	        
        if (testDAO.deleteData(deleteSqlStr))
        {
        	getDBdataAndSet();
        }
		
		return null;
	}
 
	public void add() 
	{
        // DAO save the add
        item.setOrderNo(getOrderNO());
        
        String insertSqlStr = "INSERT INTO ORDERDATA VALUES('" + 
        		item.getOrderNo() + "', '" + 
        		item.getProductName() + "', " + 
        		item.getPrice() + ", " + 
        		item.getQuantity() + ")";
        
        if (testDAO.insertData(insertSqlStr))
        {
        	dataList.clear();
        	getDBdataAndSet();
        }
        
        item = new CrudAndScope();
    }
    
    public void resetAdd() 
    {
        item = new CrudAndScope();
    }

    private String getOrderNO()
    {
    	String no = null;
    	
    	if (dataList.isEmpty())
		{
			no = "A0001";
		}
		else
		{
			CrudAndScope data = (CrudAndScope)dataList.get(dataList.size()-1);
			String orderNo = data.getOrderNo();
			
			orderNo = orderNo.substring(1, 5);
			
			BigDecimal numberConvert = new BigDecimal(orderNo).add(new BigDecimal(1));
			numberConvert = numberConvert.setScale(0);
			
			if (numberConvert.toPlainString().length() == 1)
			{
				no = "A000" + numberConvert.toPlainString();
			}
			else if (numberConvert.toPlainString().length() == 2)
			{
				no = "A00" + numberConvert.toPlainString();
			}
			else if (numberConvert.toPlainString().length() == 3)
			{
				no = "A0" + numberConvert.toPlainString();
			}
			else if (numberConvert.toPlainString().length() == 4)
			{
				no = "A" + numberConvert.toPlainString();
			}
		}
    	
    	return no;
    }
    
    private void getDBdataAndSet()
    {
    	dataList.clear();
    	
    	String[] key = {"NUMBER", "PRODUCTNAME", "PRICE", "QUANTITY"};
		String sqlStr = "SELECT * FROM ORDERDATA ORDER BY NUMBER ASC";
    	ArrayList daoDataList = (ArrayList)testDAO.getDataMap(key, sqlStr).clone();
		
		for (int i = 0; i < daoDataList.size(); i++)
		{
			HashMap dataMap = (HashMap)daoDataList.get(i);
			
			dataList.add(new CrudAndScope(
					dataMap.get("NUMBER").toString(), 
					dataMap.get("PRODUCTNAME").toString(), 
					new BigDecimal(dataMap.get("PRICE").toString()), 
					new BigDecimal(dataMap.get("QUANTITY").toString())));
			
		}
    }
}
