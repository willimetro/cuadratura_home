package cl.everis.cuadratura.obj;

import java.util.List;

public class FileCorteCanales {

	private Integer countCanales;
	private List<FileCorteCanalesRow> listaClientesCorte;

	public Integer getCountCanales() {
		return countCanales;
	}

	public void setCountCanales(Integer countCanales) {
		this.countCanales = countCanales;
	}

	public List<FileCorteCanalesRow> getListaClientesCorte() {
		return listaClientesCorte;
	}

	public void setListaClientesCorte(List<FileCorteCanalesRow> listaClientesCorte) {
		this.listaClientesCorte = listaClientesCorte;
	}

}
