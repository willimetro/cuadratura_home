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

	public FileCorteCanales getCanales(String archivo) {
		File canales = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		FileCorteCanales fileCorteCanales = new FileCorteCanales();
		List<FileCorteCanalesRow> canalesRows = new ArrayList<FileCorteCanalesRow>();

		try {
			canales = new File(archivo);
			fr = new FileReader(canales);
			br = new BufferedReader(fr);

			String linea;
			int cont = 0;
			FileCorteCanalesRow fileCorteCanalesRow = null;
			Map<String, Integer> mapCanales = new HashMap<String,Integer>();
			while ((linea = br.readLine()) != null) {
				if(cont==0){
					cont++;
				} else {
					String[] lineaArray = linea.split(",");
					fileCorteCanalesRow = new FileCorteCanalesRow();
					fileCorteCanalesRow.setRutConDv(lineaArray[1]);
					fileCorteCanalesRow.setRutSinDV(lineaArray[1]);
					fileCorteCanalesRow.setCodCanal(lineaArray[2]);
					canalesRows.add(fileCorteCanalesRow);
					if(!mapCanales.containsKey(lineaArray[3])){
						mapCanales.put(lineaArray[3], 1);
					} else {
						int cant = mapCanales.get(lineaArray[3]);
						mapCanales.put(lineaArray[3], ++cant);
					}
					
				}
			}
			fileCorteCanales.setCorteCanalesRows(canalesRows);
			fileCorteCanales.setMapCanales(mapCanales);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return fileCorteCanales;
	}
}
