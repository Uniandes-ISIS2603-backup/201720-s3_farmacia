/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.PagoEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.PagoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.carreno
 */
@Stateless
public class PagoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence;
    
    /**
     * Lista de pagos
     * @return lista de pagos
     */
    public List<PagoEntity> getPagos(){
        LOGGER.info("Inicia proceso de consultar todos los pagos");
        List<PagoEntity> pagos = persistence.findAll();
        return pagos;
    }
    
    /**
     * Pago con ID dado
     * @param id del pago
     * @return Pago
     * @throws BusinessLogicException no existe el pago con el id dado
     */
    public PagoEntity getPago (Long id){
         LOGGER.log(Level.INFO, "Inicia proceso de consultar pago con id={0}", id);
        if (id == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} nulo", id);
        }
        PagoEntity pago = persistence.find(id);
        
        if (pago == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar pago con id={0}", id);
        return pago;
    }
    
    /**
     * Crea un pago nuevo
     * @param entity del ago
     * @return entidad cread
     */
    public PagoEntity createPago(PagoEntity entity){
        
        LOGGER.info("Inicia proceso de creación de pago en la clase PagoLogic");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de pago");
        return entity;
        
    }
        
    
    /**
     * Actualiza la informacion del pago con el ID dado
     * @param id del pago
     * @return datos actualizados del pago
     * @throws BusinessLogicException no existe el pago con el ID dado 
     */
    public PagoEntity updatePago(Long id, PagoEntity pago)throws BusinessLogicException{
    
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un pago con id={0}", id);
        PagoEntity newEntity = persistence.update(pago);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pago con id={0}", pago.getId());
        return newEntity;
        
    }
    /**
     * Elimina el pago con el id dado
     * @param id del pago que se desea eliminar
     */
    public void deletePago(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un pago con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un pago con id={0}", id);
    }
    
}
