<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單列表</title>
<style type="text/css">
#borderA {
   border:1px solid black;
}

</style>
</head>
<body style="background: #EBFFEB;">
<c:set var="funcName" value="ORD" scope="session"/>
<table style="margin-left:auto; margin-right:auto; width:810; background:#F5EBFF; border:2px solid blue; border-style: outset; ">
        <tr id='borderA' height='50' >
			<th id='borderA'  colspan="4" align="center">訂購紀錄</th>
		</tr>
		<tr id='borderA' height='36' >
			<th id='borderA'>訂單編號</th>
			<th id='borderA'>訂購日期</th>
			<th id='borderA'>總金額</th>
			<th id='borderA'>送貨地址</th>
		</tr>
		<c:forEach var="anOrderBean" items="${orders}">
		<TR id='borderA' height='30'>
			<TD id='borderA' width="86" align="center">
		 <a  href='<c:url value='./RecipeOrderDetailServlet?reorderNo=${anOrderBean.reOrderNo}' />'>
				    ${anOrderBean.reOrderNo}
			    </a>
		</TD>
		<TD id='borderA' width="100" align="center">${anOrderBean.orderDate}</TD>
			<TD id='borderA' width="80" align="right">${anOrderBean.totalAmount}</TD>
			<TD id='borderA' width="400" align="left">&nbsp;${anOrderBean.shippingAddress}</TD>
       </TR>
		</c:forEach>
		<tr height='36' id='borderA'>
			<td id='borderA' align="center" colspan="4"><a href="<c:url value='./RecipeSelectServlet2?page=1' />">回首頁</a></td>
		</tr>
	</TABLE>
	</center>
</body>
</html>