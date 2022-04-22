package servletRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 2022/3/27
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
@WebServlet("/serReq")
public class servletRequest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*        //        Methods
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

    */

        /**
         * 获取请求的参数
         * *****
         * 最重要的方法
         */
//        获取指定名称的参数值
        req.setCharacterEncoding("UTF-8");
        String uname = req.getParameter("uname");
        System.out.println("uname = " + uname);
        String pwd = req.getParameter("pwd");
        System.out.println("pwd = " + pwd);
//        获取指定名称的所有的参数值
        String[] hobbys =req.getParameterValues("hobby");
//        for (String hobby : hobbys) {
//            System.out.println("爱好是 "+hobby);
//        }


        /**
         * 域对象学习
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

    }
}
