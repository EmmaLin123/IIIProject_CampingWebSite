<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<title>感謝訂購</title>
</head>
<body>
<c:set var="funcName" value="END" scope="session"/>
<CENTER>
<h1>
<BR><FONT COLOR='RED'>露營網路購物商城</FONT><BR>
 感謝您的訂購<BR>
 期待您再度光臨 
  </h1> 
<h3><a href="<c:url value="./RecipeSelectServlet2?page=1"/>">點擊返回食譜首頁</a></h3>
 <BR>
</CENTER>
</body>
</html>