package cl.everis.cuadratura.ws;

import java.rmi.RemoteException;
import java.util.Map;

import com.epcs.www.integracionit.flujotrabajo.EnviarMail;
import com.epcs.www.integracionit.flujotrabajo.EnviarMailProxy;

import cl.everis.cuadratura.obj.CountOBJ;

public class Correo {

	public void enviarCorreo(Map<String, CountOBJ> c) {
		EnviarMail em = new EnviarMailProxy();
		try {
			em.enviarMail("cbtorres@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
					getHtmlCorreo(c), null);
			em.enviarMail("jarenass@everis.com", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
					getHtmlCorreo(c), null);
//			em.enviarMail("wugaldeq@everis.com", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
//			getHtmlCorreo(c), null);
//			em.enviarMail("helder.branco@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
//					getHtmlCorreo(c), null);
//			em.enviarMail("CGrao@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
//					getHtmlCorreo(c), null);
//			em.enviarMail("daniela.pinochet@sistemassa.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
//					getHtmlCorreo(c), null);
//			em.enviarMail("egjuleff@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
//					getHtmlCorreo(c), null);
//			em.enviarMail("RPVergara@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
//					getHtmlCorreo(c), null);
			em.enviarMail("pbarra@line.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
					getHtmlCorreo(c), null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private String getHtmlCorreo(Map<String, CountOBJ> c) {
		String toReturn = "<!DOCTYPE html>" + "<html>" + "<head>" + "<style>" + "table, th, td {"
				+ "border: 1px solid black;" + "}" + "</style>" + "</head>" + "<body>"
				+ "<h3>Cuadratura 3 Play Fibra</h3>"
				+ "<p>Estimados, a continuacion las tablas con Q para los siguientes cruces:</p>"
				+ "<br>"
				+ "<h4>3 Play Fibra vs. Plataformas Tecnicas</h4>"
				+ ((null != c.get("TPLAY_KALTURA") || null != c.get("TPLAY_KALTURA_C") || null != c.get("TPLAY_OTCAR")
						|| null != c.get("TPLAY_AAA"))
								? "<table>" + "<tr>" 
										+ "<th>ITEM</th>"
										+ ((null != c.get("TPLAY_KALTURA")) ? "<th>BASE TV(KALTURA)</th>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C")) ? "<th>CANALES TV(KALTURA)</th>" : "")
										+ ((null != c.get("TPLAY_OTCAR")) ? "<th>TELEFONIA(OTCAR)</th>" : "")
										+ ((null != c.get("TPLAY_AAA")) ? "<th>INTERNET AAA(SIN RETIRADOS)</th>" : "") + "<tr/>"
										+ "<tr>"
										+ "<td>TOTAL 3PLAY</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getTotalTplay() + "</td>": "")
										+ "</tr>" + "<tr>" + "<td>TOTAL RED</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getTotalRed() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>RED Y 3PLAY</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getTotalAmbos() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>3PLAY - NO EN RED</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getDifTplayRed() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>RED - NO EN 3PLAY</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getDifRedTplay() + "</td>" : "")
										+ "</tr>" + "</table>"
								: "")
				+ "<br>"
				+ "<h4>3 Play Fibra vs. Facturador Kenan</h4>"
				+ ((null != c.get("TPLAY_KENAN_TV") || null != c.get("TPLAY_KENAN_C")
						|| null != c.get("TPLAY_KENAN_TLF") || null != c.get("TPLAY_KENAN_INT"))
								? "<table>" + "<tr>" 
										+ "<th>ITEM</th>"
										+ ((null != c.get("TPLAY_KENAN_TV")) ? "<th>TV</th>" : "")
										+ ((null != c.get("TPLAY_KENAN_C")) ? "<th>CANALES</th>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF")) ? "<th>TELEFONIA</th>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT")) ? "<th>INTERNET</th>" : "") + "<tr/>"
										+ "<tr>" + "<td>TOTAL 3PLAY</td>"
										+ ((null != c.get("TPLAY_KENAN_TV"))
												? "<td>" + c.get("TPLAY_KENAN_TV").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C"))
												? "<td>" + c.get("TPLAY_KENAN_C").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF"))
												? "<td>" + c.get("TPLAY_KENAN_TLF").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT"))
												? "<td>" + c.get("TPLAY_KENAN_INT").getTotalTplay() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>TOTAL KENAN</td>"
										+ ((null != c.get("TPLAY_KENAN_TV"))
												? "<td>" + c.get("TPLAY_KENAN_TV").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C"))
												? "<td>" + c.get("TPLAY_KENAN_C").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF"))
												? "<td>" + c.get("TPLAY_KENAN_TLF").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT"))
												? "<td>" + c.get("TPLAY_KENAN_INT").getTotalRed() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>3PLAY Y KENAN</td>"
										+ ((null != c.get("TPLAY_KENAN_TV"))
												? "<td>" + c.get("TPLAY_KENAN_TV").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C"))
												? "<td>" + c.get("TPLAY_KENAN_C").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF"))
												? "<td>" + c.get("TPLAY_KENAN_TLF").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT"))
												? "<td>" + c.get("TPLAY_KENAN_INT").getTotalAmbos() + "</td>" : "")
										+ "</tr>" +  "<tr>" + "<td>3PLAY - NO EN KENAN</td>"
										+ ((null != c.get("TPLAY_KENAN_TV"))
												? "<td>" + c.get("TPLAY_KENAN_TV").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C"))
												? "<td>" + c.get("TPLAY_KENAN_C").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF"))
												? "<td>" + c.get("TPLAY_KENAN_TLF").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT"))
												? "<td>" + c.get("TPLAY_KENAN_INT").getDifTplayRed() + "</td>" : "")
										+ "</tr>" +  "<tr>" + "<td>KENAN - NO EN 3PLAY</td>"
										+ ((null != c.get("TPLAY_KENAN_TV"))
												? "<td>" + c.get("TPLAY_KENAN_TV").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C"))
												? "<td>" + c.get("TPLAY_KENAN_C").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF"))
												? "<td>" + c.get("TPLAY_KENAN_TLF").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT"))
												? "<td>" + c.get("TPLAY_KENAN_INT").getDifRedTplay() + "</td>" : "")
										+ "</tr>" + "</table>"
										+ "<br>"
										+ "<h4>3 Play Fibra no en Ciclo 61 vs. Facturador Kenan Ciclo 62</h4>"
										+ "<table>" + "<tr>" 
										+ "<th>ITEM</th>"
										+ ((null != c.get("TPLAY_KENAN_TV_62")) ? "<th>TV</th>" : "")
										+ ((null != c.get("TPLAY_KENAN_C_62")) ? "<th>CANALES</th>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF_62")) ? "<th>TELEFONIA</th>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT_62")) ? "<th>INTERNET</th>" : "") + "<tr/>"
										+ "<tr>" + "<td>TOTAL 3PLAY NO FACTURADOS</td>"
										+ ((null != c.get("TPLAY_KENAN_TV_62"))
												? "<td>" + c.get("TPLAY_KENAN_TV_62").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C_62"))
												? "<td>" + c.get("TPLAY_KENAN_C_62").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF_62"))
												? "<td>" + c.get("TPLAY_KENAN_TLF_62").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT_62"))
												? "<td>" + c.get("TPLAY_KENAN_INT_62").getTotalTplay() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>TOTAL BASE KENAN NO FACTURADOS</td>"
										+ ((null != c.get("TPLAY_KENAN_TV_62"))
												? "<td>" + c.get("TPLAY_KENAN_TV_62").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C_62"))
												? "<td>" + c.get("TPLAY_KENAN_C_62").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF_62"))
												? "<td>" + c.get("TPLAY_KENAN_TLF_62").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT_62"))
												? "<td>" + c.get("TPLAY_KENAN_INT_62").getTotalRed() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>TOTAL 3PLAY EN CICLO 62</td>"
										+ ((null != c.get("TPLAY_KENAN_TV_62"))
												? "<td>" + c.get("TPLAY_KENAN_TV_62").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C_62"))
												? "<td>" + c.get("TPLAY_KENAN_C_62").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF_62"))
												? "<td>" + c.get("TPLAY_KENAN_TLF_62").getTotalAmbos() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT_62"))
												? "<td>" + c.get("TPLAY_KENAN_INT_62").getTotalAmbos() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>TOTAL 3PLAY NINGUN CICLO</td>"
										+ ((null != c.get("TPLAY_KENAN_TV_62"))
												? "<td>" + c.get("TPLAY_KENAN_TV_62").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C_62"))
												? "<td>" + c.get("TPLAY_KENAN_C_62").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF_62"))
												? "<td>" + c.get("TPLAY_KENAN_TLF_62").getDifTplayRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT_62"))
												? "<td>" + c.get("TPLAY_KENAN_INT_62").getDifTplayRed() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>TOTAL CICLO 62 NO EN 3PLAY</td>"
										+ ((null != c.get("TPLAY_KENAN_TV_62"))
												? "<td>" + c.get("TPLAY_KENAN_TV_62").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C_62"))
												? "<td>" + c.get("TPLAY_KENAN_C_62").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF_62"))
												? "<td>" + c.get("TPLAY_KENAN_TLF_62").getDifRedTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT_62"))
												? "<td>" + c.get("TPLAY_KENAN_INT_62").getDifRedTplay() + "</td>" : "")
										+ "</tr>" +  "</table>"
								: "")
				+ ((null != c.get("INTERNET") && null != c.get("TV") && null != c.get("TLF")
				&& null != c.get("ADICIONALES"))
						? "<br>"
								+ "<h4>(3 Play Fibra vs. Plataformas Tecnicas) vs. Kenan</h4>"
								+ "<p>Este Cruce consiste en lo siguiente:<br>"
								+ "<b>OK/OK:</b> Cantidad de productos que se encuentran en (3 play VS. Red) y Kenan, es decir en las tres plataformas<br>"
								+ "<b>OK/NOK:</b> Cantidad de productos que se encuentran en (3 play VS. Red) y no en Kenan, es decir en las dos primeras plataformas<br>"
								+ "<b>NOK/OK:</b> Cantidad de productos que se encuentran en Kenan y no en el cruce (3 play VS. Red), es decir, implica que puede no estar<br>"
								+ "en 3 Play, o en la plataforma Tecnica o en ambas</p>"
								+ "<table>" + "<tr>"
								+ "<th>  3PLAY Y RED / KENAN  </th>"
								+ "<th> BASE TV </th>"
								+ "<th> CANALES TV </th>"
								+ "<th> TELEFONIA </th>"
								+ "<th> INTERNET </th>"
								+ "<tr>"
								+ "<td> OK/OK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getTotalOk() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getTotalOk() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getTotalOk() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td>" + c.get("INTERNET").getTotalOk() + "</td>"
										: "")
								+ "</tr>" + "<tr>" + "<td> OK/NOK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getTotalRedTplay()+ "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getTotalRedTplay() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getTotalRedTplay() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td>" + c.get("INTERNET").getTotalRedTplay() + "</td>" : "")
								+ "</tr>" + "<tr>" + "<td> NOK/OK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getTotalKenan() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getTotalKenan() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getTotalKenan() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td>" + c.get("INTERNET").getTotalKenan() + "</td>" : "")
								+ "</tr>" + "</table>"
						: "")
				+ "</body>" + "</html>";
		return toReturn;
	}
}
