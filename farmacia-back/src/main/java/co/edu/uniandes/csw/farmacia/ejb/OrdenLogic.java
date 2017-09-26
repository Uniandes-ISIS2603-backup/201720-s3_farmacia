/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.OrdenEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ClientePersistence;
import co.edu.uniandes.csw.farmacia.persistence.OrdenPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jp.carreno
 */
public class OrdenLogic 
{
    
    private static final Logger LOGGER = Logger.getLogger(OrdenLogic.class.getName());
    
    @Inject
    private OrdenPersistence persistence;
    
    /**
     * Lista de Ordenes
     * @return lista de ordenes
     */
    public List<OrdenEntity> getOrdenes()
    {
        LOGGER.info("Inicia el proceso de consultar todas las ordenes");
        List<OrdenEntity> ordenes = persistence.findAll();
        return ordenes;
    }
    
    /**
     * Orden con ID dado
     * @param id de la orden
     * @return Orden
     * @throws BusinessLogicException no existe la orden con el id dado
     */
    public OrdenEntity getOrden(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar la orden con id=(0)", id);
        OrdenEntity orden = persistence.find(id);
        if(orden == null)
        {
            LOGGER.log(Level.SEVERE, "La orden con id (0) no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina el proceso de consultar clienten con id=(0)", id);
        return orden;
    }
    
    /**
     * Crea una orden nueva
     * @param entity de la orden
     * @return entidad creada
     * @throws BusinessLogicException ya existe un cliente con el id dado
     */
    public OrdenEntity createOrden(OrdenEntity entity)
    {
        LOGGER.info("Inicia el proceso de creaciòn de la orden en la clase OrdenLogic");
        persistence.create(entity);
        LOGGER.info("Termina el proceso de creaciòn de la orden");
        return entity;
    }
    
    /**
     * Actualiza la informaciòn de la orden con el ID dado
     * @param id de la orden
     * @param orden Orden con la nueva informacion
     * @return datos actuallizados de la orden
     * @throws BusinessLogicException no existe la orden con el ID dado
     */
    public OrdenEntity updateOrden(Long id, OrdenEntity orden)throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de actualizar la orden con id=(0)", id);
        OrdenEntity nEntity = persistence.update(orden);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizar la orden con id=(0)", orden.getId());
        return nEntity;
    }
    
    public void deleteOrden(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de borrar una orden con id=(0)", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar una orden con id=(0)", id);
    }
    
    
    
    
}
