package Model;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/UVCEPlacementWindow" ;
	private static final String USERNAME =  "root";
	private static final String PASSWORD = "";

	public static Connection getLocalConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");	
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
