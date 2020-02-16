/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import java.lang.Math;
import java.math.*;

/**
 *
 * @author diazt
 */
public class Produit implements I_Produit {

    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private BigDecimal prixDecimal;
    static private double tauxTVA = 0.20;
    static private BigDecimal tauxTVADecimal = BigDecimal.valueOf(tauxTVA);

    public Produit() {
    }

    public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
        if (prixUnitaireHT > 0 && quantiteStock >= 0){
        nom = nom.replace("\t"," ");
        this.quantiteStock = quantiteStock;
        this.nom = nom.trim();
        this.prixUnitaireHT = prixUnitaireHT;}
        this.prixDecimal = BigDecimal.valueOf(prixUnitaireHT).setScale(2, RoundingMode.HALF_UP );
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public String getNom() {
        nom = nom.replace("\t"," ");
        nom = nom.trim();
        return nom;
    }

    public void setNom(String nom) {
        nom = nom.replace("\t"," ");
        this.nom = nom.trim();
    }

    public double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    public void setPrixUnitaireHT(double prixUnitaireHT) {
        this.prixUnitaireHT = prixUnitaireHT;
        this.prixDecimal = BigDecimal.valueOf(prixUnitaireHT).setScale(2, RoundingMode.HALF_UP );
    }

    public BigDecimal getPrixDecimal() {
        return prixDecimal;
    }

    public void setPrixDecimal(BigDecimal prixDecimal) {
        this.prixDecimal = prixDecimal;
    }

    public static double getTauxTVA() {
        return tauxTVA;
    }

    public static void setTauxTVA(double tauxTVA) {
        Produit.tauxTVA = tauxTVA;
    }

    @Override
    public String toString() {
        String chaine;
        chaine = nom + " - prix HT : " + prixDecimal + " € " + "- prix TTC : " 
                + BigDecimal.valueOf(getPrixUnitaireTTC()).setScale(2, RoundingMode.HALF_UP) + " € - quantité en stock : " + quantiteStock + "\n";
        return chaine.replace(".", ",");
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        quantiteStock = quantiteStock + qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        if (quantiteStock - qteVendue >= 0) {
            quantiteStock = quantiteStock - qteVendue;
            return true;
        } else {
            System.out.println("Stock épuisé ou insuffisant");
            return false;
        }
    }

    @Override
    public int getQuantite() {
        return quantiteStock;
    }

    @Override
    public double getPrixUnitaireTTC() {
       
       //double res = (prixCalcul.multiply(tauxTVACalcul)).setScale(2, RoundingMode.HALF_UP).doubleValue();
       double res = prixUnitaireHT * (1 + tauxTVA);
       return res;
    }

    @Override
    public double getPrixStockTTC() {
       double res = (prixUnitaireHT *  quantiteStock) * (1 + tauxTVA) ;
       return res;
    }

}
