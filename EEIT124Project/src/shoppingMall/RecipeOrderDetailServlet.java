package shoppingMall;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecipeOrderDetailServlet
 */
@WebServlet("/RecipeOrderDetailServlet")
public class RecipeOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RecipeOrderDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reorderNo = request.getParameter("orderNo");
		int no = Integer.parseInt(reorderNo.trim());
		RecipeOrderService orderService = new RecipeOrderService();
		RecipeOrderBean ob = orderService.getOrder(no);
		request.setAttribute("RecipeOrderBean", ob);
		RequestDispatcher rd = request.getRequestDispatcher("ShowRecipeOrderDetail.jsp");
		rd.forward(request, response);
		return;
	}
	
	public void displayOrderBean(RecipeOrderBean ob) {
		System.out.println("ob.getOrderNo()=" + ob.getReOrderNo());
//		System.out.println("ob.getMemberId()=" + ob.getMemberId());
		System.out.println("ob.getOrderDate=" + ob.getOrderDate());
		System.out.println("ob.getTotalAmount=" + ob.getTotalAmount());
		System.out.println("ob.getInvoiceTitle=" + ob.getInvoiceTitle());
		System.out.println("ob.getBNO=" + ob.getBno());
		System.out.println("ob.getShippingAddress=" + ob.getShippingAddress());
		System.out.println("ob.getCancelTag=" + ob.getCancelTag());
		System.out.println("==============訂單明細=================");
		Set<RecipeOrderItemBean> items = ob.getItems();
		for (RecipeOrderItemBean oib : items) {
			System.out.println("---------------一筆明細---------------");
			System.out.println("   oib.getSeqno()=" + oib.getSeqno());
			System.out.println("   oib.getOrderNo()=" + oib.getReOrderNo());
			System.out.println("   oib.getREId()=" + oib.getReid());
			System.out.println("   oib.getDescription()=" + oib.getDescription());
			System.out.println("   oib.getQuantity()=" + oib.getQuantity());
			System.out.println("   oib.getUnitPrice()=" + oib.getUnitPrice());
			System.out.println("   oib.getDiscount()=" + oib.getDiscount());
		}
	}
	

}
