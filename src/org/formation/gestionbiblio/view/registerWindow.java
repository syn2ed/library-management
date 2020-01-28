package org.formation.gestionbiblio.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;

import org.formation.gestionbiblio.controller.BiblioController;
import org.formation.gestionbiblio.model.business.User;
import org.hibernate.Session;

import javax.swing.JLabel;
import java.awt.Font;

public class registerWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerWindow window = new registerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public registerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.setBounds(100, 100, 648, 572);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnNom = new JTextPane();
		txtpnNom.setBounds(311, 110, 111, 26);
		frame.getContentPane().add(txtpnNom);
		
		JTextPane txtpnMotDePasse = new JTextPane();
		txtpnMotDePasse.setBounds(311, 164, 111, 26);
		frame.getContentPane().add(txtpnMotDePasse);
		
		JTextPane txtpnAgain = new JTextPane();
		txtpnAgain.setBounds(311, 220, 111, 26);
		frame.getContentPane().add(txtpnAgain);
		
		JButton btnDemanderLinscription = new JButton("Demander l'inscription");
		btnDemanderLinscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtpnAgain.getText().equals(txtpnMotDePasse.getText())) {
					JOptionPane.showMessageDialog(null, "La demande de creation de compte a ete envoyee !");
					BiblioController.getInstance().getRegisterWindow().setVisible(false);
					
					/*
					Session session = BiblioController.getInstance().getDbService().getSessionFactory().openSession();
			        session.beginTransaction();
			        
			        //Add new Employee object
			        User user = new User();
			        user.setUsername("usernamea");
			        user.setPassword("mdpa");
			         
			        //Save the employee in database
			        session.save(user);
			 
			        //Commit the transaction
			        session.getTransaction().commit();
					*/
				} else {
					JOptionPane.showMessageDialog(null, "Deux mots de passe différents ont été saisis");
				}
			}
		});
		btnDemanderLinscription.setBounds(211, 288, 175, 48);
		frame.getContentPane().add(btnDemanderLinscription);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblIdentifiant.setBounds(157, 110, 144, 26);
		frame.getContentPane().add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMotDePasse.setBounds(157, 164, 144, 26);
		frame.getContentPane().add(lblMotDePasse);
		
		JLabel lblEncoreUneFois = new JLabel("Encore une fois :");
		lblEncoreUneFois.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEncoreUneFois.setBounds(157, 220, 144, 26);
		frame.getContentPane().add(lblEncoreUneFois);
		
	}
	
	public void setVisible(boolean bool) {
		this.frame.setVisible(bool);
	}
}
