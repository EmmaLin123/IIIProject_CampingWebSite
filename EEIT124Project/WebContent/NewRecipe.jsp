<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>分享食譜</title>
</head>
<body>
<h2>
食譜內容
</h2>
<!-- <form action=".\RegisterServlet" method="post"> -->
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td width="30">食譜序號</td>
    <td><input type="text" name="reid" size="5" maxlength="5"></td>
</tr>
<tr>
    <td>食譜名稱</td>
    <td><input type="text" name="rename" size="10" maxlength="10">   
    </td>
</tr>
<tr>
    <td>食譜簡介</td>
    <td><textarea name="brief" cols="40" rows="5"></textarea>(限字200個字)</td>
</tr>
<tr>
    <td width="150">圖片上傳</td>
    <td><input type="file" name="img"></td>
</tr>
<tr>
    <td>食材</td>
     <td><textarea cols="50" rows="5" name="INGREDIENTS" placeholder="請輸入食材內容"></textarea></td>
</tr>
<tr>
    <td>步驟一</td>
     <td><textarea cols="50" rows="5" name="tip1" placeholder="請輸入步驟"></textarea></td>
</tr>
<tr>
    <td>步驟二</td>
    <td><textarea cols="50" rows="5" name="tip2" placeholder="請輸入步驟"></textarea></td>
</tr>
<tr>
    <td>步驟三</td>
    <td><textarea cols="50" rows="5" name="tip3" placeholder="請輸入步驟"></textarea></td>
</tr>
<tr>
    <td>步驟四</td>
    <td><textarea cols="50" rows="5" name="tip4" placeholder="請輸入步驟"></textarea></td>
</tr>
<tr>
    <td>步驟五</td>
    <td><textarea cols="50" rows="5" name="tip5" placeholder="請輸入步驟" ></textarea></td>
</tr>
<tr>
    <td>步驟六</td>
    <td><textarea cols="50" rows="5" name="tip6" placeholder="請輸入步驟"></textarea></td>
</tr>
<tr>
    <td>備註</td>
    <td><textarea cols="50" rows="5" name="note" placeholder="請輸入"></textarea></td>
</tr>
<tr>
    <td>份量</td>
    <td><input type="text" name="people" size="5" maxlength="5">/人份   
    </td>
</tr>
<tr>
    <td>預估製作時間</td>
    <td><input type="text" name="time" size="5" maxlength="5">/分鐘   
    </td>
</tr>

</table>
<center>
<input type="submit" name="submit" value="送出">
</center>
<!-- </form> -->
</body>
</html>