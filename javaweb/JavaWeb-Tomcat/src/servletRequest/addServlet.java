package servletRequest;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2022/3/25
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/

public class addServlet extends HttpServlet {
//    就离谱
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        Integer fprice = Integer.valueOf(req.getParameter("fprice"));
        String fcount = req.getParameter("fcount");
        String fmark = req.getParameter("fmark");
        System.out.println("fmark = " + fmark);

    }

}
