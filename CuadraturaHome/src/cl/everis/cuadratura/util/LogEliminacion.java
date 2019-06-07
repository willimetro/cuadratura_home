package cl.everis.cuadratura.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEliminacion {

	private static FileWriter fw = null;
	private static PrintWriter pw = null;
	private final static String FILE_LOG = System.getProperty("user.home")+"\\Desktop\\cuadratura\\LogEliminacion\\LogEliminacion_";
	private final static File DIR_LOG=new File(System.getProperty("user.home")+"\\Desktop\\cuadratura\\LogEliminacion");

	public static void iniciarFicheros(){
		try
		{
			if (!DIR_LOG.exists()){
				DIR_LOG.mkdirs();
			}
			if (null == fw){
				fw = new FileWriter(FILE_LOG+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy.ss"))+".csv" , true);
				pw = new PrintWriter(fw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void escribirTraza(String mensaje){

		try
		{
			pw.flush();
			pw.println(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cerrarFicheros(){	
		try {
			if (null != fw){
				fw.close();
			}
			if (null != pw){
				pw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

