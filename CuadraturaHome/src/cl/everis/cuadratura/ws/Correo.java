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
//		"TPLAY_OTCAR"
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
										((null != c.get("TPLAY_KALTURA")||null != c.get("TPLAY_KALTURA_C")||null != c.get("TPLAY_OTCAR")||null != c.get("TPLAY_AAA")) ? 
										"<table>"+
											"<tr>"+
												"<th></th>"+
												((null != c.get("TPLAY_KALTURA")) ? "<th>BASE TV(KALTURA)</th>": "")+
												((null != c.get("TPLAY_KALTURA_C")) ? "<th>CANALES TV(KALTURA)</th>" : "")+
												((null != c.get("TPLAY_OTCAR")) ? "<th>TELEFONIA(OTCAR)</th>" : "")+
												((null != c.get("TPLAY_AAA")) ? "<th>INTERNET(AAA)</th>" : "")+
											"<tr/>"+
											"<tr>"+
												"<td>TOTAL 3PLAY</td>"+
												((null != c.get("TPLAY_KALTURA")) ? "<td>"+c.get("TPLAY_KALTURA").getTotalTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KALTURA_C")) ? "<td>"+c.get("TPLAY_KALTURA_C").getTotalTplay()+"</td>" : "")+
												((null != c.get("TPLAY_OTCAR")) ? "<td>"+c.get("TPLAY_OTCAR").getTotalTplay()+"</td>" : "")+
												((null != c.get("TPLAY_AAA")) ? "<td>"+c.get("TPLAY_AAA").getTotalTplay()+"</td>" : "")+
											"</tr>"+
											"<tr>"+
												"<td>TOTAL RED</td>"+
												((null != c.get("TPLAY_KALTURA")) ? "<td>"+c.get("TPLAY_KALTURA").getTotalRed()+"</td>" : "")+
												((null != c.get("TPLAY_KALTURA_C")) ? "<td>"+c.get("TPLAY_KALTURA_C").getTotalRed()+"</td>" : "")+
												((null != c.get("TPLAY_OTCAR")) ? "<td>"+c.get("TPLAY_OTCAR").getTotalRed()+"</td>" : "")+
												((null != c.get("TPLAY_AAA")) ? "<td>"+c.get("TPLAY_AAA").getTotalRed()+"</td>" : "")+
											"</tr>"+
											"<tr>"+
												"<td>RED - NO EN 3PLAY</td>"+
												((null != c.get("TPLAY_KALTURA")) ? "<td>"+c.get("TPLAY_KALTURA").getRedNoTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KALTURA_C")) ? "<td>"+c.get("TPLAY_KALTURA_C").getRedNoTplay()+"</td>" : "")+
												((null != c.get("TPLAY_OTCAR")) ? "<td>"+c.get("TPLAY_OTCAR").getRedNoTplay()+"</td>" : "")+
												((null != c.get("TPLAY_AAA")) ? "<td>"+c.get("TPLAY_AAA").getRedNoTplay()+"</td>" : "")+
											"</tr>"+
										"</table>": "")+				
										((null != c.get("TPLAY_KENAN_TV")||null != c.get("TPLAY_KENAN_C")||null != c.get("TPLAY_KENAN_TLF")||null != c.get("TPLAY_KENAN_INT")) ? 
										"<br>"+
										"<table>"+
											"<tr>"+
												"<th></th>"+
												((null != c.get("TPLAY_KENAN_TV")) ? "<th>TV(KENAN)</th>": "")+
												((null != c.get("TPLAY_KENAN_C")) ? "<th>CANALES(KENAN)</th>" : "")+
												((null != c.get("TPLAY_KENAN_TLF")) ? "<th>TELEFONIA(KENAN)</th>" : "")+
												((null != c.get("TPLAY_KENAN_INT")) ? "<th>INTERNET(KENAN)</th>" : "")+
											"<tr/>"+
											"<tr>"+
												"<td>TOTAL 3PLAY</td>"+
												((null != c.get("TPLAY_KENAN_TV")) ? "<td>"+c.get("TPLAY_KENAN_TV").getTotalTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_C")) ? "<td>"+c.get("TPLAY_KENAN_C").getTotalTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_TLF")) ? "<td>"+c.get("TPLAY_KENAN_TLF").getTotalTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_INT")) ? "<td>"+c.get("TPLAY_KENAN_INT").getTotalTplay()+"</td>" : "")+
											"</tr>"+
											"<tr>"+
												"<td>TOTAL KENAN</td>"+
												((null != c.get("TPLAY_KENAN_TV")) ? "<td>"+c.get("TPLAY_KENAN_TV").getTotalRed()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_C")) ? "<td>"+c.get("TPLAY_KENAN_C").getTotalRed()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_TLF")) ? "<td>"+c.get("TPLAY_KENAN_TLF").getTotalRed()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_INT")) ? "<td>"+c.get("TPLAY_KENAN_INT").getTotalRed()+"</td>" : "")+
											"</tr>"+
											"<tr>"+
												"<td>KENAN - NO EN 3PLAY</td>"+
												((null != c.get("TPLAY_KENAN_TV")) ? "<td>"+c.get("TPLAY_KENAN_TV").getRedNoTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_C")) ? "<td>"+c.get("TPLAY_KENAN_C").getRedNoTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_TLF")) ? "<td>"+c.get("TPLAY_KENAN_TLF").getRedNoTplay()+"</td>" : "")+
												((null != c.get("TPLAY_KENAN_INT")) ? "<td>"+c.get("TPLAY_KENAN_INT").getRedNoTplay()+"</td>" : "")+
											"</tr>"+
										"</table>"+
									"</body>"+
								"</html>"
								: "");
		return toReturn;
	}
}
