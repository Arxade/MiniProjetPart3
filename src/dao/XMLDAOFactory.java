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
public class XMLDAOFactory extends DAOAbstractFactory{
    protected XMLDAOFactory(){
    
    }

    @Override
    public  I_ProduitDAO createProduitDAO() {
        return new AdaptateurProduitDAO_XML();
    }

    @Override
    public I_CatalogueDAO createCatalogueDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
