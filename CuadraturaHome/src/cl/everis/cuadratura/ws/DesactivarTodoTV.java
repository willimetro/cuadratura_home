package cl.everis.cuadratura.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private final static String QUERY_TODO = "SELECT tk.\"HOUSE_HOLD_ID\", tk.\"MODULE_ID\" FROM todo_kaltura tk WHERE tk.\"RUT\"=";

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
					.escribirTraza("INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
							+ ";CODIGO_RESPONSE: " + resp.getResponse().getHeaderOut().getCodigo() + ";DESCRIPCION: "
							+ resp.getResponse().getHeaderOut().getDescripcion());
		} catch (MalformedURLException e1) {
			LogEliminacion.escribirTraza(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
							+ ";CODIGO_RESPONSE: MALFORMEDURLEXCEPTION;DESCRIPCION: URL ERRONEA");
			e1.printStackTrace();
		} catch (ServiceException e1) {
			LogEliminacion.escribirTraza(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
							+ ";CODIGO_RESPONSE: SERVICEEXCEPTION;DESCRIPCION: SERVICIO CON ERROR");
			e1.printStackTrace();
		} catch (DesactivarVasYCanalPremiumFaultType e) {
			LogEliminacion.escribirTraza(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
							+ ";CODIGO_RESPONSE: DESACTIVARCANALESPREMIUMTYPE;DESCRIPCION: SERVICIO CON ERROR");
			e.printStackTrace();
		} catch (RemoteException e) {
			LogEliminacion.escribirTraza(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
							+ ";CODIGO_RESPONSE: REMOTEEXCEPTION;DESCRIPCION: ERROR REMOTO");
			e.printStackTrace();
		} catch (Exception e) {
			LogEliminacion.escribirTraza(
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

		conn = ConnectionCuadraturaBD.getConnTPlay();
		try {
			pstmt = conn.prepareStatement(QUERY_VALIDA_KENAN);
			pstmt.setString(1, fileCorteCanalesRow.getRutSinDV());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileCorteCanalesRow.setCodiServicio(rs.getString("CODI_SERVICIO"));
			} else {
				fileCorteCanalesRow.setCodiServicio("");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fileCorteCanalesRow;
	}
}
