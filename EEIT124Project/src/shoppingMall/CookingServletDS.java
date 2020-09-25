package shoppingMall;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CookingServletDS
 */
@WebServlet("/CookingServletDS")
public class CookingServletDS extends HttpServlet {
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";  
    
	public CookingServletDS() {
        super();
        // TODO Auto-generated constructor stub
    }
    DataSource ds = null;
    public void init() throws ServletException
    {
      try 
      {
        InitialContext ctxt = new InitialContext();
        ds = (DataSource)ctxt.lookup("java:comp/env/jdbc/xe");  // for Oracle DB
      }
      catch (NamingException ne)
      {
        throw new ServletException(ne);
      }
    } 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");
		String cName = request.getParameter("cName");
		String sqlString = "SELECT ID,PNAME,PRICE,BRAND,QTY "+
				"From MALLCOOKING Where PNAME like "+ "'%"+ cName +"%'";
		
		System.out.println(sqlString);
		
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
	    out.println("<HTML>");
	    out.println("<HEAD><TITLE>Cooking products Form</TITLE></HEAD>");
	    out.println("<BODY>");
	    
	    try {
	    
	    System.out.println("before connection");
	    Connection connection = ds.getConnection();
	    System.out.println("after connection");
    	Statement stmt = connection.createStatement();
    	ResultSet rs = stmt.executeQuery(sqlString);
    	
    	out.println("<table border=1 width=100%>");
        out.println("<tr><th width=25%>Product ID</th>" +
                    "<th width=75%>Product Name</th>"+
                    "<th width=75%>Product Price</th>"+
                    "<th width=75%>Product Brand</th>"+
                    "<th width=75%>Product Qty</th></tr>");
	    	
	    for(int count = 0; ;count++) {
	        	if (rs.next()) {
	        		
	        		out.println("<tr><td>"+ rs.getString(1) + "</td><td>" +
	                        rs.getString(2) +"</td><td>" + rs.getInt(3) + "</td><td>"+
	        				 rs.getString(4) + "</td><td>"+
	                 		 rs.getInt(5) + "</td></tr>");
	        	}
	        	else
	        	{
	        		out.println("</table><h3>" + count + " rows retrieved</h3>");
	                break;
	        	}
	        }
	        rs.close();  	
	    	stmt.close();
	    	connection.close();
	    }catch(SQLException se) {
	    	se.printStackTrace();
	    }
	    out.println("</body></html>");
	        out.close();
		} 


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
