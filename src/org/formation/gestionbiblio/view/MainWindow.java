package org.formation.gestionbiblio.view;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.formation.gestionbiblio.controller.BiblioController;
import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre.Auteur;
import org.formation.gestionbiblio.model.technical.XmlParser;
import org.formation.gestionbiblio.utils.BiblioAppLogger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainWindow {

	private JFrame frame;
	private JMenuBar menuBar;
	private JTable table;
	private JMenu mnFile;
	private JMenu admin_registrations_Barbtn;
	private JMenuItem mntmOpenFile;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JMenu mnAbout;
	
	private JScrollPane scrollPane;
	private JPanel formulairePanel;
	private JLabel label_6;
	private JPanel panel_7;
	private JLabel label_3;
	private JPanel panel_9;
	private JLabel label_8;
	private JPanel panel_10;
	private JLabel label_9;
	private JPanel panel_11;
	private JLabel label_10;
	private JPanel panel_1;
	private JLabel label_2;
	private Object[] types;
	private JComboBox cb_type;
	private JPanel panel_2;
	private JLabel label;
	private JPanel panel_8;
	private JLabel Presentation;
	private JButton btnValider;
	private JPanel panel_4;
	private JLabel label_1;
	private JPanel panel_personne;
	private JLabel label_4;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JButton btn_add;
	private JButton btn_suppr;
	
	private JTextField textField_1;
	private JTextField tf_titre;
	private JTextField tf_presentation;
	private JTextField tf_parution;
	private JTextField tf_colonne;
	private JTextField tf_rangee;
	private JTextField tf_personne;
	private JTextField tf_auteur;
	private JTextField tf_imgUrl;
	private Image img;
	
	private File file;
	private JMenuItem mntmExportWord;
	private JMenuItem mntmExit;
	private JMenuItem mntmInfo;
	private JPanel panel_img;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	
	private TableRowSorter<TableModel> sorter;
	private JMenuItem admin_registrations_BarUnderbtn;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ImageIO.setUseCache(false);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiblioController.getInstance(); // to start AuthWindow
					AuthWindow authWindow = new AuthWindow();
					authWindow.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructor/entryPoint for Design
	 * @wbp.parser.entryPoint
	 */
	public MainWindow() {
		initialize(1);
		BiblioController.getInstance().setDbBiblioFromDb();
	}

	/**
	 * Create the application.
	 */
	public MainWindow(int role) {
		initialize(role);
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(int role) {
		this.frame = new JFrame();
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		admin_registrations_Barbtn = new JMenu("Inscriptions");
		menuBar.add(admin_registrations_Barbtn);
		
		this.admin_registrations_BarUnderbtn = new JMenuItem("G�rer");
		admin_registrations_BarUnderbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ACTIONS AU CLICK
			}
		});
		admin_registrations_Barbtn.add(admin_registrations_BarUnderbtn);
		
		this.mntmOpenFile = new JMenuItem("Open file");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		mnFile.add(mntmOpenFile);
		
		if(role > 0) {
			this.mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BiblioAppLogger.logger.info("File saved by " + BiblioController.getInstance()
							.getAuthentificator().getUserAuthentified().getUsername()); //logs
					saveFile();
				}
			});
			mnFile.add(mntmSave);
		}
		
		mntmExportWord = new JMenuItem("Export word");
		mntmExportWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BiblioAppLogger.logger.info("File exported by " + BiblioController.getInstance()
					.getAuthentificator().getUserAuthentified().getUsername()); //logs
					try {
						BiblioController.getInstance().exportBiblioAsWord();
					} catch (InvalidFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnFile.add(mntmExportWord);
		
		if(role > 0) {
			this.mntmSaveAs = new JMenuItem("Save as");
			mntmSaveAs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BiblioAppLogger.logger.info("File saved with \"save as\"by " + BiblioController.getInstance()
					.getAuthentificator().getUserAuthentified().getUsername()); //logs
					saveFileAs("Save XML as", "xml");
				}
			});
			mnFile.add(mntmSaveAs);
		}
		
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); // fermeture de la fenêtre 
			}
		});
		mnFile.add(mntmExit);
		
		this.mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Application de gestion de bibliothèque réalisée par Rémi, Mehdi & Ayoub");
			}
		});
		mnAbout.add(mntmInfo);
		frame.getContentPane().setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 0, 15);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 60, 1440, 300);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(BiblioController.getInstance().getBiblio());
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.GRAY);
		        return this;
		    }
		});
		
		
		table.setForeground(Color.DARK_GRAY);
		table.setBackground(new Color(230, 230, 250));
		
		scrollPane.setViewportView(table);
		
		if(role > 0) {
			this.formulairePanel = new JPanel();
			formulairePanel.setBackground(Color.LIGHT_GRAY);
			formulairePanel.setBounds(10, 380, 804, 174);
			frame.getContentPane().add(formulairePanel);
		}
			
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		this.label_6 = new JLabel("");
		
		this.panel_7 = new JPanel();
		panel_7.setBounds(5, 5, 174, 36);
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label_3 = new JLabel("Titre");
		panel_7.add(label_3);
		
		tf_titre = new JTextField();
		tf_titre.setColumns(10);
		panel_7.add(tf_titre);
		
		this.panel_9 = new JPanel();
		panel_9.setBounds(5, 46, 197, 36);
		panel_9.setBackground(Color.LIGHT_GRAY);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label_8 = new JLabel("Parution");
		panel_9.add(label_8);
		
		tf_parution = new JTextField();
		tf_parution.setColumns(10);
		panel_9.add(tf_parution);
		
		this.panel_10 = new JPanel();
		panel_10.setBounds(5, 87, 197, 36);
		panel_10.setBackground(Color.LIGHT_GRAY);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label_9 = new JLabel("Colonne");
		panel_10.add(label_9);
		
		tf_colonne = new JTextField();
		tf_colonne.setColumns(10);
		panel_10.add(tf_colonne);
		
		this.panel_11 = new JPanel();
		panel_11.setBounds(207, 87, 190, 36);
		panel_11.setBackground(Color.LIGHT_GRAY);
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label_10 = new JLabel("Rangee");
		panel_11.add(label_10);
		
		tf_rangee = new JTextField();
		tf_rangee.setColumns(10);
		panel_11.add(tf_rangee);
		
		this.panel_1 = new JPanel();
		panel_1.setBounds(5, 128, 197, 46);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label_2 = new JLabel("Type");
		panel_1.add(label_2);
		
		this.types = new Object[]{"Acquis", "Emprunte", "Prete"};
		this.cb_type = new JComboBox(types);
		panel_1.add(cb_type);
		
		this.panel_2 = new JPanel();
		panel_2.setBounds(184, 5, 187, 36);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label = new JLabel("Auteur");
		panel_2.add(label);
		
		tf_auteur = new JTextField();
		tf_auteur.setColumns(10);
		panel_2.add(tf_auteur);
		formulairePanel.setLayout(null);
		formulairePanel.add(panel_7);
		formulairePanel.add(panel_2);
		formulairePanel.add(panel_9);
		
		this.panel_8 = new JPanel();
		panel_8.setBounds(207, 46, 223, 36);
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.Presentation = new JLabel("Presentation");
		panel_8.add(Presentation);
		
		tf_presentation = new JTextField();
		tf_presentation.setColumns(10);
		panel_8.add(tf_presentation);
		formulairePanel.add(panel_8);
		formulairePanel.add(panel_10);
		formulairePanel.add(panel_11);
		formulairePanel.add(panel_1);
		
		this.btnValider = new JButton("Valider");
		btnValider.setBounds(711, 145, 87, 29);
		formulairePanel.add(btnValider);
		
		this.panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(474, 5, 215, 36);
		formulairePanel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label_1 = new JLabel("Image URL");
		panel_4.add(label_1);
		
		tf_imgUrl = new JTextField();
		tf_imgUrl.setColumns(10);
		panel_4.add(tf_imgUrl);
		
		this.panel_personne = new JPanel();
		panel_personne.setBounds(207, 132, 202, 36);
		formulairePanel.add(panel_personne);
		panel_personne.setBackground(Color.LIGHT_GRAY);
		panel_personne.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.label_4 = new JLabel("Personne");
		panel_personne.add(label_4);
		
		tf_personne = new JTextField();
		tf_personne.setColumns(10);
		panel_personne.add(tf_personne);
		
		panel_img = new JPanel();
		panel_img.setBounds(1200, 400, 200, 280);
		frame.getContentPane().add(panel_img);
		panel_img.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.lblNewLabel = new JLabel();
		lblNewLabel.setBackground(Color.ORANGE);
		//formulairePanel.add(lblNewLabel);
		panel_img.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Recherche");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_1.setBounds(6, 32, 71, 16);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		textField = new JTextField();
		textField.setBounds(82, 27, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		if(role > 0) {
			this.panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBounds(849, 380, 95, 171);
			frame.getContentPane().add(panel);
			
			this.btn_add = new JButton("+");
			panel.add(btn_add);
			
			this.btn_suppr = new JButton("-");
			panel.add(btn_suppr);
		}
		
		
		
		/*
		 * Mise en place de tous les EventListeners
		 */
		this.setRowsEventListener();
		this.setTypeLivreEventListener();
		this.setValiderBtnEventListener();
		this.setAddSuppBtnsEventListeners();
	}
	
	private void setRowSorter() {
		sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		
		this.setSearchFieldEventListener();
	}
	
	private void setSearchFieldEventListener() {
		textField.getDocument().addDocumentListener(new DocumentListener() {
	         @Override
	         public void insertUpdate(DocumentEvent e) {
	            search(textField.getText());
	         }
	         @Override
	         public void removeUpdate(DocumentEvent e) {
	            search(textField.getText());
	         }
	         @Override
	         public void changedUpdate(DocumentEvent e) {
	            search(textField.getText());
	         }
	         public void search(String str) {
	            if (str.length() == 0) {
	               sorter.setRowFilter(null);
	            } else {
	            	
	               sorter.setRowFilter(RowFilter.regexFilter(str));
	            }
	         }
	      });
	}

	/*
     * Fonction appelee au click du bouton "open file" afin de selectionner un fichier XML
     */
    private void openFile(){
    	JFileChooser chooser = new JFileChooser();
    	//Ces 2 lignes filtrent sur les fichiers XML en visualisation
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("XML FILES", "xml", "xml");
    	chooser.setFileFilter(filter);
        // r�cup�ration du fichier choisi...
        if (chooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
             // stockage du fichier ouvert
            try {
            	this.file = chooser.getSelectedFile(); // stockage du fichier ouvert
            	BiblioController.getInstance().setBiblio(chooser.getSelectedFile()); // MAJ de la biblio depuis le fichier importé
            	this.table.setModel(BiblioController.getInstance().getBiblio()); //MAJ du tableau côté vue depuis la biblio côté model métier
            	setRowSorter();
            	setSearchFieldEventListener();
            } catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problème au niveau de l'importation du fichier, probablement que le fichier XML ne respecte pas les régles de la XSD");
				e.printStackTrace();
			}
        }
        
        setSynchronizeDbButton();
    }
    
    /*
     * EventListener sur le bouton valider pour mettre à jour les valeurs du tableau depuis le form
     */
    private void setValiderBtnEventListener() {
    	btnValider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(isFormValid()) {
					table.setValueAt(tf_titre.getText(),table.getSelectedRow(), 0);
					table.setValueAt(tf_auteur.getText().toString(),table.getSelectedRow(), 1);
					table.setValueAt(tf_presentation.getText(),table.getSelectedRow(), 2);
					table.setValueAt(tf_parution.getText().toString(),table.getSelectedRow(), 3);
					table.setValueAt(tf_colonne.getText(),table.getSelectedRow(), 4);
					table.setValueAt(tf_rangee.getText().toString(),table.getSelectedRow(), 5);
					BiblioController.getInstance().getBiblio().setValueAt(tf_imgUrl.getText().toString(),table.getSelectedRow(), 6);
					if(cb_type.getSelectedItem() != null)
						BiblioController.getInstance().getBiblio().setValueAt(cb_type.getSelectedItem().toString(), table.getSelectedRow(), 7);
					BiblioController.getInstance().getBiblio().setValueAt(tf_personne.getText(), table.getSelectedRow(), 8);
					
					logBookEdit(tf_titre.getText(), tf_auteur.getText(), tf_presentation.getText(), tf_parution.getText(), 
							tf_colonne.getText(), tf_colonne.getText(), tf_rangee.getText(), cb_type.getSelectedItem().toString(), tf_personne.getText());
					/* 
					 * Refresh des champs du formulaire
					 */
					tf_titre.setText("");
					tf_auteur.setText("");
					tf_presentation.setText("");
					tf_parution.setText("");
					tf_colonne.setText("");
					tf_rangee.setText("");
					tf_imgUrl.setText("");
					lblNewLabel.removeAll();
					tf_personne.setText("");
					cb_type.setSelectedItem("Acquis");
					
					BiblioController.getInstance().getBiblio().fireTableDataChanged();
				}
					
			}

		});
    }
    
    private void logBookEdit(String title, String autor, String pres, String parution, String column, String row, String imgUrl, String type, String person) {
    	BiblioAppLogger.logger.info("Book edit by " + BiblioController.getInstance().getAuthentificator().getUserAuthentified().getUsername()
    			+ ": \n\t -title: " + title + "\n\t -autor: " + autor + "\n\t -presentation: " + pres
    			+ "\n\t -parution: " + parution + "\n\t -column: " + column + "\n\t -row: " + row 
    			+ "\n\t -imgUrl: " + imgUrl + "\n\t -type: " + type + "\n\t -person: " + person
				);
    }
    
    private void logBookDeletions(String title, String autor) {
    	BiblioAppLogger.logger.info("Book deleted by " + BiblioController.getInstance().getAuthentificator().getUserAuthentified().getUsername()
    			+ ": \n\t -title: " + title + "\n\t -autor: " + autor);
    }
    
    /*
     * Retourne vrai si les champs du formulaire (rangee, colonne, parution) respectent les critèrent d'acceptance
     */
    private boolean isFormValid() {
    	if(isColonneValid() && isRangeeValid() && isParutionValid())
    		return true;
    	return false;
    }
    
    /*
     * Retourne vrai si le champ "colonne" respecte le critère d'acceptance
     */
    private boolean isColonneValid() {
    	try {
    		int colonne = Integer.parseInt(this.tf_colonne.getText());
    		if(colonne < 8 && colonne >= 0)
    			return true;
    		else
    			JOptionPane.showMessageDialog(null, "Le champ colonnes doit contenir un chiffre de 0 à 7");
    		return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Le champ colonnes doit contenir un chiffre de 0 à 7");
			return false;
		}
    }
    
    /*
     * retourne vrai si le champ "rangee" respecte le critère d'acceptance
     */
    private boolean isRangeeValid() {
    	try {
    		int rangee = Integer.parseInt(this.tf_rangee.getText());
    		if(rangee < 6 && rangee >= 0)
    			return true;
    		else
    			JOptionPane.showMessageDialog(null, "Le champ rangee doit contenir un chiffre de 0 à 7");
    		return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Le champ rangee doit contenir un chiffre de 0 à 7");
			return false;
		}
    }
    
    /*
     * Retourne vrai si le champ "parution" respecte le critère d'acceptance
     */
    private boolean isParutionValid() {
    	try {
    		int parution = Integer.parseInt(this.tf_parution.getText());
    		if(parution <= Calendar.getInstance().get(Calendar.YEAR) && parution >= 0)
    			return true;
    		else
    			JOptionPane.showMessageDialog(null, "Le champ parution doit être une année comprise entre 0 et " + Calendar.getInstance().get(Calendar.YEAR));
    		return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Le champ parution doit être une année comprise entre 0 et " + Calendar.getInstance().get(Calendar.YEAR));
			return false;
		}
    }
    
    /*
     * EventListener sur le type du livre afin d'afficher ou non le champ "personne"
     */
    private void setTypeLivreEventListener() {
    	cb_type.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            	//System.out.println(livreType);
            	if(cb_type.getSelectedItem().toString().matches("Prete|Emprunte"))
            		panel_personne.setVisible(true);
            	else {
            		panel_personne.setVisible(false);
            		tf_personne.setText("");
            	}
            		
            }
        });
    }
    
 // EventListener sur le tableau pour la récupération des valeurs de la ligne sélectionnée pour le formulaire
    private void setRowsEventListener() {
    			table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
    		        public void valueChanged(ListSelectionEvent event) {
    		        	if (table.getSelectedRow() > -1) {
    	                    tf_titre.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
    	                    tf_auteur.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
    	                    tf_presentation.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
    	                    tf_parution.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
    	                    tf_colonne.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
    	                    tf_rangee.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
    	                    tf_imgUrl.setText(BiblioController.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl());
    	                    cb_type.setSelectedItem(BiblioController.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getType());
    	                    
    	                    if(cb_type.getSelectedItem().toString().matches("Prete|Emprunte"))
    	                    	tf_personne.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
    	                    
    	                    /* Récupération de l'image */
    	                    if(BiblioController.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl() != null) {
    	                    	URL url = null;
    	                    	
    	                    	if(!BiblioController.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl().isEmpty()) {
    	                    		try {
    	    							url = new URL(BiblioController.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl());
    	    						} catch (MalformedURLException e) {
    	    							System.out.println("URL INCORRECTE");
    	    							e.printStackTrace();
    	    						}
    	                        	try {
    	    							img = ImageIO.read(url);
    	    						} catch (IOException e) {
    	    							// TODO Auto-generated catch block
    	    							e.printStackTrace();
    	    						}
    	                        	
    	                        	lblNewLabel.removeAll();
    	                        	lblNewLabel.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
    	                        	
    	                    	}
    	                    	else {
    	                    		lblNewLabel.removeAll();
    	                    	}
    	                        
    	                        	//refresh pour récupérer nouvelle image
    	                        	//frame.repaint();
    	                    }
    	                }
    		        }
    		    });
    }
    
    private void setAddSuppBtnsEventListeners() {
    	btn_suppr.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
                
            	logBookDeletions(table.getValueAt(table.getSelectedRow(), 0).toString(),
            			table.getValueAt(table.getSelectedRow(), 1).toString()); // logs
            	BiblioController.getInstance().getBiblio().removeRow(table.getSelectedRow());
            	BiblioController.getInstance().getBiblio().fireTableDataChanged();
            }
        });
		
		// button add row
        btn_add.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	if(tf_titre.getText().length() == 0)
            	{
            		JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs dans les champs avant d'insérer un livre");
                	return;
            	}
            	
                // add row to the model
            	Auteur newAuteur = new Auteur();
            	newAuteur.setNom(tf_auteur.getText());
            	
            	Livre newLivre = new Livre();
            	newLivre.setTitre(tf_titre.getText());
            	newLivre.setAuteur(newAuteur);
            	newLivre.setPresentation(tf_presentation.getText());
            	newLivre.setParution(Integer.parseInt(tf_parution.getText()));
            	newLivre.setColonne(Short.parseShort(tf_colonne.getText()));
            	newLivre.setRangee(Short.parseShort(tf_rangee.getText()));
            	newLivre.setImgUrl(tf_imgUrl.getText());
            	newLivre.setPersonnePret(tf_personne.getText());
            	newLivre.setType(cb_type.getSelectedItem().toString());
            	
            	BiblioController.getInstance().getBiblio().getLivre().add(newLivre);
            	BiblioController.getInstance().getBiblio().fireTableDataChanged();
            }
        });
    }
    
    /*
     * Méthode (appélée au click du bouton sauvegarder) pour mettre à jour le fichier XML
     */
    private boolean saveFile() {
    	try {
			XmlParser.marshal(BiblioController.getInstance().getBiblio(), this.file.getAbsolutePath());
			return true;
		} catch (Exception e) {
			System.out.println("erreur in SaveFile()");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    /*
     * Méthode (appélée au click du bouton sauvegarder sous) pour sauvagarder un nouveau fichier XML 
     * dans le repertoire choisi
     */
    private boolean saveFileAs(String saveName, String format) {
    	FileDialog fDialog = new FileDialog(frame, saveName, FileDialog.SAVE);
        fDialog.setVisible(true);
        String path = fDialog.getDirectory() + fDialog.getFile(); 
    	
    	try {
			XmlParser.marshal(BiblioController.getInstance().getBiblio(), (path + "." + format));
			return true;
		} catch (Exception e) {
			System.out.println("erreur in SaveFile()");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
    
    /*
     * Mise en place du boutton synchronize (appelé quand un fichier
     * XML est importé)
     * + Event Listener pour synchronize la DB depuis le XML
     */
    private void setSynchronizeDbButton() {
    	JButton btnSynchronizeDbFrom = new JButton("Synchronize DB from XML");
		btnSynchronizeDbFrom.setBounds(499, 27, 189, 29);
		frame.getContentPane().add(btnSynchronizeDbFrom);
		
		btnSynchronizeDbFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(BiblioController.getInstance().getBiblio().getRowCount());
			}
		});
    }
    
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JFrame getFrame() {
		return frame;
	}
}
