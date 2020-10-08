<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
    <title>食譜 的主頁</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
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
<%--     <c:set var="bean" value="${requestScope.bean}"/> --%>
<%--     <c:set var="totalPages" value="${requestScope.totalPages}"/> --%>
<%--     <c:set var="page" value="${requestScope.page}"/> --%>
    

<table border="1" width="100%">
    <tr class="table-active" align="center" valign="top">
        <td colspan="10">
            <a href="NewRecipe.jsp"><input type="button" value="分享食譜"/></a>
            <a href="DeleteRecipe.jsp"><input type="button" value="刪除食譜"/></a>
            <form action="./RecipeServlet" method="post">
            請輸入更新食譜序號 <input type="text" name="upid" title=""/><input type="submit" name="preUp" value="更新食譜"/>
            </form>
        </td>
    </tr>
    <tr align="center">
        <td colspan="10">
            <form action="./RecipeServlet" method="post">
                食譜關鍵字：<input type="text" name="re_name" title=""/><input type="submit" name="submit" value="查詢"/>
            </form>
        </td>
    </tr>
 <form action="/RecipeSelectServlet2" method="post">
    <tr>
        
        <th>食譜名稱</th>
        <th>食譜簡述</th>
        <th>份量</th>
        <th>預估製作時間</th>
        <th></th>
    </tr>
    <div class="shadow-sm p-3 mb-5 bg-white rounded">
    <c:forEach var='recipe' items='${bean}'>
    <tr class="table-success">
        <td><a href="<c:url value='/RecipeContentServlet?reid=${recipe.reid}'/>"><img src="${recipe.image}" width="300" Height="300" /><br>${recipe.rename}</a></td>
        <td>${recipe.brief}</td>
        <td width="60">${recipe.people}人份</td>
        <td>${recipe.time}分鐘</td>
        <form action="<c:url value='BuyBook.do' />" method="POST">
        <td>購買數量:
               <select name='qty'>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
        
        <input type="submit" name="cart" value="放入購物車"><td/>
        </form>
    </tr>
    </c:forEach>
    </div>
</table>
<div>
        <div>
            <nav>
                <ul class="menu">
                    <li><a href="<c:url value="./RecipeSelectServlet2?page=1"/>">首頁</a></li>
                    <li><a href="<c:url value="./RecipeSelectServlet2?page=${page-1>1?page-1:1}"/>">上一頁</a>
                    </li>

                    <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                        <c:set var="active" value="${loop.index==page?'page':''}"/>
                        <li ${page}">
                            <a href="<c:url value="./RecipeSelectServlet2?page=${loop.index}"/>">${loop.index}</a>
                        </li>
                    </c:forEach>
                    <li>
                        <a href="<c:url value="./RecipeSelectServlet2?page=${page+1<totalPages?page+1:totalPages}"/>">下一頁</a>
                    </li>
                    <li>
                        <a href="<c:url value="./RecipeSelectServlet2?page=${totalPages}"/>">尾頁</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

</div>

</form>
</body>
</html>