/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import classes.*;
import java.util.ArrayList;

/**
 *
 * @author Arxade
 */
public interface I_CatalogueDAO {
    
    public boolean connect();

    public boolean create(Catalogue cat);

    public boolean delete(Catalogue cat);

    public ArrayList<I_Catalogue> readAll();

    public I_Catalogue read(String nomCatalogue);

    public boolean update(Catalogue cat);
    
}
