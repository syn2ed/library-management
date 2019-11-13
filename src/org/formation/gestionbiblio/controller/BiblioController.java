package org.formation.gestionbiblio.controller;

import java.io.File;
import java.io.IOException;

import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.technical.BiblioService;
import org.formation.gestionbiblio.model.technical.XmlParser;
import org.formation.gestionbiblio.view.ExportBiblio;

/*
 * Manager/Controller en singleton
 */
public class BiblioController {

	private static BiblioController biblioControllerInstance;
	private ExportBiblio exporteurWordBiblio;

	/*
	 * Récup de l'instance du controller (singleton)
	 */
	public static BiblioController getInstance() {
		if (biblioControllerInstance == null) {
				biblioControllerInstance = new BiblioController();
				biblioControllerInstance.exporteurWordBiblio = new ExportBiblio();
		    }
		    return biblioControllerInstance;
	}
	
	/*
	 * Méthode affectant la bibliothèque reçue en paramètre à celle de BiblioService.
	 * La biblio est reçue au format fichier et est décomposé au format Bibliothèque avant d'être
	 * fournie au BiblioService
	 */
	public void setBiblio(File biblioFile) throws Exception {
		BiblioService.getInstance().setBiblio(XmlParser.unmarshal(biblioFile));
	}

	/*
	 * Méthode réalisant l'exportation du fichier word depuis l'exporteurWordBiblio
	 */
	public void exportBiblioAsWord() throws IOException {
		this.exporteurWordBiblio.exportFile();
	}
	
}