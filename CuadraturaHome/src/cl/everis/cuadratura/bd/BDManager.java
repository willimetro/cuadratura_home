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
	 * @param jTextAreaStatusProcess 
	 */
	JTextArea cargaCSV(String producto, JTextArea jTextAreaStatusProcess);

	/**
	 * 
	 * @param producto
	 * @param jTextAreaStatusProcess 
	 */
	JTextArea borraDB(String producto, JTextArea jTextAreaStatusProcess);
	
	/**
	 * 
	 * @param opcion 
	 * @param jTextAreaStatusProcess 
	 */
	JTextArea descargarCSV(String opcion, JTextArea jTextAreaStatusProcess);
	
	/**
	 * 
	 * @param producto
	 * @param archivo
	 * @param jTextAreaStatusProcess 
	 */
	JTextArea actualiza(String producto, String archivo, JTextArea jTextAreaStatusProcess);
	
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
	 * @param jTextAreaStatusProcess 
	 */
	CountOBJ obtenerCruces(String producto, JTextArea jTextAreaStatusProcess);
	
	/**
	 * 
	 * @param producto
	 * @param jTextAreaStatusProcess 
	 */
	CountOBJ obtenerCrucesCros(String s, JTextArea textAreaTplay);	
	
}
