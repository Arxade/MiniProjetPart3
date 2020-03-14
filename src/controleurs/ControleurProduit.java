/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;
import classes.*;
import dao.*;
import javax.swing.*;

/**
 *
 * @author diazt
 */
public class ControleurProduit extends ControleurPrincipal {

    public void createProduit(String nom, double prix, int qteStock) {
        I_Produit produit = null;
        if (catalogueSelectionne.addProduit(nom, prix, qteStock) == false) {
            JOptionPane.showMessageDialog(null, "Produit déjà existant ou prix invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
        catDao.addProduit(nom , prix , qteStock , catalogueSelectionne);
        JOptionPane.showMessageDialog(null, "Le produit " + nom + " a bien été créé.", "Produit créé", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void removeProduit(String nomProduit) {
        catalogueSelectionne.removeProduit(nomProduit);
        I_Produit produit = prodDao.read(nomProduit , catalogueSelectionne.getNom());
        prodDao.delete(produit , catalogueSelectionne.getNom());
        JOptionPane.showMessageDialog(null, "Produit " + nomProduit + " supprimé");
    }

}
