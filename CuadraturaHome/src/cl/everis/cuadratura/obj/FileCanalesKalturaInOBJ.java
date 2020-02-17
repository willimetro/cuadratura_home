package cl.everis.cuadratura.obj;

public class FileCanalesKalturaInOBJ extends FileInOBJ {

	private String validaKaltura;
	private String domainExternalId;
	private String moduleId;
	private String moduleName;
	private String validaPlanDep;
	private String idCuentaTv3Play;

	public FileCanalesKalturaInOBJ(String[] lineaArray) {
		this.validaKaltura = lineaArray[0].replaceAll("\"", "");
		this.domainExternalId = lineaArray[1].replaceAll("\"", "");
		this.moduleId = lineaArray[2].replaceAll("\"", "");
		this.moduleName = lineaArray[3].replaceAll("\"", "");
		this.validaPlanDep = lineaArray[4].replaceAll("\"", "");
		this.idCuentaTv3Play = lineaArray[5].replaceAll("\"", "");
	}

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

}
