<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新食譜</title>
</head>
<body>
<h2>
更新食譜
</h2>
<form action=".\RecipeServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<c:forEach var='recipe' items='${list}'>
<tr>
    <td width="30">請輸入想修改的食譜序號</td>
    <td><input type="text" name="reid" size="5" maxlength="5" value="${recipe.reid}"></td>
</tr>
<tr>
    <td>食譜名稱</td>
    <td><input type="text" name="rename" size="10" maxlength="10" value="${recipe.rename}">   
    </td>
</tr>
<tr>
    <td>食譜簡介</td>
    <td><textarea name="brief" cols="40" rows="5" >${recipe.brief}</textarea>(限字200個字)</td>
</tr>
<tr>
    <td width="150">圖片上傳</td>
    <td><input type="text" name="img" size="100" maxlength="200" value="${recipe.image}"></td>
</tr>
<tr>
    <td>食材</td>
     <td><textarea cols="50" rows="5" name="INGREDIENTS" >${recipe.ingredient}</textarea></td>
</tr>
<tr>
    <td>步驟一</td>
     <td><textarea cols="50" rows="5" name="tip1" >${recipe.tip1}</textarea></td>
</tr>
<tr>
    <td>步驟二</td>
    <td><textarea cols="50" rows="5" name="tip2" >${recipe.tip2}</textarea></td>
</tr>
<tr>
    <td>步驟三</td>
    <td><textarea cols="50" rows="5" name="tip3" >${recipe.tip3}</textarea></td>
</tr>
<tr>
    <td>步驟四</td>
    <td><textarea cols="50" rows="5" name="tip4" >${recipe.tip4}</textarea></td>
</tr>
<tr>
    <td>步驟五</td>
    <td><textarea cols="50" rows="5" name="tip5">${recipe.tip5}</textarea></td>
</tr>
<tr>
    <td>步驟六</td>
    <td><textarea cols="50" rows="5" name="tip6">${recipe.tip6}</textarea></td>
</tr>
<tr>
    <td>備註</td>
    <td><textarea cols="50" rows="5" name="note">${recipe.note}</textarea></td>
</tr>
<tr>
    <td>份量</td>
    <td><input type="text" name="people" size="5" maxlength="5" value="${recipe.people}">/人份   
    </td>
</tr>
<tr>
    <td>預估製作時間</td>
    <td><input type="text" name="time" size="5" maxlength="5" value="${recipe.time}">/分鐘   
    </td>
</tr>
</c:forEach>
</table>
<center>
<input type="submit" name="Update" value="送出">
<a href="Recipe.jsp"><input type="button" value="取消"/></a>
</center>
</form>
</body>
</html>