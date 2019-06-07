package cl.everis.cuadratura.obj;

/**
 * 
 * @author wugaldeq
 *
 */
public class FileCanalesKalturaInOBJ extends FileInOBJ {

	private String validaKaltura;
	private String domainExternalId;
	private String moduleId;
	private String moduleName;
	private String validaPlanDep;
	private String idCuentaTv3Play;

	/**
	 * 
	 * @param lineaArray
	 */
	public FileCanalesKalturaInOBJ(String[] lineaArray) {
		this.validaKaltura = lineaArray[0].replaceAll("\"", "");
		this.domainExternalId = lineaArray[1].replaceAll("\"", "");
		this.moduleId = lineaArray[2].replaceAll("\"", "");
		this.moduleName = lineaArray[3].replaceAll("\"", "");
		this.validaPlanDep = lineaArray[4].replaceAll("\"", "");
		this.idCuentaTv3Play = lineaArray[5].replaceAll("\"", "");
	}

	/**
	 * 
	 * @return
	 */
	public String getValidaKaltura() {
		return validaKaltura;
	}

	/**
	 * 
	 * @param validaKaltura
	 */
	public void setValidaKaltura(String validaKaltura) {
		this.validaKaltura = validaKaltura;
	}

	/**
	 * 
	 * @return
	 */
	public String getDomainExternalId() {
		return domainExternalId;
	}

	/**
	 * 
	 * @param domainExternalId
	 */
	public void setDomainExternalId(String domainExternalId) {
		this.domainExternalId = domainExternalId;
	}

	/**
	 * 
	 * @return
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * 
	 * @param moduleId
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * 
	 * @return
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 
	 * @param moduleName
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 
	 * @return
	 */
	public String getValidaPlanDep() {
		return validaPlanDep;
	}

	/**
	 * 
	 * @param validaPlanDep
	 */
	public void setValidaPlanDep(String validaPlanDep) {
		this.validaPlanDep = validaPlanDep;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdCuentaTv3Play() {
		return idCuentaTv3Play;
	}

	/**
	 * 
	 * @param idCuentaTv3Play
	 */
	public void setIdCuentaTv3Play(String idCuentaTv3Play) {
		this.idCuentaTv3Play = idCuentaTv3Play;
	}

}
