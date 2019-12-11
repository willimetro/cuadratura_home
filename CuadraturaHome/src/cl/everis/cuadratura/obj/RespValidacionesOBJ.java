package cl.everis.cuadratura.obj;

/**
 * 
 * @author jarenass
 *
 */
public class RespValidacionesOBJ {
	
	private ActivarDesactivarCanalesResponseOBJ resp;
	private Boolean toDelete;
	
	/**
	 * 
	 * @return
	 */
	public ActivarDesactivarCanalesResponseOBJ getResp() {
		return resp;
	}
	
	/**
	 * 
	 * @param resp
	 */
	public void setResp(ActivarDesactivarCanalesResponseOBJ resp) {
		this.resp = resp;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isToDelete() {
		return toDelete;
	}
	
	/**
	 * 
	 * @param toDelete
	 */
	public void setToDelete(boolean toDelete) {
		this.toDelete = toDelete;
	}

}
