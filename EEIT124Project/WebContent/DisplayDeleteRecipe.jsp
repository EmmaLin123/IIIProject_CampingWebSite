<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="BIG5">
<title>刪除食譜內容</title>
</head>
<body>
<jsp:useBean id="delete_recipe" class="shoppingMall.RecipeBean" scope="session"/>
<h2>
刪除食譜如下請確認
</h2>
<form action=".\RecipeServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr bgcolor="#FFFFE1">
<td>食譜序號</td>
    <td><jsp:getProperty name="delete_recipe" property="reid" /></td>
</tr>
</table>
<center>
<input type="submit" name="confirm2" value="確認" >
</center>
</form>
</body>
</html>