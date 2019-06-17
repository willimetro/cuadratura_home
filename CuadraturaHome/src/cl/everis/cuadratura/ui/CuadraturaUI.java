package cl.everis.cuadratura.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import cl.everis.cuadratura.bd.BDManager;
import cl.everis.cuadratura.bd.BDManagerImpl;
import cl.everis.cuadratura.files.ArchivoUtil;
import cl.everis.cuadratura.obj.CountOBJ;
import cl.everis.cuadratura.obj.DesactivarCanalesResponseOBJ;
import cl.everis.cuadratura.obj.FileCorteCanales;
import cl.everis.cuadratura.obj.FileCorteCanalesRow;
import cl.everis.cuadratura.util.LogEliminacion;
import cl.everis.cuadratura.ws.Correo;
import cl.everis.cuadratura.ws.DesactivarCanales;
import cl.everis.cuadratura.ws.DesactivarTodoTV;

/**
 * 
 * @author wugaldeq
 *
 */
public class CuadraturaUI implements Runnable, ActionListener {

	private final static String[] CRUCES_TPLAY = { "TPLAY_KALTURA", "TPLAY_KALTURA_C", "TPLAY_KENAN_TV",
			"TPLAY_KENAN_TLF", "TPLAY_KENAN_INT", "TPLAY_KENAN_C", "TPLAY_AAA", "TPLAY_OTCAR" };
	private final static String[] PRODUCTOS_TPLAY = { "INTERNET", "TV", "TLF", "OTCAR", "KENAN", "KENAN_62", "KENAN_C",
			"KALTURA", "KALTURA_C", "AAA" };
	private JFrame mainFrame = null;
	private JProgressBar statusProcessTV;
	private JProgressBar statusProcess;
	// CheckBox
	JCheckBox chTodos = new JCheckBox("Todos");
	JCheckBox chTresPlayAAA = new JCheckBox("Internet AAA");
	JCheckBox chTresPlayKalturaBase = new JCheckBox("TV Planes Base");
	JCheckBox chTresPlayKalturaAdi = new JCheckBox("TV Canales Adicionales");
	JCheckBox chTresPlayOTCARTel = new JCheckBox("Telefonia OTCAR");
	JCheckBox chTresPlayKenanInter = new JCheckBox("Kenan Internet");
	JCheckBox chTresPlayKenanTVBase = new JCheckBox("Kenan TV BASE");
	JCheckBox chTresPlayKenanTVAdi = new JCheckBox("Kenan TV Adicionales");
	JCheckBox chTresPlayKenanTel = new JCheckBox("Kenan Telefonía");

	// Botones para buscar los archivos (FileChooser)
	JButton showFileDialogKenanAdiButton = new JButton("Buscar");
	JButton showFileDialogKenanButton = new JButton("Buscar");
	JButton showFileDialogAdicionalesButton = new JButton("Buscar");
	JButton showFileDialogTvPlanesBaseButton = new JButton("Buscar");
	JButton showFileDialogInternetButton = new JButton("Buscar");

	JButton showFileDialogCorteCanalesAdiButton = new JButton("Buscar");
	JButton showFileDialogCortePlanesTVButton = new JButton("Buscar");

	private JLabel pathLabelTvPlanesBase;
	private String pathLabelTodoTvKaltura;
	private JLabel pathLabelInternet;
	private JLabel pathLabelTvAdicionales;
	private JLabel pathLabelKenan;
	private JLabel pathLabelKenanAdi;
	private JLabel pathLabelCorteCanales;
	private GridBagConstraints showFileDialogConstrains;
	final JFileChooser fileDialogTvPlanesBase = new JFileChooser();
	final JFileChooser fileDialogTodoTvKaltura = new JFileChooser();
	final JFileChooser fileDialogInternet = new JFileChooser();
	final JFileChooser fileDialogTvAdicionales = new JFileChooser();
	final JFileChooser fileDialogKenan = new JFileChooser();
	final JFileChooser fileDialogKenanAdi = new JFileChooser();

	final JFileChooser fileDialogCorteCanalesAdi = new JFileChooser();
	final JFileChooser fileDialogCortePlanesTV = new JFileChooser();
	private GridBagConstraints pathConstrains;

	private BDManager bdManager = new BDManagerImpl();

	private Thread hilo;

	private String flagAction = "";

	JButton iniciarBtn = new JButton("Iniciar");
	JButton cargarDatosBtn = new JButton("Cargar Datos");
	JButton cargarDatosTVBtn = new JButton("Cargar Ruts");
	JButton cortarBtn = new JButton("Cortar");
	JButton cortarPlanesBtn = new JButton("Cortar");
	private JLabel labelInfoCanales;
	private JLabel labelInfoCorteTV;
	private JTextArea jTextAreaStatusProcess;
	private JTextArea jTextAreaStatusProcessTV;

	private JList<String> listaCanales = null;
	private JList<String> listaRuts = null;
	Map<String, FileCorteCanales> mapCanales = null;
	List<List<FileCorteCanalesRow>> listaListaCanales = null;
	List<String> listaAllRuts = null;
	List<String> listaRutsCorte = null;

	JPanel comboPanel = null;
	JPanel comboPanelTV = null;
	JPanel panel3Play = null;
	/**
	 * Constructor
	 */
	public CuadraturaUI() {
		mainFrame = new JFrame("Cuadratura Home");
		JTabbedPane jTabbedPane = new JTabbedPane();
		JComponent tresPlay = makeTextPanel3Play("Panel #1");
		jTabbedPane.addTab("3 Play", tresPlay);
		jTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent inalambrico = makeTextPanelInalambrico("Panel #2");

		jTabbedPane.addTab("Inalámbrica", inalambrico);
		jTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent corteBloqueo = makeTextPanelCorteBloqueo("Panel #3");

		jTabbedPane.addTab("Corte y Bloqueo", corteBloqueo);
		jTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		JComponent cortePlanesTV = makeTextPanelCortePlanesTV("Panel #4");
		
		jTabbedPane.addTab("Corte Planes TV", cortePlanesTV);
		jTabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		mainFrame.add(jTabbedPane, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	protected JComponent makeTextPanel3Play(String text) {

		panel3Play = new JPanel(false);
		JPanel tiposCuad = new JPanel();
		JPanel panelCheck = new JPanel();
		createMenuCheckFor3Play(panelCheck);
		JPanel panelChooser = new JPanel();
		showFileChooser3playIntenet(panelChooser);
		showFileChooser3playTvPlanesBase(panelChooser);
		showFileChooser3playTvAdicionales(panelChooser);
		showFileChooser3playKenan(panelChooser);
		showFileChooser3playKenanAdi(panelChooser);
		iniciarBtn.setEnabled(false);
		panelChooser.add(iniciarBtn);
		iniciarBtn.addActionListener(this);
		tiposCuad.setBorder(BorderFactory.createTitledBorder("Tipos de Cuadratura 3 play"));
		tiposCuad.setLayout(new GridLayout(1, 2));
		panelCheck.setBorder(BorderFactory.createTitledBorder("Seleccione la cuadratura"));
		tiposCuad.add(panelCheck);
		panelChooser.setBorder(BorderFactory.createTitledBorder("Busque los archivos"));
		tiposCuad.add(panelChooser);
		JPanel resultadosCuad = new JPanel();
		resultadosCuad.setBorder(BorderFactory.createTitledBorder("Resultados Cuadratura 3 play"));
		panel3Play.setLayout(new GridLayout(2, 1));
		panel3Play.add(tiposCuad);
		
		statusProcess = new JProgressBar();
		resultadosCuad.setLayout(new BoxLayout(resultadosCuad, BoxLayout.Y_AXIS));
		JPanel panelStatusProgress = new JPanel();
		statusProcess = new JProgressBar();
		panelStatusProgress.add(statusProcess);
		resultadosCuad.add(panelStatusProgress);
		jTextAreaStatusProcess = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(jTextAreaStatusProcess, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resultadosCuad.add(scrollPane);
		panel3Play.add(resultadosCuad);
		return panel3Play;
	}

	/**
	 * 
	 * @param panelCheck
	 */
	private void createMenuCheckFor3Play(JPanel panelCheck) {

		chTodos.setMnemonic(KeyEvent.VK_C);
		chTodos.setSelected(false);
		chTodos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTodos.isSelected()) {
					chTresPlayAAA.setSelected(false);
					chTresPlayAAA.setEnabled(true);
					chTresPlayKalturaBase.setSelected(false);
					chTresPlayKalturaBase.setEnabled(true);
					chTresPlayKalturaAdi.setSelected(false);
					chTresPlayKalturaAdi.setEnabled(true);
					chTresPlayOTCARTel.setSelected(false);
					chTresPlayOTCARTel.setEnabled(true);
					chTresPlayKenanInter.setSelected(false);
					chTresPlayKenanInter.setEnabled(true);
					chTresPlayKenanTVBase.setSelected(false);
					chTresPlayKenanTVBase.setEnabled(true);
					chTresPlayKenanTVAdi.setSelected(false);
					chTresPlayKenanTVAdi.setEnabled(true);
					chTresPlayKenanTel.setSelected(false);
					chTresPlayKenanTel.setEnabled(true);
					showFileDialogKenanAdiButton.setEnabled(false);
					showFileDialogKenanButton.setEnabled(false);
					showFileDialogAdicionalesButton.setEnabled(false);
					showFileDialogTvPlanesBaseButton.setEnabled(false);
					showFileDialogInternetButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					chTresPlayAAA.setSelected(true);
					chTresPlayAAA.setEnabled(false);
					chTresPlayKalturaBase.setSelected(true);
					chTresPlayKalturaBase.setEnabled(false);
					chTresPlayKalturaAdi.setSelected(true);
					chTresPlayKalturaAdi.setEnabled(false);
					chTresPlayOTCARTel.setSelected(true);
					chTresPlayOTCARTel.setEnabled(false);
					chTresPlayKenanInter.setSelected(true);
					chTresPlayKenanInter.setEnabled(false);
					chTresPlayKenanTVBase.setSelected(true);
					chTresPlayKenanTVBase.setEnabled(false);
					chTresPlayKenanTVAdi.setSelected(true);
					chTresPlayKenanTVAdi.setEnabled(false);
					chTresPlayKenanTel.setSelected(true);
					chTresPlayKenanTel.setEnabled(false);
					showFileDialogKenanAdiButton.setEnabled(true);
					showFileDialogKenanButton.setEnabled(true);
					showFileDialogAdicionalesButton.setEnabled(true);
					showFileDialogTvPlanesBaseButton.setEnabled(true);
					showFileDialogInternetButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}

			}
		});

		chTresPlayAAA.setMnemonic(KeyEvent.VK_C);
		chTresPlayAAA.setSelected(false);
		chTresPlayAAA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayAAA.isSelected()) {
					showFileDialogInternetButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					showFileDialogInternetButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}
			}
		});

		chTresPlayKalturaBase.setMnemonic(KeyEvent.VK_C);
		chTresPlayKalturaBase.setSelected(false);
		chTresPlayKalturaBase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayKalturaBase.isSelected()) {
					showFileDialogTvPlanesBaseButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					showFileDialogTvPlanesBaseButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}
			}
		});

		chTresPlayKalturaAdi.setMnemonic(KeyEvent.VK_C);
		chTresPlayKalturaAdi.setSelected(false);
		chTresPlayKalturaAdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayKalturaAdi.isSelected()) {
					showFileDialogAdicionalesButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					showFileDialogAdicionalesButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}
			}
		});

		chTresPlayOTCARTel.setMnemonic(KeyEvent.VK_C);
		chTresPlayOTCARTel.setSelected(false);
		chTresPlayOTCARTel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayOTCARTel.isSelected()) {
					iniciarBtn.setEnabled(false);
				} else {
					iniciarBtn.setEnabled(true);
				}
			}
		});

		chTresPlayKenanInter.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanInter.setSelected(false);
		chTresPlayKenanInter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayKenanInter.isSelected()) {
					showFileDialogKenanButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					showFileDialogKenanButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}
			}
		});

		chTresPlayKenanTVBase.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTVBase.setSelected(false);
		chTresPlayKenanTVBase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayKenanTVBase.isSelected()) {
					showFileDialogKenanButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					showFileDialogKenanButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}
			}
		});

		chTresPlayKenanTVAdi.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTVAdi.setSelected(false);
		chTresPlayKenanTVAdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayKenanTVAdi.isSelected()) {
					showFileDialogKenanAdiButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					showFileDialogKenanAdiButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}
			}
		});

		chTresPlayKenanTel.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTel.setSelected(false);
		chTresPlayKenanTel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chTresPlayKenanTel.isSelected()) {
					showFileDialogKenanButton.setEnabled(false);
					iniciarBtn.setEnabled(false);
				} else {
					showFileDialogKenanButton.setEnabled(true);
					iniciarBtn.setEnabled(true);
				}
			}
		});

		panelCheck.setLayout(new GridLayout(0, 2));
		panelCheck.add(chTodos);
		panelCheck.add(chTresPlayAAA);
		panelCheck.add(chTresPlayKalturaBase);
		panelCheck.add(chTresPlayKalturaAdi);
		panelCheck.add(chTresPlayOTCARTel);
		panelCheck.add(chTresPlayKenanInter);
		panelCheck.add(chTresPlayKenanTVBase);
		panelCheck.add(chTresPlayKenanTVAdi);
		panelCheck.add(chTresPlayKenanTel);
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	protected JComponent makeTextPanelInalambrico(String text) {
		JPanel panelIna = new JPanel(false);
		JPanel tiposCuad = new JPanel();
		tiposCuad.setBorder(BorderFactory.createTitledBorder("Tipos de Cuadratura Inalámbrica"));
		JPanel resultadosCuad = new JPanel();
		resultadosCuad.setBorder(BorderFactory.createTitledBorder("Resultados Cuadratura Inalámbrica"));
		panelIna.setLayout(new GridLayout(2, 1));
		panelIna.add(tiposCuad);
		panelIna.add(resultadosCuad);
		return panelIna;
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	protected JComponent makeTextPanelCorteBloqueo(String text) {
		JPanel panelCB = new JPanel(false);
		JPanel cargaArchivo = new JPanel();
		cargaArchivo.setBorder(BorderFactory.createTitledBorder("Configuración del Corte o Bloqueo"));
		cargaArchivo.setLayout(new BoxLayout(cargaArchivo, BoxLayout.Y_AXIS));
		JPanel panelChooser = new JPanel();
		panelChooser.setBorder(BorderFactory.createTitledBorder("Paso 1 - Busque archivo y carguelo"));
		showFileChooserCorteCanales(panelChooser);
		cargarDatosBtn.setEnabled(false);
		cargarDatosBtn.addActionListener(this);
		panelChooser.add(cargarDatosBtn);
		JPanel panelFileCargado = new JPanel();
		panelFileCargado.setLayout(new BoxLayout(panelFileCargado, BoxLayout.Y_AXIS));
		listaCanales = new JList<String>();
		listaCanales.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scroolList = new JScrollPane(listaCanales);
		scroolList.setPreferredSize(new Dimension(94, 147));
		listaCanales.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				List<String> listaSeleccionada = listaCanales.getSelectedValuesList();
				int cantidad = 0;
				listaListaCanales = new ArrayList<List<FileCorteCanalesRow>>();
				for (Iterator<String> iterator = listaSeleccionada.iterator(); iterator.hasNext();) {
					String nomCanal = (String) iterator.next();
					cantidad = cantidad + mapCanales.get(nomCanal).getCountCanales();
					listaListaCanales.add(mapCanales.get(nomCanal).getListaClientesCorte());
				}
				if (listaSeleccionada.isEmpty()) {
					labelInfoCanales.setText("Seleccione los canales que quiere regularizar ");
					cortarBtn.setEnabled(false);
				} else {
					labelInfoCanales.setText("Se intentará dar de baja " + cantidad + " clientes ");
					cortarBtn.setEnabled(true);
				}
			}
		});
		panelFileCargado
				.setBorder(BorderFactory.createTitledBorder("Paso 2 - Seleccione el canal que quiere dar de baja"));
		comboPanel = new JPanel();
		comboPanel.add(scroolList);
		cortarBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = { "Aceptar", "Cancelar" };
				int n = JOptionPane.showOptionDialog(panelCB,
						"Recuerde que " + labelInfoCanales.getText().toLowerCase() + " Desea seguir con el proceso?",
						"Seguro que desea seguir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, null);
				if (n == 0) {
					flagAction = "Cortar Canales";
					hilo = new Thread(CuadraturaUI.this);
					hilo.start();
					cortarBtn.setEnabled(false);
				}
			}
		});
		comboPanel.add(cortarBtn);
		cortarBtn.setEnabled(false);
		panelFileCargado.add(comboPanel);
		labelInfoCanales = new JLabel(" ");
		JPanel panelAlertCanales = new JPanel();
		labelInfoCanales.setText("Seleccione los canales que quiere regularizar ");
		panelAlertCanales.add(labelInfoCanales);
		panelFileCargado.add(panelAlertCanales);
		cargaArchivo.add(panelChooser);
		cargaArchivo.add(panelFileCargado);
		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(BorderFactory.createTitledBorder("Consola de Corte o bloqueo"));
		panelCB.setLayout(new GridLayout(2, 1));
		panelCB.add(cargaArchivo);
		statusProcess = new JProgressBar();
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));
		JPanel panelStatusProgress = new JPanel();
		statusProcess = new JProgressBar();
		panelStatusProgress.add(statusProcess);
		consolePanel.add(panelStatusProgress);
		jTextAreaStatusProcess = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(jTextAreaStatusProcess, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension tamanhoTextArea = jTextAreaStatusProcess.getSize();
		Point p = new Point(0,tamanhoTextArea.height);
		scrollPane.getViewport().setViewPosition(p);
		consolePanel.add(scrollPane);
		panelCB.add(consolePanel);
		return panelCB;
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	protected JComponent makeTextPanelCortePlanesTV(String text) {
		JPanel panelCB = new JPanel(false);
		JPanel cargaArchivo = new JPanel();
		cargaArchivo.setBorder(BorderFactory.createTitledBorder("Configuración del Corte TV"));
		cargaArchivo.setLayout(new BoxLayout(cargaArchivo, BoxLayout.Y_AXIS));
		JPanel panelChooser = new JPanel();
		panelChooser.setBorder(BorderFactory.createTitledBorder("Paso 1 - Busque archivo y carguelo"));
		showFileChooserCortePlanesTV(panelChooser);
		cargarDatosTVBtn.setEnabled(false);
		cargarDatosTVBtn.addActionListener(this);
		panelChooser.add(cargarDatosTVBtn);
		JPanel panelFileCargado = new JPanel();
		panelFileCargado.setLayout(new BoxLayout(panelFileCargado, BoxLayout.Y_AXIS));
		listaRuts = new JList<String>();
		listaRuts.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scroolList = new JScrollPane(listaRuts);
		scroolList.setPreferredSize(new Dimension(94, 147));
		listaRuts.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listaRutsCorte = listaRuts.getSelectedValuesList();
				int cantidad = listaRutsCorte.size();
				if (listaRutsCorte.isEmpty()) {
					labelInfoCorteTV.setText("Seleccione los RUTS que quiere regularizar");
					cortarPlanesBtn.setEnabled(false);
				} else {
					labelInfoCorteTV.setText("Se intentará eliminar los servicios de televisión a " + cantidad + " clientes.");
					cortarPlanesBtn.setEnabled(true);
				}
			}
		});
		panelFileCargado
				.setBorder(BorderFactory.createTitledBorder("Paso 2 - Seleccione los RUTS que desea dar de baja"));
		comboPanelTV = new JPanel();
		comboPanelTV.add(scroolList);
		cortarPlanesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = { "Aceptar", "Cancelar" };
				int n = JOptionPane.showOptionDialog(panelCB,
						"Recuerde que " + labelInfoCorteTV.getText().toLowerCase() + " Desea seguir con el proceso?",
						"Seguro que desea seguir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, null);
				if (n == 0) {
					flagAction = "Cortar TV";
					hilo = new Thread(CuadraturaUI.this);
					hilo.start();
					cortarPlanesBtn.setEnabled(false);
				}
			}
		});
		comboPanelTV.add(cortarPlanesBtn);
		cortarPlanesBtn.setEnabled(false);
		panelFileCargado.add(comboPanelTV);
		labelInfoCorteTV = new JLabel(" ");
		JPanel panelAlertCanales = new JPanel();
		labelInfoCorteTV.setText("Seleccione los Ruts para bajar TV ");
		panelAlertCanales.add(labelInfoCorteTV);
		panelFileCargado.add(panelAlertCanales);
		cargaArchivo.add(panelChooser);
		cargaArchivo.add(panelFileCargado);
		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(BorderFactory.createTitledBorder("Consola de Corte o bloqueo"));
		panelCB.setLayout(new GridLayout(2, 1));
		panelCB.add(cargaArchivo);
		statusProcessTV = new JProgressBar();
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));
		JPanel panelStatusProgress = new JPanel();
		statusProcessTV = new JProgressBar();
		panelStatusProgress.add(statusProcessTV);
		consolePanel.add(panelStatusProgress);
		jTextAreaStatusProcessTV = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(jTextAreaStatusProcessTV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension tamanhoTextArea = jTextAreaStatusProcessTV.getSize();
		Point p = new Point(0,tamanhoTextArea.height);
		scrollPane.getViewport().setViewPosition(p);
		consolePanel.add(scrollPane);
		panelCB.add(consolePanel);
		return panelCB;
	}

	/**
	 * 
	 * @param panelChooser
	 */
	private void showFileChooserCorteCanales(JPanel panelChooser) {
		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
		fileDialogCorteCanalesAdi.setFileFilter(filtro);
		showFileDialogCorteCanalesAdiButton.setEnabled(true);
		showFileDialogCorteCanalesAdiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogCorteCanalesAdi.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelCorteCanales.setText(fileDialogCorteCanalesAdi.getSelectedFile().getAbsolutePath());
					cargarDatosBtn.setEnabled(true);
				}
			}
		});

		pathLabelCorteCanales = new JLabel("Seleccione archivo de canales a cortar (kaltura)", SwingConstants.LEFT);
		pathLabelCorteCanales.setEnabled(false);
		pathLabelCorteCanales.setPreferredSize(new Dimension(261, 16));
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelCorteCanales, pathConstrains);
		panelChooser.add(showFileDialogCorteCanalesAdiButton, showFileDialogConstrains);

	}
	
	/**
	 * 
	 * @param panelChooser
	 */
	private void showFileChooserCortePlanesTV(JPanel panelChooser) {
		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
		fileDialogCortePlanesTV.setFileFilter(filtro);
		showFileDialogCortePlanesTVButton.setEnabled(true);
		showFileDialogCortePlanesTVButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogCortePlanesTV.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelCorteCanales.setText(fileDialogCortePlanesTV.getSelectedFile().getAbsolutePath());
					cargarDatosTVBtn.setEnabled(true);
				}
			}
		});

		pathLabelCorteCanales = new JLabel("Seleccione archivo de Planes de Televisión", SwingConstants.LEFT);
		pathLabelCorteCanales.setEnabled(false);
		pathLabelCorteCanales.setPreferredSize(new Dimension(261, 16));
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelCorteCanales, pathConstrains);
		panelChooser.add(showFileDialogCortePlanesTVButton, showFileDialogConstrains);

	}

	/**
	 * 
	 * @param panelChooser
	 */
	private void showFileChooser3playIntenet(JPanel panelChooser) {
		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
		fileDialogInternet.setFileFilter(filtro);
		showFileDialogInternetButton.setEnabled(false);
		showFileDialogInternetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogInternet.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelInternet.setText(fileDialogInternet.getSelectedFile().getAbsolutePath());
				}
			}
		});

		pathLabelInternet = new JLabel("Seleccione archivo Internet AAA (Splunk)", SwingConstants.LEFT);
		pathLabelInternet.setEnabled(false);
		pathLabelInternet.setPreferredSize(new Dimension(261, 16));
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelInternet, pathConstrains);
		panelChooser.add(showFileDialogInternetButton, showFileDialogConstrains);

	}

	/**
	 * 
	 * @param panelChooser
	 */
	private void showFileChooser3playTvPlanesBase(JPanel panelChooser) {

		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
		showFileDialogTvPlanesBaseButton.setEnabled(false);
		fileDialogTvPlanesBase.setFileFilter(filtro);
		showFileDialogTvPlanesBaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogTvPlanesBase.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelTvPlanesBase.setText(fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath());
				}
			}
		});

		pathLabelTvPlanesBase = new JLabel("Seleccione archivo Planes Base (Splunk)", SwingConstants.LEFT);
		pathLabelTvPlanesBase.setEnabled(false);
		pathLabelTvPlanesBase.setPreferredSize(new Dimension(261, 16));
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelTvPlanesBase, pathConstrains);
		panelChooser.add(showFileDialogTvPlanesBaseButton, showFileDialogConstrains);
	}

	/**
	 * 
	 * @param panelChooser
	 */
	private void showFileChooser3playTvAdicionales(JPanel panelChooser) {

		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
		showFileDialogAdicionalesButton.setEnabled(false);
		fileDialogTvAdicionales.setFileFilter(filtro);
		showFileDialogAdicionalesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogTvAdicionales.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelTvAdicionales.setText(fileDialogTvAdicionales.getSelectedFile().getAbsolutePath());
				}
			}
		});

		pathLabelTvAdicionales = new JLabel("Seleccione archivo Planes Adicionales (Splunk)", SwingConstants.LEFT);
		pathLabelTvAdicionales.setEnabled(false);
		pathLabelTvAdicionales.setPreferredSize(new Dimension(261, 16));
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelTvAdicionales, pathConstrains);
		panelChooser.add(showFileDialogAdicionalesButton, showFileDialogConstrains);
	}

	/**
	 * 
	 * @param panelChooser
	 */
	private void showFileChooser3playKenan(JPanel panelChooser) {

		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
		showFileDialogKenanButton.setEnabled(false);
		fileDialogKenan.setFileFilter(filtro);
		showFileDialogKenanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogKenan.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelKenan.setText(fileDialogKenan.getSelectedFile().getAbsolutePath());
				}
			}
		});

		pathLabelKenan = new JLabel("Seleccione archivo Kenan (Splunk)", SwingConstants.LEFT);
		pathLabelKenan.setEnabled(false);
		pathLabelKenan.setPreferredSize(new Dimension(261, 16));
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelKenan, pathConstrains);
		panelChooser.add(showFileDialogKenanButton, showFileDialogConstrains);
	}

	/**
	 * 
	 * @param panelChooser
	 */
	private void showFileChooser3playKenanAdi(JPanel panelChooser) {

		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;

		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
		showFileDialogKenanAdiButton.setEnabled(false);
		fileDialogKenanAdi.setFileFilter(filtro);
		showFileDialogKenanAdiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogKenanAdi.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelKenanAdi.setText(fileDialogKenanAdi.getSelectedFile().getAbsolutePath());
				}
			}
		});

		pathLabelKenanAdi = new JLabel("Seleccione archivo Kenan Adicionales (Splunk)", SwingConstants.LEFT);
		pathLabelKenanAdi.setEnabled(false);
		pathLabelKenanAdi.setPreferredSize(new Dimension(261, 16));
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelKenanAdi, pathConstrains);
		panelChooser.add(showFileDialogKenanAdiButton, showFileDialogConstrains);
	}

	@Override
	public void run() {

		if (flagAction.equalsIgnoreCase("Iniciar")) {
			Map<String, CountOBJ> mapResult = new HashMap<String, CountOBJ>();
			if (!chTodos.isSelected()) {
				/* INTERNET AAA */
				if (chTresPlayAAA.isSelected()) {
					bdManager.descargarCSV("INTERNET",jTextAreaStatusProcess);
					bdManager.actualiza("AAA", fileDialogInternet.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					bdManager.actualiza("INTERNET", null,jTextAreaStatusProcess);
					mapResult.put("TPLAY_AAA", bdManager.obtenerCruces("TPLAY_AAA",jTextAreaStatusProcess));
					/* TV BASE KALTURA */
				} else if (chTresPlayKalturaBase.isSelected()) {
					bdManager.descargarCSV("TV",jTextAreaStatusProcess);
					if(null!= pathLabelTodoTvKaltura && !("NO").equals(pathLabelTodoTvKaltura)){
						bdManager.actualiza("TODO_KALTURA", pathLabelTodoTvKaltura,jTextAreaStatusProcess);
					}
					bdManager.actualiza("KALTURA", fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					bdManager.actualiza("TV", null,jTextAreaStatusProcess);// BD
					mapResult.put("TPLAY_KALTURA", bdManager.obtenerCruces("TPLAY_KALTURA",jTextAreaStatusProcess));
					/* CANALES ADICIONALES KALTURA */
				} else if (chTresPlayKalturaAdi.isSelected()) {
					bdManager.descargarCSV("TV",jTextAreaStatusProcess);
					bdManager.actualiza("KALTURA_C", fileDialogTvAdicionales.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					bdManager.actualiza("TV", null,jTextAreaStatusProcess);
					mapResult.put("TPLAY_KALTURA_C", bdManager.obtenerCruces("TPLAY_KALTURA_C",jTextAreaStatusProcess));
					/* TELEFONIA OTCAR */
				} else if (chTresPlayOTCARTel.isSelected()) {
					bdManager.descargarCSV("TLF",jTextAreaStatusProcess);
					bdManager.descargarCSV("OTCAR",jTextAreaStatusProcess);
					bdManager.actualiza("TLF", null,jTextAreaStatusProcess);
					bdManager.actualiza("OTCAR", null,jTextAreaStatusProcess);
					mapResult.put("TPLAY_OTCAR", bdManager.obtenerCruces("TPLAY_OTCAR",jTextAreaStatusProcess));
					/* INTERNET KENAN */
				} else if (chTresPlayKenanInter.isSelected()) {
					bdManager.descargarCSV("INTERNET",jTextAreaStatusProcess);
					bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					bdManager.actualiza("INTERNET", null,jTextAreaStatusProcess);
					bdManager.actualiza("KENAN_62", null,jTextAreaStatusProcess);
					mapResult.put("TPLAY_KENAN_INT", bdManager.obtenerCruces("TPLAY_KENAN_INT",jTextAreaStatusProcess));
					mapResult.put("TPLAY_KENAN_INT_62", bdManager.obtenerCruces("TPLAY_KENAN_INT_62",jTextAreaStatusProcess));
					/* TV BASE KENAN */
				} else if (chTresPlayKenanTVBase.isSelected()) {
					bdManager.descargarCSV("TV",jTextAreaStatusProcess);
					bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					bdManager.actualiza("TV", null,jTextAreaStatusProcess);
					bdManager.actualiza("KENAN_62", null,jTextAreaStatusProcess);
					mapResult.put("TPLAY_KENAN_TV", bdManager.obtenerCruces("TPLAY_KENAN_TV",jTextAreaStatusProcess));
					mapResult.put("TPLAY_KENAN_TV_62", bdManager.obtenerCruces("TPLAY_KENAN_TV_62",jTextAreaStatusProcess));
					/* CANALES ADICIONALES KENAN */
				} else if (chTresPlayKenanTVAdi.isSelected()) {
					bdManager.descargarCSV("TV",jTextAreaStatusProcess);
					bdManager.actualiza("KENAN_C", fileDialogKenanAdi.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					bdManager.actualiza("TV", null,jTextAreaStatusProcess);
					bdManager.actualiza("KENAN_62", null,jTextAreaStatusProcess);
					mapResult.put("TPLAY_KENAN_C", bdManager.obtenerCruces("TPLAY_KENAN_C",jTextAreaStatusProcess));
					mapResult.put("TPLAY_KENAN_C_62", bdManager.obtenerCruces("TPLAY_KENAN_C_62",jTextAreaStatusProcess));
					/* TELEFONIA KENAN */
				} else if (chTresPlayKenanTel.isSelected()) {
					bdManager.descargarCSV("TV",jTextAreaStatusProcess);
					bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					bdManager.actualiza("TV", null,jTextAreaStatusProcess);
					bdManager.actualiza("KENAN_62", null,jTextAreaStatusProcess);
					mapResult.put("TPLAY_KENAN_TLF", bdManager.obtenerCruces("TPLAY_KENAN_TLF",jTextAreaStatusProcess));
					mapResult.put("TPLAY_KENAN_TLF_62", bdManager.obtenerCruces("TPLAY_KENAN_TLF_62",jTextAreaStatusProcess));
				}

			} else {
				for (String s : PRODUCTOS_TPLAY) {

					if ("KENAN".equals(s)) {
						bdManager.actualiza(s, fileDialogKenan.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					} else if ("KENAN_C".equals(s)) {
						bdManager.actualiza(s, fileDialogKenanAdi.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					} else if ("KALTURA".equals(s)) {
						bdManager.actualiza(s, fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
						if(null!= pathLabelTodoTvKaltura && !("NO").equals(pathLabelTodoTvKaltura)){
							bdManager.actualiza("TODO_KALTURA", pathLabelTodoTvKaltura,jTextAreaStatusProcess);
						}
					} else if ("KALTURA_C".equals(s)) {
						bdManager.actualiza(s, fileDialogTvAdicionales.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					} else if ("AAA".equals(s)) {
						bdManager.actualiza(s, fileDialogInternet.getSelectedFile().getAbsolutePath(),jTextAreaStatusProcess);
					} else if ("KENAN_62".equals(s)) {
						bdManager.actualiza(s, null,jTextAreaStatusProcess);
					} else {
						bdManager.descargarCSV(s,jTextAreaStatusProcess);
						bdManager.actualiza(s, null,jTextAreaStatusProcess);
					}
				}
				for (String s : CRUCES_TPLAY) {
					mapResult.put(s, bdManager.obtenerCruces(s,jTextAreaStatusProcess));
					if (s.indexOf("KENAN") >= 0) {
						mapResult.put(s + "_62", bdManager.obtenerCruces(s + "_62",jTextAreaStatusProcess));
					}
				}
			}
			(new Correo()).enviarCorreo(mapResult);
		} else if (flagAction.equalsIgnoreCase("Cargar Datos")) {
			ArchivoUtil archivoUtil = new ArchivoUtil();
			mapCanales = archivoUtil.getCanales(fileDialogCorteCanalesAdi.getSelectedFile().getAbsolutePath());
			DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
			for (Iterator<String> iterator = mapCanales.keySet().iterator(); iterator.hasNext();) {
				String nomCanal = (String) iterator.next();
				defaultListModel.addElement(nomCanal);
			}
			listaCanales.setModel(defaultListModel);
		} else if(flagAction.equalsIgnoreCase("Cargar Ruts")) {
			
		} else if (flagAction.equalsIgnoreCase("Cortar Canales")) {
			int contador = 0;
			statusProcess.setValue(0);
			DesactivarCanales desactivarCanales = new DesactivarCanales();
			LogEliminacion.iniciarFichero("corte_canal");
			for (Iterator<List<FileCorteCanalesRow>> iterator = listaListaCanales.iterator(); iterator.hasNext();) {
				List<FileCorteCanalesRow> list = (List<FileCorteCanalesRow>) iterator.next();
				jTextAreaStatusProcess.setText("Se proceden a cortar el canal con codigo: "+list.get(0).getCodCanal());
				int contador2 = 0;
				for (Iterator<FileCorteCanalesRow> iterator2 = list.iterator(); iterator2.hasNext();) {
					FileCorteCanalesRow fileCorteCanalesRow = (FileCorteCanalesRow) iterator2.next();
					fileCorteCanalesRow = desactivarCanales.getCodServicioCanalesPremium(fileCorteCanalesRow);
					DesactivarCanalesResponseOBJ canalesResponseOBJ = desactivarCanales
							.desactivarCanalPremium(fileCorteCanalesRow);
					if (contador == 0) {
						jTextAreaStatusProcess.setText(
								"INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
										+ ";CODIGO_RESPONSE: " + canalesResponseOBJ.getCodResponse() + ";DESCRIPCION: "
										+ canalesResponseOBJ.getDescripcion());
						contador++;
					} else {
						jTextAreaStatusProcess.setText(jTextAreaStatusProcess.getText() + "\n" + "INFO;"
								+ fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
								+ ";CODIGO_RESPONSE: " + canalesResponseOBJ.getCodResponse() + ";DESCRIPCION: "
								+ canalesResponseOBJ.getDescripcion());
					}
					statusProcess.setStringPainted(true);
					statusProcess.setValue(calculoDeAvance(list.size(), ++contador2));
				}
			}
			LogEliminacion.cerrarFicheros("corte_canal");
		} else if (flagAction.equalsIgnoreCase("Cargar Ruts")) {
			ArchivoUtil archivoUtil = new ArchivoUtil();
			listaAllRuts = archivoUtil.getRutsCorteTV(fileDialogCortePlanesTV.getSelectedFile().getAbsolutePath());
			DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
			for (String rut : listaAllRuts) {
				defaultListModel.addElement(rut);
			}
			listaRuts.setModel(defaultListModel);
		}  else if (flagAction.equalsIgnoreCase("Cortar TV")) {
			int contador = 0;
			statusProcessTV.setValue(0);
			DesactivarTodoTV desactivarTodo = new DesactivarTodoTV();
			List<FileCorteCanalesRow> toDeleteFinal = new ArrayList<FileCorteCanalesRow>();
			LogEliminacion.iniciarFichero("corte_tv");
			jTextAreaStatusProcessTV.setText("Se proceden a validar en kenan los ruts seleccionados");
			for (String rutDelete : listaRutsCorte) {
				List<FileCorteCanalesRow> toDelete = desactivarTodo.validaFacturado(rutDelete);	
				if(null == toDelete){
					if (contador == 0) {
						jTextAreaStatusProcessTV.setText(
								"INFO;" + rutDelete + ";TV"
										+ ";CODIGO_RESPONSE: 1000;DESCRIPCION: "
										+ "SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY");
					} else {
						jTextAreaStatusProcessTV.setText(jTextAreaStatusProcessTV.getText() + "\n" + "INFO;"
								+ rutDelete + ";TV"
								+ ";CODIGO_RESPONSE: 1000;DESCRIPCION: "
								+ "SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY");
					}
				} else if(toDelete.isEmpty()){
					if (contador == 0) {
						jTextAreaStatusProcessTV.setText(
								"INFO;" + rutDelete + ";TV"
										+ ";CODIGO_RESPONSE: 1001;DESCRIPCION: "
										+ "NO SE ENCUENTRA RUT EN COMPENDIO KALTURA");
					} else {
						jTextAreaStatusProcessTV.setText(jTextAreaStatusProcessTV.getText() + "\n" + "INFO;"
								+ rutDelete + ";TV"
								+ ";CODIGO_RESPONSE: 1001;DESCRIPCION: "
								+ "NO SE ENCUENTRA RUT EN COMPENDIO KALTURA");
					}
				} else { 
					toDeleteFinal.addAll(toDelete);
				}
			}
			
			for (FileCorteCanalesRow producto : toDeleteFinal) {
				DesactivarCanalesResponseOBJ canalesResponseOBJ = desactivarTodo.desactivarModulo(producto);
				if (contador == 0) {
					jTextAreaStatusProcessTV.setText(
							"INFO;" + producto.getRutConDv() + ";" + producto.getCodCanal()
									+ ";CODIGO_RESPONSE: " + canalesResponseOBJ.getCodResponse() + ";DESCRIPCION: "
									+ canalesResponseOBJ.getDescripcion());
				} else {
					jTextAreaStatusProcessTV.setText(jTextAreaStatusProcessTV.getText() + "\n" + "INFO;"
							+ producto.getRutConDv() + ";" + producto.getCodCanal()
							+ ";CODIGO_RESPONSE: " + canalesResponseOBJ.getCodResponse() + ";DESCRIPCION: "
							+ canalesResponseOBJ.getDescripcion());
				}
				statusProcessTV.setStringPainted(true);
				statusProcessTV.setValue(calculoDeAvance(toDeleteFinal.size(), ++contador));
			}
			listaAllRuts.removeAll(listaRutsCorte);
			LogEliminacion.cerrarFicheros("corte_tv");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JButton) {
			JButton btn = (JButton) o;
			if (btn.getText().equals("Iniciar")) {
				if (chTodos.isSelected()||chTresPlayKalturaBase.isSelected()){
					Object[] options = { "Aceptar", "Cancelar" };
					pathLabelTodoTvKaltura="NO";
					int n = JOptionPane.showOptionDialog(panel3Play,
							"Desea cargar archivo compendio de servicios Kaltura?",
							"ARCHIVO TODO KALTURA", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, null);
					if (n == 0) {
						FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.CSV", "csv");
						fileDialogTodoTvKaltura.setFileFilter(filtro);
						int returnVal = fileDialogTodoTvKaltura.showOpenDialog(mainFrame);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							pathLabelTodoTvKaltura=fileDialogTodoTvKaltura.getSelectedFile().getAbsolutePath();
						}
					}
				}

				flagAction = "Iniciar";
				hilo = new Thread(this);
				hilo.start();
				btn.setEnabled(false);
			} else if (btn.getText().equals("Cargar Datos")) {
				flagAction = "Cargar Datos";
				hilo = new Thread(this);
				hilo.start();
				btn.setEnabled(false);
			} else if (btn.getText().equals("Cargar Ruts")) {
				flagAction = "Cargar Ruts";
				hilo = new Thread(this);
				hilo.start();
				btn.setEnabled(false);
			}
		}

	}

	/**
	 * 
	 * @param size
	 * @param i
	 * @return
	 */
	private int calculoDeAvance(int size, int i) {
		double indice = i;
		double total = size;
		int porcentaje = (int) ((indice / total) * 100);
		return porcentaje;
	}
}
