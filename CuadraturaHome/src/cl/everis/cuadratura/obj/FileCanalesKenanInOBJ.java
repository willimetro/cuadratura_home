package cl.everis.cuadratura.obj;

/**
 * 
 * @author wugaldeq
 *
 */
public class FileCanalesKenanInOBJ extends FileInOBJ {

	private String canal;
	private String codigo3Play;
	private String codigoCanal;
	private String cuentaKenan;
	private String estado;
	private String rutCliente;
	private String serviceEnd;
	private String serviceStart;
	private String periodoFact;

	/**
	 * 
	 * @param lineaArray
	 */
	public FileCanalesKenanInOBJ(String[] lineaArray) {
		this.canal = lineaArray[0].replaceAll("\"", "");
		this.codigo3Play = lineaArray[1].replaceAll("\"", "");
		this.codigoCanal = lineaArray[2].replaceAll("\"", "");
		this.cuentaKenan = lineaArray[3].replaceAll("\"", "");
		this.estado = lineaArray[4].replaceAll("\"", "");
		this.periodoFact = lineaArray[5].replaceAll("\"", "");
		this.rutCliente = lineaArray[6].replaceAll("\"", "");
		this.serviceEnd = lineaArray[7].replaceAll("\"", "");
		this.serviceStart = lineaArray[8].replaceAll("\"", "");
	}

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

	public String getPeriodoFact() {
		return periodoFact;
	}

	public void setPeriodoFact(String periodoFact) {
		this.periodoFact = periodoFact;
	}

}
