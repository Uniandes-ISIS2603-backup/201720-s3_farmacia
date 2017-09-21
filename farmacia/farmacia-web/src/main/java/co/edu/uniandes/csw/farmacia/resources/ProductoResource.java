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

import co.edu.uniandes.csw.farmacia.dtos.ProductoDetailDTO;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.ejb.ProductoLogic;
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
    public ProductoDetailDTO createProducto(ProductoDetailDTO Producto) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ProductoEntity ProductoEntity = Producto.toEntity();
        // Invoca la lógica para crear la Producto nueva
        ProductoEntity nuevoProducto = ProductosLogic.createProducto(ProductoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ProductoDetailDTO(nuevoProducto);
    }

    /**
     * GET para todas las Productos.
     * http://localhost:8080/Productos-web/api/Productos
     *
     * @return la lista de todas las Productoes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ProductoDetailDTO> getProductos() throws BusinessLogicException {
        return listEntity2DetailDTO(ProductosLogic.getProductos());
    }

   
    /**
     * PUT http://localhost:8080/Productos-web/api/Productos/1 Ejemplo
     * json { "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Producto a actualizar.
     * @param Productos corresponde  al objeto con los cambios que se van a
     * realizar.
     * @return La Producto actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Producto a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public ProductoDetailDTO updateProducto(@PathParam("id") Long id, ProductoDetailDTO Productos) throws BusinessLogicException, UnsupportedOperationException {
          throw new UnsupportedOperationException("Este servicio  no está implementado");
      
    }

    /**
     * DELETE http://localhost:8080/Productos-web/api/Productos/{id}
     *
     * @param id corresponde a la Producto a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Producto a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProducto(@PathParam("id") Long id) throws BusinessLogicException {
         throw new UnsupportedOperationException("Este servicio no está implementado");
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
    private List<ProductoDetailDTO> listEntity2DetailDTO(List<ProductoEntity> entityList) {
        List<ProductoDetailDTO> list = new ArrayList<>();
        for (ProductoEntity entity : entityList) {
            list.add(new ProductoDetailDTO(entity));
        }
        return list;
    }

}
