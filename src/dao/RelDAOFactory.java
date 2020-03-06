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
public class RelDAOFactory extends DAOAbstractFactory{

    public  I_CatalogueDAO createCatalogueDAO() {
        return CatalogueDAORel.getInstance();
    }
    
    public  I_ProduitDAO createProduitDAO() {
        return ProduitDAORel.getInstance(CatalogueDAORel.getInstance().getConnection());
    }
}
