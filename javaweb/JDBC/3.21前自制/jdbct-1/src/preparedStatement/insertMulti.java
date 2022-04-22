package preparedStatement;

import util.JDBC_Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class insertMulti {
    public static void main(String[] args) throws Exception {
        insertMulti in = new insertMulti();
        in.insert2();
    }

    public void insert() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBC_Utils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 210000; i++) {
                ps.setObject(1, "name_" + i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费时间为" + (end-start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn, ps);

        }

    }
    public void insert1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBC_Utils.getConnection();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 210000; i++) {
                ps.setObject(1, "name_" + i);
                ps.addBatch();
                if (i%500==0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
//                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费时间为" + (end-start)/1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn, ps);

        }

    }

    public void insert2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBC_Utils.getConnection();
            //不允许自动提交
            conn.setAutoCommit(false);

            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 210000; i++) {
                ps.setObject(1, "name_" + i);
                ps.addBatch();//积攒Batch
                if (i%500==0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
//                ps.execute();
            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费时间为" + (end-start)/1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn, ps);

        }

    }
}
