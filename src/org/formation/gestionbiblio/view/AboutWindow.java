package org.formation.gestionbiblio.view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutWindow {

	private JFrame frame;
	private Image img;
	private URL imgUrl;

	/**
	 * Create the application.
	 */
	public AboutWindow() {
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
		
		JLabel lblApplicationDeGestion = new JLabel("GestionBiblio réalisée par Rémi, Mehdi & Ayoub");
		lblApplicationDeGestion.setBounds(69, 0, 297, 53);
		frame.getContentPane().add(lblApplicationDeGestion);
		
		JPanel panel_img = new JPanel();
		panel_img.setBackground(Color.GRAY);
		panel_img.setBounds(111, 38, 212, 210);
		frame.getContentPane().add(panel_img);
		panel_img.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBackground(Color.ORANGE);
		panel_img.add(lblNewLabel);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFrame().setVisible(false);
			}
		});
		btnValider.setBounds(160, 253, 106, 25);
		frame.getContentPane().add(btnValider);
		
		URL imgUrl;
		try {
			this.imgUrl = new URL("https://i.imgur.com/GparX8O.jpg");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			img = ImageIO.read(this.imgUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	lblNewLabel.removeAll();
    	if(img != null)
    		lblNewLabel.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	
}
