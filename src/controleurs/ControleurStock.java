/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;
/**
 *
 * @author diazt
 */
public class ControleurStock extends ControleurPrincipal{  
    
    public String getStock(){
        return catalogueSelectionne.toString();
    }
    
    public String[] getNomsProduits() {
        return catalogueSelectionne.getNomProduits();
    }
    
    
}
