package shoppingMall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RecipeOrderDao {
	
	// 本類別
    //  1.新增一筆訂單到orders表格
    //  2.查詢orders表格內的單筆訂單
    //  3.查詢orders表格內的所有訂單
	
	private Connection conn;
	int reorderNo = 0;
	
	public RecipeOrderDao() {
	}
	
	public void insertOrder(RecipeOrderBean ob) {
		String sqlOrder = "Insert Into Recipeorders "
				+ " (memberId, totalAmount, shippingAddress,"
				+ " BNO, InvoiceTitle, orderDate) "
				+ " values(?, ?, ?, ?, ?, ?) ";

		String sqlItem = "Insert Into RecipeOrderItems (orderNo, RE_ID,"
				+ " description, amount, unitPrice, discount) "
				+ " values(?, ?, ?, ?, ?, ?) ";

		ResultSet generatedKeys = null;

		String generatedColumns[] = {"ORDERNO"};
		try (
			PreparedStatement ps = conn.prepareStatement(sqlOrder,
					generatedColumns);
		) {
			ps.setString(1, null);
			ps.setDouble(2, ob.getTotalAmount());
			ps.setString(3, ob.getShippingAddress());
			ps.setString(4, ob.getBno());
			ps.setString(5, ob.getInvoiceTitle());
			Timestamp ts = new Timestamp(ob.getOrderDate().getTime());
			ps.setTimestamp(6, ts);
			ps.executeUpdate();
			int id = 0;
			// 取回剛才新增之訂單的主鍵值
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new RuntimeException("OrderDaoImpl類別#insertOrder()無法取得新增之orders表格的主鍵");
			}
            int n = 0 ; 
			Set<RecipeOrderItemBean> items = ob.getItems();
			try (PreparedStatement ps2 = conn.prepareStatement(sqlItem);) {
				for (RecipeOrderItemBean oib : items) {
					 //下列四個敘述為交易測試而編寫
//					 n++;
//					 if (n > 1) {
//					 	  System.out.println("發生例外 n>2");
//					 	  throw new RuntimeException("JDBC交易測試用");
//					 }
					ps2.setInt(1, id);
					ps2.setString(2, oib.getReid());
					ps2.setString(3, oib.getDescription());
					ps2.setDouble(4, oib.getQuantity());
					ps2.setDouble(5, oib.getUnitPrice());
					ps2.setDouble(6, oib.getDiscount());
					ps2.executeUpdate();
					ps2.clearParameters();
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("RecipeOrderDaoImpl類別#insertOrder()發生SQL例外: " + ex.getMessage());
		}
	}

	public RecipeOrderBean getOrder(int reorderNo) {
		RecipeOrderBean ob = null;
		DataSource ds = null;
		Set<RecipeOrderItemBean> set = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleXE");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("RecipeOrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}

		String sql = "SELECT * FROM Recipeorders WHERE orderno = ? ";
		String sql1 = "SELECT * FROM RecipeOrderItems"
				+ ""
				+ "ms WHERE orderno = ? ";
		try (
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(sql1);
		) {
			ps.setInt(1, reorderNo);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				if (rs.next()) {
					Integer no = rs.getInt("orderNo");
					//String cancel = rs.getString("cancelTag");
					String bno = rs.getString("bno");
					String title = rs.getString("invoiceTitle");
//					String id = rs.getString("memberId");
					Timestamp orderDate = rs.getTimestamp("orderDate");
					String shipAddr = rs.getString("shippingAddress");
					Date shipDate = rs.getDate("shippingDate");
					double totalAmount = rs.getDouble("totalAmount");
					ob = new RecipeOrderBean(no, totalAmount, shipAddr, bno, title, orderDate, shipDate, null);
				}
			}
			ps1.setInt(1, reorderNo);
			try (
				ResultSet rs = ps1.executeQuery();
			) {
				set = new HashSet<>();
				while (rs.next()) {
					int seqNo = rs.getInt("seqNo");
					int reorderNo2 = rs.getInt("orderNo");
					String reid = rs.getString("RE_ID");
					String description = rs.getString("description");
					Integer amount = rs.getInt("amount");
					Double uPrice = rs.getDouble("unitPrice");
					Double discount = rs.getDouble("discount");
					RecipeOrderItemBean oi = new RecipeOrderItemBean(seqNo, reorderNo2, reid, 
							description, amount, uPrice, discount);
					set.add(oi);
				}
				ob.setItems(set);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("RecipeOrderDaoImpl類別#getOrder()-2發生例外: " + ex.getMessage());
		}
		return ob;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public List<RecipeOrderBean> getAllOrders() {
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleXE");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("RecipeOrderDao類別#getOrder()-1發生例外: " + ex.getMessage());
		}
		List<RecipeOrderBean> list = new ArrayList<RecipeOrderBean>();
		String sql = "SELECT OrderNo FROM Recipeorders";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()) {
				Integer no = rs.getInt(1);
				list.add(getOrder(no));
			}
		} catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		return list;
	}
	
	public List<RecipeOrderBean> getMemberOrders(String memberId) {
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleXE");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}
		List<RecipeOrderBean> list = new ArrayList<RecipeOrderBean>();
		String sql = "SELECT OrderNo FROM Orders where memberId = ? Order by orderDate desc ";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			) {
				ps.setString(1, memberId);
				try (
					ResultSet rs = ps.executeQuery();
				) {
					while (rs.next()) {
						Integer no = rs.getInt(1);
						list.add(getOrder(no));
					}
				}
		} catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		return list;
	}
	

	
}
