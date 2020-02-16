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
public class ControleurProduit extends Controleur {

    public void createProduit(String nom, double prix, int qteStock) {
        Produit produit = null;
        if (catalogue.addProduit(nom, prix, qteStock) == false) {
            JOptionPane.showMessageDialog(null, "Produit déjà existant ou prix invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
        produit = new Produit(nom, prix, qteStock);
        System.out.println(produit.toString());
        dao.create(produit);
        JOptionPane.showMessageDialog(null, "Le produit " + nom + " a bien été créé.", "Produit créé", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void removeProduit(String nomProduit) {
        catalogue.removeProduit(nomProduit);
        Produit produit = dao.read(nomProduit);
        dao.delete(produit);
        JOptionPane.showMessageDialog(null, "Produit " + produit.getNom() + " supprimé");
    }

}
