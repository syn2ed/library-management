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
	XWPFParagraph paragraph_header;
	XWPFParagraph paragraph_titre;
	XWPFParagraph paragraph_sommaire;
	XWPFParagraph paragraph_livres;
	XWPFParagraph paragraph_pret;
    XWPFRun sommaire;
    XWPFRun header;
    XWPFRun title;
    XWPFRun titre;
    XWPFRun livres;
    XWPFTable table;
    XWPFTableRow tableligne1;
    XWPFTableRow tableligne2;
    String filename ="bibliotheque";
    
	public void exportFile() throws IOException{
    	try {

	      	//Write the Document in file system
	      	FileOutputStream out = new FileOutputStream(new File(filename+".docx"));
    	      	document = new XWPFDocument(); 
		    	
		    	//paragraph_pret = document.createParagraph();
    	      	
		    	paragraph_header = document.createParagraph();
		    	header = paragraph_header.createRun();
		    	header.setFontSize((short)(15));
				header.setText(LocalDate.now() +" "+filename);
				
				
		    	paragraph_titre = document.createParagraph();
		    	titre = paragraph_titre.createRun();
		    	
		    	titre.addCarriageReturn();
		    	titre.addCarriageReturn();
		    	titre.addCarriageReturn();
		    	titre.addCarriageReturn();
		    	titre.addCarriageReturn();
		    	titre.addCarriageReturn();
		    	titre.setFontSize((short)(35));
		    	titre.setText("CONTENU DE LA BIBLIOTHEQUE");
				titre.addBreak(BreakType.PAGE);
		    	
		    	paragraph_sommaire = document.createParagraph();
		    	sommaire = paragraph_sommaire.createRun();
		    	sommaire.setFontSize((short)(22));
		    	sommaire.setText("Sommaire");
		    	sommaire.addCarriageReturn();
		    	sommaire.addCarriageReturn();
		    	sommaire.addCarriageReturn();
		    	sommaire.addCarriageReturn();
		    	sommaire.addCarriageReturn();
		    	sommaire.addCarriageReturn();
		    	
		    	sommaire.setFontSize((short)(15));
		    	sommaire.setText("LIVRES DE LA BIBLIOTHEQUE: ");
		    	sommaire.addCarriageReturn();
		    	int j;
				j=0;
				while(j<BiblioService.getInstance().getBiblio().getLivre().size())
				{		
						String nom;
						
						nom = "          "+BiblioService.getInstance().getBiblio().getLivre().get(j).getTitre();
						sommaire.setText(nom);
						sommaire.addCarriageReturn();
						j++;
				 } 
				sommaire.addCarriageReturn();
				sommaire.addCarriageReturn();
				sommaire.addCarriageReturn();
				sommaire.setText("LIVRES EN PRET: ");
		    	sommaire.addCarriageReturn();
/* FAIRE PAREIL MAIS AVEC LES LIVRES PRETES (getPret())
		    	int k;
				k=0;
				while(k<App.getBiblio().getLivre().size())
				{		
						String nom;
						
						nom = "          "+App.getBiblio().getLivre().get(k).getTitre();
						sommaire.setText(nom);
						sommaire.addCarriageReturn();
						k++;
				 } 
		    	sommaire.addBreak(BreakType.PAGE);
*/				
		    	sommaire.addBreak(BreakType.PAGE);
				paragraph_livres = document.createParagraph();
		    	livres = paragraph_livres.createRun();
		    	int i;
				i=0;
				while(i<BiblioService.getInstance().getBiblio().getLivre().size())
				{		
						String parutionStr = Integer.toString(BiblioService.getInstance().getBiblio().getLivre().get(i).getParution());
						String rangeeStr = Integer.toString(BiblioService.getInstance().getBiblio().getLivre().get(i).getRangee());
						String colonneStr = Integer.toString(BiblioService.getInstance().getBiblio().getLivre().get(i).getColonne());
						String nom;
						String auteur;
						String description;
						String parution;
						String rangee;
						String colonne;
						String urlimage;
						nom ="NOM DU LIVRE: "+BiblioService.getInstance().getBiblio().getLivre().get(i).getTitre();
						livres.setText(nom);
						livres.addCarriageReturn();
						auteur="     AUTEUR: "+BiblioService.getInstance().getBiblio().getLivre().get(i).getAuteur().getNom();
						livres.setText(auteur);
						livres.addCarriageReturn();
						description="     DESCRIPTION: "+BiblioService.getInstance().getBiblio().getLivre().get(i).getPresentation();
						livres.setText(description);
						livres.addCarriageReturn();
						parution="     PARUTION: "+parutionStr;
						livres.setText(parution);
						livres.addCarriageReturn();
						rangee="     RANGEE: "+rangeeStr;
						livres.setText(rangee);
						livres.addCarriageReturn();
						colonne="     COLONNE: "+colonneStr;
						livres.setText(colonne);
						livres.addCarriageReturn();
						urlimage="     IMAGE URL: "+BiblioService.getInstance().getBiblio().getLivre().get(i).getImgUrl();
						livres.setText(urlimage);
						livres.addCarriageReturn();
						livres.addCarriageReturn();
						i++;
				 } 
				
				
//	      	entete();		//appel du texte a ecrire dans le document
				
				
	      
	      	document.write(out);
	      	out.close();
	      	//document.close();
	      	System.out.println("DOCUMENT CREE");
        }
    	catch(IOException e)
    	{
    			System.out.println("Probleme creation de WORD");
    	}
    }
	
}
