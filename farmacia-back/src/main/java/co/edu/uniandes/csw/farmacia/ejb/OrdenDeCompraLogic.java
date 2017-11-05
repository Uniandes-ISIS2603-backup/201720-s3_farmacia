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
import co.edu.uniandes.csw.farmacia.persistence.OrdenDeCompraPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *clase que representa la logica para el recurso de Orden De Compra
 * @author hs.hernandez
 */
@Stateless
public class OrdenDeCompraLogic {
    
    @Inject
    private OrdenDeCompraPersistence persistence;
    
    @Inject
    private ClienteLogic clientelogic;
    
    @Inject
    private FacturaLogic facturalogic;
    
    
    
    /**
     * metodo para persistir una entidad
     * @param ent la entidad que se quiere persistir
     * @return la entidad persistida 
     */
    public OrdenDeCompraEntity createOrdenDeCompra(OrdenDeCompraEntity ent, Long idCliente) throws BusinessLogicException{
      
        //Creo la factura asociada a la orden de compra con el cliente
        FacturaEntity entFactura  = new FacturaEntity();
        entFactura.setTotalFactura(ent.getCostoTotal());
        entFactura.setFecha(ent.getFecha());
        facturalogic.createFactura(idCliente, entFactura);
        //Creo la orden de compra segun el cliente
        ClienteEntity cliente = clientelogic.getCliente(idCliente);
        ent.setCliente(cliente);
        
        return persistence.create(ent); 
    }
    
    /**
     * metodo que devuelve todas las ordenes persistidas.
     * @return una lista de las entidades persistidas
     */
    public List<OrdenDeCompraEntity> getAllOrdenDeCompra()
    {
        return persistence.findAll(); 
    }
    
    /**
     * metodo que devuelve, dado un id, el metodo con ese id
     * @param id el id de la orden que se pide
     * @return la entidad con el id dado
     */
    public OrdenDeCompraEntity getOrdenDeCompraByIdCliente(Long idCliente ,Long id)
    {
        return persistence.findByClient(idCliente, id);
    }
    
    /**
     * metodo que devuelve, dado un id, el metodo con ese id
     * @param id el id de la orden que se pide
     * @return la entidad con el id dado
     */
    public OrdenDeCompraEntity getOrdenDeCompraById(Long id)
    {
        return persistence.find(id);
    }
    
    /**
     * Metodo que actualiza una orden que ya existe
     * @param entity entidad con el mismo id pero datos cambiados 
     * @return la entidad actualizada
     */
   public OrdenDeCompraEntity updateOrdenDeCompra (OrdenDeCompraEntity entity)
   {
       return persistence.update(entity);
   }
   
   /**
    * metodo que borra una entidad de la base de datos
    * @param ent entidad que se quiere borrar
    */
   public void DeleteOrdenDeCompra(OrdenDeCompraEntity ent)
   {
       persistence.delete(ent.getId());
   }
}
