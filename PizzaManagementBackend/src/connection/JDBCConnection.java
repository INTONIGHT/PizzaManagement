package connection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCConnection {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdc.Driver");
			Properties props = new Properties();
			InputStream input = JDBCConnection.class.getClassLoader()
					.getResourceAsStream("connections.properties");
			props.load(input);
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			Connection conn = DriverManager.getConnection(url,username,password);
			return conn;
		} catch(SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
			return null;
	}

}
