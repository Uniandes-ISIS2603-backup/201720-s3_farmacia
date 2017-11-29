/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.PagoDTO;
import co.edu.uniandes.csw.farmacia.dtos.PagoDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.PagoLogic;
import co.edu.uniandes.csw.farmacia.entities.CarritoEntity;
import co.edu.uniandes.csw.farmacia.entities.PagoEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.CarritoPersistence;
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
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {
    
     @Inject
     PagoLogic pagoLogic;
     
     @Inject
     CarritoPersistence carritoPersistence;
     
     @GET
     public List<PagoDetailDTO> getPagos()throws BusinessLogicException{
         return listPagoentity2DetailDTO(pagoLogic.getPagos());
     }
     
     @GET
     @Path("{id: \\d+}")
     public PagoDetailDTO getPago(@PathParam("id") long id) throws BusinessLogicException{
         PagoEntity en = pagoLogic.getPago(id);
         if(en == null) {
             throw new WebApplicationException("El recurso /pagos/ " + id + "no existe", 404);
         }
         //revisar si DETAILDTO o solo DTO por el tema de las facturas
        return  new PagoDetailDTO(en);
     }
     
     @POST
     public PagoDetailDTO createPago(PagoDetailDTO pago) throws BusinessLogicException{
         return new PagoDetailDTO(pagoLogic.createPago(pago.toEntity()));
     }
     
     @PUT
     @Path("{id: \\d+}")
     public PagoDetailDTO updatePago(@PathParam("id") Long id , PagoDetailDTO pago) throws BusinessLogicException{
        pago.setId(id);
        PagoEntity enti = pagoLogic.getPago(id);
        if(enti == null)    throw new WebApplicationException("El recurso /pagos/" + id + "no existe",404);
        return new PagoDetailDTO(pagoLogic.updatePago(id, pago.toEntity()));
     }
     
     @DELETE
     @Path("{id: \\d+}")
     public void deletePago(@PathParam("id") Long id) throws BusinessLogicException{
         PagoEntity enti = pagoLogic.getPago(id);
         if(enti == null) throw new WebApplicationException("El recurso pago " + id + " no existe",404);
         pagoLogic.deletePago(id);
     }
    
     private List<PagoDetailDTO> listPagoentity2DetailDTO(List<PagoEntity> entityLis){
         List<PagoDetailDTO> list = new ArrayList<>();
         for(PagoEntity en : entityLis)
             list.add(new PagoDetailDTO(en));
         return list;
     }
     
    @GET
    @Path("dar/{id: \\d+}")
    public PagoDTO darPago(@PathParam("idCarrito") Long idCarrito) {
        CarritoEntity carrito = carritoPersistence.find(idCarrito);
        PagoEntity pago = carrito.getPago();

        //Si el pago no existe, lo crea
        if (pago == null) {
            pago = new PagoEntity();
            
            double costo = carrito.getTotalCarrito();
            pago.setCosto(costo);
            pago.setIdUsuario(carrito.getIdCliente());

            pago = crearPago(carrito, pago);
        }

        return new PagoDTO(pago);
    }
    
    private PagoEntity crearPago(CarritoEntity carrito, PagoEntity pago) {
        
        pago.setCarrito(carrito);

        pago = pagoLogic.createPago(pago);

        carrito.setPago(pago);
        
        carritoPersistence.update(carrito);
        
        return pago;
    }
}
