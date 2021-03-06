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


import co.edu.uniandes.csw.farmacia.entities.ItemEntity;
import co.edu.uniandes.csw.farmacia.entities.ProductoEntity;
import co.edu.uniandes.csw.farmacia.entities.SuministroEntity;
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.ItemPersistence;
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
public class ItemLogic {

    
    private static final Logger LOGGER = Logger.getLogger(ItemLogic.class.getName());

    @Inject
    private ItemPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    @Inject
    private ProductoPersistence algo;
    
    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public ItemEntity createitem(ItemEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de item");
        // Verifica la regla de negocio que dice que no puede haber dos items con el mismo nombre
        if (persistence.find(entity.getId()) != null) {
            throw new BusinessLogicException("Ya existe una item con el nombre \"" + entity.getId() + "\"");
        }
        // Invoca la persistencia para crear el item

        LOGGER.info("Termina proceso de creación de item");
        return persistence.create(entity);
    }
    
    public ItemEntity createItemProd(ProductoEntity entity)
    {
        LOGGER.info("Inicia proceso de creacion de item");
        ItemEntity nuevoItem = new ItemEntity();
        nuevoItem.setProductoEntity(entity);
        Long id = algo.findByName(entity.getName()).getId();
        nuevoItem.setId(id+10000);
        nuevoItem.setCosto(entity.getCosto());
        return persistence.create(nuevoItem);
    }
    
    public ItemEntity createItemSum(SuministroEntity entity)
    {
        LOGGER.info("Inicia proceso de creación de item");
        ItemEntity nuevoItem = new ItemEntity();
        
        nuevoItem.setSuministroAsociado(entity);
        return persistence.create(nuevoItem);
    }

    /**
     *
     * Obtener todas los items existentes en la base de datos.
     *
     * @return una lista de items.
     */
    public List<ItemEntity> getitem() {
        LOGGER.info("Inicia proceso de consultar todas los items");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ItemEntity> items = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas los items");
        return items;
    }

    /**
     *
     * Obtener un item por medio de su id.
     *
     * @param id: id de el item para ser buscada.
     * @return el item solicitada por medio de su id.
     */
    public ItemEntity getitem(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar item con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ItemEntity item = persistence.find(id);
        if (item == null) {
            LOGGER.log(Level.SEVERE, "El item con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar item con id={0}", id);
        return item;
    }
    
    /**
     *
     * Actualizar una item.
     *
     * @param id: id de la item para buscarla en la base de datos.
     * @param entity: item con los cambios para ser actualizada, por
     * ejemplo el nombre.
     * @return la item con los cambios actualizados en la base de datos.
     */
    public ItemEntity updateitem(Long id, ItemEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar item con id={0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ItemEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar item con id={0}", entity.getId());
        return newEntity;
    }

    /**
     * Borrar un item
     *
     * @param id: id de la item a borrar
     */
    public void deleteitem(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar item con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar item con id={0}", id);
    }
    
     
}
