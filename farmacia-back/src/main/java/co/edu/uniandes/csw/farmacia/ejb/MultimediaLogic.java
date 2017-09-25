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
import co.edu.uniandes.csw.farmacia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.farmacia.persistence.MultimediaPersistence;
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


}
