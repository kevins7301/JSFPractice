package at.jsfpractice.jsf22.crudandscope;

import java.io.Serializable;
import java.math.BigDecimal;

public class CrudAndScope implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String orderNo;
	private String productName;
	private BigDecimal price;
	private BigDecimal quantity;
	private boolean editable;
		
	public CrudAndScope() {}
	
	public CrudAndScope(String orderNo, String productName, BigDecimal price, BigDecimal qty) 
	{
		this.orderNo = orderNo;
		this.productName = productName;
		this.price = price;
		this.quantity = qty;
	}
	
	public boolean isEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	
}
