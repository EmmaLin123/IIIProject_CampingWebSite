<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>訂單詳細明細</title>
<style type="text/css">
#main {
	position: absolute;
	top: 110px;
	left: 210px;
}

#borderA {
	border: 1px solid black;
}
</style>
</head>
<body style="background: #EBFFEB;">
<TABLE style="margin-left: auto; margin-right: auto; background: #F0E4F4; border: 1px blue solid;">
		<tr id='borderA' height='50'>
			<th id='borderA' align="center" colspan="7"><h3>訂單明細</h3></th>
		</tr>
		<tr id='borderA' height='36'>
			<td colspan="7">
				<table>
					<tr id='borderA'>
						<td align="Left" width="350px">
							<b>出貨地址：</b>${RecipeOrderBean.shippingAddress}
						</td>
						<td align="center" width="300px">
							<b>訂購日期：</b>${RecipeOrderBean.orderDate}
						</td>
						<td align="center" width="280px">
							<b>訂單編號：</b>${RecipeOrderBean.reOrderNo}
						</td>
						</tr>
				</table>
			</td>
		</tr>
		<tr id='borderA' height='36'>
			<th id='borderA' width="100px" align="center">食譜序號</th>
			<th id='borderA' width="400px" align="center">詳細食材資訊</th>
			<th id='borderA' width="70px" align="center">單價</th>
			<th id='borderA' width="50px" align="center">數量</th>
			<th id='borderA' width="100px" align="center">總價</th>
			<th id='borderA' width="80px" align="center">折扣</th>
			<th id='borderA' width="100px" align="center">售價</th>
		</tr>
		<c:set var="subtotal" value="0" />
		<c:forEach var="aBean" varStatus="stat" items="${RecipeOrderBean.items}">
		<c:choose>
				<c:when test="${ stat.count % 2 == 0 }">
					<c:set var="aColor" value="#E6FFA0" />
				</c:when>
				<c:otherwise>
					<c:set var="aColor" value="#EBFFEB" />
				</c:otherwise>
			</c:choose>
			<tr id='borderA' bgColor="${aColor}" height='30'>
				<td id='borderA' align="center">${aBean.reid}</td>
				<td id='borderA' align="left">${aBean.description}</td>
				<td id='borderA' align="right">${aBean.unitPrice}&nbsp;</td>
				<td id='borderA' align="right">${aBean.quantity}&nbsp;</td>
				<td id='borderA' align="right">${aBean.unitPrice*aBean.quantity}&nbsp;</td>
				<td id='borderA' align="center">${aBean.discount}&nbsp;</td>
				<td id='borderA' align="right"><fmt:formatNumber
						value="${aBean.unitPrice*aBean.discount*aBean.quantity}"
						pattern="#,###,###" />元</td>
				<c:set var="subtotal"
					value="${ subtotal + aBean.unitPrice * aBean.discount * aBean.quantity }" />
			</tr>
		</c:forEach>
		<tr height='30'>
			<TD id='borderA' align="center" rowspan="3" colspan="5">&nbsp;</TD>
			<TD id='borderA' width="60px" align="center"><b>合 計</b></TD>
			<TD id='borderA' width="100px" align="right">
			   <fmt:formatNumber value="${subtotal}" pattern="#,###,###" />元</TD>
		</tr>
		<tr height='30'>
			<TD id='borderA' width="60px" align="center"><b>總金額</b></TD>
			<TD id='borderA' width="100px" align="right">
			    <fmt:formatNumber value="${RecipeOrderBean.totalAmount}" pattern="#,###,###" />元</TD>
		</tr>
	</TABLE>
	<p />

	<div style="text-align: center">
		<a href="<c:url value='./RecipeOrderListServlet' />">回上一頁</a>&nbsp;&nbsp;
		<a href="<c:url value='./RecipeSelectServlet2?page=1' />">回首頁</a>
	</div>
</body>
</html>
</body>
</html>