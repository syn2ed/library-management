package org.formation.gestionbiblio.controller;

import java.io.File;

import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.technical.BiblioService;
import org.formation.gestionbiblio.model.technical.XmlParser;

/*
 * Manager/Controller en singleton
 */
public class BiblioController {

	private static BiblioController biblioControllerInstance;
	
	/*
	 * Récup de l'instance du controller (singleton)
	 */
	public static BiblioController getInstance() {
		if (biblioControllerInstance == null) {
				biblioControllerInstance = new BiblioController();
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
	
	
}