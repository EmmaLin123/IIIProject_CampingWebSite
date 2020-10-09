<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<body bgcolor='lightyellow'>
<h1>露營料理食譜</h1>
<table border="1" width="100%">
    <tr class="table-active" align="center" valign="top">
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
    <div class="shadow-sm p-3 mb-5 bg-white rounded">
    <c:forEach var='recipe' items='${beandata}'>
    <tr class="table-success">
        <td><a href="<c:url value='/RecipeContentServlet?reid=${recipe.reid}'/>"><img src="${recipe.image}"; width="300" Height="300"><br>${recipe.rename}</a></td>
        <td>${recipe.brief}</td>
        <td width="60">${recipe.people}人份</td>
        <td>${recipe.time}分鐘</td>
                <form action="<c:url value='./BuyRecipeServlet'/>" method="POST">
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
                  <select/>  
         <!-- 這些隱藏欄位都會送到後端 -->
               <Input type='hidden' name='REID' value='${recipe.reid}'>
               <Input type='hidden' name='rename' value='${recipe.rename}'>
               <Input type='hidden' name='ingredient' value='${recipe.ingredient}'>
               <Input type='hidden' name='price' value='${recipe.price}'>
               <Input type='hidden' name='discount' value='${recipe.discount}'>  
        <input type="submit" name="cart" value="放入購物車"><td/>
        </form>
    </tr>
    </c:forEach>
    </div>
</table>
</body>
</html>