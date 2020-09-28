package JDBC;

public class ShoppingProduct {
	
	 private String productId;
	 private String productBrand;
	 private String productName;
	 private int productPrice;
	
	 private String productSpec;
	 private int productStack;
	 private String productWarring;
	 
	 private int people;
	
	

	public ShoppingProduct(String productId, String productBrand, String productName, int productPrice,
			String productSpec, int productStack, String productWarring) {
		super();
		this.productId = productId;
		this.productBrand = productBrand;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productSpec = productSpec;
		this.productStack = productStack;
		this.productWarring = productWarring;
	}

	public ShoppingProduct() {
		
	}

	public ShoppingProduct(String productId, String productBrand, String productName, int productPrice,
			String productSpec, int productStack, String productWarring, int people) {
		super();
		this.productId = productId;
		this.productBrand = productBrand;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productSpec = productSpec;
		this.productStack = productStack;
		this.productWarring = productWarring;
		this.people = people;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public int getProductStack() {
		return productStack;
	}

	public void setProductStack(int productStack) {
		this.productStack = productStack;
	}

	public String getProductWarring() {
		return productWarring;
	}

	public void setProductWarring(String productWarring) {
		this.productWarring = productWarring;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	
}
