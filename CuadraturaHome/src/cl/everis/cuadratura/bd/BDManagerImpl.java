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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import cl.everis.cuadratura.bd.conn.ConnectionCuadraturaBD;
import cl.everis.cuadratura.util.Constantes;

public class BDManagerImpl implements BDManager {

	private final static String PATH_ARCHIVOS = "C:\\Users\\Administrador\\Desktop\\Documentos\\EntelProyectoFijo\\cuadratura\\CSVs\\";
	private final static String PATH_CRUCE = "C:\\Users\\Administrador\\Desktop\\Documentos\\EntelProyectoFijo\\cuadratura\\Cruces\\";

	@Override
	public void desacargarCSV(String opcion) {

		// se agrega esta validación para descargar solo aquellos archivos que podemos obtener directamente en BDs
		if ("INTERNET".equals(opcion)||"TV".equals(opcion)||"TLF".equals(opcion)||"OCTAR".equals(opcion)){
			System.out.println("Inició proceso de descarga para producto: " + opcion);
			descargaArchivo(PATH_ARCHIVOS+Constantes.getFile(opcion),
					Constantes.getQueryDescarga(opcion),
					Constantes.getCabecera(opcion),
					opcion);
			System.out.println("Descargó archivo " + Constantes.getFile(opcion) +" en la ruta: " + PATH_ARCHIVOS );	
		} else {
			// aca codigo para seleccionar archivo de forma local
		}
	}

	@Override
	public void actualiza(String producto) {
		borraDB(producto);
		cargaCSV(producto);
	}

	@Override
	public void cargaCSV(String producto) {
		Connection conn = null;

		String fileName = "";
		String sql = "";

		try {

			conn = ConnectionCuadraturaBD.getLocalConn();
			BaseConnection pgcon = (BaseConnection)conn;
			CopyManager mgr = new CopyManager(pgcon);
			fileName = Constantes.getFile(producto);
			sql = Constantes.getQueryCarga(producto);
			Reader in = new BufferedReader(new FileReader(new File(PATH_ARCHIVOS+fileName)));
			long rowsaffected  = mgr.copyIn(sql, in);
			System.out.println("Se ejecutó: " + Constantes.getQueryCarga(producto) + " con el archivo: "
					+Constantes.getFile(producto) + " y se copiaron " + rowsaffected + " registros");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

		try {
			if ("OCTAR".equals(producto)){
				conn = ConnectionCuadraturaBD.getConnOCTAR();
			} else {
				conn = ConnectionCuadraturaBD.getConnTPlay();
			}
			FileWriter fw = new FileWriter(filename);
			fw.append(cabecera);
			fw.append('\n');
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				procesar(fw, rs, producto);
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
		} else if ("OCTAR".equals(producto)){
			fw.append(rs.getString(2)+"-"+rs.getString(4)+";");
			for (int i=1;i<columnas;i++){
				fw.append(rs.getString(i)+";");
			}
			fw.append(rs.getString(columnas));
			fw.append('\n');
		}
	}


	@Override
	public void obtenerCruces(String producto) {
		Connection conn = null;
		FileOutputStream fileOutputStream = null;
		try {
			conn = ConnectionCuadraturaBD.getLocalConn();
			CopyManager copyManager = new CopyManager((BaseConnection) conn);


			for(int i = 0; i < Constantes.getFileCruce(producto).length;i++){
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
	}
}
