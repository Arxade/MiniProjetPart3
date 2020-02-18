/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classes.*;
import dao.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre
 */
public class ControleurPrincipal {

    protected I_CatalogueDAO catDao = new CatalogueDAO();
    protected I_ProduitDAO prodDao = ProduitDAOFactory.createProduitDAORelationnel();
    protected EnsembleCatalogue ensembleCat = getEnsembleCatalogue();
    protected I_Catalogue catalogueSelected;
    
    public void addCatalogue(String nomCatalogue) {
        Catalogue catalogue = new Catalogue(nomCatalogue);
        ensembleCat.addCatalogue(catalogue);
        catDao.create(catalogue);
        JOptionPane.showMessageDialog(null, "Catalogue "+nomCatalogue+" ajouté");
    }

    public void removeCatalogue(String nomCatalogue) {
        I_Catalogue removedCat = ensembleCat.getCatalogueFromNom(nomCatalogue);
        catDao.delete(removedCat);
        ensembleCat.removeCatalogue(removedCat);
        JOptionPane.showMessageDialog(null, "Catalogue "+nomCatalogue+" supprimé");
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
        EnsembleCatalogue ec = new EnsembleCatalogue();
        catDao.connect();
        ec.addLesCatalogues(catDao.readAll());
        return ec;
    }
    
    public void setCatalogue(String catalogueSelectionne){
        catalogueSelected = ensembleCat.getCatalogueFromNom(catalogueSelectionne);
    }
    
    public ControleurStock createControleurStock(){
        ControleurStock ctrlStock = new ControleurStock();
        ctrlStock.catalogueSelected = this.catalogueSelected;
        return ctrlStock;
    }

    public ControleurProduit createControleurProduit() {
        ControleurProduit ctrlProduit = new ControleurProduit();
        ctrlProduit.catalogueSelected = this.catalogueSelected;
        return ctrlProduit;
    }

    public ControleurTransaction createControleurTransaction() {
        ControleurTransaction ctrlTransaction = new ControleurTransaction();
        ctrlTransaction.catalogueSelected = this.catalogueSelected;
        return ctrlTransaction;
    }
    

    

}
