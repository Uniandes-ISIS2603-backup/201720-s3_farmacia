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
        LOGGER.info("Inicia proceso de creación de producto");
        // Verifica la regla de negocio que dice que no puede haber dos productos con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Producto con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear el producto

        LOGGER.info("Termina proceso de creación de producto");
        return persistence.create(entity);
    }

    /**
     *
     * Obtener todas los productos existentes en la base de datos.
     *
     * @return una lista de productos.
     */
    public List<ProductoEntity> getProducto() {
        LOGGER.info("Inicia proceso de consultar todas los productos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ProductoEntity> productos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas los productos");
        return productos;
    }

    /**
     *
     * Obtener un producto por medio de su id.
     *
     * @param id: id de el producto para ser buscada.
     * @return el producto solicitada por medio de su id.
     */
    public ProductoEntity getProducto(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar producto con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ProductoEntity producto = persistence.find(id);
        if (producto == null) {
            LOGGER.log(Level.SEVERE, "El producto con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar producto con id={0}", id);
        return producto;
    }
    
    /**
     *
     * Actualizar una producto.
     *
     * @param id: id de la producto para buscarla en la base de datos.
     * @param entity: producto con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return la producto con los cambios actualizados en la base de datos.
     */
    public ProductoEntity updateproducto(Long id, ProductoEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar producto con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ProductoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar producto con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Borrar un producto
     *
     * @param id: id de la producto a borrar
     */
    public void deleteproducto(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar producto con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar producto con id={0}", id);
    }
    
     /**
     * Obtiene una colección de instancias de MultimediaEntity asociadas a una
     * instancia de Producto
     *
     * @param productoId Identificador de la instancia de Producto
     * @return Colección de instancias de MultimediaEntity asociadas a la instancia
     * de Producto
     * 
     */
    public List<MultimediaEntity> listMultimedia(Long productoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", productoId);
        return getProducto(productoId).getMultimedia();
    }
    
    
    
    

    /**
     * Obtiene una instancia de MultimediaEntity asociada a una instancia de Producto
     *
     * @param productoId Identificador de la instancia de Producto
     * @param multimediaId Identificador de la instancia de Multimedia
     * 
     */
    public MultimediaEntity getMultimedia(Long productoId, Long multimediaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una multimedia del producto con id = {0}", productoId);
        List<MultimediaEntity> list = getProducto(productoId).getMultimedia();
        MultimediaEntity multimediaEntity = new MultimediaEntity();
        multimediaEntity.setId(multimediaId);
        int index = list.indexOf(multimediaEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia una Multimedia existente a un Producto
     *
     * @param productoId Identificador de la instancia de Producto
     * @param multimediaId Identificador de la instancia de Multimedia
     * @return Instancia de MultimediaEntity que fue asociada a Producto
     * 
     */
    public MultimediaEntity addMultimedia(Long productoId, Long multimediaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar una multimedia al producto con id = {0}", productoId);
        ProductoEntity productoEntity = getProducto(productoId);
        MultimediaEntity multimediaEntity = new MultimediaEntity();
        multimediaEntity.setId(multimediaId);
        productoEntity.getMultimedia().add(multimediaEntity);
        return getMultimedia(productoId, multimediaId);
    }

    /**
     * Remplaza las instancias de Multimedia asociadas a una instancia de Producto
     *
     * @param productoId Identificador de la instancia de Producto
     * @param list Colección de instancias de MultimediaEntity a asociar a instancia
     * de Producto
     * @return Nueva colección de MultimediaEntity asociada a la instancia de Producto
     * 
     */
    public List<MultimediaEntity> replaceMultimedia(Long productoId, List<MultimediaEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar una multimedia del producto con id = {0}", productoId);
        ProductoEntity productoEntity = getProducto(productoId);
        productoEntity.setMultimedia(list);
        return productoEntity.getMultimedia();
    }

    /**
     * Desasocia una Multimedia existente de un Producto existente
     *
     * @param productoId Identificador de la instancia de Producto
     * @param multimediaId Identificador de la instancia de Multimedia
     * 
     */
    public void removeMultimedia(Long productoId, Long multimediaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una multmedia del producto con id = {0}", productoId);
        ProductoEntity entity = getProducto(productoId);
        MultimediaEntity multimediaEntity = new MultimediaEntity();
        multimediaEntity.setId(multimediaId);
        entity.getMultimedia().remove(multimediaEntity);
    }
}
