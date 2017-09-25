/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.OrdenDeRotacionDeInventarioDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.OrdenDeRotacionDeInventarioLogic;
import co.edu.uniandes.csw.farmacia.entities.OrdenDeRotacionDeInventarioEntity;
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
 *
 * @author a.gracia10
 */
@Path("OrdenDeRotacionDeInventario")
@Produces("application/json")
@Stateless
public class OrdenDeRotacionDeInventarioResource {
    
    @Inject
    OrdenDeRotacionDeInventarioLogic logic;
    
    @POST
    public OrdenDeRotacionDeInventarioDetailDTO createOrdenDeRotacionDeInventario(OrdenDeRotacionDeInventarioDetailDTO dto)
    {
        OrdenDeRotacionDeInventarioEntity ent = dto.toEntity();
        OrdenDeRotacionDeInventarioEntity nuevoent = logic.createOrdenDeRotacionDeInventario(ent);
        return new OrdenDeRotacionDeInventarioDetailDTO(nuevoent);
    }
    
    @GET
    public List<OrdenDeRotacionDeInventarioDetailDTO> getAll()
    {
        System.out.println("este picherio se llama :)");
        List<OrdenDeRotacionDeInventarioEntity> data = logic.getAllOrdenDeRotacionDeInventario();
        List<OrdenDeRotacionDeInventarioDetailDTO> resp = new ArrayList<> ();
        for(OrdenDeRotacionDeInventarioEntity ent:data)
        {
            resp.add(new OrdenDeRotacionDeInventarioDetailDTO(ent));
        }
        return resp;
    }
    
    @GET
    @Path("{id}")
    public OrdenDeRotacionDeInventarioDetailDTO getOrdenDeCompraByID(@PathParam("id") long id)
    {
        return new OrdenDeRotacionDeInventarioDetailDTO(logic.getOrdenDeRotacionDeInventarioById(id));
    }
    
    @PUT
    @Path("{id}")
    //NO ESTA FUNCIONANDO BIEN, DA FUQ
    public OrdenDeRotacionDeInventarioDetailDTO updateOrdenDeCompra(@PathParam("id") long id, OrdenDeRotacionDeInventarioDetailDTO dto)
    {
        dto.setId(id);
        //miro que si exista una orden con ese id
        OrdenDeRotacionDeInventarioEntity ent = logic.getOrdenDeRotacionDeInventarioById(id);
        if(ent == null)
        {
            throw new WebApplicationException("no existe una orden con el id dado", 404);
        }
        
        return new OrdenDeRotacionDeInventarioDetailDTO(logic.updateOrdenDeRotacionDeInventario(ent));
    }
    
    @DELETE
    @Path("{id}")
    public void deleteOrdenDeCompra(@PathParam("id") long id)
    {
        OrdenDeRotacionDeInventarioEntity ent = logic.getOrdenDeRotacionDeInventarioById(id);
        if(ent == null)
        {
            throw new WebApplicationException("no existe una orden con el id dado", 404);
        }
        
        logic.DeleteOrdenDeRotacionDeInventario(ent);
    }
    
}
