import java.sql.Connection;
import java.sql.Statement;


import shoppingMall.RecipeBean;

public class RecipeDAO {
	
	private Connection conn;
	
	public RecipeDAO(Connection conn) {
		this.conn = conn;
	}
	
	public boolean insertRecipe(RecipeBean recipeData) {
		try {
			String sqlString = "insert into Recipe values('"
					            +recipeData.getReid()+"','"
					            +recipeData.getRename()+"','"
					            +recipeData.getBrief()+"','"
					            +recipeData.getImage()+"','"
					            +recipeData.getIngredient()+"','"
					            +recipeData.getTip1()+"','"
					            +recipeData.getTip2()+"','"
					            +recipeData.getTip3()+"','"
					            +recipeData.getTip4()+"','"
					            +recipeData.getTip5()+"','"
					            +recipeData.getTip6()+"','"
					            +recipeData.getNote()+"','"
					            +recipeData.getPeople()+"','"
					            +recipeData.getTime()+"')";
			
			Statement stmt = conn.createStatement();
		    System.out.println(sqlString);
		    int updatecount = stmt.executeUpdate(sqlString);
		    stmt.close();
		      if (updatecount >= 1) return true;
		      else                  return false;
		    }catch (Exception e) {
			    System.err.println("新增食譜資料時發生錯誤:" + e);
				  return false;
		}
	}

}
