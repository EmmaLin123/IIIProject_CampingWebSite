<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
    h1{
       text-align:center;
    }
    td{
      text-align:center
    }
    li {
    float:left;
    text-align:center;
    list-style-type:none;
    padding:5px
    }    
</style>
</head>
<body>
<h1>露營料理食譜</h1>
<table border="1" width="100%">
    <tr align="center" valign="top">
        <td colspan="10">
            <a href="NewRecipe.jsp"><input type="button" value="分享食譜"/></a>
            <a href="DeleteRecipe.jsp"><input type="button" value="刪除食譜"/></a>
            <a href="<c:url value="./RecipeSelectServlet2?page=1"/>"><input type="button" value="食譜列表"/></a>
            <form action="./RecipeServlet" method="post">
            請輸入更新食譜序號 <input type="text" name="upid" title=""/><input type="submit" name="preUp" value="更新食譜"/>
            </form>
        </td>
    </tr>
    <tr align="center">
        <td colspan="10">
            <form action="./RecipeServlet" method="post">
                食譜關鍵字：<input type="text" name="re_name" title=""/><input type="submit" name="submit"value="查詢"/>
            </form>
        </td>
    </tr>
    <tr>
        <th>食譜名稱</th>
        <th>食譜簡述</th>
        <th>份量</th>
        <th>預估製作時間</th>
    </tr>
    <c:forEach var='recipe' items='${beandata}'>
    <tr>
        <td><a href="<c:url value="./RecipeContent.jsp"/>"><img src="${recipe.image}"; width="200" Height="200"><br>${recipe.rename}</a></td>
        <td>${recipe.brief}</td>
        <td width="60">${recipe.people}人份</td>
        <td>${recipe.time}分鐘</td>
    </tr>
    </c:forEach>
</table>
</div>

</body>
</html>