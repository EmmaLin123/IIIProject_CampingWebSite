package shoppingMall;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RecipeCheckoutServlet")
public class RecipeCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RecipeCheckoutServlet() {
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
		
		RecipeShoppingCart sc = (RecipeShoppingCart) session.getAttribute("RecipeShoppingCart");
				if (sc == null) {
					// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
					// 導向首頁
					response.sendRedirect("./RecipeSelectServlet2?page=1");
					return;
	}
				RequestDispatcher rd = request.getRequestDispatcher("RecipeOrderConfirm.jsp");
				rd.forward(request, response);
				return;
	}
}
