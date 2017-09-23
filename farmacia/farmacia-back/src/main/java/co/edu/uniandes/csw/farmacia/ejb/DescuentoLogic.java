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
import co.edu.uniandes.csw.farmacia.entities.DescuentoEntity;
import co.edu.uniandes.csw.farmacia.persistence.DescuentoPersistence;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author lm.gonzalezf
 */
@Stateless
public class DescuentoLogic {

    private static final Logger LOGGER = Logger.getLogger(DescuentoLogic.class.getName());

    @Inject
    private DescuentoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public DescuentoEntity createDescuento(DescuentoEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Descuento");
        // Invoca la persistencia para crear la DescuentoLogic
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Descuento");
        return entity;
    }

    /**
     * 
     * Obtener todas las Descuentoes existentes en la base de datos.
     *
     * @return una lista de Descuentoes.
     */
    public List<DescuentoEntity> getDescuentos() {
        LOGGER.info("Inicia proceso de consultar todas las Descuentoes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<DescuentoEntity> Descuentos = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Descuentoes");
        return Descuentos;
    }

    /**
     * Descuento con ID dado
     * @param id del Descuento
     * @return Descuento
     * @throws BusinessLogicException no existe el Descuento con el id dado
     */
    public DescuentoEntity getDescuento (Long id) throws BusinessLogicException{
         LOGGER.log(Level.INFO, "Inicia proceso de consultar Descuento con id={0}", id);
        DescuentoEntity Descuento = persistence.find(id);
        if (Descuento == null) {
            LOGGER.log(Level.SEVERE, "El Descuento con el id {0} no existe", id);
            throw new BusinessLogicException("El Descuento no existe cpn el id dado");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Descuento con id={0}", id);
        return Descuento;
    }
    
    
    /**
     * Actualiza la informacion del Descuento con el ID dado
     * @param id del Descuento
     * @return datos actualizados del Descuento
     * @throws BusinessLogicException no existe el Descuento con el ID dado 
     */
    public DescuentoEntity updateDescuento(Long id)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un Descuento con id={0}", id);
        DescuentoEntity entity = getDescuento(id);
        if (entity !=null) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        DescuentoEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Descuento con id={0}", entity.getId());
        return newEntity;
    }
    /**
     * Elimina el Descuento con el id dado
     * @param id del Descuento que se desea eliminar
     */
    public void deleteBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Descuento con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un Descuento con id={0}", id);
    }
}

