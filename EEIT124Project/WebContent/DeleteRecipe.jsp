<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新食譜</title>
</head>
<body>
<h2>
刪除食譜
</h2>
<form action=".\RecipeServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td width="30">請輸入想刪除的食譜序號</td>
    <td><input type="text" name="reid" size="5" maxlength="5"></td>
</tr>
</table>
<center>
<input type="submit" name="Delete" value="送出">
<a href="Recipe.jsp"><input type="button" value="取消"/></a>
</center>
</form>
</body>
</html>