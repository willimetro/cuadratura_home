package cl.everis.cuadratura.obj;

import javax.swing.JTextArea;

public class CountOBJ {

	private int totalTplay;
	private int totalRed;
	private int totalAmbos;
	private int difTplayRed;
	private int difRedTplay;
	private int okOkOk;
	private int okNokOk;
	private int nokOkOk;
	private int okOKNok;
	private int okNokNok;
	private int nokOkNok;
	private int nokNokOk;
	private JTextArea jTextAreaStatusProcess;
	
	public int getOkOkOk() {
		return okOkOk;
	}

	public void setOkOkOk(int totalOk) {
		this.okOkOk = totalOk;
	}

	public int getOkNokOk() {
		return okNokOk;
	}

	public void setOkNokOk(int totalRedTplay) {
		this.okNokOk = totalRedTplay;
	}

	public int getNokOkOk() {
		return nokOkOk;
	}

	public void setNokOkOk(int totalKenan) {
		this.nokOkOk = totalKenan;
	}
	
	public int getOkOKNok() {
		return okOKNok;
	}

	public void setOkOKNok(int okOKNok) {
		this.okOKNok = okOKNok;
	}

	public int getOkNokNok() {
		return okNokNok;
	}

	public void setOkNokNok(int okNokNok) {
		this.okNokNok = okNokNok;
	}

	public int getNokOkNok() {
		return nokOkNok;
	}

	public void setNokOkNok(int nokOkNok) {
		this.nokOkNok = nokOkNok;
	}

	public int getNokNokOk() {
		return nokNokOk;
	}

	public void setNokNokOk(int nokNokOk) {
		this.nokNokOk = nokNokOk;
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
