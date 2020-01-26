package org.formation.gestionbiblio.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.WordBiblio;
import org.formation.gestionbiblio.model.technical.Authentificator;
import org.formation.gestionbiblio.model.technical.XmlParser;

/*
 * Manager/Controller en singleton
 */
public class BiblioController {

	private static BiblioController biblioControllerInstance;
	private Bibliotheque biblio;
	private WordBiblio exporteurWordBiblio;
	private Authentificator authentificator;

	/*
	 * Récup de l'instance du controller (singleton)
	 */
	public static BiblioController getInstance() {
		if (biblioControllerInstance == null) {
			biblioControllerInstance = new BiblioController();
			biblioControllerInstance.exporteurWordBiblio = new WordBiblio();
			biblioControllerInstance.biblio = new Bibliotheque();
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
	public void exportBiblioAsWord() throws IOException {
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

}