package servletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

/**
 * 2022/3/27
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
@WebServlet("/seratt")
public class serletAttribute extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("*******==================********");
        System.out.println("serletAttribute.service is running ");
        String name = String.valueOf(req.getAttribute("name"));
        System.out.println("name = " + name);
        Integer age = (Integer) req.getAttribute("age");
        System.out.println("age = " + age);
        List<String> list= (List<String>) req.getAttribute("list");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

    }
}
