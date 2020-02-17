package cl.everis.cuadratura.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BodyMail {

	private static FileWriter fw = null;
	private static PrintWriter pw = null;
	private final static String FILE_HTML = System.getProperty("user.home")
			+ "\\Desktop\\cuadratura\\HTML\\BodyHTML_";
	private final static File DIR_HTML = new File(
			System.getProperty("user.home") + "\\Desktop\\cuadratura\\HTML");

	public static void iniciarHTML() {
		try {
			if (!DIR_HTML.exists()) {
				DIR_HTML.mkdirs();
			}
			if (null == fw) {
				fw = new FileWriter(FILE_HTML
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy.ss")) + ".html", true);
				pw = new PrintWriter(fw);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getHTML(String mensaje) {

		try {
			pw.println(mensaje);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cerrarHTML() {
		try {
				if (null != fw) {
					fw.close();
					fw = null;
				}
				if (null != pw) {
					pw.close();
					pw = null;
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
