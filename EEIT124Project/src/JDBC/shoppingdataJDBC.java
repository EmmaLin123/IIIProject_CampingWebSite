package JDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class shoppingdataJDBC {

	private DataSource dataSource;
	
	private Connection conn;

	public shoppingdataJDBC(Connection conn) {
		this.conn = conn;
    }
	public shoppingdataJDBC(){
		
	}
	// init DB
	public DataSource getDataSource() {

		if (dataSource == null) {
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("scott");
			ds.setPassword("tiger");
			ds.setMaxTotal(50);
			ds.setMaxIdle(50);
			dataSource = ds;
		}
		return dataSource;
	}

	// 依照品牌搜尋全部商品
	public List<ShoppingProduct> SearchBrandItem(String keyWord) {

		StringBuffer searchResult = new StringBuffer();

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sqlString = "select * from shoppingdata where PRODUCT_BRAND like ?";

		try (Connection connection = getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sqlString);)

		{
			pstmt.setString(1, "%" + keyWord + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("PRODUCT_NAME");
				String id = rs.getString("PRODUCT_ID");
				String warring = rs.getString("PRODUCT_WARRING");
				String brands = rs.getString("PRODUCT_BRAND");
				String spec = rs.getString("PRODUCT_SPEC");
				int price = rs.getInt("PRODUCT_PRICE");
				int stack = rs.getInt("PRODUCT_STACK");

				ShoppingProduct product = new ShoppingProduct(id, brands, name, price, spec, stack, warring);
//	      searchResult.append("商品品牌: "+brands+"\n"+
//	    		              "商品名稱: "+name+"\n"+	    		             
//	    		              "商品價格: "+String.valueOf(price)+"\n"+
//	    		              "商品庫存: "+feature+String.valueOf(stack)+"\n");
				list.add(product);

			}
			System.out.println(searchResult);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<ShoppingProduct> searchtype(int selectWhich) {

		String[] typeArray = { "bedding", "carpet", "furniture", "ice", "lamp", "tableware", "tool" };

		List<ShoppingProduct> list = new ArrayList<ShoppingProduct>();

		String sqlString = "select * from shoppingdata where PRODUCT_id like ?";
		try (Connection connection = getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sqlString);)

		{

			pstmt.setString(1, "%" + typeArray[selectWhich] + "%");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("PRODUCT_ID");
				String brand = rs.getString("PRODUCT_BRAND");
				String name = rs.getString("PRODUCT_NAME");
				int price = rs.getInt("PRODUCT_PRICE");
				String spec = rs.getString("PRODUCT_SPEC");
				int stack = rs.getInt("PRODUCT_STACK");
				String warring = rs.getString("PRODUCT_WARRING");

				ShoppingProduct product = new ShoppingProduct();

				product.setProductBrand(brand);
				product.setProductPrice(price);
				product.setProductSpec(spec);
				product.setProductStack(stack);
				product.setProductWarring(warring);
				product.setProductId(id);
				list.add(product);

				System.out.println(product.getProductId());
			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public String creatOrder(String productID) {

		return null;

	}

	public int updateData(ShoppingProduct shoppingProduct, int updateAmount) {

		String productId = shoppingProduct.getProductId();
		int productStack = shoppingProduct.getProductStack();

		if (productStack < updateAmount) {
			System.out.println("庫存不足");
		} else {
			try (Connection conn = getDataSource().getConnection();
					PreparedStatement stmt = conn
							.prepareStatement("update shoppingdata set product_stack=? where product_id=?")) {
				productStack = productStack - updateAmount;
				stmt.setInt(1, productStack);
				stmt.setString(2, productId);
				int result = stmt.executeUpdate();
				if (result == 0) {
					throw new RuntimeException("update fail");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return productStack;
	}

	public void inserData(ShoppingProduct shoppingProduct) {

		String sqlString = "INSERT INTO shoppingdata VALUES(?,?,?,?,?,?,?,?)";	
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement stmt = conn
						.prepareStatement(sqlString)) {
			
			shoppingProduct.getProductName();
			shoppingProduct.getProductPrice();
			shoppingProduct.getProductStack();
			
			stmt.setString(1, shoppingProduct.getProductId()+1);
			stmt.setString(2, shoppingProduct.getProductBrand());
			stmt.setString(3, shoppingProduct.getProductName());
			stmt.setInt(4, shoppingProduct.getProductPrice());
			stmt.setString(5, shoppingProduct.getProductSpec());
			stmt.setInt(6, shoppingProduct.getProductStack());
			stmt.setString(7, shoppingProduct.getProductWarring());
			stmt.setInt(8, shoppingProduct.getPeople());
			
			int result = stmt.executeUpdate();
			
			if (result == 0) {
				throw new RuntimeException("create fail");
			}else {
				System.out.println("成功新增");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public void delete(ShoppingProduct shoppingProduct) {

		String sqlString = "DELETE FROM shoppingdata where product_id=? ";	
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement stmt = conn
						.prepareStatement(sqlString)) {
			stmt.setString(1, shoppingProduct.getProductId());
			
			int result = stmt.executeUpdate();
			
			if (result == 0) {
				throw new RuntimeException("create fail");
			}else {
				System.out.println("成功新增");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
	
}