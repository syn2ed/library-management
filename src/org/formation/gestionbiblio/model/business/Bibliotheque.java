//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.10.16 � 04:20:41 PM CEST 
//


package org.formation.gestionbiblio.model.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.CascadeType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="livre" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="auteur">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *                   &lt;element name="imgUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "livre"
})
@XmlRootElement(name = "bibliotheque")
public class Bibliotheque extends AbstractTableModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -261601011942104965L;
	
	//Propriété non utilisée dans le XML/XSD
	@XmlTransient
	private final String[] columnsName = {
			"Titre",
	    	"Auteur",
	    	"Presentation",
	    	"Parution",
	    	"Colonne",
	    	"Rangee",
	    	"UrlImg",
	    	"Type",
	    	"PersonnePret"
	};
	
	@XmlElement(required = true)
    protected List<Bibliotheque.Livre> livre;
   
    public Bibliotheque() {
    	super();
    	//this.setColumnIdentifiers(Bibliotheque.columns.values());
    }

    /**
     * Gets the value of the livre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the livre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLivre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bibliotheque.Livre }
     * 
     * 
     */
    public List<Bibliotheque.Livre> getLivre() {
        if (livre == null) {
            livre = new ArrayList<Bibliotheque.Livre>();
        }
        return this.livre;
    }


    public List<Bibliotheque.Livre> getLoanedBooks() {
    	List<Bibliotheque.Livre> loanedBooks = new ArrayList<Bibliotheque.Livre>();
    	
    	for (Bibliotheque.Livre book : this.getLivre()) {
			if(book.getType().equals("Prete"))
				loanedBooks.add(book);
		}
    	
        return loanedBooks;
    }
    
    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="auteur">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="presentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="parution" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="colonne" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="rangee" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
     *         &lt;element name="imgUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "colonnes", propOrder = {
        "titre",
        "auteur",
        "presentation",
        "parution",
        "colonne",
        "rangee",
        "imgUrl",
        "type",
        "personnePret"
    })
    public static class Livre {

        @XmlElement(required = true)
        protected String titre;
        @XmlElement(required = true)
        protected Bibliotheque.Livre.Auteur auteur;
        @XmlElement(required = true)
        protected String presentation;
        @XmlSchemaType(name = "unsignedShort")
        protected int parution;
        @XmlSchemaType(name = "unsignedByte")
        protected short colonne;
        @XmlSchemaType(name = "unsignedByte")
        protected short rangee;
        @XmlSchemaType(name = "string")
        protected String imgUrl;
        @XmlSchemaType(name = "type")
        protected String type;
        @XmlSchemaType(name = "personnePret")
        protected String personnePret;

        /**
         * Obtient la valeur de la propri�t� titre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitre() {
            return titre;
        }

        /**
         * D�finit la valeur de la propriete titre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitre(String value) {
            this.titre = value;
        }

        /**
         * Obtient la valeur de la propri�t� auteur.
         * 
         * @return
         *     possible object is
         *     {@link Bibliotheque.Livre.Auteur }
         *     
         */
        public Bibliotheque.Livre.Auteur getAuteur() {
            return auteur;
        }

        /**
         * D�finit la valeur de la propri�t� auteur.
         * 
         * @param value
         *     allowed object is
         *     {@link Bibliotheque.Livre.Auteur }
         *     
         */
        public void setAuteur(Bibliotheque.Livre.Auteur value) {
            this.auteur = value;
        }

        /**
         * Obtient la valeur de la propri�t� presentation.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPresentation() {
            return presentation;
        }

        /**
         * D�finit la valeur de la propri�t� presentation.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPresentation(String value) {
            this.presentation = value;
        }

        /**
         * Obtient la valeur de la propri�t� parution.
         * 
         */
        public int getParution() {
            return parution;
        }

        /**
         * D�finit la valeur de la propri�t� parution.
         * 
         */
        public void setParution(int value) {
            this.parution = value;
        }

        /**
         * Obtient la valeur de la propri�t� colonne.
         * 
         */
        public short getColonne() {
            return colonne;
        }

        /**
         * D�finit la valeur de la propri�t� colonne.
         * 
         */
        public void setColonne(short value) {
            this.colonne = value;
        }

        /**
         * Obtient la valeur de la propri�t� rangee.
         * 
         */
        public short getRangee() {
            return rangee;
        }

        /**
         * D�finit la valeur de la propri�t� rangee.
         * 
         */
        public void setRangee(short value) {
            this.rangee = value;
        }
        
        public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		
		public String getPersonnePret() {
			return personnePret;
		}

		public void setPersonnePret(String personnePret) {
			this.personnePret = personnePret;
		}

		/**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de schema suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "nom",
            "prenom"
        })
        public static class Auteur {

            @XmlElement(required = true)
            protected String nom;
            @XmlElement(required = true)
            protected String prenom;

            /**
             * Obtient la valeur de la propri�t� nom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNom() {
                return nom;
            }

            /**
             * D�finit la valeur de la propri�t� nom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNom(String value) {
                this.nom = value;
            }

            /**
             * Obtient la valeur de la propri�t� prenom.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrenom() {
                return prenom;
            }

            /**
             * D�finit la valeur de la propri�t� prenom.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrenom(String value) {
                this.prenom = value;
            }
        }
    }


    /*
     * Retourne le nombre de livres presentants dans la bib
     */
	@Override
	public int getRowCount() {
		return this.getLivre().toArray().length;
	}

	/*
	 * Retourne le nombre de colonnes du tableau presentant les livres de la biblio
	 */
	@Override
	public int getColumnCount() {
		return this.columnsName.length;
	}

	/*
	 * Retourne la valeur d'un livre depuis son numero de ligne et de colonne
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Livre livre = this.livre.get(rowIndex);
		 
        Object value = null;
        switch (columnIndex) {
            case 0 : value = livre.getTitre(); break;
            case 1 : value = livre.getAuteur().getNom(); break;
            case 2 : value = livre.getPresentation(); break;
            case 3 : value = livre.getParution(); break;
            case 4 : value = livre.getColonne(); break;
            case 5 : value = livre.getRangee(); break;
            case 6 : value = livre.getImgUrl(); break;
            case 7 : value = livre.getType(); break;
            case 8 : value = livre.getPersonnePret(); break;
        }
        
        return value;
	}
	
	/*
	 * Insértion d'une valeur dans une cellule définie par son num de ligne et colonne
	 */
	@Override
	public void setValueAt(Object valuee, int rowIndex, int columnIndex) {
		Livre livre = this.livre.get(rowIndex);
		 
        switch (columnIndex) {
            case 0 : livre.setTitre(valuee.toString()); break;
            case 1 : livre.getAuteur().setNom(valuee.toString());
            case 2 : livre.setPresentation(valuee.toString()); break;
            case 3 : livre.setParution(Integer.parseInt(valuee.toString())); break;
            case 4 : livre.setColonne(Short.parseShort((String) valuee)); break;
            case 5 : livre.setRangee(Short.parseShort((String) valuee)); break;
            case 6 : livre.setImgUrl((String) valuee); break;
            case 7 : livre.setType((String) valuee); break;
            case 8 : livre.setPersonnePret((String) valuee); break;
        }
	}
	
	/*
	 * Retourne le nom de la colonne dont l'index/cursuer/numéro est passé en paramètre
	 */
	@Override
    public String getColumnName(int index) {
        return Arrays.asList(this.columnsName).get(index);
    }
	
	/*
	 * Retire la ligne/enregistrement dont le numéro est passé en paramètre
	 */
	public void removeRow(int rowNumber) {
		this.getLivre().remove(rowNumber);
	}
}