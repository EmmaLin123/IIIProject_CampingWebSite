<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>
<%
response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server 
response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance 
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale" 
response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show ShoppingCart Content</title>
<script type="text/javascript">

function confirmDelete(n) {
	if (confirm("確定刪除此項商品 ? ") ) {
		document.forms[0].action="<c:url value='./UpdateRecipeServlet?cmd=DEL&reid=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		return;
	}
}
function modify(key, qty, index) {
	var x = "newQty" + index;
	var newQty = document.getElementById(x).value;
	if  (newQty < 0 ) {
		window.alert ('數量不能小於 0');
		return ; 
	}
	if  (newQty == 0 ) {
		window.alert ("請執行刪除功能來刪除此項商品");
		document.getElementById(x).value = qty;
		return ; 
	}
	if  (newQty == qty ) {
		window.alert ("新、舊數量相同，不必修改");
		return ; 
	}
	if (confirm("確定將此商品的數量由" + qty + " 改為 " + newQty + " ? ") ) {
		document.forms[0].action="<c:url value='./UpdateRecipeServlet?cmd=MOD&reid=" + key + "&newQty=" + newQty +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
	} else {
		document.getElementById(x).value = qty;
	}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
      return false;
   }
   return true;
}
function Checkout(qty) {
	if (qty == 0)  {
		alert("無購買任何商品，不需結帳");
		return false;
	}
	if (confirm("再次確認訂單內容 ? ") ) {
		return true;
	} else {
		return false;
	}
}
function Abort() {
	if (confirm("確定放棄購物 ? ") ) {
		return true;
	} else {
		return false;
	}
}
</script>
</head>
<body>
<c:set var="funcName" value="CHE" scope="session"/>
<c:choose>
   <c:when test="${RecipeShoppingCart.subtotal > 0}">
      <c:set var="subtotalMessage" value="金額小計:${RecipeShoppingCart.subtotal} 元"/>
      <c:set var="subtotal" value="${RecipeShoppingCart.subtotal}"/>  
   </c:when>
   <c:otherwise>
      <c:set var="subtotalMessage" value="金額小計:  0 元"/>
      <c:set var="subtotal" value="0"/>                
   </c:otherwise>
</c:choose>

<TABLE style="margin: 0 auto; width:820px; background:#EFEFFB; border:2px solid black;">
<TR><TD colspan='4'>
<!--          購物車的標題          --> 
<table>
<tr>
<td></td>
<td><font size='+2'>購物清單</font></td>
<td></td>
</tr>
</table>
</TD></TR>

<TR>
   <TD>
   <table border='1'>
   <tr width='100'><th>菜餚名稱</th><th  width='50'>食譜序號</th><th width='200'>食材</th><th>單價</th><th>優惠</th><th>數量</th><th>小計</th><th>修改</th></tr>
     <c:forEach varStatus="vs" var="anEntry" items="${RecipeShoppingCart.content}">
   <tr>
   <td>${anEntry.value.rename}</td>
   <td>${fn:substring(anEntry.value.reid,0,5)}</td>
   <td>${anEntry.value.ingredient}</td>
   <td><fmt:formatNumber value="${anEntry.value.price}" pattern="#,###" />元</td>
   <c:if test="${anEntry.value.discount < 1}">
   <td>${anEntry.value.discount*10}折</td>
   </c:if>
   <c:if test="${anEntry.value.discount>=1}">
   <td></td>
   </c:if>
   <td>
   <input id="newQty${vs.index}" name="newQty" type="text" value="<fmt:formatNumber value="${anEntry.value.qty}" />" name="qty" onkeypress="return isNumberKey(event)" >
   </td>
   <td align='right'><fmt:formatNumber value="${anEntry.value.price * anEntry.value.discount * anEntry.value.qty}" pattern="#,###,###" />元</td>
   <td><input type="button" name="update" value="修改" onclick="modify(${anEntry.key}, ${anEntry.value.qty},${vs.index})">
       <input type="button" name="delete" value="刪除" onclick="confirmDelete(${anEntry.key})"></td>
   </tr>
   </c:forEach>
   <tr height='16'>
   <td colspan='6' align='right'>總計金額：</td>
   <td align='right'><fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</td>
   <td align='right'>&nbsp;</td>
   </tr>
   </table>
   </TD>
   </TR>
<tr height='80'>
<td>
   <table border='1'>
   <tr>
   <td align='center'>
   <a href="<c:url value='./RecipeSelectServlet2?page=${param.page}' />">繼續購物</a>
   </td>
   <td align='center'>
   <a href="<c:url value='./RecipeCheckoutServlet' />" onClick="return Checkout(${subtotal});">再次確認</a>
   </td>
   <td align='center'>
   <a href="<c:url value='/AbortRecipeServlet' />" onClick="return Abort();">放棄購物</a>
   </td>
   </tr>
   </table>
</td>
</tr>
</TABLE>

<div style='text-align:center;'>
<c:if test='${not empty OrderErrorMessage}'>
		<font color='red'>${OrderErrorMessage}</font>
		<c:remove var="OrderErrorMessage"/>	
</c:if>
</div>
    
<form>
   <input type="hidden" name="a"/>
</form>

</body>
</html>