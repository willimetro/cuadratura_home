package cl.everis.cuadratura.obj;

/**
 * 
 * @author wugaldeq
 *
 */
public class FileCanalesKenanOutOBJ extends FileOutOBJ {
	private String canal;
	private String codigo3Play;
	private String codigoCanal;
	private String cuentaKenan;
	private String estado;
	private String rutCliente;
	private String serviceEnd;
	private String serviceStart;
	private String keyCanal;
	private String periodoFact;

	/**
	 * 
	 * @return
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * 
	 * @param canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodigo3Play() {
		return codigo3Play;
	}

	/**
	 * 
	 * @param codigo3Play
	 */
	public void setCodigo3Play(String codigo3Play) {
		this.codigo3Play = codigo3Play;
	}

	/**
	 * 
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * 
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * 
	 * @return
	 */
	public String getCuentaKenan() {
		return cuentaKenan;
	}

	/**
	 * 
	 * @param cuentaKenan
	 */
	public void setCuentaKenan(String cuentaKenan) {
		this.cuentaKenan = cuentaKenan;
	}

	/**
	 * 
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * 
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @return
	 */
	public String getRutCliente() {
		return rutCliente;
	}

	/**
	 * 
	 * @param rutCliente
	 */
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	/**
	 * 
	 * @return
	 */
	public String getServiceEnd() {
		return serviceEnd;
	}

	/**
	 * 
	 * @param serviceEnd
	 */
	public void setServiceEnd(String serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	/**
	 * 
	 * @return
	 */
	public String getServiceStart() {
		return serviceStart;
	}

	/**
	 * 
	 * @param serviceStart
	 */
	public void setServiceStart(String serviceStart) {
		this.serviceStart = serviceStart;
	}

	/**
	 * 
	 * @return
	 */
	public String getKeyCanal() {
		return keyCanal;
	}

	/**
	 * 
	 * @param keyCanal
	 */
	public void setKeyCanal(String keyCanal) {
		this.keyCanal = keyCanal;
	}

	public String getPeriodoFact() {
		return periodoFact;
	}

	public void setPeriodoFact(String periodoFact) {
		this.periodoFact = periodoFact;
	}

	@Override
	public String toString() {
		return this.canal + ";" + this.codigo3Play + ";" + this.codigoCanal + ";" + this.cuentaKenan + ";" + this.estado
				+ ";" + this.rutCliente + ";" + this.serviceEnd + ";" + this.serviceStart + ";" + this.keyCanal + ";" + this.periodoFact;
	}

}
