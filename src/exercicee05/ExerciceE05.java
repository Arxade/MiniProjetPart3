/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicee05;

import interfaces.FenetrePrincipale;
import classes.*;
import controleurs.*;
import dao.*;
import interfaces.FenetreAccueil;

/**
 *
 * @author diazt
 */
public class ExerciceE05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Controleur ctrl = new Controleur();
        FenetreAccueil fenetre = new FenetreAccueil();
        
        EnsembleCatalogue lesCat = new EnsembleCatalogue();
        Catalogue cat1 = new Catalogue("test");
        Catalogue cat2 = new Catalogue("poke");
        lesCat.addCatalogue(cat1);
        lesCat.addCatalogue(cat2);
        System.out.println(lesCat.getCatalogueFromNom("poke").getNom());
        
    }

}
