package transaction;

import util.JDBC_Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class connectionTest {
    public static void main(String[] args) throws Exception {
        connectionTest test = new connectionTest();
        test.test();
    }
    public void test() throws Exception {
        Connection conn = JDBC_Utils.getConnection();
        System.out.println(conn);
    }
}
