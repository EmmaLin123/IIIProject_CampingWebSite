<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>食譜 的主頁</title>
<style type="text/css">
    h1{
       text-align:center;
    }
    td{
      text-align:center
    }
</style>
</head>
<body>
<h1>露營料理食譜</h1>

<form action="./RecipeSelectServlet2" method="post">
<table border="1" width="100%">
    <tr align="center" valign="top">
        <td colspan="10">
<%--             共<%=maxPage%>頁&nbsp;共<%=pageNum%>條記錄&nbsp;當前是第<%=number%>頁&nbsp; --%>
<%--             <a href="<%=rawQuery%>pageNum=0">首頁</a>&nbsp; --%>
<%--             <a href="<%=rawQuery%>pageNum=<%=number - 1%>">上一頁</a>&nbsp; --%>
<%--             <a href="<%=rawQuery%>pageNum=<%=number + 1%>">下一頁</a>&nbsp; --%>
<%--             <a href="<%=rawQuery%>pageNum=<%=maxPage%>">末頁</a> --%>
            <a href="NewRecipe.jsp"><input type="button" value="分享食譜"/></a>
            <a href="UpdateRecipe.jsp"><input type="button" value="更新食譜"/></a>
            <a href="DeleteRecipe.jsp"><input type="button" value="刪除食譜"/></a>
            
        </td>
    </tr>
    <tr align="center">
        <td colspan="10">
            <form action="Recipe.jsp" method="get">
                食譜關鍵字：<input type="text" name="rename" title=""/><input type="submit" value="查詢"/>
            </form>
        </td>
    </tr>
    <tr>
        <th>食譜序號</th>
        <th>食譜名稱</th>
        <th>食譜簡述</th>
        <th>份量</th>
        <th>預估製作時間</th>
    </tr>
    <c:forEach var='recipe' items='${list}'>
    <tr>
        <td width="100">${recipe.reid}</td>
        <td><img src="${recipe.image}"; width="200" Height="200"><br>${recipe.rename};</a></td>
        <td>${recipe.brief}</td>
        <td width="60">${recipe.people}人份</td>
        <td>${recipe.time}</td>
    </tr>
    </c:forEach>
    
</table>
</form>
</body>
</html>