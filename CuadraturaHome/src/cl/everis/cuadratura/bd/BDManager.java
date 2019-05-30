package cl.everis.cuadratura.bd;

import cl.everis.cuadratura.obj.CountOBJ;

public interface BDManager {

	
	/**
	 * 
	 * @param producto
	 */
	void cargaCSV(String producto);

	/**
	 * 
	 * @param producto
	 */
	void borraDB(String producto);
	
	/**
	 * 
	 * @param opcion 
	 */
	void descargarCSV(String opcion);
	
	/**
	 * 
	 * @param producto
	 * @param archivo
	 */
	void actualiza(String producto, String archivo);
	
	/**
	 * 
	 * @param producto
	 * @param archivo
	 */
	void formatear(String archivo, String producto);
	
	/**
	 * 
	 * @param filename
	 * @param query
	 * @param cabecera
	 * @param producto
	 */
	void descargaArchivo(String filename, String query, String cabecera, String producto);

	/**
	 * 
	 * @param producto
	 */
	CountOBJ obtenerCruces(String producto);	
	
}
