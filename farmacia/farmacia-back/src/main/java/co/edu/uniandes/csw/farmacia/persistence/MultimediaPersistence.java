/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.MultimediaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lm.gonzalezf
 */
@Stateless
public class MultimediaPersistence {
    private static final Logger LOGGER = Logger.getLogger(MultimediaPersistence.class.getName());
    @PersistenceContext(unitName = "multimediaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto multimedia que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MultimediaEntity create(MultimediaEntity entity) {
        LOGGER.info("Creando un multimedia nuevo");
        em.persist(entity);
        LOGGER.info("Creando un multimedia nuevo");
        return entity;
    }
    
    /**
     * Actualiza un multimedia.
     *
     * @param entity: la multimedia que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un multimedia con los cambios aplicados.
     */
    public MultimediaEntity update(MultimediaEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando multimedia con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un multimedia de la base de datos recibiendo como argumento el id
     * del multimedia
     *
     * @param id: id correspondiente al multimedia a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando multimedia con id={0}", id);
         MultimediaEntity entity = em.find(MultimediaEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun multimedia con el id que se envía de argumento
     *
     * @param id: id correspondiente al multimedia buscada.
     * @return un multimedia.
     */
    public MultimediaEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando multimedia con id={0}", id);
         return em.find(MultimediaEntity.class, id);
    }
    
    /**
     * Devuelve todos los multimedias de la base de datos.
     *
     * @return una lista con todos los multimedias que encuentre en la base de
     * datos, "select u from MultimediaEntity u" es como un "select * from
     * MultimediaEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<MultimediaEntity> findAll(){
        LOGGER.info("Consultando todos los multimedias");
        TypedQuery query = em.createQuery("select u from MultimediaEntity u", MultimediaEntity.class);
        return query.getResultList();   
    }
}