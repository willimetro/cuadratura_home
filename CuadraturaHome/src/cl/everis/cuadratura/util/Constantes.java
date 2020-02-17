package cl.everis.cuadratura.util;

public class Constantes {
	
	private final static String[] QUERYS_CANALES_3P_KALTURA ={
			// 3PLAY_Y_RED
			"SELECT * FROM tvcanales_3play canales_3p INNER JOIN canales_kaltura c_kaltura "
			+ "ON  canales_3p.\"KEY_CANAL\" = c_kaltura.\"KEY_CANAL\" WHERE canales_3p.\"ESTADO\" = 'ACTIVO' "
			+ "AND canales_3p.\"DETALLE\" = 'ADICIONAL'",
			// 3PLAY_NO_RED
			"SELECT canales_3p.* FROM tvcanales_3play canales_3p LEFT OUTER JOIN canales_kaltura c_kaltura "
			+ "ON  canales_3p.\"KEY_CANAL\" = c_kaltura.\"KEY_CANAL\" "
			+ "WHERE canales_3p.\"DETALLE\" = 'ADICIONAL' AND canales_3p.\"TRYBUY\" = 'NO' "
			+ "AND c_kaltura.\"KEY_CANAL\" IS NULL",
			// RED_NO_3PLAY
			"SELECT c_kaltura.* FROM canales_kaltura c_kaltura LEFT OUTER JOIN tvcanales_3play canales_3p "
			+ "ON  canales_3p.\"DETALLE\" = 'ADICIONAL' AND c_kaltura.\"KEY_CANAL\" = canales_3p.\"KEY_CANAL\" "
			+ "WHERE canales_3p.\"KEY_CANAL\" IS NULL",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM tvcanales_3play canales_3p "
			+ "WHERE canales_3p.\"DETALLE\" = 'ADICIONAL' AND canales_3p.\"TRYBUY\" = 'NO'",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM canales_kaltura",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM tvcanales_3play canales_3p INNER JOIN canales_kaltura c_kaltura "
			+ "ON  canales_3p.\"KEY_CANAL\" = c_kaltura.\"KEY_CANAL\" WHERE canales_3p.\"DETALLE\" = 'ADICIONAL' "
			+ "AND canales_3p.\"ESTADO\" = 'ACTIVO'",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM tvcanales_3play canales_3p LEFT OUTER JOIN canales_kaltura c_kaltura "
			+ "ON  canales_3p.\"KEY_CANAL\" = c_kaltura.\"KEY_CANAL\" "
			+ "WHERE canales_3p.\"DETALLE\" = 'ADICIONAL' AND canales_3p.\"TRYBUY\" = 'NO' "
			+ "AND c_kaltura.\"KEY_CANAL\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) FROM canales_kaltura c_kaltura LEFT OUTER JOIN tvcanales_3play canales_3p "
			+ "ON  c_kaltura.\"KEY_CANAL\" = canales_3p.\"KEY_CANAL\" AND canales_3p.\"DETALLE\" = 'ADICIONAL' "
			+ "WHERE canales_3p.\"KEY_CANAL\" IS NULL"};
	private final static String[] QUERYS_CANALES_3P_KENAN ={
			// 3PLAY_Y_RED
			"SELECT tv_3p.\"RUT\",tv_3p.\"DV\",tv_3p.\"PRODUCTO\",tv_3p.\"CODI_PRODUCTO\",tv_3p.\"DETALLE\",tv_3p.\"ESTADO\" AS ESTADO_TPLAY, "
			+ "kenan.\"CUENTA_KENAN\",kenan.\"ESTADO\" AS ESTADO_KENAN, kenan.\"CANAL\", kenan.\"PERIODO_FACT\" "
			+ "FROM tvcanales_3play tv_3p INNER JOIN facturador_kenan_canal kenan ON  tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" "
			+ "WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO'",
			// 3PLAY_NO_RED
			"SELECT tv_3p.* FROM tvcanales_3play tv_3p LEFT OUTER JOIN facturador_kenan_canal kenan "
			+ "ON tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' "
			+ "AND kenan.\"KEY_CANAL\" IS NULL",
			// RED_NO_3PLAY
			"SELECT kenan.* FROM facturador_kenan_canal kenan LEFT OUTER JOIN tvcanales_3play tv_3p "
			+ "ON  tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' "
			+ "AND kenan.\"KEY_CANAL\" = tv_3p.\"KEY_CANAL\" WHERE tv_3p.\"RUT\" IS NULL AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM tvcanales_3play canales_3p "
			+ "WHERE canales_3p.\"DETALLE\" = 'ADICIONAL' AND canales_3p.\"TRYBUY\" = 'NO'",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM facturador_kenan_canal kenan ",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p INNER JOIN facturador_kenan_canal kenan "
			+ "ON  tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO'",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p LEFT OUTER JOIN facturador_kenan_canal kenan "
			+ "ON tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' "
			+ "AND kenan.\"KEY_CANAL\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) "
			+ "FROM facturador_kenan_canal kenan LEFT OUTER JOIN tvcanales_3play tv_3p ON  tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' "
			+ "AND kenan.\"KEY_CANAL\" = tv_3p.\"KEY_CANAL\" WHERE tv_3p.\"RUT\" IS NULL AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// BORRA TABLA 3PLAY PARA CICLO 62
			"TRUNCATE canales_3play_62",
			// CARGA DATOS SEGUNDA PASADA
			"INSERT INTO canales_3play_62 (SELECT tv_3p.* FROM tvcanales_3play tv_3p LEFT OUTER JOIN facturador_kenan_canal kenan "
			+ "ON tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" "
			+ "WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' AND kenan.\"KEY_CANAL\" IS NULL)"};
	private final static String[] QUERYS_INTERNET_3P_AAA ={
			// 3PLAY_Y_RED
			"SELECT * FROM internet_3play tplay INNER JOIN internet_aaa aaa ON  tplay.\"CODI_TECNICO\" = aaa.\"UID\"",
			// 3PLAY_NO_RED
			"SELECT tplay.* FROM internet_3play tplay LEFT OUTER JOIN internet_aaa aaa "
			+ "ON  tplay.\"CODI_TECNICO\" = aaa.\"UID\" "
			+ "WHERE aaa.\"UID\" IS NULL",
			// RED_NO_3PLAY
			"SELECT aa.* FROM (SELECT aaa.* FROM internet_aaa aaa LEFT OUTER JOIN internet_3play tplay "
			+ "ON  aaa.\"UID\" = tplay.\"CODI_TECNICO\" "
			+ "WHERE tplay.\"CODI_TECNICO\" IS NULL) AS aa LEFT OUTER JOIN bdservicios_retirados bserv "
			+ "ON  aa.\"UID\" = bserv.\"VALOR_ATRIBUTO\" WHERE bserv.\"VALOR_ATRIBUTO\" IS NULL",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM internet_3play",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM internet_aaa",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM internet_3play tplay INNER JOIN internet_aaa aaa ON  tplay.\"CODI_TECNICO\" = aaa.\"UID\"",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM internet_3play tplay LEFT OUTER JOIN internet_aaa aaa "
			+ "ON  tplay.\"CODI_TECNICO\" = aaa.\"UID\" "
			+ "WHERE aaa.\"UID\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) FROM (SELECT aaa.* FROM internet_aaa aaa LEFT OUTER JOIN internet_3play tplay "
			+ "ON  aaa.\"UID\" = tplay.\"CODI_TECNICO\" "
			+ "WHERE tplay.\"CODI_TECNICO\" IS NULL) AS aa LEFT OUTER JOIN bdservicios_retirados bserv "
			+ "ON  aa.\"UID\" = bserv.\"VALOR_ATRIBUTO\" WHERE bserv.\"VALOR_ATRIBUTO\" IS NULL"};
	private final static String[] QUERYS_INTERNET_3P_KENAN ={
			// 3PLAY_Y_RED
			"SELECT tplay.\"NRUT_CLIENTE\",tplay.\"DRUT_CLIENTE\",tplay.\"CODI_TECNICO\",tplay.\"NMRO_SOLICITUDACT\", kenan.\"CUENTA_KENAN\", "
			+ "kenan.\"ESTADO\",kenan.\"PLAN\", kenan.\"PERIODO_FACT\" FROM internet_3play tplay INNER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// 3PLAY_NO_RED
			"SELECT tplay.* FROM internet_3play tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// RED_NO_3PLAY
			"SELECT kenan.* FROM facturador_kenan kenan LEFT OUTER JOIN internet_3play tplay "
			+ "ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" IS NULL AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM internet_3play",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM facturador_kenan kenan WHERE kenan.\"PLAN\" = 'PLAN BANDA ANCHA'",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM internet_3play tplay INNER JOIN facturador_kenan kenan ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' "
			+ "AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM internet_3play tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) FROM facturador_kenan kenan LEFT OUTER JOIN internet_3play tplay ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" IS NULL AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// BORRA TABLA 3PLAY PARA CICLO 62
			"TRUNCATE internet_3play_62",
			// CARGA DATOS SEGUNDA PASADA
			"INSERT INTO internet_3play_62 (SELECT tplay.* FROM internet_3play tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL)"};
	private final static String[] QUERYS_TLF_3P_KENAN ={
			// 3PLAY_Y_RED
			"SELECT tplay.\"NRUT_CLIENTE\",tplay.\"DRUT_CLIENTE\",tplay.\"CODI_TECNICO\",tplay.\"NMRO_SOLICITUDACT\", "
			+ "kenan.\"CUENTA_KENAN\",kenan.\"ESTADO\",kenan.\"PLAN\", kenan.\"PERIODO_FACT\" FROM tlf_3play tplay INNER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// 3PLAY_NO_RED
			"SELECT tplay.* FROM tlf_3play tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// RED_NO_3PLAY
			"SELECT kenan.* FROM facturador_kenan kenan LEFT OUTER JOIN tlf_3play tplay ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" IS NULL AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM tlf_3play",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM facturador_kenan kenan WHERE kenan.\"PLAN\" = 'PLAN TELEFONIA'",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM tlf_3play tplay INNER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM tlf_3play tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) FROM facturador_kenan kenan LEFT OUTER JOIN tlf_3play tplay ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" IS NULL AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// BORRA TABLA 3PLAY PARA CICLO 62			
			"TRUNCATE tlf_3play_62",
			// CARGA DATOS SEGUNDA PASADA
			"INSERT INTO tlf_3play_62 (SELECT tplay.* FROM tlf_3play tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL)"};
	private final static String[] QUERYS_TLF_3P_OTCAR ={
			// 3PLAY_Y_RED
			"SELECT tplay.\"NRUT_CLIENTE\",tplay.\"DRUT_CLIENTE\",tplay.\"CODI_TECNICO\",otcar.\"KEY_ANI\" "
			+ "FROM tlf_3play tplay INNER JOIN tlf_otcar otcar ON  tplay.\"KEY_ANI\" = otcar.\"KEY_ANI\"",
			// 3PLAY_NO_RED
			"SELECT tplay.* FROM tlf_3play tplay LEFT OUTER JOIN tlf_otcar otcar "
			+ "ON  tplay.\"KEY_ANI\" = otcar.\"KEY_ANI\" WHERE otcar.\"KEY_ANI\" IS NULL",
			// RED_NO_3PLAY
			"SELECT otcar.* FROM tlf_otcar otcar LEFT OUTER JOIN tlf_3play tplay "
			+ "ON  otcar.\"KEY_ANI\" = tplay.\"KEY_ANI\" WHERE tplay.\"KEY_ANI\" IS NULL",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM tlf_3play",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM tlf_otcar",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM tlf_3play tplay INNER JOIN tlf_otcar otcar ON  tplay.\"KEY_ANI\" = otcar.\"KEY_ANI\"",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM tlf_3play tplay LEFT OUTER JOIN tlf_otcar otcar "
			+ "ON  tplay.\"KEY_ANI\" = otcar.\"KEY_ANI\" WHERE otcar.\"KEY_ANI\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) FROM tlf_otcar otcar LEFT OUTER JOIN tlf_3play tplay "
			+ "ON  otcar.\"KEY_ANI\" = tplay.\"KEY_ANI\" WHERE tplay.\"KEY_ANI\" IS NULL"};
	private final static String[] QUERYS_TV_3P_KALTURA ={
			// 3PLAY_Y_RED
			"SELECT * FROM tvcanales_3play tv_3p INNER JOIN tv_kaltura tv_kal ON tv_3p.\"RUT\" = tv_kal.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND tv_3p.\"ESTADO\" = 'ACTIVO'",
			// 3PLAY_NO_RED
			"SELECT tv_3p.* FROM tvcanales_3play tv_3p LEFT OUTER JOIN tv_kaltura tv_kal ON  tv_3p.\"RUT\" = tv_kal.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND tv_kal.\"KEY_RUT_SIN_DV\" IS NULL",
			// RED_NO_3PLAY
			"SELECT tv_kal.* FROM tv_kaltura tv_kal LEFT OUTER JOIN tvcanales_3play tv_3p "
			+ "ON  tv_kal.\"KEY_RUT_SIN_DV\" = tv_3p.\"RUT\" AND tv_3p.\"CODI_PRODUCTO\" IN ('128','129') "
			+ "WHERE tv_3p.\"RUT\" IS NULL",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129')",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM tv_kaltura tv_kal",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p INNER JOIN tv_kaltura tv_kal ON tv_3p.\"RUT\" = tv_kal.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND tv_3p.\"ESTADO\" = 'ACTIVO'",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p LEFT OUTER JOIN tv_kaltura tv_kal ON  tv_3p.\"RUT\" = tv_kal.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND tv_kal.\"KEY_RUT_SIN_DV\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) FROM tv_kaltura tv_kal LEFT OUTER JOIN tvcanales_3play tv_3p "
			+ "ON  tv_kal.\"KEY_RUT_SIN_DV\" = tv_3p.\"RUT\" AND tv_3p.\"CODI_PRODUCTO\" IN ('128','129') "
			+ "WHERE tv_3p.\"RUT\" IS NULL"};
	private final static String[] QUERYS_TV_3P_KENAN ={
			// 3PLAY_Y_RED
			"SELECT tv_3p.\"RUT\",tv_3p.\"DV\",tv_3p.\"PRODUCTO\",tv_3p.\"CODI_PRODUCTO\",tv_3p.\"DETALLE\",tv_3p.\"ESTADO\", "
			+ "kenan.\"CUENTA_KENAN\",kenan.\"ESTADO\",kenan.\"PLAN\", kenan.\"PERIODO_FACT\" FROM tvcanales_3play tv_3p INNER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129')",
			// 3PLAY_NO_RED
			"SELECT tv_3p.* FROM tvcanales_3play tv_3p LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// RED_NO_3PLAY
			"SELECT kenan.* FROM facturador_kenan kenan LEFT OUTER JOIN tvcanales_3play tv_3p ON  tv_3p.\"CODI_PRODUCTO\" IN ('128','129') "
			+ "AND kenan.\"KEY_RUT_SIN_DV\" = tv_3p.\"RUT\" WHERE kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" IS NULL "
			+ "AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// COUNT TOTAL 3_PLAY
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129')",
			// COUNT TOTAL RED
			"SELECT COUNT(1) FROM facturador_kenan kenan WHERE kenan.\"PLAN\" = 'PLAN TELEVISION'",
			// COUNT 3PLAY_Y_RED
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p INNER JOIN facturador_kenan kenan ON  kenan.\"PLAN\" = 'PLAN TELEVISION' "
			+ "AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129')",
			// COUNT 3PLAY_NO_RED
			"SELECT COUNT(1) FROM tvcanales_3play tv_3p LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// COUNT RED_NO_3PLAY
			"SELECT COUNT(1) FROM facturador_kenan kenan LEFT OUTER JOIN tvcanales_3play tv_3p "
			+ "ON  tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND kenan.\"KEY_RUT_SIN_DV\" = tv_3p.\"RUT\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" IS NULL AND kenan.\"ESTADO\" IN ('Facturado','Nuevo','Otro')",
			// BORRA TABLA 3PLAY PARA CICLO 62
			"TRUNCATE tv_3play_62",
			// CARGA DATOS SEGUNDA PASADA
			"INSERT INTO tv_3play_62 (SELECT tv.* FROM tvcanales_3play tv LEFT OUTER JOIN facturador_kenan kn "
			+ "ON tv.\"RUT\" = kn.\"KEY_RUT_SIN_DV\" AND kn.\"PLAN\" = 'PLAN TELEVISION' "
			+ "WHERE tv.\"CODI_PRODUCTO\" IN ('128','129') AND kn.\"KEY_RUT_SIN_DV\" IS NULL)"};
	private final static String[] QUERYS_TV_3P_KENAN_62 ={
			// 3PLAY SEGUNDA PASADA QUE ESTA EN KENAN CICLO 62
			"SELECT tv_62.\"RUT\", tv_62.\"DV\", tv_62.\"PRODUCTO\", tv_62.\"CODI_PRODUCTO\", tv_62.\"TRYBUY\", tv_62.\"ESTADO\", "
			+ "kn_62.\"NEGOCIO\", kn_62.\"FECHA_VCTO\", kn_62.\"NRO_FOLIO\", kn_62.\"OBSERVACION\", kn_62.\"SALDO\" "
			+ "FROM tv_3play_62 tv_62 INNER JOIN facturador_kenan_62 kn_62 "
			+ "ON tv_62.\"RUT\" = kn_62.\"RUT_SIN_DV\"",
			// 3PLAY SEGUNDA PASADA QUE NO ESTA EN KENAN CICLO 62
			"SELECT tv_62.* FROM tv_3play_62 tv_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON tv_62.\"RUT\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// KENAN CICLO 62 NO 3PLAY SEGUNDA PASADA
			"SELECT kn_62.* FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN tv_3play_62 tv_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = tv_62.\"RUT\" WHERE tv_62.\"RUT\" IS NULL",
			// COUNT 3PLAY NO KENAN CICLO 61
			"SELECT COUNT(1) FROM tv_3play_62",
			// COUNT TOTAL BASE KENAN CICLO 62
			"SELECT COUNT(1) FROM facturador_kenan_62",
			// COUNT 3PLAY_Y_KENAN 62
			"SELECT COUNT(1) FROM tv_3play_62 tv_62 INNER JOIN facturador_kenan_62 kn_62 "
			+ "ON tv_62.\"RUT\" = kn_62.\"RUT_SIN_DV\"",
			// COUNT 3PLAY NO KENAN 62
			"SELECT COUNT(1) FROM tv_3play_62 tv_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON tv_62.\"RUT\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// COUNT KENAN 62_NO_3PLAY
			"SELECT COUNT(1) FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN tv_3play_62 tv_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = tv_62.\"RUT\" WHERE tv_62.\"RUT\" IS NULL"};
	private final static String[] QUERYS_CANALES_3P_KENAN_62 ={
			// 3PLAY SEGUNDA PASADA QUE ESTA EN KENAN CICLO 62
			"SELECT canal_62.*, kn_62.\"NEGOCIO\", kn_62.\"FECHA_VCTO\", kn_62.\"NRO_FOLIO\", kn_62.\"OBSERVACION\", kn_62.\"SALDO\" "
			+ "FROM canales_3play_62 canal_62 INNER JOIN facturador_kenan_62 kn_62 "
			+ "ON canal_62.\"RUT\" = kn_62.\"RUT_SIN_DV\"",
			// 3PLAY SEGUNDA PASADA QUE NO ESTA EN KENAN CICLO 62
			"SELECT canal_62.* FROM canales_3play_62 canal_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON canal_62.\"RUT\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// KENAN CICLO 62 NO 3PLAY SEGUNDA PASADA
			"SELECT kn_62.* FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN canales_3play_62 canal_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = canal_62.\"RUT\" WHERE canal_62.\"RUT\" IS NULL",
			// COUNT 3PLAY NO KENAN CICLO 61
			"SELECT COUNT(1) FROM canales_3play_62",
			// COUNT TOTAL BASE KENAN CICLO 62
			"SELECT COUNT(1) FROM facturador_kenan_62",
			// COUNT 3PLAY_Y_KENAN 62
			"SELECT COUNT(1) FROM canales_3play_62 canal_62 INNER JOIN facturador_kenan_62 kn_62 "
			+ "ON canal_62.\"RUT\" = kn_62.\"RUT_SIN_DV\"",
			// COUNT 3PLAY NO KENAN 62
			"SELECT COUNT(1) FROM canales_3play_62 canal_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON canal_62.\"RUT\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// COUNT KENAN 62_NO_3PLAY
			"SELECT COUNT(1) FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN canales_3play_62 canal_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = canal_62.\"RUT\" WHERE canal_62.\"RUT\" IS NULL"};
	private final static String[] QUERYS_INTERNET_3P_KENAN_62 ={
			// 3PLAY SEGUNDA PASADA QUE ESTA EN KENAN CICLO 62
			"SELECT int_62.\"NRUT_CLIENTE\", int_62.\"DRUT_CLIENTE\", int_62.\"CODI_TECNICO\", int_62.\"DESC_GLOSAPROD\", "
			+ "kn_62.\"NEGOCIO\", kn_62.\"FECHA_VCTO\", kn_62.\"NRO_FOLIO\", kn_62.\"OBSERVACION\", kn_62.\"SALDO\" "
			+ "FROM internet_3play_62 int_62 INNER JOIN facturador_kenan_62 kn_62 ON int_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\"",
			// 3PLAY SEGUNDA PASADA QUE NO ESTA EN KENAN CICLO 62
			"SELECT int_62.* FROM internet_3play_62 int_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON int_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// KENAN CICLO 62 NO 3PLAY SEGUNDA PASADA
			"SELECT kn_62.* FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN internet_3play_62 int_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = int_62.\"NRUT_CLIENTE\" WHERE int_62.\"NRUT_CLIENTE\" IS NULL",
			// COUNT 3PLAY NO KENAN CICLO 61
			"SELECT COUNT(1) FROM internet_3play_62",
			// COUNT TOTAL BASE KENAN CICLO 62
			"SELECT COUNT(1) FROM facturador_kenan_62",
			// COUNT 3PLAY_Y_KENAN 62
			"SELECT COUNT(1) FROM internet_3play_62 int_62 INNER JOIN facturador_kenan_62 kn_62 "
			+ "ON int_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\"",
			// COUNT 3PLAY NO KENAN 62
			"SELECT COUNT(1) FROM internet_3play_62 int_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON int_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// COUNT KENAN 62_NO_3PLAY
			"SELECT COUNT(1) FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN internet_3play_62 int_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = int_62.\"NRUT_CLIENTE\" WHERE int_62.\"NRUT_CLIENTE\" IS NULL"};
	private final static String[] QUERYS_TLF_3P_KENAN_62 ={
			// 3PLAY SEGUNDA Y KENAN CICLO 62
			"SELECT tlf_62.\"NRUT_CLIENTE\", tlf_62.\"DRUT_CLIENTE\", tlf_62.\"CODI_TECNICO\", tlf_62.\"DESC_GLOSAPROD\", "
			+ "kn_62.\"NEGOCIO\", kn_62.\"FECHA_VCTO\", kn_62.\"NRO_FOLIO\", kn_62.\"OBSERVACION\", kn_62.\"SALDO\" "
			+ "FROM tlf_3play_62 tlf_62 INNER JOIN facturador_kenan_62 kn_62 ON tLF_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\"",
			// 3PLAY SEGUNDA PASADA NO KENAN CICLO 62
			"SELECT tlf_62.* FROM tlf_3play_62 tlf_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON tLF_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// KENAN CICLO 62 NO 3PLAY SEGUNDA PASADA
			"SELECT kn_62.* FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN tlf_3play_62 tlf_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = tlf_62.\"NRUT_CLIENTE\" WHERE tlf_62.\"NRUT_CLIENTE\" IS NULL",
			// COUNT 3PLAY NO KENAN CICLO 61
			"SELECT COUNT(1) FROM tlf_3play_62",
			// COUNT TOTAL BASE KENAN CICLO 62
			"SELECT COUNT(1) FROM facturador_kenan_62",
			// COUNT 3PLAY_Y_KENAN 62
			"SELECT COUNT(1) FROM tlf_3play_62 tlf_62 INNER JOIN facturador_kenan_62 kn_62 "
			+ "ON tLF_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\"",
			// COUNT 3PLAY NO KENAN 62
			"SELECT COUNT(1) FROM tlf_3play_62 tlf_62 LEFT OUTER JOIN facturador_kenan_62 kn_62 "
			+ "ON tLF_62.\"NRUT_CLIENTE\" = kn_62.\"RUT_SIN_DV\" WHERE kn_62.\"RUT_SIN_DV\" IS NULL",
			// COUNT KENAN 62_NO_3PLAY
			"SELECT COUNT(1) FROM facturador_kenan_62 kn_62 LEFT OUTER JOIN tlf_3play_62 tlf_62 "
			+ "ON kn_62.\"RUT_SIN_DV\" = tlf_62.\"NRUT_CLIENTE\" WHERE tlf_62.\"NRUT_CLIENTE\" IS NULL"};
	private final static String[] QUERYS_CROS_CANALES ={
			// BORRA TABLA 3PLAY_RED CANALES
			"TRUNCATE canales_3play_red",
			// INSERTA DATOS 3PLAY_Y_RED CANALES
			"INSERT INTO canales_3play_red (SELECT canales_3p.* FROM tvcanales_3play canales_3p INNER JOIN canales_kaltura c_kaltura ON  "
			+ "canales_3p.\"KEY_CANAL\" = c_kaltura.\"KEY_CANAL\" WHERE canales_3p.\"ESTADO\" = 'ACTIVO' "
			+ "AND canales_3p.\"DETALLE\" = 'ADICIONAL')",
			// 3PLAYRED_Y_KENAN CANALES
			"SELECT tv_3p.\"RUT\",tv_3p.\"DV\",tv_3p.\"PRODUCTO\",tv_3p.\"CODI_PRODUCTO\",tv_3p.\"DETALLE\",tv_3p.\"ESTADO\" "
			+ "AS ESTADO_TPLAY,kenan.\"CUENTA_KENAN\",kenan.\"ESTADO\" AS ESTADO_KENAN,kenan.\"CANAL\" "
			+ "FROM canales_3play_red tv_3p INNER JOIN facturador_kenan_canal kenan ON "
			+ "tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" "
			+ "WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO'",
			// 3PLAYRED_NO_KENAN CANALES
			"SELECT tv_3p.* FROM canales_3play_red tv_3p LEFT OUTER JOIN facturador_kenan_canal kenan "
			+ "ON  tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" "
			+ "WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' "
			+ "AND kenan.\"KEY_CANAL\" IS NULL",
			// KENAN_NO_3PLAYRED CANALES
			"SELECT kenan.* FROM facturador_kenan_canal kenan LEFT OUTER JOIN canales_3play_red tv_3p "
			+ "ON  tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' "
			+ "AND kenan.\"KEY_CANAL\" = tv_3p.\"KEY_CANAL\" "
			+ "WHERE tv_3p.\"RUT\" IS NULL",
			// COUNT 3PLAYRED_Y_KENAN CANALES
			"SELECT COUNT(1) FROM canales_3play_red tv_3p INNER JOIN facturador_kenan_canal kenan "
			+ "ON tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" "
			+ "WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO'",
			// COUNT 3PLAYRED_NO_KENAN CANALES
			"SELECT COUNT(1) FROM canales_3play_red tv_3p LEFT OUTER JOIN facturador_kenan_canal kenan ON "
			+ "tv_3p.\"KEY_CANAL\" = kenan.\"KEY_CANAL\" WHERE tv_3p.\"DETALLE\" = 'ADICIONAL' "
			+ "AND tv_3p.\"TRYBUY\" = 'NO' AND kenan.\"KEY_CANAL\" IS NULL",
			// COUNT KENAN_NO_3PLAYRED CANALES
			"SELECT COUNT(1) "
			+ "FROM facturador_kenan_canal kenan LEFT OUTER JOIN canales_3play_red tv_3p "
			+ "ON  tv_3p.\"DETALLE\" = 'ADICIONAL' AND tv_3p.\"TRYBUY\" = 'NO' "
			+ "AND kenan.\"KEY_CANAL\" = tv_3p.\"KEY_CANAL\" "
			+ "WHERE tv_3p.\"RUT\" IS NULL"};
	private final static String[] QUERYS_CROS_INTERNET ={
			// BORRA TABLA 3PLAY_RED INTERNET
			"TRUNCATE internet_3play_red",
			// INSERTA DATOS 3PLAY_Y_RED INTERNET
			"INSERT INTO internet_3play_red (SELECT tplay.* FROM internet_3play tplay "
			+ "INNER JOIN internet_aaa aaa ON  tplay.\"CODI_TECNICO\" = aaa.\"UID\")",
			// 3PLAYRED_Y_KENAN INTERNET
			"SELECT tplay.\"NRUT_CLIENTE\",tplay.\"DRUT_CLIENTE\",tplay.\"CODI_TECNICO\",tplay.\"NMRO_SOLICITUDACT\", "
			+ "kenan.\"CUENTA_KENAN\",kenan.\"ESTADO\",kenan.\"PLAN\" FROM internet_3play_red tplay INNER JOIN "
			+ "facturador_kenan kenan ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' "
			+ "AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// 3PLAYRED_NO_KENAN INTERNET
			"SELECT tplay.* FROM internet_3play_red tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// KENAN_NO_3PLAYRED INTERNET
			"SELECT kenan.* FROM facturador_kenan kenan LEFT OUTER JOIN internet_3play_red tplay "
			+ "ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" IS NULL",
			// COUNT 3PLAYRED_Y_KENAN INTERNET
			"SELECT COUNT(1) FROM internet_3play_red tplay INNER JOIN "
			+ "facturador_kenan kenan ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' "
			+ "AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// COUNT 3PLAYRED_NO_KENAN INTERNET
			"SELECT COUNT(1) FROM internet_3play_red tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// COUNT KENAN_NO_3PLAYRED INTERNET
			"SELECT COUNT(1) FROM facturador_kenan kenan LEFT OUTER JOIN internet_3play_red tplay "
			+ "ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN BANDA ANCHA' AND tplay.\"NRUT_CLIENTE\" IS NULL"};
	private final static String[] QUERYS_CROS_TLF ={
			// BORRA TABLA 3PLAY_RED TLF
			"TRUNCATE tlf_3play_red",
			// INSERTA DATOS 3PLAY_Y_RED TLF
			"INSERT INTO tlf_3play_red (SELECT tplay.* FROM tlf_3play tplay "
			+ "INNER JOIN tlf_otcar otcar ON  tplay.\"KEY_ANI\" = otcar.\"KEY_ANI\")",	
			// 3PLAYRED_Y_KENAN TLF
			"SELECT tplay.\"NRUT_CLIENTE\",tplay.\"DRUT_CLIENTE\",tplay.\"CODI_TECNICO\",tplay.\"NMRO_SOLICITUDACT\", "
			+ "kenan.\"CUENTA_KENAN\",kenan.\"ESTADO\",kenan.\"PLAN\" FROM tlf_3play_red tplay INNER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// 3PLAYRED_NO_KENAN TLF
			"SELECT tplay.* FROM tlf_3play_red tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// KENAN_NO_3PLAYRED TLF
			"SELECT kenan.* FROM facturador_kenan kenan LEFT OUTER JOIN tlf_3play_red tplay "
			+ "ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN TELEFONIA' AND  tplay.\"NRUT_CLIENTE\" IS NULL",
			// COUNT 3PLAYRED_Y_KENAN CANALES
			"SELECT COUNT(1) FROM tlf_3play_red tplay INNER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\"",
			// COUNT 3PLAYRED_NO_KENAN CANALES
			"SELECT COUNT(1) FROM tlf_3play_red tplay LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON  kenan.\"PLAN\" = 'PLAN TELEFONIA' AND tplay.\"NRUT_CLIENTE\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// COUNT KENAN_NO_3PLAYRED CANALES
			"SELECT COUNT(1) FROM facturador_kenan kenan LEFT OUTER JOIN tlf_3play_red tplay "
			+ "ON  kenan.\"KEY_RUT_SIN_DV\" = tplay.\"NRUT_CLIENTE\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN TELEFONIA' AND  tplay.\"NRUT_CLIENTE\" IS NULL"};
	private final static String[] QUERYS_CROS_TV ={		
			// BORRA TABLA 3PLAY_RED TV
			"TRUNCATE tv_3play_red",
			// INSERTA DATOS 3PLAY_Y_RED TV
			"INSERT INTO tv_3play_red (SELECT tv_3p.* FROM tvcanales_3play tv_3p "
			+ "INNER JOIN tv_kaltura tv_kal ON  tv_3p.\"RUT\" = tv_kal.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND tv_3p.\"ESTADO\" = 'ACTIVO')",
			// 3PLAYRED_Y_KENAN TV
			"SELECT tv_3p.\"RUT\",tv_3p.\"DV\",tv_3p.\"PRODUCTO\",tv_3p.\"CODI_PRODUCTO\",tv_3p.\"DETALLE\","
			+ "tv_3p.\"ESTADO\", kenan.\"CUENTA_KENAN\",kenan.\"ESTADO\",kenan.\"PLAN\" FROM tv_3play_red tv_3p "
			+ "INNER JOIN facturador_kenan kenan ON  kenan.\"PLAN\" = 'PLAN TELEVISION' "
			+ "AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129')",
			// 3PLAYRED_NO_KENAN TV
			"SELECT tv_3p.* FROM tv_3play_red tv_3p LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// KENAN_NO_3PLAYRED TV
			"SELECT kenan.* FROM facturador_kenan kenan LEFT OUTER JOIN tv_3play_red tv_3p "
			+ "ON  tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND kenan.\"KEY_RUT_SIN_DV\" = tv_3p.\"RUT\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" IS NULL",
			// COUNT 3PLAYRED_Y_KENAN TV
			"SELECT COUNT(1) FROM tv_3play_red tv_3p "
			+ "INNER JOIN facturador_kenan kenan ON  kenan.\"PLAN\" = 'PLAN TELEVISION' "
			+ "AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129')",
			// COUNT 3PLAYRED_NO_KENAN CANALES
			"SELECT COUNT(1) FROM tv_3play_red tv_3p LEFT OUTER JOIN facturador_kenan kenan "
			+ "ON kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" = kenan.\"KEY_RUT_SIN_DV\" "
			+ "WHERE tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND kenan.\"KEY_RUT_SIN_DV\" IS NULL",
			// COUNT KENAN_NO_3PLAYRED CANALES
			"SELECT COUNT(1) FROM facturador_kenan kenan LEFT OUTER JOIN tv_3play_red tv_3p "
			+ "ON  tv_3p.\"CODI_PRODUCTO\" IN ('128','129') AND kenan.\"KEY_RUT_SIN_DV\" = tv_3p.\"RUT\" "
			+ "WHERE kenan.\"PLAN\" = 'PLAN TELEVISION' AND tv_3p.\"RUT\" IS NULL"};
	
	private final static String[] FILES_CANALES_3P_KALTURA ={"canales_tplay_kaltura_{0}.csv","canales_tplay_no_kaltura_{0}.csv","canales_kaltura_no_tplay_{0}.csv"};
	private final static String[] FILES_CANALES_3P_KENAN ={"canales_tplay_kenan_{0}.csv","canales_tplay_no_kenan_{0}.csv","canales_kenan_no_tplay_{0}.csv"};
	private final static String[] FILES_INTERNET_3P_AAA ={"internet_tplay_aaa_{0}.csv","internet_tplay_no_aaa_{0}.csv","internet_aaa_no_tplay_{0}.csv"};
	private final static String[] FILES_INTERNET_3P_KENAN ={"internet_tplay_kenan_{0}.csv","internet_tplay_no_kenan_{0}.csv","internet_kenan_no_tplay_{0}.csv"};
	private final static String[] FILES_TLF_3P_KENAN ={"tlf_tplay_kenan_{0}.csv","tlf_tplay_no_kenan_{0}.csv","tlf_kenan_no_tplay_{0}.csv"};
	private final static String[] FILES_TLF_3P_OTCAR ={"tlf_tplay_octar_{0}.csv","tlf_tplay_no_octar_{0}.csv","tlf_octar_no_tplay_{0}.csv"};
	private final static String[] FILES_TV_3P_KALTURA ={"tv_tplay_kaltura_{0}.csv","tv_tplay_no_kaltura_{0}.csv","tv_kaltura_no_tplay_{0}.csv"};
	private final static String[] FILES_TV_3P_KENAN ={"tv_tplay_kenan_{0}.csv","tv_tplay_no_kenan_{0}.csv","tv_kenan_no_tplay_{0}.csv"};
	private final static String[] FILES_TV_3P_KENAN_62 ={"tv_tplay_kenan62_{0}.csv","tv_tplay_no_kenan62_{0}.csv","tv_kenan62_no_tplay_{0}.csv"};
	private final static String[] FILES_CANALES_3P_KENAN_62 ={"canales_tplay_kenan62_{0}.csv","canales_tplay_no_kenan62_{0}.csv","canales_kenan62_no_tplay_{0}.csv"};
	private final static String[] FILES_INTERNET_3P_KENAN_62 ={"internet_tplay_kenan62_{0}.csv","internet_tplay_no_kenan62_{0}.csv","internet_kenan62_no_tplay_{0}.csv"};
	private final static String[] FILES_TLF_3P_KENAN_62 ={"tlf_tplay_kenan62_{0}.csv","tlf_tplay_no_kenan62_{0}.csv","tlf_kenan62_no_tplay_{0}.csv"};
	private final static String[] FILES_CROS_INTERNET = {"internet_tplayred_kenan_{0}.csv","internet_tplayred_nokenan_{0}.csv","internet_kenan_no_tplayred_{0}.csv"};
	private final static String[] FILES_CROS_TV = {"tv_tplayred_kenan_{0}.csv","tv_tplayred_nokenan_{0}.csv","tv_kenan_no_tplayred_{0}.csv"};
	private final static String[] FILES_CROS_TLF = {"tlf_tplayred_kenan_{0}.csv","tlf_tplayred_nokenan_{0}.csv","tlf_kenan_no_tplayred_{0}.csv"};
	private final static String[] FILES_CROS_CANALES = {"canales_tplayred_kenan_{0}.csv","canales_tplayred_nokenan_{0}.csv","canales_kenan_no_tplayred_{0}.csv"};
	
	private final static String CARGA_TLF ="copy tlf_3play FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_INT ="copy internet_3play FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_TV_CANALES ="copy tvcanales_3play FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_OCTAR ="copy tlf_otcar FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_KALTURA ="copy tv_kaltura FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_KALTURA_C ="copy canales_kaltura FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_KENAN ="copy facturador_kenan FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_KENAN_62 ="copy facturador_kenan_62 FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_KENAN_C ="copy facturador_kenan_canal FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_AAA ="copy internet_aaa FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_TODO_KAL ="copy todo_kaltura FROM stdin DELIMITER ';' CSV header";
	private final static String CARGA_SERVRET ="copy bdservicios_retirados FROM stdin DELIMITER ';' CSV header";
	
	private final static String TRUNCATE_TLF ="TRUNCATE tlf_3play";
	private final static String TRUNCATE_INT ="TRUNCATE internet_3play";
	private final static String TRUNCATE_TV_CANALES ="TRUNCATE tvcanales_3play";
	private final static String TRUNCATE_OCTAR ="TRUNCATE tlf_otcar";
	private final static String TRUNCATE_KALTURA ="TRUNCATE tv_kaltura";
	private final static String TRUNCATE_KALTURA_C ="TRUNCATE canales_kaltura";
	private final static String TRUNCATE_KENAN ="TRUNCATE facturador_kenan";
	private final static String TRUNCATE_KENAN_62 ="TRUNCATE facturador_kenan_62";
	private final static String TRUNCATE_KENAN_C ="TRUNCATE facturador_kenan_canal";
	private final static String TRUNCATE_AAA ="TRUNCATE internet_aaa";
	private final static String TRUNCATE_TODO_KAL ="TRUNCATE todo_kaltura";
	private final static String TRUNCATE_SERVRET ="TRUNCATE bdservicios_retirados";
	
	private final static String CABECERA_TLF ="CODI_TECNICO;NRUT_CLIENTE;DRUT_CLIENTE;DESC_GLOSAPROD;NMRO_SOLICITUDACT;KEY_ANI";
	private final static String CABECERA_INT ="CODI_TECNICO;NRUT_CLIENTE;DRUT_CLIENTE;DESC_GLOSAPROD;NMRO_SOLICITUDACT";
	private final static String CABECERA_TV_CANALES ="KEY_CANAL;RUT;DV;DETALLE;PRODUCTO;CODI_PRODUCTO;TRYBUY;ESTADO_CLIENTE";
	private final static String CABECERA_OCTAR ="KEY_ANI;REQUEST_ID;RUT;DV;VALOR_DEFECTO";
	private final static String CABECERA_SERVRET ="RUT;COD_SERVICIO_TECNICO;ATRIBUTO_NOM;VALOR_ATRIBUTO;VALOR_ATRIBUTO_1";

	private final static String FILE_TV_CANALES ="tv_tplay_{0}.csv";
	private final static String FILE_TLF = "tlf_tplay_{0}.csv";
	private final static String FILE_INT = "internet_tplay_{0}.csv";
	private final static String FILE_OCTAR ="otcar_{0}.csv";
	private final static String FILE_KALTURA ="tv_base_kaltura_{0}.csv";
	private final static String FILE_KALTURA_C ="canales_kaltura_{0}.csv";
	private final static String FILE_KENAN ="base_kenan_{0}.csv";
	private final static String FILE_KENAN_62 ="base_kenan_62.csv";
	private final static String FILE_KENAN_C ="canales_kenan_{0}.csv";
	private final static String FILE_AAA ="internet_aaa_{0}.csv";
	private final static String FILE_TODO_KAL ="todo_kaltura_{0}.csv";
	private final static String FILE_SERVRET ="servicios_retirados_{0}.csv";
	
	
	private final static String QUERY_TV_CANALES = "SELECT RUT,"
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
			+ "ORDER BY RUT asc";
	private final static String QUERY_TLF = "select d.codi_tecnico, c.nrut_cliente, c.drut_cliente, c.desc_glosaprod, c.nmro_solicitudact "
			+ "from MDP_NEG_CLIENTESACTIVOS C, RMA_NEG_DATOSOTC D "
			+ "where C.nmro_solicitudact = D.corr_solicitud "
			+ "and D.CORR_TIPOSERVICIO = 1 "
			+ "and c.vlor_estadocomp = 1 "
			+ "and c.desc_tiposerv = 'TELEFONIA' "
			+ "and d.codi_tecnico is not null "
			+ "and desc_catego = 'PLAN BASE'";
	private final static String QUERY_INT = "select (select v.id_banda_ancha from MDP_OXC_VENTA V where v.rut_cliente =c.nrut_cliente "
			+ "and v.tipo_termino = 'Terminado' and v.nro_comercial = (select max(f.nro_comercial) from MDP_OXC_VENTA F where f.rut_cliente = v.rut_cliente "
			+ "and f.tipo_termino = 'Terminado' and f.estado_oxc = 40) and v.estado_oxc = 40) as codi_tecnico, c.nrut_cliente, c.drut_cliente, c.desc_glosaprod, c.nmro_solicitudact "
			+ "from MDP_NEG_CLIENTESACTIVOS C where c.vlor_estadocomp = 1 and c.desc_tiposerv = 'INTERNET' and c.desc_catego = 'PLAN BASE'";

	private final static String QUERY_OCTAR = "SELECT o.REQUEST_ID, o.RUT, o.DV, r.VALOR_DEFECTO "
			+ "FROM SP_OXT o INNER JOIN SP_CS_ATRIBUTO_RECURSO r ON r.REQUEST_ID_OXT = o.REQUEST_ID "
			+ "WHERE r.NOMBRE_ATRIBUTO = 'Ani asociado' "
			+ "AND o.tipo_de_servicio = '3 PLAY FIBRA' "
			+ "AND o.tipo_trabajo = 'INSTALACION' "
			+ "AND o.tipo_de_termino_ott = 'Terminado'";
	private final static String QUERY_SERVRET = "select tec.rut, tec.cod_servicio_tecnico, rec.ATRIBUTO_NOM, rec.VALOR_ATRIBUTO, atr.VALOR_ATRIBUTO "
			+ "from servicio_tecnico tec, recurso_atributo rec, atributo_servicio atr "
			+ "where tec.ID_TRANSACCION=rec.ID_TRANSACCION and tec.ID_TRANSACCION = atr.ID_TRANSACCION and tec.serv_gen='3 PLAY FIBRA' "
			+ "and rec.ATRIBUTO_NOM = 'Cuenta PPPe INTE' and atr.ATRIBUTO_NOM ='Tipo_Servicio_SIAC' and tec.estado in ('RET')";
	
	public static String getQueryDescarga(String arg) {
		String toReturn = "";
		if ("INTERNET".equals(arg)){
			toReturn = QUERY_INT;
		} else if ("TLF".equals(arg)){
			toReturn = QUERY_TLF;
		} else if ("TV".equals(arg)){
			toReturn = QUERY_TV_CANALES;
		} else if ("OTCAR".equals(arg)){
			toReturn = QUERY_OCTAR;
		} else if ("SERV_RETIRADOS".equals(arg)){
			toReturn = QUERY_SERVRET;
		}
		return toReturn;
	}
	
	public static String getCabecera(String arg) {
		String toReturn = "";
		if ("INTERNET".equals(arg)){
			toReturn = CABECERA_INT;
		} else if ("TLF".equals(arg)){
			toReturn = CABECERA_TLF;
		} else if ("TV".equals(arg)){
			toReturn = CABECERA_TV_CANALES;
		} else if ("OTCAR".equals(arg)){
			toReturn = CABECERA_OCTAR;
		} else if ("SERV_RETIRADOS".equals(arg)){
			toReturn = CABECERA_SERVRET;
		}
		return toReturn;	
	}
	
	public static String getFile(String arg) {
		String toReturn = "";
		if ("INTERNET".equals(arg)){
			toReturn = FILE_INT;
		} else if ("TLF".equals(arg)){
			toReturn = FILE_TLF;
		} else if ("TV".equals(arg)){
			toReturn = FILE_TV_CANALES;
		} else if ("OTCAR".equals(arg)){
			toReturn = FILE_OCTAR;
		} else if ("KALTURA".equals(arg)){
			toReturn = FILE_KALTURA;
		} else if ("KALTURA_C".equals(arg)){
			toReturn = FILE_KALTURA_C;
		} else if ("KENAN".equals(arg)){
			toReturn = FILE_KENAN;
		}  else if ("KENAN_62".equals(arg)){
			toReturn = FILE_KENAN_62;
		}else if ("KENAN_C".equals(arg)){
			toReturn = FILE_KENAN_C;
		} else if ("AAA".equals(arg)){
			toReturn = FILE_AAA;
		} else if ("TODO_KALTURA".equals(arg)){
			toReturn = FILE_TODO_KAL;
		}  else if ("SERV_RETIRADOS".equals(arg)){
			toReturn = FILE_SERVRET;
		}
		return toReturn;
	}
	
	public static String getQueryCarga(String arg) {
		String toReturn = "";
		if ("INTERNET".equals(arg)){
			toReturn = CARGA_INT;
		} else if ("TLF".equals(arg)){
			toReturn = CARGA_TLF;
		} else if ("TV".equals(arg)){
			toReturn = CARGA_TV_CANALES;
		} else if ("OTCAR".equals(arg)){
			toReturn = CARGA_OCTAR;
		} else if ("KALTURA".equals(arg)){
			toReturn = CARGA_KALTURA;
		} else if ("KALTURA_C".equals(arg)){
			toReturn = CARGA_KALTURA_C;
		} else if ("KENAN".equals(arg)){
			toReturn = CARGA_KENAN;
		} else if ("KENAN_62".equals(arg)){
			toReturn = CARGA_KENAN_62;
		} else if ("KENAN_C".equals(arg)){
			toReturn = CARGA_KENAN_C;
		} else if ("AAA".equals(arg)){
			toReturn = CARGA_AAA;
		} else if ("TODO_KALTURA".equals(arg)){
			toReturn = CARGA_TODO_KAL;
		} else if ("SERV_RETIRADOS".equals(arg)){
			toReturn = CARGA_SERVRET;
		}
		return toReturn;
	}
	
	public static String getQueryTruncate(String arg) {
		String toReturn = "";
		if ("INTERNET".equals(arg)){
			toReturn = TRUNCATE_INT;
		} else if ("TLF".equals(arg)){
			toReturn = TRUNCATE_TLF;
		} else if ("TV".equals(arg)){
			toReturn = TRUNCATE_TV_CANALES;
		} else if ("OTCAR".equals(arg)){
			toReturn = TRUNCATE_OCTAR;
		} else if ("KALTURA".equals(arg)){
			toReturn = TRUNCATE_KALTURA;
		} else if ("KALTURA_C".equals(arg)){
			toReturn = TRUNCATE_KALTURA_C;
		} else if ("KENAN".equals(arg)){
			toReturn = TRUNCATE_KENAN;
		}  else if ("KENAN_62".equals(arg)){
			toReturn = TRUNCATE_KENAN_62;
		} else if ("KENAN_C".equals(arg)){
			toReturn = TRUNCATE_KENAN_C;
		} else if ("AAA".equals(arg)){
			toReturn = TRUNCATE_AAA;
		} else if ("TODO_KALTURA".equals(arg)){
			toReturn = TRUNCATE_TODO_KAL;
		} else if ("SERV_RETIRADOS".equals(arg)){
			toReturn = TRUNCATE_SERVRET;
		}
		return toReturn;
	}

	public static String[] getQueryCruce(String arg) {
		String[] toReturn = {};
		if ("TPLAY_KALTURA".equals(arg)){
			toReturn = QUERYS_TV_3P_KALTURA;
		} else if ("TPLAY_KALTURA_C".equals(arg)){
			toReturn = QUERYS_CANALES_3P_KALTURA;
		} else if ("TPLAY_KENAN_TV".equals(arg)){
			toReturn = QUERYS_TV_3P_KENAN;
		} else if ("TPLAY_KENAN_TLF".equals(arg)){
			toReturn = QUERYS_TLF_3P_KENAN;
		} else if ("TPLAY_KENAN_INT".equals(arg)){
			toReturn = QUERYS_INTERNET_3P_KENAN;
		} else if ("TPLAY_KENAN_C".equals(arg)){
			toReturn = QUERYS_CANALES_3P_KENAN;
		} else if ("TPLAY_AAA".equals(arg)){
			toReturn = QUERYS_INTERNET_3P_AAA;
		} else if ("TPLAY_OTCAR".equals(arg)){
			toReturn = QUERYS_TLF_3P_OTCAR;
		} else if ("TPLAY_KENAN_INT_62".equals(arg)){
			toReturn = QUERYS_INTERNET_3P_KENAN_62;
		} else if ("TPLAY_KENAN_C_62".equals(arg)){
			toReturn = QUERYS_CANALES_3P_KENAN_62;
		} else if ("TPLAY_KENAN_TV_62".equals(arg)){
			toReturn = QUERYS_TV_3P_KENAN_62;
		} else if ("TPLAY_KENAN_TLF_62".equals(arg)){
			toReturn = QUERYS_TLF_3P_KENAN_62;
		} else if ("INTERNET".equals(arg)){
			toReturn = QUERYS_CROS_INTERNET;
		} else if ("TV".equals(arg)){
			toReturn = QUERYS_CROS_TV;
		} else if ("TLF".equals(arg)){
			toReturn = QUERYS_CROS_TLF;
		} else if ("ADICIONALES".equals(arg)){
			toReturn = QUERYS_CROS_CANALES;
		}
		return toReturn;
	}
	
	public static String[] getFileCruce(String arg) {
		String[] toReturn = {};
		if ("TPLAY_KALTURA".equals(arg)){
			toReturn = FILES_TV_3P_KALTURA;
		} else if ("TPLAY_KALTURA_C".equals(arg)){
			toReturn = FILES_CANALES_3P_KALTURA;
		} else if ("TPLAY_KENAN_TV".equals(arg)){
			toReturn = FILES_TV_3P_KENAN;
		} else if ("TPLAY_KENAN_TLF".equals(arg)){
			toReturn = FILES_TLF_3P_KENAN;
		} else if ("TPLAY_KENAN_INT".equals(arg)){
			toReturn = FILES_INTERNET_3P_KENAN;
		} else if ("TPLAY_KENAN_C".equals(arg)){
			toReturn = FILES_CANALES_3P_KENAN;
		} else if ("TPLAY_AAA".equals(arg)){
			toReturn = FILES_INTERNET_3P_AAA;
		} else if ("TPLAY_OTCAR".equals(arg)){
			toReturn = FILES_TLF_3P_OTCAR;
		} else if ("TPLAY_KENAN_INT_62".equals(arg)){
			toReturn = FILES_INTERNET_3P_KENAN_62;
		} else if ("TPLAY_KENAN_C_62".equals(arg)){
			toReturn = FILES_CANALES_3P_KENAN_62;
		} else if ("TPLAY_KENAN_TV_62".equals(arg)){
			toReturn = FILES_TV_3P_KENAN_62;
		} else if ("TPLAY_KENAN_TLF_62".equals(arg)){
			toReturn = FILES_TLF_3P_KENAN_62;
		} else if ("INTERNET".equals(arg)){
			toReturn = FILES_CROS_INTERNET;
		} else if ("TV".equals(arg)){
			toReturn = FILES_CROS_TV;
		} else if ("TLF".equals(arg)){
			toReturn = FILES_CROS_TLF;
		} else if ("ADICIONALES".equals(arg)){
			toReturn = FILES_CROS_CANALES;
		}
		return toReturn;
	}
		
}
