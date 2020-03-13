package dao;

import classes.*;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author diazt
 */
public class ProduitDAORel implements I_ProduitDAO {

    private Connection connection = null;
    private DatabaseMetaData dbMetadata = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static ProduitDAORel instanceDAO;
    
    static public ProduitDAORel getInstance(Connection co)
    {
        if(instanceDAO == null)
        {
            instanceDAO = new ProduitDAORel(co);
        }
        return instanceDAO;
    }

    protected ProduitDAORel(Connection co) {
        this.connection = co;
    }

    @Override
    public boolean create(I_Produit produit) {
        String requete = "INSERT INTO PRODUITS (idProduit, nomProduit, prixHTProduit, QteStockProduit) "
                + "VALUES (seqProduits.nextval,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setBigDecimal(2, produit.getPrixDecimal());
            preparedStatement.setInt(3, produit.getQuantite());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur insertion de produit : " + ex);
            return false;
        }
    }

    @Override
    public ArrayList<I_Produit> readAll() {
        ResultSet rs = null;
        ArrayList<I_Produit> lesProduits = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String requete = "SELECT * FROM PRODUITS";
            rs = statement.executeQuery(requete);
            while (rs.next()) {
                I_Produit leProduit = new Produit(rs.getString("NOMPRODUIT"), rs.getDouble("PRIXHTPRODUIT"), rs.getInt("QTESTOCKPRODUIT"));
                lesProduits.add(leProduit);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur read : " + ex);
        }
        System.out.println(lesProduits);
        return lesProduits;
    }

    @Override
    public Produit read(String nomProduit , String nomCatalogue) {
        ResultSet rs = null;
        Produit produit = null;
        try {
            String requete = "SELECT * FROM PRODUITS WHERE NOMPRODUIT = ? AND IDCATALOGUE IN (SELECT IDCATALOGUE FROM CATALOGUES WHERE NOMCATALOGUE = ?)";
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, nomProduit);
            preparedStatement.setString(2, nomCatalogue);
            rs = preparedStatement.executeQuery();
            rs.next();
            produit = new Produit(rs.getString("NOMPRODUIT"), rs.getDouble("PRIXHTPRODUIT"), rs.getInt("QTESTOCKPRODUIT"));
        } catch (SQLException ex) {
            System.out.println("Erreur read : " + ex);
        }
        return produit;
    }

    @Override
    public boolean update(I_Produit produit , String nomCatalogue) {
        try {
            String requete = "UPDATE PRODUITS SET QTESTOCKPRODUIT = ? WHERE NOMPRODUIT = ? AND IDCATALOGUE IN (SELECT IDCATALOGUE FROM CATALOGUES WHERE NOMCATALOGUE = ?)";
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, produit.getQuantite());
            preparedStatement.setString(2, produit.getNom());
            preparedStatement.setString(3, nomCatalogue);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAORel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(I_Produit produit , String nomCatalogue) {
        String requete = "DELETE FROM PRODUITS WHERE NOMPRODUIT = ? AND IDCATALOGUE IN (SELECT IDCATALOGUE FROM CATALOGUES WHERE NOMCATALOGUE = ?)";
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, nomCatalogue);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur suppression de produit : " + ex);
            return false;
        }
    }
    
    
}
