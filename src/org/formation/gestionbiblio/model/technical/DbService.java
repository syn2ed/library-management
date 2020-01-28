package org.formation.gestionbiblio.model.technical;

import java.util.ArrayList;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DbService {
	private Configuration config;
	protected EntityManager manager;
	
	public DbService() {
		this.config = new Configuration();
		this.initConfig();
	}
	
	public void initConfig() {
		this.config.addClass(Livre.class);
		System.out.println("config");
	}
	
	
	public void getLivreById(int i) {
		SessionFactory sessionFactory = config.buildSessionFactory();
	    Session session = sessionFactory.openSession();
	    try { 
	        Livre livre = (Livre) session.load(Livre.class, new Integer(1)); 
	        System.out.println(livre.getAuteur().getNom()); 
	      } finally { 
	        session.close(); 
	      } 
	      sessionFactory.close();
	}
	
	/**
	 * Récupération de la biblio depuis la bdd
	 * @return
	 */
	public Bibliotheque getBiblioFromDb() {
		Bibliotheque dbBiblio = new Bibliotheque();
		ArrayList<Livre> livres = new ArrayList<Livre>();
		
		SessionFactory sessionFactory = config.buildSessionFactory();
	    Session session = sessionFactory.openSession();
	    String hql = "from org.formation.gestionbiblio.model.business.Bibliotheque$Livre";
	    try { 
	        livres = (ArrayList<Livre>) session.createQuery(hql).list(); 
	      } finally { 
	        session.close(); 
	      } 
	    sessionFactory.close();
	    
	    for (Livre livre : livres) {
			dbBiblio.getLivre().add(livre);
		}
	    
		return dbBiblio;
	}
}
