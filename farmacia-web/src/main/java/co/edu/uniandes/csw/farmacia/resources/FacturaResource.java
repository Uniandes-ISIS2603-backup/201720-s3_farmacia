/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.FacturaDTO;
import co.edu.uniandes.csw.farmacia.ejb.FacturaLogic;
import co.edu.uniandes.csw.farmacia.entities.FacturaEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
 * @author hs.hernandez
 */

@Produces("application/json")
@Consumes("application/json")
public class FacturaResource {
    
    @Inject
    FacturaLogic facturalogic;
    
    /**
     * Get desde cliente
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    public List<FacturaDTO> getFacturas(@PathParam("idCliente") Long id)throws BusinessLogicException{
        return listEntity2DTO(facturalogic.getFacturas(id));
    }
    
    @GET
    @Path("{id: \\d+}")
    public FacturaDTO getFactura(@PathParam("idCliente") Long idCliente, @PathParam("id") Long id) throws BusinessLogicException {
        FacturaEntity entity = facturalogic.getFacturas(idCliente, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /factura/" + idCliente + "/factura/" + id + " no existe.", 404);
        }
        return new FacturaDTO(entity);
    }
    
    
    
    /**
     * Crea la factura de un cliente
     * @param id
     * @param facura
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public FacturaDTO createFactura(@PathParam("idCliente") Long id, FacturaDTO facura)throws BusinessLogicException{
        return new FacturaDTO(facturalogic.createFactura(id, facura.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public FacturaDTO updateFactura(@PathParam("idCliente") Long idCliente, @PathParam("id") Long id, FacturaDTO factura)throws BusinessLogicException{
        factura.setId(id);
        FacturaEntity entity = facturalogic.getFacturas(idCliente, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /clientes/" + idCliente + "/facturas/" + id + " no existe.", 404);
        }
        return new FacturaDTO(facturalogic.updateFactura(idCliente, factura.toEntity()));
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFactura(@PathParam("idCliente") Long idCliente, @PathParam("id") Long id) throws BusinessLogicException {
        FacturaEntity entity = facturalogic.getFacturas(idCliente, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /clientes/" + idCliente + "/facturas/" + id + " no existe.", 404);
        }
        facturalogic.deleteFactura(idCliente, id);
    }
    
    private List<FacturaDTO> listEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDTO> list = new ArrayList<>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDTO(entity));
        }
        return list;
    }
}
