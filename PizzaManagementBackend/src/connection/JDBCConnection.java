package connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCConnection {
	private static Connection conn = null;
	public static Connection getConnection() {
		try {
			if(conn == null) {
				//Class.forName("com.mysql.jdc.Driver");
				Properties props = new Properties();
				InputStream input;
				input = new FileInputStream("C:/Users/tyler/Desktop/PizzaApp/PizzaManagement/PizzaManagementBackend/connections.properties");
				props.load(input);
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				conn = DriverManager.getConnection(url,username,password);
				return conn;
			}
			else {
				return conn;
			}
		} catch(SQLException | IOException e) {
			e.printStackTrace();
		}
			return null;
	}
	
}
