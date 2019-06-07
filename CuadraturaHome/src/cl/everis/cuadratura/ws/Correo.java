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
			em.enviarMail("carboleda@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
					getHtmlCorreo(c), null);
			em.enviarMail("iarancibia@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
					getHtmlCorreo(c), null);
			em.enviarMail("wugaldeq@everis.com", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
					getHtmlCorreo(c), null);
			em.enviarMail("jarenass@everis.com", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"",
					getHtmlCorreo(c), null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private String getHtmlCorreo(Map<String, CountOBJ> c) {
		String toReturn = "<!DOCTYPE html>" + "<html>" + "<head>" + "<style>" + "table, th, td {"
				+ "border: 1px solid black;" + "}" + "</style>" + "</head>" + "<body>"
				+ ((null != c.get("TPLAY_KALTURA") || null != c.get("TPLAY_KALTURA_C") || null != c.get("TPLAY_OTCAR")
						|| null != c.get("TPLAY_AAA"))
								? "<table>" + "<tr>" + "<th></th>"
										+ ((null != c.get("TPLAY_KALTURA")) ? "<th>BASE TV(KALTURA)</th>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C")) ? "<th>CANALES TV(KALTURA)</th>" : "")
										+ ((null != c.get("TPLAY_OTCAR")) ? "<th>TELEFONIA(OTCAR)</th>" : "")
										+ ((null != c.get("TPLAY_AAA")) ? "<th>INTERNET(AAA)</th>" : "") + "<tr/>"
										+ "<tr>"
										+ "<td>TOTAL 3PLAY</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getTotalTplay() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getTotalTplay() + "</td>"
												: "")
										+ "</tr>" + "<tr>" + "<td>TOTAL RED</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getTotalRed() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getTotalRed() + "</td>" : "")
										+ "</tr>" + "<tr>" + "<td>RED - NO EN 3PLAY</td>"
										+ ((null != c.get("TPLAY_KALTURA"))
												? "<td>" + c.get("TPLAY_KALTURA").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_KALTURA_C"))
												? "<td>" + c.get("TPLAY_KALTURA_C").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_OTCAR"))
												? "<td>" + c.get("TPLAY_OTCAR").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_AAA"))
												? "<td>" + c.get("TPLAY_AAA").getDiferencia() + "</td>" : "")
										+ "</tr>" + "</table>"
								: "")
				+ ((null != c.get("TPLAY_KENAN_TV") || null != c.get("TPLAY_KENAN_C")
						|| null != c.get("TPLAY_KENAN_TLF") || null != c.get("TPLAY_KENAN_INT"))
								? "<br>" + "<table>" + "<tr>" + "<th></th>"
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
										+ "</tr>" + "<tr>" + "<td>KENAN - NO EN 3PLAY</td>"
										+ ((null != c.get("TPLAY_KENAN_TV"))
												? "<td>" + c.get("TPLAY_KENAN_TV").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C"))
												? "<td>" + c.get("TPLAY_KENAN_C").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF"))
												? "<td>" + c.get("TPLAY_KENAN_TLF").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT"))
												? "<td>" + c.get("TPLAY_KENAN_INT").getDiferencia() + "</td>" : "")
										+ "</tr>" + "</table>" + "<br>" + "<table>" + "<tr>" + "<th></th>"
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
										+ "</tr>" + "<tr>" + "<td>TOTAL 3PLAY NINGUN CICLO</td>"
										+ ((null != c.get("TPLAY_KENAN_TV_62"))
												? "<td>" + c.get("TPLAY_KENAN_TV_62").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_C_62"))
												? "<td>" + c.get("TPLAY_KENAN_C_62").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_TLF_62"))
												? "<td>" + c.get("TPLAY_KENAN_TLF_62").getDiferencia() + "</td>" : "")
										+ ((null != c.get("TPLAY_KENAN_INT_62"))
												? "<td>" + c.get("TPLAY_KENAN_INT_62").getDiferencia() + "</td>" : "")
										+ "</tr>" + "</table>"
								: "")
				+ "</body>" + "</html>";
		return toReturn;
	}
}
