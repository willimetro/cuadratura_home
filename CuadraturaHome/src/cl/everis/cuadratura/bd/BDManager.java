package cl.everis.cuadratura.bd;

import javax.swing.JTextArea;

import cl.everis.cuadratura.obj.CountOBJ;

/**
 * 
 * @author jarenass
 *
 */
public interface BDManager {

	
	/**
	 * 
	 * @param producto
	 */
	void cargaCSV(String producto, JTextArea jTextAreaStatusProcess);

	/**
	 * 
	 * @param producto
	 */
	void borraDB(String producto, JTextArea jTextAreaStatusProcess);
	
	/**
	 * 
	 * @param opcion 
	 * @param jTextAreaStatusProcess 
	 */
	void descargarCSV(String opcion, JTextArea jTextAreaStatusProcess);
	
	/**
	 * 
	 * @param producto
	 * @param archivo
	 * @param jTextAreaStatusProcess 
	 */
	void actualiza(String producto, String archivo, JTextArea jTextAreaStatusProcess);
	
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
	CountOBJ obtenerCruces(String producto, JTextArea jTextAreaStatusProcess);	
	
}
