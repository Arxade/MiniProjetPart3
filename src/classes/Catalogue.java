/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.List;
import java.math.*;
import java.util.Arrays;

/**
 *
 * @author diazt
 */
public class Catalogue implements I_Catalogue {

    private String nom;
    
    private ArrayList<I_Produit> ensembleProduits;
    private static Catalogue instance;

    public ArrayList<I_Produit> getEnsembleProduits() {
        return ensembleProduits;
    }

    protected Catalogue() {
        ensembleProduits = new ArrayList<I_Produit>();
    }
    
    public static Catalogue getInstance(){
        if (instance == null)
            instance = new Catalogue();
        return instance;
    }

    @Override
    public String toString() {
        String chaine = "";
        for (I_Produit p : ensembleProduits) {
            chaine = chaine + p.toString();
        }
        chaine = chaine + "\nMontant total TTC du stock : " + BigDecimal.valueOf(getMontantTotalTTC()).setScale(2, RoundingMode.HALF_UP) + " €";
        return chaine.replace(".", ",");

    }

    @Override
    public boolean addProduit(I_Produit produit) {
        boolean ajoute = true;
        if (produit == null || produit.getPrixUnitaireHT() <= 0 || produit.getQuantite() < 0) {
            ajoute = false;
        }
        for (I_Produit p : ensembleProduits) {
            if (p.getNom().equals(produit.getNom())) {
                ajoute = false;
            }
        }
        if (ajoute) {
            ensembleProduits.add(produit);
        }
        return ajoute;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        nom = nom.replace("\t", " ");
        nom = nom.trim();
        boolean ajoute = true;
        if (prix <= 0 || qte < 0) {
            ajoute = false;
        }

        for (I_Produit p : ensembleProduits) {
            
            if (p.getNom().equals(nom)) {
                ajoute = false;
            }
        }
        if (ajoute) {
            Produit produit = new Produit(nom, prix, qte);
            ensembleProduits.add(produit);
        }
        return ajoute;
    }
 // Function to remove duplicates from an ArrayList 
    public static <I_Produit> List<I_Produit> removeDuplicates(List<I_Produit> list) 
    { 
  
        // Create a new ArrayList 
        List<I_Produit> newList = new ArrayList<I_Produit>(); 
  
        // Traverse through the first list 
        for (I_Produit element : list) { 
  
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
    } 
    @Override
    public int addProduits(List<I_Produit> catalogNew) {
        int i = 0;
        ArrayList<I_Produit> listeDoublons = new ArrayList<>();
        
        if (catalogNew != null) {
        catalogNew = Catalogue.removeDuplicates(catalogNew);
            for (I_Produit produitOld : ensembleProduits) {
                for (I_Produit produitNew : catalogNew) {
                    if (produitOld.getNom().equals(produitNew.getNom())) {
                        listeDoublons.add(produitNew);
                    }

                }
            }
            catalogNew.removeAll(listeDoublons);
            for (I_Produit produitNew : catalogNew) {
                if (produitNew.getPrixUnitaireHT() > 0 && produitNew.getQuantite() >= 0) {
                    ensembleProduits.add(produitNew);
                    i++;
                }
            }
        }
        return i;

    }

    @Override
    public boolean removeProduit(String nom) {
        boolean remove = false;
        I_Produit produitAEfface = null;
        for (I_Produit prod : ensembleProduits) {
            if (prod.getNom().equals(nom)) {
                remove = true;
                produitAEfface = prod;
            }
        }

        if (remove) {
            ensembleProduits.remove(produitAEfface);
        }

        return remove;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        I_Produit produitAchete = null;
        int i = 0;
        boolean achete = false;
        for (I_Produit prod : ensembleProduits) {
            if (prod.getNom().equals(nomProduit) && qteAchetee > 0) {
                produitAchete = prod;
                produitAchete.ajouter(qteAchetee);
                achete = true;
            }
            i++;

        }
        return achete;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        I_Produit produitVendu = null;
        boolean vendu = false;
        if (qteVendue <= 0) {
            return false;
        }
        for (I_Produit prod : ensembleProduits) {
            if (prod.getNom().equals(nomProduit)) {
                produitVendu = prod;
                return produitVendu.enlever(qteVendue);
            }

        }
        return vendu;
    }

    @Override
    public String[] getNomProduits() {
        String[] lesNoms = new String[ensembleProduits.size()];
        int i = 0;
        for (I_Produit produit : ensembleProduits) {
            lesNoms[i] = produit.getNom();
            i++;
        }
        Arrays.sort(lesNoms);
        return lesNoms;
    }

    @Override
    public double getMontantTotalTTC() {
        double total = 0;
        for (I_Produit produit : ensembleProduits) {
            total = total + produit.getPrixStockTTC();
        }
        BigDecimal totalBG = BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
        total = totalBG.doubleValue();
        return total;
    }

    @Override
    public void clear() {
        ensembleProduits.removeAll(ensembleProduits);
    }
    
    public Boolean estVide() {
        if (ensembleProduits.isEmpty()) {
            return true;
        }
        return false;
    }

}
