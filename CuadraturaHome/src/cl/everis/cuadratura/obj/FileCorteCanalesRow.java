package cl.everis.cuadratura.obj;

public class FileCorteCanalesRow {

	private String rutSinDV;
	private String codCanal;
	private String rutConDv;

	public String getRutSinDV() {
		return rutSinDV;
	}

	public void setRutSinDV(String rutSinDV) {
		String[] rut = rutSinDV.split("-");
		this.rutSinDV = rut[0];
	}

	public String getCodCanal() {
		return codCanal;
	}

	public void setCodCanal(String codCanal) {
		this.codCanal = codCanal;
	}

	public String getRutConDv() {
		return rutConDv;
	}

	public void setRutConDv(String rutConDv) {
		this.rutConDv = rutConDv;
	}
}
