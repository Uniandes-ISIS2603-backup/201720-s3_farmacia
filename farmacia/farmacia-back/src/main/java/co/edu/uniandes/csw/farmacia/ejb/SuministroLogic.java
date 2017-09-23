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
import co.edu.uniandes.csw.farmacia.entities.SuministroEntity;
import co.edu.uniandes.csw.farmacia.persistence.SuministroPersistence;
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
public class SuministroLogic {

    private static final Logger LOGGER = Logger.getLogger(SuministroLogic.class.getName());

    @Inject
    private SuministroPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public SuministroEntity createSuministro(SuministroEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Suministro");
        // Invoca la persistencia para crear la SuministroLogic
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Suministro");
        return entity;
    }

    /**
     * 
     * Obtener todas las Suministroes existentes en la base de datos.
     *
     * @return una lista de Suministroes.
     */
    public List<SuministroEntity> getSuministros() {
        LOGGER.info("Inicia proceso de consultar todas las Suministroes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<SuministroEntity> Suministros = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Suministroes");
        return Suministros;
    }

    /**
     * Suministro con ID dado
     * @param id del Suministro
     * @return Suministro
     * @throws BusinessLogicException no existe el Suministro con el id dado
     */
    public SuministroEntity getSuministro (Long id) throws BusinessLogicException{
         LOGGER.log(Level.INFO, "Inicia proceso de consultar Suministro con id={0}", id);
        SuministroEntity Suministro = persistence.find(id);
        if (Suministro == null) {
            LOGGER.log(Level.SEVERE, "El Suministro con el id {0} no existe", id);
            throw new BusinessLogicException("El Suministro no existe cpn el id dado");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar Suministro con id={0}", id);
        return Suministro;
    }
    
    
    /**
     * Actualiza la informacion del Suministro con el ID dado
     * @param id del Suministro
     * @return datos actualizados del Suministro
     * @throws BusinessLogicException no existe el Suministro con el ID dado 
     */
    public SuministroEntity updateSuministro(Long id)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un Suministro con id={0}", id);
        SuministroEntity entity = getSuministro(id);
        if (entity !=null) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        SuministroEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Suministro con id={0}", entity.getId());
        return newEntity;
    }
    /**
     * Elimina el Suministro con el id dado
     * @param id del Suministro que se desea eliminar
     */
    public void deleteBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un Suministro con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un Suministro con id={0}", id);
    }
}

