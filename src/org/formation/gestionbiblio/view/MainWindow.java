package org.formation.gestionbiblio.view;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;

import org.formation.gestionbiblio.controller.BiblioController;
import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre.Auteur;
import org.formation.gestionbiblio.model.technical.BiblioService;
import org.formation.gestionbiblio.model.technical.XmlParser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;

import net.miginfocom.swing.MigLayout;
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
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainWindow {

	private JFrame frame;
	private JMenuBar menuBar;
	private JTable table;
	private JMenu mnFile;
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
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		this.mntmOpenFile = new JMenuItem("Open file");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		mnFile.add(mntmOpenFile);
		
		this.mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		
		mntmExportWord = new JMenuItem("Export Word");
		mntmExportWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BiblioController.getInstance().exportBiblioAsWord();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnFile.add(mntmExportWord);
		mnFile.add(mntmSave);
		
		this.mntmSaveAs = new JMenuItem("Save as");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});
		mnFile.add(mntmSaveAs);
		
		this.mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		frame.getContentPane().setLayout(null);
		
		this.scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1440, 300);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(BiblioService.getInstance().getBiblio());
		table.setForeground(new Color(0, 128, 128));
		table.setBackground(new Color(230, 230, 250));
		
		scrollPane.setViewportView(table);
		
		this.formulairePanel = new JPanel();
		formulairePanel.setBackground(Color.LIGHT_GRAY);
		formulairePanel.setBounds(10, 312, 804, 174);
		frame.getContentPane().add(formulairePanel);
		
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
		
		this.lblNewLabel = new JLabel();
		lblNewLabel.setBackground(Color.ORANGE);
		lblNewLabel.setBounds(474, 53, 215, 115);
		formulairePanel.add(lblNewLabel);
		
		this.panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(849, 312, 95, 171);
		frame.getContentPane().add(panel);
		
		this.btn_add = new JButton("+");
		panel.add(btn_add);
		
		this.btn_suppr = new JButton("-");
		panel.add(btn_suppr);
		
		/*
		 * Mise en place de tous les EventListeners
		 */
		this.setRowsEventListener();
		this.setTypeLivreEventListener();
		this.setValiderBtnEventListener();
		this.setAddSuppBtnsEventListeners();		
					
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
            	this.table.setModel(BiblioService.getInstance().getBiblio()); //MAJ du tableau côté vue depuis la biblio côté model métier
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Problème au niveau de l'importation du fichier, probablement que le fichier XML ne respecte pas les régles de la XSD");
				e.printStackTrace();
			}
        }
    }
    
    /*
     * EventListener sur le bouton valider pour mettre à jour les valeurs du tableau depuis le form
     */
    private void setValiderBtnEventListener() {
    	btnValider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					table.setValueAt(tf_titre.getText(),table.getSelectedRow(), 0);
					table.setValueAt(tf_auteur.getText().toString(),table.getSelectedRow(), 1);
					table.setValueAt(tf_presentation.getText(),table.getSelectedRow(), 2);
					table.setValueAt(tf_parution.getText().toString(),table.getSelectedRow(), 3);
					table.setValueAt(tf_colonne.getText(),table.getSelectedRow(), 4);
					table.setValueAt(tf_rangee.getText().toString(),table.getSelectedRow(), 5);
					BiblioService.getInstance().getBiblio().setValueAt(tf_imgUrl.getText().toString(),table.getSelectedRow(), 6);
					if(cb_type.getSelectedItem() != null)
						BiblioService.getInstance().getBiblio().setValueAt(cb_type.getSelectedItem().toString(), table.getSelectedRow(), 7);
					BiblioService.getInstance().getBiblio().setValueAt(tf_personne.getText(), table.getSelectedRow(), 8);
					
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
					
					BiblioService.getInstance().getBiblio().fireTableDataChanged();
				}

			});
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
            	else
            		panel_personne.setVisible(false);
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
    	                    tf_imgUrl.setText(BiblioService.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl());
    	                    cb_type.setSelectedItem(BiblioService.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getType());
    	                    
    	                    if(cb_type.getSelectedItem().toString().matches("Prete|Emprunte"))
    	                    	tf_personne.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
    	                    
    	                    /* Récupération de l'image */
    	                    if(BiblioService.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl() != null) {
    	                    	URL url = null;
    	                    	
    	                    	if(!BiblioService.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl().isEmpty()) {
    	                    		try {
    	    							url = new URL(BiblioService.getInstance().getBiblio().getLivre().get(table.getSelectedRow()).getImgUrl());
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
            	
                // add row to the model
                BiblioService.getInstance().getBiblio().removeRow(table.getSelectedRow());
                BiblioService.getInstance().getBiblio().fireTableDataChanged();
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
            	
            	BiblioService.getInstance().getBiblio().getLivre().add(newLivre);
            	BiblioService.getInstance().getBiblio().fireTableDataChanged();
            }
        });
    }
    
    /*
     * Méthode (appélée au click du bouton sauvegarder) pour mettre à jour le fichier XML
     */
    private boolean saveFile() {
    	try {
			XmlParser.marshal(BiblioService.getInstance().getBiblio(), this.file.getAbsolutePath());
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
    private boolean saveFileAs() {
    	FileDialog fDialog = new FileDialog(frame, "Save as", FileDialog.SAVE);
        fDialog.setVisible(true);
        String path = fDialog.getDirectory() + fDialog.getFile(); 
    	
    	try {
			XmlParser.marshal(BiblioService.getInstance().getBiblio(), (path + ".xml"));
			return true;
		} catch (Exception e) {
			System.out.println("erreur in SaveFile()");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
