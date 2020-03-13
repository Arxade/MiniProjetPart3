/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.I_Produit;
import java.util.ArrayList;

/**
 *
 * @author Alexandre
 */
public class AdaptateurProduitDAO_XML implements I_ProduitDAO{

    private ProduitDAO_XML produitDAO_XML;
    static private AdaptateurProduitDAO_XML instanceDAO;
    
    public AdaptateurProduitDAO_XML()
    {
            produitDAO_XML = new ProduitDAO_XML();
    }

    @Override
    public boolean create(I_Produit produit) {
        return produitDAO_XML.creer(produit);
    }

    @Override
    public ArrayList<I_Produit> readAll() {
        return (ArrayList) produitDAO_XML.lireTous();
    }

    @Override
    public I_Produit read(String nomProduit , String nomCat) {
        return produitDAO_XML.lire(nomProduit , nomCat);
    }


    @Override
    public boolean update(I_Produit produit , String nomCat) {
        return produitDAO_XML.maj(produit , nomCat);
    }

    @Override
    public boolean delete(I_Produit produit , String nomCat) {
        return produitDAO_XML.supprimer(produit , nomCat);
    }
    
    
    
    
    
}
