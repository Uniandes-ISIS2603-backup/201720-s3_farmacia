/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.farmacia.entities.ItemInventarioEntity;
import java.util.logging.Level;
import javax.persistence.Query;

/**
 *
 * @author a.gracia10
 */
@Stateless
public class ItemInventarioPersistence  {
    
    private static final Logger LOGGER = Logger.getLogger(ItemInventarioPersistence.class.getName());
    
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    public List<ItemInventarioEntity> findAll()
    {
        Query q = em.createQuery("Select u From ItemInventarioEntity u");
        return q.getResultList();
    }
    
    public ItemInventarioEntity findById(long id)
    {
        LOGGER.log(Level.INFO, "Consultando el item en el inventario con id={0}", id);
        return em.find(ItemInventarioEntity.class, id);
    }
    
    public ItemInventarioEntity create (ItemInventarioEntity ent)
    {
        LOGGER.info("Creando un item en el inventario nuevo");
        em.persist(ent);
        LOGGER.info("item creado");
        return ent;
    }
    
    public ItemInventarioEntity update (ItemInventarioEntity ent)
    {
            LOGGER.info ("Actualizando el item");
            return em.merge(ent);
    }
    
    public void delete (long id)
    {
        LOGGER.log(Level.INFO, "Borrando el item con id ={0}",id);
        ItemInventarioEntity ent = findById(id);
        em.remove(ent);
    }
        
    
    
}
