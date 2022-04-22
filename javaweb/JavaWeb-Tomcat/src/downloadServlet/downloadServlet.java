package downloadServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * 2022/3/31
 * mcxen
 * JavaWeb-Tomcat
 *
 * @author McXen
 **/
@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        System.out.println("文件下载");
        resp.getWriter().write("Downloading ! ");

        req.setCharacterEncoding("UTF-8");
        String filename = req.getParameter("filename");
        if (filename == null || "".equals(filename.trim())) {//去除字符串前后空格
            resp.getWriter().write("请输入要下载的文件名");
            resp.getWriter().close();
            return;
        }
        //得到图片存放的路径
        String path = req.getServletContext().getRealPath("/downloaded/");
        File file = new File(path + filename);
        if (file.exists()&&file.isFile()){
            resp.setContentType("application/x-msdownload");//设置响应类型激活程序来处理
            resp.setHeader("Content-Disposition","attachment;filename="+filename);
            FileInputStream in = new FileInputStream(file);
            //字节输出流
            ServletOutputStream out = resp.getOutputStream();
            byte[] bytes = new byte[1024];
            //定义长度
            int len = 0;

        } else {
            resp.getWriter().write("FIle is real");
            resp.getWriter().close();
        }
    }
}
