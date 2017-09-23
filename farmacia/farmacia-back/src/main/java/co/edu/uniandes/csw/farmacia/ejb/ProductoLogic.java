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

import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.persistence.ProductoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.gonzalezf
 */
@Stateless
public class ProductoLogic {

    private static final Logger LOGGER = Logger.getLogger(ProductoLogic.class.getName());

    @Inject
    private ProductoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ProductoEntity createProducto(ProductoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Producto");
        // Invoca la persistencia para crear la ProductoLogic
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Producto");
        return entity;
    }

    /**
     * 
     * Obtener todas las Productoes existentes en la base de datos.
     *
     * @return una lista de Productoes.
     */
    public List<ProductoEntity> getProductos() {
        LOGGER.info("Inicia proceso de consultar todas las Productoes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ProductoEntity> Productos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Productoes");
        return Productos;
    }

    /**
     * Producto con ID dado
     * @param id del Producto
     * @return Producto
     * @throws BusinessLogicException no existe el Producto con el id dado
     */
    public ProductoEntity getProducto (Long id) throws BusinessLogicException{
         LOGGER.log(Level.INFO, "Inicia proceso de consultar Producto con id={0}", id);
        ProductoEntity Producto = persistence.find(id);
        if (Producto == null) {
            LOGGER.log(Level.SEVERE, "El Producto con el id {0} no existe", id);
            throw new BusinessLogicException("El Producto no existe cpn el id dado");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Producto con id={0}", id);
        return Producto;
    }
    
    
    /**
     * Actualiza la informacion del Producto con el ID dado
     * @param id del Producto
     * @return datos actualizados del Producto
     * @throws BusinessLogicException no existe el Producto con el ID dado 
     */
    public ProductoEntity updateProducto(Long id)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un Producto con id={0}", id);
        ProductoEntity entity = getProducto(id);
        if (entity !=null) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        ProductoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Producto con id={0}", entity.getId());
        return newEntity;
    }
    /**
     * Elimina el Producto con el id dado
     * @param id del Producto que se desea eliminar
     */
    public void deleteBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Producto con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un Producto con id={0}", id);
    }
}
