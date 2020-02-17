package cl.everis.cuadratura.obj;

import javax.swing.JTextArea;

public class CountOBJ {

	private int totalTplay;
	private int totalRed;
	private int totalAmbos;
	private int difTplayRed;
	private int difRedTplay;
	private int totalOk;
	private int totalRedTplay;
	private int totalKenan;
	private JTextArea jTextAreaStatusProcess;
	
	public int getTotalOk() {
		return totalOk;
	}

	public void setTotalOk(int totalOk) {
		this.totalOk = totalOk;
	}

	public int getTotalRedTplay() {
		return totalRedTplay;
	}

	public void setTotalRedTplay(int totalRedTplay) {
		this.totalRedTplay = totalRedTplay;
	}

	public int getTotalKenan() {
		return totalKenan;
	}

	public void setTotalKenan(int totalKenan) {
		this.totalKenan = totalKenan;
	}
	
	public JTextArea getjTextAreaStatusProcess() {
		return jTextAreaStatusProcess;
	}

	public void setjTextAreaStatusProcess(JTextArea jTextAreaStatusProcess) {
		this.jTextAreaStatusProcess = jTextAreaStatusProcess;
	}

	public int getTotalRed() {
		return totalRed;
	}

	public void setTotalRed(int totalRed) {
		this.totalRed = totalRed;
	}

	public int getTotalTplay() {
		return totalTplay;
	}

	public void setTotalTplay(int totalTplay) {
		this.totalTplay = totalTplay;
	}

	public int getDifRedTplay() {
		return difRedTplay;
	}

	public void setDifRedTplay(int redNoTplay) {
		this.difRedTplay = redNoTplay;
	}
	
	public int getTotalAmbos() {
		return totalAmbos;
	}

	public void setTotalAmbos(int totalAmbos) {
		this.totalAmbos = totalAmbos;
	}

	public int getDifTplayRed() {
		return difTplayRed;
	}

	public void setDifTplayRed(int difTplayRed) {
		this.difTplayRed = difTplayRed;
	}

}
