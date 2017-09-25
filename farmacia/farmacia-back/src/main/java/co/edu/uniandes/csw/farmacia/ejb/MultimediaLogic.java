package co.edu.uniandes.csw.farmacia.ejb;
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

import co.edu.uniandes.csw.farmacia.entities.MultimediaEntity;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.MultimediaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.gonzalezf
 */
@Stateless
public class MultimediaLogic {

    private static final Logger LOGGER = Logger.getLogger(MultimediaLogic.class.getName());

    @Inject
    private MultimediaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private ProductoLogic productoLogic;

    /**
     * Obtiene la lista de los registros de Multimedia que pertenecen a un Producto.
     *
     * @param productoid id del Producto el cual es padre de los Multimedia.
     * @return Colección de objetos de MultimediaEntity.
     * @throws co.edu.uniandes.csw.productostore.exceptions.BusinessLogicException
     */
    public List<MultimediaEntity> getMultimedia(Long productoid) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de consultar todos los multimedias");
        ProductoEntity producto = productoLogic.getProducto(productoid);
        if (producto.getMultimedia() == null) {
            throw new BusinessLogicException("El producto que consulta aún no tiene multimedias");
        }
        if (producto.getMultimedia().isEmpty()) {
            throw new BusinessLogicException("El libro que consulta aún no tiene multimedias");
        }
        return producto.getMultimedia();
    }

    /**
     * Obtiene los datos de una instancia de Multimedia a partir de su ID.
     *
     * @param productoid
     * @pre La existencia del elemento padre Book se debe garantizar.
     * @param reviewid) Identificador del Multimedia a consultar
     * @return Instancia de MultimediaEntity con los datos del Multimedia consultado.
     * 
     */
    public MultimediaEntity getMultimedia(Long productoid, Long reviewid) {
        return persistence.find(productoid, reviewid);
    }

    /**
     * Se encarga de crear un Multimedia en la base de datos.
     *
     * @param entity Objeto de MultimediaEntity con los datos nuevos
     * @param productoid id del Book el cual sera padre del nuevo Multimedia.
     * @return Objeto de MultimediaEntity con los datos nuevos y su ID.
     * 
     */
    public MultimediaEntity createMultimedia(Long productoid, MultimediaEntity entity) {
        LOGGER.info("Inicia proceso de crear review");
        ProductoEntity producto = productoLogic.getProducto(productoid);
        entity.setProducto(producto);
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Multimedia.
     *
     * @param entity Instancia de MultimediaEntity con los nuevos datos.
     * @param productoid id del Book el cual sera padre del Multimedia actualizado.
     * @return Instancia de MultimediaEntity con los datos actualizados.
     * 
     */
    public MultimediaEntity updateMultimedia(Long productoid, MultimediaEntity entity) {
        LOGGER.info("Inicia proceso de actualizar review");
        ProductoEntity producto = productoLogic.getProducto(productoid);
        entity.setProducto(producto);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Multimedia de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param productoid id del Book el cual es padre del Multimedia.
     * 
     */
    public void deleteMultimedia(Long productoid, Long id) {
        LOGGER.info("Inicia proceso de borrar review");
        MultimediaEntity old = getMultimedia(productoid, id);
        persistence.delete(old.getId());
    }


}
