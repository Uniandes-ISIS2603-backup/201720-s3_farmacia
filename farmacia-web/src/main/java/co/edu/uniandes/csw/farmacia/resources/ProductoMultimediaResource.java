/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.farmacia.resources;

import co.edu.uniandes.csw.farmacia.dtos.MultimediaDTO;
import co.edu.uniandes.csw.farmacia.ejb.ProductoLogic;
import co.edu.uniandes.csw.farmacia.entities.MultimediaEntity;
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
import javax.ws.rs.core.MediaType;

/**
 * URL: Productos/(productosId : \\d+)/Multimedias
 * @author lm.gonzalezf
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoMultimediaResource {
    
    @Inject
    private ProductoLogic productosLogic;
    
    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDetailDTO convertida.
     * 
     */
    private List<MultimediaDTO> multimediaListEntity2DTO(List<MultimediaEntity> entityList) {
        List<MultimediaDTO> list = new ArrayList<>();
        for (MultimediaEntity entity : entityList) {
            list.add(new MultimediaDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     * 
     */
    private List<MultimediaEntity> multimediaListDTO2Entity(List<MultimediaDTO> dtos) {
        List<MultimediaEntity> list = new ArrayList<>();
        for (MultimediaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    /**
     * Obtiene una colección de instancias de BookDetailDTO asociadas a una
     * instancia de Editorial
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @return Colección de instancias de BookDetailDTO asociadas a la instancia
     * de Editorial
     * 
     */
    @GET
    public List<MultimediaDTO> listMultimedia(@PathParam("ProductosId") Long editorialsId) {
        return multimediaListEntity2DTO(productosLogic.listMultimedia(editorialsId));
    }
    
    /**
     * Obtiene una instancia de Book asociada a una instancia de Editorial
     *
     * @param productoId Identificador de la instancia de Editorial
     * @param multimediasId Identificador de la instancia de Book
     * @return
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * 
     */
    @GET
    @Path("{MultimediasId: \\d+}")
    public MultimediaDTO getMultimedia(@PathParam("MultimediasId") Long productoId, @PathParam("MultimediasId") Long multimediasId) throws BusinessLogicException {
        return new MultimediaDTO(productosLogic.getMultimedia(productoId, multimediasId));
    }
    
    /**
     * Asocia un Book existente a un Editorial
     *
     * @param productosId Identificador de la instancia de Editorial
     * @param multimediasId Identificador de la instancia de Book
     * @return Instancia de BookDetailDTO que fue asociada a Editorial
     * 
     */
    @POST
    @Path("{MultimediasId: \\d+}")
    public MultimediaDTO addMultimedia(@PathParam("ProductosId") Long productosId, @PathParam("MultimediasId") Long multimediasId) {
        return new MultimediaDTO(productosLogic.addMultimedia(multimediasId,productosId));
    }
    
    /**
     * Remplaza las instancias de Book asociadas a una instancia de Editorial
     *
     * @param productosId Identificador de la instancia de Editorial
     * @param multimedias Colección de instancias de BookDTO a asociar a instancia de
     * Editorial
     * @return Nueva colección de BookDTO asociada a la instancia de Editorial
     * 
     */
    @PUT
    public List<MultimediaDTO> replaceBooks(@PathParam("ProductosId") Long productosId, List<MultimediaDTO> multimedias) throws BusinessLogicException {
        return multimediaListEntity2DTO(productosLogic.replaceMultimedia(productosId, multimediaListDTO2Entity(multimedias)));
    }

    /**
     * Desasocia un Book existente de un Editorial existente
     *
     * @param editorialsId Identificador de la instancia de Editorial
     * @param booksId Identificador de la instancia de Book
     * 
     */
    @DELETE
    @Path("{MultimediasId: \\d+}")
    public void removeMultimedia(@PathParam("ProductosId") Long editorialsId, @PathParam("ProductosId") Long booksId) {
        productosLogic.removeMultimedia(booksId,editorialsId);
    }
}
