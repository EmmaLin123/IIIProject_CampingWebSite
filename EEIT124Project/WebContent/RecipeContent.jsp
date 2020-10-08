<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
 table{
    border-collapse:collapse
 }



</style>
<title>Content</title>
</head>
<body>
<table border="1" width="80%">
<a href="<c:url value="./RecipeSelectServlet2?page=1"/>"><input type="button" value="食譜列表"/></a>
<c:forEach var='recipe' items='${list}'>
<tr  align="center">
        <td colspan="8"><h1>${recipe.rename}</h1><br>
            <img src="${recipe.image}" width="500" Height="500" /><br>
            <input type="submit" name="qtysubmit" value="購買食材">
        </td>
        
 <tr align="center" > 
        <td><h4>食譜簡述:</h4></td>
        <td colspan="7"><h4>${recipe.brief}</h4></td>
              
    </tr> 
 <tr align="center"> 
        <td><h4>食譜序號</h4></td>
        <td><h4>${recipe.reid}</h4></td>  
        <td><h4>預估製作時間</h4></td>
        <td><h4>${recipe.time}分鐘</h4></td>
        <td><h4>份量<h4></td>
        <td><h4>${recipe.people}人份</h4></td>       
    </tr> 
    <tr align="center">   
        <td><h4>材料</h4></td>
        <td colspan="7"> ${recipe.ingredient}</td>      
    </tr> 
    <tr>   
        <td align="center"><h4>步驟一</h4></td>
        <td colspan="7"> ${recipe.tip1}</td>      
    </tr> 
    <tr>   
        <td align="center"><h4>步驟二</h4></td>
        <td colspan="7"> ${recipe.tip2}</td>      
    </tr>
    <c:if test="${recipe.tip3 != 'null'}">
    <tr>   
        <td align="center"><h4>步驟三</h4></td>
        <td colspan="7">${recipe.tip3}</td>      
    </tr>
    </c:if>
    <c:if test="${recipe.tip4 != 'null'}">
    <tr>   
        <td align="center"><h4>步驟四</h4></td>
        <td colspan="7">${recipe.tip4}</td>      
    </tr>
    </c:if>    
    <c:if test="${recipe.tip5 != 'null'}">
    <tr>   
        <td align="center"><h4>步驟五</h4></td>
        <td colspan="7">${recipe.tip5}</td>      
    </tr>
    </c:if>   
    <c:if test="${recipe.tip6 != 'null'}">
    <tr>   
        <td align="center"><h4>步驟六</h4></td>
        <td colspan="7">${recipe.tip6}</td>      
    </tr>
    </c:if>
    <c:if test="${recipe.note != 'null'}">
    <tr>   
        <td align="center"><h4>小叮嚀:</h4></td>
        <td colspan="7">${recipe.note}</td>      
    </tr>
    </c:if> 

</c:forEach>
</table>
</body>
</html>