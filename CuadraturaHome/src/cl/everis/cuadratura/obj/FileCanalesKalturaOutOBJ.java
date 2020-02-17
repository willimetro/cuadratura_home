package cl.everis.cuadratura.obj;

public class FileCanalesKalturaOutOBJ extends FileOutOBJ {

	private String validaKaltura;
	private String domainExternalId;
	private String moduleId;
	private String moduleName;
	private String validaPlanDep;
	private String idCuentaTv3Play;

	public String getValidaKaltura() {
		return validaKaltura;
	}

	public void setValidaKaltura(String validaKaltura) {
		this.validaKaltura = validaKaltura;
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

	public String getValidaPlanDep() {
		return validaPlanDep;
	}

	public void setValidaPlanDep(String validaPlanDep) {
		this.validaPlanDep = validaPlanDep;
	}

	public String getIdCuentaTv3Play() {
		return idCuentaTv3Play;
	}

	public void setIdCuentaTv3Play(String idCuentaTv3Play) {
		this.idCuentaTv3Play = idCuentaTv3Play;
	}

	@Override
	public String toString() {
		return this.validaKaltura + ";" + this.domainExternalId + ";" + this.moduleId + ";" + this.moduleName + ";"
				+ this.validaPlanDep + ";" + this.idCuentaTv3Play;
	}
}
