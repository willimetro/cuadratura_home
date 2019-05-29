package cl.everis.cuadratura.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
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

public class CuadraturaUI{

	private final static String[] CRUCES_TPLAY = {
			"TPLAY_KALTURA",
			"TPLAY_KALTURA_C",
			"TPLAY_KENAN_TV",
			"TPLAY_KENAN_TLF",
			"TPLAY_KENAN_INT",
			"TPLAY_KENAN_C",
			"TPLAY_AAA",
			"TPLAY_OCTAR"};
	private final static String[] PRODUCTOS_TPLAY = {
			"INTERNET",
			"TV",
			"TLF",
			"OCTAR",
			"KENAN",
			"KENAN_C",
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
		iniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	Map<String, CountOBJ> mapResult = new HashMap<String, CountOBJ>();
		                if(!chTodos.isSelected()){
		                	//Archivo AAA Splunk
		                	if(chTresPlayAAA.isSelected()){
		                		bdManager.descargarCSV("INTERNET");//BD
		                		bdManager.actualiza("AAA", pathLabelInternet.getText());
		                		bdManager.actualiza("INTERNET", null);//BD
		                		mapResult.put("TPLAY_AAA", bdManager.obtenerCruces("TPLAY_AAA"));
		                	} else if(chTresPlayKalturaBase.isSelected()){
		                		bdManager.descargarCSV("TV");//BD
		                		bdManager.actualiza("KALTURA", pathLabelTvPlanesBase.getText());
		                		bdManager.actualiza("TV", null);//BD
		                		mapResult.put("TPLAY_KALTURA",bdManager.obtenerCruces("TPLAY_KALTURA"));
		                	} else if(chTresPlayKalturaAdi.isSelected()){
		                		bdManager.descargarCSV("TV");//BD
		                		bdManager.actualiza("KALTURA_C", pathLabelTvAdicionales.getText());
		                		bdManager.actualiza("TV", null);//BD
		                		mapResult.put("TPLAY_KALTURA_C",bdManager.obtenerCruces("TPLAY_KALTURA_C"));
		                	} else if(chTresPlayOTCARTel.isSelected()){
		                		bdManager.descargarCSV("TLF");
		                		bdManager.descargarCSV("OCTAR");
		                		bdManager.actualiza("TLF", null);
		                		bdManager.actualiza("OCTAR", null);//BD
		                		mapResult.put("TPLAY_OCTAR",bdManager.obtenerCruces("TPLAY_OCTAR"));
		                	} else if(chTresPlayKenanInter.isSelected()){
		                		bdManager.descargarCSV("INTERNET");//BD
		                		bdManager.actualiza("KENAN",  pathLabelKenan.getText());
		                		bdManager.actualiza("INTERNET", null);//BD
		                		mapResult.put("TPLAY_KENAN_INT",bdManager.obtenerCruces("TPLAY_KENAN_INT"));
		                	} else if(chTresPlayKenanTVBase.isSelected()){
		                		bdManager.descargarCSV("TV");//BD
		                		bdManager.actualiza("KENAN", pathLabelKenan.getText());
		                		bdManager.actualiza("TV", null);//BD
		                		mapResult.put("TPLAY_KENAN_TV",bdManager.obtenerCruces("TPLAY_KENAN_TV"));
		                	} else if(chTresPlayKenanTVAdi.isSelected()){
		                		bdManager.descargarCSV("TV");//BD
		                		bdManager.actualiza("KENAN_C", pathLabelKenanAdi.getText());
		                		bdManager.actualiza("TV", null);//BD
		                		mapResult.put("TPLAY_KENAN_C",bdManager.obtenerCruces("TPLAY_KENAN_C"));
		                	} else if(chTresPlayKenanTel.isSelected()){
		                		//bdManager.descargarCSV("KENAN", pathLabelKenan.getText());//Splunk
		                		bdManager.descargarCSV("TV");//BD
		                		bdManager.actualiza("KENAN", pathLabelKenan.getText());
		                		bdManager.actualiza("TV", null);//BD
		                		mapResult.put("TPLAY_KENAN_TLF",bdManager.obtenerCruces("TPLAY_KENAN_TLF"));
		                	}       	
		                	
		                } else {
		            		for (String s : PRODUCTOS_TPLAY) {
		            			bdManager.descargarCSV(s);
		            			//bdManager.actualiza(s);
		            		}
		                	for (String s: CRUCES_TPLAY){
		                		mapResult.put(s,bdManager.obtenerCruces(s));
		                	}
		                }
		                (new Correo()).enviarCorreo(mapResult);
		            }
		        });
			}
		});
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
					File file = fileDialogInternet.getSelectedFile();
					pathLabelInternet.setText(file.getAbsolutePath());
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
					File file = fileDialogTvPlanesBase.getSelectedFile();
					pathLabelTvPlanesBase.setText(file.getAbsolutePath());
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
					File file = fileDialogTvAdicionales.getSelectedFile();
					pathLabelTvAdicionales.setText(file.getAbsolutePath());
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
					File file = fileDialogKenan.getSelectedFile();
					pathLabelKenan.setText(file.getAbsolutePath());
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
					File file = fileDialogKenanAdi.getSelectedFile();
					pathLabelKenanAdi.setText(file.getAbsolutePath());
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
}
