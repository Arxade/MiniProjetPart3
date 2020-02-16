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
import sun.security.jca.GetInstance;

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
    
    static public ProduitDAORel getInstance()
    {
        if(instanceDAO == null)
        {
            instanceDAO = new ProduitDAORel();
        }
        return instanceDAO;
    }

    @Override
    public boolean connect() {
        try {
            String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
            connection = DriverManager.getConnection(url, "diazt", "1107013536H");
            System.out.println("Connexion à la BDD");
            return true;
        } catch (HeadlessException | SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    protected ProduitDAORel() {
        this.connect();
    }

    @Override
    public boolean create(Produit produit) {
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
    public Produit read(String nomProduit) {
        ResultSet rs = null;
        Produit produit = null;
        try {
            String requete = "SELECT * FROM PRODUITS WHERE NOMPRODUIT = ?";
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, nomProduit);
            rs = preparedStatement.executeQuery();
            rs.next();
            produit = new Produit(rs.getString("NOMPRODUIT"), rs.getDouble("PRIXHTPRODUIT"), rs.getInt("QTESTOCKPRODUIT"));
        } catch (SQLException ex) {
            System.out.println("Erreur read : " + ex);
        }
        return produit;
    }

    @Override
    public boolean update(Produit produit) {
        try {
            String requete = "UPDATE PRODUITS SET QTESTOCKPRODUIT = ? WHERE NOMPRODUIT = ? ";
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setInt(1, produit.getQuantite());
            preparedStatement.setString(2, produit.getNom());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDAORel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Produit produit) {
        String requete = "DELETE FROM PRODUITS WHERE NOMPRODUIT = ? ";
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur suppression de produit : " + ex);
            return false;
        }
    }
    
    
}
