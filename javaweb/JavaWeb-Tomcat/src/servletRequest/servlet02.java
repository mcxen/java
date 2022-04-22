package servletRequest;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * 2022/3/27
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
@WebServlet("/ser02")
public class servlet02 extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("第二种方式 继承genericServlet");
    }
}
