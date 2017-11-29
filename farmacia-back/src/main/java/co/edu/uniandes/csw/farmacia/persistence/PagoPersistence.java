/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.PagoEntity;
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
public class PagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto Pago que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PagoEntity create(PagoEntity entity) {
        LOGGER.info("Creando un pago nuevo");
        em.persist(entity);
        LOGGER.info("Creando un pago nuevo");
        return entity;
    }
    
    /**
     * Actualiza un pago.
     *
     * @param entity: el pago que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un pago con los cambios aplicados.
     */
    public PagoEntity update(PagoEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando pago con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un pago de la base de datos recibiendo como argumento el id
     * del pago
     *
     * @param id: id correspondiente al pago a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando pago con id={0}", id);
         PagoEntity entity = em.find(PagoEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun pago con el id que se envía de argumento
     *
     * @param id: id correspondiente al pago buscado.
     * @return un pago.
     */
    public PagoEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando pago con id={0}", id);
         return em.find(PagoEntity.class, id);
    }
    
    /**
     * Devuelve todos los pagos de la base de datos.
     *
     * @return una lista con todos los pagos que encuentre en la base de
     * datos, "select u from PsgoEntity u" es como un "select * from
     * PagoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<PagoEntity> findAll(){
        LOGGER.info("Consultando todos los pagos");
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();   
    }
    
}