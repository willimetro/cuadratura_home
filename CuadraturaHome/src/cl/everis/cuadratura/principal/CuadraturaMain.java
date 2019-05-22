package cl.everis.cuadratura.principal;

import cl.everis.cuadratura.bd.BDManager;
import cl.everis.cuadratura.bd.BDManagerImpl;

public class CuadraturaMain {

	static BDManager bdManager = new BDManagerImpl();

	public static void main(String[] args) {

		
		bdManager.desacargarCSV();
		//bdManager.borraDB();
		//bdManager.cargaCSV();

	}
}