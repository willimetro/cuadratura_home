package cl.everis.cuadratura.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import cl.everis.cuadratura.bd.BDManager;
import cl.everis.cuadratura.bd.BDManagerImpl;
import cl.everis.cuadratura.obj.CountOBJ;
import cl.everis.cuadratura.ws.Correo;

public class CuadraturaUI implements Runnable, ActionListener{

	private final static String[] CRUCES_TPLAY = {
			"TPLAY_KALTURA",
			"TPLAY_KALTURA_C",
			"TPLAY_KENAN_TV",
			"TPLAY_KENAN_TLF",
			"TPLAY_KENAN_INT",
			"TPLAY_KENAN_C",
			"TPLAY_AAA",
			"TPLAY_OTCAR"};
	private final static String[] PRODUCTOS_TPLAY = {
			"INTERNET",
			"TV",
			"TLF",
			"OTCAR",
			"KENAN",
			"KENAN_C",
			"KENAN_62",
			"KALTURA",
			"KALTURA_C",
			"AAA"};
	private JFrame mainFrame = null;
	private JProgressBar statusProcess;
	//CheckBox
	JCheckBox chTodos = new JCheckBox("Todos");
	JCheckBox chTresPlayAAA = new JCheckBox("Internet AAA");
	JCheckBox chTresPlayKalturaBase = new JCheckBox("TV Planes Base");
	JCheckBox chTresPlayKalturaAdi = new JCheckBox("TV Canales Adicionales");
	JCheckBox chTresPlayOTCARTel = new JCheckBox("Telefonia OTCAR");
	JCheckBox chTresPlayKenanInter = new JCheckBox("Kenan Internet");
	JCheckBox chTresPlayKenanTVBase = new JCheckBox("Kenan TV BASE");
	JCheckBox chTresPlayKenanTVAdi = new JCheckBox("Kenan TV Adicionales");
	JCheckBox chTresPlayKenanTel = new JCheckBox("Kenan Telefonía");
	
	//Botones para buscar los archivos (FileChooser)
	JButton showFileDialogKenanAdiButton = new JButton("Buscar");
	JButton showFileDialogKenanButton = new JButton("Buscar");
	JButton showFileDialogAdicionalesButton = new JButton("Buscar");
	JButton showFileDialogTvPlanesBaseButton = new JButton("Buscar");
	JButton showFileDialogInternetButton = new JButton("Buscar");
	
	private JLabel pathLabelTvPlanesBase;
	private JLabel pathLabelInternet;
	private JLabel pathLabelTvAdicionales;
	private JLabel pathLabelKenan;
	private JLabel pathLabelKenanAdi;
	private GridBagConstraints showFileDialogConstrains;
	final JFileChooser fileDialogTvPlanesBase = new JFileChooser();
	final JFileChooser fileDialogInternet = new JFileChooser();
	final JFileChooser fileDialogTvAdicionales = new JFileChooser();
	final JFileChooser fileDialogKenan = new JFileChooser();
	final JFileChooser fileDialogKenanAdi = new JFileChooser();
	private GridBagConstraints pathConstrains;
	
	private BDManager bdManager = new BDManagerImpl();
	
	private Thread hilo;
	
	JButton iniciar = new JButton("Iniciar");
	
	public CuadraturaUI() {
		mainFrame = new JFrame("Cuadratura Home");
		JTabbedPane jTabbedPane =  new JTabbedPane();
		JComponent tresPlay = makeTextPanel3Play("Panel #1");
		jTabbedPane.addTab("3 Play",tresPlay);
		jTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent inalambrico = makeTextPanelInalambrico("Panel #2");
		
		jTabbedPane.addTab("Inalámbrica", inalambrico);
		jTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		mainFrame.add(jTabbedPane, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800,600);
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
		showFileChooser3playKenan(panelChooser);
		showFileChooser3playKenanAdi(panelChooser);
		iniciar.setEnabled(false);
		panelChooser.add(iniciar);
		iniciar.addActionListener(this);
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
	    resultadosCuad.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.weightx = 1;
	    c.ipady = 20;
	    resultadosCuad.add(statusProcess,c);
		panel3Play.add(resultadosCuad);
		return panel3Play;
    }
	
	private void createMenuCheckFor3Play(JPanel panelCheck) {
		
		chTodos.setMnemonic(KeyEvent.VK_C);
		chTodos.setSelected(false);
		chTodos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTodos.isSelected()) {
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
					iniciar.setEnabled(false);
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
					iniciar.setEnabled(true);
				}
				
			}
		});
		
		chTresPlayAAA.setMnemonic(KeyEvent.VK_C);
		chTresPlayAAA.setSelected(false);
		chTresPlayAAA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayAAA.isSelected()){
					showFileDialogInternetButton.setEnabled(false);
					iniciar.setEnabled(false);
				} else {
					showFileDialogInternetButton.setEnabled(true);
					iniciar.setEnabled(true);
				}
			}
		});
		
		chTresPlayKalturaBase.setMnemonic(KeyEvent.VK_C);
		chTresPlayKalturaBase.setSelected(false);
		chTresPlayKalturaBase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayKalturaBase.isSelected()){
					showFileDialogTvPlanesBaseButton.setEnabled(false);
					iniciar.setEnabled(false);
				} else {
					showFileDialogTvPlanesBaseButton.setEnabled(true);
					iniciar.setEnabled(true);
				}
			}
		});
		
		chTresPlayKalturaAdi.setMnemonic(KeyEvent.VK_C);
		chTresPlayKalturaAdi.setSelected(false);
		chTresPlayKalturaAdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayKalturaAdi.isSelected()){
					showFileDialogAdicionalesButton.setEnabled(false);
					iniciar.setEnabled(false);
				} else {
					showFileDialogAdicionalesButton.setEnabled(true);
					iniciar.setEnabled(true);
				}
			}
		});
		
		chTresPlayOTCARTel.setMnemonic(KeyEvent.VK_C);
		chTresPlayOTCARTel.setSelected(false);
		chTresPlayOTCARTel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayOTCARTel.isSelected()){
					iniciar.setEnabled(false);
				} else {
					iniciar.setEnabled(true);
				}
			}
		});
		
		chTresPlayKenanInter.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanInter.setSelected(false);
		chTresPlayKenanInter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayKenanInter.isSelected()){
					showFileDialogKenanButton.setEnabled(false);
					iniciar.setEnabled(false);
				} else {
					showFileDialogKenanButton.setEnabled(true);
					iniciar.setEnabled(true);
				}
			}
		});
		
		chTresPlayKenanTVBase.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTVBase.setSelected(false);
		chTresPlayKenanTVBase.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayKenanTVBase.isSelected()){
					showFileDialogKenanButton.setEnabled(false);
					iniciar.setEnabled(false);
				} else {
					showFileDialogKenanButton.setEnabled(true);
					iniciar.setEnabled(true);
				}
			}
		});
		
		chTresPlayKenanTVAdi.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTVAdi.setSelected(false);
		chTresPlayKenanTVAdi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayKenanTVAdi.isSelected()){
					showFileDialogKenanAdiButton.setEnabled(false);
					iniciar.setEnabled(false);
				} else {
					showFileDialogKenanAdiButton.setEnabled(true);
					iniciar.setEnabled(true);
				}
			}
		});
		
		chTresPlayKenanTel.setMnemonic(KeyEvent.VK_C);
		chTresPlayKenanTel.setSelected(false);
		chTresPlayKenanTel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!chTresPlayKenanTel.isSelected()){
					showFileDialogKenanButton.setEnabled(false);
					iniciar.setEnabled(false);
				} else {
					showFileDialogKenanButton.setEnabled(true);
					iniciar.setEnabled(true);
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
					pathLabelInternet.setText("El archivo ha sido seleccionado                             ");
				}
			}
		});
		
		pathLabelInternet = new JLabel("Seleccione archivo Internet AAA               (Splunk)", SwingConstants.LEFT);
		pathLabelInternet.setEnabled(false);
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelInternet,pathConstrains);		
		panelChooser.add(showFileDialogInternetButton,showFileDialogConstrains);
		
	}
	
	private void showFileChooser3playTvPlanesBase(JPanel panelChooser){
		
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
					pathLabelTvPlanesBase.setText("El archivo ha sido seleccionado                             ");
				}
			}
		});
		
		pathLabelTvPlanesBase = new JLabel("Seleccione archivo Planes Base             (Splunk)", SwingConstants.LEFT); 
		pathLabelTvPlanesBase.setEnabled(false);
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelTvPlanesBase,pathConstrains);
		panelChooser.add(showFileDialogTvPlanesBaseButton,showFileDialogConstrains);
	}
	
	private void showFileChooser3playTvAdicionales(JPanel panelChooser){
		
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
					pathLabelTvAdicionales.setText("El archivo ha sido seleccionado                             ");
				}
			}
		});
		
		pathLabelTvAdicionales = new JLabel("Seleccione archivo Planes Adicionales (Splunk)", SwingConstants.LEFT); 
		pathLabelTvAdicionales.setEnabled(false);
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelTvAdicionales,pathConstrains);
		panelChooser.add(showFileDialogAdicionalesButton,showFileDialogConstrains);
	}
	
	private void showFileChooser3playKenan(JPanel panelChooser){
		
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
					pathLabelKenan.setText("El archivo ha sido seleccionado                             ");
				}
			}
		});
		
		pathLabelKenan = new JLabel("Seleccione archivo Kenan                         (Splunk)", SwingConstants.LEFT); 
		pathLabelKenan.setEnabled(false);
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelKenan,pathConstrains);
		panelChooser.add(showFileDialogKenanButton,showFileDialogConstrains);
	}
	
	private void showFileChooser3playKenanAdi(JPanel panelChooser){
		
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
					pathLabelKenanAdi.setText("El archivo ha sido seleccionado                             ");
				}
			}
		});
		
		pathLabelKenanAdi = new JLabel("Seleccione archivo Kenan Adicionales   (Splunk)", SwingConstants.LEFT); 
		pathLabelKenanAdi.setEnabled(false);
		pathConstrains = new GridBagConstraints();
		pathConstrains.insets = new Insets(0, 0, 0, 5);
		pathConstrains.fill = GridBagConstraints.HORIZONTAL;
		pathConstrains.gridwidth = 2;
		pathConstrains.gridx = 0;
		pathConstrains.gridy = 0;
		panelChooser.add(pathLabelKenanAdi,pathConstrains);
		panelChooser.add(showFileDialogKenanAdiButton,showFileDialogConstrains);
	}

	@Override
	public void run() {
		
		Map<String, CountOBJ> mapResult = new HashMap<String, CountOBJ>();
        if(!chTodos.isSelected()){
/* INTERNET AAA */
        	if(chTresPlayAAA.isSelected()){
        		bdManager.descargarCSV("INTERNET");
        		bdManager.actualiza("AAA", fileDialogInternet.getSelectedFile().getAbsolutePath());
        		bdManager.actualiza("INTERNET", null);
        		mapResult.put("TPLAY_AAA", bdManager.obtenerCruces("TPLAY_AAA"));
/* TV BASE KALTURA */
        	} else if(chTresPlayKalturaBase.isSelected()){
        		bdManager.descargarCSV("TV");
        		bdManager.actualiza("KALTURA", fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath());
        		bdManager.actualiza("TV", null);//BD
        		mapResult.put("TPLAY_KALTURA",bdManager.obtenerCruces("TPLAY_KALTURA"));
/* CANALES ADICIONALES KALTURA */
        	} else if(chTresPlayKalturaAdi.isSelected()){
        		bdManager.descargarCSV("TV");
        		bdManager.actualiza("KALTURA_C", fileDialogTvAdicionales.getSelectedFile().getAbsolutePath());
        		bdManager.actualiza("TV", null);
        		mapResult.put("TPLAY_KALTURA_C",bdManager.obtenerCruces("TPLAY_KALTURA_C"));
/* TELEFONIA OTCAR */
        	} else if(chTresPlayOTCARTel.isSelected()){
        		bdManager.descargarCSV("TLF");
        		bdManager.descargarCSV("OTCAR");
        		bdManager.actualiza("TLF", null);
        		bdManager.actualiza("OTCAR", null);
        		mapResult.put("TPLAY_OTCAR",bdManager.obtenerCruces("TPLAY_OTCAR"));
/* INTERNET KENAN */
        	} else if(chTresPlayKenanInter.isSelected()){
        		bdManager.descargarCSV("INTERNET");
        		bdManager.actualiza("KENAN",  fileDialogKenan.getSelectedFile().getAbsolutePath());
        		bdManager.actualiza("INTERNET", null);
        		bdManager.actualiza("KENAN_62", null);
        		mapResult.put("TPLAY_KENAN_INT",bdManager.obtenerCruces("TPLAY_KENAN_INT"));
        		mapResult.put("TPLAY_KENAN_INT_62",bdManager.obtenerCruces("TPLAY_KENAN_INT_62"));
/* TV BASE KENAN */
        	} else if(chTresPlayKenanTVBase.isSelected()){
        		bdManager.descargarCSV("TV");
        		bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath());
        		bdManager.actualiza("TV", null);
        		bdManager.actualiza("KENAN_62", null);
        		mapResult.put("TPLAY_KENAN_TV",bdManager.obtenerCruces("TPLAY_KENAN_TV"));
        		mapResult.put("TPLAY_KENAN_TV_62",bdManager.obtenerCruces("TPLAY_KENAN_TV_62"));
/* CANALES ADICIONALES KENAN */
        	} else if(chTresPlayKenanTVAdi.isSelected()){
        		bdManager.descargarCSV("TV");
        		bdManager.actualiza("KENAN_C", fileDialogKenanAdi.getSelectedFile().getAbsolutePath());
        		bdManager.actualiza("TV", null);
        		bdManager.actualiza("KENAN_62", null);
        		mapResult.put("TPLAY_KENAN_C",bdManager.obtenerCruces("TPLAY_KENAN_C"));
        		mapResult.put("TPLAY_KENAN_C_62",bdManager.obtenerCruces("TPLAY_KENAN_C_62"));
/* TELEFONIA KENAN */
        	} else if(chTresPlayKenanTel.isSelected()){
        		bdManager.descargarCSV("TV");
        		bdManager.actualiza("KENAN", fileDialogKenan.getSelectedFile().getAbsolutePath());
        		bdManager.actualiza("TV", null);
        		bdManager.actualiza("KENAN_62", null);
        		mapResult.put("TPLAY_KENAN_TLF",bdManager.obtenerCruces("TPLAY_KENAN_TLF"));
        		mapResult.put("TPLAY_KENAN_TLF_62",bdManager.obtenerCruces("TPLAY_KENAN_TLF_62"));
        	}       	
        	
        } else {
    		for (String s : PRODUCTOS_TPLAY) {
    			
    			if ("KENAN".equals(s)) {
    				bdManager.actualiza(s, fileDialogKenan.getSelectedFile().getAbsolutePath());
    			} else if ("KENAN_C".equals(s)) {
    				bdManager.actualiza(s, fileDialogKenanAdi.getSelectedFile().getAbsolutePath());
    			} else if ("KALTURA".equals(s)) {
    				bdManager.actualiza(s, fileDialogTvPlanesBase.getSelectedFile().getAbsolutePath());
    			} else if ("KALTURA_C".equals(s)) {
    				bdManager.actualiza(s, fileDialogTvAdicionales.getSelectedFile().getAbsolutePath());
    			} else if ("AAA".equals(s)) {
    				bdManager.actualiza(s, fileDialogInternet.getSelectedFile().getAbsolutePath());
    			}  else if ("KENAN_62".equals(s)) {
    				bdManager.actualiza(s, null);
    			} else {
    				bdManager.descargarCSV(s);
    				bdManager.actualiza(s, null);
    			}
    		}
        	for (String s: CRUCES_TPLAY){
        		mapResult.put(s,bdManager.obtenerCruces(s));
        		if (s.indexOf("KENAN")>=0){
        			mapResult.put(s+"_62",bdManager.obtenerCruces(s+"_62"));
        		}
        	}
        }
        (new Correo()).enviarCorreo(mapResult);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
        if( o instanceof JButton ) {
            JButton btn = (JButton)o;
            if( btn.getText().equals("Iniciar")) {
            	hilo = new Thread(this);
            	hilo.start();
            	btn.setEnabled(false);
            } 
        }
		
	}	
}
