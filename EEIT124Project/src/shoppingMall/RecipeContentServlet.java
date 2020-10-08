package shoppingMall;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class RecipeContentServlet
 */
@WebServlet("/RecipeContentServlet")
public class RecipeContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
    public RecipeContentServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		
		String coid = request.getParameter("reid");
		
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/OracleXE");
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Recipe");
			
			System.out.println(coid);
			while (rs.next()) {
				List list = new ArrayList<>();
				String reidDB = rs.getString("RE_ID");
                String rename = rs.getString("RE_Name");
                String brief = rs.getString("BRIEF");
                String image = rs.getString("IMAGE");
                String ingredient = rs.getString("INGREDIENTS");
                String tip1 = rs.getString("TIP1");
                String tip2 = rs.getString("TIP2");
                String tip3 = rs.getString("TIP3");
                String tip4 = rs.getString("TIP4");
                String tip5 = rs.getString("TIP5");
                String tip6 = rs.getString("TIP6");
                String note = rs.getString("NOTE");
                int people = rs.getInt("PEOPLE");
                int time = rs.getInt("TIME1");
                list.add(new RecipeBean(reidDB,rename,brief,image,ingredient,tip1,tip2,tip3,tip4,tip5,tip6,note,people,time));
				if (coid.equals(reidDB)) {					
					request.getSession(true).setAttribute("list", list);
					request.getRequestDispatcher("RecipeContent.jsp").forward(request, response);
			}
			}
			return;
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
			ne.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		} 
		
		
	}

}
