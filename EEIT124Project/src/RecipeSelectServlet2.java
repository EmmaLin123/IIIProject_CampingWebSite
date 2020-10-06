

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import shoppingMall.RecipeBean;

/**
 * Servlet implementation class RecipeSelectServlet2
 */
@WebServlet("/RecipeSelectServlet2")
public class RecipeSelectServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
       
    public RecipeSelectServlet2() {
        super();
        
    }

    public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    
	    response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Pragma","no-cache"); 
		response.setDateHeader ("Expires", -1);
		
		gotoSelectProcess(request,response);
		
	}
	private void gotoSelectProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    try {
	    	ctxt = new InitialContext();
	    	
	    	ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/OracleXE");
	    	conn = ds.getConnection();
	    	
	    	RecipeDAO recipeDAO = new RecipeDAO(conn);
	    	List<RecipeBean> list = recipeDAO.selectAll();   	    
	        request.getSession(true).setAttribute("list", list);
   			request.getRequestDispatcher("./Recipe.jsp").forward(request,response);    	            
	     		
	     	}catch (NamingException ne) {
			      System.out.println("Naming Service Lookup Exception");
			      ne.printStackTrace();
			    } catch (SQLException e) {
			      System.out.println("Database Connection Error"); 
			    } finally {
			      try {
			        if (conn != null) conn.close();
			      } catch (Exception e) {
			        System.out.println("Connection Pool Error!");
			      }
			    }
	    
	}

}
