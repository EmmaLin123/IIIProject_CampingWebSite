<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>��s����</title>
</head>
<body>
<h2>
��s����
</h2>
<form action=".\RecipeServlet" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td width="30">�п�J�Q�ק諸���ЧǸ�</td>
    <td><input type="text" name="reid" size="5" maxlength="5"></td>
</tr>
<tr>
    <td>���ЦW��</td>
    <td><input type="text" name="rename" size="10" maxlength="10">   
    </td>
</tr>
<tr>
    <td>����²��</td>
    <td><textarea name="brief" cols="40" rows="5"></textarea>(���r200�Ӧr)</td>
</tr>
<tr>
    <td width="150">�Ϥ��W��</td>
    <td><input type="text" name="img" size="100" maxlength="200"></td>
</tr>
<tr>
    <td>����</td>
     <td><textarea cols="50" rows="5" name="INGREDIENTS" placeholder="�п�J�������e"></textarea></td>
</tr>
<tr>
    <td>�B�J�@</td>
     <td><textarea cols="50" rows="5" name="tip1" placeholder="�п�J�B�J"></textarea></td>
</tr>
<tr>
    <td>�B�J�G</td>
    <td><textarea cols="50" rows="5" name="tip2" placeholder="�п�J�B�J"></textarea></td>
</tr>
<tr>
    <td>�B�J�T</td>
    <td><textarea cols="50" rows="5" name="tip3" placeholder="�п�J�B�J"></textarea></td>
</tr>
<tr>
    <td>�B�J�|</td>
    <td><textarea cols="50" rows="5" name="tip4" placeholder="�п�J�B�J"></textarea></td>
</tr>
<tr>
    <td>�B�J��</td>
    <td><textarea cols="50" rows="5" name="tip5" placeholder="�п�J�B�J" ></textarea></td>
</tr>
<tr>
    <td>�B�J��</td>
    <td><textarea cols="50" rows="5" name="tip6" placeholder="�п�J�B�J"></textarea></td>
</tr>
<tr>
    <td>�Ƶ�</td>
    <td><textarea cols="50" rows="5" name="note" placeholder="�п�J"></textarea></td>
</tr>
<tr>
    <td>���q</td>
    <td><input type="text" name="people" size="5" maxlength="5">/�H��   
    </td>
</tr>
<tr>
    <td>�w���s�@�ɶ�</td>
    <td><input type="text" name="time" size="5" maxlength="5">/����   
    </td>
</tr>

</table>
<center>
<input type="submit" name="reid1" value="�e�X">
<a href="Recipe.jsp"><input type="button" value="����"/></a>
</center>
</form>
</body>
</html>