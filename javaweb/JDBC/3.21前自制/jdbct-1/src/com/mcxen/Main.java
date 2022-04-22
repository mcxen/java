package com.mcxen;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    //Way one
    public void testConnection1() throws SQLException{
        Driver driver =new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        // 用户名和密码放在property
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","2486");
        Connection conn = driver.connect(url,info);
        System.out.println(conn);
    }
    //Way two
    public void testConnection2() throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        //TODO 获取实现类对象使用反射
        Class clazz= Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","2486");
        Connection conn=driver.connect(url,info);

        System.out.println(conn);
    }
    //Way three
    public void testConnection() throws ClassNotFoundException, NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取实现类对象，获取driver;
        Class clazz= Class.forName("com.mysql.cj.jdbc.Driver");//默认加载了静态代码块，mysql可以省略
        //Driver driver = (Driver) clazz.getDeclaredConstructor().newInstance();

        // 注册驱动
        //DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        Connection conn = DriverManager.getConnection(url,"root","2486");
        System.out.println(conn);
    }
    //加载配置文件方式
    public void testConnection5() throws SQLException, ClassNotFoundException, IOException {
        // read the propetries类加载器
        InputStream is = Main.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros =new Properties();
        pros.load(is);
        String user=pros.getProperty("user");
        String password=pros.getProperty("password");
        String url=pros.getProperty("url");
        String driver =pros.getProperty("driver");

        //加载驱动
        Class.forName(driver);

        //获取链接
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {
	// write your code here
        Main c=new Main();
        c.testConnection5();
        System.out.println("china is here");
        Scanner scanner = new Scanner(System.in);

    }
}
