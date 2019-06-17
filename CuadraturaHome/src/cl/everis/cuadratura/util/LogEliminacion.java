package cl.everis.cuadratura.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEliminacion {

	private static FileWriter fwC = null;
	private static PrintWriter pwC = null;
	private static FileWriter fwT = null;
	private static PrintWriter pwT = null;
	private final static String FILE_CANALES = System.getProperty("user.home")+"\\Desktop\\cuadratura\\LogEliminacion\\LogEliminacionCanales_";
	private final static String FILE_TV = System.getProperty("user.home")+"\\Desktop\\cuadratura\\LogEliminacion\\LogEliminacionTv_";
	private final static File DIR_LOG=new File(System.getProperty("user.home")+"\\Desktop\\cuadratura\\LogEliminacion");

	public static void iniciarFichero(String opt){
		try
		{
			if (!DIR_LOG.exists()){
				DIR_LOG.mkdirs();
			}
			if ("corte_canales".equals(opt)) {
				if (null == fwC){
					fwC = new FileWriter(FILE_CANALES+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy.ss"))+".csv" , true);
					pwC = new PrintWriter(fwC);
				}
			} else if ("corte_tv".equals(opt)) {
				if (null == fwT){
					fwT = new FileWriter(FILE_TV+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy.ss"))+".csv" , true);
					pwT = new PrintWriter(fwT);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void escribirTrazaCanales(String mensaje){

		try
		{
			pwC.flush();
			pwC.println(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void escribirTrazaTv(String mensaje){

		try
		{
			pwT.flush();
			pwT.println(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cerrarFicheros(String opt){	
		try {
			if ("corte_canales".equals(opt)) {
				if (null != fwC){
					fwC.close();
					fwC = null;
				}
				if (null != pwC){
					pwC.close();
					pwC = null;
				}
			} else if ("corte_tv".equals(opt)) {
				if (null != fwT){
					fwT.close();
					fwT = null;
				}
				if (null != pwT){
					pwT.close();
					pwT = null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

