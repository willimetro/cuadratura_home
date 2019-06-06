package cl.everis.cuadratura.obj;

import java.util.List;
import java.util.Map;

public class FileCorteCanales {

	private List<FileCorteCanalesRow> corteCanalesRows;
	private Map<String, Integer> mapCanales;

	public List<FileCorteCanalesRow> getCorteCanalesRows() {
		return corteCanalesRows;
	}

	public void setCorteCanalesRows(List<FileCorteCanalesRow> corteCanalesRows) {
		this.corteCanalesRows = corteCanalesRows;
	}

	public Map<String, Integer> getMapCanales() {
		return mapCanales;
	}

	public void setMapCanales(Map<String, Integer> mapCanales) {
		this.mapCanales = mapCanales;
	}

}
