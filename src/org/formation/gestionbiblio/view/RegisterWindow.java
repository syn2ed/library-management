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
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import java.awt.Rectangle;

public class RegisterWindow {

	private JFrame frame;
	private JTextField tf_password_repeat;
	private JTextField tf_id;
	private JTextField tf_email;
	private JTextField tf_password;

	/**
	 * Create the application.
	 */
	public RegisterWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 300, 200));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.setBounds(100, 100, 648, 251);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel formulairePanel = new JPanel();
		formulairePanel.setBorder(new LineBorder(Color.PINK, 1, true));
		formulairePanel.setLayout(null);
		formulairePanel.setBackground(Color.LIGHT_GRAY);
		formulairePanel.setBounds(31, 52, 589, 133);
		frame.getContentPane().add(formulairePanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.PINK, 1, true));
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(5, 46, 256, 77);
		formulairePanel.add(panel_2);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 12, 59, 15);
		panel_2.add(lblPassword);
		
		tf_password_repeat = new JTextField();
		tf_password_repeat.setColumns(10);
		tf_password_repeat.setBounds(102, 48, 148, 19);
		panel_2.add(tf_password_repeat);
		
		tf_password = new JTextField();
		tf_password.setColumns(10);
		tf_password.setBounds(102, 8, 147, 19);
		panel_2.add(tf_password);
		
		JLabel lblRepeat = new JLabel("Repeat");
		lblRepeat.setBounds(31, 51, 42, 15);
		panel_2.add(lblRepeat);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBackground(Color.LIGHT_GRAY);
		panel_11.setBounds(6, 6, 255, 36);
		formulairePanel.add(panel_11);
		
		JLabel lblIdentifiant_1 = new JLabel("Identifiant");
		lblIdentifiant_1.setBounds(18, 7, 72, 15);
		panel_11.add(lblIdentifiant_1);
		
		tf_id = new JTextField();
		tf_id.setColumns(10);
		tf_id.setBounds(102, 4, 147, 19);
		panel_11.add(tf_id);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBackground(Color.LIGHT_GRAY);
		panel_10.setBounds(286, 6, 297, 36);
		formulairePanel.add(panel_10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(12, 7, 52, 15);
		panel_10.add(lblEmail);
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(71, 7, 220, 19);
		panel_10.add(tf_email);
		
		JButton btnDemanderLinscription = new JButton("Demander l'inscription");
		btnDemanderLinscription.setBounds(408, 75, 175, 48);
		formulairePanel.add(btnDemanderLinscription);
		btnDemanderLinscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BiblioController.getInstance().checkIfUserExist(tf_email.getText())) {
					JOptionPane.showMessageDialog(null, "Email déjà existant");
				} else if(tf_password_repeat.getText().equals(tf_password.getText())) {
					BiblioController.getInstance().registerUser(tf_id.getText(), tf_password.getText(), tf_email.getText());
					
					JOptionPane.showMessageDialog(null, "La demande de creation de compte a ete envoyée !");
					BiblioController.getInstance().getRegisterWindow().setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Deux mots de passe differents ont ete saisis");
				}
			}
		});
		
	}
	
	public void setVisible(boolean bool) {
		this.frame.setVisible(bool);
	}
}
