package cl.everis.cuadratura.obj;

public class FileTvBaseKalturaOutOBJ extends FileOutOBJ {

	private String keyRutSinDv;
	private String domainExternalId;
	private String moduleId;
	private String moduleName;
	private String validaRut;
	private String domainId;
	private String suspentionState;

	public String getKeyRutSinDv() {
		return keyRutSinDv;
	}

	public void setKeyRutSinDv(String keyRutSinDv) {
		if (keyRutSinDv.equalsIgnoreCase("KEY_RUT_SIN_DV")) {
			this.keyRutSinDv = keyRutSinDv;
		} else {
			this.keyRutSinDv = keyRutSinDv.split("-")[0];
		}
	}

	public String getDomainExternalId() {
		return domainExternalId;
	}

	public void setDomainExternalId(String domainExternalId) {
		this.domainExternalId = domainExternalId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getValidaRut() {
		return validaRut;
	}

	public void setValidaRut(String validaRut) {
		this.validaRut = validaRut;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getSuspentionState() {
		return suspentionState;
	}

	public void setSuspentionState(String suspentionState) {
		this.suspentionState = suspentionState;
	}

	@Override
	public String toString() {
		return this.keyRutSinDv + ";" + this.domainExternalId + ";" + this.moduleId + ";" + this.moduleName + ";"
				+ this.validaRut + ";" + this.domainId + ";" + this.suspentionState;
	}
}
