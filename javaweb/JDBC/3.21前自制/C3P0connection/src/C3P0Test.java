package conncetion;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/*
 * import com.mchange.v2.c3p0.*;

...

ComboPooledDataSource cpds = new ComboPooledDataSource();
cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver
cpds.setJdbcUrl( "jdbc:postgresql://localhost/testdb" );
cpds.setUser("dbuser");
cpds.setPassword("dbpassword");

 */
public class C3P0Test {
	@Test
	//方式一
	public void testGetConnection() throws PropertyVetoException, SQLException {
//		获取C3P0连接池，
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( "com.mysql.jdbc.Driver" );
		cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
		cpds.setUser("root");
		cpds.setPassword("2486");
//		设置相关参数
		cpds.setInitialPoolSize(10);//设置初始数据库连接数；
		java.sql.Connection conn =cpds.getConnection();
		System.out.println(conn);

	}
	//	方式二
	@Test
	public void testGetConnection1() throws SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
		Connection conn = (Connection) cpds.getConnection();
		System.out.println(conn);
	}

}
