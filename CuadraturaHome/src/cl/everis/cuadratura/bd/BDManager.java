package cl.everis.cuadratura.bd;

import javax.swing.JTextArea;

import cl.everis.cuadratura.obj.CountOBJ;


public interface BDManager {

	JTextArea cargaCSV(String producto, JTextArea jTextAreaStatusProcess);

	JTextArea borraDB(String producto, JTextArea jTextAreaStatusProcess);
	
	JTextArea descargarCSV(String opcion, JTextArea jTextAreaStatusProcess);
	
	JTextArea actualiza(String producto, String archivo, JTextArea jTextAreaStatusProcess);
	
	void formatear(String archivo, String producto);
	
	void descargaArchivo(String filename, String query, String cabecera, String producto);

	CountOBJ obtenerCruces(String producto, JTextArea jTextAreaStatusProcess);
	
	CountOBJ obtenerCrucesCros(String s, JTextArea textAreaTplay);	
	
}
