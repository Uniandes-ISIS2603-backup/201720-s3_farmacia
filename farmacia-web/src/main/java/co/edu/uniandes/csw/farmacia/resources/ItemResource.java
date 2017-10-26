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

import co.edu.uniandes.csw.farmacia.dtos.ItemDTO;
import co.edu.uniandes.csw.farmacia.entities.ItemEntity;
import co.edu.uniandes.csw.farmacia.ejb.ItemLogic;
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
 * Clase que implementa el recurso REST correspondiente a "Items".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Items". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Items"
 *
 * @author lm.gonzalezf
 *
 */
@Path("Items")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ItemResource {

    @Inject
    ItemLogic ItemsLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(ItemResource.class.getName());

    /**
     * POST http://localhost:8080/Items-web/api/Items
     *
     * @param Item correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "ItemDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public ItemDTO createItem(ItemDTO Item) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ItemEntity ItemEntity = Item.toEntity();
        // Invoca la lógica para crear la Item nueva
        ItemEntity nuevoItem = ItemsLogic.createitem(ItemEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ItemDTO(nuevoItem);
    }

    /**
     * GET para todas las Items.
     * http://localhost:8080/Items-web/api/Items
     *
     * @return la lista de todas las Itemes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ItemDTO> getItems() throws BusinessLogicException {
        return listEntity2DTO(ItemsLogic.getitem());
    }
    
    
    /**
     * GET para una editorial
     * http://localhost:8080/Items-web/api/Items/1
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
    public ItemDTO getItem(@PathParam("id") Long id) throws BusinessLogicException {
        ItemEntity entity = ItemsLogic.getitem(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /items/" + id + " no existe.", 404);
        }
        return new ItemDTO(ItemsLogic.getitem(id));
    }
   
    /**
     * PUT http://localhost:8080/Items-web/api/Items/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde al item a actualizar.
     * @param item corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return La editorial actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la editorial a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public ItemDTO updateItem(@PathParam("id") Long id, ItemDTO item) throws BusinessLogicException {
        item.setId(id);
        ItemEntity entity = ItemsLogic.getitem(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /items/" + id + " no existe.", 404);
        }
        return new ItemDTO(ItemsLogic.updateitem(id, item.toEntity()));
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
    public void deleteItem(@PathParam("id") Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Item con id {0}", id);
        ItemEntity entity = ItemsLogic.getitem(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Items/" + id + " no existe.", 404);
        }
        ItemsLogic.deleteitem(id);

    }
    

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ItemEntity a una lista de
     * objetos ItemDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Itemes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Itemes en forma DTO (json)
     */
    private List<ItemDTO> listEntity2DTO(List<ItemEntity> entityList) {
        List<ItemDTO> list = new ArrayList<>();
        for (ItemEntity entity : entityList) {
            list.add(new ItemDTO(entity));
        }
        return list;
    }

}
