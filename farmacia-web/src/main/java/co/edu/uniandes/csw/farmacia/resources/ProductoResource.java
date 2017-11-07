package co.edu.uniandes.csw.farmacia.resources;


/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import co.edu.uniandes.csw.farmacia.dtos.ProductoDTO;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.ejb.ProductoLogic;
import co.edu.uniandes.csw.farmacia.ejb.ItemLogic;
import co.edu.uniandes.csw.farmacia.entities.ItemEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
 * Clase que implementa el recurso REST correspondiente a "Productos".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Productos". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Productos"
 *
 * @author lm.gonzalezf
 *
 */
@Path("Productos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ProductoResource {

    @Inject
    ProductoLogic ProductosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @Inject
    ItemLogic ItemsLogic;

    private static final Logger LOGGER = Logger.getLogger(ProductoResource.class.getName());

    /**
     * POST http://localhost:8080/Productos-web/api/Productos
     *
     * @param Producto correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "ProductoDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public ProductoDTO createProducto(ProductoDTO Producto) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ProductoEntity ProductoEntity = Producto.toEntity();
        // Invoca la lógica para crear la Producto nueva y su respectivo item
        ProductoEntity nuevoProducto = ProductosLogic.createProducto(ProductoEntity);
        ItemEntity nuevoItem = ItemsLogic.createItemProd(ProductoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ProductoDTO(nuevoProducto);
    }

    /**
     * GET para todas las Productos.
     * http://localhost:8080/Productos-web/api/Productos
     *
     * @return la lista de todas las Productoes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ProductoDTO> getProductos() throws BusinessLogicException {
        return listEntity2DTO(ProductosLogic.getProducto());
    }
    
    
    /**
     * GET para una editorial
     * http://localhost:8080/Productos-web/api/Productos/1
     *
     * @param id corresponde al id de la editorial buscada.
     * @return La editorial encontrada. Ejemplo: { "type": "editorialDetailDTO",
     * "id": 1, "name": "Norma" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial buscada se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public ProductoDTO getProducto(@PathParam("id") Long id) throws BusinessLogicException {
        ProductoEntity entity = ProductosLogic.getProducto(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + id + " no existe.", 404);
        }
        return new ProductoDTO(ProductosLogic.getProducto(id));
    }
   
    /**
     * PUT http://localhost:8080/Productos-web/api/Productos/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde al producto a actualizar.
     * @param producto corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La editorial actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public ProductoDTO updateProducto(@PathParam("id") Long id, ProductoDTO producto) throws BusinessLogicException {
        producto.setId(id);
        ProductoEntity entity = ProductosLogic.getProducto(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /productos/" + id + " no existe.", 404);
        }
        return new ProductoDTO(ProductosLogic.updateproducto(id, producto.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/backstepbystep-web/api/editorials/1
     *
     * @param id corresponde a la editorial a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial a actualizar se retorna un
     * 404 con el mensaje.
     * @throws java.sql.SQLException
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProducto(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Producto con id {0}", id);
        ProductoEntity entity = ProductosLogic.getProducto(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Productos/" + id + " no existe.", 404);
        }
        
        ItemsLogic.deleteitem(entity.getItemAsociado().getId());
        

    }
    

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ProductoEntity a una lista de
     * objetos ProductoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Productoes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Productoes en forma DTO (json)
     */
    private List<ProductoDTO> listEntity2DTO(List<ProductoEntity> entityList) {
        List<ProductoDTO> list = new ArrayList<>();
        for (ProductoEntity entity : entityList) {
            list.add(new ProductoDTO(entity));
        }
        return list;
    }

}
