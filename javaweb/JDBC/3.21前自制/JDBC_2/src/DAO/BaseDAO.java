package DAO;

import java.lang.reflect.Field;
import java.sql.*;

/*
 Date(base) Access Object
 D          A       O
 short for DAO

 */
public class BaseDAO {
    //    提供通用的增删改查询操作
    public int update(Connection conn,String sql,Object...args) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.JDBC_Utils.closeResources(null, ps);
        }
        return 0;
    }

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



}
