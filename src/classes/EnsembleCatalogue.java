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
        return lesCatalogues.add(catalogue);
    }
    
    public boolean addLesCatalogues(ArrayList<I_Catalogue> lesCat)
    {
        return lesCatalogues.addAll(lesCat);
    }

    public boolean removeCatalogue(I_Catalogue catalogue) {
        return lesCatalogues.remove(catalogue);
    }

    public ArrayList<I_Catalogue> getLesCatalogues() {
        return lesCatalogues;
    }

    public String[] getNomsDesCatalogues() {
        ArrayList<String> lesNomsAL = new ArrayList<>();
        for (I_Catalogue cat : lesCatalogues) {
            lesNomsAL.add(cat.getNom());
        }
        String[] lesNomsTab = lesNomsAL.toArray(new String[lesNomsAL.size()]);
        return lesNomsTab;
    }
    
    public String[] getDetailsDesCatalogues() {
        ArrayList<String> lesNomsAL = new ArrayList<>();
        for (I_Catalogue cat : lesCatalogues) {
            lesNomsAL.add(cat.getNom() + " : " + cat.getNbProduits() + " produits");
        }
        String[] lesNomsTab = lesNomsAL.toArray(new String[lesNomsAL.size()]);
        return lesNomsTab;
    }

    public int getNombreDeCatalogues() {
        return lesCatalogues.size();
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
