package cl.everis.cuadratura.util;

public class Constantes {
	
	public final static String CABECERA_TLF ="CODI_TECNICO;NRUT_CLIENTE;DRUT_CLIENTE;DESC_GLOSAPROD;NMRO_SOLICITUDACT;KEY_ANI";
	public final static String CABECERA_INT ="CODI_TECNICO;NRUT_CLIENTE;DRUT_CLIENTE;DESC_GLOSAPROD;NMRO_SOLICITUDACT";
	public final static String CABECERA_TV ="KEY_CANAL;RUT;DV;DETALLE;PRODUCTO;CODI_PRODUCTO;TRYBUY;ESTADO_CLIENTE";
	public final static String CABECERA_OCTAR ="KEY_ANI;REQUEST_ID;RUT;DV;VALOR_DEFECTO";

	public final static String FILE_TV ="tv_tplay.csv";
	public final static String FILE_TLF = "tlf_tplay.csv";
	public final static String FILE_INTERNET = "internet_tplay.csv";
	public final static String FILE_OCTAR ="otcar.csv";
	
	public final static String QUERY_TV = "SELECT RUT,"
			+ "DV,"
			+ "DETALLE,"
			+ "PRODUCTO,CODI_PRODUCTO,"
			+ "TRYBUY,"
			+ "ESTADO_CLIENTE "
			+ "FROM (SELECT TO_CHAR(ca.NRUT_CLIENTE) AS RUT"
			+ ",ca.DRUT_CLIENTE AS DV,"
			+ "ca.DESC_TIPODETALLE AS DETALLE,"
			+ "ca.DESC_GLOSAPROD AS PRODUCTO,"
			+ "TO_CHAR(pr.CODI_PRODUCTO) AS CODI_PRODUCTO,"
			+ "'NO' as TRYBUY,"
			+ "DECODE(CU.CODI_ENUMESTADO, 0, 'ACTIVO', 1, 'BLOQUEADO POR CORTE') as ESTADO_CLIENTE "
			+ "FROM OWNMDP.MDP_NEG_CLIENTESACTIVOS ca,"
			+ "OWN3PLAY.RMA_MAE_PRODUCTO pr,"
			+ "RMA_MAE_CLIENTE CLI,"
			+ "RMA_NEG_CUENTA CU "
			+ "WHERE CLI.CORR_CLIENTE = CU.CORR_CLIENTE "
			+ "AND CLI.NRUT_CLIENTE = CA.NRUT_CLIENTE "
			+ "AND ca.VLOR_ESTADOCOMP = 1 "
			+ "AND ca.DESC_TIPOCOMPONENTE in ('SERVICIO', 'PLAN') "
			+ "AND ca.DESC_GLOSAPROD != 'Multiscreen' "
			+ "and pr.codi_producto not in (1026, 103, 104, 127, 130, 132, 133, 750, 801, 802, 950) "
			+ "AND pr.DESC_ENUMTIPOPROUCTO in ('Plan Base', 'Servicio Adicional') "
			+ "AND TRIM(pr.NOMB_PRODUCTO) = ca.DESC_GLOSAPROD "
			+ "UNION SELECT TO_CHAR(pp.NRUT_CLIENTE) AS RUT"
			+ ",pp.DRUT_CLIENTE AS DV,"
			+ "'ADICIONAL' AS PRODUCTO,TRIM(pr.NOMB_PRODUCTO) AS PRODUCTO,"
			+ "TO_CHAR(pr.CODI_PRODUCTO) AS CODI_PRODUCTO,'SI' as TRYBUY,"
			+ "DECODE(CU.CODI_ENUMESTADO, 0, 'ACTIVO', 1, 'BLOQUEADO POR CORTE') as ESTADO_CLIENTE"
			+ " FROM OWN3PLAY.RMA_NEG_PROMPREMIUM pp,"
			+ "RMA_MAE_PRODUCTO pr,"
			+ "RMA_MAE_CLIENTE CLI,"
			+ "RMA_NEG_CUENTA CU "
			+ "WHERE CLI.CORR_CLIENTE = CU.CORR_CLIENTE "
			+ "AND CLI.NRUT_CLIENTE = pp.NRUT_CLIENTE "
			+ "AND pp.CORR_CANAL = pr.CODI_PRODUCTO "
			+ "AND pp.FLAG_ACTIVADO = 0) "
			//+ "WHERE ROWNUM <10 "
			+ "ORDER BY RUT asc";
	public final static String QUERY_TLF = "select d.codi_tecnico, c.nrut_cliente, c.drut_cliente, c.desc_glosaprod, c.nmro_solicitudact "
			+ "from MDP_NEG_CLIENTESACTIVOS C, RMA_NEG_DATOSOTC D "
			+ "where C.nmro_solicitudact = D.corr_solicitud "
			+ "and D.CORR_TIPOSERVICIO = 1 "
			+ "and c.vlor_estadocomp = 1 "
			+ "and c.desc_tiposerv = 'TELEFONIA' "
			+ "and desc_catego = 'PLAN BASE'";
			//+ "and desc_catego = 'PLAN BASE' "
			//+ "AND ROWNUM <10";
	public final static String QUERY_INTERNET = "select d.codi_tecnico, c.nrut_cliente, c.drut_cliente, c.desc_glosaprod, c.nmro_solicitudact "
			+ "from MDP_NEG_CLIENTESACTIVOS C, RMA_NEG_DATOSOTC D "
			+ "where C.nmro_solicitudact = D.corr_solicitud "
			+ "and D.CORR_TIPOSERVICIO = 3 "
			+ "and c.vlor_estadocomp = 1 "
			+ "and c.desc_tiposerv = 'INTERNET' "
			+ "and desc_catego = 'PLAN BASE'";
			//+ "and desc_catego = 'PLAN BASE' "
			//+ "AND ROWNUM <10";
	public final static String QUERY_OCTAR = "SELECT o.REQUEST_ID, o.RUT, o.DV, r.VALOR_DEFECTO "
			+ "FROM SP_OXT o INNER JOIN SP_CS_ATRIBUTO_RECURSO r ON r.REQUEST_ID_OXT = o.REQUEST_ID "
			+ "WHERE r.NOMBRE_ATRIBUTO = 'Ani asociado' "
			+ "AND o.tipo_de_servicio = '3 PLAY FIBRA' "
			+ "AND o.tipo_trabajo = 'INSTALACION' "
			+ "AND o.tipo_de_termino_ott = 'Terminado'";
			//+ "AND o.tipo_de_termino_ott = 'Terminado' "
			//+ "AND ROWNUM <10";	
	
	public String getQueryTV() {
		return QUERY_TV;
		
	}
	public String getQueryTLF() {
		return QUERY_TLF;
		
	}
	public String getQueryINT() {
		return QUERY_INTERNET;
		
	}
	public String getQueryOCT() {
		return QUERY_OCTAR;
		
	}
	public String getFileTV() {
		return FILE_TV;
		
	}
	public String getFileTLF() {
		return FILE_TLF;
		
	}
	public String getFileINT() {
		return FILE_INTERNET;
		
	}
	public String getFileOCT() {
		return FILE_OCTAR;
		
	}
	public String getCabeceraTV() {
		return CABECERA_TV;
		
	}
	public String getCabeceraTLF() {
		return CABECERA_TLF;
		
	}
	public String getCabeceraINT() {
		return CABECERA_INT;
		
	}
	public String getCabeceraOCT() {
		return CABECERA_OCTAR;
		
	}
	
}
