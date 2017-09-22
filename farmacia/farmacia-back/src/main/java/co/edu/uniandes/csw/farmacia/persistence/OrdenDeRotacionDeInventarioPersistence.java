/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.persistence;

import co.edu.uniandes.csw.farmacia.entities.OrdenDeRotacionDeInventarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author a.gracia10
 */
@Stateless
public class OrdenDeRotacionDeInventarioPersistence  {
    private static final Logger LOGGER = Logger.getLogger(OrdenDeRotacionDeInventarioPersistence.class.getName());

    @PersistenceContext(unitName = "farmaciaPU")
    protected EntityManager em;

    public OrdenDeRotacionDeInventarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Orden De Rotacion De Inventario con id={0}", id);
        return em.find(OrdenDeRotacionDeInventarioEntity.class, id);
    }

    public OrdenDeRotacionDeInventarioEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Orden De Rotacion De Inventario con name= ", name);
        TypedQuery<OrdenDeRotacionDeInventarioEntity> q
                = em.createQuery("select u from OrdenDeRotacionDeInventarioEntity u where u.name = :name", OrdenDeRotacionDeInventarioEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<OrdenDeRotacionDeInventarioEntity> findAll() {
        LOGGER.info("Consultando todas las Ordenes De Rotacion De Inventario");
        Query q = em.createQuery("select u from OrdenDeRotacionDeInventarioEntity u");
        return q.getResultList();
    }

    public OrdenDeRotacionDeInventarioEntity create(OrdenDeRotacionDeInventarioEntity entity) {
        LOGGER.info("Creando una OrdenDeRotacionDeInventario nuevo");
        em.persist(entity);
        LOGGER.info("OrdenDeRotacionDeInventario creado");
        return entity;
    }

    public OrdenDeRotacionDeInventarioEntity update(OrdenDeRotacionDeInventarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando employee con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando employee con id={0}", id);
        OrdenDeRotacionDeInventarioEntity entity = em.find(OrdenDeRotacionDeInventarioEntity.class, id);
        em.remove(entity);
    }
}