package shoppingMall;

public class Product {
	
	private String pID;
	private String pBrand;
	private String pName;
	private int pdprice;
	private String pSpec;
	private int pstack;
	private String pWarning;
	private int pdPeople;
	
	public Product() {
		
	}
    public Product(String pID,String pBrand,String pName,int pdprice,int pstack) {
    	
    	setpID(pID);
    	setpBrand(pBrand);
    	setpName(pName);
    	setPdprice(pdprice);
    	setPstack(pstack);
		
	}
	public Product(String pID,String pBrand,String pName,int pdprice,String pSpec,int pstack,String pWarning,int pdPeople) {
		this.pID = pID;
		this.pBrand = pBrand;
		this.pName = pName;
		this.pdprice =pdprice;
		this.pSpec = pSpec;
		this.pstack = pstack;
		this.pWarning = pWarning;
		this.pdPeople = pdPeople;
	}
	
	public String getpID() {
		return pID;
	}
	public void setpID(String pID) {
		this.pID = pID;
	}
	public String getpBrand() {
		return pBrand;
	}
	public void setpBrand(String pBrand) {
		this.pBrand = pBrand;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getPdprice() {
		return pdprice;
	}
	public void setPdprice(int pdprice) {
		this.pdprice = pdprice;
	}
	public String getpSpec() {
		return pSpec;
	}
	public void setpSpec(String pSpec) {
		this.pSpec = pSpec;
	}
	public int getPstack() {
		return pstack;
	}
	public void setPstack(int pstack) {
		this.pstack = pstack;
	}
	public String getpWarning() {
		return pWarning;
	}
	public void setpWarning(String pWarning) {
		this.pWarning = pWarning;
	}
	public int getPdPeople() {
		return pdPeople;
	}
	public void setPdPeople(int pdPeople) {
		this.pdPeople = pdPeople;
	}
	
	

}
