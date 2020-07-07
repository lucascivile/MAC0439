package bd.documentos;

import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    public static final String DRIVER_NAME = "org.postgresql.Driver";
	public static final String URL_BD = "jdbc:postgresql://localhost:5432/ep3";
	public static final String USER_BD = "ep3";
	public static final String PASSWORD_BD = "";

	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
	}

	public Connection getConnection() {

		Properties props = new Properties();
		props.setProperty("ssl", "false");
		props.setProperty("user", USER_BD);
		props.setProperty("password", PASSWORD_BD);

		try {
			return DriverManager.getConnection(URL_BD, props);
		} catch (SQLException e) {
			throw new RuntimeException(e);
        }
	}
	
	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
