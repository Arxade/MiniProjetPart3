/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Catalogue;
import classes.I_Catalogue;
import classes.I_Produit;
import classes.Produit;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
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

/**
 *
 * @author Arxade
 */
public class CatalogueDAORel implements I_CatalogueDAO {

    private Connection connection = null;
    private DatabaseMetaData dbMetadata = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static I_CatalogueDAO instance;
    
    public static I_CatalogueDAO getInstance(Connection cn)
    {
        if(instance == null)
        {
            instance = new CatalogueDAORel(cn);
        }
        return instance;
    }
    
    private CatalogueDAORel()
    {
    }

    private CatalogueDAORel(Connection cn) {
        connection = cn;
    }

    @Override
    public boolean connect() {
         try {
            String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
            connection = DriverManager.getConnection(url, "bernadaca", "1110042889h");
            System.out.println("Connexion à la BDD");
            return true;
        } catch (HeadlessException | SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    @Override
    public boolean create(I_Catalogue cat) {
        String requete = "INSERT INTO CATALOGUES (idCatalogue, nomCatalogue) "
                + "VALUES (seqCatalogues.nextval,?)";
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, cat.getNom());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur insertion de Catalogue : " + ex);
            return false;
        }
    }

    @Override
    public boolean delete(I_Catalogue cat) {
        String requete = "DELETE FROM CATALOGUES WHERE NOMCATALOGUE = ?";
        try {
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, cat.getNom());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur suppression de Catalogue : " + ex);
            return false;
        }
    }

    @Override
    public ArrayList<I_Catalogue> readAll() {
        ResultSet rs = null;
        ArrayList<I_Catalogue> lesCatalogues = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String requete = "SELECT * FROM CATALOGUES";
            rs = statement.executeQuery(requete);
            while (rs.next()) {
                I_Catalogue leCatalogue = new Catalogue(rs.getString("NOMCATALOGUE"));
                lesCatalogues.add(leCatalogue);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur read : " + ex);
        }
        return lesCatalogues;
    }

    @Override
    public I_Catalogue read(String nomCatalogue) {
        ResultSet rs = null;
        I_Catalogue cat = null;
        try {
            String requete = "SELECT * FROM CATALOGUES WHERE NOMCATALOGUE = ?";
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, nomCatalogue);
            rs = preparedStatement.executeQuery();
            rs.next();
            cat = new Catalogue(rs.getString("NOMPRODUIT"));
        } catch (SQLException ex) {
            System.out.println("Erreur read : " + ex);
        }
        return cat;
    }
    
    @Override
    public int getNbProduits(I_Catalogue catalogue) {
        ResultSet rs = null;
        int nbProduits = 0;
        try {
            String requete = "SELECT COUNT(*) "
                    + "FROM PRODUITS "
                    + "NATURAL JOIN CATALOGUES "
                    + "WHERE NOMCATALOGUE = ?";
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, catalogue.getNom());
            rs = preparedStatement.executeQuery();
            rs.next();
            nbProduits = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("Erreur count produits : " + ex);
        }
        return nbProduits;
    }

    @Override
    public boolean addProduit(String nom , double prix , int stock, I_Catalogue selectedCatalogue) {
            try
            {
                ResultSet rs;
                String nomCatalogue = selectedCatalogue.getNom();
                int idCat = 0;

                preparedStatement = connection.prepareStatement("SELECT IDCATALOGUE FROM CATALOGUES WHERE NOMCATALOGUE = ?");
                preparedStatement.setString(1, nomCatalogue);
                rs = preparedStatement.executeQuery();
                rs.next();
                idCat = rs.getInt(1);
                System.out.println(idCat);

                System.err.println(nomCatalogue);

                preparedStatement = connection.prepareCall("INSERT INTO PRODUITS (IDPRODUIT, NOMPRODUIT, PRIXHTPRODUIT, QTESTOCKPRODUIT, IDCATALOGUE) "
                        + "VALUES(seqProduits.nextval, ?, ?, ? ,?)");
                preparedStatement.setString(1, nom);
                preparedStatement.setDouble(2, prix);
                preparedStatement.setInt(3, stock);
                preparedStatement.setInt(4, idCat);
                preparedStatement.executeUpdate();

                return true;

            }
            catch(SQLException ex)
            {
                System.out.println("Erreur lors de l'ajout d'un produit " + ex);
                return false;
            }
    }
    
    @Override
    public ArrayList<I_Produit> getProduitsFromCatalogue(I_Catalogue catalogue) {
                    ArrayList<I_Produit> lesProduits = new ArrayList<>();
        try {

            String requete = "SELECT * FROM PRODUITS "
                    + "NATURAL JOIN CATALOGUES "
                    + "WHERE NOMCATALOGUE = ?";
            ResultSet rs;
            String nomCatalogue = catalogue.getNom();
            
            preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, nomCatalogue);
            rs = preparedStatement.executeQuery();
            I_Produit leProduit = null;
            while (rs.next()) {
                leProduit = new Produit(rs.getString("NOMPRODUIT"), rs.getDouble("PRIXHTPRODUIT"), rs.getInt("QTESTOCKPRODUIT"));
                lesProduits.add(leProduit);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CatalogueDAORel.class.getName()).log(Level.SEVERE, null, ex);
        }
            return lesProduits;
    }
    

    
    
    
}
