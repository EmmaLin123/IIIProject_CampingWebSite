package shoppingMall;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RecipeData {

		public static void main(String[] args) throws IOException, ParseException {

			try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "project2",
					"project2");) {
				connection.setAutoCommit(false);
				File file = new File("C:\\eclipse\\JavaWorkspace\\menu2 (2).csv");
				try (FileInputStream fi = new FileInputStream(file);
						BufferedInputStream bf = new BufferedInputStream(fi);
						InputStreamReader isr = new InputStreamReader(bf, "MS950");
						BufferedReader br = new BufferedReader(isr);) {
					String s = null;

					int counts = 0;
					s = br.readLine();
					while ((s = br.readLine()) != null) {
						String[] array = s.split(",");
						for (int i = 0; i < array.length; i++) {
							System.out.println(array[i]);

						}
						
						PreparedStatement pstmt = connection.prepareStatement(
								"insert into recipe(RE_id,RE_Name,brief,image,Ingredients,Tip1,Tip2,Tip3,Tip4,Tip5,Tip6,Note,People,Time1) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

						pstmt.setString(1, array[0]);
						pstmt.setString(2, array[1]);
						pstmt.setString(3, array[2]);
						pstmt.setString(4, array[3]);
						pstmt.setString(5, array[4]);
						pstmt.setString(6, array[5]);
						pstmt.setString(7, array[6]);
						pstmt.setString(8, array[7]);
						pstmt.setString(9, array[8]);
						pstmt.setString(10, array[9]);
						pstmt.setString(11, array[10]);
						pstmt.setString(12, array[11]);
						pstmt.setInt(13, Integer.parseInt(array[12]));
						pstmt.setInt(14, Integer.parseInt(array[13]));

						pstmt.addBatch();
						pstmt.clearParameters();

						counts++;
						if (counts % 50 == 0) {
							int[] results = pstmt.executeBatch();
							pstmt.clearBatch();

						}

						int[] results = pstmt.executeBatch();
						pstmt.clearBatch();
						connection.commit();
					}
				} catch (FileNotFoundException e1) {
					connection.rollback();
					e1.printStackTrace();
				} catch (Exception e) {
					connection.rollback();
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}



	}
