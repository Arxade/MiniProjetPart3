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
        return AdaptateurProduitDAO_XML.getInstance();
    }

    @Override
    public I_CatalogueDAO createCatalogueDAO() {
        return new CatalogueDAO_XML();
    }
    

}
