/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.OrdenEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jp.carreno
 */
@Stateless
public class OrdenPersistence {
    
    
    @PersistenceContext (unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     * 
     * @param entity objeto Orden qe se crea en la base de datos.
     * @return la entidad creada con un id dado por la base de datos.
     */
    public OrdenEntity create(OrdenEntity entity){
       
        em.persist(entity);
       
        return entity;
    }
    
     /**
     * Actualiza una orden.
     *
     * @param entity el objeto orden que viene con los nuevos cambios.
     * @return una orden nueva con los cambios aplicados.
     */
    public OrdenEntity update(OrdenEntity entity) {
      
          return em.merge(entity);
    }
    
    /**
     *
     * Borra una orden de la base de datos recibiendo como argumento el id
     * de la Orden
     *
     * @param id id de la Orden a borrar.
     */
    public void delete(Long id){
       
         OrdenEntity entity = em.find(OrdenEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay alguna Orden con el id que se envía de argumento
     *
     * @param id id de la Orden buscada.
     * @return una Orden si se encuentra.
     */
    public OrdenEntity find(Long id){
         
         return em.find(OrdenEntity.class, id);
    }
    
    /**
     * Devuelve todas las Ordenes de la base de datos.
     *
     * @return lista con todos los objetos ded tipo Ordeb que encuentre en la 
     * base de datos, "select u from OrdenEntity u" es como un "select * from
     * OrdenEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<OrdenEntity> findAll(){
      
        TypedQuery query = em.createQuery("select u from OrdenEntity u", OrdenEntity.class);
        return query.getResultList();   
    }
    
}