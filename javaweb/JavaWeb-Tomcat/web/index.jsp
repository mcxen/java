<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index域对象</title>
</head>
<body>
重定向到这个区域<br>
<%
    String uname = (String) session.getAttribute("uname");
    String pwd = (String) session.getAttribute("pwd");
    String name = (String) request.getAttribute("name");//来自request与对象的
    out.println("Session  uname:  "+uname);
    out.println("\n Session name:  "+pwd);
    out.println("\n request name:  "+name);

%>
<h1 align="center">域对象</h1>
<form method="post" action="uploadServlet" enctype="multipart/form-data">
    姓名： <input type="text" name="uname"><br>
    文件： <input type="file" name="ufile"><br>
    <button type="submit" value="Submit">提交</button>
</form>

</body>
</html>
