package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.MultimediaDTO;
import co.edu.uniandes.csw.farmacia.ejb.MultimediaLogic;
import co.edu.uniandes.csw.farmacia.entities.MultimediaEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
 * Clase que implementa el recurso REST correspondiente a "Multimedias".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Multimedias". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Multimedias"
 *
 * @author lm.gonzalezf
 *
 */
@Path("Productos/{ProductosId: \\d+}/Multimedias")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class MultimediaResource {

    @Inject
    MultimediaLogic MultimediasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(MultimediaResource.class.getName());
    
    
    
    /**
     * GET http://localhost:8080/Farmacia-web/api/Productos/1/Multimedias
     * @param idProducto
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    public List<MultimediaDTO> getMultimedia(@PathParam("idProducto") Long idProducto) throws BusinessLogicException {
        return listEntity2DTO(MultimediasLogic.getMultimedia(idProducto));
    }

    
    /**
     * GET http://localhost:8080/Farmacia-web/api/Productos/1/Multimedias/1
     * @param idProducto
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{id: \\d+}")
    public MultimediaDTO getMultimedia(@PathParam("idProducto") Long idProducto, @PathParam("id") Long id) throws BusinessLogicException {
        MultimediaEntity entity = MultimediasLogic.getMultimedia(idProducto, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + idProducto + "/multimedias/" + id + " no existe.", 404);
        }
        return new MultimediaDTO(entity);
    }

    /**
     * 
     * POST http://localhost:8080/Farmacia-web/api/Productos/1/Multimedias
     * @param idProducto
     * @param multimedia
     * @return 
     * @throws co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException
     */
    @POST
    public MultimediaDTO createMultimedia(@PathParam("idProducto") Long idProducto, MultimediaDTO multimedia) throws BusinessLogicException {
        return new MultimediaDTO(MultimediasLogic.createMultimedia(idProducto, multimedia.toEntity()));
    }

    /**
     * PUT http://localhost:8080/Farmacia-web/api/Productos/1/Multimedias/1
     * @param idProducto
     * @param id
     * @param multimedia
     * @return
     * @throws BusinessLogicException 
     */
    @PUT 
    @Path("{id: \\d+}")
    public MultimediaDTO updateMultimedia(@PathParam("idProducto") Long idProducto, @PathParam("id") Long id, MultimediaDTO multimedia) throws BusinessLogicException {
        multimedia.setId(id);
        MultimediaEntity entity = MultimediasLogic.getMultimedia(idProducto, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + idProducto + "/multimedias/" + id + " no existe.", 404);
        }
        return new MultimediaDTO(MultimediasLogic.updateMultimedia(idProducto, multimedia.toEntity()));

    }

    /**
     * DELETE http://localhost:8080/Farmacia-web/api/Productos/1/Multimedias/1

     * @param idProducto
     * @param id
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMultimedia(@PathParam("idProducto") Long idProducto, @PathParam("id") Long id) throws BusinessLogicException {
        MultimediaEntity entity = MultimediasLogic.getMultimedia(idProducto, id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + idProducto + "/multimedias/" + id + " no existe.", 404);
        }
        MultimediasLogic.deleteMultimedia(idProducto, id);
    }

    private List<MultimediaDTO> listEntity2DTO(List<MultimediaEntity> entityList) {
        List<MultimediaDTO> list = new ArrayList<>();
        for (MultimediaEntity entity : entityList) {
            list.add(new MultimediaDTO(entity));
        }
        return list;
    }

    

}
