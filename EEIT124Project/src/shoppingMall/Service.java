package shoppingMall;

import java.util.ArrayList;
import java.util.List;

import JDBC.ShoppingProduct;
import JDBC.shoppingdataJDBC;

public class Service {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		shoppingdataJDBC jdbcDB=new shoppingdataJDBC();
		
		jdbcDB.SearchBrandItem("MP");//依照品牌顯示產品
		
		List<ShoppingProduct> typeofwhichProduct = jdbcDB.searchtype(1);//顯示類別
	    
		//庫存更新
		System.out.println(typeofwhichProduct.get(0).getProductStack());
		int updateData = jdbcDB.updateData(typeofwhichProduct.get(0), 2);
		System.out.println(updateData);
		
		//新增商品先思考類別
		ShoppingProduct lastProduct = typeofwhichProduct.get(typeofwhichProduct.size()-1);
		lastProduct.setProductBrand("品牌");
		lastProduct.setProductPrice(100);
		jdbcDB.inserData(lastProduct);
		
		//刪除
		jdbcDB.delete(lastProduct);
		
		//
		
		
		
		

	}

}
