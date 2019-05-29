package cl.everis.cuadratura.obj;

public class FileInternetAAAInOBJ extends FileInOBJ {

	private String group;
	private String uid;

	public FileInternetAAAInOBJ(String[] lineaArray) {
		this.group = lineaArray[0].replaceAll("\"", "");
		this.uid = lineaArray[1].replaceAll("\"", "");
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
