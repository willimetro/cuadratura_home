package cl.everis.cuadratura.bd.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author jarenass
 *
 */
public class ConnectionCuadraturaBD {
	
	/**
	 * 
	 * @return
	 */
	public static Connection getLocalConn() {
		String driver = "org.postgresql.Driver";
		String connectString = "jdbc:postgresql://localhost:5432/cuadratura_hogar";
		String user = "admin_hogar";
		String password = "Entel123";
		Connection con = get(driver, connectString, user, password);
		return con;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnOCTAR() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String connectString = "jdbc:oracle:thin:@192.168.186.114:1521:arsystem";
		String user = "ARLECTURA";
		String password = "ar123";
		Connection con = get(driver, connectString, user, password);
		return con;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnTPlay() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String connectString = "jdbc:oracle:thin:@//ofmprodb-scan.unix.entelpcs.entelcorp.com:1521/BNNPROD";
		String user = "CONS3PLAY";
		String password = "mgs762nbc";
		Connection con = get(driver, connectString, user, password);
		return con;
	}
	
	/**
	 * 
	 * @param driver
	 * @param connectString
	 * @param user
	 * @param password
	 * @return
	 */
	private static Connection get(String driver, String connectString, String user, String password) {
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
