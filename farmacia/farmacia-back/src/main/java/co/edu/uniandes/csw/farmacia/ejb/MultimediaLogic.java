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
import co.edu.uniandes.csw.farmacia.entities.MultimediaEntity;
import co.edu.uniandes.csw.farmacia.persistence.MultimediaPersistence;
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
public class MultimediaLogic {

    private static final Logger LOGGER = Logger.getLogger(MultimediaLogic.class.getName());

    @Inject
    private MultimediaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public MultimediaEntity createMultimedia(MultimediaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Multimedia");
        // Invoca la persistencia para crear la MultimediaLogic
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Multimedia");
        return entity;
    }

    /**
     * 
     * Obtener todas las Multimediaes existentes en la base de datos.
     *
     * @return una lista de Multimediaes.
     */
    public List<MultimediaEntity> getMultimedias() {
        LOGGER.info("Inicia proceso de consultar todas las Multimediaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<MultimediaEntity> Multimedias = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Multimediaes");
        return Multimedias;
    }

    /**
     * Multimedia con ID dado
     * @param id del Multimedia
     * @return Multimedia
     * @throws BusinessLogicException no existe el Multimedia con el id dado
     */
    public MultimediaEntity getMultimedia (Long id) throws BusinessLogicException{
         LOGGER.log(Level.INFO, "Inicia proceso de consultar Multimedia con id={0}", id);
        MultimediaEntity Multimedia = persistence.find(id);
        if (Multimedia == null) {
            LOGGER.log(Level.SEVERE, "El Multimedia con el id {0} no existe", id);
            throw new BusinessLogicException("El Multimedia no existe cpn el id dado");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Multimedia con id={0}", id);
        return Multimedia;
    }
    
    
    /**
     * Actualiza la informacion del Multimedia con el ID dado
     * @param id del Multimedia
     * @return datos actualizados del Multimedia
     * @throws BusinessLogicException no existe el Multimedia con el ID dado 
     */
    public MultimediaEntity updateMultimedia(Long id)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un Multimedia con id={0}", id);
        MultimediaEntity entity = getMultimedia(id);
        if (entity !=null) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        MultimediaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Multimedia con id={0}", entity.getId());
        return newEntity;
    }
    /**
     * Elimina el Multimedia con el id dado
     * @param id del Multimedia que se desea eliminar
     */
    public void deleteBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Multimedia con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un Multimedia con id={0}", id);
    }
}
