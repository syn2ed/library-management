package org.formation.gestionbiblio.model.technical;

import java.awt.FileDialog;
import java.awt.Frame;

import org.formation.gestionbiblio.controller.BiblioController;

public class Exportateur {

    private boolean saveFileAs(String saveName, String format) {
    	Frame frame = null;
		FileDialog fDialog = new FileDialog(frame, saveName, FileDialog.SAVE);
        fDialog.setVisible(true);
        String path = fDialog.getDirectory() + fDialog.getFile(); 
    	
    	try {
			XmlParser.marshal(BiblioController.getInstance().getBiblio(), (path + "." + format));
			return true;
		} catch (Exception e) {
			System.out.println("erreur in SaveFile()");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
}
