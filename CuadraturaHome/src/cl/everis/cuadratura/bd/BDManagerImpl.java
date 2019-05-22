package cl.everis.cuadratura.bd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import cl.everis.cuadratura.bd.conn.ConnectionCuadraturaBD;
import cl.everis.cuadratura.util.Constantes;

public class BDManagerImpl implements BDManager {

	static Constantes constantes= new Constantes();

	@Override
	public void cargaCSV() {
		Connection conn = null;

		String fileName = "internet_tplay.csv";
		String sql = "copy internet_3play FROM stdin DELIMITER ';' CSV header";

		try {
			conn = ConnectionCuadraturaBD.getLocalConn();
			BaseConnection pgcon = (BaseConnection)conn;
			CopyManager mgr = new CopyManager(pgcon);
			Reader in = new BufferedReader(new FileReader(new File(fileName)));
			long rowsaffected  = mgr.copyIn(sql, in);
			System.out.println("Rows copied: " + rowsaffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void borraDB() {
		Connection conn = null;
		try {
			conn = ConnectionCuadraturaBD.getLocalConn();
			Statement statement = conn.createStatement();
			statement.executeUpdate("TRUNCATE internet_3play");
			System.out.println("TRUNCATE internet_3play");
			statement.executeUpdate("TRUNCATE tvcanales_3play");
			System.out.println("TRUNCATE tvcanales_3play");
			statement.executeUpdate("TRUNCATE tlf_3play");
			System.out.println("TRUNCATE tlf_3play");
			statement.executeUpdate("TRUNCATE tlf_otcar");
			System.out.println("TRUNCATE tlf_otcar");
//			statement.executeUpdate("TRUNCATE canales_kaltura");
//			System.out.println("TRUNCATE canales_kaltura");
//			statement.executeUpdate("TRUNCATE facturador_kenan");
//			System.out.println("TRUNCATE facturador_kenan");
//			statement.executeUpdate("TRUNCATE facturador_kenan_canal");
//			System.out.println("TRUNCATE facturador_kenan_canal");
//			statement.executeUpdate("TRUNCATE internet_aaa");
//			System.out.println("TRUNCATE internet_aaa");
//			statement.executeUpdate("TRUNCATE tv_kaltura");
//			System.out.println("TRUNCATE tv_kaltura");
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	


	}

	@Override
	public void desacargarCSV() {

		// INTERNET 3PLAY
		descargaArchivo(constantes.getFileINT(),
				constantes.getQueryINT(),
				constantes.getCabeceraINT());
		System.out.println("Descargó " + constantes.getFileINT());
		// TV 3PLAY
		descargaArchivo(constantes.getFileTV(),
				constantes.getQueryTV(),
				constantes.getCabeceraTV());
		System.out.println("Descargó " + constantes.getFileTV());
		// TLF 3PLAY
		descargaArchivo(constantes.getFileTLF(),
				constantes.getQueryTLF(),
				constantes.getCabeceraTLF());
		System.out.println("Descargó " + constantes.getFileTLF());
		// TLF OTCAR
		descargaArchivo(constantes.getFileOCT(),
				constantes.getQueryOCT(),
				constantes.getCabeceraOCT());
		System.out.println("Descargó " + constantes.getFileOCT());
	}

	@Override
	public void descargaArchivo(String filename, String query, String cabecera) {

		Connection conn = null;

		try {
			if (filename.indexOf("tv_tplay") >= 0 || filename.indexOf("tlf_tplay") >= 0 || filename.indexOf("internet_tplay") >= 0){
				conn = ConnectionCuadraturaBD.getConnTPlay();
			} else if (filename.indexOf("otcar") >= 0){
				conn = ConnectionCuadraturaBD.getConnOCTAR();
			}
			FileWriter fw = new FileWriter(filename);
			fw.append(cabecera);
			fw.append('\n');
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (filename.indexOf("internet_tplay") >= 0){
				while (rs.next()) {
					procesaINT(fw, rs);
				} 
			}else if (filename.indexOf("tlf_tplay") >= 0){
				while (rs.next()) {
					procesaTLF(fw, rs);	
				}
			} else if (filename.indexOf("tv_tplay") >= 0){
				while (rs.next()) {
					procesaTV(fw, rs);
				}
			} else if (filename.indexOf("otcar") >= 0){
				while (rs.next()) {
					procesaOctar(fw, rs);
				}
			}
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	private static void procesaINT(FileWriter fw, ResultSet rs)  throws IOException, SQLException {
		fw.append(rs.getString(1)+";");
		fw.append(rs.getString(2)+";");
		fw.append(rs.getString(3)+";");
		fw.append(rs.getString(4)+";");
		fw.append(rs.getString(5));
		fw.append('\n');
	}

	private static void procesaTLF(FileWriter fw, ResultSet rs) throws IOException, SQLException {
		fw.append(rs.getString(1)+";");
		fw.append(rs.getString(2)+";");
		fw.append(rs.getString(3)+";");
		fw.append(rs.getString(4)+";");
		fw.append(rs.getString(5)+";");
		fw.append(rs.getString(2)+"-"+rs.getString(1));
		fw.append('\n');
	}

	private static void procesaTV(FileWriter fw, ResultSet rs) throws IOException, SQLException {
		fw.append(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(5)+";");
		fw.append(rs.getString(1)+";");
		fw.append(rs.getString(2)+";");
		fw.append(rs.getString(3)+";");
		fw.append(rs.getString(4)+";");
		fw.append(rs.getString(5)+";");
		fw.append(rs.getString(6)+";");
		fw.append(rs.getString(7));
		fw.append('\n');	
	}

	private static void procesaOctar(FileWriter fw, ResultSet rs) throws IOException, SQLException {
		fw.append(rs.getString(2)+"-"+rs.getString(4)+";");
		fw.append(rs.getString(1)+";");
		fw.append(rs.getString(2)+";");
		fw.append(rs.getString(3)+";");
		fw.append(rs.getString(4));
		fw.append('\n');	
	}

}
