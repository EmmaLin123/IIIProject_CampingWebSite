package shoppingMall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeOrderItemDao {
	
	Connection conn;

	public RecipeOrderItemDao() {
		
	}
	
	public double findItemAmount(RecipeOrderItemBean oib) {
		double subtotal = oib.getQuantity() * oib.getUnitPrice() * oib.getDiscount();
		return subtotal;
	}
	
	public int updateProductStock(RecipeOrderItemBean oib) {
		int n = 0;
		int stock = 0;
		String sql0 = "SELECT stock FROM Recipe WHERE RE_ID = ?";
		String sql1 = "UPDATE Recipe SET stock = stock - ? WHERE RE_ID = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql0);) {
			ps.setString(1, oib.getReid());
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					stock = rs.getInt(1);
					if (stock < oib.getQuantity()) {
						throw new RecipeStockException("庫存數量不足: 產品序號: " 
								+ oib.getReid() + ", 在庫量: " + stock+ ", 訂購量: " + oib.getQuantity());
					} else {
						;
					}
					try (PreparedStatement ps1 = conn.prepareStatement(sql1);) {
						ps1.setInt(1, oib.getQuantity());
						ps1.setString(2, oib.getReid());
						n = ps1.executeUpdate();
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("RecipeOrderItemDao類別#updateProductStock()發生SQL例外: " + ex.getMessage());
		}
		return n;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
		
	}
	

}
