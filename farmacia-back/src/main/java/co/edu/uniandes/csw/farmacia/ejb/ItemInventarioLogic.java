/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.farmacia.entities.ItemInventarioEntity;
import co.edu.uniandes.csw.farmacia.persistence.ItemInventarioPersistence;
import java.util.List;

/**
 *
 * @author a.gracia10
 */
@Stateless
public class ItemInventarioLogic {
    @Inject
    private ItemInventarioPersistence persistence;
    
    public ItemInventarioEntity create (ItemInventarioEntity ent)
    {
        return persistence.create(ent);
    }
    
    public List<ItemInventarioEntity> findAll()
    {
        return persistence.findAll();
    }
    
    public ItemInventarioEntity findItemById(long id)
    {
        return persistence.findById(id);
    }
    
    public ItemInventarioEntity update (ItemInventarioEntity ent)
    {
        return persistence.update(ent);
    }
    
    public void delete (long id)
    {
        persistence.delete(id);
    }
    
        
    
}
