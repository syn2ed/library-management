package org.formation.gestionbiblio.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;

import javax.swing.JTextField;

import org.formation.gestionbiblio.controller.BiblioController;
import org.formation.gestionbiblio.model.technical.Authentificator;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;

public class AuthWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public AuthWindow() throws FileNotFoundException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(155, 80, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 118, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(75, 85, 62, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(75, 123, 61, 16);
		frame.getContentPane().add(lblPassword);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BiblioController.getInstance().getAuthentificator().checkUser(textField.getText(), textField_1.getText())) {
					JOptionPane.showMessageDialog(null, "Hello " + BiblioController.getInstance().getAuthentificator().getUserAuthentified().getUsername() + "!");
					BiblioController.getInstance().getMainWindow().initialize(BiblioController.getInstance().getAuthentificator().getUserAuthentified().getRole());
					BiblioController.getInstance().getMainWindow().getFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Identifiants incorrects");
				}
			}
		});
		btnConnexion.setBounds(155, 156, 117, 29);
		frame.getContentPane().add(btnConnexion);
		
		JLabel lblNewLabel = new JLabel("BIBLIO APP");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(164, 17, 86, 16);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BiblioController.getInstance().getRegisterWindow().setVisible(true);
			}
		});
		btnInscription.setBounds(309, 227, 117, 26);
		frame.getContentPane().add(btnInscription);
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
