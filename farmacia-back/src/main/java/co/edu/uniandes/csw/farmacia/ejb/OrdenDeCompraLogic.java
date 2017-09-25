/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.ejb;

import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;
import co.edu.uniandes.csw.farmacia.persistence.OrdenDeCompraPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *clase que representa la logica para el recurso de Orden De Compra
 * @author a.gracia10
 */
@Stateless
public class OrdenDeCompraLogic {
    
    @Inject
    private OrdenDeCompraPersistence persistence;
    
    /**
     * metodo para persistir una entidad
     * @param ent la entidad que se quiere persistir
     * @return la entidad persistida 
     */
    public OrdenDeCompraEntity createOrdenDeCompra(OrdenDeCompraEntity ent)
    {
       return persistence.create(ent); //la verdad, no me interesa si tienen el mismo nombre o no
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
    public OrdenDeCompraEntity getOrdenDeCompraById(long id)
    {
        return persistence.find(id);
    }
    
    /**
     * Metodo que actualiza una orden que ya existe
     * @param entity entidad con el mismo id pero datos cambiados 
     * @return la entidad actualizada
     */
   public OrdenDeCompraEntity updateOrdenDeCompra (OrdenDeCompraEntity entity)//, long id )
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
