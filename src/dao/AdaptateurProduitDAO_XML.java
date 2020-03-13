/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.I_Produit;
import classes.Produit;
import classes.ProduitFactory;
import java.util.ArrayList;

/**
 *
 * @author Alexandre
 */
public class AdaptateurProduitDAO_XML implements I_ProduitDAO{

    private ProduitDAO_XML produitDAO_XML;
    static private AdaptateurProduitDAO_XML instanceDAO;
    
    protected AdaptateurProduitDAO_XML()
    {
        this.connect();
    }
    
    static public AdaptateurProduitDAO_XML getInstance()
    {
        if(instanceDAO == null)
        {
            instanceDAO = new AdaptateurProduitDAO_XML();
        }
        return instanceDAO;
    }
    
    @Override
    public boolean connect() {
        try
        {
            System.out.println("Connexion à la BDD");
            produitDAO_XML = new ProduitDAO_XML();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean create(Produit produit) {
        return produitDAO_XML.creer(produit);
    }

    @Override
    public ArrayList<I_Produit> readAll() {
        return (ArrayList) produitDAO_XML.lireTous();
    }

    @Override
    public I_Produit read(String nomProduit) {
        return produitDAO_XML.lire(nomProduit);
    }


    @Override
    public boolean update(Produit produit) {
        return produitDAO_XML.maj(produit);
    }

    @Override
    public boolean delete(Produit produit) {
        return produitDAO_XML.supprimer(produit);
    }
    
    
    
    
    
}
