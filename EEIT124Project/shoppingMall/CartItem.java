package shoppingMall;

public class CartItem {
	
	private Product product;
	private int quantity;

	
	
	public CartItem(Product product) {
		setProduct(product);
		setQuantity(1);
	}
	
	public Product getProduct() {
		return (product);
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getPID() {
		return(getProduct().getpID());
	}
	
	public String pBrand() {
		
		return(getProduct().getpBrand());
	}
	
	public String pName() {
		return(getProduct().getpName());
	}
	
	public double getUnitPrice() {
	    return(getProduct().getPdprice());
	  }
	
	public double getStock() {
		return(getProduct().getPstack());
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int n) {
		this.quantity = n;
	}
	
	public void incrementQty() {
	    setQuantity(getQuantity() + 1);
	  }
	
	public void cancelOrder() {
		setQuantity(0);
	}
	
	public double getTotalPrice() {
		return(getQuantity()*getUnitPrice());
	}
	
}
