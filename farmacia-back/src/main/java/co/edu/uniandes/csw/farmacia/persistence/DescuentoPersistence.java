

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.DescuentoEntity;
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
public class DescuentoPersistence {
    
    
    @PersistenceContext (unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     * 
     * @param entity Descuento que se crea en la base de datos.
     * @return la entidad creada con un id dado por la base de datos.
     */
    public DescuentoEntity create(DescuentoEntity entity){
       
        em.persist(entity);
       
        return entity;
    }
    
     /**
     * Actualiza un descuento.
     *
     * @param entity el descuento que viene con los nuevos cambios.
     * @return un descuento nuevo con los cambios aplicados.
     */
    public DescuentoEntity update(DescuentoEntity entity) {
      
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un descuento de la base de datos recibiendo como argumento el id
     * del Descuento
     *
     * @param id id del descuento a borrar.
     */
    public void delete(Long id){
       
         DescuentoEntity entity = em.find(DescuentoEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun Descuento con el id que se envia de argumento
     *
     * @param id id del Descuento buscado.
     * @return un Descuento si se encuentra.
     */
    public DescuentoEntity find(Long id){
         
         return em.find(DescuentoEntity.class, id);
    }
    
    /**
     * Devuelve todos los Descuentos de la base de datos.
     *
     * @return lista con todos los descuentos que encuentre en la 
     * base de datos, "select u from DescuentoEntity u" es como un "select * from
     * DescuentoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<DescuentoEntity> findAll(){
      
        TypedQuery query = em.createQuery("select u from DescuentoEntity u", 
                DescuentoEntity.class);
        return query.getResultList();   
    }
    
}