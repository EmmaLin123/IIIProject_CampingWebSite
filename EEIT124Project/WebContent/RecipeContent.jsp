<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Content</title>
</head>
<body>
<form action="/RecipeSelectServlet2" method="post">
<tr>
        <th>���ЦW��</th>
        <th>����²�z</th>
        <th>���q</th>
        <th>�w���s�@�ɶ�</th>
    </tr>
<c:forEach var='recipe' items='${bean}'>
<c:if test="${recipe.re_id}">

</c:if>
</c:forEach>
</body>
</html>