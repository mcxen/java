package uploadServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 2022/3/31
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
@MultipartConfig//文件上传要设置这个注解MultipartConfig
@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("文件上床中....");
        req.setCharacterEncoding("UTF-8");
        String uname = req.getParameter("uname");
        resp.getWriter().write("Uname是： "+uname);

        Part ufile = req.getPart("ufile");//file文件域的name属性值
//        通过Part对象获得文件名
        String fileName = ufile.getSubmittedFileName();
        System.out.println("fileName = " + fileName);
//      得到文件存放的路径
        String realPath = req.getServletContext().getRealPath("/");
        System.out.println("realPath = " + realPath);
        ufile.write(realPath+"/"+fileName);//上传文件到指定目录
    }
}
