/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Catalogue;
import classes.I_Catalogue;
import classes.I_Produit;
import classes.Produit;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Alexandre
 */
public class CatalogueDAO_XML implements I_CatalogueDAO{



    private static I_CatalogueDAO instance;
    private String uri = "D:/Catalogues.xml";
    private Document doc;
    
    protected CatalogueDAO_XML()
    {
        this.connect();
    }
    
    @Override
    public boolean connect() 
    {
        SAXBuilder sdoc = new SAXBuilder();
        try
        {
                doc = sdoc.build(uri);
                return true;
        } 
        catch (Exception e) 
        {
                System.out.println("erreur construction arbre JDOM");
                return false;
        }
    }

    @Override
    public boolean create(I_Catalogue cat) {
        try 
        {
			Element root = doc.getRootElement();
			Element catalogue = new Element("catalogue");
			catalogue.setAttribute("nom", cat.getNom());
			root.addContent(catalogue);
			return sauvegarde();
        }
        catch (Exception e) 
        {
			System.out.println("erreur creer produit");
			return false;
        }
    }
    
    private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

    @Override
    public boolean delete(I_Catalogue cat) {
        try {
			Element root = doc.getRootElement();
			Element catalogue = chercheCatalogue(cat.getNom());
			if (catalogue != null) {
				root.removeContent(catalogue);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
    }
    
    private Element chercheCatalogue(String nom) {
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("catalogue");
		int i = 0;
		while (i < lProd.size() && !lProd.get(i).getAttributeValue("nom").equals(nom))
			i++;
		if (i < lProd.size())
			return lProd.get(i);
		else
			return null;
	}

    @Override
    public ArrayList<I_Catalogue> readAll() {
        List<I_Catalogue> l = new ArrayList<I_Catalogue>();
		try {
			Element root = doc.getRootElement();
			List<Element> lCat = root.getChildren("catalogue");

			for (Element c : lCat) {
				String nom = c.getAttributeValue("nom");
				l.add(new Catalogue(nom));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
		}
		return (ArrayList<I_Catalogue>) l;
    }

    @Override
    public I_Catalogue read(String nomCatalogue) {
        Element e = chercheCatalogue(nomCatalogue);
		if (e != null)
			return new Catalogue(e.getAttributeValue("nom"));
		else
			return null;
    }

    @Override
    public boolean addProduit(String nom, double prix, int stock, I_Catalogue selectedCatalogue) {
        try {
			Element root = doc.getRootElement();
			Element prod = new Element("produit");
			prod.setAttribute("nom", nom);
			Element prixEle = new Element("prixHT");
			prod.addContent(prixEle.setText(String.valueOf(prix)));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(stock)));
                        Element cat = new Element("catalogue");
                        prod.addContent(cat.setText(selectedCatalogue.getNom()));
			root.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
    }

    @Override
    public ArrayList<I_Produit> getProduitsFromCatalogue(I_Catalogue catalogue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
