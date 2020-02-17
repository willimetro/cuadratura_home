package cl.everis.cuadratura.obj;

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

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getCodigo3Play() {
		return codigo3Play;
	}

	public void setCodigo3Play(String codigo3Play) {
		this.codigo3Play = codigo3Play;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCuentaKenan() {
		return cuentaKenan;
	}

	public void setCuentaKenan(String cuentaKenan) {
		this.cuentaKenan = cuentaKenan;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getServiceEnd() {
		return serviceEnd;
	}

	public void setServiceEnd(String serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	public String getServiceStart() {
		return serviceStart;
	}

	public void setServiceStart(String serviceStart) {
		this.serviceStart = serviceStart;
	}

	public String getKeyCanal() {
		return keyCanal;
	}

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
