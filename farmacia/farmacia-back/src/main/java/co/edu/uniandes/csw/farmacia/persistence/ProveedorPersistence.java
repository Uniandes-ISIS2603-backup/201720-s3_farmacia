/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProveedorEntity;
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
public class ProveedorPersistence {
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto proveedor que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ProveedorEntity create(ProveedorEntity entity) {
        LOGGER.info("Creando un proveedor nuevo");
        em.persist(entity);
        LOGGER.info("Creando un proveedor nuevo");
        return entity;
    }
    
    /**
     * Actualiza un proveedor.
     *
     * @param entity: el proveedor que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un proveedor con los cambios aplicados.
     */
    public ProveedorEntity update(ProveedorEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando proveedor con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un proveedor de la base de datos recibiendo como argumento el id
     * del proveedor
     *
     * @param id: id correspondiente al proveedor a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando proveedor con id={0}", id);
         ProveedorEntity entity = em.find(ProveedorEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun proveedor con el id que se envía de argumento
     *
     * @param id: id correspondiente al proveedor buscado.
     * @return un proveedor.
     */
    public ProveedorEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando proveedor con id={0}", id);
         return em.find(ProveedorEntity.class, id);
    }
    
    /**
     * Devuelve todos los proveedores de la base de datos.
     *
     * @return una lista con todos los proveedores que encuentre en la base de
     * datos, "select u from ProveedorEntity u" es como un "select * from
     * ProveedorEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<ProveedorEntity> findAll(){
        LOGGER.info("Consultando todos los proveedores");
        TypedQuery query = em.createQuery("select u from ProveedorEntity u", ProveedorEntity.class);
        return query.getResultList();   
    }
}