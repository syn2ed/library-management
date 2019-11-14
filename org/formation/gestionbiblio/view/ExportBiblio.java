package org.formation.gestionbiblio.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.formation.gestionbiblio.model.technical.BiblioService;

import javafx.css.ParsedValue;

public class ExportBiblio {
	//Blank Document
    XWPFDocument document;
	XWPFParagraph paragraph;
    XWPFRun sommaire;
    XWPFRun header;
    XWPFRun sautpage;
    XWPFRun titre;
    XWPFRun livres;
    XWPFTable table;
    XWPFTableRow tableligne1;
    XWPFTableRow tableligne2;
    String filename ="bibliocr";
    
    public ExportBiblio() {
    	document = new XWPFDocument(); 
    	paragraph = document.createParagraph();
    	header = paragraph.createRun();
    	titre = paragraph.createRun();
    	sautpage = paragraph.createRun();
    	sommaire = paragraph.createRun();
    	sautpage = paragraph.createRun();
    	livres = paragraph.createRun();
    }
    
	public void exportFile() throws IOException{
    	try {
    		
    	      	//Write the Document in file system
    	      	FileOutputStream out = new FileOutputStream(new File(filename+".docx"));
    	      
    	      	entete();		//appel du texte a ecrire dans le document
    	      
    	      	document.write(out);
    	      	out.close();
    	      	System.out.println("DOCUMENT CREE");
        }
    	catch(IOException e)
    	{
    			System.out.println("Probleme creation de WORD");
    	}
    }
	
	private void entete() throws IOException{
    	
		int i;
		
		header.setFontSize((short)(18));
		header.setText(LocalDate.now() +" "+filename);
		titre.setText("BIBLIOTHEQUE");
		sautpage.addBreak(BreakType.PAGE);
		sommaire.setText("Sommaire");
		sautpage.addBreak(BreakType.PAGE);
		String text="";
		i=0;
		while(i<BiblioService.getInstance().getBiblio().getLivre().size())
		{		
				String parutionStr = Integer.toString(BiblioService.getInstance().getBiblio().getLivre().get(i).getParution());
				String rangeeStr = Integer.toString(BiblioService.getInstance().getBiblio().getLivre().get(i).getRangee());
				String colonneStr = Integer.toString(BiblioService.getInstance().getBiblio().getLivre().get(i).getColonne());
								
				text =text+"NOM DU LIVRE:"+BiblioService.getInstance().getBiblio().getLivre().get(i).getTitre()+" AUTEUR:"+BiblioService.getInstance().getBiblio().getLivre().get(i).getAuteur().getNom()+" DESCRIPTION:"+BiblioService.getInstance().getBiblio().getLivre().get(i).getPresentation()+" PARUTION:"+parutionStr+" RANGEE:"+rangeeStr+" COLONNE:"+colonneStr+"IMAGE URL"+BiblioService.getInstance().getBiblio().getLivre().get(i).getImgUrl();
				i++;
		 } 
		livres.setText(text);
    }
}
