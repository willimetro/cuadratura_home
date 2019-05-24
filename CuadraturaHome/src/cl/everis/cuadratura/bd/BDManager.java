package cl.everis.cuadratura.bd;

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
	void desacargarCSV(String opcion);
	
	/**
	 * 
	 * @param producto
	 */
	void actualiza(String producto);
	
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
	void obtenerCruces(String producto);	
	
}
