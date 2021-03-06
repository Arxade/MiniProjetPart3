package dao;

import classes.I_Produit;
import classes.Produit;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

public class ProduitDAOXML {

    private String uri = "Catalogues.xml";
    private Document doc;

    public ProduitDAOXML() {
        this.connect();
    }
    
    public boolean connect() {
        SAXBuilder sdoc = new SAXBuilder();
        try {
            doc = sdoc.build(uri);
            return true;
        } catch (Exception e) {
            System.out.println("erreur construction arbre JDOM");
            return false;
        }
    }

    public boolean creer(I_Produit p) {
        try {
            Element root = doc.getRootElement();
            Element prod = new Element("produit");
            prod.setAttribute("nom", p.getNom());
            Element prix = new Element("prixHT");
            prod.addContent(prix.setText(String.valueOf(p.getPrixUnitaireHT())));
            Element qte = new Element("quantite");
            prod.addContent(qte.setText(String.valueOf(p.getQuantite())));
            root.addContent(prod);
            return sauvegarde();
        } catch (Exception e) {
            System.out.println("erreur creer produit");
            return false;
        }
    }

    public boolean maj(I_Produit p, String nomCatalogue) {
        try {
            Element prod = chercheProduit(p.getNom(), nomCatalogue);
            if (prod != null) {
                prod.getChild("quantite").setText(String.valueOf(p.getQuantite()));
                return sauvegarde();
            }
            return false;
        } catch (Exception e) {
            System.out.println("erreur maj produit");
            return false;
        }
    }

    public boolean supprimer(I_Produit p, String nomCatalogue) {
        try {
            Element root = doc.getRootElement();
            Element prod = chercheProduit(p.getNom(), nomCatalogue);
            if (prod != null) {
                Element cat = prod.getParentElement();
                cat.removeContent(prod);
                return sauvegarde();
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("erreur supprimer produit");
            return false;
        }
    }

    public I_Produit lire(String nom, String nomCatalogue) {
        Element e = chercheProduit(nom, nomCatalogue);
        if (e != null) {
            return new Produit(e.getAttributeValue("nom"), Double.parseDouble(e.getChildText("prixHT")), Integer.parseInt(e.getChildText("quantite")));
        } else {
            return null;
        }
    }

    public List<I_Produit> lireTous() {

        List<I_Produit> l = new ArrayList<I_Produit>();
        try {
            Element root = doc.getRootElement();
            List<Element> lProd = root.getChildren("produit");

            for (Element prod : lProd) {
                String nomP = prod.getAttributeValue("nom");
                Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
                int qte = Integer.parseInt(prod.getChild("quantite").getText());
                l.add(new Produit(nomP, prix, qte));
            }
        } catch (Exception e) {
            System.out.println("erreur lireTous tous les produits");
        }
        return l;
    }

    private boolean sauvegarde() {
        System.out.println("Sauvegarde");
        XMLOutputter out = new XMLOutputter();
        try {
            out.output(doc, new PrintWriter(uri));
                        this.connect();

            return true;
        } catch (Exception e) {
            System.out.println("erreur sauvegarde dans fichier XML");
            return false;
        }
    }

    private Element chercheProduit(String nomProduit, String nomCatalogue) {
        List<Element> lesCatXML = doc.getRootElement().getChildren();
        Element catalogue = null;
        for (Element leCatXML : lesCatXML) {
            if (leCatXML.getAttributeValue("nom").equals(nomCatalogue)) {
                catalogue = leCatXML;
            }
        }
        List<Element> lProd = catalogue.getChildren("produit");
        int i = 0;
        while (i < lProd.size() && !lProd.get(i).getAttributeValue("nom").equals(nomProduit)) {
            i++;
        }
        if (i < lProd.size()) {
            return lProd.get(i);
        } else {
            return null;
        }

    }
}
