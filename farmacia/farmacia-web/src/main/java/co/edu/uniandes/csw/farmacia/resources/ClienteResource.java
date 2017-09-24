/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.ClienteDTO;
import co.edu.uniandes.csw.farmacia.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.ClienteLogic;
import co.edu.uniandes.csw.farmacia.entities.ClienteEntity;
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
 * @author hs.hernandez
 */
@Path("cliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {
    
     @Inject
     ClienteLogic clientelogic;
     
     
     @GET
     public List<ClienteDetailDTO> getClientes()throws BusinessLogicException{
         return listClienteentity2DetailDTO(clientelogic.getClientes());
     }
     
     @GET
     @Path("{id: \\d+}")
     public ClienteDetailDTO getCliente(@PathParam("id") long id) throws BusinessLogicException{
         ClienteEntity en = clientelogic.getCliente(id);
         if(en == null) {
             throw new WebApplicationException("El recurso /clientes/" + id + "no existe", 404);
         }
        return  new ClienteDetailDTO(en);
     }
     
     @POST
     public ClienteDetailDTO createCliente(ClienteDTO cliente) throws BusinessLogicException{
         return new ClienteDetailDTO(clientelogic.createCliente(cliente.toEntity()));
     }
     
     @PUT
     @Path("{id: \\d+}")
     public ClienteDetailDTO updateCliente(@PathParam("id") Long id , ClienteDetailDTO cliente) throws BusinessLogicException{
        cliente.setId(id);
        ClienteEntity enti = clientelogic.getCliente(id);
        if(enti == null)    throw new WebApplicationException("El recurso /cliente/" + id + "o existe",404);
        return new ClienteDetailDTO(clientelogic.updateCliente(id, cliente.toEntity()));
     }
     
     @DELETE
     @Path("{id: \\d+}")
     public void deleteCliente(@PathParam("id") Long id) throws BusinessLogicException{
         ClienteEntity enti = clientelogic.getCliente(id);
         if(enti == null) throw new WebApplicationException("El recurso /cliente/" + id + "o existe",404);
         clientelogic.deleteBook(id);
     }
     
     @Path("{idCliente: \\d+}/facturas")
    public Class<FacturaResource> getFacturaResource(@PathParam("idCliente") Long clienteID) throws BusinessLogicException{
        ClienteEntity entity = clientelogic.getCliente(clienteID);
        if (entity == null) {
            throw new WebApplicationException("El recurso /cliente/" + clienteID + "/facturas no existe.", 404);
        }
        return FacturaResource.class;
    }
    
     private List<ClienteDetailDTO> listClienteentity2DetailDTO(List<ClienteEntity> entityLis){
         List<ClienteDetailDTO> list = new ArrayList<>();
         for(ClienteEntity en : entityLis)
             list.add(new ClienteDetailDTO(en));
         return list;
     }
     
}
