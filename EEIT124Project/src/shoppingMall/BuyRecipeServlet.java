package shoppingMall;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BuyRecipeServlet")
public class BuyRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BuyRecipeServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		RecipeShoppingCart cart = (RecipeShoppingCart)session.getAttribute("ShoppingCart");
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new RecipeShoppingCart();
			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			session.setAttribute("ShoppingCart", cart);   
		}
		
		String reid = request.getParameter("REID");
		String rename = request.getParameter("rename");
		String ingredient = request.getParameter("ingredient");
		String qtyStr = request.getParameter("qty");
		String priceStr = request.getParameter("price");
		String discountStr = request.getParameter("discount");
		
		int qty = 0;
		double price = 0;
		double discount = 0;
		
		try {
			qty = Integer.parseInt(qtyStr.trim());
			price = Double.parseDouble(priceStr.trim());
			discount = Double.parseDouble(discountStr.trim());
			
		} catch (NumberFormatException e) {
			throw new ServletException(e); 
		}
		// 將訂單資料封裝到OrderItem物件內
				RecipeOrderItem oi = new RecipeOrderItem(reid, qty, price, discount, rename, ingredient);
				// 將OrderItem物件內加入ShoppingCart的物件內
				cart.addToCart(reid, oi);
	}

}
