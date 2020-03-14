/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Arxade
 */
public interface I_CatalogueDAO {
    
    public boolean connect();

    public boolean create(I_Catalogue cat);

    public boolean delete(I_Catalogue cat);

    public ArrayList<I_Catalogue> readAll();

    public I_Catalogue read(String nomCatalogue);
    
    public boolean addProduit(String nom, double prix, int stock, I_Catalogue selectedCatalogue);
    
    public ArrayList<I_Produit> getProduitsFromCatalogue(I_Catalogue catalogue);
    
    public int getNbProduits(I_Catalogue catalogue);
    
}
