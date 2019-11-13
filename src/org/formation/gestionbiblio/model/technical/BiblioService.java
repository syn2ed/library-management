package org.formation.gestionbiblio.model.technical;

import org.formation.gestionbiblio.model.business.Bibliotheque;

/*
 * Classe (singleton) permettant d'avoir une instance de la bibliothèque
 */
public class BiblioService {
	
	private static BiblioService instance = null;
	private Bibliotheque biblio;
	
	/*
	 * Constructeur par défaut
	 */
	public BiblioService() {
		this.biblio = new Bibliotheque();
	}
	
	/*
	 * Méthode de récupération de l'instance de l'objet unique BiblioService
	 */
	public static BiblioService getInstance() {
		if (instance == null) {
			instance = new BiblioService();
		}
		return instance;
	}

	public Bibliotheque getBiblio() {
		return biblio;
	}

	public void setBiblio(Bibliotheque biblio) {
		this.biblio = biblio;
	}
	
}
