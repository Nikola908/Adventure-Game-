package Igrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private Connection connection;
	private final String url ="jdbc:sqlserver://0.0.0.0:1433;DatabaseName=master;encrypt=true;trustServerCertificate=true";
	private final String username = "sa";
	private final String password = "A0d92fa77!";
	
	public DatabaseConnection()
	{
		
	}
	public Connection createConnection()
	{
		try {
			 connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public void closeConnection()
	{
		try {
			this.connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
