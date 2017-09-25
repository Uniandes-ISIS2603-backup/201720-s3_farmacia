/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.SuministroEntity;
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
public class SuministroPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(SuministroPersistence.class.getName());
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto suministro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public SuministroEntity create(SuministroEntity entity) {
        LOGGER.info("Creando un suministro nuevo");
        em.persist(entity);
        LOGGER.info("Creando un suministro nuevo");
        return entity;
    }
    
    /**
     * Actualiza un suministro.
     *
     * @param entity: la suministro que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un suministro con los cambios aplicados.
     */
    public SuministroEntity update(SuministroEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando suministro con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un suministro de la base de datos recibiendo como argumento el id
     * del suministro
     *
     * @param id: id correspondiente al suministro a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando Suministro con id={0}", id);
         SuministroEntity entity = em.find(SuministroEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun suministro con el id que se envía de argumento
     *
     * @param id: id correspondiente al suministro buscada.
     * @return un suministro.
     */
    public SuministroEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando Suministro con id={0}", id);
         return em.find(SuministroEntity.class, id);
    }
    
    /**
     * Devuelve todos los suministros de la base de datos.
     *
     * @return una lista con todos los suministros que encuentre en la base de
     * datos, "select u from SuministroEntity u" es como un "select * from
     * SuministroEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<SuministroEntity> findAll(){
        LOGGER.info("Consultando todos los suministros");
        TypedQuery query = em.createQuery("select u from SuministroEntity u", SuministroEntity.class);
        return query.getResultList();   
    }
    
}