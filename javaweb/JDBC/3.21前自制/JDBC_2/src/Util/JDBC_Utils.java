package util;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: ${USER}
 * @date: ${DATE} ${TIME}
 * @description: 操作数据库的工具类
 */
public class JDBC_Utils {
    /**
     * @return Conncetion
     * @throws Exception
     * @Description 获取链接
     */
    public static Connection getConnection() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driver = pros.getProperty("driver");
        //加载驱动
        Class.forName(driver);
        //获取链接
        Connection conn = DriverManager.getConnection(url, user, password);
        //预编译
        System.out.println(conn);
        return conn;
    }

    /**
     * 关闭资源 conn 和 ps
     *
     * @param conn
     * @param ps
     */

    public static void closeResources(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
