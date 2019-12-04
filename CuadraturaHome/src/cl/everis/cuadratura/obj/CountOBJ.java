package cl.everis.cuadratura.obj;

import javax.swing.JTextArea;

/**
 * 
 * @author wugaldeq
 *
 */
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
	
	/**
	 * 
	 * @return
	 */
	public int getTotalOk() {
		return totalOk;
	}

	/**
	 * 
	 * @param totalOk
	 */
	public void setTotalOk(int totalOk) {
		this.totalOk = totalOk;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalRedTplay() {
		return totalRedTplay;
	}

	/**
	 * 
	 * @param totalRedTplay
	 */
	public void setTotalRedTplay(int totalRedTplay) {
		this.totalRedTplay = totalRedTplay;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalKenan() {
		return totalKenan;
	}

	/**
	 * 
	 * @param totalKenan
	 */
	public void setTotalKenan(int totalKenan) {
		this.totalKenan = totalKenan;
	}
	
	/**
	 * 
	 * @return
	 */
	public JTextArea getjTextAreaStatusProcess() {
		return jTextAreaStatusProcess;
	}

	/**
	 * 
	 * @param jTextAreaStatusProcess
	 */
	public void setjTextAreaStatusProcess(JTextArea jTextAreaStatusProcess) {
		this.jTextAreaStatusProcess = jTextAreaStatusProcess;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalRed() {
		return totalRed;
	}

	/**
	 * 
	 * @param totalRed
	 */
	public void setTotalRed(int totalRed) {
		this.totalRed = totalRed;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalTplay() {
		return totalTplay;
	}

	/**
	 * 
	 * @param totalTplay
	 */
	public void setTotalTplay(int totalTplay) {
		this.totalTplay = totalTplay;
	}

	/**
	 * 
	 * @return
	 */
	public int getDifRedTplay() {
		return difRedTplay;
	}

	/**
	 * 
	 * @param redNoTplay
	 */
	public void setDifRedTplay(int redNoTplay) {
		this.difRedTplay = redNoTplay;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTotalAmbos() {
		return totalAmbos;
	}

	/**
	 * 
	 * @param totalAmbos
	 */
	public void setTotalAmbos(int totalAmbos) {
		this.totalAmbos = totalAmbos;
	}

	/**
	 * 
	 * @return
	 */
	public int getDifTplayRed() {
		return difTplayRed;
	}

	/**
	 * 
	 * @param difTplayRed
	 */
	public void setDifTplayRed(int difTplayRed) {
		this.difTplayRed = difTplayRed;
	}

}
