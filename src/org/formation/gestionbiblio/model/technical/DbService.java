package org.formation.gestionbiblio.model.technical;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre.Auteur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DbService {
	private Configuration config;
	protected EntityManager manager;
	private SessionFactory sessionFactory;
	private Session session;
	
	public DbService() {
		this.config = new Configuration();
		this.initConfig();
	}
	
	public void initConfig() {
		this.config.addAnnotatedClass(Livre.class);
		this.config.addAnnotatedClass(Auteur.class);
		this.sessionFactory = config.buildSessionFactory();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Récupération de la biblio depuis la bdd
	 * @return
	 */
	public Bibliotheque getBiblioFromDb() {
		Bibliotheque dbBiblio = new Bibliotheque();
		ArrayList<Livre> livres = new ArrayList<Livre>();
	    String hql = "from org.formation.gestionbiblio.model.business.Bibliotheque$Livre";
	    
	    this.session = this.sessionFactory.openSession();

	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        livres = (ArrayList<Livre>) session.createQuery(hql).list(); 
	        tx.commit(); // Flush happens automatically
	    }
	    catch (RuntimeException e) {
	        tx.rollback();
	        throw e; // or display error message
	    }
	    finally {
	        session.close();
	    }
	    
	    for (Livre livre : livres) {
			dbBiblio.getLivre().add(livre);
		}
	    
		return dbBiblio;
	}

	public void synchronizeDbFromXmlBiblio(Bibliotheque biblio) {
		List<Livre> absentLivres = this.getLivresThatAreNotInDbFromXml(biblio);
	    this.session = this.sessionFactory.openSession();

	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        for (Livre livre : absentLivres) {
		    	session.save(livre);
			}
	        tx.commit(); // Flush happens automatically
	    }
	    catch (RuntimeException e) {
	        tx.rollback();
	        throw e; // or display error message
	    }
	    finally {
	        session.close();
	    }
	}
	
	/**
	 * Récupération des livres qui sont dans le XML mais absent en DB
	 * @param biblio
	 * @return
	 */
	public List<Livre> getLivresThatAreNotInDbFromXml(Bibliotheque biblio) {
		List<Livre> livresXml = biblio.getLivre();
		List<Livre> livresToAddInDb = new ArrayList<Livre>();
	
		this.session = this.sessionFactory.openSession();
	    Transaction tx = null;
	    
	    try {
	        tx = session.beginTransaction();
	        for (Livre livre : livresXml) {
				String titre = livre.getTitre();
				
				Query query = session
						.createQuery("select l from org.formation.gestionbiblio.model.business.Bibliotheque$Livre l where l.titre = " 
								+ " :title").setParameter("title", titre);
				
				if(query.getResultList().size() == 0) {
					livresToAddInDb.add(livre);
				}
			}
	        tx.commit(); // Flush happens automatically
	    }
	    catch (RuntimeException e) {
	        tx.rollback();
	        throw e; // or display error message
	    }
	    finally {
	        session.close();
	    }
	    
		return livresToAddInDb;
	}

	public void updateDbBiblio(List<Livre> livres) {
		this.session = this.sessionFactory.openSession();

	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        for (Livre livre : livres) {
		    	session.saveOrUpdate(livre);
			}
	        tx.commit(); // Flush happens automatically
	    }
	    catch (RuntimeException e) {
	        tx.rollback();
	        throw e; // or display error message
	    }
	    finally {
	        session.close();
	    }
	}
}
