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

<%-- <% --%>
//     int pageNum = RecipeList.size();
//     int pageSize = 10;

//     String strNum = request.getParameter("pageNum");
//     // get current page number
//     int number;
//     if (strNum == null || strNum.equals("0")) {
//         number = 1;
//     } else {
//         number = Integer.parseInt(strNum);
//     }
//     int maxPage;
//     if (pageNum % 10 == 0) {
//         maxPage = pageNum / 10;
//     } else {
//         maxPage = pageNum / 10 + 1;
//     }
//     if (number > maxPage) {
//         number = maxPage;
//     }
//     int start = (number - 1) * pageSize;
//     int end = number * pageSize;
//     if (end > pageNum) {
//         end = pageNum;
//     }
<%-- %> --%>
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
        <td>食譜名稱</td>
        <td>食譜簡述</td>
        <td>份量</td>
        <td>預估時間</td>
    </tr>
    
    <c:forEach varStatus="stVar" var="aRecipeBean" item="${products_DPP}">
    <tr>
        <td><img src="${aRecipeBean.Image}"; width="200" Height="200"><br>${aRecipeBean.Rename};</a> </td>
        <td>${aRecipeBean.Brief}</td>
        <td>${aRecipeBean.People}</td>
        <td>${aRecipeBean.Time}</td>
    </tr>
    </c:forEach>
    
</table>
</body>
</html>