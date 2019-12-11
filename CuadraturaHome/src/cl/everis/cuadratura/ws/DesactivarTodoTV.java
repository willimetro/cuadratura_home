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
import cl.everis.cuadratura.obj.ActivarDesactivarCanalesResponseOBJ;
import cl.everis.cuadratura.obj.FileCorteCanalesRow;
import cl.everis.cuadratura.util.LogEliminacion;

/**
 * 
 * @author jarenass
 *
 */
public class DesactivarTodoTV {

	private final static String QUERY_VALIDA_KENAN = "SELECT kn.\"ESTADO\" FROM facturador_kenan kn "
			+ "WHERE kn.\"PLAN\"='PLAN TELEVISION'AND kn.\"ESTADO\" in ('Nuevo','Facturado') AND kn.\"RUT_CLIENTE\"=?";
	private final static String QUERY_TODO = "SELECT tk.\"HOUSE_HOLD_ID\", tk.\"MODULE_ID\" FROM todo_kaltura tk WHERE tk.\"RUT\"= ?";
	private DesactivarVasYCanalPremiumLocator desactivarVasYCanalPremiumLocator;
	private DesactivarVasYCanalPremiumPortType desactivarVasYCanalPremium;
	private DesactivarVasYCanalPremiumBindingStub bindingStub;
	/**
	 * 
	 * @param fileCorteCanalesRow
	 * @return
	 */
	public ActivarDesactivarCanalesResponseOBJ desactivarModulo(FileCorteCanalesRow fileCorteCanalesRow) {

		DesactivarVasYCanalPremiumRequestType type = new DesactivarVasYCanalPremiumRequestType();
		RequestType rq = new RequestType();
		type.setRequest(rq);
		type.getRequest().setProductId("");
		type.getRequest().setUserId("");
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
			if (null==bindingStub) {
				bindingStub = (DesactivarVasYCanalPremiumBindingStub) desactivarVasYCanalPremium;
			}
			bindingStub.setTimeout(10000);
			DesactivarVasYCanalPremiumResponseType resp = bindingStub.desactivarVasYCanalPremium(type);
			/**
			DesactivarVasYCanalPremiumResponseType resp = new ActivarVasYCanalPremiumResponseType();
			ResponseType rs = new ResponseType(new HeaderOutType("0000", "PRUEBA EXITOSA", "", ""));
			resp.setResponse(rs);
			**/
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
			desactivarCanalesResponseOBJ.setCodResponse("MALFORMEDURLEXCEPTION");
			desactivarCanalesResponseOBJ.setDescripcion("URL ERRONEA");
			e1.printStackTrace();
		} catch (ServiceException e1) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: SERVICEEXCEPTION;DESCRIPCION: SERVICIO CON ERROR");
			desactivarCanalesResponseOBJ.setCodResponse("SERVICEEXCEPTION");
			desactivarCanalesResponseOBJ.setDescripcion("SERVICIO CON ERROR");
			e1.printStackTrace();
		} /****/catch (DesactivarVasYCanalPremiumFaultType e) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: DESACTIVARCANALESPREMIUMTYPE;DESCRIPCION: SERVICIO CON ERROR");
			desactivarCanalesResponseOBJ.setCodResponse("DESACTIVARCANALESPREMIUMTYPE");
			desactivarCanalesResponseOBJ.setDescripcion("SERVICIO CON ERROR");
			e.printStackTrace();
		} catch (RemoteException e) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: REMOTEEXCEPTION;DESCRIPCION: ERROR REMOTO");
			desactivarCanalesResponseOBJ.setCodResponse("REMOTEEXCEPTION");
			desactivarCanalesResponseOBJ.setDescripcion("ERROR REMOTO");
			e.printStackTrace();
		} /****/catch (Exception e) {
			LogEliminacion.escribirTrazaTv(
					"ERROR;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
					+ ";CODIGO_RESPONSE: ERROR GENERAL;DESCRIPCION: ERROR GENERAL");
			desactivarCanalesResponseOBJ.setCodResponse("ERROR GENERAL");
			desactivarCanalesResponseOBJ.setDescripcion("ERROR GENERAL");
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
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		boolean facturado = false;
		List<FileCorteCanalesRow> serviciosTV = null;
		FileCorteCanalesRow servicio = null;
		conn = ConnectionCuadraturaBD.getLocalConn();
		try {
			pstmt1 = conn.prepareStatement(QUERY_VALIDA_KENAN);
			pstmt1.setString(1, rut);
			rs1 = pstmt1.executeQuery();
			facturado = rs1.next();
			if (!facturado){
				serviciosTV = new ArrayList<FileCorteCanalesRow>();
				pstmt2 = conn.prepareStatement(QUERY_TODO);
				pstmt2.setString(1, rut);
				rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					servicio = new FileCorteCanalesRow();
					servicio.setRutConDv(rut);
					servicio.setCodiServicio(rs2.getString("HOUSE_HOLD_ID"));
					servicio.setCodCanal(rs2.getString("MODULE_ID"));
					serviciosTV.add(servicio);
				} if (serviciosTV.isEmpty()) {
					LogEliminacion.escribirTrazaTv("INFO;" + rut + ";TV"
							+ ";CODIGO_RESPONSE: 1100;DESCRIPCION: "
							+ "NO SE ENCUENTRA RUT EN COMPENDIO KALTURA");
				}
				
			} else {
				LogEliminacion.escribirTrazaTv("INFO;" + rut + ";TV"
						+ ";CODIGO_RESPONSE: 1000;DESCRIPCION: "
						+ "SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
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
				if (null!=conn){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return serviciosTV;
	}
}
