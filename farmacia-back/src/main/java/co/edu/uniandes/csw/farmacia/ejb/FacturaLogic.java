/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.FacturaPersistence;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import java.util.ArrayList;
/**
 *
 * @author hs.hernandez
 */
@Stateless
public class FacturaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());

    @Inject
    private FacturaPersistence persistence;
    
    @Inject
    private ClienteLogic clientelogic;
    
    @Inject
    private OrdenDeCompraLogic ordenlogic;
    
    /**
     * Lista de factura de un cliente
     * @param id del cliente
     * @return Lista de facturas
     * @throws BusinessLogicException  no existe el cliente
     */
   public List<FacturaEntity> getFacturas(Long id) throws BusinessLogicException{
       LOGGER.info("Inicia proceso de consultar todas las facturas");
       ClienteEntity cliente = clientelogic.getCliente(id);
       if(cliente.getFacturas() == null || cliente.getFacturas().isEmpty()) throw new BusinessLogicException("El cliente no tiene facturas");
       return cliente.getFacturas();
   }
   
   /**
    * Factura de la orden de compra
    * @param id
    * @return
    * @throws BusinessLogicException 
    */
   public FacturaEntity getFacturaOrden(Long id) throws BusinessLogicException{
       OrdenDeCompraEntity en = ordenlogic.getOrdenDeCompraById(id);
       if(en.getFactura() == null) throw new BusinessLogicException("No hay una factura asociada");
       return en.getFactura();
   }
   
   /**
    * 
    * @param idOrden
    * @param entity
    * @return
    * @throws BusinessLogicException 
    */
   public FacturaEntity createFacturaOrden(Long idOrden, FacturaEntity entity)throws BusinessLogicException{
       OrdenDeCompraEntity en = ordenlogic.getOrdenDeCompraById(idOrden);
       entity.setOrden(en);
       return persistence.create(entity);
   }
   
   /**
    * Crea una nueva factura
    * @param idCliente 
    * @param entity 
    * @return entidad cread
    * @throws BusinessLogicException 
    */
   public FacturaEntity createFactura(Long idCliente, FacturaEntity entity) throws BusinessLogicException{
      
       LOGGER.info("Inicia proceso de crear una factura");
       ClienteEntity cliente = clientelogic.getCliente(idCliente);
       entity.setClientes(cliente);
       return persistence.create(entity);
   }
   
   /**
    * Actualiza la informacion de una factura
    * @param id del cliente
    * @param entity
    * @return la factura actualizada
    * 
    */
   public FacturaEntity updateFactura(Long idCliente , FacturaEntity entity)throws BusinessLogicException{
        
        LOGGER.info("Inicia proceso de actualizar una factura");
        ClienteEntity cliente = clientelogic.getCliente(idCliente);
        entity.setClientes(cliente);
        return persistence.update(entity);
   }
   
   /**
    * Factura especifica asosicada a un cliente especifico
    * @param idCliente
    * @param idFactura
    * @return factura asociada
    */
   public FacturaEntity getFacturas(Long idCliente, Long idFactura){
       return persistence.find(idCliente, idFactura);
   }
   
   /**
    * Elimina una factura de un cliente dado
    * @param idCliente
    * @param idFactura
    */
   public void deleteFactura(Long idCliente, Long idFactura){
       LOGGER.info("Inicia proceso de borrar una factura");
       FacturaEntity old = getFacturas(idCliente, idFactura);
       persistence.delete(old.getId());
       
   }
}
