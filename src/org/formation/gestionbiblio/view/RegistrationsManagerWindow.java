package org.formation.gestionbiblio.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;

import java.awt.Font;
import javax.swing.JScrollPane;

import org.formation.gestionbiblio.controller.BiblioController;
import org.formation.gestionbiblio.model.business.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class RegistrationsManagerWindow {

private JFrame frame;
private List<User> usersWaitingForValidation;
private List<User> usersValidated;


/**
 * Create the application.
 */
public RegistrationsManagerWindow() {
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

    this.usersWaitingForValidation = BiblioController.getInstance().getUsersWaitingForValidation();
    this.usersValidated = new ArrayList<User>();
    
    DefaultListModel<Object> model_list = new DefaultListModel<Object>();
    DefaultListModel<Object> model_list_1 = new DefaultListModel<Object>();
    for (User user : this.usersWaitingForValidation) {
        model_list.addElement(user.getUsername());
    }

    JList<Object> list = new JList<Object>();
    list.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
    list.setBounds(17, 41, 174, 194);
    list.setModel(model_list);

    JScrollPane listScroller = new JScrollPane();
    listScroller.setLocation(15, 43);
    listScroller.setSize(174, 194);
    listScroller.setViewportView(list);
    list.setLayoutOrientation(JList.VERTICAL);
    frame.getContentPane().add(listScroller);

    JList<Object> list_1 = new JList<Object>();
    list_1.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
    list_1.setBounds(262, 41, 174, 194);
    list_1.setModel(model_list_1);

    JScrollPane listScroller1 = new JScrollPane();
    listScroller1.setLocation(262, 41);
    listScroller1.setSize(174, 194);
    listScroller1.setViewportView(list_1);
    list_1.setLayoutOrientation(JList.VERTICAL);
    frame.getContentPane().add(listScroller1);

    JButton shiftbutton = new JButton(">>>");
    shiftbutton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            list.getSelectedValuesList().stream().forEach((data) -> {
                model_list_1.addElement(data);
                model_list.removeElement(data);
            });
        }
    });
    shiftbutton.setBounds(192, 116, 66, 52);
    frame.getContentPane().add(shiftbutton);
    
    JLabel lblValidationsDesInscriptions = new JLabel("Validations des inscriptions");
    lblValidationsDesInscriptions.setBounds(144, 12, 184, 15);
    frame.getContentPane().add(lblValidationsDesInscriptions);
    
    JButton btnValider = new JButton("Valider");
    btnValider.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		for(int i = 0; i< list_1.getModel().getSize();i++){
    			for (User user : usersWaitingForValidation) {
					if(user.getUsername().equals(list_1.getModel().getElementAt(i))) {
						usersValidated.add(user);
					}
				}
            }
    		
    		BiblioController.getInstance().validateUsers(usersValidated);
    		JOptionPane.showMessageDialog(null, "Utilisateurs validées");
    		frame.setVisible(false);
    	}
    });
    btnValider.setBounds(169, 249, 106, 25);
    frame.getContentPane().add(btnValider);
}

public JFrame getFrame() {
	return frame;
}


}