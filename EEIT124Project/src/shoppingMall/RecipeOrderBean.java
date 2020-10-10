package shoppingMall;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class RecipeOrderBean {
	
	Integer reOrderNo;
	Double	totalAmount;
	String	shippingAddress; 
	String  bno;
	String  invoiceTitle;
	Date  orderDate;
	Date  shippingDate;
	String	cancelTag;
	Set<RecipeOrderItemBean> items = new LinkedHashSet<>();
	
	public RecipeOrderBean() {
		
	}

	public RecipeOrderBean(Integer no, Double totalAmount, String shippingAddress, String bno,
			String invoiceTitle, Date orderDate, Date shippingDate, Set<RecipeOrderItemBean> items) {
		super();
		this.reOrderNo = no;
		this.totalAmount = totalAmount;
		this.shippingAddress = shippingAddress;
		this.bno = bno;
		this.invoiceTitle = invoiceTitle;
		this.orderDate = orderDate;
		this.shippingDate = shippingDate;
		this.items = items;
	}

	public Integer getReOrderNo() {
		return reOrderNo;
	}

	public void setReOrderNo(Integer reOrderNo) {
		this.reOrderNo = reOrderNo;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getCancelTag() {
		return cancelTag;
	}

	public void setCancelTag(String cancelTag) {
		this.cancelTag = cancelTag;
	}

	public Set<RecipeOrderItemBean> getItems() {
		return items;
	}

	public void setItems(Set<RecipeOrderItemBean> items) {
		this.items = items;
	}
	
	

	
	
}
