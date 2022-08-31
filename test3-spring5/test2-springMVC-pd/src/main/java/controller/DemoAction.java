package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;
@RequestMapping("/user")
@Controller
public class DemoAction {
    @RequestMapping("/demo")
    public String demo(){
        System.out.println("服务器已经访问到了");
        return "main";//直接跳转到main.jsp的页面。
    }
}
