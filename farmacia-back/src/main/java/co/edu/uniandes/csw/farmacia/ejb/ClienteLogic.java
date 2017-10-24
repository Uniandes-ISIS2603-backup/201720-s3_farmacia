/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author hs.hernandez
 */
@Stateless
public class ClienteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private ClientePersistence persistence;
    
    /**
     * Lista de clientes
     * @return lista de clientes
     */
    public List<ClienteEntity> getClientes(){
        LOGGER.info("Inicia proceso de consultar todos los clientes");
        List<ClienteEntity> clientes = persistence.findAll();
        return clientes;
    }
    
    /**
     * Cliente con ID dado
     * @param id del cliente
     * @return Cliente
     * @throws BusinessLogicException no existe el cliente con el id dado
     */
    public ClienteEntity getCliente (Long id){
         LOGGER.log(Level.INFO, "Inicia proceso de consultar cliente con id={0}", id);
        ClienteEntity cliente = persistence.find(id);
        if (cliente == null) {
            LOGGER.log(Level.SEVERE, "El cliente con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar cliente con id={0}", id);
        return cliente;
    }
    
    /**
     * Crea un cliente nuevo
     * @param entity del cliente
     * @return entidad cread
     * @throws BusinessLogicException ya existe un cliente con el id dado
     */
    public ClienteEntity createCliente(ClienteEntity entity)throws BusinessLogicException{
        verificarEdad(entity);
        LOGGER.info("Inicia proceso de creación de cliente en la clase ClienteLogic");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de cliente");
        return entity;
        
    }
        
    
    /**
     * Actualiza la informacion del cliente con el ID dado
     * @param id del cliente
     * @return datos actualizados del cliente
     * @throws BusinessLogicException no existe el cliente con el ID dado 
     */
    public ClienteEntity updateCliente(Long id, ClienteEntity cliente)throws BusinessLogicException{
        verificarEdad(cliente);
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un cliente con id={0}", id);
        ClienteEntity newEntity = persistence.update(cliente);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id={0}", cliente.getId());
        return newEntity;
        
    }
    /**
     * Elimina el cliente con el id dado
     * @param id del cliente que se desea eliminar
     */
    public void deleteBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un cliente con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un cliente con id={0}", id);
    }
    
    private void verificarEdad(ClienteEntity entity)throws BusinessLogicException{
        if(entity.getEdad() < 0 || entity.getEdad() > 200) throw new BusinessLogicException("Edad no valida, debe ser mayor a 0 y menor a 200");
        if(entity.getCedula() <= 0) throw new BusinessLogicException("Ingrese una celula valida");
    }
}
