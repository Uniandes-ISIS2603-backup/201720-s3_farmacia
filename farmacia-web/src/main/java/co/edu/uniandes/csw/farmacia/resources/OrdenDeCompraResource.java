/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.OrdenDeCompraDTO;
import co.edu.uniandes.csw.farmacia.ejb.OrdenDeCompraLogic;
import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *clase que representa el recurso de orden de compra, usando REST
 * @author hs.hernandez
 */
@Path("/ordenesCompra")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenDeCompraResource {
    
    @Inject
    OrdenDeCompraLogic logic;
   
    /**
     * Metodo auxiliar
     * @param entityLis
     * @return 
     */
    private List<OrdenDeCompraDTO> listOrdenEntity2DTO(List<OrdenDeCompraEntity> entityLis){
         List<OrdenDeCompraDTO> list = new ArrayList<>();
         for(OrdenDeCompraEntity en : entityLis)
             list.add(new OrdenDeCompraDTO(en));
         return list;
    }
     
    @GET
    public List<OrdenDeCompraDTO> getOrdenes()throws BusinessLogicException{
        return listOrdenEntity2DTO(logic.getAllOrdenDeCompra());
    }
    
    @GET
    @Path("{idOrden: \\d+}")
    public OrdenDeCompraDTO getOrden(@PathParam("idOrden") Long idOrden) throws BusinessLogicException {
        OrdenDeCompraEntity entity = logic.getOrdenDeCompraById(idOrden);
        if (entity == null)
            throw new WebApplicationException("La orden de compra identificada con id " + idOrden +" no existe", 404);
        return new OrdenDeCompraDTO(entity);
    }
    
    @POST
    public OrdenDeCompraDTO createFactura(OrdenDeCompraDTO ent)throws BusinessLogicException{
       return new OrdenDeCompraDTO(logic.createOrdenDeCompra(ent.toEntity()));
    }
   
}
