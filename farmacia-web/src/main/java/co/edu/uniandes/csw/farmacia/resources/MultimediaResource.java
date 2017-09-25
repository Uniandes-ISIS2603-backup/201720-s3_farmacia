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

import co.edu.uniandes.csw.farmacia.ejb.MultimediaLogic;
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
 * Clase que implementa el recurso REST correspondiente a "Multimedias".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Multimedias". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/Multimedias"
 *
 * @author lm.gonzalezf
 *
 */
@Path("Multimedias")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class MultimediaResource {

    @Inject
    MultimediaLogic MultimediasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(MultimediaResource.class.getName());

    /**
     * POST http://localhost:8080/Multimedias-web/api/Multimedias
     *
     * @param Multimedia correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "MultimediaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public MultimediaDTO createMultimedia(MultimediaDTO Multimedia) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        MultimediaEntity multimediaEntity = Multimedia.toEntity();
        // Invoca la lógica para crear la Multimedia nueva
        MultimediaEntity nuevoMultimedia = MultimediasLogic.createMultimedia( multimediaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new MultimediaDTO(nuevoMultimedia);
    }

    /**
     * GET para todas las Multimedias.
     * http://localhost:8080/Multimedias-web/api/Multimedias
     *
     * @return la lista de todas las Multimediaes en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<MultimediaDTO> getMultimedias() throws BusinessLogicException {
        return listEntity2DTO(MultimediasLogic.getMultimedias());
    }

   
    /**
     * PUT http://localhost:8080/Multimedias-web/api/Multimedias/1 Ejemplo
     * json { "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Multimedia a actualizar.
     * @param Multimedias corresponde  al objeto con los cambios que se van a
     * realizar.
     * @return La Multimedia actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Multimedia a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public MultimediaDTO updateMultimedia(@PathParam("id") Long id, MultimediaDTO Multimedias) throws BusinessLogicException, UnsupportedOperationException {
          throw new UnsupportedOperationException("Este servicio  no está implementado");
      
    }

    /**
     * DELETE http://localhost:8080/Multimedias-web/api/Multimedias/{id}
     *
     * @param id corresponde a la Multimedia a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Multimedia a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMultimedia(@PathParam("id") Long id) throws BusinessLogicException {
         throw new UnsupportedOperationException("Este servicio no está implementado");
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos MultimediaEntity a una lista de
     * objetos MultimediaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Multimediaes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Multimediaes en forma DTO (json)
     */
    private List<MultimediaDTO> listEntity2DTO(List<MultimediaEntity> entityList) {
        List<MultimediaDTO> list = new ArrayList<>();
        for (MultimediaEntity entity : entityList) {
            list.add(new MultimediaDTO(entity));
        }
        return list;
    }

}
