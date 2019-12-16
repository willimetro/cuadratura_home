package cl.everis.cuadratura.obj;

public class FileKenanOutOBJ extends FileOutOBJ {

	private String rutSinDv;
	private String codPlan;
	private String cuentaKenan;
	private String estado;
	private String plan;
	private String rutCliente;
	private String serviceEnd;
	private String serviceStart;
	private String periodoFact;

	public String getRutSinDv() {
		return rutSinDv;
	}

	public void setRutSinDv(String rutSinDv) {
		if (rutSinDv.equalsIgnoreCase("KEY_RUT_SIN_DV")) {
			this.rutSinDv = rutSinDv;
		} else {
			this.rutSinDv = rutSinDv.split("-")[0];
		}
	}

	public String getCodPlan() {
		return codPlan;
	}

	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
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
	
	@Override
	public String toString() {
		return this.rutSinDv + ";" + this.codPlan + ";" + this.cuentaKenan + ";" + this.estado + ";" + this.plan + ";"
				+ this.rutCliente + ";" + this.serviceEnd + ";" + this.serviceStart + ";" + this.periodoFact;
	}

}
