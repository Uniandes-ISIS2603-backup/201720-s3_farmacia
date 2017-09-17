/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;


import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
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
public class FacturaPersistence {
    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());
    
    @PersistenceContext(unitName = "FacturaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto factura que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public FacturaEntity create(FacturaEntity entity) {
        LOGGER.info("Creando una factura nuevo");
        em.persist(entity);
        LOGGER.info("Creando una factura nuevo");
        return entity;
    }
    
    /**
     * Actualiza un factura.
     *
     * @param entity: la factura que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un factura con los cambios aplicados.
     */
    public FacturaEntity update(FacturaEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando factura con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra una facura de la base de datos recibiendo como argumento el id
     * de la factura
     *
     * @param id: id correspondiente a la factura a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando factura con id={0}", id);
         FacturaEntity entity = em.find(FacturaEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun cliente con el id que se envía de argumento
     *
     * @param id: id correspondiente al cliente buscada.
     * @return un curso.
     */
    public FacturaEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando factura con id={0}", id);
         return em.find(FacturaEntity.class, id);
    }
    
    /**
     * Devuelve todos los facutura de la base de datos.
     *
     * @return una lista con todos los factura que encuentre en la base de
     * datos, "select u from FacturaEntity u" es como un "select * from
     * Factura;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<FacturaEntity> findAll(){
        LOGGER.info("Consultando todos los clientes");
        TypedQuery query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        return query.getResultList();   
    }
}