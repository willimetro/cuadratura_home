package cl.everis.cuadratura.obj;

public class FileInternetAAAOutOBJ extends FileOutOBJ {

	private String group;
	private String uid;

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

	@Override
	public String toString() {
		return this.group + ";" + this.uid;
	}

}
