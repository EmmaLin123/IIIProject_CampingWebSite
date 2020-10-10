

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import shoppingMall.RecipeBean;

/**
 * Servlet implementation class RecipeSelectServlet2
 */
@WebServlet("/RecipeSelectServlet2")
public class RecipeSelectServlet2 extends HttpServlet {
	private RecipeDAO dao;
	
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    
	    response.setHeader("Cache-Control","no-cache"); 
		response.setHeader("Pragma","no-cache"); 
		response.setDateHeader ("Expires", -1);
		// ==================模糊查詢====================	
		
		if (request.getParameter("submit") !=null) {
			gotoCheckID(request, response);
		}else {
		    gotoSelectAll(request, response);	
	    }	
	}

	private void gotoSelectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("page");
		int page;
		try {page = Integer.valueOf(p);
		} catch (NumberFormatException e) {
			page = 1;}
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    try {
	    	ctxt = new InitialContext();
	    	ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/OracleXE");	    	
	    conn = ds.getConnection();
	    RecipeDAO dao = new RecipeDAO(conn);
	    int totalcount = dao.counts();
		int recipePerPage = 6;
		int endIndex = page*recipePerPage;
		int totalPages = totalcount % recipePerPage ==0 ? totalcount/recipePerPage:totalcount/recipePerPage+1;
		int beginIndex = (page-1)*recipePerPage+1;
		List<RecipeBean> bean = dao.listAllOf(beginIndex, endIndex);
		System.out.println(beginIndex);
		System.out.println(endIndex);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("page", page);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("Recipe.jsp").forward(request,response);
			     		
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

	private void gotoCheckID(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
			DataSource ds = null;
			InitialContext ctxt = null;
			Connection conn = null;
			try {
				ctxt = new InitialContext();

				ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/OracleXE");
				conn = ds.getConnection();
				String p = request.getParameter("page");
				int page;
				int recipePerPage = 6;				
				try {page = Integer.valueOf(p);
				} catch (NumberFormatException e) {
					page = 1;}
				
				RecipeDAO recipeDAO = new RecipeDAO(conn);	
				String rename1 = request.getParameter("re_name");
				int beginIndex = (page-1)*recipePerPage+1;
				int totalcount = recipeDAO.getRecordCounts(rename1);
				int endIndex = page*recipePerPage;
				List<RecipeBean> bean = recipeDAO.selectByName(rename1,beginIndex,endIndex);
				System.out.println(beginIndex);
				System.out.println(endIndex);
				System.out.println(totalcount);
				int totalPages = totalcount % recipePerPage ==0 ? totalcount/recipePerPage:totalcount/recipePerPage+1;
				request.setAttribute("totalPages", totalPages);
				request.setAttribute("page", page);
				request.getSession(true).setAttribute("bean", bean);
				request.setAttribute("rename1", rename1);
				request.getRequestDispatcher("/Recipe.jsp").forward(request, response);

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
	
