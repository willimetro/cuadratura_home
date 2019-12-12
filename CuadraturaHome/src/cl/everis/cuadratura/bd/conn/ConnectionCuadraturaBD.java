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
	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = getConnServicios();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from servicio_tecnico where serv_gen='3 PLAY FIBRA' and estado='SER' and ROWNUM <= 1");
			while (rs.next()) {
				System.out.println(rs.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null!= rs){
					rs.close();
				}
				if (null!=stmt){
					stmt.close();
				}
				if (null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	**/	
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
		String connectString = "jdbc:oracle:thin:@//172.17.251.37:1521/BNNPROD";
		String user = "STL_DPINOCHET";
		String password = "Daniela_Dic.2019";
		Connection con = get(driver, connectString, user, password);
		return con;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnServicios() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String connectString = "jdbc:oracle:thin:@//192.168.186.114:1525/BSERVICP";
		String user = "bserviciosp";
		String password = "bserviciosp";
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
