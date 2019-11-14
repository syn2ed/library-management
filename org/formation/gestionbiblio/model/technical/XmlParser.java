package org.formation.gestionbiblio.model.technical;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.formation.gestionbiblio.model.business.Bibliotheque;

/*
 * Classe d'outils possédant 2 méthodes (unmarshal/marshal) pour décomposer/composer la biblio au format XML
 */
public class XmlParser {
	/*
	 * Méthode permettant de récupérer un objet Bibliotheque (d'abstraction)
	 * depuis le fichier XML fourni en paramètre
	 */
	public static Bibliotheque unmarshal(File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Bibliotheque.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Bibliotheque model = (Bibliotheque)unmarshaller.unmarshal(file);
        return model;
    }
	
	/*
	 * Méthode générant le fichier XML corréspondant aux données sérializés dans l'objet
	 * Biblio
	 */
	public static void marshal(Bibliotheque model, String file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Bibliotheque.class);
        Marshaller marshaller = context.createMarshaller();
        File f= new File(file);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(model, f);
    }
}
