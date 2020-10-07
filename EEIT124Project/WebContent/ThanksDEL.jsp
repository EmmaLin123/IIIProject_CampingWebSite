<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<%
response.setContentType("text/html;charset=UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食譜刪除成功</title>
</head>
<body>
食譜刪除成功!<br>
<a href="<c:url value="./RecipeSelectServlet2?page=1"/>"><input type="button" value="回食譜查詢"></a>
</body>
</html>