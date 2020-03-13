/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.I_Produit;
import classes.Produit;
import java.util.ArrayList;

/**
 *
 * @author diazt
 */
public interface I_ProduitDAO {

    public boolean connect();

    public boolean create(I_Produit produit);
    
    public boolean delete(I_Produit produit, String nomCatalogue);

    public ArrayList<I_Produit> readAll();

    public I_Produit read(String nomProduit, String nomCatalogue);
    
    public boolean update(I_Produit produit, String nomCatalogue);
    
    
}
