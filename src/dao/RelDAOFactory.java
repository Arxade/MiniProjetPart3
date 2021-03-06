/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Alexandre
 */
public class RelDAOFactory extends DAOAbstractFactory {

    private Connection cn;
    private static RelDAOFactory instance;

    protected RelDAOFactory() {
        connect();
    }

    public static RelDAOFactory getInstance() {
        if (instance == null) {
            instance = new RelDAOFactory();
        }
        return instance;
    }

    public boolean connect() {
        try {
            String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
            cn = DriverManager.getConnection(url, "bernadaca", "1110042889h");
            System.out.println("Connexion à la BDD");
            return true;
        } catch (HeadlessException | SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public I_CatalogueDAO createCatalogueDAO() {
        return CatalogueDAORel.getInstance(cn);
    }

    public I_ProduitDAO createProduitDAO() {
        return ProduitDAORel.getInstance(cn);
    }
}
