package shoppingMall;

public class RecipeOrderItemBean {
	
	Integer seqno;
	Integer reOrderNo;
	String  reid;
	String description;
	Integer quantity;
	Double unitPrice;
	Double discount;
	
	public RecipeOrderItemBean(Integer seqno, Integer reOrderNo, String reid, String description, Integer quantity,
			Double unitPrice, Double discount) {
		super();
		this.seqno = seqno;
		this.reOrderNo = reOrderNo;
		this.reid = reid;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
	}

	public RecipeOrderItemBean(Integer reOrderNo, String reid, String description, Integer quantity, Double unitPrice,
			Double discount) {
		super();
		this.reOrderNo = reOrderNo;
		this.reid = reid;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
	}

	public RecipeOrderItemBean() {
		
	}

	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public Integer getReOrderNo() {
		return reOrderNo;
	}

	public void setReOrderNo(Integer reOrderNo) {
		this.reOrderNo = reOrderNo;
	}

	public String getReid() {
		return reid;
	}

	public void setReid(String reid) {
		this.reid = reid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	

}
