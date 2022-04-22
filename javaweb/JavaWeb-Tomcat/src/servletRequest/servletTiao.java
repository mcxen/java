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
 * 请求转发 就是getRequestDispatcher("url servlet名称").forward(req,resp);转发一个参数
 * @author McXen
 **/
@WebServlet("/servDispatch")
public class servletTiao extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("uname");
        System.out.println("这里是Dispatch");
//        req.getRequestDispatcher("serReq01").forward(req,resp);
//        req.getRequestDispatcher("login.jsp").forward(req,resp);
        req.getRequestDispatcher("hell.jsp").forward(req,resp);
    }
}
