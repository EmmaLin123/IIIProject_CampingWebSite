package cprkdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;



public class INUPDEQ implements DataDAO{
	
	private DataSource datasource;
	
	public DataSource getDataSource() {
		if (datasource == null) {
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("project2");
			ds.setPassword("project2");
			ds.setMaxTotal(50);
			ds.setMaxIdle(50);

			datasource = ds;
		}
		return datasource;

	}
	
	
	
	
	
	@Override
	public List<Data> listData() {
		List<Data> list = new ArrayList<>();
		try (Connection connection = getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from COUPON");) {
			while (rs.next()) {
				// 把每一筆資料轉換成物件
				Data data = new Data();
				int Memberid = rs.getInt("Memberid");
				data.setMemberid (Memberid);
				String Expirationdate = rs.getString("Experationdate");
				data.setExpirationdate (Expirationdate);
				String Coupontype = rs.getString("Coupontype");
				data.setCoupontype (Coupontype);
				String Couponnum = rs.getString("Couponnum");
				data.setCouponnum (Couponnum);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}






	@Override
	public void insertData(Data data) {
		try {
			Connection connection = getDataSource().getConnection();
			{

				PreparedStatement pstmt = connection.prepareStatement(
						"insert into COUPON (Couponnum,,Coupontype,Expirationdate,Memberid) values(?,?,?,?)");
				pstmt.setString(1, data.getCouponnum());
				pstmt.setString(2, data.getCoupontype());
				pstmt.setString(3, data.getExpirationdate());
				pstmt.setInt(4, data.getMemberid());
				int i = pstmt.executeUpdate();
				if (i >= 1) {
					System.out.println("創建成功");
				} else {
					System.out.println("創建失敗");
				}
				pstmt.clearParameters();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	@Override
	public void updateData(Data data) {
		try (Connection connection = getDataSource().getConnection();) {
			PreparedStatement pstmt = connection.prepareStatement(
					"update Coupon set Couponnum=?, Coupontype=?, Expirationdate=?, Memberid=? where Couponnum=? and Coupontype=? and Expirationdate=?");
			pstmt.setString(1, data.getCouponnum());
			pstmt.setString(2, data.getCoupontype());
			pstmt.setString(3, data.getExpirationdate());
			pstmt.setInt(4, data.getMemberid());
			
			int i = pstmt.executeUpdate();
			if (i >= 1) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失敗");
			}
			pstmt.clearParameters();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}




	public void selectMemberid(Data data) {
		try (Connection connection = getDataSource().getConnection();) {
			PreparedStatement pstmt = connection.prepareStatement("select * from Coupon where Memberid like =?");
			pstmt.setInt(1, data.getMemberid());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("Couponnum") + "" + rs.getString("Coupontype") + "(" + rs.getString("Expirationdate"));
			}

			pstmt.clearParameters();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public void selectCouponnum(Data data) {
		try (Connection connection = getDataSource().getConnection();) {
			PreparedStatement pstmt = connection.prepareStatement("select * from Coupon where Couponnum like =?");
			pstmt.setString(1, data.getCouponnum());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("Coupontype") + "" + rs.getString("Expirationdate") + "(" + rs.getInt("Memberid"));
			}

			pstmt.clearParameters();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public void selectCoupontype(Data data) {
		try (Connection connection = getDataSource().getConnection();) {
			PreparedStatement pstmt = connection.prepareStatement("select * from Coupon where Coupontype like =?");
			pstmt.setString(1, data.getCouponnum());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("Couponnum") + "" + rs.getString("Expirationdate") + "(" + rs.getInt("Memberid"));
			}

			pstmt.clearParameters();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteCoupon(Data data) {
		try (Connection connection = getDataSource().getConnection();) {

			PreparedStatement pstmt = connection
					.prepareStatement("select * from Coupon where Couponnum=? and Coupontype=? and Expirationdate=? and Memberid=?");
			PreparedStatement pstmt2 = connection.prepareStatement("delete from Coupon where Couponnum=? and Coupontype=? and Expirationdate=?");
			connection.setAutoCommit(false);
			pstmt.setString(1, data.getCouponnum());
			pstmt.setString(2, data.getCoupontype());
			pstmt.setString(3, data.getExpirationdate());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			pstmt.setString(1, data.getCouponnum());
			pstmt.setString(2, data.getCoupontype());
			pstmt.setString(3, data.getExpirationdate());
			int i = pstmt2.executeUpdate();
			if (i == 1) {
				System.out.println("Couponnum: " + rs.getString("Couponnum") + " Coupontype:" + rs.getString("Coupontype") + 
						"Expirationdate"+ rs.getString("Expirationdate") + "已刪除");
				connection.commit();
			} else {
				System.out.println("刪除失敗");
				connection.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}






}
