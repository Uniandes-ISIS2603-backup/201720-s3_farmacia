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
    @PersistenceContext(unitName = "farmaciaPU")
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
    
    public MultimediaEntity find(Long productoId, Long multimediaId) {
        TypedQuery<MultimediaEntity> q = em.createQuery("select p from MultimediaEntity p where (p.producto.id = :productoId) and (p.id = :multimediaId)", MultimediaEntity.class);
        q.setParameter("productoId", productoId);
        q.setParameter("multimediaId", multimediaId);
        List<MultimediaEntity> results = q.getResultList();
        MultimediaEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }

        return review;
    }
    
    /**
     * Busca si hay algun producto con el id que se envía de argumento
     *
     * @param id: id correspondiente al producto buscada.
     * @return un producto.
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
    
    public MultimediaEntity findByName(String name) {
    LOGGER.log(Level.INFO, "Consultando producto por nombre ", name);


    // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
    TypedQuery query = em.createQuery("Select e From MultimediaEntity e where e.name = :name", MultimediaEntity.class);
    // Se remplaza el placeholder ":name" con el valor del argumento
    query = query.setParameter("name", name);
    // Se invoca el query se obtiene la lista resultado
    List<MultimediaEntity> sameName = query.getResultList();
    if (sameName.isEmpty()) {
        return null;
    } else {
        return sameName.get(0);
    }
}
}