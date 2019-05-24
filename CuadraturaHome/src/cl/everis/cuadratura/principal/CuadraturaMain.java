package cl.everis.cuadratura.principal;

import cl.everis.cuadratura.bd.BDManager;
import cl.everis.cuadratura.bd.BDManagerImpl;

public class CuadraturaMain {

	/*
	 *  ESTE ES EL MAIN Y SE EJECUTA DE LA SIGUIENTE FORMA:
	 *  1. DESCARGA LOS ARCHIVOS A LOS CUALES TENESMOS ACCESO DE BD Y ESO SE FILTRA EN EL METODO BDManagerImpl.desacargarCSV
	 *  2. ACTUALIZAR EL PRODUCTO SELECCIONADO (LOS CUALES DEBEN SER LOS INCLUIDOS EN PRODUCTOS_TPLAY  Y CONSTA DE BORRAR LA TABLA CORRESPONDIENTE Y LUEGO CARGAR EL ARCHIVO CORRESPONDIENTE EN DICHA TABLA)
	 *  3. EJECUTA LAS QUERYS CORRESPONDIENTES A CRUCES Y ENTREGA DOS ARCHIVOS POR CRUCE (EN 3PLAY Y NO EN CONTRAPARTE, EN CONTRAPARTE Y NO EN 3PLAY)
	 */
	
	static BDManager bdManager = new BDManagerImpl();
	private final static String[] PRODUCTOS_TPLAY = {
			"INTERNET",
			"TV",
			"TLF",
			"OCTAR",
			"KENAN",
			"KENAN_C",
			"KALTURA",
			"KALTURA_C",
			"AAA"};
	private final static String[] CRUCES_TPLAY = {
			"TPLAY_KALTURA",
			"TPLAY_KALTURA_C",
			"TPLAY_KENAN",
			"TPLAY_KENAN_C",
			"TPLAY_AAA",
			"TPLAY_OCTAR"};
	

	public static void main(String[] args) {
		/* 
		 * CODIGO PARA PROBAR TODOS LOS ITEMS DE FORMA AUTOMATICA SOLO EN ACTUALIZACIÓN Y CRUCES, SE PUEDE AGREGAR LA DESCARGA
		 *
		 *
		for (String arg : PRODUCTOS_TPLAY) {
			System.out.println("METODO MAIN: ACTUALIZA (borra tablas y carga archivo nuevo) 3 PLAY");
			bdManager.actualiza(arg);
		}
		
		for (String arg : CRUCES_TPLAY) {
			System.out.println("METODO MAIN: OBTIENE CRUCES 3 PLAY");
			bdManager.obtenerCruces(arg);
		}
		*/
		
//		CODIGO PARA PROBAR PRODUCTO ESPECIFICO CON DESCARGA, CARGA Y CRUCE
		System.out.println("METODO MAIN: DESCARGA DE CSV");
		bdManager.desacargarCSV("opcion");
		System.out.println("METODO MAIN: ACTUALIZA (borra tablas y carga archivo nuevo) 3 PLAY");
		bdManager.actualiza("opcion");
		System.out.println("METODO MAIN: OBTIENE CRUCES 3 PLAY");
		bdManager.obtenerCruces("opcion");
	}
}