package transaction;

import java.lang.reflect.Field;
import java.sql.*;

import static util.JDBC_Utils.closeResources;
import static util.JDBC_Utils.getConnection;

public class transactionTest {

    /*
    描述：
    UserTable
    A——》B转账100
    update user_table balance = balance -100 where user ='AA';
    update user_table balance = balance +100 where user ='BB';

     */
    public static void main(String[] args) throws Exception {
        transactionTest test = new transactionTest();
    }
//===============考虑事务=====================
    //
    public void transactionSelect() throws Exception {
        Connection conn = getConnection();
        System.out.println(conn.getTransactionIsolation());//获取隔离级别，读未提交
        conn.setAutoCommit(false);//取消自动提交
        String sql = "select user,password,balance from user_table where user = ?";
        User user = getInstance(conn, User.class, sql, "CC");
        System.out.println(user);

    }
    public void transactionUpadate() throws Exception {
        Connection conn = getConnection();
        System.out.println(conn.getTransactionIsolation());//获取隔离级别
        String sql = "update user_table set balance = ? where user = ?";
        update(conn,sql,2000,"CC");
        Thread.sleep(15000);
        System.out.println("修改结束");

    }

    //通用的查询操作，返回表中的一条记录
    public <T> T getInstance(Connection conn,Class<T> clazz,String sql,Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = util.JDBC_Utils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i=0;i<args.length;i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                T t = clazz.newInstance();
                //Each COlume  and column name
                for (int i = 0; i < columnCount; i++) {
                    Object columValue=rs.getObject(i+1);
                    //获取列的别名，
                    String columnLabel =rsmd.getColumnLabel(i+1);
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columValue);
                }//经过循环之后就把t赋值了。
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.JDBC_Utils.closeResources(null,ps,rs);
        }

        return null;
    }
    //DDL操作一旦执行，就会自动提交；
    //DML默认也会 自动提交
        //set autocommit = false 取消DML自动提交
    //关闭连接时，会自动提交
    //============考虑事务=============================
    public void updateTest1() throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
//            取消自动提交功能
            conn.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance -100 where user = ?";
            update(conn, sql1, "AA");
            String sql2 = "update user_table set balance = balance +100 where user =? ";
            update(conn, sql2, "BB");
            System.out.println("转账完成");
            //提交
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();//回滚数据
                System.out.println("回滚成功");
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("回滚数据失败");
            }
        } finally {
            util.JDBC_Utils.closeResources(conn, null);
        }
    }
    //通用增删改操作改进版v2.0 2022年3月17日
    public int update(Connection conn,String sql,Object ...args) throws Exception {
        PreparedStatement ps = null;
        try {
            //conn传进来
            //1.prepare
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //3.编译执行 exe
            int updateResult = ps.executeUpdate();
            System.out.println("通用增删改操作完毕！");
            return updateResult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            关闭资源
            closeResources(null, ps);
        }
        return 0;
    }

    //============没有考虑事务=============================
    public void updateTest() throws Exception {
        String sql1="update user_table set balance = balance -100 where user = ?";
        update(sql1,"AA");
        String sql2="update user_table set balance = balance +100 where user =? ";
        update(sql2,"BB");
        System.out.println("转账完成");
    }
    //通用增删改操作 2022年3月17日
    public int update(String sql,Object ...args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //link
            conn = getConnection();
            //prepare
            ps = conn.prepareStatement(sql);
            //???
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //exe
            int updateResult = ps.executeUpdate();
            System.out.println("通用增删改操作完毕！");
            return updateResult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps);
        }
        return 0;
    }
}
