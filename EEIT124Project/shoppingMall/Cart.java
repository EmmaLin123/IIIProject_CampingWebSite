package shoppingMall;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart{
	
	private Map<String, CartItem> ProductMap = new LinkedHashMap<>();
	
	private double price;
	
	public void addProduct(Product product) {
		CartItem cartItem = ProductMap.get(product.getpID());
		
		if(cartItem == null) {
			cartItem = new CartItem(product);
			
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			ProductMap.put(product.getpID(), cartItem);
			}else {
				cartItem.setQuantity(cartItem.getQuantity());		
		}
	}
	        public double getPrice() {
				
				double totalPrice = 0;
				for(Map.Entry<String, CartItem> me: ProductMap.entrySet()) {
					
					CartItem cartItem = me.getValue();
					totalPrice = cartItem.getTotalPrice();				
				}
	                 return totalPrice;
	        }
	        public Map<String, CartItem> getProductMap(){
	        	return ProductMap;
	        }
	        
	        public void setPrice(double price) {
	        	this.price = price;
	        }
	
}
