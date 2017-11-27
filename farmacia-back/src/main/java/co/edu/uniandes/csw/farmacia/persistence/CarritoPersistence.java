/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.CarritoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author hs.hernandez
 */
@Stateless
public class CarritoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CarritoPersistence.class.getName());
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto Curso que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CarritoEntity create(CarritoEntity entity) {
        LOGGER.info("Creando un CarritoEntity nuevo");
        em.persist(entity);
        LOGGER.info("Creando un CarritoEntity nuevo");
        return entity;
    }
    
    /**
     * Actualiza un CarritoEntity.
     *
     * @param entity: la CarritoEntity que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un curso con los cambios aplicados.
     */
    public CarritoEntity update(CarritoEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando CarritoEntity con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un curso de la base de datos recibiendo como argumento el id
     * de la Curso
     *
     * @param id: id correspondiente arritoEntity a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando CarritoEntity con id={0}", id);
         CarritoEntity entity = em.find(CarritoEntity.class, id);
         entity.setProductos(null);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun CarritoEntity con el id que se envía de argumento
     *
     * @param id: id correspondiente al cliente buscada.
     * @return un curso.
     */
    public CarritoEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando CarritoEntity con id={0}", id);
         return em.find(CarritoEntity.class, id);
    }
    
    /**
     * Devuelve todos los CarritoEntity de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de
     * datos, "select u from CursoEntity u" es como un "select * from
     * CursoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<CarritoEntity> findAll(){
        LOGGER.info("Consultando todos los CarritoEntity");
        TypedQuery query = em.createQuery("select u from CarritoEntity u", CarritoEntity.class);
        return query.getResultList();   
    }
}
