package cl.everis.cuadratura.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.everis.cuadratura.obj.FileCorteCanales;
import cl.everis.cuadratura.obj.FileCorteCanalesRow;

public class ArchivoUtil {

	public Map<String, FileCorteCanales> getCanales(String archivo) {
		File canales = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<FileCorteCanalesRow> canalesRows = null;
		Map<String, FileCorteCanales> mapCanales = new HashMap<String, FileCorteCanales>();
		try {
			canales = new File(archivo);
			fr = new FileReader(canales);
			br = new BufferedReader(fr);

			String linea;
			int cont = 0;
			FileCorteCanalesRow fileCorteCanalesRow = null;
			FileCorteCanales fileCorteCanales = null;
			while ((linea = br.readLine()) != null) {
				if (cont == 0) {
					cont++;
				} else {
					String[] lineaArray = linea.split(",");
					fileCorteCanalesRow = new FileCorteCanalesRow();
					fileCorteCanalesRow.setRutConDv(lineaArray[1]);
					fileCorteCanalesRow.setRutSinDV(lineaArray[1]);
					fileCorteCanalesRow.setCodCanal(lineaArray[2]);
					if (!mapCanales.containsKey(lineaArray[3])) {
						canalesRows = new ArrayList<FileCorteCanalesRow>();
						canalesRows.add(fileCorteCanalesRow);
						fileCorteCanales = new FileCorteCanales();
						fileCorteCanales.setCountCanales(1);
						fileCorteCanales.setListaClientesCorte(canalesRows);
						mapCanales.put(lineaArray[3], fileCorteCanales);
					} else {
						fileCorteCanales = mapCanales.get(lineaArray[3]);
						int cant = fileCorteCanales.getCountCanales();
						fileCorteCanales.setCountCanales(++cant);
						fileCorteCanales.getListaClientesCorte().add(fileCorteCanalesRow);
						mapCanales.put(lineaArray[3], fileCorteCanales);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			canales = null;
			try {
				if (null != fr) {
					fr.close();
				}
				if (null != br) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return mapCanales;
	}
	
	public List<String> getRutsCorteTV(String archivo) {
		File ruts = null;
		FileReader fr = null;
		BufferedReader br = null;

		List<String> listaRuts = new ArrayList<String>();
		try {
			ruts = new File(archivo);
			fr = new FileReader(ruts);
			br = new BufferedReader(fr);

			String linea;
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				if (cont == 0) {
					cont++;
				} else {
					String[] lineaArray = linea.split(",");			
					if (!listaRuts.contains(lineaArray[1])) {
						listaRuts.add(lineaArray[1]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ruts = null;
			try {
				if (null != fr) {
					fr.close();
				}
				if (null != br) {
					br.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listaRuts;
	}
}
