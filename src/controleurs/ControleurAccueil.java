/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classes.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre
 */
public class ControleurAccueil {
    
    private EnsembleCatalogue lesCat = new EnsembleCatalogue();
    
    public void addCatalogue(String nom) {
        Catalogue catalogue = new Catalogue(nom);
        lesCat.addCatalogue(catalogue);  
    }

    public void removeCatalogue(String nomCatalogue) {
        lesCat.removeCatalogue(lesCat.getCatalogueFromNom(nomCatalogue));
    }

}

