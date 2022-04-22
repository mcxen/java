<%--
  Created by IntelliJ IDEA.
  User: mcxen
  Date: 2022/3/27
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login<登录></title>
</head>
<body>
    <h1 align="center">登录页面</h1>
<div align="center" >
<%--    post请求会乱码--%>
    <form method="post" action="/serReq01">
        姓名<input type="text" name="uname"><br>
        密码<input type="password" name="upwd"><br>
        <button type="submit" value="submit">登录</button>
    </form>
</div>
</body>
</html>
