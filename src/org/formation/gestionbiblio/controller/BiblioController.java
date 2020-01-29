package org.formation.gestionbiblio.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.WordBiblio;
import org.formation.gestionbiblio.model.technical.Authentificator;
import org.formation.gestionbiblio.model.technical.DbService;
import org.formation.gestionbiblio.model.technical.XmlParser;
import org.formation.gestionbiblio.view.AuthWindow;
import org.formation.gestionbiblio.view.MainWindow;
import org.formation.gestionbiblio.view.registerWindow;

/*
 * Manager/Controller en singleton
 */
public class BiblioController {

	private static BiblioController biblioControllerInstance;
	private Bibliotheque biblio;
	private WordBiblio exporteurWordBiblio;
	private Authentificator authentificator;
	private MainWindow mainWindow;
	private AuthWindow authWindow;
	private registerWindow registerWindow;
	private DbService dbService;

	/*
	 * Récup de l'instance du controller (singleton)
	 */
	public static BiblioController getInstance() {
		if (biblioControllerInstance == null) {
			biblioControllerInstance = new BiblioController();
			biblioControllerInstance.exporteurWordBiblio = new WordBiblio();
			biblioControllerInstance.biblio = new Bibliotheque();
			biblioControllerInstance.dbService = new DbService();
			biblioControllerInstance.registerWindow = new registerWindow();
			try {
				biblioControllerInstance.authWindow = new AuthWindow();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			biblioControllerInstance.mainWindow = new MainWindow();
			
			try {
				biblioControllerInstance.authentificator = new Authentificator();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return biblioControllerInstance;
	}

	/*
	 * Méthode affectant la bibliothèque reçue en paramètre à celle de
	 * BiblioService. La biblio est reçue au format fichier et est décomposé au
	 * format Bibliothèque avant d'être fournie au BiblioService
	 */
	public void setBiblio(File biblioFile) throws Exception {
		biblioControllerInstance.setBiblio(XmlParser.unmarshal(biblioFile));
	}

	/*
	 * Méthode réalisant l'exportation du fichier word depuis l'exporteurWordBiblio
	 */
	public void exportBiblioAsWord() throws IOException, InvalidFormatException {
		this.exporteurWordBiblio.exportFile();
	}

	public Bibliotheque getBiblio() {
		return biblio;
	}

	public void setBiblio(Bibliotheque biblio) {
		this.biblio = biblio;
	}

	public Authentificator getAuthentificator() {
		return authentificator;
	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}
	
	public AuthWindow getAuthWindow() {
		return authWindow;
	}

	/**
	 * Met à jour la biblio depuis la base de données
	 */
	public void setDbBiblioFromDb() {
		// TODO REFRESH LA BIBLIO DEPUIS LA DB à CHAQUE GET
		this.biblio = this.dbService.getBiblioFromDb();
		this.biblio.fireTableDataChanged();
	}

	/**
	 * Synchronisation du la DB depuis les données récupérées du XML
	 */
	public void synchronizeDbFromXmlBiblio() {
		this.dbService.synchronizeDbFromXmlBiblio(this.biblio);
	}
	
	public void getLivresThatAreNotInDbFromXml() {
		this.dbService.getLivresThatAreNotInDbFromXml(this.biblio);
	}

	public registerWindow getRegisterWindow() {
		return this.registerWindow;
	}

	public DbService getDbService() {
		return dbService;
	}

	public void updateDbBiblio() {
		this.dbService.updateDbBiblio(this.biblio.getLivre());
	}
	
	
}