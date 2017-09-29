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
import co.edu.uniandes.csw.farmacia.entities.FarmaciaEntity;
import co.edu.uniandes.csw.farmacia.persistence.FarmaciaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class FarmaciaLogic {

    private static final Logger LOGGER = Logger.getLogger(FarmaciaLogic.class.getName());

    @Inject
    private FarmaciaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public FarmaciaEntity createFarmacia(FarmaciaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Farmacia");
        // Invoca la persistencia para crear la FarmaciaLogic
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Farmacia");
        return entity;
    }

    /**
     * Farmacia con ID dado
     * @param id de la farmacia
     * @return Farmacia
     * @throws BusinessLogicException no existe la farmacia con el id dado
     */
    public FarmaciaEntity getFarmacia(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar la farmacia con id=(0)", id);
        FarmaciaEntity farmacia = persistence.find(id);
        if(farmacia == null)
        {
            LOGGER.log(Level.SEVERE, "La farmacia con id (0) no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina el proceso de consultar farmacia con id=(0)", id);
        return farmacia;
    }
    
    /**
     * Actualiza la informaciòn de la farmacia con el ID dado
     * @param id de la farmacia
     * @param farmacia Orden con la nueva informacion
     * @return datos actuallizados de la farmacia
     * @throws BusinessLogicException no existe la farmacia con el ID dado
     */
    public FarmaciaEntity updateFarmacia(Long id, FarmaciaEntity farmacia)throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de actualizar la farmacia con id=(0)", id);
        FarmaciaEntity nEntity = persistence.update(farmacia);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizar la farmacia con id=(0)", farmacia.getId());
        return nEntity;
    }
    
    /**
     * Elimina la farmacia con el id dado
     * @param id de la farmacia que se desea eliminar
     */
    public void deleteFarmacia(Long id)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de borrar una farmacia con id=(0)", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar una farmacia con id=(0)", id);
    }
    
    /**
     * 
     * Obtener todas las Farmaciaes existentes en la base de datos.
     *
     * @return una lista de Farmaciaes.
     */
    public List<FarmaciaEntity> getFarmacias() {
        LOGGER.info("Inicia proceso de consultar todas las Farmaciaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<FarmaciaEntity> Farmacias = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Farmaciaes");
        return Farmacias;
    }


}