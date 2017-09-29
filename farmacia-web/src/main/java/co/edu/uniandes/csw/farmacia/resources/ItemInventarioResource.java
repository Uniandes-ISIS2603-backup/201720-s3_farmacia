/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.ItemInventarioDTO;
import co.edu.uniandes.csw.farmacia.ejb.ItemInventarioLogic;
import co.edu.uniandes.csw.farmacia.entities.ItemInventarioEntity;
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
 @Stateless
 @Path("ItemInventario")
 @Produces("applications/json")
public class ItemInventarioResource {
    
     @Inject
     ItemInventarioLogic logic;
     
     @POST
     public ItemInventarioDTO create (ItemInventarioDTO dto)
     {
         ItemInventarioEntity ent = dto.toEntity();
         ItemInventarioEntity ent2 = logic.create(ent);
         return new ItemInventarioDTO(ent2);
     }
     
     @GET
     public List<ItemInventarioDTO> getAll()
     {
        List<ItemInventarioEntity> data = logic.findAll();
        List<ItemInventarioDTO> resp = new ArrayList<> ();
        for(ItemInventarioEntity ent:data)
        {
            resp.add(new ItemInventarioDTO(ent));
        }
        return resp;
     }
     
     @GET
     @Path("{id}")
     public ItemInventarioDTO getById(@PathParam("id") long id)
     {
         return new ItemInventarioDTO (logic.findItemById(id));
     }
     
     @PUT
     @Path("{id}")
     public ItemInventarioDTO update (@PathParam("id") long id, ItemInventarioDTO dto)
     {
        dto.setId(id);
        ItemInventarioEntity ent = logic.findItemById(id);
        if(ent == null)
        {
            throw new WebApplicationException("no existe un item con el id dado", 404);
        }
        
        return new ItemInventarioDTO(logic.update(dto.toEntity()));
     }
     
     @DELETE
     @Path("{id}")
     public void delete (@PathParam("id") long id)
     {
         ItemInventarioEntity ent = logic.findItemById(id);
         if(ent == null)
         {
             throw new WebApplicationException("no existe un item con el id dado", 404);
         }
         logic.delete(id);
     }
}
