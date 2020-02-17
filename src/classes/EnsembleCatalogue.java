/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author Arxade
 */
public class EnsembleCatalogue {

    private ArrayList<I_Catalogue> lesCatalogues = new ArrayList<>();

    public EnsembleCatalogue() {
    }

    public boolean addCatalogue(I_Catalogue catalogue) {
        lesCatalogues.add(catalogue);
        return true;
    }

    public boolean removeCatalogue(I_Catalogue catalogue) {
        return lesCatalogues.remove(catalogue);
    }
    
    public I_Catalogue getCatalogueFromNom(String nomCatalogue) {
        int i = 0;
        boolean trouve = false;

        while (trouve == false && i < lesCatalogues.size()) {
            if (lesCatalogues.get(i).getNom().equals(nomCatalogue)) {
                trouve = true;
            } else {
                i++;
            }
        }
        
        if (trouve == true) {
            return lesCatalogues.get(i);
        } else {
            return null;
        }
    }

}
    
    
 
