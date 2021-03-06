/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;
import classes.Catalogue;
import classes.I_Produit;
import classes.Produit;
import dao.I_ProduitDAO;
import dao.RelDAOFactory;
import dao.ProduitDAORel;
import java.awt.Component;
import javax.swing.JOptionPane;
/**
 *
 * @author diazt
 */
public class ControleurTransaction extends ControleurPrincipal{
    
    public void enregistrerAchat(String nomProduit, int qteAchete, Component laFenetre)
    {
        boolean achete = catalogueSelectionne.acheterStock(nomProduit, qteAchete);
        
        if(!achete)
        {
            JOptionPane.showMessageDialog(laFenetre, "Achat impossible");
        }
        else
        {
            I_Produit produit = prodDao.read(nomProduit , catalogueSelectionne.getNom());
            produit.setQuantiteStock(produit.getQuantite() + qteAchete);
            prodDao.update(produit , catalogueSelectionne.getNom());
            JOptionPane.showMessageDialog(laFenetre, "Produit acheté");
        }
    }
    
    public void enregistrerVente(String nomProduit, int QteVendue, Component laFenetre)
    {
        boolean vendu = catalogueSelectionne.vendreStock(nomProduit, QteVendue);
        
        if(!vendu)
        {
            JOptionPane.showMessageDialog(laFenetre, "Vente impossible");
        }
        else
        {
            I_Produit produit = prodDao.read(nomProduit , catalogueSelectionne.getNom());
            produit.setQuantiteStock(produit.getQuantite() - QteVendue);
            prodDao.update(produit , catalogueSelectionne.getNom());
            JOptionPane.showMessageDialog(laFenetre, "Produit vendu");
        }
    }
    
}
