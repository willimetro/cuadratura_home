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

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import cl.everis.cuadratura.bd.conn.ConnectionCuadraturaBD;
import cl.everis.cuadratura.files.FormatoArchivo;
import cl.everis.cuadratura.obj.CountOBJ;
import cl.everis.cuadratura.util.Constantes;

public class BDManagerImpl implements BDManager {

	private final static String PATH_ARCHIVOS = "C:\\Users\\Administrador\\Desktop\\Documentos\\EntelProyectoFijo\\cuadratura\\CSVs\\";
	private final static String PATH_CRUCE = "C:\\Users\\Administrador\\Desktop\\Documentos\\EntelProyectoFijo\\cuadratura\\Cruces\\";

	@Override
	public void descargarCSV(String opcion) {

		// descargar solo aquellos archivos que podemos obtener directamente en BDs
		System.out.println("Inició proceso de descarga para producto: " + opcion);
		descargaArchivo(PATH_ARCHIVOS+Constantes.getFile(opcion),
				Constantes.getQueryDescarga(opcion),
				Constantes.getCabecera(opcion),
				opcion);
		System.out.println("Descargó archivo " + Constantes.getFile(opcion) +" en la ruta: " + PATH_ARCHIVOS );	

	}

	@Override
	public void actualiza(String producto, String archivo) {
		borraDB(producto);
		if(null != archivo)
			formatear(archivo, producto);
		cargaCSV(producto);
	}

	@Override
	public void cargaCSV(String producto) {
		Connection conn = null;
		BaseConnection pgcon = null;
		Reader in = null;

		String fileName = "";
		String sql = "";

		try {

			conn = ConnectionCuadraturaBD.getLocalConn();
			pgcon = (BaseConnection)conn;
			CopyManager mgr = new CopyManager(pgcon);
			fileName = Constantes.getFile(producto);
			sql = Constantes.getQueryCarga(producto);
			in = new BufferedReader(new FileReader(new File(PATH_ARCHIVOS+fileName)));
			long rowsaffected  = mgr.copyIn(sql, in);
			System.out.println("Se ejecutó: " + Constantes.getQueryCarga(producto) + " con el archivo: "
					+Constantes.getFile(producto) + " y se copiaron " + rowsaffected + " registros");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				pgcon.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void borraDB(String producto) {
		Connection conn = null;
		try {
			conn = ConnectionCuadraturaBD.getLocalConn();
			Statement statement = conn.createStatement();
			statement.executeUpdate(Constantes.getQueryTruncate(producto));
			System.out.println("Se ejecutó: " + Constantes.getQueryTruncate(producto));
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
	public void descargaArchivo(String filename, String query, String cabecera, String producto) {

		Connection conn = null;
		FileWriter fw = null;

		try {
			if ("OTCAR".equals(producto)){
				conn = ConnectionCuadraturaBD.getConnOCTAR();
			} else {
				conn = ConnectionCuadraturaBD.getConnTPlay();
			}
			fw = new FileWriter(filename);
			fw.append(cabecera);
			fw.append('\n');
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				fw.flush();
				procesar(fw, rs, producto);
			}
			fw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				conn.close();
				fw.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public CountOBJ obtenerCruces(String producto) {
		Connection conn = null;
		FileOutputStream fileOutputStream = null;
		CountOBJ contadores = new CountOBJ();
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		Statement statement = null;
		try {
			conn = ConnectionCuadraturaBD.getLocalConn();
			CopyManager copyManager = new CopyManager((BaseConnection) conn);
			for(int i = 0; i < 2;i++){
				File file = new File(PATH_CRUCE+Constantes.getFileCruce(producto)[i]);
				fileOutputStream = new FileOutputStream(file);
				copyManager.copyOut("COPY (" + Constantes.getQueryCruce(producto)[i] + ") TO STDOUT WITH (FORMAT CSV, HEADER)", fileOutputStream);
				System.out.println("Se ejecutó: " + Constantes.getQueryCruce(producto)[i] + " y se entrega en archivo " + Constantes.getFileCruce(producto)[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				conn.close();
				fileOutputStream.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			conn = ConnectionCuadraturaBD.getLocalConn();
			for (int i = 2; i < 5; i++) {
				pstmt = conn.prepareStatement(Constantes.getQueryCruce(producto)[i]);
				rs = pstmt.executeQuery();
				if(rs.next()){
					if(i==2)
						contadores.setTotalTplay(rs.getInt(1));
					else if(i==3)
						contadores.setTotalRed(rs.getInt(1));
					else
						contadores.setDiferencia(rs.getInt(1));
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (producto.indexOf("KENAN")>=0 && producto.indexOf("62")<0){
			try {
				conn = ConnectionCuadraturaBD.getLocalConn();
				for (int i = 5; i < Constantes.getQueryCruce(producto).length; i++) {
					statement = conn.createStatement();
					statement.executeUpdate(Constantes.getQueryCruce(producto)[i]);
					System.out.println("Se ejecutó: " + Constantes.getQueryCruce(producto)[i]);
				}
			}catch (Exception e){
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return contadores;
	}

	@Override
	public void formatear(String archivo, String producto) {

		FormatoArchivo formatoArchivo = new FormatoArchivo();

		if ("KENAN".equals(producto)){
			formatoArchivo.formatFileKenan(archivo);
		}
		else if ("KENAN_C".equals(producto)){
			formatoArchivo.formatFileCanalesKenan(archivo);
		}
		else if ("KALTURA".equals(producto)){
			formatoArchivo.formatFileTvBaseKaltura(archivo);
		}
		else if ("KALTURA_C".equals(producto)){
			formatoArchivo.formatFileCanalesKaltura(archivo);
		}
		else if ("AAA".equals(producto)){
			formatoArchivo.formatFileInternetAAA(archivo);
		}

	}

	private void procesar(FileWriter fw, ResultSet rs, String producto) throws IOException, SQLException {

		int columnas = rs.getMetaData().getColumnCount();

		if ("INTERNET".equals(producto) && !"".equals(rs.getNString("CODI_TECNICO").trim())){
			for (int i=1;i<columnas;i++){
				fw.append(rs.getString(i)+";");
			}
			fw.append(rs.getString(columnas));
			fw.append('\n');
		} else if ("TLF".equals(producto)){
			for (int i=1;i<=columnas;i++){
				fw.append(rs.getString(i)+";");
			}
			fw.append(rs.getString(2)+"-"+rs.getString(1));
			fw.append('\n');
		} else if ("TV".equals(producto)){
			fw.append(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(5)+";");
			for (int i=1;i<columnas;i++){
				fw.append(rs.getString(i)+";");
			}
			fw.append(rs.getString(columnas));
			fw.append('\n');
		} else if ("OTCAR".equals(producto)){
			fw.append(rs.getString(2)+"-"+rs.getString(4)+";");
			for (int i=1;i<columnas;i++){
				fw.append(rs.getString(i)+";");
			}
			fw.append(rs.getString(columnas));
			fw.append('\n');
		}
	}
}
