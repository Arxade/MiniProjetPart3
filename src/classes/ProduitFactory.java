/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author diazt
 */
public class ProduitFactory {
    
    protected ProduitFactory(){
    
    }

    public static I_Produit createProduit() {
        return new Produit();
    }

    public static I_Produit createProduit(String nomProduit, double prixHTProduit, int qteStock) {
        return new Produit(nomProduit, prixHTProduit, qteStock);
    }
}
