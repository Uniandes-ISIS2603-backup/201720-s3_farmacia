/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.OrdenAprovEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author a.bravo
 */
@Stateless
public class OrdenAprovPersistence {
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    @PersistenceContext(unitName = "ClientePU")
    protected EntityManager em;
    
     /**
     *
     * @param entity objeto orden de aprovisionamiento que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public OrdenAprovEntity create(OrdenAprovEntity entity) {
        LOGGER.info("Creando una orden de aprovisionamiento nueva");
        em.persist(entity);
        LOGGER.info("Creando una orden de aprovisionamiento nueva");
        return entity;
    }
    
    /**
     * Actualiza una orden de aprovisionamiento.
     *
     * @param entity: la orden de aprovisionamiento que viene con los nuevos cambios. Por ejemplo
     * el pedido pudo cambiar. En ese caso, se haria uso del método update.
     * @return un proveedor con los cambios aplicados.
     */
    public OrdenAprovEntity update(OrdenAprovEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando orden de aprovisionamiento con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra una orden de aprovisionamiento de la base de datos recibiendo como argumento el id
     * de la orden de aprovisionamiento
     *
     * @param id: id correspondiente a la orden de aprovisionamiento a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando orden de aprovisionamiento con id={0}", id);
         OrdenAprovEntity entity = em.find(OrdenAprovEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay alguna orden de aprovisionamiento con el id que se envía de argumento
     *
     * @param id: id correspondiente a la orden de aprovisionamiento buscada.
     * @return una orden de aprovisionamiento.
     */
    public OrdenAprovEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando orden de aprovisionamiento con id={0}", id);
         return em.find(OrdenAprovEntity.class, id);
    }
    
    /**
     * Devuelve todas las ordenes de aprovisionamiento de la base de datos.
     *
     * @return una lista con todas las ordenes de aprovisionamiento que encuentre en la base de
     * datos, "select u from OrdenAprovEntity u" es como un "select * from
     * OrdenAprovEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<OrdenAprovEntity> findAll(){
        LOGGER.info("Consultando todas las ordenes de aprovisionamiento");
        TypedQuery query = em.createQuery("select u from OrdenAprovEntity u", OrdenAprovEntity.class);
        return query.getResultList();   
    }
    
}