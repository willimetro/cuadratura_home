package cl.everis.cuadratura.ws;

import java.rmi.RemoteException;
import java.util.Map;

import com.epcs.www.integracionit.flujotrabajo.EnviarMail;
import com.epcs.www.integracionit.flujotrabajo.EnviarMailProxy;

import cl.everis.cuadratura.obj.CountOBJ;

public class Correo {
	
	public void enviarCorreo(Map<String, CountOBJ> c){
		EnviarMail em = new EnviarMailProxy();
		try {
			em.enviarMail("carboleda@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"", getHtmlCorreo(c), null);
			em.enviarMail("iarancibia@entel.cl", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"", getHtmlCorreo(c), null);
			em.enviarMail("wugaldeq@everis.com", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"", getHtmlCorreo(c), null);
			em.enviarMail("jarenass@everis.com", "cuadratura.entelfijo@entel.cl", "Cruces \"Mi Entel Fijo\"", getHtmlCorreo(c), null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private String getHtmlCorreo(Map<String, CountOBJ> c){
		
//		"TPLAY_KALTURA",
//		"TPLAY_KALTURA_C",
//		"TPLAY_KENAN_TV",
//		"TPLAY_KENAN_TLF",
//		"TPLAY_KENAN_INT",
//		"TPLAY_KENAN_C",
//		"TPLAY_AAA",
//		"TPLAY_OCTAR"
		String toReturn = "<!DOCTYPE html>"+
								"<html>"+
									"<head>"+
										"<style>"+
											"table, th, td {"+
												"border: 1px solid black;"+
											"}"+
										"</style>"+
									"</head>"+
									"<body>"+

										"<table>"+
											"<tr>"+
												"<th></th>"+
												"<th>3PLAY-TVB</th>"+
												"<th>3PLAY-CHA</th>"+
												"<th>3PLAY-TLF</th>"+
												"<th>3PLAY-INT</th>"+
											"<tr/>"+
											"<tr>"+
												"<td>TOTAL 3PLAY</td>"+
												"<td>"+c.get("TPLAY_KALTURA").getTotalTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KALTURA_C").getTotalTplay()+"</td>"+
												"<td>"+c.get("TPLAY_OCTAR").getTotalTplay()+"</td>"+
												"<td>"+c.get("TPLAY_AAA").getTotalTplay()+"</td>"+
											"</tr>"+
											"<tr>"+
												"<td>TOTAL RED</td>"+
												"<td>"+c.get("TPLAY_KALTURA").getTotalRed()+"</td>"+
												"<td>"+c.get("TPLAY_KALTURA_C").getTotalRed()+"</td>"+
												"<td>"+c.get("TPLAY_OCTAR").getTotalRed()+"</td>"+
												"<td>"+c.get("TPLAY_AAA").getTotalRed()+"</td>"+
											"</tr>"+
											"<tr>"+
												"<td>DIFERENCIA RED-3PLAY</td>"+
												"<td>"+c.get("TPLAY_KALTURA").getRedNoTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KALTURA_C").getRedNoTplay()+"</td>"+
												"<td>"+c.get("TPLAY_OCTAR").getRedNoTplay()+"</td>"+
												"<td>"+c.get("TPLAY_AAA").getRedNoTplay()+"</td>"+
											"</tr>"+
										"</table>"+
										"<br>"+
										"<table>"+
											"<tr>"+
												"<th></th>"+
												"<th>3PLAY-KTVB</th>"+
												"<th>3PLAY-KCHA</th>"+
												"<th>3PLAY-KTLF</th>"+
												"<th>3PLAY-KINT</th>"+
											"<tr/>"+
											"<tr>"+
												"<td>TOTAL 3PLAY</td>"+
												"<td>"+c.get("TPLAY_KENAN_TV").getTotalTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_C").getTotalTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_TLF").getTotalTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_INT").getTotalTplay()+"</td>"+
											"</tr>"+
											"<tr>"+
												"<td>TOTAL RED</td>"+
												"<td>"+c.get("TPLAY_KENAN_TV").getTotalRed()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_C").getTotalRed()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_TLF").getTotalRed()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_INT").getTotalRed()+"</td>"+
											"</tr>"+
											"<tr>"+
												"<td>DIFERENCIA RED-3PLAY</td>"+
												"<td>"+c.get("TPLAY_KENAN_TV").getRedNoTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_C").getRedNoTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_TLF").getRedNoTplay()+"</td>"+
												"<td>"+c.get("TPLAY_KENAN_INT").getRedNoTplay()+"</td>"+
											"</tr>"+
										"</table>"+
									"</body>"+
								"</html>";
		return toReturn;
	}
}
