package shoppingMall;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RecipeOrderService {
	
	private DataSource ds;
	private RecipeOrderItemDao roidao;
	private RecipeOrderDao rodao;
	
	public RecipeOrderService() {
		try {
			Context ctx = new InitialContext();
			ds 	  = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleXE");
			roidao = new RecipeOrderItemDao();
			rodao  = new RecipeOrderDao();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void persistOrder(RecipeOrderBean ob) {
		Connection conn = null;
		
	    try {
	    	conn = ds.getConnection();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	    
	    try {
	    	conn.setAutoCommit(false);
	    	// 檢查所有訂單明細所訂購之商品的庫存數量是否足夠
	    	checkStock(ob, conn);
	    	
	    	rodao.setConnection(conn);
			rodao.insertOrder(ob);
			conn.commit();	
	    }catch (Exception e) {
			try {
				conn.rollback();
				System.out.println("發生異常，交易回滾.....,原因: " + e.getMessage());
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			}
	}

	private void checkStock(RecipeOrderBean ob, Connection conn) {
		Set<RecipeOrderItemBean> items = ob.getItems();
		roidao.setConnection(conn);
		for (RecipeOrderItemBean oib : items) {
			roidao.updateProductStock(oib);
		}
		
	}
	
	public RecipeOrderDao getRodao() {
		return rodao;
	}

	public void setRodao(RecipeOrderDao rodao) {
		this.rodao = rodao;
	}

	public RecipeOrderBean getOrder(int reOrderNo) {
		return rodao.getOrder(reOrderNo);
	}

	public List<RecipeOrderBean> getAllOrders(){
		return rodao.getAllOrders();
	}

//	List<OrderBean> getMemberOrders(String memberId);

}
