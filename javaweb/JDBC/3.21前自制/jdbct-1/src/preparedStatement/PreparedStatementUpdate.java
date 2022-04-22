package preparedStatement;
/*
    这里是JDBC_Utils的运用，增删改操作，
     */

import com.mcxen.Main;
import util.JDBC_Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static util.JDBC_Utils.closeResources;
import static util.JDBC_Utils.getConnection;

//Prepared Statement增删改
public class PreparedStatementUpdate {

    /**
     * 测试通用操作，测试更新操作如下
     * @throws Exception
     */
    public void testDelete() throws Exception {
//        String sql="delete from customers where id = ?";
        String sql = "update test.order set order_name = ? where order_id =?";
        update(sql,"cxw","2");
    }
    /**
     * Universial Update and delete  zengshangai
     * @throws Exception
     */
    public void update(String sql,Object ...args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //link
            conn = getConnection();
            //prepare
            ps = conn.prepareStatement(sql);
            //???
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //exe
            ps.execute();
            System.out.println("通用增删改操作完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeResources(conn,ps);
        }
    }


    public void testInsert() throws Exception {
        // read the propetries类加载器
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            //预编译
            String sql="insert into customers(name,email,birth)values(?,?,?)";//? 占位符
            ps = conn.prepareStatement(sql);
            //填充占位符
            ps.setString(1,"王力宏");
            ps.setString(2,"lh@1.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1928-12-12");
            System.out.println(date);
            ps.setDate(3,new Date(date.getTime()));
            //执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭
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
    }
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1 获取链接
            conn = JDBC_Utils.getConnection();
            // 预编译sql，返回实例
            String sql = "update customers set name = ? where id = ?";
            ps = conn.prepareStatement(sql);
            //占位符。。
            ps.setString(1,"Marak");
            ps.setObject(2,18);
            //zhixing
            ps.execute();
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn,ps);
        }
        //close
    }

    public static void main(String[] args) throws Exception {
        PreparedStatementUpdate ts = new PreparedStatementUpdate();
        String ssql ="update customers set name = ? where id = ? ";
        ts.update(ssql,"北京",13);
    }
}
