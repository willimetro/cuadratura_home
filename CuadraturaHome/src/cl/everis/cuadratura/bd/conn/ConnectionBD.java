package cl.everis.cuadratura.bd.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

	public static Connection getConn() {
		String driver = "org.postgresql.Driver";
		String connectString = "jdbc:postgresql://localhost:5432/dataInc";
		String user = "userInc";
		String password = "Everis2020";
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(connectString, user , password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
