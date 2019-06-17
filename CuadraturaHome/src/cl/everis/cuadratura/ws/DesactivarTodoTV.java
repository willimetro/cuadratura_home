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
 * @author jarenass
 *
 */
public class DesactivarTodoTV {

	private final static String QUERY_VALIDA_KENAN = "SELECT kn.\"ESTADO\" FROM facturador_kenan kn "
			+ "WHERE kn.\"PLAN\"='PLAN TELEVISION'AND kn.\"RUT_CLIENTE\"=?";
	private final static String QUERY_TODO = "SELECT tk.\"HOUSE_HOLD_ID\", tk.\"MODULE_ID\" FROM todo_kaltura tk WHERE tk.\"RUT\"= ?";

	/**
	 * 
	 * @param fileCorteCanalesRow
	 * @return
	 */
	public DesactivarCanalesResponseOBJ desactivarModulo(FileCorteCanalesRow fileCorteCanalesRow) {

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
			.escribirTrazaTv("INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
			+ ";CODIGO_RESPONSE: " + resp.getResponse().getHeaderOut().getCodigo() + ";DESCRIPCION: "
			+ resp.getResponse().getHeaderOut().getDescripcion());
		} catch (MalformedURLException e1) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: MALFORMEDURLEXCEPTION;DESCRIPCION: URL ERRONEA");
			e1.printStackTrace();
		} catch (ServiceException e1) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: SERVICEEXCEPTION;DESCRIPCION: SERVICIO CON ERROR");
			e1.printStackTrace();
		} catch (DesactivarVasYCanalPremiumFaultType e) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: DESACTIVARCANALESPREMIUMTYPE;DESCRIPCION: SERVICIO CON ERROR");
			e.printStackTrace();
		} catch (RemoteException e) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: REMOTEEXCEPTION;DESCRIPCION: ERROR REMOTO");
			e.printStackTrace();
		} catch (Exception e) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: ERROR GENERAL;DESCRIPCION: ERROR GENERAL");
			e.printStackTrace();
		}

		return desactivarCanalesResponseOBJ;
	}

	/**
	 * 
	 * @param rut
	 * @return
	 */
	public List<FileCorteCanalesRow> validaFacturado(String rut) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean facturado = false;
		List<FileCorteCanalesRow> serviciosTV = null;
		FileCorteCanalesRow servicio = null;
		conn = ConnectionCuadraturaBD.getLocalConn();
		try {
			pstmt = conn.prepareStatement(QUERY_VALIDA_KENAN);
			pstmt.setString(1, rut);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				facturado = (!"Retirado".equals(rs.getString("ESTADO")));
				if(facturado){
					break;
				}
			}
			if (!facturado){
				serviciosTV = new ArrayList<FileCorteCanalesRow>();
				pstmt = conn.prepareStatement(QUERY_TODO);
				pstmt.setString(1, rut);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					servicio = new FileCorteCanalesRow();
					servicio.setRutConDv(rut);
					servicio.setCodiServicio(rs.getString("HOUSE_HOLD_ID"));
					servicio.setCodCanal(rs.getString("MODULE_ID"));
					serviciosTV.add(servicio);
				} if (serviciosTV.isEmpty()) {
					LogEliminacion.escribirTrazaTv("INFO;" + rut + ";TV"
							+ ";CODIGO_RESPONSE: 1001;DESCRIPCION: "
							+ "NO SE ENCUENTRA RUT EN COMPENDIO KALTURA");
				}
				
			} else {
				LogEliminacion.escribirTrazaTv("INFO;" + rut + ";TV"
						+ ";CODIGO_RESPONSE: 1000;DESCRIPCION: "
						+ "SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serviciosTV;
	}
}
