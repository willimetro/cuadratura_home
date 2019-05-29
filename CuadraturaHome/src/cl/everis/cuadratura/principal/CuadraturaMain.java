package cl.everis.cuadratura.principal;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cl.everis.cuadratura.ui.CuadraturaUI;

public class CuadraturaMain {

	/*
	 *  ESTE ES EL MAIN Y SE EJECUTA DE LA SIGUIENTE FORMA:
	 *  1. DESCARGA LOS ARCHIVOS A LOS CUALES TENESMOS ACCESO DE BD Y ESO SE FILTRA EN EL METODO BDManagerImpl.desacargarCSV
	 *  2. ACTUALIZAR EL PRODUCTO SELECCIONADO (LOS CUALES DEBEN SER LOS INCLUIDOS EN PRODUCTOS_TPLAY  Y CONSTA DE BORRAR LA TABLA CORRESPONDIENTE Y LUEGO CARGAR EL ARCHIVO CORRESPONDIENTE EN DICHA TABLA)
	 *  3. EJECUTA LAS QUERYS CORRESPONDIENTES A CRUCES Y ENTREGA DOS ARCHIVOS POR CRUCE (EN 3PLAY Y NO EN CONTRAPARTE, EN CONTRAPARTE Y NO EN 3PLAY)
	 */
	
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
//		System.out.println("METODO MAIN: DESCARGA DE CSV");
//		bdManager.desacargarCSV("opcion");
//		System.out.println("METODO MAIN: ACTUALIZA (borra tablas y carga archivo nuevo) 3 PLAY");
//		bdManager.actualiza("opcion");
//		System.out.println("METODO MAIN: OBTIENE CRUCES 3 PLAY");
//		bdManager.obtenerCruces("opcion");
		/* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CuadraturaUI();
            }
        });

	}
}