package servletCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2022/3/29
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
@WebServlet("/serck")
public class servletCookie extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("name", "admin");
        resp.addCookie(cookie);

//
    }
}
