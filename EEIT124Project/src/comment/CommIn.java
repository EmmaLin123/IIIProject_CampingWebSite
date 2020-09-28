package comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;





public class CommIn implements CommDataDao {
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
	

	
	public List<CommData> listData() {
		List<CommData> list = new ArrayList<>();
		try (Connection connection = getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from RANKING");) {
			while (rs.next()) {
				// 把每一筆資料轉換成物件
				CommData commdata = new CommData();
				int Campgroundid = rs.getInt("Campgroundid");
				commdata.setCampgroundid(Campgroundid);
				int Memberid = rs.getInt("Memberid");
				commdata.setMemberid(Memberid);
				int Ranking = rs.getInt("Ranking");
				commdata.setRanking(Ranking);
				String Content = rs.getString("Content");
				commdata.setContent(Content);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public void insertData(CommData data) {
		try {
			Connection connection = getDataSource().getConnection();
			{

				PreparedStatement pstmt = connection.prepareStatement(
						"insert into RANKING (Campgroundid,,Memberid,Ranking,Content) values(?,?,?,?)");
				pstmt.setInt(1, data.getCampgroundid());
				pstmt.setInt(2, data.getMemberid());
				pstmt.setInt(3, data.getRanking());
				pstmt.setString(4, data.getContent());
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
	
	
	
	
	
	
	
	
	
	
}
