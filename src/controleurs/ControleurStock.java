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
        return catalogueSelected.toString();
    }
    
    public String[] getNomsProduits() {
        return catalogueSelected.getNomProduits();
    }
    
    
}
