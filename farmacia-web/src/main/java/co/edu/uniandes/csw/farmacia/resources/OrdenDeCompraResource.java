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
import javax.ejb.Stateless;
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
@Stateless
public class OrdenDeCompraResource {
    
    @Inject
    OrdenDeCompraLogic logic;
   
    @GET
    @Path("{idCliente: \\d+}")
    public OrdenDeCompraDTO getFactura(@PathParam("idCliente") Long idCliente) throws BusinessLogicException {
        OrdenDeCompraEntity entity = logic.getOrdenDeCompraById(idCliente);
        if (entity == null) {
            throw new WebApplicationException("El cliente identificado con id " + idCliente +" no tiene ordenes de compra asociadas", 404);
        }
        return new OrdenDeCompraDTO(entity);
    }
    
    @POST
    @Path("{id: \\d+}")
    public OrdenDeCompraDTO createFactura(@PathParam("id") Long id, OrdenDeCompraDTO facura)throws BusinessLogicException{
        return new OrdenDeCompraDTO(logic.createOrdenDeCompra(facura.toEntity()));
    }
}
