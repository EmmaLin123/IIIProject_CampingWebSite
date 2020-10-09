package shoppingMall;

public class RecipeOrderItem {
	
	String reid;
	String rename;
    String ingredient;
    Integer qty = 0 ; 
    Double price = 0.0 ; 
	Double discount = 1.0 ;
	
	public RecipeOrderItem(String reid, int qty, double price, double qtydiscount, String rename, String ingredient) {
		this.reid = reid;
		this.rename= rename;
		this.ingredient = ingredient;
		this.qty = qty;
		this.price = price;
		this.discount = discount;
	}

	public String getReid() {
		return reid;
	}

	public void setReid(String reid) {
		this.reid = reid;
	}

	public String getRename() {
		return rename;
	}

	public void setRename(String rename) {
		this.rename = rename;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	

}
