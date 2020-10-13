<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>確認食譜內容</title>
</head>
<body>
<jsp:useBean id="reg_recipe" class="shoppingMall.RecipeBean" scope="session"/>
<h2>
食譜內容如下請確認
</h2>
<form action=".\RecipeServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr bgcolor="#FFFFE1">
<td>食譜序號</td>
    <td><jsp:getProperty name="reg_recipe" property="reid" /></td>
</tr>
			<tr bgcolor="#FFFFE1">
				<td>食譜名稱</td>
				<td><jsp:getProperty name="reg_recipe" property="rename" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食譜簡介</td>
				<td><jsp:getProperty name="reg_recipe" property="brief" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>圖片上傳</td>
				<td><jsp:getProperty name="reg_recipe" property="image" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>食材</td>
				<td><jsp:getProperty name="reg_recipe" property="ingredient" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>步驟一</td>
				<td><jsp:getProperty name="reg_recipe" property="tip1" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>步驟二</td>
				<td><jsp:getProperty name="reg_recipe" property="tip2" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>步驟三</td>
				<td><jsp:getProperty name="reg_recipe" property="tip3" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>步驟四</td>
				<td><jsp:getProperty name="reg_recipe" property="tip4" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>步驟五</td>
				<td><jsp:getProperty name="reg_recipe" property="tip5" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>步驟六</td>
				<td><jsp:getProperty name="reg_recipe" property="tip6" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>備註</td>
				<td><jsp:getProperty name="reg_recipe" property="note" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>份量</td>
				<td><jsp:getProperty name="reg_recipe" property="people" /></td>
			</tr>
			<tr bgcolor="#FFFFE1">
				<td>預估製作時間</td>
				<td><jsp:getProperty name="reg_recipe" property="time" /></td>
			</tr>
</table>
<center>
<input type="submit" name="confirm" value="確認" >
<a href="./RecipeSelectServlet2?page=1"><input type="button" value="取消" ></a>
</center>
</form>
</body>
</html>
