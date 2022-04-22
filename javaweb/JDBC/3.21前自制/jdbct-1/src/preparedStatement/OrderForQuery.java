package preparedStatement;

import util.JDBC_Utils;

import java.lang.reflect.Field;
import java.sql.*;

/*
    针对 Order表格 查询
 */
public class OrderForQuery {
    public static void main(String[] args){
        OrderForQuery orderTest = new OrderForQuery();
        String sql = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
        Order order = orderForQuery(sql,1);
        System.out.println(order);
    }

    public static Order orderForQuery(String sql, Object... args){
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

            if (rs.next()) {
                Order order = new Order();
                //Each COlume  and column name
                for (int i = 0; i < columnCount; i++) {
                    Object columeVlaue=rs.getObject(i+1);
                    //获取列的别名，
                    String columnLabel =rsmd.getColumnLabel(i+1);
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order,columeVlaue);
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn,ps,rs);
        }

        return null;
    }


    public void testQuery(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBC_Utils.getConnection();
            String sql="select order_id,order_name,order_date from `order` where order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,1);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = (int) resultSet.getObject(1);
                String name = (String) resultSet.getObject(2);
                Date date = (Date) resultSet.getObject(3);
                Order order = new Order(id, name, date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBC_Utils.closeResources(conn,ps,resultSet);
        }

    }
}
