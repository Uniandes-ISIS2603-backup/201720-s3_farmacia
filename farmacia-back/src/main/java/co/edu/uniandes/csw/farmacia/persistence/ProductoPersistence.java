/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
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
public class ProductoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ProductoPersistence.class.getName());
    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto producto que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ProductoEntity create(ProductoEntity entity) {
        LOGGER.info("Creando un producto nuevo");
        em.persist(entity);
        LOGGER.info("Creando un producto nuevo");
        return entity;
    }
    
    /**
     * Actualiza un producto.
     *
     * @param entity: la producto que viene con los nuevos cambios. Por ejemplo
     * el codigo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un producto con los cambios aplicados.
     */
    public ProductoEntity update(ProductoEntity entity) {
         LOGGER.log(Level.INFO, "Actualizando producto con id={0}", entity.getId());
          return em.merge(entity);
    }
    
    /**
     *
     * Borra un producto de la base de datos recibiendo como argumento el id
     * del producto
     *
     * @param id: id correspondiente al producto a borrar.
     */
    public void delete(Long id){
         LOGGER.log(Level.INFO, "Borrando producto con id={0}", id);
         ProductoEntity entity = em.find(ProductoEntity.class, id);
         em.remove(entity);
    }
    
    /**
     * Busca si hay algun producto con el id que se envía de argumento
     *
     * @param id: id correspondiente al producto buscada.
     * @return un producto.
     */
    public ProductoEntity find(Long id){
         LOGGER.log(Level.INFO, "Consultando producto con id={0}", id);
         return em.find(ProductoEntity.class, id);
    }
    
    /**
     * Devuelve todos los productos de la base de datos.
     *
     * @return una lista con todos los productos que encuentre en la base de
     * datos, "select u from ProductoEntity u" es como un "select * from
     * ProductoEntity;" - "SELECT * FROM table_codigo" en SQL.
     */
    
    public List<ProductoEntity> findAll(){
        LOGGER.info("Consultando todos los productos");
        TypedQuery query = em.createQuery("select u from ProductoEntity u", ProductoEntity.class);
        return query.getResultList();   
    }
    
    public ProductoEntity findByName(String name) {
    LOGGER.log(Level.INFO, "Consultando producto por nombre ", name);


    // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
    TypedQuery query = em.createQuery("Select e From ProductoEntity e where e.name = :name", ProductoEntity.class);
    // Se remplaza el placeholder ":name" con el valor del argumento
    query = query.setParameter("name", name);
    // Se invoca el query se obtiene la lista resultado
    List<ProductoEntity> sameName = query.getResultList();
    if (sameName.isEmpty()) {
        return null;
    } else {
        return sameName.get(0);
    }
}
}