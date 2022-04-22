package preparedStatement;

import bean.Customer;
import util.JDBC_Utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class PreparedStatementCrudQuery {
    public static void main(String[] args) {
        PreparedStatementCrudQuery psc = new PreparedStatementCrudQuery();
        String sql="select id,name,email from customers where id < ?";
        List<Customer> list = psc.getForList(Customer.class, sql, 12);
        list.forEach(System.out::println);

        String sql1 ="select order_id orderId, order_name orderName from `order` ";
        List<Order> orderList = psc.getForList(Order.class, sql1);
        orderList.forEach(System.out::println);
//        测试单个表的查询
        ////      测试ORDER
////
//        String sql="select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
//        Order instance = psc.getInstance(Order.class, sql, 1);
//        System.out.println(instance);
//
//        //测试Customer。
//        String sql1 = "select id,name,email,birth from customers where id = ?";
//        Customer instance1 = psc.getInstance(Customer.class, sql1, 1);
//        System.out.println(instance1);
    }


    public <T> List<T> getForList(Class<T> clazz,String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBC_Utils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
//            获取列的数量
            int columnCount = rsmd.getColumnCount();
//            创造集合对象
            ArrayList<T> list = new ArrayList<T>();
            while (rs.next()) {
                T t= clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
//                    获取列Label
                    String columnLabel = rsmd.getColumnLabel(i+1);
                    Object columnValue = rs.getObject(i + 1);
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue );
                }
                list.add(t);
//                抛出异常可能是查找不到例子
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn, ps, rs);
        }
        return null;
    }
    public <T> T getInstance(Class<T> clazz,String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBC_Utils.getConnection();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn,ps,rs);
        }

        return null;
    }
}
