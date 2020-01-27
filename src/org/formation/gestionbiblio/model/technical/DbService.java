package org.formation.gestionbiblio.model.technical;

import java.util.Properties;

import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbService {
	private Configuration config;
	
	public DbService() {
		this.config = new Configuration();
		this.initConfig();
	}
	
	public void initConfig() {
		this.config.addClass(Livre.class);
	}
	
	public void getLivreById(int i) {
		SessionFactory sessionFactory = config.buildSessionFactory();
	    Session session = sessionFactory.openSession();
	    try { 
	        Livre livre = (Livre) session.load(Livre.class, new Integer(1)); 
	        System.out.println(livre.getParution()); 
	      } finally { 
	        session.close(); 
	      } 
	      sessionFactory.close();
	}
}
