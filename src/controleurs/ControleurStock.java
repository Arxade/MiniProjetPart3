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
public class ControleurStock extends Controleur{
    
    public String getStock(){
        return catalogue.toString();
    }
    
    public String[] getNomsProduits() {
        return catalogue.getNomProduits();
    }
    
    
}
