package cl.everis.cuadratura.ws;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumPortType;
import com.esa.www.Provision.OrderingServ.N.DesactivarVasYCanalPremium.DesactivarVasYCanalPremiumPortTypeProxy;
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
	
	private final static String QUERY = "select d.codi_servicio "
			+ "from MDP_NEG_CLIENTESACTIVOS C, RMA_NEG_DATOSOTC D "
			+ "where C.nmro_solicitudact = D.corr_solicitud "
			+ "and D.CORR_TIPOSERVICIO = 2 "
			+ "and c.vlor_estadocomp = 1 "
			+ "and c.desc_tiposerv = 'TELEVISION' "
			+ "and desc_catego = 'PLAN BASE' "
			+ "and NRUT_CLIENTE = ?";
	
	/**
	 * 
	 * @param fileCorteCanalesRow
	 * @return
	 */
	public DesactivarCanalesResponseOBJ desactivarCanalPremium(FileCorteCanalesRow fileCorteCanalesRow){
		DesactivarVasYCanalPremiumPortType dcprocy = new DesactivarVasYCanalPremiumPortTypeProxy();
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
			DesactivarVasYCanalPremiumResponseType resp = dcprocy.desactivarVasYCanalPremium(type);
			desactivarCanalesResponseOBJ = new DesactivarCanalesResponseOBJ();
			desactivarCanalesResponseOBJ.setCodResponse(resp.getResponse().getHeaderOut().getCodigo());
			desactivarCanalesResponseOBJ.setDescripcion(resp.getResponse().getHeaderOut().getDescripcion());
			LogEliminacion.escribirTraza("INFO;"+fileCorteCanalesRow.getRutConDv()+ ";"+fileCorteCanalesRow.getCodCanal()+";CODIGO_RESPONSE: "
					+resp.getResponse().getHeaderOut().getCodigo()+";DESCRIPCION: "+resp.getResponse().getHeaderOut().getDescripcion());
		} catch (DesactivarVasYCanalPremiumFaultType e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return desactivarCanalesResponseOBJ;
	}
	
	/**
	 * 
	 * @param fileCorteCanalesRow
	 * @return
	 */
	public FileCorteCanalesRow getCodServicioCanalesPremium(FileCorteCanalesRow fileCorteCanalesRow){
		Connection conn = null;
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		
		conn = ConnectionCuadraturaBD.getConnTPlay();
		try {
			pstmt = conn.prepareStatement(QUERY);
			pstmt.setString(1, fileCorteCanalesRow.getRutSinDV());
			rs = pstmt.executeQuery();
			if(rs.next()){
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
