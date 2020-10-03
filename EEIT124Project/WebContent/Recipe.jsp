<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
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
<%!
    class Recipe {
        private String reid;
        private String rename;
        private String brief;
        private String image;
        private int people;
        private int time;

        Recipe(String reid, String rename, String brief, String image, int people, int time) {
            this.reid = reid;
            this.rename = rename;
            this.brief = brief;
            this.image = image;
            this.people = people;
            this.time = time;
        }

        String getReid() {
            return reid;
        }
        
        String getRename(){
        	return rename;
        }

        String getBrief() {
            return brief;
        }

        String getImage() {
            return image;
        }

        int getPeople() {
            return people;
        }

        int getTime() {
            return time;
        }
    }
%>
<%
    String URL = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
    String USERNAME = "project2";
    String PASSWORD = "project2";
    String DRIVER = "oracle.jdbc.driver.OracleDriver";
%>
<%
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;
%>
<%
    try {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        stat = conn.createStatement();
 
        String rawString = request.getParameter("rename");
        System.out.println(rawString);
        String rawQuery;
        StringBuilder sql = new StringBuilder();
        if (rawString == null || rawString.equals("")) {
            sql.append("SELECT * FROM RECIPE");
            rawQuery = "Recipe.jsp?";
        } else {
            String queryName = new String(rawString.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            sql.append("SELECT * FROM RECIPE WHERE re_name like ").append("'%").append(rawString).append("%'");
            System.out.println(sql);
            rawQuery = "Recipe.jsp?rename=" + rawString + "&";
            
        }
        rs = stat.executeQuery(sql.toString());
%>
<%
    List RecipeList = new ArrayList();
    while (rs.next()) {
    	String reid = rs.getString(1);
        String rename = rs.getString(2);
        String brief = rs.getString(3);
        String image = rs.getString(4);
        int people = rs.getInt(13);
        int time = rs.getInt(14);
        RecipeList.add(new Recipe(reid,rename,brief,image,people,time));
    }
%>
<%
    int pageNum = RecipeList.size();
    int pageSize = 10;
    System.out.println(pageNum);

    String strNum = request.getParameter("pageNum");
    // get current page number
    int number;
    if (strNum == null || strNum.equals("0")) {
        number = 1;
    } else {
        number = Integer.parseInt(strNum);
    }
    int maxPage;
    if (pageNum % 10 == 0) {
        maxPage = pageNum / 10;
    } else {
        maxPage = pageNum / 10 + 1;
    }
    if (number > maxPage) {
        number = maxPage;
    }
    int start = (number - 1) * pageSize;
    int end = number * pageSize;
    if (end > pageNum) {
        end = pageNum;
        System.out.println(pageNum);
    }
%>
<table border="1" width="100%">
    <tr align="center" valign="top">
        <td colspan="10">
            共<%=maxPage%>頁&nbsp;共<%=pageNum%>條記錄&nbsp;當前是第<%=number%>頁&nbsp;
            <a href="<%=rawQuery%>pageNum=0">首頁</a>&nbsp;
            <a href="<%=rawQuery%>pageNum=<%=number - 1%>">上一頁</a>&nbsp;
            <a href="<%=rawQuery%>pageNum=<%=number + 1%>">下一頁</a>&nbsp;
            <a href="<%=rawQuery%>pageNum=<%=maxPage%>">末頁</a>
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
    <%
        for (int j = start; j < end; j++) {
            Recipe recipe = (Recipe)RecipeList.get(j);
    %>
    <tr>
        <td><a href="ThanksDEL.jsp"><img src=<%=recipe.getImage()%> width="200" Height="200"><br><%=recipe.getRename()%></a> </td>
        <td><%=recipe.getBrief()%></td>
        <td><%=recipe.getPeople()%></td>
        <td><%=recipe.getTime()%></td>
    </tr>
    <%
        }
    %>
</table>
<%
System.out.println(rawQuery);
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (stat != null) {
                stat.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
%>
</body>
</html>