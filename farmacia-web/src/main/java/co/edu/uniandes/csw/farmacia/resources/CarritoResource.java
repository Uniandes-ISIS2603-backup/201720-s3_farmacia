/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.CarritoDTO;
import co.edu.uniandes.csw.farmacia.dtos.CarritoDetailDTO;
import co.edu.uniandes.csw.farmacia.ejb.CarritoLogic;
import co.edu.uniandes.csw.farmacia.entities.CarritoEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
@Path("carrito")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoResource {
    
    @Inject
    private CarritoLogic carritolgic;
    
    @GET
    public List<CarritoDetailDTO> getCarritos()throws BusinessLogicException{
        return listCCarritoentity2DetailDTO(carritolgic.getCarrito());
    }
    
    @POST
    public CarritoDetailDTO createCliente(CarritoDetailDTO carrito) throws BusinessLogicException{
         return new CarritoDetailDTO(carritolgic.createCarrito(carrito.toEntity()));
    }
    
     @DELETE
     @Path("{id: \\d+}")
     public void deleteCarrito(@PathParam("id") Long id) throws BusinessLogicException{
         CarritoEntity enti = carritolgic.getCarrito(id);
         if(enti == null) throw new WebApplicationException("El recurso Carrito " + id + " no existe",404);
         carritolgic.deleteCarrito(id);
    }
     
    @PUT
    @Path("{id: \\d+}")
    public CarritoDetailDTO añadirProducto(@PathParam("id") Long id, CarritoDetailDTO carrito){
        return new CarritoDetailDTO(carritolgic.añadirProducto(id, carrito.toEntity()));
    }
     
    
    private List<CarritoDetailDTO> listCCarritoentity2DetailDTO(List<CarritoEntity> entityLis){
         List<CarritoDetailDTO> list = new ArrayList<>();
         for(CarritoEntity en : entityLis)
             list.add(new CarritoDetailDTO(en));
         return list;
     }
     
}
