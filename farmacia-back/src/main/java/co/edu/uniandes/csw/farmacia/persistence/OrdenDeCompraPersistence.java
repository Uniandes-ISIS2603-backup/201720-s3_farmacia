/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author hs.hernandez
 */
@Stateless
public class OrdenDeCompraPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(OrdenDeCompraPersistence.class.getName());

    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;

    /**
     * 
     * @param id
     * @return 
     */
    public OrdenDeCompraEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando orden de compra con id={0}", id);
        return em.find(OrdenDeCompraEntity.class, id);
    }

    /**
     * 
     * @param name
     * @return 
     */
    public OrdenDeCompraEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando orden de compra con name= ", name);
        TypedQuery<OrdenDeCompraEntity> q
                = em.createQuery("select u from OrdenDeCompraEntity u where u.name = :name", OrdenDeCompraEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    /**
     * 
     * @return 
     */
    public List<OrdenDeCompraEntity> findAll() {
        LOGGER.info("Consultando todas las ordenes de compra");
        Query q = em.createQuery("select u from OrdenDeCompraEntity u");
        return q.getResultList();
    }

    public OrdenDeCompraEntity create(OrdenDeCompraEntity entity) {
        LOGGER.info("Creando una Orden De Compra nueva");
        em.persist(entity);
        LOGGER.info("Orden De Compra creada");
        return entity;
    }
    
    /**
     * 
     * @param entity
     * @return 
     */
    public OrdenDeCompraEntity update(OrdenDeCompraEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Orden De Compra con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    /**
     * 
     * @param id 
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Orden De Compra con id={0}", id);
        OrdenDeCompraEntity entity = em.find(OrdenDeCompraEntity.class, id);
        em.remove(entity);
    }
    
    public OrdenDeCompraEntity findByClient(Long idCliente, Long idOrden){
        ClienteEntity cliente = find(idOrden).getCliente();
        if(cliente.getId().equals(idCliente))
            return find(idOrden);
    return null;
    }
}