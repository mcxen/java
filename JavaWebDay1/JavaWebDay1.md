IO流操作一般分为两类：字符流和字节流。以“Reader”结尾都是字符流，操作的都是字符型的数据；以“Stream”结尾的都是字节流，操作的都是byte数据。

# IDEA j 学习

Java SE(Java Platform,Standard Edition)，应该先说这个，因为这个是标准版本。Java EE (Java Platform，Enterprise Edition)，java 的企业版本Java ME(Java Platform，Micro Edition)，java的微型版本。

新版本新特性 2018.1

![截图](69ca398d4e0a31627bb887a38bf94129.png)

![截图](7ce60451ce1f49fcbbf5a963384fbbb3.png)

这里是添加FraneWork加入JAVAEE web application

<br/>

<br/>

commit 是本地的提交

push是远程的提交

![截图](f00ca5f1f7110bbe5cbdb87d4d0102b9.png)

pull是更新到本地

clone 是拷贝远程仓库

![截图](446865368c6667491db82e5e715299e0.png)

上面是修改JDK的版本

<br/>

![截图](27a1624e47e9337f826bfe6959bf68d3.png)

## Tomcat

![截图](268aaa33bce53d6875566b336e567b91.png)

Tomcat的容器要加入WEB-INF

![截图](308aaf5daa45c0a71f66dadf432f5109.png)

<br/>

![截图](e528473d1b60c82aa3acd6e60ac00be6.png)

<br/>

![截图](91255b99b5e1b7dd01bf80fa0d912b11.png)

<br/>

<br/>

servlet

功能：获取用户返回的数据

<br/>

<br/>

![截图](5079e106ed30ef5346a6c42e551ca63b.png)

哈哈哈哈哈

我晓得了

上面应该是

![截图](9ef20d89fd31acac9b76f91386c491bf.png)

Servlet的注解作为访问的路径

<br/>

@WebSevlet("/ser01")

<br/>

<br/>

## HttpServlet

### HttpServletRequest

对象由Tomcat自动创建

我们掌握常用方法就可以了

```java
        //        Methods
//        获取请求的完整路径 从Http到？前面结束
        String url = String.valueOf(req.getRequestURL());
        System.out.println("url = " + url);
//        获取请求的部分路径从项目的站点名到？前面结束
        String uri = req.getRequestURI();
        System.out.println("uri = " + uri);
//        获取请求的参数字符串 从？到最后的字符串
        String queryString = req.getQueryString();
        System.out.println("获取请求的参数字符串 queryString = " + queryString);
//        获取请求方式
        String method = req.getMethod();
        System.out.println("获取请求方式 method = " + method);
//        获取当前协议版本
        String protocol = req.getProtocol();
        System.out.println("获取当前协议版本 protocol = " + protocol);
//        获取项目站点名
        String contextPath = req.getContextPath();
//        上下文路径
        System.out.println("获取项目站点名  contextPath = " + contextPath);
```

<%--    post请求会乱码--%>

```java
/**
         * 获取请求的参数
         * *****
         * 最重要的方法
         */
//        获取指定名称的参数值
        String uname = req.getParameter("uname");
        System.out.println("uname = " + uname);
        String pwd = req.getParameter("pwd");
        System.out.println("pwd = " + pwd);
//        获取指定名称的所有的参数值
        String[] hobbys =req.getParameterValues("hobby");
        for (String hobby : hobbys) {
            System.out.println("爱好是 "+hobby);
        }
```

请求转发 

就是getRequestDispatcher("url servlet名称").forward(req,resp);转发一个参数

```java
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        System.out.println("这里是Dispatch");
//        req.getRequestDispatcher("serReq01").forward(req,resp);
//        req.getRequestDispatcher("login.jsp").forward(req,resp);
        req.getRequestDispatcher("hell.jsp").forward(req,resp);
    }
```

Dispacher

<br/>

域对象:

```java
         */
//        设置域对象内容
        req.setAttribute("name","admin");
        req.setAttribute("age",18);
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        req.setAttribute("list",list);//添加List
//        请求转发跳转到servletAttribute
        req.getRequestDispatcher("hell.jsp").forward(req,resp);
```

域对象: 就是一个代码块向另一个传送数据

```

```

![截图](58d65c8f51b5cd1247630ac0d7f1a2c2.png)

<br/>

## Response 

两个方式

- getwrite
- 
