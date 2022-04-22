package preparedStatement;

import bean.Customer;
import util.JDBC_Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author: 白矖
 * @date: 2022/1/9 14:07
 * @description:
 */
public class CustomerForQuery {
    /**
     *  针对表的通用查询操作
     * @throws Exception
     */
    public Customer queryForCustomers(String sql,Object...args) throws Exception {
        Connection conn = JDBC_Utils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i+1,args[i]);
        }
        ResultSet resultSet = ps.executeQuery();
        //获取结果集的元数据。，其实就是变量名，元注解，
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        if(resultSet.next()) {
            //查询到了再创造对象。
            Customer cust = new Customer();
            for (int i = 0; i < columnCount; i++) {
                Object columnValue = resultSet.getObject(i + 1);
                //反射，给cust的某个属性赋值为对应的值。
                String columnName = rsmd.getColumnName(i + 1);
                //反射，获取对象的名字，通过field.set()来进行操作
                Field field = Customer.class.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(cust, columnValue);
            }
            return cust;
        }
        JDBC_Utils.closeResources(conn,ps,resultSet);
        return null;
    }

    /**
     * 测试单独的查询操作。
     * @throws Exception
     */
    public void testQuery() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBC_Utils.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);

                //            System.out.println(id+name+email+birth);
                //            Object[] data=new Object[]{id,name,email,birth};
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            JDBC_Utils.closeResources(conn, ps, resultSet);
        }
    }

    public static void main(String[] args) throws Exception {
        CustomerForQuery cfq = new CustomerForQuery();
        cfq.testQuery();
    }
}
