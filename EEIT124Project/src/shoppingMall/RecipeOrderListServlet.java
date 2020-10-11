package shoppingMall;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RecipeOrderListServlet")
public class RecipeOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RecipeOrderListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect("./RecipeSelectServlet2?page=1");
			return;
		}
		
		RecipeOrderService os = new RecipeOrderService();
		List<RecipeOrderBean> orders = os.getAllOrders();
		request.setAttribute("orders", orders);
		RequestDispatcher rd = request.getRequestDispatcher("./RecipeOrderList.jsp");
		rd.forward(request, response);
		return;
	}

}
