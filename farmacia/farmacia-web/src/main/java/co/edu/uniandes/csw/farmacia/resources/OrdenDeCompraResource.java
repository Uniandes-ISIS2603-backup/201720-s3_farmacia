/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.OrdenDeCompraDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.OrdenDeCompraLogic;
import co.edu.uniandes.csw.farmacia.entities.OrdenDeCompraEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *clase que representa el recurso de orden de compra, usando REST
 * @author a.gracia10
 */
@Path("OrdenDeCompra")
@Produces("application/json")
@Stateless
public class OrdenDeCompraResource {
    
    @Inject
    OrdenDeCompraLogic logic;
    
    @POST
    public OrdenDeCompraDetailDTO createOrdenDeCompra(OrdenDeCompraDetailDTO dto)
    {
        OrdenDeCompraEntity ent = dto.toEntity();
        OrdenDeCompraEntity nuevoent = logic.createOrdenDeCompra(ent);
        return new OrdenDeCompraDetailDTO(nuevoent);
    }
    
    @GET
    public List<OrdenDeCompraDetailDTO> getAll()
    {
        System.out.println("este picherio se llama :)");
        List<OrdenDeCompraEntity> data = logic.getAllOrdenDeCompra();
        List<OrdenDeCompraDetailDTO> resp = new ArrayList<> ();
        for(OrdenDeCompraEntity ent:data)
        {
            resp.add(new OrdenDeCompraDetailDTO(ent));
        }
        return resp;
    }
    
    @GET
    @Path("{id}")
    public OrdenDeCompraDetailDTO getOrdenDeCompraByID(@PathParam("id") long id)
    {
        return new OrdenDeCompraDetailDTO(logic.getOrdenDeCompraById(id));
    }
    
    @PUT
    @Path("{id}")
    public OrdenDeCompraDetailDTO updateOrdenDeCompra(@PathParam("id") long id, OrdenDeCompraDetailDTO dto)
    {
        dto.setId(id);
        //miro que si exista una orden con ese id
        OrdenDeCompraEntity ent = logic.getOrdenDeCompraById(id);
        if(ent == null)
        {
            throw new WebApplicationException("no existe una orden con el id dado", 404);
        }
        
        return new OrdenDeCompraDetailDTO(logic.updateOrdenDeCompra(dto.toEntity()));
    }
    
    @DELETE
    @Path("{id}")
    public void deleteOrdenDeCompra(@PathParam("id") long id)
    {
        OrdenDeCompraEntity ent = logic.getOrdenDeCompraById(id);
        if(ent == null)
        {
            throw new WebApplicationException("no existe una orden con el id dado", 404);
        }
        
        logic.DeleteOrdenDeCompra(ent);
    }
}
