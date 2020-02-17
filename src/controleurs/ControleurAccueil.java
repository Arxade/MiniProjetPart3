/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classes.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre
 */
public class ControleurAccueil {
    
    private EnsembleCatalogue ensembleCat = getEnsembleCatalogue();
    
    public void addCatalogue(String nomCatalogue) {
        Catalogue catalogue = new Catalogue(nomCatalogue);
        ensembleCat.addCatalogue(catalogue);  
    }

    public void removeCatalogue(String nomCatalogue) {
        ensembleCat.removeCatalogue(ensembleCat.getCatalogueFromNom(nomCatalogue));
    }
    
    public ArrayList<I_Catalogue> getLesCatalogues(){
        return ensembleCat.getLesCatalogues();
    }

    public String[] getNomsDesCatalogues() {
        return ensembleCat.getNomsDesCatalogues();
    }
    
    public String[] getDetailsDesCatalogues() {
        return ensembleCat.getDétailsDesCatalogues();
    }
    
    public int getNombreDeCatalogues() {
        return ensembleCat.getNombreDeCatalogues();
    }
    
    public EnsembleCatalogue getEnsembleCatalogue() {
        //pour tests
        //normalement => lesCat = daoCatalogue.readAll();
        
        EnsembleCatalogue ec = new EnsembleCatalogue();
        Catalogue cat1 = new Catalogue("Catalogue 1");
        Catalogue cat2 = new Catalogue("Catalogue 2");
        Catalogue cat3 = new Catalogue("Catalogue 3");
        Produit p1 = new Produit("mars", 10, 10);
        Produit p2 = new Produit("kinder", 10, 10);
        cat1.addProduit(p1);
        cat1.addProduit(p2);
        cat2.addProduit(p2);
        ec.addCatalogue(cat1);
        ec.addCatalogue(cat2);
        ec.addCatalogue(cat3);
        
        return ec;
    }

    

}

