<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function cancelOrder() {
	if (confirm("確定取消此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "RecipeCancel";
		document.forms[0].action="<c:url value='./RecipeProcessOrderServlet' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
function reconfirmOrder() {
	var sa = document.getElementById('ShippingAddress').value;
	if  (sa === "") {
		window.alert ('出貨地址不能是空白');
		return ; 
	}
	if (confirm("確定送出此份訂單 ? ") ) {
		// 接收此資料的Servlet會使用 finalDecision 參數的值
		document.forms[0].finalDecision.value = "RecipeOrder";
		document.forms[0].action="<c:url value='./RecipeProcessOrderServlet' />";
		document.forms[0].method="POST";
		document.forms[0].submit();
		return;
	} else {
		return;
	}
}
</script>
<meta charset="UTF-8">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 取得今天的日期，今天的日期應當在最後確認時才取得 -->
<jsp:useBean   id="today"  class="java.util.Date" scope="session"/> 
<title>Insert title here</title>
</head>
<body>
<c:set var="funcName" value="CHE" scope="session"/>
<div style="text-align:center">
<h3>請確認下列訊息：</h3>
<form action="<c:url value='./RecipeProcessOrderServlet' />" method="post">
<table border='1' style="background:#F5EBFF; border-color:rgb( 100, 100, 255); border-style: outset;">
<tr>
         <td style="text-align:left; border-color: #FFBD32; border-style: ridge;">
         	訂單日期：<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>
         </td>
</tr>
<tr>
<td colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
出貨地址：<Input style="background:#ECFFCD;" size="60" type="text" id='ShippingAddress' 
                   name="ShippingAddress" value="">
                   <font color='red'>${errorMsg.ShippingAddress}</font>
</td>
</tr>
<tr>
      <td colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
                 統一編號：<Input style="background:#ECFFCD;" size="10" type="text" name="BNO" value="">
         </td>
      </tr>
<tr>
<td colspan='3' style="text-align:left; border-color: #FFBD32; border-style: ridge;">
                                   發票抬頭：<Input style="background:#ECFFCD;" size="50" type="text" 
                      name="InvoiceTitle" value="" >
         </td>
</tr>
<tr>
   <td colspan='3'>
   <table border='1' style="background:#FFE7CD; border-color:rgb( 100, 100, 255); ">
   <tr><th style="text-align:center;font-size: 12pt;" width="350">菜餚名稱</th>
         <th style="text-align:center;font-size: 12pt;" width="80">食譜序號</th>
         <th style="text-align:center;font-size: 12pt;" width="80">食材</th>
         <th style="text-align:center;font-size: 12pt;" width="80">單價</th>
         <th style="text-align:center;font-size: 12pt;" width="60">數量</th>
         <th style="text-align:center;font-size: 12pt;" width="110">小計</th></tr>
   <c:forEach varStatus="vs" var="anEntry" items="${RecipeShoppingCart.content}">
   <tr height='16'>
   <td style="text-align:left  ;font-size: 11pt;">${anEntry.value.rename}</td>
   <td style="text-align:center;font-size: 11pt;">${fn:substring(anEntry.value.reid, 0, 5)}</td>
   <td style="text-align:center;font-size: 11pt;">${anEntry.value.ingredient}</td>
   <td style="text-align:right ;font-size: 11pt;"><fmt:formatNumber value="${anEntry.value.price * anEntry.value.discount}" pattern="#,###" />元</td>
   <td style="text-align:right ;font-size: 11pt;">${anEntry.value.qty}</td>
   <td style="text-align:right ;font-size: 11pt;">
   <fmt:formatNumber 
          	value="${anEntry.value.price * anEntry.value.discount * anEntry.value.qty}" pattern="#,###,###" />元
          </td> 
   </tr>
   </c:forEach>  
   <tr height='16'>
          <td style="text-align:right;font-size: 11pt;" colspan='5' >總計金額：</td>
          <td style="text-align:right;font-size: 11pt;" >
          <fmt:formatNumber value="${RecipeShoppingCart.subtotal}" pattern="#,###,###" />元</td>   
    </tr>   
   </table>
   </td></tr>
   <input type="hidden" name="finalDecision"  value="">   
   <input type="button" name="RecipeOrderBtn"  value="確定送出" onclick="reconfirmOrder()">
   <input type="button" name="RecipeCancelBtn" value="取消訂單" onclick="cancelOrder()">
</table>
</form>
</body>
</html>