import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shoppingMall.RecipeBean;

public class RecipeDAO {
	
	private Connection conn;
	
	public RecipeDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<RecipeBean> selectAll(){
		List list = new ArrayList();
		String sql = "select * from Recipe order by RE_ID";
		try {
			PreparedStatement ptmst=conn.prepareStatement(sql);
			ResultSet rs = ptmst.executeQuery();
			while (rs.next()) {
                String reid = rs.getString("re_id");
                String rename = rs.getString("re_name");
                String brief = rs.getString("brief");
                String image = rs.getString("image");
                int people = rs.getInt("people");
                int time = rs.getInt("time");
                list.add(new RecipeBean(reid,rename,brief,image,people,time)); 
            }
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return list;
	}
	
//    public RecipeBean selectById(String id) {
//    	RecipeBean recipeBean = null;
//        String sql = "select * from Recipe where re_id = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, id);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {        	
//            	String reid = rs.getString("re_id");
//
//            	recipeBean = new RecipeBean(reid,rename,brief,image,people,time);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return recipeBean;
//    }
	
	public boolean DeleteRecipe(RecipeBean recipeBean) {
		try {
		String sqlString = "Delete from Recipe Where re_id='"+recipeBean.getReid()+"'";
		System.out.println(sqlString);
		Statement stmt = conn.createStatement();
		int updatecount = stmt.executeUpdate(sqlString);
	    stmt.close();
	    if (updatecount >= 1) return true;
	    else                  return false;
		} catch (SQLException e) {
			System.err.println("更新食譜資料時發生錯誤:" + e);
			e.printStackTrace();
			return false;			
		}	
	    
	}
	
	
	public boolean updateRecipe(RecipeBean recipeData) {
		try{
			String sqlString = "update Recipe set re_name='"+recipeData.getRename()+
					           "', brief='"+recipeData.getBrief()+
					           "', image='"+recipeData.getImage()+
					           "',INGREDIENTS='"+recipeData.getIngredient()+
					           "',Tip1='"+recipeData.getTip1()+
					           "',Tip2='"+recipeData.getTip2()+
					           "',Tip3='"+recipeData.getTip3()+
					           "',Tip4='"+recipeData.getTip4()+
					           "',Tip5='"+recipeData.getTip5()+
					           "',Tip6='"+recipeData.getTip6()+
					           "',NOTE='"+recipeData.getNote()+
					           "',people= "+recipeData.getPeople()+
					           ",Time1= "+recipeData.getTime()+
					           " Where re_id='"+recipeData.getReid()+"'";
			System.out.println(sqlString);
			Statement stmt = conn.createStatement();
		    int updatecount = stmt.executeUpdate(sqlString);
		    stmt.close();
		    if (updatecount >= 1) return true;
		      else                return false;
		}catch (Exception e) {
		    System.err.println("更新食譜資料時發生錯誤:" + e);
			  return false;
	}		           
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
