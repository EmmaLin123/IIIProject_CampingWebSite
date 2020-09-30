

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
 * Servlet implementation class RecipeServlet
 */
@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 
    public RecipeServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		if (request.getParameter("submit")!=null)
		     gotoSubmitProcess(request, response);
		   else if (request.getParameter("confirm")!=null)
		     gotoConfirmProcess(request, response);
		
		
	}


	private void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String reid;
	        String rename;
	        String brief;
	        String image;
	        String ingredient;
	        String tip1;
	        String tip2;
	        String tip3;
	        String tip4;
	        String tip5;
	        String tip6;
	        String note;
	        int people;
	        int time;
	        
	        reid =request.getParameter("reid").trim();
	        rename =request.getParameter("rename").trim();
	        brief =request.getParameter("brief").trim();
	        image = request.getParameter("img").trim();
	        ingredient = request.getParameter("INGREDIENTS");
	        tip1 = request.getParameter("tip1").trim();
	        tip2 = request.getParameter("tip2").trim();
	        tip3 = request.getParameter("tip3").trim();
	        tip4 = request.getParameter("tip4").trim();
	        tip5 = request.getParameter("tip5").trim();
	        tip6 = request.getParameter("tip6").trim();
	        note = request.getParameter("note").trim();
	        people = Integer.parseInt(request.getParameter("people"));
	        time = Integer.parseInt(request.getParameter("time"));
	        RecipeBean reg_recipe = new RecipeBean(reid,rename,brief,image,ingredient,tip1,tip2,tip3,tip4,tip5,tip6,note,people,time);
		    request.getSession(true).setAttribute("reg_recipe", reg_recipe);
		    request.getRequestDispatcher("/DisplayRecipe.jsp").forward(request,response);
	}

	private void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) {
		
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	    try {
	    	ctxt = new InitialContext();
	    	
	    	ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/OracleXE");
	    	conn = ds.getConnection();
	    	
	    	RecipeDAO recipeDAO = new RecipeDAO(conn);
	    	RecipeBean recipeData = (RecipeBean)request.getSession(true).getAttribute("reg_recipe");
	    	if(recipeDAO.insertRecipe(recipeData)) 
	    	{
	    		System.out.println("Get some SQL commands done!");
		          request.getSession(true).invalidate();
		          request.getRequestDispatcher("/Thanks.jsp").forward(request,response);
	    	}
	    	
	    }catch (NamingException ne) {
		      System.out.println("Naming Service Lookup Exception");  
		    } catch (SQLException e) {
		      System.out.println("Database Connection Error"); 
		    } catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		      try {
		        if (conn != null) conn.close();
		      } catch (Exception e) {
		        System.out.println("Connection Pool Error!");
		      }
		    }
		
	}

}
