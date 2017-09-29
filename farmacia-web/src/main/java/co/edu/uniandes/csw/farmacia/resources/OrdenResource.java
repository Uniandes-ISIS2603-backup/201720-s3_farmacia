/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.OrdenDTO;
import co.edu.uniandes.csw.farmacia.dtos.OrdenDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.OrdenLogic;
import co.edu.uniandes.csw.farmacia.entities.OrdenEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author jp.carreno
 */
@Path("ordenes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenResource {
    
    @Inject
    OrdenLogic ordenL;
    
    @GET
    public List<OrdenDetailDTO> getOrdenes()throws BusinessLogicException
    {
        return listOrdEntToDetDTO(ordenL.getOrdenes());
    }

    @GET
    @Path("(id: \\d+}")
    public OrdenDetailDTO getOrden(@PathParam("id") long id) throws BusinessLogicException
    {
        OrdenEntity e = ordenL.getOrden(id);
        if(e == null)
        {
            throw new WebApplicationException("El recurso /ordenes/" + id + "no existe", 404);
            
        }
        return new OrdenDetailDTO(e);
    }
    
    @POST
    public OrdenDTO createOrden(OrdenDTO orden)throws BusinessLogicException
    {
        return new OrdenDTO(ordenL.createOrden(orden.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public OrdenDetailDTO updateCliente(@PathParam("id") Long id , OrdenDetailDTO orden) throws BusinessLogicException
    {
        orden.setId(id);
        OrdenEntity e = ordenL.getOrden(id);
        if(e == null)    throw new WebApplicationException("El recurso /orden/" + id + "no existe",404);
        return new OrdenDetailDTO(ordenL.updateOrden(id, orden.toEntity()));
     }
     
     @DELETE
     @Path("{id: \\d+}")
     public void deleteOrden(@PathParam("id") Long id) throws BusinessLogicException{
         OrdenEntity enti = ordenL.getOrden(id);
         if(enti == null) throw new WebApplicationException("El recurso /cliente/" + id + "o existe",404);
         ordenL.deleteOrden(id);
     }
    
    
    private List<OrdenDetailDTO> listOrdEntToDetDTO(List<OrdenEntity> entityList)
    {
         List<OrdenDetailDTO> list = new ArrayList<>();
         for(OrdenEntity e : entityList)
             list.add(new OrdenDetailDTO(e));
         return list;
     }
}
