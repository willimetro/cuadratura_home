package cl.everis.cuadratura.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremium;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumBindingStub;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumLocator;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumPortType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.fault.DesactivarVasYCanalPremiumFaultType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.request.DesactivarVasYCanalPremiumRequestType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.request.RequestType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.response.DesactivarVasYCanalPremiumResponseType;

import cl.everis.cuadratura.bd.conn.ConnectionCuadraturaBD;
import cl.everis.cuadratura.obj.DesactivarCanalesResponseOBJ;
import cl.everis.cuadratura.obj.FileCorteCanalesRow;
import cl.everis.cuadratura.util.LogEliminacion;

/**
 * 
 * @author wugaldeq
 *
 */
public class DesactivarCanales {

	private final static String QUERY_HOUSE_HOLD_ID =  "SELECT tk.\"HOUSE_HOLD_ID\" FROM todo_kaltura tk WHERE tk.\"RUT\"= ? "
			+ "AND tk.\"MODULE_ID\" = ?";

	private final static String QUERY_VALIDA_KENAN = "select * from facturador_kenan_canal fcan where fcan.\"ESTADO\" in ('Facturado','Nuevo') "
			+ "and fcan.\"RUT_CLIENTE\" = ? and fcan.\"CODIGO_TPLAY\" = ?";

	/**	private final static String QUERY = "select d.codi_servicio "
			+ "from MDP_NEG_CLIENTESACTIVOS C, RMA_NEG_DATOSOTC D " + "where C.nmro_solicitudact = D.corr_solicitud "
			+ "and D.CORR_TIPOSERVICIO = 2 " + "and c.vlor_estadocomp = 1 " + "and c.desc_tiposerv = 'TELEVISION' "
			+ "and desc_catego = 'PLAN BASE' " + "and NRUT_CLIENTE = ?";**/
	/**
	 * 
	 * @param fileCorteCanalesRow
	 * @return
	 */
	public DesactivarCanalesResponseOBJ desactivarCanalPremium(FileCorteCanalesRow fileCorteCanalesRow) {

		DesactivarVasYCanalPremiumRequestType type = new DesactivarVasYCanalPremiumRequestType();
		RequestType rq = new RequestType();
		type.setRequest(rq);
		type.getRequest().setProductId("");
		type.getRequest().setUserId("");
		type.getRequest().setHouseholdId(null != fileCorteCanalesRow.getCodiServicio() ? fileCorteCanalesRow.getCodiServicio() : "");
		type.getRequest().setProductId(fileCorteCanalesRow.getCodCanal());
		type.getRequest().setUserId(fileCorteCanalesRow.getRutConDv());
		DesactivarCanalesResponseOBJ desactivarCanalesResponseOBJ = null;

		try {
			DesactivarVasYCanalPremiumLocator desactivarVasYCanalPremiumLocator = new DesactivarVasYCanalPremiumLocator();
			URL url = new URL(
					"http://esb.entel.cl:80/provision/orderingserv/orqordenservicio/ose_n_px_desactivarvasycanalpremiumps");
			DesactivarVasYCanalPremiumPortType desactivarVasYCanalPremium = desactivarVasYCanalPremiumLocator
					.getDesactivarVasYCanalPremiumPort(url);
			DesactivarVasYCanalPremiumBindingStub bindingStub = (DesactivarVasYCanalPremiumBindingStub) desactivarVasYCanalPremium;
			bindingStub.setTimeout(10000);
			DesactivarVasYCanalPremiumResponseType resp = bindingStub.desactivarVasYCanalPremium(type);
			desactivarCanalesResponseOBJ = new DesactivarCanalesResponseOBJ();
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

		return desactivarCanalesResponseOBJ;
	}

	/**
	 * 
	 * @param fileCorteCanalesRow
	 * @return
	 */
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

	/**
	 * 
	 * @param rut
	 * @return
	 */
	public boolean validaFacturado(FileCorteCanalesRow reg) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean facturado = false;
		conn = ConnectionCuadraturaBD.getLocalConn();
		try {
			pstmt = conn.prepareStatement(QUERY_VALIDA_KENAN);
			pstmt.setString(1, reg.getRutConDv());
			pstmt.setString(2, reg.getCodCanal());
			rs = pstmt.executeQuery();
			if ( facturado = rs.next()) {
				LogEliminacion.escribirTrazaCanales("INFO;" + reg.getRutConDv() + ";"
						+ reg.getCodCanal()
						+ ";CODIGO_RESPONSE: 1000;DESCRIPCION: "
						+ "SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY");
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
		return facturado;
	}

}
