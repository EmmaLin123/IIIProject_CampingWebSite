package shoppingMall;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RecipeProcessOrderServlet")
public class RecipeProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RecipeProcessOrderServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		
		String finalDecision = request.getParameter("finalDecision");		
		HttpSession session = request.getSession(false);
		
		RecipeShoppingCart sc = (RecipeShoppingCart)session.getAttribute("RecipeShoppingCart");
		if (sc == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect("./RecipeSelectServlet2?page=1");
			return;
			// 如果使用者取消訂單	
		}
		if  (finalDecision.equals("RecipeCancel")){
			session.removeAttribute("RecipeShoppingCart");
			response.sendRedirect("./RecipeSelectServlet2?page=1");
			return;  			
		}
		double totalAmount = Math.round(sc.getSubtotal() * 1); // 計算訂單總金額
		String shippingAddress = request.getParameter("ShippingAddress"); // 出貨地址
		String bNO = request.getParameter("BNO");					// 發票的統一編號  
		String invoiceTitle = request.getParameter("InvoiceTitle");	// 發票的抬頭
		Date today = new Date();   									// 新增訂單的時間
		// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
		RecipeOrderBean ob = new RecipeOrderBean(null, totalAmount, shippingAddress, 
				bNO, invoiceTitle, today, null, null);
		// 新建一個存放訂單明細的Set物件: items
		Set<RecipeOrderItemBean> items = new HashSet<RecipeOrderItemBean>();
		// 取出存放在購物車內的商品，放入Map型態的變數cart，準備將其內的商品一個一個轉換為OrderItemBean，
		// 然後存入items。
		Map<String, RecipeOrderItem> cart = sc.getContent();
		Set<String> set = cart.keySet();
		for (String k : set) {
			RecipeOrderItem oi = cart.get(k);   // 經由Map物件的 get方法取出Key所對應的value物件
			String description = oi.getReid().substring(0, 5) + " " +  
                         		 // 比較上下兩行的寫法
			                     oi.getRename() +  " " +  
			                     oi.getIngredient() ;
			// 由於表格的Primary Key為自動遞增，為了配合Hibernate，在此主鍵設定為null
			// (Hibernate規定：自動遞增的主鍵，其對應之物件的欄位必須是null)，絕對不可以是零。
			RecipeOrderItemBean oib = new RecipeOrderItemBean(null, 0, oi.getReid() ,description, oi.getQty(), 
										oi.getPrice(), oi.getDiscount());
			items.add(oib);
		}
		
	    ob.setItems(items);
	// 執行到此，購物車內所有購買的商品已經全部轉換為為OrderItemBean物件，並放在Items內
	try {
		RecipeOrderService orderService = new RecipeOrderService();
		orderService.persistOrder(ob);
		session.removeAttribute("RecipeShoppingCart");
		response.sendRedirect(response.encodeRedirectURL ("./ThanksForOrdering.jsp"));
		return;
	} catch(RuntimeException ex){
		String message = ex.getMessage();
		String shortMsg = "" ;   
		shortMsg =  message.substring(message.indexOf(":") + 1);
		System.out.println(shortMsg);
		session.setAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
		//System.out.println("處理訂單時發生異常: " + message);
		response.sendRedirect(response.encodeRedirectURL ("./ShowRecipeCartContent.jsp"));
		return;
	}
	
	}

}
