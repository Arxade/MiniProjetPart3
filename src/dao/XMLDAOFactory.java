/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Alexandre
 */
public class XMLDAOFactory extends DAOAbstractFactory {

    public static XMLDAOFactory instance;

    protected XMLDAOFactory() {

    }

    public static XMLDAOFactory getInstance() {
        {
            if (instance == null) {
                instance = new XMLDAOFactory();
            }
            return instance;
        }
    }

//    
    @Override
    public I_ProduitDAO createProduitDAO() {
        return new AdaptateurProduitDAOXML();
    }

    @Override
    public I_CatalogueDAO createCatalogueDAO() {
        return new CatalogueDAOXML();
    }

}
