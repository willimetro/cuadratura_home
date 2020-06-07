package cl.everis.cuadratura.bd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextArea;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import cl.everis.cuadratura.bd.conn.ConnectionCuadraturaBD;
import cl.everis.cuadratura.files.FormatoArchivo;
import cl.everis.cuadratura.obj.CountOBJ;
import cl.everis.cuadratura.util.Constantes;

public class BDManagerImpl implements BDManager {

	private final static String PATH_ARCHIVOS = System.getProperty("user.home") + "\\Desktop\\cuadratura\\CSVs\\";
	private final static String PATH_CRUCE = System.getProperty("user.home") + "\\Desktop\\cuadratura\\Cruces\\";
	private final static String PATH_CROS = System.getProperty("user.home") + "\\Desktop\\cuadratura\\Cruces\\Cross\\";
	private final static File DIR_CARGA_ARCHIVOS = new File(
			System.getProperty("user.home") + "\\Desktop\\cuadratura\\CSVs");
	private final static File DIR_DESC_ARCHIVOS = new File(
			System.getProperty("user.home") + "\\Desktop\\cuadratura\\Cruces");
	private final static File DIR_DESC_CROS = new File(
			System.getProperty("user.home") + "\\Desktop\\cuadratura\\Cruces\\Cross");

	@Override
	public JTextArea descargarCSV(String opcion, JTextArea jTextAreaStatusProcess) {
		System.out.println("Inició proceso de descarga para producto: " + opcion);
		descargaArchivo(PATH_ARCHIVOS + MessageFormat.format(Constantes.getFile(opcion),
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))),
				Constantes.getQueryDescarga(opcion), Constantes.getCabecera(opcion), opcion);
		System.out.println("Descargó archivo "+ MessageFormat.format(Constantes.getFile(opcion),
						LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")))+ " en la ruta: " + PATH_ARCHIVOS);
		jTextAreaStatusProcess.setText(jTextAreaStatusProcess.getText() + "Descargó archivo "
				+ MessageFormat.format(Constantes.getFile(opcion), LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")))
				+ " en la ruta: " + PATH_ARCHIVOS+ "\n");
		return jTextAreaStatusProcess;
	}

	@Override
	public JTextArea actualiza(String producto, String archivo, JTextArea jTextAreaStatusProcess) {
		jTextAreaStatusProcess = borraDB(producto, jTextAreaStatusProcess);
		if (null != archivo)
			formatear(archivo, producto);
		return cargaCSV(producto, jTextAreaStatusProcess);
	}

	@Override
	public JTextArea cargaCSV(String producto, JTextArea jTextAreaStatusProcess) {
		Connection conn = null;
		BaseConnection pgcon = null;
		Reader in = null;

		String fileName = "";
		String sql = "";

		try {
			if (!DIR_CARGA_ARCHIVOS.exists()) {
				DIR_CARGA_ARCHIVOS.mkdirs();
			}
			conn = ConnectionCuadraturaBD.getLocalConn();
			pgcon = (BaseConnection) conn;
			CopyManager mgr = new CopyManager(pgcon);
			fileName = Constantes.getFile(producto);
			sql = Constantes.getQueryCarga(producto);
			in = new BufferedReader(new FileReader(new File(PATH_ARCHIVOS + MessageFormat.format(fileName,
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))))));
			long rowsaffected = mgr.copyIn(sql, in);
			System.out.println("Se ejecutó: " + sql + " con el archivo: "
					+ MessageFormat.format(fileName,LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))) + " y se copiaron " + rowsaffected + " registros");
			jTextAreaStatusProcess.setText(jTextAreaStatusProcess.getText() + "Se ejecutó: " + sql + " con el archivo: "
					+ MessageFormat.format(fileName,LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))) + " y se copiaron " + rowsaffected + " registros"+ "\n");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null!=in){
					in.close();
				}
				if (null!=pgcon){
					pgcon.close();
				}
				if (null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return jTextAreaStatusProcess;

	}

	@Override
	public JTextArea borraDB(String producto, JTextArea jTextAreaStatusProcess) {
		Connection conn = null;
		Statement statement = null;
		try {
			conn = ConnectionCuadraturaBD.getLocalConn();
			statement = conn.createStatement();
			statement.executeUpdate(Constantes.getQueryTruncate(producto));
			System.out.println("Se ejecutó: " + Constantes.getQueryTruncate(producto));
			jTextAreaStatusProcess.setText(jTextAreaStatusProcess.getText() + "Se ejecutó: "+ Constantes.getQueryTruncate(producto)+ "\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null!=statement){
					statement.close();
				}
				if (null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jTextAreaStatusProcess;
	}

	@Override
	public void descargaArchivo(String filename, String query, String cabecera, String producto) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		FileWriter fw = null;
		try {
			if (!DIR_CARGA_ARCHIVOS.exists()) {
				DIR_CARGA_ARCHIVOS.mkdirs();
			}
			if ("OTCAR".equals(producto)) {
				conn = ConnectionCuadraturaBD.getConnOCTAR();
			} else if("SERV_RETIRADOS".equals(producto)){
				conn = ConnectionCuadraturaBD.getConnServicios();
			}  else {
				conn = ConnectionCuadraturaBD.getConnTPlay();
			}
			fw = new FileWriter(filename);
			fw.append(cabecera);
			fw.append('\n');
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				fw.flush();
				procesar(fw, rs, producto);
			}
			fw.flush();
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
				if (null != fw){
					fw.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public CountOBJ obtenerCruces(String producto, JTextArea jTextAreaStatusProcess) {
		Connection conn = null;
		FileOutputStream fileOutputStream = null;
		CountOBJ contadores = new CountOBJ();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (!DIR_DESC_ARCHIVOS.exists()) {
				DIR_DESC_ARCHIVOS.mkdirs();
			}
			conn = ConnectionCuadraturaBD.getLocalConn();
			CopyManager copyManager = new CopyManager((BaseConnection) conn);
			for (int i = 0; i < 8; i++) {
				if (i<3) {
					File file = new File(PATH_CRUCE + MessageFormat.format(Constantes.getFileCruce(producto)[i],
							LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))));
					fileOutputStream = new FileOutputStream(file);
					copyManager.copyOut(
							"COPY (" + Constantes.getQueryCruce(producto)[i] + ") TO STDOUT WITH (FORMAT CSV, HEADER)",
							fileOutputStream);
					System.out.println("Se ejecutó: " + Constantes.getQueryCruce(producto)[i] + " y se entrega en archivo "
							+ MessageFormat.format(Constantes.getFileCruce(producto)[i],
									LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))));
					jTextAreaStatusProcess.setText(jTextAreaStatusProcess.getText() + "Se ejecutó: "
							+ Constantes.getQueryCruce(producto)[i] + " y se entrega en archivo "
							+ MessageFormat.format(Constantes.getFileCruce(producto)[i],
									LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")))+ "\n");
				} else {
					pstmt = conn.prepareStatement(Constantes.getQueryCruce(producto)[i]);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						if (i == 3)
							contadores.setTotalTplay(rs.getInt(1));
						else if (i == 4)
							contadores.setTotalRed(rs.getInt(1));
						else if (i == 5)
							contadores.setTotalAmbos(rs.getInt(1));
						else if (i == 6)
							contadores.setDifTplayRed(rs.getInt(1));
						else
							contadores.setDifRedTplay(rs.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null!=conn){
					conn.close();
				}
				if (null!=fileOutputStream){
					fileOutputStream.close();
				}
				if (null!=rs){
					rs.close();
				}
				if (null!=pstmt){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		contadores.setjTextAreaStatusProcess(jTextAreaStatusProcess);
		return contadores;
	}

	@Override
	public CountOBJ obtenerCrucesCros(String producto, JTextArea jTextAreaStatusProcess) {
		Connection conn = null;
		FileOutputStream fileOutputStream = null;
		CountOBJ contadores = new CountOBJ();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement statement = null;
		try {
			if (!DIR_DESC_CROS.exists()) {
				DIR_DESC_CROS.mkdirs();
			}
			conn = ConnectionCuadraturaBD.getLocalConn();
			CopyManager copyManager = new CopyManager((BaseConnection) conn);
			for (int i = 0; i < 20; i++) {
				if ((i<=5 && !"INTERNET".equals(producto)) || (i<5 && i!=2 && "INTERNET".equals(producto))) {
					statement = conn.createStatement();
					statement.executeUpdate(Constantes.getQueryCruce(producto)[i]);
					jTextAreaStatusProcess.setText(jTextAreaStatusProcess.getText()+"Se ejecutó: " + Constantes.getQueryCruce(producto)[i]+"\n");
				} else if ((i>5 && i<=12 && !"INTERNET".equals(producto)) || ((i>5 && i<=10 && i!=8 && "INTERNET".equals(producto)))) {
					File file = new File(PATH_CROS + MessageFormat.format(Constantes.getFileCruce(producto)[i-6],
							LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))));
					fileOutputStream = new FileOutputStream(file);
					copyManager.copyOut(
							"COPY (" + Constantes.getQueryCruce(producto)[i] + ") TO STDOUT WITH (FORMAT CSV, HEADER)",
							fileOutputStream);
					System.out.println("Se ejecutó: " + Constantes.getQueryCruce(producto)[i] + " y se entrega en archivo "
							+ MessageFormat.format(Constantes.getFileCruce(producto)[i-6],
									LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))));
					jTextAreaStatusProcess.setText(jTextAreaStatusProcess.getText() + "Se ejecutó: "
							+ Constantes.getQueryCruce(producto)[i] + " y se entrega en archivo "
							+ MessageFormat.format(Constantes.getFileCruce(producto)[i-6],
									LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")))
							 + "\n");
				} else if ((i>12 && !"INTERNET".equals(producto)) || ((i>12 && i<=17 && i!=15 && "INTERNET".equals(producto)))) {
					pstmt = conn.prepareStatement(Constantes.getQueryCruce(producto)[i]);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						if (i == 13) {
							contadores.setOkOkOk(rs.getInt(1));
						} else if (i == 14) {
							contadores.setOkNokOk(rs.getInt(1));
						} else if (i == 15) {
							contadores.setNokOkOk(rs.getInt(1));
						} else if (i == 16) {
							contadores.setOkOKNok(rs.getInt(1));
						} else if (i == 17) {
							contadores.setOkNokNok(rs.getInt(1));
						} else if (i == 18) {
							contadores.setNokOkNok(rs.getInt(1));
						} else if (i == 19) {
							contadores.setNokNokOk(rs.getInt(1));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null!=rs){
					rs.close();
				}
				if (null!=pstmt){
					pstmt.close();
				}
				if (null!=statement){
					statement.close();
				}
				if (null!=conn){
					conn.close();
				}
				if (null!=fileOutputStream){
					fileOutputStream.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		contadores.setjTextAreaStatusProcess(jTextAreaStatusProcess);
		return contadores;	
	}
	
	@Override
	public void formatear(String archivo, String producto) {

		FormatoArchivo formatoArchivo = new FormatoArchivo();

		if ("KENAN".equals(producto)) {
			formatoArchivo.formatFileKenan(archivo);
		} else if ("KENAN_C".equals(producto)) {
			formatoArchivo.formatFileCanalesKenan(archivo);
		} else if ("KALTURA".equals(producto)) {
			formatoArchivo.formatFileTvBaseKaltura(archivo);
		} else if ("KALTURA_C".equals(producto)) {
			formatoArchivo.formatFileCanalesKaltura(archivo);
		} else if ("AAA".equals(producto)) {
			formatoArchivo.formatFileInternetAAA(archivo);
		}  else if ("TODO_KALTURA".equals(producto)) {
			formatoArchivo.formatGenerico(archivo);
		}

	}

	private void procesar(FileWriter fw, ResultSet rs, String producto) throws IOException, SQLException {

		int columnas = rs.getMetaData().getColumnCount();

		if (("INTERNET".equals(producto) && null != rs.getNString("CODI_TECNICO") && !"".equals(rs.getNString("CODI_TECNICO").trim()))||"SERV_RETIRADOS".equals(producto)) {
			for (int i = 1; i < columnas; i++) {
				fw.append(rs.getString(i) + ";");
			}
			fw.append(rs.getString(columnas));
			fw.append('\n');
		} else if ("TLF".equals(producto)) {
			for (int i = 1; i <= columnas; i++) {
				fw.append(rs.getString(i) + ";");
			}
			fw.append(rs.getString(2) + "-" + rs.getString(1));
			fw.append('\n');
		} else if ("TV".equals(producto)) {
			fw.append(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(5) + ";");
			for (int i = 1; i < columnas; i++) {
				fw.append(rs.getString(i) + ";");
			}
			fw.append(rs.getString(columnas));
			fw.append('\n');
		} else if ("OTCAR".equals(producto)) {
			fw.append(rs.getString(2) + "-" + rs.getString(4) + ";");
			for (int i = 1; i < columnas; i++) {
				fw.append(rs.getString(i) + ";");
			}
			fw.append(rs.getString(columnas));
			fw.append('\n');
		}
	}
}
