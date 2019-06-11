package cl.everis.cuadratura.obj;

public class FileTvBaseKalturaInOBJ extends FileInOBJ {

	private String domainExternalId;
	private String moduleId;
	private String moduleName;
	private String validaRut;
	private String domainId;
	private String suspentionState;

	public FileTvBaseKalturaInOBJ(String[] lineaArray) {
		this.domainExternalId = lineaArray[0].replaceAll("\"", "");
		this.moduleId = lineaArray[1].replaceAll("\"", "");
		this.moduleName = lineaArray[2].replaceAll("\"", "");
		this.validaRut = lineaArray[3].replaceAll("\"", "");
		this.domainId = lineaArray[4].replaceAll("\"", "");
		this.suspentionState = lineaArray[5].replaceAll("\"", "");
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

}
