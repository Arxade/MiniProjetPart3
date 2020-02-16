/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classes.Catalogue;
import dao.I_ProduitDAO;
import dao.ProduitDAOFactory;
import dao.ProduitDAORel;

/**
 *
 * @author Alexandre
 */
public class Controleur {
    
    protected I_ProduitDAO dao = ProduitDAOFactory.createProduitDAORelationnel();
    protected Catalogue catalogue = getCatalogue();
    
    protected Catalogue getCatalogue()
    {
        Catalogue cat = Catalogue.getInstance();
        if (cat.estVide() == true) {
            System.out.println("Chargement du catalogue");
            cat.addProduits(dao.readAll());
        }
        return cat;
    }
    
}
