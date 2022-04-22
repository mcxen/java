<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HellJSP</title>
</head>
<body>
<h2>Index页面</h2>
<%--获取JAVA代码,在JSP中写JAVA代码--%>
<%
    System.out.println("*******==================********");
    System.out.println("serletAttribute.service is running ");
    String name = String.valueOf(req.getAttribute("name"));
    System.out.println("name = " + name);
    Integer age = (Integer) req.getAttribute("age");
    System.out.println("age = " + age);
    List<String> list= (List<String>) req.getAttribute("list");
%>
</body>
</html>
