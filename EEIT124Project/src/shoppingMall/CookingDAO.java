package shoppingMall;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CookingDAO{
	
	private Connection connection;
	
	public CookingDAO(Connection connection) {
		this.connection = connection;
	}
	
public int createOrder(String cID, String cName) {
	try {
		int oID = 1;
		String sqlString = "Select ORDERID.nextval From DUAL"; 
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		
		if(rs.next()) oID = rs.getInt(1);
		
		rs.close();
		
		sqlString = "INSERT INTO Order1(ORDERID,PRODUCTID) "+"VALUES("+ oID + ",'"+ cID + "')";
		stmt.executeUpdate(sqlString);
	  	stmt.close();
	  	return oID;
		
	}catch(Exception e) {
	    System.err.println("建立訂單時發生錯誤:" + e);
		  return -1;
	  }
}

public boolean deleteDept(int oID) {
    try {
      String sqlString = "DELETE FROM Order1 " +
			                   "WHERE ORDERID = " + oID;
	    Statement stmt = connection.createStatement();
		  int deletecount = stmt.executeUpdate(sqlString);
		  stmt.close();
      if (deletecount >= 1) return true;
		  else                  return false;
	  } catch (Exception e) {
	    System.err.println("刪除訂單時發生錯誤: "+ e);
		  return false;
	  }
  }

public boolean updateOrder(Cooking cooking,Order order ) {
    try {
      String sqlString = "UPDATE Order1" +
	                  	   	 "SET PRODUCTID = '" + cooking.getcID() +"' "+
	                  	     "PRODUCTNAME = '" + cooking.getcName() +"' "+
	                  	     "PRICE = " + cooking.getcPrice() +
	                  	     "BRAND = '" + cooking.getcBrand() +"' "+
	                  	     "SPEC = '" + cooking.getcSpec() +"' "+
	                  	     "Qty = " + order.getQty() +	                  	     
                    		 "WHERE ORDERID = " + order.getOID();

      Statement stmt = connection.createStatement();
	    int updatecount = stmt.executeUpdate(sqlString);
      stmt.close();
      if (updatecount >= 1) return true;
      else                  return false;
	  } catch (Exception e) {
	    System.err.println("更新訂單資料時發生錯誤:" + e);
		  return false;
    }
  }
	
	public Cooking findCooking(String cName) {
		try {
			Cooking cooking = null;
			String cID = null;
			
			Statement stmt = connection.createStatement();
			String sqlString = "SELECT ID,PNAME,PRICE,BRAND,QTY "+
					"From MALLCOOKING Where PNAME like"+ "'%"+ cName +"%'";
			
			System.out.println(sqlString);
			
			ResultSet rs = stmt.executeQuery(sqlString);
			
			if(rs.next()) {
				
				cName = rs.getString("PNAME");
				cooking = new Cooking(cID,cName); 
			}
			rs.close();
			stmt.close();
			return cooking;
			
		}catch(Exception e) {
			System.err.println("找不到此商品" + e);
		    return null;
		}
		
	}

}
