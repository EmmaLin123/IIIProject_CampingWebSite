package shoppingMall;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RecipeShoppingCart {
	
	private Map<String, RecipeOrderItem> cart = new LinkedHashMap< >();

	public RecipeShoppingCart() {
		
	}
	
	public Map<String, RecipeOrderItem> getContent(){
		return cart;
	}
	
	public void addToCart(String reid, RecipeOrderItem oi) {
		if (oi.getQty() <= 0 ) {
			return;
		}
		
		if ( cart.get(reid) == null ) {
		    cart.put(reid, oi);
		} else {
	        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			RecipeOrderItem oib = cart.get(reid);
			// 加購的數量：oi.getQty()
			// 原有的數量：oib.getQty()			
			oib.setQty(oi.getQty() + oib.getQty());
		}
	}
	
	public boolean modifyQty(String reid, int newQty) {
		if ( cart.get(reid) != null ) {
		   RecipeOrderItem  oi = cart.get(reid);
		   oi.setQty(newQty);
	       return true;
		} else {
		   return false;
		}
	}
	
	
	// 刪除某項商品
		public int deleteRecipe(String reid) {
			if ( cart.get(reid) != null ) {
		       cart.remove(reid);  // Map介面的remove()方法
		       return 1;
			} else {
			   return 0;
			}
		}
		public int getItemNumber(){   // ShoppingCart.itemNumber
			return cart.size();
		}
		//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
		public double getSubtotal(){
			double subTotal = 0 ;
			Set<String> set = cart.keySet();
			for(String n : set){
				double price    = cart.get(n).getPrice();
				double discount = cart.get(n).getDiscount();
				int    qty      = cart.get(n).getQty();
				subTotal +=  price * discount * qty;
			}
			return subTotal;
		}
		
		public void listCart() {
			Set<String> set = cart.keySet();
			for(String k : set){
				System.out.printf("BookID=%3d,  Qty=%3d,  price=%5.2f,  discount=%6.2f\n" , k , cart.get(k).getQty(), cart.get(k).getPrice(), cart.get(k).getDiscount());
			}
			System.out.println("------------------");
		}
		
		

}
