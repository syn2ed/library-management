package org.formation.gestionbiblio.model.technical;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.formation.gestionbiblio.controller.BiblioController;
import org.formation.gestionbiblio.model.business.Bibliotheque;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre;
import org.formation.gestionbiblio.model.business.Bibliotheque.Livre.Auteur;
import org.formation.gestionbiblio.model.business.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DbService {
	private Configuration config;
	private SessionFactory sessionFactory;
	private Session session;

	public DbService() {
		this.config = new Configuration();
		this.initConfig();
	}

	public void initConfig() {
		this.config.addAnnotatedClass(Livre.class);
		this.config.addAnnotatedClass(Auteur.class);
		this.config.addAnnotatedClass(User.class);
		this.sessionFactory = config.buildSessionFactory();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Récupération de la biblio depuis la bdd
	 * 
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
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
		}

		for (Livre livre : livres) {
			dbBiblio.getLivre().add(livre);
		}

		return dbBiblio;
	}

	public void synchronizeDbFromXmlBiblio(Bibliotheque biblio) {
		List<Livre> absentLivres = this.getLivresThatAreNotInDbFromBiblio(biblio);
		this.session = this.sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (Livre livre : absentLivres) {
				session.save(livre);
			}
			tx.commit(); // Flush happens automatically
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
		}
	}

	/**
	 * Récupération des livres qui sont dans la biblio passée en paramètre mais
	 * absent en DB
	 * 
	 * @param biblio
	 * @return
	 */
	public List<Livre> getLivresThatAreNotInDbFromBiblio(Bibliotheque biblio) {
		List<Livre> livres = biblio.getLivre();
		List<Livre> livresToAddInDb = new ArrayList<Livre>();

		this.session = this.sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = this.session.beginTransaction();
			for (Livre livre : livres) {
				String titre = livre.getTitre();

				Query query = session.createQuery(
						"select l from org.formation.gestionbiblio.model.business.Bibliotheque$Livre l where l.titre = "
								+ " :title")
						.setParameter("title", titre);

				if (query.getResultList().size() == 0) {
					livresToAddInDb.add(livre);
				}
			}
			tx.commit(); // Flush happens automatically
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
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

			List<Livre> livresToDelete = new ArrayList<Livre>();
			List<Livre> dbLivres = new ArrayList<Livre>();

			Query query = session.createQuery("from org.formation.gestionbiblio.model.business.Bibliotheque$Livre");
			dbLivres = (List<Livre>) query.getResultList();

			for (Livre livre : dbLivres) {
				boolean isInCurrentBiblio = false;

				if (livres.contains(livre))
					isInCurrentBiblio = true;

				if (!isInCurrentBiblio) {
					livresToDelete.add(livre);
				}
			}
			for (Livre livre : livresToDelete) {
				session.remove(livre);
			}
			tx.commit(); // Flush happens automatically
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
		}
	}

	public List<User> getDbUsers() {
		ArrayList<User> users = new ArrayList<User>();
		String hql = "from org.formation.gestionbiblio.model.business.User";

		this.session = this.sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			users = (ArrayList<User>) session.createQuery(hql).getResultList();
			tx.commit(); // Flush happens automatically
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
		}

		return users;
	}

	/**
	 * User persistence
	 * @param tf_id
	 * @param tf_password
	 * @param tf_email
	 */
	public void registerUser(String tf_id, String tf_password, String tf_email) {
		this.session = this.sessionFactory.openSession();
		User newUser = new User(tf_id, tf_password, tf_email);

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(newUser);
			tx.commit(); // Flush happens automatically
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
			BiblioController.getInstance().refreshUsers();
		}
	}

	public List<User> getUsersWaitingForValidation() {
		ArrayList<User> users = new ArrayList<User>();
		String hql = "from org.formation.gestionbiblio.model.business.User u where u.isValidate = 0";

		this.session = this.sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			users = (ArrayList<User>) session.createQuery(hql).getResultList();
			tx.commit(); // Flush happens automatically
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
		}

		return users;
	}

	public void validateUsers(List<User> usersValidated) {
		List<User> usersValidatedByAdmin = usersValidated;
		
		
		for (User user : usersValidatedByAdmin) {
			user.setValidate(true);
		}
		
		this.session = this.sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (User user : usersValidatedByAdmin) {
				session.update(user);
			}
			tx.commit(); // Flush happens automatically
		} catch (RuntimeException e) {
			tx.rollback();
			throw e; // or display error message
		} finally {
			session.close();
			BiblioController.getInstance().refreshUsers();
		}
	}
}
