package cl.everis.cuadratura.obj;

import javax.swing.JTextArea;

/**
 * 
 * @author wugaldeq
 *
 */
public class CountOBJ {

	private int totalRed;
	private int totalTplay;
	private int dif;
	private JTextArea jTextAreaStatusProcess;
	
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
	public int getDiferencia() {
		return dif;
	}

	/**
	 * 
	 * @param redNoTplay
	 */
	public void setDiferencia(int redNoTplay) {
		this.dif = redNoTplay;
	}

}
