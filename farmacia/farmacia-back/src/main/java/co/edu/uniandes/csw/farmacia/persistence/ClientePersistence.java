/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
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
public class ClientePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
    @PersistenceContext(unitName = "ClientePU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto Curso que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity entity) {
        LOGGER.info("Creando un cliente nuevo");
        em.persist(entity);
        LOGGER.info("Creando un cliente nuevo");
        return entity;
    }
    
    /**
     * Actualiza un curso.
     *
     * @param entity: la cliente que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un curso con los cambios aplicados.
     */
    public ClienteEntity update(ClienteEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando cliente con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un curso de la base de datos recibiendo como argumento el id
     * de la Curso
     *
     * @param id: id correspondiente a la Curso a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando Curso con id={0}", id);
         ClienteEntity entity = em.find(ClienteEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun cliente con el id que se envía de argumento
     *
     * @param id: id correspondiente al cliente buscada.
     * @return un curso.
     */
    public ClienteEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando Cliente con id={0}", id);
         return em.find(ClienteEntity.class, id);
    }
    
    /**
     * Devuelve todos los cliente de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de
     * datos, "select u from CursoEntity u" es como un "select * from
     * CursoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<ClienteEntity> findAll(){
        LOGGER.info("Consultando todos los clientes");
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();   
    }
    
}