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
public abstract class DAOAbstractFactory {
    
    public static DAOAbstractFactory instance;
    
    protected DAOAbstractFactory(){}
    
    public synchronized static DAOAbstractFactory getInstance()
    {
        if(instance == null)
        {
            instance = new RelDAOFactory();
        }
        return instance;
    }
    
    abstract public I_CatalogueDAO createCatalogueDAO();
    
    abstract public I_ProduitDAO createProduitDAO();
}
