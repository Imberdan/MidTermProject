package tw.oliver.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnUtil {
	
	private Connection conn;
	
	public Connection createConn() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=OliverLiu;user=watcher;password=P@ssw0rd;encrypt=true;trustServerCertificate=true";
		conn = DriverManager.getConnection(urlstr);
		System.out.println("Connection Status:" + !conn.isClosed());
		return conn;
	}
	
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		System.out.println("Connection Closed");
	}

}
