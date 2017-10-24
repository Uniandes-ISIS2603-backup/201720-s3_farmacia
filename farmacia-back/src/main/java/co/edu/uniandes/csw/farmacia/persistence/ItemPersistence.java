/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ItemEntity;
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
public class ItemPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ItemPersistence.class.getName());
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto Item que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ItemEntity create(ItemEntity entity) {
        LOGGER.info("Creando un Item nuevo");
        em.persist(entity);
        LOGGER.info("Creando un Item nuevo");
        return entity;
    }
    
    /**
     * Actualiza un Item.
     *
     * @param entity: la Item que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un Item con los cambios aplicados.
     */
    public ItemEntity update(ItemEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando Item con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un Item de la base de datos recibiendo como argumento el id
     * del Item
     *
     * @param id: id correspondiente al Item a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando Item con id={0}", id);
         ItemEntity entity = em.find(ItemEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun Item con el id que se envía de argumento
     *
     * @param id: id correspondiente al Item buscada.
     * @return un Item.
     */
    public ItemEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando Item con id={0}", id);
         return em.find(ItemEntity.class, id);
    }
    
    /**
     * Devuelve todos los Items de la base de datos.
     *
     * @return una lista con todos los Items que encuentre en la base de
     * datos, "select u from ItemEntity u" es como un "select * from
     * ItemEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<ItemEntity> findAll(){
        LOGGER.info("Consultando todos los Items");
        TypedQuery query = em.createQuery("select u from ItemEntity u", ItemEntity.class);
        return query.getResultList();   
    }
    
    public ItemEntity findByName(String name) {
    LOGGER.log(Level.INFO, "Consultando Item por nombre ", name);


    // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
    TypedQuery query = em.createQuery("Select e From ItemEntity e where e.name = :name", ItemEntity.class);
    // Se remplaza el placeholder ":name" con el valor del argumento
    query = query.setParameter("name", name);
    // Se invoca el query se obtiene la lista resultado
    List<ItemEntity> sameName = query.getResultList();
    if (sameName.isEmpty()) {
        return null;
    } else {
        return sameName.get(0);
    }
}
}