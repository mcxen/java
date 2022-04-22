package servletSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 2022/3/30
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
@WebServlet("/serSes")
public class servletSession extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        设置Session的域对象
        session.setAttribute("uname","McX");
        session.setAttribute("pwd","2486");

//        Request的域对象
//        吵过一次的i请求 request的域对象就会自动销毁了。
         req.setAttribute("name","mmmm");
         req.setAttribute("pws","reqpwd");
//         请求转发
//         req.getRequestDispatcher("index.jsp").forward(req,resp);
//        重定向跳转
        resp.sendRedirect("index.jsp");
    }
}
