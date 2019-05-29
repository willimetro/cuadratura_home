package cl.everis.cuadratura.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import cl.everis.cuadratura.obj.FileCanalesKalturaInOBJ;
import cl.everis.cuadratura.obj.FileCanalesKalturaOutOBJ;
import cl.everis.cuadratura.obj.FileCanalesKenanInOBJ;
import cl.everis.cuadratura.obj.FileCanalesKenanOutOBJ;
import cl.everis.cuadratura.obj.FileInOBJ;
import cl.everis.cuadratura.obj.FileInternetAAAInOBJ;
import cl.everis.cuadratura.obj.FileInternetAAAOutOBJ;
import cl.everis.cuadratura.obj.FileKenanInOBJ;
import cl.everis.cuadratura.obj.FileKenanOutOBJ;
import cl.everis.cuadratura.obj.FileOutOBJ;
import cl.everis.cuadratura.obj.FileTvBaseKalturaInOBJ;
import cl.everis.cuadratura.obj.FileTvBaseKalturaOutOBJ;

public class FormatoArchivo {

	public void formatFileKenan(String pathArchivo) {
		File kenanIn = null;
		FileReader fr = null;
		BufferedReader br = null;

		FileWriter kenanOut = null;
		PrintWriter pw = null;

		try {
			kenanIn = new File(pathArchivo);
			fr = new FileReader(kenanIn);
			br = new BufferedReader(fr);

			kenanOut = new FileWriter("C:\\Users\\wugaldeq\\Desktop\\29052019\\" + "base_kenan.csv");
			pw = new PrintWriter(kenanOut);

			String linea;
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				FileKenanInOBJ fileKenanInOBJ = (FileKenanInOBJ) getFileInObj(linea, "FileKenanInOBJ");
				FileKenanOutOBJ fileKenanOutOBJ = (FileKenanOutOBJ) getLineFileOut(fileKenanInOBJ, cont,
						"FileKenanInOBJ");
				pw.println(fileKenanOutOBJ.toString());
				cont++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
				if (null != kenanOut) {
					kenanOut.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void formatFileCanalesKaltura(String pathArchivo) {
		File canalesKalturaIn = null;
		FileReader fr = null;
		BufferedReader br = null;

		FileWriter canalesKalturaOut = null;
		PrintWriter pw = null;

		try {
			canalesKalturaIn = new File(pathArchivo);
			fr = new FileReader(canalesKalturaIn);
			br = new BufferedReader(fr);

			canalesKalturaOut = new FileWriter("C:\\Users\\wugaldeq\\Desktop\\29052019\\" + "canales_kaltura.csv");
			pw = new PrintWriter(canalesKalturaOut);

			String linea;
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				FileCanalesKalturaInOBJ fileCanalesKalturaInOBJ = (FileCanalesKalturaInOBJ) getFileInObj(linea,
						"FileCanalesKalturaInOBJ");
				FileCanalesKalturaOutOBJ fileCanalesKalturaOutOBJ = (FileCanalesKalturaOutOBJ) getLineFileOut(
						fileCanalesKalturaInOBJ, cont, "FileCanalesKalturaInOBJ");
				pw.println(fileCanalesKalturaOutOBJ.toString());
				cont++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
				if (null != canalesKalturaOut) {
					canalesKalturaOut.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void formatFileCanalesKenan(String pathArchivo) {
		File canalesKenanIn = null;
		FileReader fr = null;
		BufferedReader br = null;

		FileWriter canalesKenanOut = null;
		PrintWriter pw = null;

		try {
			canalesKenanIn = new File(pathArchivo);
			fr = new FileReader(canalesKenanIn);
			br = new BufferedReader(fr);

			canalesKenanOut = new FileWriter("C:\\Users\\wugaldeq\\Desktop\\29052019\\" + "canales_kenan.csv");
			pw = new PrintWriter(canalesKenanOut);

			String linea;
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				FileCanalesKenanInOBJ fileCanalesKenanInOBJ = (FileCanalesKenanInOBJ) getFileInObj(linea,
						"FileCanalesKenanInOBJ");
				FileCanalesKenanOutOBJ fileCanalesKenanOutOBJ = (FileCanalesKenanOutOBJ) getLineFileOut(
						fileCanalesKenanInOBJ, cont, "FileCanalesKenanInOBJ");
				pw.println(fileCanalesKenanOutOBJ.toString());
				cont++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
				if (null != canalesKenanOut) {
					canalesKenanOut.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void formatFileInternetAAA(String pathArchivo) {
		File internetAAAIn = null;
		FileReader fr = null;
		BufferedReader br = null;

		FileWriter internetAAAOut = null;
		PrintWriter pw = null;

		try {
			internetAAAIn = new File(pathArchivo);
			fr = new FileReader(internetAAAIn);
			br = new BufferedReader(fr);

			internetAAAOut = new FileWriter("C:\\Users\\wugaldeq\\Desktop\\29052019\\" + "internet_aaa.csv");
			pw = new PrintWriter(internetAAAOut);

			String linea;
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				FileInternetAAAInOBJ fileInternetAAAInOBJ = (FileInternetAAAInOBJ) getFileInObj(linea,
						"FileInternetAAAInOBJ");
				if (null != fileInternetAAAInOBJ) {
					FileInternetAAAOutOBJ fileInternetAAAOutOBJ = (FileInternetAAAOutOBJ) getLineFileOut(
							fileInternetAAAInOBJ, cont, "FileInternetAAAInOBJ");

					pw.println(fileInternetAAAOutOBJ.toString());
				}
				cont++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
				if (null != internetAAAOut) {
					internetAAAOut.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void formatFileTvBaseKaltura(String pathArchivo) {
		File tvBaseKalturaIn = null;
		FileReader fr = null;
		BufferedReader br = null;

		FileWriter tvBaseKalturaOut = null;
		PrintWriter pw = null;

		try {
			tvBaseKalturaIn = new File(pathArchivo);
			fr = new FileReader(tvBaseKalturaIn);
			br = new BufferedReader(fr);

			tvBaseKalturaOut = new FileWriter("C:\\Users\\wugaldeq\\Desktop\\29052019\\" + "tv_base_kaltura.csv");
			pw = new PrintWriter(tvBaseKalturaOut);

			String linea;
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				FileTvBaseKalturaInOBJ fileTvBaseKalturaInOBJ = (FileTvBaseKalturaInOBJ) getFileInObj(linea,
						"FileTvBaseKalturaInOBJ");
				FileTvBaseKalturaOutOBJ fileTvBaseKalturaOutOBJ = (FileTvBaseKalturaOutOBJ) getLineFileOut(
						fileTvBaseKalturaInOBJ, cont, "FileTvBaseKalturaInOBJ");

				pw.println(fileTvBaseKalturaOutOBJ.toString());
				cont++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
				if (null != tvBaseKalturaOut) {
					tvBaseKalturaOut.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private FileOutOBJ getLineFileOut(FileInOBJ fileIn, int cont, String objectName) {
		FileOutOBJ fileOutOBJ = null;
		if (objectName.equalsIgnoreCase("FileKenanInOBJ")) {
			FileKenanInOBJ fileKenanInOBJ = (FileKenanInOBJ) fileIn;
			fileOutOBJ = new FileKenanOutOBJ();
			FileKenanOutOBJ fileKenanOutOBJ = (FileKenanOutOBJ) fileOutOBJ;
			if (cont == 0) {
				fileKenanOutOBJ.setRutSinDv("KEY_RUT_SIN_DV");
			} else {
				fileKenanOutOBJ.setRutSinDv(fileKenanInOBJ.getRutCliente());
			}
			fileKenanOutOBJ.setCodPlan(fileKenanInOBJ.getCodPlan());
			fileKenanOutOBJ.setCuentaKenan(fileKenanInOBJ.getCuentakenan());
			fileKenanOutOBJ.setEstado(fileKenanInOBJ.getEstado());
			fileKenanOutOBJ.setPlan(fileKenanInOBJ.getPlan());
			fileKenanOutOBJ.setRutCliente(fileKenanInOBJ.getRutCliente());
			fileKenanOutOBJ.setServiceEnd(fileKenanInOBJ.getServiceEnd());
			fileKenanOutOBJ.setServiceStart(fileKenanInOBJ.getServiceStart());
		} else if (objectName.equalsIgnoreCase("FileCanalesKalturaInOBJ")) {
			FileCanalesKalturaInOBJ fileCanalesKalturaInOBJ = (FileCanalesKalturaInOBJ) fileIn;
			fileOutOBJ = new FileCanalesKalturaOutOBJ();
			FileCanalesKalturaOutOBJ fileCanalesKalturaOutOBJ = (FileCanalesKalturaOutOBJ) fileOutOBJ;
			fileCanalesKalturaOutOBJ.setValidaKaltura(fileCanalesKalturaInOBJ.getValidaKaltura());
			fileCanalesKalturaOutOBJ.setDomainExternalId(fileCanalesKalturaInOBJ.getDomainExternalId());
			fileCanalesKalturaOutOBJ.setModuleId(fileCanalesKalturaInOBJ.getModuleId());
			fileCanalesKalturaOutOBJ.setModuleName(fileCanalesKalturaInOBJ.getModuleName());
			fileCanalesKalturaOutOBJ.setValidaPlanDep(fileCanalesKalturaInOBJ.getValidaPlanDep());
			fileCanalesKalturaOutOBJ.setIdCuentaTv3Play(fileCanalesKalturaInOBJ.getIdCuentaTv3Play());
		} else if (objectName.equalsIgnoreCase("FileCanalesKenanInOBJ")) {
			FileCanalesKenanInOBJ fileCanalesKenanInOBJ = (FileCanalesKenanInOBJ) fileIn;
			fileOutOBJ = new FileCanalesKenanOutOBJ();
			FileCanalesKenanOutOBJ fileCanalesKenanOutOBJ = (FileCanalesKenanOutOBJ) fileOutOBJ;
			fileCanalesKenanOutOBJ.setCanal(fileCanalesKenanInOBJ.getCanal());
			fileCanalesKenanOutOBJ.setCodigo3Play(fileCanalesKenanInOBJ.getCodigo3Play());
			fileCanalesKenanOutOBJ.setCodigoCanal(fileCanalesKenanInOBJ.getCodigoCanal());
			fileCanalesKenanOutOBJ.setCuentaKenan(fileCanalesKenanInOBJ.getCuentaKenan());
			fileCanalesKenanOutOBJ.setEstado(fileCanalesKenanInOBJ.getEstado());
			fileCanalesKenanOutOBJ.setRutCliente(fileCanalesKenanInOBJ.getRutCliente());
			fileCanalesKenanOutOBJ.setServiceEnd(fileCanalesKenanInOBJ.getServiceEnd());
			fileCanalesKenanOutOBJ.setServiceStart(fileCanalesKenanInOBJ.getServiceStart());
			if (cont == 0) {
				fileCanalesKenanOutOBJ.setKeyCanal("KEY_CANAL");
			} else {
				fileCanalesKenanOutOBJ.setKeyCanal(
						fileCanalesKenanInOBJ.getRutCliente() + "-" + fileCanalesKenanInOBJ.getCodigo3Play());
			}
		} else if (objectName.equalsIgnoreCase("FileInternetAAAInOBJ")) {
			FileInternetAAAInOBJ fileInternetAAAInOBJ = (FileInternetAAAInOBJ) fileIn;
			fileOutOBJ = new FileInternetAAAOutOBJ();
			FileInternetAAAOutOBJ fileInternetAAAOutOBJ = (FileInternetAAAOutOBJ) fileOutOBJ;
			fileInternetAAAOutOBJ.setGroup(fileInternetAAAInOBJ.getGroup());
			fileInternetAAAOutOBJ.setUid(fileInternetAAAInOBJ.getUid());
		} else if (objectName.equalsIgnoreCase("FileTvBaseKalturaInOBJ")) {
			FileTvBaseKalturaInOBJ fileTvBaseKalturaInOBJ = (FileTvBaseKalturaInOBJ) fileIn;
			fileOutOBJ = new FileTvBaseKalturaOutOBJ();
			FileTvBaseKalturaOutOBJ fileTvBaseKalturaOutOBJ = (FileTvBaseKalturaOutOBJ) fileOutOBJ;
			if (cont == 0) {
				fileTvBaseKalturaOutOBJ.setKeyRutSinDv("KEY_RUT_SIN_DV");
			} else {
				fileTvBaseKalturaOutOBJ.setKeyRutSinDv(fileTvBaseKalturaInOBJ.getDomainExternalId());
			}
			fileTvBaseKalturaOutOBJ.setDomainExternalId(fileTvBaseKalturaInOBJ.getDomainExternalId());
			fileTvBaseKalturaOutOBJ.setModuleId(fileTvBaseKalturaInOBJ.getModuleId());
			fileTvBaseKalturaOutOBJ.setModuleName(fileTvBaseKalturaInOBJ.getModuleName());
			fileTvBaseKalturaOutOBJ.setValidaRut(fileTvBaseKalturaInOBJ.getValidaRut());
			fileTvBaseKalturaOutOBJ.setDomainId(fileTvBaseKalturaInOBJ.getDomainId());
			fileTvBaseKalturaOutOBJ.setSuspentionState(fileTvBaseKalturaInOBJ.getSuspentionState());
		}
		return fileOutOBJ;
	}

	private FileInOBJ getFileInObj(String linea, String nameObject) {
		String[] lineaArray = linea.split(",");
		FileInOBJ fileIn = null;
		if (nameObject.equalsIgnoreCase("FileKenanInOBJ")) {
			fileIn = new FileKenanInOBJ(lineaArray);
		} else if (nameObject.equalsIgnoreCase("FileCanalesKalturaInOBJ")) {
			fileIn = new FileCanalesKalturaInOBJ(lineaArray);
		} else if (nameObject.equalsIgnoreCase("FileCanalesKenanInOBJ")) {
			fileIn = new FileCanalesKenanInOBJ(lineaArray);
		} else if (nameObject.equalsIgnoreCase("FileInternetAAAInOBJ")) {
			if (lineaArray.length > 1) {
				fileIn = new FileInternetAAAInOBJ(lineaArray);
			}
		} else if (nameObject.equalsIgnoreCase("FileTvBaseKalturaInOBJ")) {
			fileIn = new FileTvBaseKalturaInOBJ(lineaArray);
		}
		return fileIn;
	}

	public static void main(String[] args) {
		FormatoArchivo formatoArchivo = new FormatoArchivo();
		formatoArchivo.formatFileKenan("C:\\Users\\wugaldeq\\Desktop\\29052019\\sinformato\\base_kenan.csv");
		formatoArchivo
				.formatFileCanalesKaltura("C:\\Users\\wugaldeq\\Desktop\\29052019\\sinformato\\canales_kaltura.csv");
		formatoArchivo.formatFileCanalesKenan("C:\\Users\\wugaldeq\\Desktop\\29052019\\sinformato\\canales_kenan.csv");
		formatoArchivo.formatFileInternetAAA("C:\\Users\\wugaldeq\\Desktop\\29052019\\sinformato\\internet_aaa.csv");
		formatoArchivo.formatFileTvBaseKaltura("C:\\Users\\wugaldeq\\Desktop\\29052019\\sinformato\\tv_base_kaltura.csv");
	}

}
