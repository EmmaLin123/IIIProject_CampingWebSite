package shoppingMall;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateRecipeServlet
 */
@WebServlet("/UpdateRecipeServlet")
public class UpdateRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;   
    
    public UpdateRecipeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect("./RecipeSelectServlet2?page=1");
			return;
		}
		RecipeShoppingCart sc= (RecipeShoppingCart)session.getAttribute("RecipeShoppingCart");
		if (sc == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect("./RecipeSelectServlet2?page=1");
			return;
        }
		// cmd可能是DEL或是MOD
				String cmd = request.getParameter("cmd");
				String REID = request.getParameter("reid");
				System.out.println("進入123");
				if (cmd.equals("DEL")) {
			        sc.deleteRecipe(REID); // 刪除購物車內的某項商品
			        RequestDispatcher rd = request.getRequestDispatcher("./ShowRecipeCartContent.jsp");
				    rd.forward(request, response);
				    return;
				} else if (cmd.equals("MOD")) {
					String newQtyStr = request.getParameter("newQty");
					int newQty = Integer.parseInt(newQtyStr.trim());
					System.out.println("進入!!!");
					sc.modifyQty(REID, newQty);   // 修改某項商品的數項
			        RequestDispatcher rd = request.getRequestDispatcher("./ShowRecipeCartContent.jsp");
				    rd.forward(request, response);
				    return;
				}
	}

}
