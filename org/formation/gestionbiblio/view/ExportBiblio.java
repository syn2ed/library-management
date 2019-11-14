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
    
    public ExportBiblio() {
    	
    }
    
	public void exportFile() throws IOException{
    	try {
    		
    	      	//Write the Document in file system
    	      	FileOutputStream out = new FileOutputStream(new File(filename+".docx"));
	    	      	document = new XWPFDocument(); 
			    	
			    	//paragraph_pret = document.createParagraph();

			    	paragraph_header = document.createParagraph();
			    	header = paragraph_header.createRun();
			    	header.setFontSize((short)(18));
					header.setText(LocalDate.now() +" "+filename);
					
					
			    	paragraph_titre = document.createParagraph();
			    	titre = paragraph_titre.createRun();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.addCarriageReturn();
			    	titre.setText("CONTENU DE LA BIBLIOTHEQUE");
					titre.addBreak(BreakType.PAGE);
			    	
			    	paragraph_sommaire = document.createParagraph();
			    	sommaire = paragraph_sommaire.createRun();
			    	sommaire.setText("Sommaire");
					
					paragraph_livres = document.createParagraph();
			    	livres = paragraph_livres.createRun();
			    	int i;
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
					
					
//    	      	entete();		//appel du texte a ecrire dans le document
					
					
    	      
    	      	document.write(out);
    	      	out.close();
    	      	document.close();
    	      	System.out.println("DOCUMENT CREE");
        }
    	catch(IOException e)
    	{
    			System.out.println("Probleme creation de WORD");
    	}
    }
	
	private void entete() throws IOException{
    	
		
    }
}
