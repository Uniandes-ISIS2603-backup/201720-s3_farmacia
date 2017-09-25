/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.DireccionEntity;
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
public class DireccionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(DireccionPersistence.class.getName());
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto Curso que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public DireccionEntity create(DireccionEntity entity) {
        LOGGER.info("Creando una direccion nueva");
        em.persist(entity);
        LOGGER.info("Creando una direccion nueva");
        return entity;
    }
    
    /**
     * Actualiza un curso.
     *
     * @param entity: la cliente que viene con los nuevos cambios. Por ejemplo
     * la ciudad pudo cambiar. En ese caso, se haria uso del método update.
     * @return una direccion con los cambios aplicados.
     */
    public DireccionEntity update(DireccionEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando direccion con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra una direccion de la base de datos recibiendo como argumento el id
     * de la Direccion
     *
     * @param id: id correspondiente a la Direccion a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando Direccion con id={0}", id);
         DireccionEntity entity = em.find(DireccionEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay alguna direccion con el id que se envía de argumento
     *
     * @param id: id correspondiente a la direccion buscada.
     * @return una direccion.
     */
    public DireccionEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando Direccion con id={0}", id);
         return em.find(DireccionEntity.class, id);
    }
    
    /**
     * Devuelve todas los direcciones de la base de datos.
     *
     * @return una lista con todos las direccoines que encuentre en la base de
     * datos, "select u from DireccionEntity u" es como un "select * from
     * DireccionEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<DireccionEntity> findAll(){
        LOGGER.info("Consultando todas las direcciones");
        TypedQuery query = em.createQuery("select u from DireccionEntity u", DireccionEntity.class);
        return query.getResultList();   
    }
    
}