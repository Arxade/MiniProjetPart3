/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.Catalogue;
import classes.I_Catalogue;
import classes.Produit;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Arxade
 */
public class CatalogueDAO implements I_CatalogueDAO {

    private Connection connection = null;
    private DatabaseMetaData dbMetadata = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
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
    public boolean create(Catalogue cat) {
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
    public boolean delete(Catalogue cat) {
        String requete = "DELETE FROM CATALOGUES WHERE NOMCATALOGUE = ? ";
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
        System.out.println(lesCatalogues);
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
    public boolean update(Catalogue cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
