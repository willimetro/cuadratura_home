package cl.everis.cuadratura.obj;

public class FileKenanInOBJ extends FileInOBJ{

	private String codPlan;
	private String cuentakenan;
	private String estado;
	private String plan;
	private String rutCliente;
	private String serviceEnd;
	private String serviceStart;
	private String periodoFact;

	public FileKenanInOBJ(String[] lineaArray) {
		this.codPlan = lineaArray[0].replaceAll("\"", "");
		this.cuentakenan = lineaArray[1].replaceAll("\"", "");
		this.estado = lineaArray[2].replaceAll("\"", "");
		this.periodoFact = lineaArray[3].replaceAll("\"", "");
		this.plan = lineaArray[4].replaceAll("\"", "");
		this.rutCliente = lineaArray[5].replaceAll("\"", "");
		this.serviceEnd = lineaArray[6].replaceAll("\"", "");
		this.serviceStart = lineaArray[7].replaceAll("\"", "");
	}
	
	public String getCodPlan() {
		return codPlan;
	}
	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}
	public String getCuentakenan() {
		return cuentakenan;
	}
	public void setCuentakenan(String cuentakenan) {
		this.cuentakenan = cuentakenan;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
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
	
	public String getPeriodoFact() {
		return periodoFact;
	}

	public void setPeriodoFact(String periodoFact) {
		this.periodoFact = periodoFact;
	}
}
