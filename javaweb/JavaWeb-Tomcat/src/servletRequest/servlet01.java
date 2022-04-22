package servletRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2022/3/27
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
//@WebServlet("/ser01")
//@WebServlet(name = "servlet01",value = "/ser01")
//@WebServlet(name = "servlet01",value = {"/ser01","/ser001"})

@WebServlet("/ser01")
public class servlet01 extends HttpServlet {
//   解释生命周期

    /**x`
     * 有请求到达servlet时，就会调用该方法
     * 方法可以被多次调用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet 开始使用了");
        resp.getWriter().write("<h1>How are U </h1>");
    }

    /*
        初始化方法，服务器自动调用，请求到达servlet容器，容器会判断servlet对象是否存在，不存在则创造实力并初始化

         */
    @Override
    public void init() throws ServletException {
        System.out.println("init创建了");
    }

    @Override
    public void destroy() {
//        服务器关闭或者应用程序停止，会调用该方法
    }
}
