package cl.everis.cuadratura.ws;

import java.rmi.RemoteException;
import java.util.Map;

import com.epcs.www.integracionit.flujotrabajo.EnviarMail;
import com.epcs.www.integracionit.flujotrabajo.EnviarMailProxy;

import cl.everis.cuadratura.obj.CountOBJ;
import cl.everis.cuadratura.util.BodyMail;

public class Correo {

	public void enviarCorreo(Map<String, CountOBJ> c, String mailsList) {
		EnviarMail em = new EnviarMailProxy();
		try {
			String body = getHtmlCorreo(c);
			BodyMail.iniciarHTML();
			BodyMail.getHTML(body);
			BodyMail.cerrarHTML();
			if (mailsList != null) {
				String[] listMails = mailsList.split(";");
				for (String mail: listMails) {
					em.enviarMail(mail, "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
							body, null);
				}
			}
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
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
				+ "<h4>3 Play Fibra vs. Facturador Kenan (En la base de KENAN se incluye \"periodo de facturacion 62\" y estado \"retirado\")</h4>"
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
								: "")
				+ ((null != c.get("INTERNET") && null != c.get("TV") && null != c.get("TLF")
				&& null != c.get("ADICIONALES"))
						? "<br>"
								+ "<h4> 3 Play Fibra vs. Plataformas Tecnicas  vs. Kenan</h4>"
								+ "<p>Este Cruce consiste en lo siguiente:<br>"
								+ "<b>OK/OK/OK:</b> Cantidad de productos que se encuentran en 3 play, en la Red y Kenan<br>"
								+ "<b>OK/NOK/OK:</b> Cantidad de productos que se encuentran en 3 play y en Kenan pero no en la red<br>"
								+ "<b>NOK/OK/OK:</b> Cantidad de productos que se encuentran en Kenan y en la red pero no en 3 play<br>"
								+ "<b>OK/OK/NOK:</b> Cantidad de productos que se encuentran en 3 play y en la Red pero no en Kenan<br>"
								+ "<b>OK/NOK/NOK:</b> Cantidad de productos que solo se encuentran en 3 play<br>"
								+ "<b>NOK/OK/NOK:</b> Cantidad de productos que solo se encuentran en la Red<br>"
								+ "<b>NOK/NOK/OK:</b> Cantidad de productos que solo se encuentran en Kenan<br>"
								+ "<table>" + "<tr>"
								+ "<th>  3PLAY / RED / KENAN  </th>"
								+ "<th> BASE TV </th>"
								+ "<th> CANALES TV </th>"
								+ "<th> TELEFONIA </th>"
								+ "<th> INTERNET </th>"
								+ "<tr>"
								+ "<td> OK/OK/OK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getOkOkOk() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getOkOkOk() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getOkOkOk() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td>" + c.get("INTERNET").getOkOkOk() + "</td>" : "")
								+ "</tr>" + "<tr>" + "<td> OK/NOK/OK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getOkNokOk()+ "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getOkNokOk() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getOkNokOk() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td>" + c.get("INTERNET").getOkNokOk() + "</td>" : "")
								+ "</tr>" + "<tr>" + "<td> NOK/OK/OK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getNokOkOk() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getNokOkOk() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getNokOkOk() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td> -- </td>" : "")
								+ "</tr>" + "<tr>" + "<td> OK/OK/NOK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getOkOKNok() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getOkOKNok() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getOkOKNok() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td>" + c.get("INTERNET").getOkOKNok() + "</td>" : "")
								+ "</tr>" + "<tr>" + "<td> OK/NOK/NOK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getOkNokNok() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getOkNokNok() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getOkNokNok() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td>" + c.get("INTERNET").getOkNokNok() + "</td>" : "")
								+ "</tr>" + "<tr>" + "<td> NOK/OK/NOK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getNokOkNok() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getNokOkNok() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getNokOkNok() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td> -- </td>" : "")
								+ "</tr>" + "<tr>" + "<td> NOK/NOK/OK </td>"
								+ ((null != c.get("TV"))
										? "<td>" + c.get("TV").getNokNokOk() + "</td>" : "")
								+ ((null != c.get("ADICIONALES"))
										? "<td>" + c.get("ADICIONALES").getNokNokOk() + "</td>" : "")
								+ ((null != c.get("TLF"))
										? "<td>" + c.get("TLF").getNokNokOk() + "</td>" : "")
								+ ((null != c.get("INTERNET"))
										? "<td> -- </td>" : "")
								+ "</tr>" + "</table>"
						: "")
				+ "</body>" + "</html>";
		return toReturn;
	}
}
