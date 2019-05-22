package cl.everis.cuadratura.bd;

public interface BDManager {

	
	/**
	 * 
	 * @return
	 */
	void cargaCSV();

	/**
	 * 
	 * @return
	 */
	void borraDB();
	
	/**
	 * 
	 * @return
	 */
	void desacargarCSV();
	
	/**
	 * 
	 * @param filename
	 * @param query
	 * @param cabecera
	 */
	void descargaArchivo(String filename, String query, String cabecera);
	
	
}
