package org.formation.gestionbiblio.model.business;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.formation.gestionbiblio.controller.BiblioController;


public class WordBiblio {
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
    private Image img;
    
	public void exportFile() throws IOException, InvalidFormatException{
    	try {
    		
	      	//Write the Document in file system
	      	FileOutputStream out = new FileOutputStream(new File(filename+".docx"));
    	      	document = new XWPFDocument(); 
		    	//paragraph_pret = document.createParagraph();
    	      	
    	      	
    	      	//CREATION HEADER
    	      	CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
    	        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
				
				CTP ctpHeader = CTP.Factory.newInstance();
	            CTR ctrHeader = ctpHeader.addNewR();
	            CTText ctHeader = ctrHeader.addNewT();

	            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	            Date date = new Date();
	            String tctDate = format.format(date);

	            String headerText = tctDate+" "+ filename;
	            ctHeader.setStringValue(headerText);
	            XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
	            XWPFParagraph[] parsHeader = new XWPFParagraph[1];
	            parsHeader[0] = headerParagraph;
	            policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
				
				
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
				while(j<BiblioController.getInstance().getBiblio().getLivre().size())
				{		
						String nom;
						
						nom = "          "+BiblioController.getInstance().getBiblio().getLivre().get(j).getTitre();
						sommaire.setText(nom);
						sommaire.addCarriageReturn();
						j++;
				 } 
				sommaire.addCarriageReturn();
				sommaire.addCarriageReturn();
				sommaire.addCarriageReturn();
				sommaire.setText("LIVRES EN PRET: ");
		    	sommaire.addCarriageReturn();
		    	int k;
				k=0;
				while(k<BiblioController.getInstance().getBiblio().getLoanedBooks().size())
				{		
						String nom;
						
						nom = "          "+BiblioController.getInstance().getBiblio().getLoanedBooks().get(k).getTitre();
						sommaire.setText(nom);
						sommaire.addCarriageReturn();
						k++;
				 } 
		    	sommaire.addBreak(BreakType.PAGE);
				paragraph_livres = document.createParagraph();
		    	livres = paragraph_livres.createRun();
		    	int i;
				i=0;
				while(i<BiblioController.getInstance().getBiblio().getLivre().size())
				{		
						String parutionStr = Integer.toString(BiblioController.getInstance().getBiblio().getLivre().get(i).getParution());
						String rangeeStr = Integer.toString(BiblioController.getInstance().getBiblio().getLivre().get(i).getRangee());
						String colonneStr = Integer.toString(BiblioController.getInstance().getBiblio().getLivre().get(i).getColonne());
						String nom;
						String auteur;
						String description;
						String parution;
						String rangee;
						String colonne;
						String urlimage;
						nom ="NOM DU LIVRE: "+BiblioController.getInstance().getBiblio().getLivre().get(i).getTitre();
						livres.setText(nom);
						livres.addCarriageReturn();
						auteur="     AUTEUR: "+BiblioController.getInstance().getBiblio().getLivre().get(i).getAuteur().getNom();
						livres.setText(auteur);
						livres.addCarriageReturn();
						description="     DESCRIPTION: "+BiblioController.getInstance().getBiblio().getLivre().get(i).getPresentation();
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
						
						//URL imgUrl = new URL(BiblioController.getInstance().getBiblio().getLivre().get(i).getImgUrl());
						//this.img = ImageIO.read(imgUrl);
						URL monurl = new URL (BiblioController.getInstance().getBiblio().getLivre().get(i).getImgUrl());
						Image monimg = ImageIO.read(monurl);
						
						
						BufferedImage img = ImageIO.read(monurl);
						File file = new File("img");
						ImageIO.write(img, "jpg", file);
						String imgFile = file.getName(); 
						
						
						livres.addPicture(new FileInputStream(file), XWPFDocument.PICTURE_TYPE_JPEG ,imgFile, Units.toEMU(200), Units.toEMU(200)); // 200x200 pixels
						
						/*
						urlimage="     IMAGE URL: "+BiblioController.getInstance().getBiblio().getLivre().get(i).getImgUrl();
						livres.setText(urlimage);
						*/
						
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
