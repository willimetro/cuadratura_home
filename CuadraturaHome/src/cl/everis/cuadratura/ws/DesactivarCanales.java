package cl.everis.cuadratura.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.rpc.ServiceException;

import com.esa.www.Provision.OrderingServ.N.ActivarVasYCanalPremium.ActivarVasYCanalPremiumBindingQSServiceLocator;
import com.esa.www.Provision.OrderingServ.N.ActivarVasYCanalPremium.ActivarVasYCanalPremiumBindingStub;
import com.esa.www.Provision.OrderingServ.N.ActivarVasYCanalPremium.ActivarVasYCanalPremiumPortType;
import com.esa.www.Provision.OrderingServ.N.ActivarVasYCanalPremium.request.ActivarVasYCanalPremiumRequestType;
import com.esa.www.Provision.OrderingServ.N.ActivarVasYCanalPremium.response.ActivarVasYCanalPremiumResponseType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumBindingStub;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumLocator;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumPortType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.fault.DesactivarVasYCanalPremiumFaultType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.request.DesactivarVasYCanalPremiumRequestType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.request.RequestType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.response.DesactivarVasYCanalPremiumResponseType;

import cl.everis.cuadratura.bd.conn.ConnectionCuadraturaBD;
import cl.everis.cuadratura.obj.ActivarDesactivarCanalesResponseOBJ;
import cl.everis.cuadratura.obj.FileCorteCanalesRow;
import cl.everis.cuadratura.obj.RespValidacionesOBJ;
import cl.everis.cuadratura.util.LogEliminacion;

public class DesactivarCanales {

	private final static String QUERY_HOUSE_HOLD_ID =  "SELECT tk.\"HOUSE_HOLD_ID\" FROM todo_kaltura tk WHERE tk.\"RUT\"= ? "
			+ "AND tk.\"MODULE_ID\" = ?";
	
	private final static String QUERY_EXISTE =  "SELECT COUNT(*) FROM todo_kaltura tk WHERE tk.\"RUT\"= ? AND tk.\"MODULE_ID\" = ?";

	private final static String QUERY_VALIDA_KENAN = "select * from facturador_kenan_canal fcan where fcan.\"ESTADO\" in ('Facturado','Nuevo') "
			+ "and fcan.\"RUT_CLIENTE\" = ? and fcan.\"CODIGO_TPLAY\" = ?";

	private final static String QUERY_TPLAY_CDF = "select * from tvcanales_3play tp where tp.\"RUT\" = ? AND tp.\"PRODUCTO\" LIKE ('%CDF%')";

	private final static String QUERY_KENAN_CDF = "SELECT * FROM facturador_kenan_canal fcan WHERE fcan.\"RUT_CLIENTE\" = ? AND fcan.\"CANAL\" "
			+ "LIKE ('%CDF%') AND fcan.\"ESTADO\" in ('Facturado','Nuevo')";

	private ActivarVasYCanalPremiumBindingQSServiceLocator activarVasYCanalPremiumLocator;
	private ActivarVasYCanalPremiumPortType activarVasYCanalPremium;
	private ActivarVasYCanalPremiumBindingStub bindingStub;
	private DesactivarVasYCanalPremiumLocator desactivarVasYCanalPremiumLocator;
	private DesactivarVasYCanalPremiumPortType desactivarVasYCanalPremium;
	private DesactivarVasYCanalPremiumBindingStub binding;

	public ActivarDesactivarCanalesResponseOBJ activarCanalPremium(FileCorteCanalesRow fileCorteCanalesRow) {

		ActivarVasYCanalPremiumRequestType type = new ActivarVasYCanalPremiumRequestType();
		com.esa.www.Provision.OrderingServ.N.ActivarVasYCanalPremium.request.RequestType rq = 
				new com.esa.www.Provision.OrderingServ.N.ActivarVasYCanalPremium.request.RequestType();
		type.setRequest(rq);
		type.getRequest().setProductType("CANALPREMIUM");
		type.getRequest().setHouseholdId(null != fileCorteCanalesRow.getCodiServicio() ? fileCorteCanalesRow.getCodiServicio() : "");
		type.getRequest().setProductId(fileCorteCanalesRow.getCodCanal());
		type.getRequest().setUserId(fileCorteCanalesRow.getRutConDv());
		ActivarDesactivarCanalesResponseOBJ activarCanalesResponseOBJ = null;

		try {
			
			URL url = new URL(
					"http://esb.entel.cl/provision/orderingserv/orqordenservicio/ose_n_px_activarvasycanalpremiumps");
			
			if (null==activarVasYCanalPremiumLocator) {
				activarVasYCanalPremiumLocator = new ActivarVasYCanalPremiumBindingQSServiceLocator();
			}
			if (null==activarVasYCanalPremium) {
				activarVasYCanalPremium = activarVasYCanalPremiumLocator
						.getActivarVasYCanalPremiumBindingQSPort(url);
			}
			if (null==bindingStub) {
				bindingStub = (ActivarVasYCanalPremiumBindingStub) activarVasYCanalPremium;
			}
			bindingStub.setTimeout(10000);
			ActivarVasYCanalPremiumResponseType resp = bindingStub.activarVasYCanalPremium(type);
			activarCanalesResponseOBJ = new ActivarDesactivarCanalesResponseOBJ();
			activarCanalesResponseOBJ.setCodResponse(resp.getResponse().getHeaderOut().getCodigo());
			activarCanalesResponseOBJ.setDescripcion(resp.getResponse().getHeaderOut().getDescripcion());
			LogEliminacion
			.escribirTrazaCanales("INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
			+ ";CODIGO_RESPONSE: " + resp.getResponse().getHeaderOut().getCodigo() + ";DESCRIPCION: "
			+ resp.getResponse().getHeaderOut().getDescripcion());
		} catch (MalformedURLException e1) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: MALFORMEDURLEXCEPTION;DESCRIPCION: URL ERRONEA");
			e1.printStackTrace();
		} catch (ServiceException e1) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: SERVICEEXCEPTION;DESCRIPCION: SERVICIO CON ERROR");
			e1.printStackTrace();
		} catch (DesactivarVasYCanalPremiumFaultType e) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: DESACTIVARCANALESPREMIUMTYPE;DESCRIPCION: SERVICIO CON ERROR");
			e.printStackTrace();
		} catch (RemoteException e) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: REMOTEEXCEPTION;DESCRIPCION: ERROR REMOTO");
			e.printStackTrace();
		} catch (Exception e) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: ERROR GENERAL;DESCRIPCION: ERROR GENERAL");
			e.printStackTrace();
		}

		return activarCanalesResponseOBJ;
	}

	public ActivarDesactivarCanalesResponseOBJ desactivarCanalPremium(FileCorteCanalesRow fileCorteCanalesRow) {

		DesactivarVasYCanalPremiumRequestType type = new DesactivarVasYCanalPremiumRequestType();
		RequestType rq = new RequestType();
		type.setRequest(rq);
		type.getRequest().setHouseholdId(null != fileCorteCanalesRow.getCodiServicio() ? fileCorteCanalesRow.getCodiServicio() : "");
		type.getRequest().setProductId(fileCorteCanalesRow.getCodCanal());
		type.getRequest().setUserId(fileCorteCanalesRow.getRutConDv());
		ActivarDesactivarCanalesResponseOBJ desactivarCanalesResponseOBJ = new ActivarDesactivarCanalesResponseOBJ();

		try {
			URL url = new URL(
					"http://esb.entel.cl:80/provision/orderingserv/orqordenservicio/ose_n_px_desactivarvasycanalpremiumps");
			
			if (null==desactivarVasYCanalPremiumLocator) {
				desactivarVasYCanalPremiumLocator = new DesactivarVasYCanalPremiumLocator();
			}
			if (null==desactivarVasYCanalPremium) {
				desactivarVasYCanalPremium = desactivarVasYCanalPremiumLocator
					.getDesactivarVasYCanalPremiumPort(url);
			}
			if (null==binding) {
				binding = (DesactivarVasYCanalPremiumBindingStub) desactivarVasYCanalPremium;
			}
			binding.setTimeout(10000);
			DesactivarVasYCanalPremiumResponseType resp = binding.desactivarVasYCanalPremium(type);
			desactivarCanalesResponseOBJ = new ActivarDesactivarCanalesResponseOBJ();
			desactivarCanalesResponseOBJ.setCodResponse(resp.getResponse().getHeaderOut().getCodigo());
			desactivarCanalesResponseOBJ.setDescripcion(resp.getResponse().getHeaderOut().getDescripcion());
			LogEliminacion
			.escribirTrazaCanales("INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
			+ ";CODIGO_RESPONSE: " + resp.getResponse().getHeaderOut().getCodigo() + ";DESCRIPCION: "
			+ resp.getResponse().getHeaderOut().getDescripcion());
		} catch (MalformedURLException e1) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: MALFORMEDURLEXCEPTION;DESCRIPCION: URL ERRONEA");
			desactivarCanalesResponseOBJ.setCodResponse("MALFORMEDURLEXCEPTION");
			desactivarCanalesResponseOBJ.setDescripcion("URL ERRONEA");
			e1.printStackTrace();
		} catch (ServiceException e1) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: SERVICEEXCEPTION;DESCRIPCION: SERVICIO CON ERROR");
			desactivarCanalesResponseOBJ.setCodResponse("SERVICEEXCEPTION");
			desactivarCanalesResponseOBJ.setDescripcion("SERVICIO CON ERROR");
			e1.printStackTrace();
		} catch (DesactivarVasYCanalPremiumFaultType e) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: DESACTIVARCANALESPREMIUMTYPE;DESCRIPCION: SERVICIO CON ERROR");
			desactivarCanalesResponseOBJ.setCodResponse("DESACTIVARCANALESPREMIUMTYPE");
			desactivarCanalesResponseOBJ.setDescripcion("SERVICIO CON ERROR");
			e.printStackTrace();
		} catch (RemoteException e) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: REMOTEEXCEPTION;DESCRIPCION: ERROR REMOTO");
			desactivarCanalesResponseOBJ.setCodResponse("REMOTEEXCEPTION");
			desactivarCanalesResponseOBJ.setDescripcion("ERROR REMOTO");
			e.printStackTrace();
		} catch (Exception e) {
			LogEliminacion.escribirTrazaCanales(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: ERROR GENERAL;DESCRIPCION: ERROR GENERAL");
			desactivarCanalesResponseOBJ.setCodResponse("ERROR GENERAL");
			desactivarCanalesResponseOBJ.setDescripcion("ERROR GENERAL");
			e.printStackTrace();
		}

		return desactivarCanalesResponseOBJ;
	}

	public FileCorteCanalesRow getCodServicioCanalesPremium(FileCorteCanalesRow fileCorteCanalesRow) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConnectionCuadraturaBD.getLocalConn();
		try {
			pstmt = conn.prepareStatement(QUERY_HOUSE_HOLD_ID);
			pstmt.setString(1, fileCorteCanalesRow.getRutConDv());
			pstmt.setString(2, fileCorteCanalesRow.getCodCanal());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileCorteCanalesRow.setCodiServicio(rs.getString("HOUSE_HOLD_ID"));
			} else {
				fileCorteCanalesRow.setCodiServicio("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return fileCorteCanalesRow;
	}

	public RespValidacionesOBJ validaFacturado(FileCorteCanalesRow reg) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = ConnectionCuadraturaBD.getLocalConn();
		ActivarDesactivarCanalesResponseOBJ resp = new ActivarDesactivarCanalesResponseOBJ();
		RespValidacionesOBJ validToDelete = new RespValidacionesOBJ();
		validToDelete.setToDelete(true);
		try {
			pstmt = conn.prepareStatement(QUERY_VALIDA_KENAN);
			pstmt.setString(1, reg.getRutConDv());
			pstmt.setString(2, reg.getCodCanal());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
						+ reg.getCodCanal()
						+ ";CODIGO_RESPONSE: 1000;DESCRIPCION: "
						+ "SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY");
				resp.setCodResponse("1000");
				resp.setDescripcion("SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY");
				validToDelete.setResp(resp);
				validToDelete.setToDelete(false);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null!=rs){
					rs.close();
				}
				if (null!=pstmt){
					pstmt.close();
				}
				if (null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return validToDelete;
	}

	public RespValidacionesOBJ validaCDF(FileCorteCanalesRow reg) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		String codiKal = reg.getCodCanal();
		String codiTplay = null;
		String codiKen = null;
		int dobleKenan = 0;
		RespValidacionesOBJ objDeleteCDF = new RespValidacionesOBJ();
		objDeleteCDF.setToDelete(false);
		ActivarDesactivarCanalesResponseOBJ resp = new ActivarDesactivarCanalesResponseOBJ();
		conn = ConnectionCuadraturaBD.getLocalConn();
		try {
			pstmt1 = conn.prepareStatement(QUERY_TPLAY_CDF);
			pstmt1.setString(1, reg.getRutSinDV());
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				codiTplay = rs1.getString("CODI_PRODUCTO");
			}
			pstmt2 = conn.prepareStatement(QUERY_KENAN_CDF);
			pstmt2.setString(1, reg.getRutConDv());
			rs2 = pstmt2.executeQuery();
			while (rs2.next()){
				dobleKenan++;
				codiKen = rs2.getString("CODIGO_TPLAY");
			}
			if(dobleKenan > 1){
				//				CASO EN QUE PRESENTA MAS DE UN RESULTADO PARA CANALES CDF EN KENAN
				LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
						+ reg.getCodCanal()
						+ ";CODIGO_RESPONSE: 1001;DESCRIPCION: "
						+ "CLIENTE CDF PRESENTA MAS DE UNA FACTURACIÓN");
				resp.setCodResponse("1001");
				resp.setDescripcion("CLIENTE CDF PRESENTA MAS DE UNA FACTURACIÓN");			
			} else if(null == codiTplay){
				if(null==codiKen){
					//				CASO EN QUE NO SE ENCUENTRA EN 3 PLAY Y KENAN - ELIMINAR EN KAL
					objDeleteCDF.setToDelete(true);

				} else if(null!=codiKal && !codiKal.equals(codiKen)){
					//				CASO EN QUE TIENE DISTINTO CDF EN KALTURA Y KENAN Y NO ESTA EN 3PLAY
					LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
							+ reg.getCodCanal()
							+ ";CODIGO_RESPONSE: 1002;DESCRIPCION: "
							+ "CLIENTE CDF PRESENTA DISTINTO CODIGO EN KENAN Y NO SE ENCUENTRA EN 3PLAY");
					resp.setCodResponse("1002");
					resp.setDescripcion("CLIENTE CDF PRESENTA DISTINTO CODIGO EN KENAN Y NO SE ENCUENTRA EN 3PLAY");				
				} else if(null!=codiKal && codiKal.equals(codiKen)){
					//				CASO EN QUE TIENE IGUAL CDF EN KALTURA Y KENAN Y NO ESTA EN 3PLAY
					LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
							+ reg.getCodCanal()
							+ ";CODIGO_RESPONSE: 1003;DESCRIPCION: "
							+ "CLIENTE CDF SE ENCUENTRA EN KENAN Y NO EN 3 PLAY");
					resp.setCodResponse("1003");
					resp.setDescripcion("CLIENTE CDF SE ENCUENTRA EN KENAN Y NO EN 3 PLAY");
				}	
			} else {		
				if(null==codiKen){
					//				CASO EN QUE NO SE ENCUENTRA EN KENAN Y ES DISTINTO EN 3PLAY - ELIMINAR EN KAL E INDICAR REGULARIZAR 3 PLAY
					reg = getCodServicioCanalesPremium(reg);
					resp = desactivarCanalPremium(reg);
					if (!"0000".equals(resp.getCodResponse())){
						LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
								+ reg.getCodCanal()
								+ ";CODIGO_RESPONSE: 1004;DESCRIPCION: "
								+ "CLIENTE CDF NO SE ENCUENTRA EN KENAN, SE DEBE ELIMINAR EN 3PLAY Y FALLA ELIMINACION EN KALTURA CON CODIGO: "
								+ resp.getCodResponse());
						resp.setCodResponse("1004");
						resp.setDescripcion("CLIENTE CDF NO SE ENCUENTRA EN KENAN, SE DEBE ELIMINAR EN 3PLAY Y FALLA ELIMINACION EN KALTURA CON CODIGO: "
								+ resp.getCodResponse());
					} else {
						LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
								+ reg.getCodCanal()
								+ ";CODIGO_RESPONSE: 1005;DESCRIPCION: "
								+ "CLIENTE CDF NO SE ENCUENTRA EN KENAN, DEBE ELIMINAR EN 3PLAY Y EN KALTURA SE OBTIENE RESPUESTA DE ELIMINACION: "
								+ resp.getDescripcion());
						resp.setCodResponse("1005");
						resp.setDescripcion("CLIENTE CDF NO SE ENCUENTRA EN KENAN, DEBE ELIMINAR EN 3PLAY Y EN KALTURA SE OBTIENE RESPUESTA DE ELIMINACION: "
								+ resp.getDescripcion());
					}
				} else if(null!=codiKal && !codiKal.equals(codiKen)){
					//				CASO EN QUE TIENE DISTINTO CDF EN KALTURA QUE EN KENAN Y 3PLAY - ELIMINAR INCORRECTO Y CREAR CORRECTO
					reg = getCodServicioCanalesPremium(reg);
					resp = desactivarCanalPremium(reg);
					String codiEliminacion = resp.getCodResponse();
					if (!"0000".equals(codiEliminacion)){
						LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
								+ reg.getCodCanal()
								+ ";CODIGO_RESPONSE: 1006;DESCRIPCION: "
								+ "NO SE PUDO ELIMINAR CANAL "+reg.getCodCanal()+" PARA VALIDAR "+codiTplay+", LA DESCRIPCION DE ELIMINACION ES: "
								+ resp.getDescripcion());
						resp.setCodResponse("1006");
						resp.setDescripcion("NO SE PUDO ELIMINAR CANAL "+reg.getCodCanal()+" PARA VALIDAR "+codiTplay+", LA DESCRIPCION DE ELIMINACION ES: "
								+ resp.getDescripcion());
					} else {
						pstmt3 = conn.prepareStatement(QUERY_EXISTE);
						pstmt3.setString(1, reg.getRutConDv());
						pstmt3.setString(2, codiTplay);
						rs3 = pstmt3.executeQuery();
						if (rs3.next()){
							LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
									+ reg.getCodCanal()
									+ ";CODIGO_RESPONSE: 1009;DESCRIPCION: "
									+ "SE ELIMINO CANAL "+reg.getCodCanal()+" Y SE ENCUENTRA QUE CANAL "+codiTplay+" YA EXISTE EN KALTURA, LA DESCRIPCION DE ELIMINACION ES: "
									+ resp.getDescripcion());	
							resp.setCodResponse("1009");
							resp.setDescripcion("SE ELIMINO CANAL "+reg.getCodCanal()+" Y SE ENCUENTRA QUE CANAL "+codiTplay+" YA EXISTE EN KALTURA, LA DESCRIPCION DE ELIMINACION ES: "
									+ resp.getDescripcion());
						} else {
							FileCorteCanalesRow regCrea = new FileCorteCanalesRow();
							regCrea.setCodCanal(codiTplay);
							regCrea.setCodiServicio(reg.getCodiServicio());
							regCrea.setRutConDv(reg.getRutConDv());
							resp = activarCanalPremium(regCrea);
							if (!"0000".equals(resp.getCodResponse())){
								LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
										+ reg.getCodCanal()
										+ ";CODIGO_RESPONSE: 1007;DESCRIPCION: "
										+ "SE ELIMINO CANAL "+reg.getCodCanal()+" Y SE OBTUVO ERROR CREANDO CANAL "+codiTplay+" CON DESCRIPCION: "
										+ resp.getDescripcion());	
								resp.setCodResponse("1007");
								resp.setDescripcion("SE ELIMINO CANAL "+reg.getCodCanal()+" Y SE OBTUVO ERROR CREANDO CANAL "+codiTplay+" CON DESCRIPCION: "
										+ resp.getDescripcion());
							} else {
								LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
										+ reg.getCodCanal()
										+ ";CODIGO_RESPONSE: 1008;DESCRIPCION: "
										+ "SE ELIMINO CANAL "+reg.getCodCanal()+" Y SE CREO " +codiTplay+" CON DESCRIPCION: "
										+ resp.getDescripcion());
								resp.setCodResponse("1008");
								resp.setDescripcion("SE ELIMINO CANAL "+reg.getCodCanal()+" Y SE CREO " +codiTplay+" CON DESCRIPCION: "
										+ resp.getDescripcion());
							}
						}
					}	
				} else if(null!=codiKal && codiKal.equals(codiKen)){
					//				CASO EN QUE TIENE IGUAL CDF EN KALTURA Y KENAN Y NO EN 3PLAY
					LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
							+ reg.getCodCanal()
							+ ";CODIGO_RESPONSE: 1010;DESCRIPCION: "
							+ "CLIENTE CDF SE ENCUENTRA EN KENAN Y ES DIFERENTE EN 3 PLAY");
					resp.setCodResponse("1010");
					resp.setDescripcion("CLIENTE CDF SE ENCUENTRA EN KENAN Y ES DIFERENTE EN 3 PLAY");
				}				
			}
			objDeleteCDF.setResp(resp);			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null!=rs1){
					rs1.close();
				}
				if (null!=pstmt1){
					pstmt1.close();
				}
				if (null!=rs2){
					rs2.close();
				}
				if (null!=pstmt2){
					pstmt2.close();
				}
				if (null!=rs3){
					rs3.close();
				}
				if (null!=pstmt3){
					pstmt3.close();
				}
				if (null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objDeleteCDF;
	}

}
