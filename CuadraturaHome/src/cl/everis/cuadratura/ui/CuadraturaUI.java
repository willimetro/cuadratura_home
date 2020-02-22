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
import java.util.Collections;
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
import cl.everis.cuadratura.obj.ActivarDesactivarCanalesResponseOBJ;
import cl.everis.cuadratura.obj.CountOBJ;
import cl.everis.cuadratura.obj.FileCorteCanales;
import cl.everis.cuadratura.obj.FileCorteCanalesRow;
import cl.everis.cuadratura.obj.RespValidacionesOBJ;
import cl.everis.cuadratura.util.LogEliminacion;
import cl.everis.cuadratura.ws.Correo;
import cl.everis.cuadratura.ws.DesactivarCanales;
import cl.everis.cuadratura.ws.DesactivarTodoTV;

public class CuadraturaUI implements Runnable, ActionListener {

	private final static String[] CRUCES_TPLAY = { "TPLAY_KALTURA", "TPLAY_KALTURA_C", "TPLAY_KENAN_TV",
			"TPLAY_KENAN_TLF", "TPLAY_KENAN_INT", "TPLAY_KENAN_C", "TPLAY_AAA", "TPLAY_OTCAR" };
	private final static String[] CRUCES_CROS = { "INTERNET", "TV", "TLF", "ADICIONALES" };
	private final static String[] PRODUCTOS_TPLAY = { "INTERNET", "TV", "TLF", "OTCAR", "KENAN", "KENAN_C",
			"KALTURA", "KALTURA_C", "AAA", "SERV_RETIRADOS"};
	private JFrame mainFrame = null;
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
	JCheckBox chSoloCruces = new JCheckBox("Sólo Realizar Cruces");
	// Botones para buscar los archivos (FileChooser cruces)
	JButton showFileDialogKenanAdiButton = new JButton("Buscar");
	JButton showFileDialogKenanButton = new JButton("Buscar");
	JButton showFileDialogTvAdiButton = new JButton("Buscar");
	JButton showFileDialogTvAllButton = new JButton("Buscar");
	JButton showFileDialogTvBaseButton = new JButton("Buscar");
	JButton showFileDialogInternetButton = new JButton("Buscar");
	// Botones para buscar los archivos (FileChooser cortes)
	JButton showFileDialogCorteCanalesAdiButton = new JButton("Buscar");
	JButton showFileDialogCortePlanesTVButton = new JButton("Buscar");
	// labels para mostrar rutas seleccionadas e informaciones
	private JLabel pathLabelTvPlanesBase;
	private JLabel pathLabelAllTv;
	private JLabel pathLabelInternet;
	private JLabel pathLabelTvAdicionales;
	private JLabel pathLabelKenan;
	private JLabel pathLabelKenanAdi;
	private JLabel pathLabelCorteCanales;
	private JLabel labelInfoCanales;
	private JLabel labelInfoCorteTV;
	// choosers y constrains para dialogos de seleccion de cruces y cortes
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
	// BD manager para llamados a incios de procesos de cortes cruces cargas y descargas
	private BDManager bdManager = new BDManagerImpl();
	// flag para indicar la accion a ejecutar
	private String flagAction = "";
	// boten de procesos de cruce y cortes
	JButton iniciarBtn = new JButton("Iniciar");
	JButton cargarDatosBtn = new JButton("Cargar Datos");
	JButton cargarDatosTVBtn = new JButton("Cargar Ruts");
	JButton cortarBtn = new JButton("Cortar");
	JButton cortarPlanesBtn = new JButton("Cortar");
	//Text Area
	private JTextArea textAreaTplay;
	private JTextArea textAreaCorte;
	private JTextArea textAreaTV;
	//Progress Bar
	private JProgressBar statusProcessTplay;
	private JProgressBar statusProcessTV;
	private JProgressBar statusProcessCorte;
	// otros
	private JList<String> listaCanales = null;
	private JList<String> listaRuts = null;
	Map<String, FileCorteCanales> mapCanales = null;
	List<String> listaSeleccionada = null;
	List<String> listaAllRuts = null;
	List<String> listaRutsCorte = null;
	// filtro pra los chooser
	private FileNameExtensionFilter filtro = null;
	// booleaans para habilitar boton de inicio de cruce

	public CuadraturaUI() {
		mainFrame = new JFrame("Cuadratura Home");
		JTabbedPane jTabbedPane = new JTabbedPane();
		setConstrains();
		JComponent tresPlay = makeTextPanel3Play("Panel #1");
		jTabbedPane.addTab("3 Play", tresPlay);
		jTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent corteCanalesTV = makeTextPanelCorteBloqueo("Panel #2");
		jTabbedPane.addTab("Corte Canales TV", corteCanalesTV);
		jTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent cortePlanesTV = makeTextPanelCortePlanesTV("Panel #3");
		jTabbedPane.addTab("Corte Planes TV", cortePlanesTV);
		jTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		mainFrame.add(jTabbedPane, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
	}

	protected JComponent makeTextPanel3Play(String text) {

		JPanel panel3Play = new JPanel(false);
		JPanel tiposCuad = new JPanel();
		JPanel panelCheck = new JPanel();
		createMenuCheckFor3Play(panelCheck);
		JPanel panelChooser = new JPanel();
		showFileChooser3playIntenet(panelChooser);
		showFileChooser3playTvPlanesBase(panelChooser);
		showFileChooser3playTvAdicionales(panelChooser);
		showFileChooser3playTvAll(panelChooser);
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
		statusProcessTplay = new JProgressBar();
		resultadosCuad.setLayout(new BoxLayout(resultadosCuad, BoxLayout.Y_AXIS));
		JPanel panelStatusProgress = new JPanel();
		statusProcessTplay = new JProgressBar();
		panelStatusProgress.add(statusProcessTplay);
		resultadosCuad.add(panelStatusProgress);
		textAreaTplay = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textAreaTplay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension tamanhoTextArea = textAreaTplay.getSize();
		Point p = new Point(0,tamanhoTextArea.height);
		scrollPane.getViewport().setViewPosition(p);
		resultadosCuad.add(scrollPane);
		panel3Play.add(resultadosCuad);
		return panel3Play;
	}

	private void createMenuCheckFor3Play(JPanel panelCheck) {

		chTodos.setMnemonic(KeyEvent.VK_C);
		chTodos.setSelected(false);
		chTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTodos.isSelected();
				chTresPlayAAA.setSelected(toSet);
				chTresPlayAAA.setEnabled(!toSet);
				chTresPlayKalturaBase.setSelected(toSet);
				chTresPlayKalturaBase.setEnabled(!toSet);
				chTresPlayKalturaAdi.setSelected(toSet);
				chTresPlayKalturaAdi.setEnabled(!toSet);
				chTresPlayOTCARTel.setSelected(toSet);
				chTresPlayOTCARTel.setEnabled(!toSet);
				chTresPlayKenanInter.setSelected(toSet);
				chTresPlayKenanInter.setEnabled(!toSet);
				chTresPlayKenanTVBase.setSelected(toSet);
				chTresPlayKenanTVBase.setEnabled(!toSet);
				chTresPlayKenanTVAdi.setSelected(toSet);
				chTresPlayKenanTVAdi.setEnabled(!toSet);
				chTresPlayKenanTel.setSelected(toSet);
				chTresPlayKenanTel.setEnabled(!toSet);
				showFileDialogKenanAdiButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				showFileDialogKenanButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				showFileDialogTvAdiButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				showFileDialogTvBaseButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				showFileDialogTvAllButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				showFileDialogInternetButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				if (toSet && !chSoloCruces.isSelected()) {
					iniciarBtn.setEnabled(validaInicioCruce());
				} else {
					iniciarBtn.setEnabled(toSet && chSoloCruces.isSelected());
				}
			}
		});

		chSoloCruces.setMnemonic(KeyEvent.VK_C);
		chSoloCruces.setSelected(false);
		chSoloCruces.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isAnySelect = isAnySelect();
				if(chSoloCruces.isSelected()) {
					showFileDialogKenanAdiButton.setEnabled(false);
					showFileDialogKenanButton.setEnabled(false);
					showFileDialogTvAdiButton.setEnabled(false);
					showFileDialogTvBaseButton.setEnabled(false);
					showFileDialogTvAllButton.setEnabled(false);
					showFileDialogInternetButton.setEnabled(false);
					iniciarBtn.setEnabled(isAnySelect);
				} else {
					showFileDialogKenanAdiButton.setEnabled(chTresPlayKenanTVAdi.isSelected());
					showFileDialogKenanButton.setEnabled(chTresPlayKenanInter.isSelected()||chTresPlayKenanTVBase.isSelected()||chTresPlayKenanTel.isSelected());
					showFileDialogTvAdiButton.setEnabled(chTresPlayKalturaAdi.isSelected());
					showFileDialogTvBaseButton.setEnabled(chTresPlayKalturaBase.isSelected());
					showFileDialogTvAllButton.setEnabled(chTresPlayKalturaAdi.isSelected()||chTresPlayKalturaBase.isSelected());
					showFileDialogInternetButton.setEnabled(chTresPlayAAA.isSelected());
					iniciarBtn.setEnabled(isAnySelect&&validaInicioCruce());
				}
			}
		});
		
		chTresPlayAAA.setMnemonic(KeyEvent.VK_C);
		chTresPlayAAA.setSelected(false);
		chTresPlayAAA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTresPlayAAA.isSelected();
				showFileDialogInternetButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				setIfAnySelect();
			}
		});

		chTresPlayKalturaBase.setMnemonic(KeyEvent.VK_C);
		chTresPlayKalturaBase.setSelected(false);
		chTresPlayKalturaBase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTresPlayKalturaBase.isSelected();
				showFileDialogTvBaseButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				showFileDialogTvAllButton.setEnabled((toSet||chTresPlayKalturaAdi.isSelected())&&!chSoloCruces.isSelected());
				setIfAnySelect();
			}
		});

		chTresPlayKalturaAdi.setMnemonic(KeyEvent.VK_C);
		chTresPlayKalturaAdi.setSelected(false);
		chTresPlayKalturaAdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTresPlayKalturaAdi.isSelected();
				showFileDialogTvAdiButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				showFileDialogTvAllButton.setEnabled((toSet||chTresPlayKalturaBase.isSelected())&&!chSoloCruces.isSelected());
				setIfAnySelect();
			}
		});

		chTresPlayOTCARTel.setMnemonic(KeyEvent.VK_C);
		chTresPlayOTCARTel.setSelected(false);
		chTresPlayOTCARTel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setIfAnySelect();
			}
		});

		chTresPlayKenanInter.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanInter.setSelected(false);
		chTresPlayKenanInter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTresPlayKenanInter.isSelected();
				showFileDialogKenanButton.setEnabled((toSet||chTresPlayKenanTVBase.isSelected()||chTresPlayKenanTel.isSelected())&&!chSoloCruces.isSelected());
				setIfAnySelect();
			}
		});

		chTresPlayKenanTVBase.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTVBase.setSelected(false);
		chTresPlayKenanTVBase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTresPlayKenanTVBase.isSelected();
				showFileDialogKenanButton.setEnabled((toSet||chTresPlayKenanInter.isSelected()
						||chTresPlayKenanTel.isSelected())&&!chSoloCruces.isSelected());
				setIfAnySelect();
			}
		});

		chTresPlayKenanTVAdi.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTVAdi.setSelected(false);
		chTresPlayKenanTVAdi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTresPlayKenanTVAdi.isSelected();
				showFileDialogKenanAdiButton.setEnabled(toSet&&!chSoloCruces.isSelected());
				setIfAnySelect();
			}
		});

		chTresPlayKenanTel.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTel.setSelected(false);
		chTresPlayKenanTel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean toSet = chTresPlayKenanTel.isSelected();
				showFileDialogKenanButton.setEnabled((toSet||chTresPlayKenanInter.isSelected()
						||chTresPlayKenanTVBase.isSelected())&&!chSoloCruces.isSelected());
				setIfAnySelect();
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
		panelCheck.add(chSoloCruces);
	}

	protected JComponent makeTextPanelCorteBloqueo(String text) {
		JPanel panelCB = new JPanel(false);
		JPanel cargaArchivo = new JPanel();
		cargaArchivo.setBorder(BorderFactory.createTitledBorder("Configuración Corte Canales TV"));
		cargaArchivo.setLayout(new BoxLayout(cargaArchivo, BoxLayout.Y_AXIS));
		JPanel panelChooser = new JPanel();
		panelChooser.setBorder(BorderFactory.createTitledBorder("Paso 1 - Busque archivo y cárguelo"));
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
				listaSeleccionada = listaCanales.getSelectedValuesList();
				int cantidad = 0;
				for (Iterator<String> iterator = listaSeleccionada.iterator(); iterator.hasNext();) {
					String nomCanal = (String) iterator.next();
					cantidad = cantidad + mapCanales.get(nomCanal).getCountCanales();
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
		JPanel comboPanel = new JPanel();
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
					Thread hilo = new Thread(CuadraturaUI.this);
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
		statusProcessCorte = new JProgressBar();
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));
		JPanel panelStatusProgress = new JPanel();
		statusProcessCorte = new JProgressBar();
		panelStatusProgress.add(statusProcessCorte);
		consolePanel.add(panelStatusProgress);
		textAreaCorte = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textAreaCorte, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension tamanhoTextArea = textAreaCorte.getSize();
		Point p = new Point(0,tamanhoTextArea.height);
		scrollPane.getViewport().setViewPosition(p);
		consolePanel.add(scrollPane);
		panelCB.add(consolePanel);
		return panelCB;
	}

	protected JComponent makeTextPanelCortePlanesTV(String text) {
		JPanel panelCB = new JPanel(false);
		JPanel cargaArchivo = new JPanel();
		cargaArchivo.setBorder(BorderFactory.createTitledBorder("Configuración Corte Planes TV"));
		cargaArchivo.setLayout(new BoxLayout(cargaArchivo, BoxLayout.Y_AXIS));
		JPanel panelChooser = new JPanel();
		panelChooser.setBorder(BorderFactory.createTitledBorder("Paso 1 - Busque archivo y cárguelo"));
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
		.setBorder(BorderFactory.createTitledBorder("Paso 2 - Seleccione los RUTS para dar de baja"));
		JPanel comboPanelTV = new JPanel();
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
					Thread hilo = new Thread(CuadraturaUI.this);
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
		textAreaTV = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textAreaTV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		Dimension tamanhoTextArea = textAreaTV.getSize();
		Point p = new Point(0,tamanhoTextArea.height);
		scrollPane.getViewport().setViewPosition(p);
		consolePanel.add(scrollPane);
		panelCB.add(consolePanel);
		return panelCB;
	}

	private void showFileChooserCorteCanales(JPanel panelChooser) {
		
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

		pathLabelCorteCanales = new JLabel("Seleccione Archivo de Canales TV (kaltura)", SwingConstants.LEFT);
		pathLabelCorteCanales.setEnabled(false);
		pathLabelCorteCanales.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelCorteCanales, pathConstrains);
		panelChooser.add(showFileDialogCorteCanalesAdiButton, showFileDialogConstrains);

	}

	private void showFileChooserCortePlanesTV(JPanel panelChooser) {
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

		pathLabelCorteCanales = new JLabel("Seleccione Archivo de Planes de Televisión (Kaltura)", SwingConstants.LEFT);
		pathLabelCorteCanales.setEnabled(false);
		pathLabelCorteCanales.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelCorteCanales, pathConstrains);
		panelChooser.add(showFileDialogCortePlanesTVButton, showFileDialogConstrains);

	}

	private void showFileChooser3playIntenet(JPanel panelChooser) {
		fileDialogInternet.setFileFilter(filtro);
		showFileDialogInternetButton.setEnabled(false);
		showFileDialogInternetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogInternet.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelInternet.setText(fileDialogInternet.getSelectedFile().getAbsolutePath());
					iniciarBtn.setEnabled(validaInicioCruce());
				}
			}
		});

		pathLabelInternet = new JLabel("Archivo Internet AAA desde Splunk", SwingConstants.LEFT);
		pathLabelInternet.setEnabled(false);
		pathLabelInternet.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelInternet, pathConstrains);
		panelChooser.add(showFileDialogInternetButton, showFileDialogConstrains);

	}

	private void showFileChooser3playTvPlanesBase(JPanel panelChooser) {
		showFileDialogTvBaseButton.setEnabled(false);
		fileDialogTvPlanesBase.setFileFilter(filtro);
		showFileDialogTvBaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogTvPlanesBase.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelTvPlanesBase.setText(fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath());
					iniciarBtn.setEnabled(validaInicioCruce());
				}
			}
		});

		pathLabelTvPlanesBase = new JLabel("Archivo Planes Base TV desde Splunk", SwingConstants.LEFT);
		pathLabelTvPlanesBase.setEnabled(false);
		pathLabelTvPlanesBase.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelTvPlanesBase, pathConstrains);
		panelChooser.add(showFileDialogTvBaseButton, showFileDialogConstrains);
	}

	private void showFileChooser3playTvAdicionales(JPanel panelChooser) {
		showFileDialogTvAdiButton.setEnabled(false);
		fileDialogTvAdicionales.setFileFilter(filtro);
		showFileDialogTvAdiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogTvAdicionales.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelTvAdicionales.setText(fileDialogTvAdicionales.getSelectedFile().getAbsolutePath());
					iniciarBtn.setEnabled(validaInicioCruce());
				}
			}
		});

		pathLabelTvAdicionales = new JLabel("Archivo Canales Adicionales TV desde Splunk", SwingConstants.LEFT);
		pathLabelTvAdicionales.setEnabled(false);
		pathLabelTvAdicionales.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelTvAdicionales, pathConstrains);
		panelChooser.add(showFileDialogTvAdiButton, showFileDialogConstrains);
	}

	private void showFileChooser3playKenan(JPanel panelChooser) {
		showFileDialogKenanButton.setEnabled(false);
		fileDialogKenan.setFileFilter(filtro);
		showFileDialogKenanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogKenan.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelKenan.setText(fileDialogKenan.getSelectedFile().getAbsolutePath());
					iniciarBtn.setEnabled(validaInicioCruce());
				}
			}
		});

		pathLabelKenan = new JLabel("Archivo Planes Base Kenan desde Splunk", SwingConstants.LEFT);
		pathLabelKenan.setEnabled(false);
		pathLabelKenan.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelKenan, pathConstrains);
		panelChooser.add(showFileDialogKenanButton, showFileDialogConstrains);
	}

	private void showFileChooser3playKenanAdi(JPanel panelChooser) {
		showFileDialogKenanAdiButton.setEnabled(false);
		fileDialogKenanAdi.setFileFilter(filtro);
		showFileDialogKenanAdiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogKenanAdi.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelKenanAdi.setText(fileDialogKenanAdi.getSelectedFile().getAbsolutePath());
					iniciarBtn.setEnabled(validaInicioCruce());
				}
			}
		});

		pathLabelKenanAdi = new JLabel("Archivo Canales TV Kenan desde Splunk", SwingConstants.LEFT);
		pathLabelKenanAdi.setEnabled(false);
		pathLabelKenanAdi.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelKenanAdi, pathConstrains);
		panelChooser.add(showFileDialogKenanAdiButton, showFileDialogConstrains);
	}

	private void showFileChooser3playTvAll(JPanel panelChooser) {
		showFileDialogTvAllButton.setEnabled(false);
		fileDialogTodoTvKaltura.setFileFilter(filtro);
		showFileDialogTvAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialogTodoTvKaltura.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					pathLabelAllTv.setText(fileDialogTodoTvKaltura.getSelectedFile().getAbsolutePath());
					iniciarBtn.setEnabled(validaInicioCruce());
				}
			}
		});
		pathLabelAllTv = new JLabel("Archivo Todo Kaltura TV desde Splunk", SwingConstants.LEFT);
		pathLabelAllTv.setEnabled(false);
		pathLabelAllTv.setPreferredSize(new Dimension(261, 16));
		panelChooser.add(pathLabelAllTv, pathConstrains);
		panelChooser.add(showFileDialogTvAllButton, showFileDialogConstrains);
	}

	private void setConstrains() {
		filtro = new FileNameExtensionFilter("*.CSV", "csv");
		showFileDialogConstrains = new GridBagConstraints();
		showFileDialogConstrains.insets = new Insets(0, 0, 0, 5);
		showFileDialogConstrains.gridx = 3;
		showFileDialogConstrains.gridy = 0;
		showFileDialogConstrains.gridwidth = 2;
		showFileDialogConstrains.gridheight = 1;
		
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		
	}

	@Override
	public void run() {

		if (flagAction.equalsIgnoreCase("Iniciar")) {
			Map<String, CountOBJ> mapResult = new HashMap<String, CountOBJ>();
			if (!chTodos.isSelected()) {
					/* INTERNET AAA */
				if (chTresPlayAAA.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("INTERNET",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.descargarCSV("SERV_RETIRADOS",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("AAA", fileDialogInternet.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("INTERNET", null,textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("SERV_RETIRADOS", null,textAreaTplay).getText());
					}
					CountOBJ obj = bdManager.obtenerCruces("TPLAY_AAA",textAreaTplay);
					textAreaTplay.setText(obj.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_AAA", obj);
					/* TV BASE KALTURA */
				} else if (chTresPlayKalturaBase.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("TV",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TODO_KALTURA", fileDialogTodoTvKaltura.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("KALTURA", fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TV", null,textAreaTplay).getText());
					}
					CountOBJ obj = bdManager.obtenerCruces("TPLAY_KALTURA",textAreaTplay);
					textAreaTplay.setText(obj.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_KALTURA", obj);
					/* CANALES ADICIONALES KALTURA */
				} else if (chTresPlayKalturaAdi.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("TV",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TODO_KALTURA", fileDialogTodoTvKaltura.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("KALTURA_C", fileDialogTvAdicionales.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TV", null,textAreaTplay).getText());
					}
					CountOBJ obj = bdManager.obtenerCruces("TPLAY_KALTURA_C",textAreaTplay);
					textAreaTplay.setText(obj.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_KALTURA_C", obj);
					/* TELEFONIA OTCAR */
				} else if (chTresPlayOTCARTel.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("TLF",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.descargarCSV("OTCAR",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TLF", null,textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("OTCAR", null,textAreaTplay).getText());
					}
					CountOBJ obj = bdManager.obtenerCruces("TPLAY_OTCAR",textAreaTplay);
					textAreaTplay.setText(obj.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_OTCAR", obj);
					/* INTERNET KENAN */
				} else if (chTresPlayKenanInter.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("INTERNET",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("INTERNET", null,textAreaTplay).getText());
					}
					CountOBJ obj1 = bdManager.obtenerCruces("TPLAY_KENAN_INT",textAreaTplay);
					textAreaTplay.setText(obj1.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_KENAN_INT", obj1);
				} else if (chTresPlayKenanTVBase.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("TV",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TV", null,textAreaTplay).getText());
					}
					CountOBJ obj1 = bdManager.obtenerCruces("TPLAY_KENAN_TV",textAreaTplay);
					textAreaTplay.setText(obj1.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_KENAN_TV", obj1);
				} else if (chTresPlayKenanTVAdi.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("TV",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("KENAN_C", fileDialogKenanAdi.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TV", null,textAreaTplay).getText());
					}
					CountOBJ obj1 = bdManager.obtenerCruces("TPLAY_KENAN_C",textAreaTplay);
					textAreaTplay.setText(obj1.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_KENAN_C", obj1);
				} else if (chTresPlayKenanTel.isSelected()) {
					if(!chSoloCruces.isSelected()) {
						textAreaTplay.setText(bdManager.descargarCSV("TV",textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						textAreaTplay.setText(bdManager.actualiza("TV", null,textAreaTplay).getText());
					}
					CountOBJ obj1 = bdManager.obtenerCruces("TPLAY_KENAN_TLF",textAreaTplay);
					textAreaTplay.setText(obj1.getjTextAreaStatusProcess().getText());
					mapResult.put("TPLAY_KENAN_TLF", obj1);
				}

			} else {
				if(!chSoloCruces.isSelected()) {
					for (String s : PRODUCTOS_TPLAY) {
						if ("KENAN".equals(s)) {
							textAreaTplay.setText(bdManager.actualiza(s, fileDialogKenan.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						} else if ("KENAN_C".equals(s)) {
							textAreaTplay.setText(bdManager.actualiza(s, fileDialogKenanAdi.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						} else if ("KALTURA".equals(s)) {
							textAreaTplay.setText(bdManager.actualiza(s, fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
							textAreaTplay.setText(bdManager.actualiza("TODO_KALTURA", fileDialogTodoTvKaltura.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						} else if ("KALTURA_C".equals(s)) {
							textAreaTplay.setText(bdManager.actualiza(s, fileDialogTvAdicionales.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						} else if ("AAA".equals(s)) {
							textAreaTplay.setText(bdManager.actualiza(s, fileDialogInternet.getSelectedFile().getAbsolutePath(),textAreaTplay).getText());
						}else {
							textAreaTplay.setText(bdManager.descargarCSV(s,textAreaTplay).getText());
							textAreaTplay.setText(bdManager.actualiza(s, null,textAreaTplay).getText());
						}
					}
				}
				for (String s : CRUCES_TPLAY) {
					CountOBJ obj1 = bdManager.obtenerCruces(s,textAreaTplay);
					textAreaTplay.setText(obj1.getjTextAreaStatusProcess().getText());
					mapResult.put(s, obj1);
				}
				for (String s : CRUCES_CROS) {
					CountOBJ obj1 = bdManager.obtenerCrucesCros(s,textAreaTplay);
					textAreaTplay.setText(obj1.getjTextAreaStatusProcess().getText());
					mapResult.put(s, obj1);
				}
			}

			String mailsList = (String) JOptionPane.showInputDialog(mainFrame, 
					"Ingrese correo(s) separados por ';' y sin espacios, si cancela la operación o deja el campo vacío\n"
					+ "podrá recuperar el html de resultado en la carpeta de cuadraturas creada en el escritorio",
					"listado de correos para envío de resultados", 
					JOptionPane.QUESTION_MESSAGE);
			if (mailsList == null || "".equals(mailsList)) {
				(new Correo()).enviarCorreo(mapResult, null);
			} else {
				(new Correo()).enviarCorreo(mapResult, mailsList);
			}

		} else if (flagAction.equalsIgnoreCase("Cargar Datos")) {
			ArchivoUtil archivoUtil = new ArchivoUtil();
			mapCanales = archivoUtil.getCanales(fileDialogCorteCanalesAdi.getSelectedFile().getAbsolutePath());
			DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
			for (Iterator<String> iterator = mapCanales.keySet().iterator(); iterator.hasNext();) {
				String nomCanal = (String) iterator.next();
				defaultListModel.addElement(nomCanal);
			}
			listaCanales.setModel(defaultListModel);
		} else if (flagAction.equalsIgnoreCase("Cortar Canales")) {
			statusProcessCorte.setValue(0);
			DesactivarCanales desactivarCanales = new DesactivarCanales();
			LogEliminacion.iniciarFichero("corte_canal");
			for (Iterator<String> iterator = listaSeleccionada.iterator(); iterator.hasNext();) {
				String nomCanal = (String) iterator.next();
				List<FileCorteCanalesRow> list = mapCanales.get(nomCanal).getListaClientesCorte();;
				String codigo = list.get(0).getCodCanal();
				textAreaCorte.setText("Se proceden a cortar el canal con codigo: "+codigo+"\n");
				int contador2 = 0;
				for (Iterator<FileCorteCanalesRow> iterator2 = list.iterator(); iterator2.hasNext();) {
					FileCorteCanalesRow fileCorteCanalesRow = (FileCorteCanalesRow) iterator2.next();
					RespValidacionesOBJ facturado = new RespValidacionesOBJ();
					facturado.setToDelete(false);
					RespValidacionesOBJ objDeleteCDF = new RespValidacionesOBJ();
					objDeleteCDF.setToDelete(false);
					if("20752".equals(codigo)||"20753".equals(codigo)){
						objDeleteCDF = desactivarCanales.validaCDF(fileCorteCanalesRow);
						if (!objDeleteCDF.isToDelete()){
							textAreaCorte.setText(textAreaCorte.getText() + 
									"INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
									+ ";CODIGO_RESPONSE: " + objDeleteCDF.getResp().getCodResponse() + ";DESCRIPCION: "
									+ objDeleteCDF.getResp().getDescripcion()
									+ "\n");
						}
					} else {
						facturado = desactivarCanales.validaFacturado(fileCorteCanalesRow);
						if (!facturado.isToDelete()){
							textAreaCorte.setText(textAreaCorte.getText() + 
									"INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
									+ ";CODIGO_RESPONSE: " + facturado.getResp().getCodResponse() + ";DESCRIPCION: "
									+ facturado.getResp().getDescripcion()
									+ "\n");
						}
					}
					if (facturado.isToDelete() || objDeleteCDF.isToDelete()){
						fileCorteCanalesRow = desactivarCanales.getCodServicioCanalesPremium(fileCorteCanalesRow);
						ActivarDesactivarCanalesResponseOBJ canalesResponseOBJ = desactivarCanales
								.desactivarCanalPremium(fileCorteCanalesRow);
						textAreaCorte.setText(textAreaCorte.getText() + 
								"INFO;" + fileCorteCanalesRow.getRutConDv() + ";" + fileCorteCanalesRow.getCodCanal()
								+ ";CODIGO_RESPONSE: " + canalesResponseOBJ.getCodResponse() + ";DESCRIPCION: "
								+ canalesResponseOBJ.getDescripcion()
								+ "\n");
					}
					statusProcessCorte.setStringPainted(true);
					statusProcessCorte.setValue(calculoDeAvance(list.size(), ++contador2));
				}
				DefaultListModel<String> defaultListModel = (DefaultListModel<String>)listaCanales.getModel();
				defaultListModel.removeElement(nomCanal);
			}
			LogEliminacion.cerrarFicheros("corte_canal");
		} else if (flagAction.equalsIgnoreCase("Cargar Ruts")) {
			ArchivoUtil archivoUtil = new ArchivoUtil();
			listaAllRuts = archivoUtil.getRutsCorteTV(fileDialogCortePlanesTV.getSelectedFile().getAbsolutePath());
			Collections.sort(listaAllRuts); 
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
			String toAppend = "Se proceden a validar en kenan los ruts seleccionados\n";
			textAreaTV.setText(toAppend);
			listaAllRuts.removeAll(listaRutsCorte);
			DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
			for (String rut : listaAllRuts) {
				defaultListModel.addElement(rut);
			}
			for (String rutDelete : listaRutsCorte) {
				List<FileCorteCanalesRow> toDelete = desactivarTodo.validaFacturado(rutDelete);	
				if(null == toDelete){
					toAppend = toAppend	+ "INFO;" + rutDelete + ";TV"
							+ ";CODIGO_RESPONSE: 1000;DESCRIPCION: "
							+ "SE ENCUENTRA EN KENAN. REGULARIZAR EN 3 PLAY"
							+ "\n";
					contador++;
				} else if(toDelete.isEmpty()){
					toAppend = toAppend	+ "INFO;" + rutDelete + ";TV"
							+ ";CODIGO_RESPONSE: 1001;DESCRIPCION: "
							+ "NO SE ENCUENTRA RUT EN COMPENDIO KALTURA"
							+ "\n";
					contador++;
				} else { 
					toDeleteFinal.addAll(toDelete);
				}
			}
			
			textAreaTV.setText(toAppend);
			int size = toDeleteFinal.size() + contador;
			statusProcessTV.setStringPainted(true);
			statusProcessTV.setValue(calculoDeAvance(size, contador));
			
			for (FileCorteCanalesRow producto : toDeleteFinal) {
				ActivarDesactivarCanalesResponseOBJ canalesResponseOBJ = desactivarTodo.desactivarModulo(producto);
				textAreaTV.setText(textAreaTV.getText() + "INFO;"
						+ producto.getRutConDv() + ";" + producto.getCodCanal()
						+ ";CODIGO_RESPONSE: " + canalesResponseOBJ.getCodResponse() + ";DESCRIPCION: "
						+ canalesResponseOBJ.getDescripcion()
						+ "\n");
				statusProcessTV.setStringPainted(true);
				statusProcessTV.setValue(calculoDeAvance(size, ++contador));
			}
			listaRuts.setModel(defaultListModel);
			LogEliminacion.cerrarFicheros("corte_tv");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JButton) {
			JButton btn = (JButton) o;
			flagAction = btn.getText();
			Thread hilo = new Thread(this);
			hilo.start();
			btn.setEnabled(false);
		}

	}

	private int calculoDeAvance(int size, int i) {
		double indice = i;
		double total = size;
		int porcentaje = (int) ((indice / total) * 100);
		return porcentaje;
	}

	protected boolean isAnySelect() {
		boolean isAnySelect = chTresPlayKenanTVAdi.isSelected()|| chTresPlayKenanInter.isSelected()
				||chTresPlayKenanTVBase.isSelected()||chTresPlayKenanTel.isSelected()
				||chTresPlayKalturaAdi.isSelected()||chTresPlayKalturaBase.isSelected()
				||chTresPlayAAA.isSelected();
		return isAnySelect;
	}

	protected void setIfAnySelect() {
		boolean isAnySelect = isAnySelect();
		if (isAnySelect && !chSoloCruces.isSelected()) {
			iniciarBtn.setEnabled(validaInicioCruce());
		} else {
			iniciarBtn.setEnabled(isAnySelect && chSoloCruces.isSelected());
		}
	}
	
	protected boolean validaInicioCruce() {
		boolean aaa = (chTresPlayAAA.isSelected() && null != fileDialogInternet.getSelectedFile() 
				&& !fileDialogInternet.getSelectedFile().getAbsolutePath().isEmpty())||!chTresPlayAAA.isSelected();
		boolean bkal = (chTresPlayKalturaBase.isSelected() && null != fileDialogTvPlanesBase.getSelectedFile() 
				&& !fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath().isEmpty())||!chTresPlayKalturaBase.isSelected();
		boolean ckal = (chTresPlayKalturaAdi.isSelected() && null != fileDialogTvAdicionales.getSelectedFile() 
				&& !fileDialogTvAdicionales.getSelectedFile().getAbsolutePath().isEmpty())||!chTresPlayKalturaAdi.isSelected();
		boolean tkal = ((chTresPlayKalturaAdi.isSelected()||chTresPlayKalturaBase.isSelected()) && null != fileDialogTodoTvKaltura.getSelectedFile() 
				&& !fileDialogTodoTvKaltura.getSelectedFile().getAbsolutePath().isEmpty())||!(chTresPlayKalturaAdi.isSelected()||chTresPlayKalturaBase.isSelected());
		boolean bken = ((chTresPlayKenanTVBase.isSelected()||chTresPlayKenanTel.isSelected()||chTresPlayKenanInter.isSelected())
				&& null != fileDialogKenan.getSelectedFile()&& !fileDialogKenan.getSelectedFile().getAbsolutePath().isEmpty())
				||!(chTresPlayKenanTVBase.isSelected()||chTresPlayKenanTel.isSelected()||chTresPlayKenanInter.isSelected());
		boolean cken = chTresPlayKenanTVAdi.isSelected() && null != fileDialogKenanAdi.getSelectedFile() 
				&& !fileDialogKenanAdi.getSelectedFile().getAbsolutePath().isEmpty()||!chTresPlayKenanTVAdi.isSelected();
		return (aaa && bkal && ckal && tkal && bken && cken);
	}
	
}
